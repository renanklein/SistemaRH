package br.uerj.rh.BDconfig;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import br.uerj.rh.model.Especialidade;
import br.uerj.rh.model.Historico_Vaga;
import br.uerj.rh.model.Vaga;

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
	public static synchronized Vaga getVaga(int id_vaga) {
		Vaga v = null;
		try {
			ConexaoBD a = new ConexaoBD();
			a.iniciaBd();
			Connection c = a.getConexao();
			//Fazendo a consulta no BD com o numero da vaga para obter o status da vaga
			PreparedStatement ps = (PreparedStatement) c.prepareStatement("SELECT id_situacao FROM concurso_vaga_historico as vh WHERE vh.nu_vaga = ? "
					+ "AND MIN(HOUR(TIMEDIFF(NOW(),dt_atualizacao)));");
			ps.setInt(1, id_vaga);
			ResultSet rs = ps.executeQuery();
			v = new Vaga();
			//Verificando a flag da situacao da vaga e inserindo o status correspondente
			int id = rs.getInt("id_situacao");
			if(id == 1) v.setStatus("Desocupada");
			else if(id == 2) v.setStatus("Em espera");
			else v.setStatus("Ocupada");
			//Atribuindo novos valores para outros atributos do objeto vaga
			v.setHistorico(new Historico_Vaga(v));
			v.setNum_vaga(id_vaga);
			//Obtendo o numero do processo do concurso a qual pertence a vaga
			ps = (PreparedStatement) c.prepareStatement("SELECT ce.cd_processo FROM concurso_especialidade as ce"
					+ "WHERE concurso_vaga.nu_vaga = ? "
					+ "AND concurso_vaga.id_concurso_especialidade = ce.id_concurso_especialidade");
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
			PreparedStatement ps = (PreparedStatement) c.prepareStatement("SELECT ct.ds_situacao FROM concurso_candidato_situacao_tipo as ct"
					+ "WHERE ch.cd_cpf = ?"
					+ "JOIN concurso_candidato_historico as ch ON ch.id_situacao_nova = ct.id_candidato_situacao");
			ps.setString(1, cpf);
			ResultSet rs = ps.executeQuery();
			String status_antigo = rs.getString("ds_situacao");
			//Persistindo esse status como  o antigo do candidato
			ps = (PreparedStatement) c.prepareStatement("UPDATE concurso_candidato_situacao_tipo ct"
					+ "SET ct.ds_situacao = ?"
					+ "WHERE concurso_candidato.cd_cpf = ? "
					+ "AND concurso_candidato_historico.cd_cpf = concurso_candidato.cd_cpf "
					+ "AND concurso_candidato_historico.id_situacao_antida = ct.id_candidato_situacao; ");
			ps.setString(1,status_antigo);
			ps.setString(2, cpf);
			ps.executeQuery();
			//Agora persistindo no banco o novo status :
			ps = (PreparedStatement) c.prepareStatement("UPDATE concurso_candidato_situacao_tipo ct"
					+ "SET ct.ds_situacao = ?"
					+ "WHERE concurso_candidato_historico.cd_cpf = ? "
					+ "AND concurso_candidato_historico.cd_cpf = concurso_candidato.cd_cpf "
					+ "AND concurso_candidato_historico.id_situacao_nova = ct.id_candidato_situacao;");
			ps.setString(1, status);
			ps.setString(2, cpf);
			ps.executeQuery();
			//Alterando a data nova mundaca na tabela concurso_candidato_historico
			Calendar ca = Calendar.getInstance();
			Date d = (Date) ca.getTime();
			ps = (PreparedStatement) c.prepareStatement("UPDATE concurso_candidato_historico "
					+ "SET dt_mudancao_situacao = ?"
					+ "WHERE cd_cpf = ?;");
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
	public static synchronized void setStatusVaga(String status, int nu_vaga,String cpf) {
		try {
			ConexaoBD a = new ConexaoBD();
			a.iniciaBd();
			Connection c = a.getConexao();
			//Obtendo as informacoes necessarias para inserrir uma nova alteracao no historico da vaga
			PreparedStatement ps = (PreparedStatement) c.prepareStatement("SELECT id_vaga FROM concurso_vaga WHERE nu_vaga = ?");
			ps.setInt(1, nu_vaga);
			ResultSet rs = ps.executeQuery();
			int id_vaga = rs.getInt("id_vaga");
			//Executando a persistencia do novo status da vaga no BD
			ps = (PreparedStatement) c.prepareStatement("INSERT INTO concurso_vaga_historico(id_vaga,dt_atualizacao,cd_cpf,id_situacao) VALUES (?,?,?,?)");
			ps.setInt(1,id_vaga);
			Calendar ca = Calendar.getInstance();
			Date d = (Date) ca.getTime();
			ps.setDate(2, d);
			ps.setString(3, cpf);
			//Compando a string status com o inteiro que o reprensenta no BD (id_situacao)
			if(status.equals("Desocupada")) ps.setInt(1, 1);
			else if(status.equals("Em espera")) ps.setInt(1, 2);
			else if(status.equals("Ocupada")) ps.setInt(1, 3);
			//Executando a query...
			ps.executeUpdate();
		}catch(SQLException s) {
			s.getStackTrace();
		}
	}
}
