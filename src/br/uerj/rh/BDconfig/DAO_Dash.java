package br.uerj.rh.BDconfig;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import br.uerj.rh.model.*;
import br.uerj.rh.BDconfig.ConexaoBD;
 
public class DAO_Dash {
	
	//Lista vagas desocupadas
	public static synchronized LinkedList<Vaga> ListarVagasAbertas() {
		try {
			LinkedList<Vaga> vagas = new LinkedList<Vaga>();

			ConexaoBD a = new ConexaoBD();
			a.iniciaBd();
			Connection c = a.getConexao();
			PreparedStatement ps = (PreparedStatement) c.prepareStatement(
					"SELECT v.id_concurso_especialidade AS concurso, id_vaga AS vaga, ds_situacao AS situacao, cd_processo AS processo, ds_especialidade AS especialidade\r\n" + 
					"FROM concurso_vaga AS v\r\n" + 
					"JOIN concurso_vaga_situacao AS s ON v.id_situacao = s.id_vaga_situacao\r\n" + 
					"JOIN concurso_especialidade AS e ON v.id_concurso_especialidade = e.id_concurso_especialidade\r\n" + 
					"WHERE v.id_situacao !=1");
			ResultSet res = (ResultSet) ps.executeQuery();
			while (res.next()) {
				vagas.add(new Vaga(
						res.getInt("concurso"),
						res.getInt("vaga"),
						res.getString("situacao"),
						res.getString("processo"),
						res.getString("especialidade")));
			}

			ps.close();
			c.close();
			a.fechaBd();
			return vagas;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	//Lista candidatod em processo de convocação
	public static synchronized LinkedList<Candidato> ListarCandidatosConvocados() {
		try {
			LinkedList<Candidato> Lcand = new LinkedList<Candidato>();

			ConexaoBD a = new ConexaoBD();
			a.iniciaBd();
			Connection c = a.getConexao();
			PreparedStatement ps = (PreparedStatement) c.prepareStatement(
					"SELECT p.cd_cpf AS cpf, nm_nome_completo AS nome, ds_Unidade AS unidade, ds_situacao AS situacao, cd_processo AS processo, ds_perfil AS perfil, ds_especialidade AS especialidade, ds_regiao AS regiao\r\n" + 
					"FROM concurso_candidato AS c\r\n" + 
					"JOIN pessoa AS p ON c.cd_cpf = p.cd_cpf\r\n" + 
					"JOIN concurso_candidato_situacao_tipo AS s ON c.id_situacao = s.id_candidato_situacao\r\n" + 
					"JOIN concurso_especialidade AS e ON e.id_concurso_especialidade = c.id_concurso_especialidade\r\n" + 
					"WHERE c.id_situacao =3\r\n" + 
					"OR c.id_situacao =2");
			ResultSet res = (ResultSet) ps.executeQuery();
			while (res.next()) {
				Lcand.add(new Candidato(
						res.getString("cpf"),
						res.getString("nome"),
						res.getString("situacao"),
						res.getString("unidade"),
						res.getString("processo"),
						res.getString("perfil"),
						res.getString("especialidade"),
						res.getString("regiao")));
			}

			ps.close();
			c.close();
			a.fechaBd();
			return Lcand;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
