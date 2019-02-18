package br.uerj.rh.BDconfig;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;


import org.apache.tomcat.dbcp.dbcp2.ConnectionFactory;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.util.*;
import br.uerj.rh.model.*;
import br.uerj.rh.BDconfig.DAO_Dash;

public class DAO_user {
	public static synchronized Usuario consultaUser (String usuario) {
		try {
			ConexaoBD a = new ConexaoBD();
			a.iniciaBd();
			Connection c = a.getConexao();
			PreparedStatement ps = (PreparedStatement) c.prepareStatement("SELECT * FROM sistema_usuarios WHERE usuario = '"+ usuario + "'");
			//ps.setString(1, usuario);		
			ResultSet res1 = (ResultSet) ps.executeQuery();
			Usuario user = null;
			while(res1.next()) {
				user = new Usuario(res1.getString("usuario"),
								   res1.getString("senha"),
								   //res1.getString("nm_user"),
								   res1.getInt("permissao"));
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
				user = new Usuario(res1.getString("usuario"),res1.getString("senha"),/*res1.getString("nome_usuario"),*/res1.getInt("permissao"));
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
	
	
		public static synchronized Usuario updateSenha(Usuario senha) {
			String sql = "UPDATE sistema_usuarios SET senha = ? WHERE usuario = ?";
			
			PreparedStatement stmt = null;
			try {
				ConexaoBD a = new ConexaoBD();
				a.iniciaBd();
				Connection c = a.getConexao();
				
				stmt = (PreparedStatement) c.prepareStatement(sql);
				stmt.setString(1, senha.getSenha());
				stmt.setString(2, senha.getUsuario());
				stmt.executeUpdate();
				
				stmt.close();
				c.close();
				a.fechaBd();				
				
				return senha;
				
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}
	

		}
		
		public static synchronized Usuario delete(Usuario user) {
			String sql = "DELETE FROM sistema_usuarios WHERE usuario = ?";
			
			PreparedStatement stmt = null;
			try {
				ConexaoBD a = new ConexaoBD();
				a.iniciaBd();
				Connection c = a.getConexao();
				
				stmt = (PreparedStatement) c.prepareStatement(sql);
				stmt.setString(1, user.getUsuario());
				stmt.executeUpdate();
				
				stmt.close();
				c.close();
				a.fechaBd();				
				
				return user;
				
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}
	

		}
		
		public static synchronized Usuario inserir(Usuario user) {
			
			String sql = "INSERT INTO sistema_usuarios (usuario, senha, nm_user, permissao) VALUES (?,?,?,?)";
			
			PreparedStatement stmt = null;
			try {
				ConexaoBD a = new ConexaoBD();
				a.iniciaBd();
				Connection c = a.getConexao();
				
				String nm_user="User";
				
				stmt = (PreparedStatement) c.prepareStatement(sql);
				stmt.setString(1, user.getUsuario());
				stmt.setString(2, user.getSenha());
				stmt.setString(3, nm_user);
				stmt.setInt(4, user.getPermissao());
				stmt.executeUpdate();
				
				stmt.close();
				c.close();
				a.fechaBd();				
				
				return user;
				
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}
	

		}
		
}