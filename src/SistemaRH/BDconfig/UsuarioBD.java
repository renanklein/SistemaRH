package SistemaRH.BDconfig;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import SistemaRH.model.Candidato;
import SistemaRH.model.Especialidade;
import SistemaRH.model.Funcionario;

public class UsuarioBD {
	
	//Os dois primeiros métodos estão relacionados com o caso de uso RH1
	public static synchronized Funcionario consultaFunc(String mat) {
		try {
			ConexaoBD a = new ConexaoBD();
			a.iniciaBd();
			Connection c = a.getConexao();
			PreparedStatement ps = (PreparedStatement) c.prepareStatement("select id_matricula, cd_cpf, ativo from funcionario where ? = id_matricula");
			ps.setString(1, mat);
			ResultSet res1 = (ResultSet) ps.executeQuery();
			PreparedStatement ps2 = (PreparedStatement) c.prepareStatement("select nm_nome from pessoa where ? = cpf");
			ps2.setString(1, res1.getString("cpf"));
			ResultSet res2 = (ResultSet) ps.executeQuery();
			Funcionario func = new Funcionario(res1.getString("id_matricula"), res1.getString("cd_cpf"), res2.getString("nm_nome"), res1.getBoolean("ativo"));
			ps.close();
			c.close();
			a.fechaBd();
			return func;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

	}
	public static synchronized boolean exoneraFunc(String mat){
		try {
			ConexaoBD a = new ConexaoBD();
			a.iniciaBd();
			Connection c = a.getConexao();
			//Atualizando o status da vaga que o funcionario ocupava
			Funcionario func = UsuarioBD.consultaFunc(mat);
			func.getVaga_func().getHist().atualiza_historico(func);
			//Atualizando a data de exoneracao
			Calendar ca = Calendar.getInstance();
			func.setData_desligamento(ca);
			//Atualizando status da vaga que estava ocupada
			func.getVaga_func().getHist().atualiza_historico(func);
			//Atualizando seu status
			func.setStatus(false);
			//Persistindo as mudanca no bd
			//Status...
			PreparedStatement ps = (PreparedStatement) c.prepareStatement("UPDATE funcionario SET status = ? WHERE id_matricula = ?");
			ps.setString(1, mat);
			ps.executeQuery();
			//Status da vaga e data de exoneracao
			Date d = (Date) func.getData_desligamento().getTime();
			ps = (PreparedStatement) c.prepareStatement("UPDATE concurso_historico_vaga SET dt_atualizacao = ?  WHERE cd_cpf = ?");
			ps.setDate(1,d);
			//OBS : Nao ha campo no modelo do bd para alterar o status da vaga
			ps.setString(2, func.getCPF());
			
			ps.close();
			c.close();
			a.fechaBd();
		}catch(SQLException e) {
			e.getStackTrace();
			return false;
		}
		
		return true;
	}
	// Metodo(s) relacionados ao caso de uso RH2
	/*public static synchronized Vaga criaVaga(String num_processo,String nome_especialidade)  {
		Vaga v;
		try {
			ConexaoBD a = new ConexaoBD();
			a.iniciaBd();
			Connection c = a.getConexao();
			//Verificando se os dados digitados pelo usuario realmente constam no bd
			PreparedStatement ps = (PreparedStatement) c.prepareStatement("SELECT ce.cd_processo,ce.ds_perfil from"
					+ "concurso_especialidade as ce WHERE ce.cd_processo = ? and ce.ds_perfil = ? ");
			ps.setString(1,num_processo);
			ps.setString(2,nome_especialidade);
			ResultSet rs = ps.executeQuery();
			//Devera ter um tratamento de erro caso seja retornado uma tabela vazia
			
			//Caso realmente os dados constem no bd ...
			v = new Vaga();
			/*Acessando o banco de dados para receber informacoes da especialidade e carrega-los no
			 * objeto vaga*/ 
			/*ps = (PreparedStatement) c.prepareStatement("SELECT * FROM concurso_especialidade as ce WHERE ce.cd_processo = ? ");
			ps.setString(1,num_processo);
			rs = ps.executeQuery();
			Especialidade esp = new Especialidade();
			esp.setPerfil(rs.getString("ds_perfil"));
			esp.setNome_espec(rs.getString("ds_especialidade"));
			esp.setRegiao(rs.getString("ds_regiao"));
			esp.setVagas_iniciais(rs.getInt("nu_vagas_iniciais"));
			esp.setVagas_amplicadas(rs.getInt("nu_vagas_amplidadas"));
			esp.setQtd_aprovados(rs.getInt("nu_aprovados"));
			esp.setVacancias(rs.getInt("nu_vacancias"));
			esp.setQtd_nomeados(rs.getInt("nu_nomeados"));
			esp.setQtd_exonerados(rs.getInt("nu_eliminados_exonerados"));
			esp.setCand_restantes(rs.getInt("nu_banco_restante"));
			//Settando o objeto Especialidade no obj Vaga
			v.setVaga_espec(esp);
			
			ps.close();
			c.close();
			a.fechaBd();
			
		}catch(SQLException s) {
			s.getStackTrace();
			return null;
		}
		return v;
	}*/
	//Metodo(s) do caso de uso RH3
	public static synchronized String getPrimeiroCPF(String esp) {
		String cpf;
		try {
			ConexaoBD a = new ConexaoBD();
			a.iniciaBd();
			Connection c = a.getConexao();
			//Descobrindo o cpf do primeiro candidato a ser chamado
			PreparedStatement ps = (PreparedStatement) c.prepareStatement("SELECT cc.cd_cpf FROM concurso_candidato as cc,concurso_especialidade as ce "
					+ "WHERE ce.ds_perfil = ? and cc.min(nu_candidato_posicao)"
					+ "JOIN concurso_candidato_situacao_tipo as ct on cc.id_situacao = ct.id_candidato_situacao and ct.ds_situacao = 'Em espera'");
			//OBS:Verificar se o status do candidado ainda nao selecionada e 'Em espera'
			//Perfil = nome da especialidade?
			ps.setString(1,esp);
			ResultSet rs =  ps.executeQuery();
			cpf = rs.getString("cd_cpf");

			ps.close();
			c.close();
			a.fechaBd();
			
		}catch(SQLException s) {
			s.getStackTrace();
			return null;
		}
		return cpf;
	}
	public static synchronized Candidato selecionaCandidato(String esp) {
		Candidato selected;
		try {
			//Obtendo o CPF do primeiro candidato da classificacao da especialidade
			String cpf = UsuarioBD.getPrimeiroCPF(esp);
			ConexaoBD a = new ConexaoBD();
			a.iniciaBd();
			Connection c = a.getConexao();
			/*Alterando o status do candidato nas tabelas concurso_candidato_situacao_tipo 
			 * e concurso_candidato_historico. Tambem alterando a data */
			Calendar ca = Calendar.getInstance();
			Date d = (Date) ca.getTime();
			PreparedStatement ps = (PreparedStatement) c.prepareStatement("UPDATE concurso_candidato_historico "
					+ "SET ds_situacao_antiga = 'Em espera',ds_situacao_nova = 'Selecionado', dt_mudancao_situacao = ?"
					+ "WHERE cd_cpf = ?");
			//OBS:Os campos de status da tabela de historico estao como Int, para executar a mudanca acima deve altera-los para VARCHAR
			ps.setDate(1, d);
			ps.setString(2, cpf);
			ps.executeQuery();
			//Agora a table situacao tipo
			ps = (PreparedStatement) c.prepareStatement("UPDATE concurso_candidato_situacao_tipo ct"
					+ "JOIN concurso_candidato cc ON cc.id_situacao = ct.id_candidato_situacao"
					+ "SET ct.ds_situacao = 'Selecionado");
			ps.executeQuery();
			//Montando o objeto Candidato
			//Especialidade ...
			Especialidade espec = new Especialidade();
			ps = (PreparedStatement) c.prepareStatement("SELECT * FROM concurso_especialidade as ce WHERE ");
		}catch(SQLException s) {
			s.getStackTrace();
			return null;
		}
		return null;
	}
}

