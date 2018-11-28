package SistemaRH.BDconfig;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

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
	

}
