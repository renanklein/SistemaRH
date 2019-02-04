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
					"SELECT c.cd_chave_candidato AS idc, c.id_situacao, c.ds_Unidade AS unidade, c.ds_lotacao AS lotacao,\r\n" + 
					"c.ds_localizacao AS localiz, c.nm_nome_completo AS nome, c.id_Matricula AS matricula,\r\n" + 
					"c.dt_portaria_Nomeacao AS datap,c.ds_codigo_portaria AS portaria, c.id_VAGA AS vaga,\r\n" + 
					"e.cd_processo AS processo, e.ds_perfil AS perfil, e.ds_especialidade AS especialidade, e.ds_regiao AS regiao,\r\n" + 
					"s.ds_situacao AS situacao\r\n" + 
					"FROM concurso_candidato AS c\r\n" + 
					"JOIN concurso_especialidade AS e ON e.id_concurso_especialidade = c.id_concurso_especialidade\r\n" + 
					"JOIN concurso_candidato_situacao_tipo AS s ON c.id_situacao = s.id_candidato_situacao\r\n" + 
					"WHERE  c.id_situacao IN (2, 3, 8)");

			ResultSet res = (ResultSet) ps.executeQuery();
			while (res.next()) {
				Lcand.add(new Candidato(
						res.getString("idc"),
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
	public static synchronized LinkedList<Usuario> ListarUsuarios() {
		try {
			LinkedList<Usuario> Luser = new LinkedList<Usuario>();

			ConexaoBD a = new ConexaoBD();
			a.iniciaBd();
			Connection c = a.getConexao();
			PreparedStatement ps = (PreparedStatement) c.prepareStatement(
					"SELECT usuario AS usuario, senha AS senha, nm_user AS nome_usuario ,permissao AS permissao\r\n" + 
					"FROM sistema_usuarios AS c\r\n");
			ResultSet res = (ResultSet) ps.executeQuery();
			while (res.next()) {
				Luser.add(new Usuario(
						res.getString("usuario"),
						res.getString("senha"),
						//res.getString("nome_usuario"),
						res.getInt("permissao")));
			}

			ps.close();
			c.close();
			a.fechaBd();
			
			return Luser;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
}