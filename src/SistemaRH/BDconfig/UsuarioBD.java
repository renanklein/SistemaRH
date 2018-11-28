package SistemaRH.BDconfig;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import model.ConexaoBD;
import model.Contato;

public class UsuarioBD {
	public static synchronized boolean addContato(String nome, String telefone, String email){
		try{
			ConexaoBD a = new ConexaoBD();
			a.iniciaBd();
			Connection c = a.getConexao();
			PreparedStatement ps = (PreparedStatement) c.prepareStatement("INSERT INTO contato (nome, telefone, email) Values (?,?,?)");
			ps.setString(1, nome);
			ps.setString(2, telefone);
			ps.setString(3, email);
			ps.executeUpdate();
			ps.close();
			c.close();
			a.fechaBd();
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	public static synchronized Funcionario () {
		try {
			ConexaoBD a = new ConexaoBD();
			a.iniciaBd();
			Connection c = a.getConexao();
			PreparedStatement ps = (PreparedStatement) c.prepareStatement("select id_matricula,  from aluno order by nome");
			ResultSet res = (ResultSet) ps.executeQuery();
			while (res.next()) {
				lContatos.add(new Contato(res.getString("nome"),res.getString("telefone"),res.getString("email")));
			}

			ps.close();
			c.close();
			a.fechaBd();
			return lContatos;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

	}

}
