package SistemaRH.BDconfig;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import SistemaRH.model.Especialidade;
import SistemaRH.model.Historico_Vaga;
import SistemaRH.model.Vaga;

public class DAO_Util {
	public static synchronized Especialidade getEspCand(String cpf) {
		Especialidade espec = new Especialidade();
		try{
			ConexaoBD a = new ConexaoBD();
			a.iniciaBd();
			Connection c = a.getConexao();
			PreparedStatement ps = (PreparedStatement) c.prepareStatement("SELECT * FROM concurso_especialidade as ce "
					+ "JOIN concurso_candidato as cc ON cc.id_concurso_especialidade = ce.id_concurso_especialidade"
					+ "WHERE cc.cd_cpf = ?");
			ps.setString(1, cpf);
			ResultSet rs = ps.executeQuery();
			espec.setId_especialidade(rs.getInt("id_concurso_especialidade"));
			espec.setPerfil(rs.getString("ds_perfil"));
			espec.setNome_espec(rs.getString("ds_especialidade"));
			espec.setRegiao(rs.getString("ds_regiao"));
			espec.setVagas_iniciais(rs.getInt("nu_vagas_iniciais"));
			espec.setVagas_amplicadas(rs.getInt("nu_vagas_amplidadas"));
			espec.setQtd_aprovados(rs.getInt("nu_aprovados"));
			espec.setVacancias(rs.getInt("nu_vacancias"));
			espec.setQtd_nomeados(rs.getInt("nu_nomeados"));
			espec.setQtd_exonerados(rs.getInt("nu_eliminados_exonerados"));
			espec.setCand_restantes(rs.getInt("nu_banco_restante"));
			espec.setId_concurso(rs.getInt("cd_processo"));
			ps.close();
			c.close();
			a.fechaBd();
		}catch(SQLException s) {
			s.getStackTrace();
			return null;
		}
		return espec;
	}
	public static synchronized Vaga getVaga(Especialidade espec,int id_vaga) {
		Vaga v = null;
		try {
			ConexaoBD a = new ConexaoBD();
			a.iniciaBd();
			Connection c = a.getConexao();
			//Fazendo a consulta no BD com o id da vaga passado como parametro
			PreparedStatement ps = (PreparedStatement) c.prepareStatement("SELECT * FROM concurso_vaga_historico as vh "
					+ "JOIN concurso_especialidade as ce ON ce.id_concurso_especialidade = vt.id_concurso_especialidade"
					+ "WHERE ce.ds_especialidade = ? and vt.id_nu_vaga = ?");
			ps.setString(1,espec.getNome_espec());
			ps.setInt(2, id_vaga);
			ResultSet rs = ps.executeQuery();
			v = new Vaga();
			//Verificando a flag da situacao da vaga e inserindo o status correspondente
			int id = rs.getInt("fl_vaga_ocupada");
			if(id == 1) v.setStatus("Desocupada");
			else if(id == 2) v.setStatus("Em espera");
			else v.setStatus("Ocupada");
			//Atribuindo novos valores para outros atributos do objeto vaga
			v.setVaga_espec(espec);
			v.setHistorico(new Historico_Vaga(v));
			v.setNum_vaga(id_vaga);
			//Obtendo o numero do processo do concurso a qual pertence a vaga
			ps = (PreparedStatement) c.prepareStatement("SELECT ce.cd_processo FROM concurso_especialidade as ce"
					+ "JOIN concurso_vaga_especialidade as vt ON vt.id_concurso_especialidade = ce.id_concurso_especialidade"
					+ "WHERE vt.id_nu_vaga = ?");
			ps.setInt(1, id_vaga);
			rs = ps.executeQuery();
			v.setProcesso(rs.getString("cd_processo"));
			ps.close();
			c.close();
			a.fechaBd();
		}catch(SQLException s) {
			s.getStackTrace();
		}
		return v;
	}
	public static synchronized void setStatusCandidato(String status,String cpf) {
		try {
			ConexaoBD a = new ConexaoBD();
			a.iniciaBd();
			Connection c = a.getConexao();
			//Alterando o status do candidato nas tabelas concurso_candidato_situacao_tipo 
			//Pegando a atual situacao do candidato
			PreparedStatement ps = (PreparedStatement) c.prepareStatement("SELECT ct.ds_situacao FROM concurso_candidato_situacao_tipo as ct"
					+ "JOIN concurso_candidato_historico as ch ON ch.id_situacao_nova = ct.id_candidato_situacao"
					+ "WHERE ch.cd_cpf = ?");
			ps.setString(1, cpf);
			ResultSet rs = ps.executeQuery();
			String status_antigo = rs.getString("ds_situacao");
			//Persistindo esse status como  o antigo do candidato
			ps = (PreparedStatement) c.prepareStatement("UPDATE concurso_candidato_situacao_tipo ct"
					+ "JOIN concurso_candidato_historico cch ON cch.id_situacao_antiga = ct.id_candidato_situacao"
					+ "SET ct.ds_situacao = ?"
					+ "WHERE concurso_candidato.cd_cpf = ? and cch.cd_cpf = concurso_candidato.cd_cpf ");
			ps.setString(1,status_antigo);
			ps.setString(2, cpf);
			ps.executeQuery();
			//Agora persistindo no banco o novo status :
			ps = (PreparedStatement) c.prepareStatement("UPDATE concurso_candidato_situacao_tipo ct"
					+ "JOIN concurso_candidato_historico cch ON cch.id_situacao_nova = ct.id_candidato_situacao"
					+ "SET ct.ds_situacao = ?"
					+ "WHERE cch.cd_cpf = ? and cch.cd_cpf = concurso_candidato.cd_cpf ");
			ps.setString(1, status);
			ps.setString(2, cpf);
			ps.executeQuery();
			//Alterando a data nova mundaca na tabela concurso_candidato_historico
			Calendar ca = Calendar.getInstance();
			Date d = (Date) ca.getTime();
			ps = (PreparedStatement) c.prepareStatement("UPDATE concurso_candidato_historico "
					+ "SET dt_mudancao_situacao = ?"
					+ "WHERE cd_cpf = ?");
			ps.setDate(1, d);
			ps.setString(2, cpf);
			ps.executeQuery();
			ps.close();
			c.close();
			a.fechaBd();
		}catch(SQLException s) {
			s.getStackTrace();		
		}
	}
}
