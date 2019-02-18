package br.uerj.rh.BDconfig;

import java.sql.DriverManager;

import com.mysql.jdbc.Connection;

public class ConexaoBD {

	private Connection con;
	
	public void iniciaBd(){
		try{
			String database = "jdbc:mysql://127.0.0.1:3306/rh2";
			String usuario = "root";
			String senha = "";
			Class.forName("com.mysql.jdbc.Driver");
			con = (Connection) DriverManager.getConnection(database, usuario, senha);
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void fechaBd(){
		try{
			con.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public Connection getConexao(){
		return con;
	}


}
