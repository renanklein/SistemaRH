package br.uerj.rh.BDconfig;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import br.uerj.rh.model.Usuario;

public class DAO_user {
	public static synchronized Usuario consultaUser (String usuario) {
		try {
			ConexaoBD a = new ConexaoBD();
			a.iniciaBd();
			Connection c = a.getConexao();
			PreparedStatement ps = (PreparedStatement) c.prepareStatement("SELECT * FROM sistema_usuarios");
			//ps.setString(1, usuario);		
			ResultSet res1 = (ResultSet) ps.executeQuery();
			Usuario user = null;
			while(res1.next()) {
				user = new Usuario(res1.getString("usuario"),res1.getString("senha"),res1.getInt("permissao"));
			}
			ps.close();
			c.close();
			a.fechaBd();
			return user;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

	}
	
	public static synchronized Boolean updateStatus (Usuario user) {
		try {
			ConexaoBD a = new ConexaoBD();
			a.iniciaBd();
			Connection c = a.getConexao();
			PreparedStatement ps = (PreparedStatement) c.prepareStatement("SELECT * FROM sistema_usuarios");
			//ps.setString(1, usuario);		
			ResultSet res1 = (ResultSet) ps.executeQuery();
			Usuario usuario = null;
			while(res1.next()) {
				user = new Usuario(res1.getString("usuario"),res1.getString("senha"),res1.getInt("permissao"));
			}
			ps.close();
			c.close();
			a.fechaBd();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}
	
}
