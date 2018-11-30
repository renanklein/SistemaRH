package SistemaRH.BDconfig;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import SistemaRH.model.Especialidade;
import SistemaRH.model.Funcionario;
import SistemaRH.model.Vaga;

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
			PreparedStatement ps = (PreparedStatement) c.prepareStatement("UPDATE funcionario SET status = false WHERE id_matricula = ?");
			ps.setString(1, mat);
			ps.executeQuery();
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
			
		}catch(SQLException s) {
			s.getStackTrace();
			return null;
		}
		return v;*/
	}

}
