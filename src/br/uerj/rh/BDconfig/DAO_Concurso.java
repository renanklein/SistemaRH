package br.uerj.rh.BDconfig;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;


import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

//import java.util.Date;
//import java.util.Calendar;
//import java.util.Stack;

import br.uerj.rh.model.*;
import br.uerj.rh.BDconfig.ConexaoBD;

public class DAO_Concurso {
	
	public static synchronized LinkedList<Concurso> ListarConcursos() {
		try {
			LinkedList<Concurso> LConcursos = new LinkedList<Concurso>();

			ConexaoBD a = new ConexaoBD();
			a.iniciaBd();
			Connection c = a.getConexao();
			PreparedStatement ps = (PreparedStatement) c.prepareStatement(
					"SELECT cd_processo AS num_processo, dt_validade_concurso AS DT_validade,\r\n"
					+ "dt_resultado_final_doerj AS DT_resultfinal FROM concurso_processo"); 

			ResultSet res = (ResultSet) ps.executeQuery();
			while (res.next()) {
				LConcursos.add(new Concurso (res.getString("num_processo"), res.getDate("DT_validade"), res.getDate("DT_resultfinal")));
				
			}

			ps.close();
			c.close();
			a.fechaBd();
			return LConcursos;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
}
	
	public static synchronized LinkedList<Especialidade> ListarEspecialidades() {
		try {
			LinkedList<Especialidade> LVagas = new LinkedList<Especialidade>();

			ConexaoBD a = new ConexaoBD();
			a.iniciaBd();
			Connection c = a.getConexao();
			PreparedStatement ps = (PreparedStatement) c.prepareStatement(
					"SELECT cd_processo AS processo,\r\n" + 
					"SUM(nu_vagas_iniciais) AS vagas,\r\n" + 
					"SUM(nu_vacancia) AS vacancia,\r\n" +
					"SUM(nu_vagas_ampliadas) AS ampliacao,\r\n"+
					"SUM(nu_banco_restante) AS aguardando\r\n" +
					"FROM concurso_especialidade\r\n" + 
					"GROUP BY cd_processo;"); 

			ResultSet res = (ResultSet) ps.executeQuery();
			while (res.next()) {
				LVagas.add(new Especialidade (res.getInt("vagas"), res.getInt("vacancia"), res.getInt("aguardando"), res.getInt("ampliacao"), res.getString("processo")));
				
			}

			ps.close();
			c.close();
			a.fechaBd();
			return LVagas;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
}
}