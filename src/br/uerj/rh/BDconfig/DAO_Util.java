package br.uerj.rh.BDconfig;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import br.uerj.rh.model.Candidato;
import br.uerj.rh.model.Funcionario;
import br.uerj.rh.model.Vaga;

public class DAO_Util {
	/*public static synchronized Especialidade getEspCand(String cpf) {
		Especialidade espec = new Especialidade();
		try{
			ConexaoBD a = new ConexaoBD();
			a.iniciaBd();
			Connection c = a.getConexao();
			PreparedStatement ps = (PreparedStatement) c.prepareStatement("SELECT * FROM concurso_especialidade as ce "
					+ "JOIN concurso_candidato as cc ON cc.id_concurso_especialidade = ce.id_concurso_especialidade"
					+ "WHERE cc.cd_cpf = ?");
			ps.setString(1, cpf);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				espec.setId_especialidade(rs.getInt("id_concurso_especialidade"));
				espec.setPerfil(rs.getString("ds_perfil"));
				espec.setNome_espec(rs.getString("ds_especialidade"));
				espec.setRegiao(rs.getString("ds_regiao"));
				espec.setVagas_iniciais(rs.getInt("nu_vagas_iniciais"));
				espec.setVagas_amplicadas(rs.getInt("nu_vagas_amplidadas"));
				espec.setQtd_aprovados(rs.getInt("nu_aprovados"));
				espec.setVacancias(rs.getInt("nu_vacancias"));
				espec.setQtd_nomeados(rs.getInt("nu_nomeados"));
				espec.setQtd_exonerados(rs.getInt("nu_eliminados_exonerados"));
				espec.setCand_restantes(rs.getInt("nu_banco_restante"));
				espec.setId_concurso(rs.getInt("cd_processo"));
			}
			rs.close();
			ps.close();
			c.close();
			a.fechaBd();
		}catch(SQLException s) {
			s.getStackTrace();
			return null;
		}
		return espec;
	}*/
	public static synchronized Vaga getVaga(int id_vaga) {
		Vaga v = null;
		try {
			ConexaoBD a = new ConexaoBD();
			a.iniciaBd();
			Connection c = a.getConexao();
			//Obtendo as informações da vaga, inclusive a descrição de seu status
			PreparedStatement ps = (PreparedStatement) c.prepareStatement(" SELECT cv.*,ce.ds_especialidade,ce.cd_processo,vs.ds_situacao\r\n" + 
					"FROM concurso_vaga as cv\r\n" + 
					"JOIN concurso_especialidade as ce on cv.id_concurso_especialidade  = ce.id_concurso_especialidade\r\n" + 
					"JOIN concurso_vaga_situacao as vs on vs.id_vaga_situacao = cv.id_situacao\r\n" + 
					"WHERE cv.id_vaga = ?;");
			ps.setInt(1, id_vaga);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				v =  new Vaga(
						rs.getInt("id_concurso_especialidade"),
						rs.getInt("id_vaga"),
						rs.getString("ds_situacao"),
						rs.getString("cd_processo"),
						rs.getString("ds_especialidade")
				);
			} 
			rs.close();
			ps.close();
			c.close();
			a.fechaBd();
		}catch(SQLException s) {
			s.getStackTrace();
		}
		return v;
	}
	public static synchronized void setStatusCandidato(int id_situacao,Candidato cand) {
		try {
			ConexaoBD a = new ConexaoBD();
			a.iniciaBd();
			Connection c = a.getConexao();
			//Atualizando o banco de dados nas duas tabelas que contêm informações do candidato
			PreparedStatement ps = (PreparedStatement) c.prepareStatement("INSERT INTO concurso_candidato_historico (id_concurso_especialidade,cd_chave_concurso,id_situacao_antiga,id_situacao_nova,dt_mudanca_situacao) VALUES (?,?,?,?,?);");
			ps.setInt(1, cand.getId_espec());
			ps.setString(2, cand.getChave());
			ps.setInt(3,cand.getId_situacao());
			ps.setInt(4,id_situacao);
			Calendar ca = Calendar.getInstance();
			java.util.Date d = ca.getTime();
			ps.setDate(5, new java.sql.Date(d.getTime()));
			
			ps.executeUpdate();
			
			ps = (PreparedStatement) c.prepareStatement("UPDATE concurso_candidato SET id_situacao = ? WHERE cd_chave_candidato = ?;");
			
			ps.setInt(1, id_situacao);
			ps.setString(2,cand.getChave());
			
			ps.executeUpdate();
			if (id_situacao == 7 ) {
				ps = (PreparedStatement) c.prepareStatement("UPDATE concurso_especialidade "
						+ "SET nu_eliminados_exonerados = nu_eliminados_exonerados + 1, nu_banco_restante = nu_banco_restante - 1 "
						+ "WHERE id_concurso_especialidade = ?;"
						+ "");
				ps.setInt(1, cand.getId_espec());
				ps.executeUpdate();
			} else if(id_situacao == 4) {
				PreparedStatement ps1 = (PreparedStatement) c.prepareStatement("UPDATE concurso_especialidade "
						+ "SET nu_nomeados = nu_nomeados + 1, nu_banco_restante = nu_banco_restante - 1"
						+ " WHERE id_concurso_especialidade = ?");
				ps1.setInt(1, cand.getId_espec());
				ps1.executeUpdate();
				ps1.close();
			}
			ps.close();
			c.close();
			a.fechaBd();
			
		}catch(SQLException s) {
			s.getStackTrace();		
		}
	}
	public static synchronized void setStatusVaga(int id_situacao, int id_vaga,String chave) {
		try {
			ConexaoBD a = new ConexaoBD();
			a.iniciaBd();
			Connection c = a.getConexao();
			//Atualizando as duas tabelas necessarias para mudar o status da vaga
			PreparedStatement ps = (PreparedStatement) c.prepareStatement("UPDATE concurso_vaga SET id_situacao = ? WHERE id_vaga = ?;");
			ps.setInt(1, id_situacao);
			ps.setInt(2,id_vaga);
			
			ps.executeUpdate();
			
			ps = (PreparedStatement) c.prepareStatement("INSERT INTO concurso_vaga_historico ( id_vaga , dt_atualizacao , cd_chave_candidato,id_situacao) VALUES (?,?,?,?);");
			ps.setInt(1, id_vaga);
			Calendar ca = Calendar.getInstance();
			java.util.Date d = ca.getTime();
			ps.setDate(2, new java.sql.Date(d.getTime()));
			ps.setString(3, chave);
			ps.setInt(4,id_situacao);
			
			ps.executeUpdate();
			
			ps.close();
			c.close();
			a.fechaBd();
		}catch(SQLException s) {
			s.getStackTrace();
		}
	}
	public static synchronized boolean isConcursoValido(String cd_processo) {
		boolean validade = false;
		try {
				ConexaoBD a = new ConexaoBD();
				a.iniciaBd();
				Connection c = a.getConexao();
				PreparedStatement ps = (PreparedStatement) c.prepareStatement("SELECT dt_validade_concurso FROM concurso_processo WHERE cd_processo = ?;");
				ps.setString(1, cd_processo);
				ResultSet rs = ps.executeQuery();				
				java.sql.Date dt_validade = null;
				if(rs.next()) {
					dt_validade = rs.getDate("dt_validade_concurso");
				}
				//Obtendo o tempo atual
				Calendar ca = Calendar.getInstance();
				java.util.Date dt = ca.getTime();
				
				//Verificando se o concurso esta na validade
				if(new java.sql.Date(dt.getTime()).compareTo(dt_validade) < 0) validade = true;
				rs.close();
				ps.close();
				c.close();
				a.fechaBd();
		}catch(SQLException s) {
			s.printStackTrace();
		}
		return validade;
	}
	public static synchronized ArrayList<Candidato> ProximoCand(Funcionario exonerado) {
		//O método retorna um array list
		//Caso o arraylist tenha mais de um candidato, ocorreu empate
		ArrayList<Candidato> lcands = new ArrayList <Candidato>();
		try {
			ConexaoBD a = new ConexaoBD();
			a.iniciaBd();
			Connection c = a.getConexao();
			//Obtendo o nome do candidato a ser chamado
			System.gc();
			PreparedStatement pes = (PreparedStatement) c.prepareStatement("SELECT cc.cd_chave_candidato,cc.nm_nome_completo,cc.nu_candidato_posicao_empate FROM concurso_candidato as cc WHERE \r\n" + 
					"	cc.id_concurso_especialidade = ? \r\n" + 
					"	AND cc.nu_candidato_posicao = (select min(c.nu_candidato_posicao) from concurso_candidato as c where c.id_situacao = 1); ");
			pes.setInt(1,exonerado.getId_especialidade());
			ResultSet res = pes.executeQuery();
			ResultSetMetaData r = res.getMetaData();
			System.out.println(r.getColumnDisplaySize(1));

			if(res.next()) {
				int empate = res.getInt("nu_candidato_posicao_empate");
				lcands.add(DAO_RH4.getCandidato(res.getString("cd_chave_candidato")));
				String[] splitChave =  lcands.get(0).getChave().split(":");
				System.out.println("Primeira parte :" + splitChave[0] +" Segunda parte:" +splitChave[1]);
				
				if(Integer.parseInt(splitChave[1]) > 0  && empate > 0) {
					//Obtendo os nomes dos candidato que estão empatados(Ultima parte da chave)
					PreparedStatement ps1 = (PreparedStatement) c.prepareStatement("SELECT cc.nm_nome_completo, cc.cd_chave_candidato,cc.nu_candidato_posicao_empate FROM concurso_candidato as cc \r\n" + 
							"								WHERE SUBSTRING_INDEX(cc.cd_chave_candidato,':',-1) = ?\r\n" + 
							"								AND SUBSTRING_INDEX(cc.cd_chave_candidato,':',1) != ?\r\n" + 
							"								AND cc.id_situacao = 1\r\n" + 
							"								AND cc.nu_candidato_posicao_empate > 0;\r\n" + 
							"                                ");
					ps1.setString(1, splitChave[1]);
					ps1.setString(2, splitChave[0]);
					
					ResultSet res1 = ps1.executeQuery();
					while(res1.next()) {
						System.out.println();
						lcands.add(DAO_RH4.getCandidato(res1.getString("cd_chave_candidato")));
					}
					res1.close();
					ps1.close();
				}
			}
			else{
				//Obtendo o nome do candidato a ser chamado
				PreparedStatement ps2 = (PreparedStatement) c.prepareStatement("  SELECT cc.cd_chave_candidato,cc.nm_nome_completo,cc.nu_candidato_posicao_fim,cc.nu_candidato_posicao_empate FROM concurso_candidato as cc WHERE \r\n" + 
						"						    cc.id_concurso_especialidade = ?\r\n" + 
						"						    AND cc.nu_candidato_posicao = (select min(c.nu_candidato_posicao) from concurso_candidato as c where c.id_situacao = 5);");
				ps2.setInt(1, exonerado.getId_especialidade());
				ResultSet res2 = ps2.executeQuery();
				if(res2.next()) {
					int empate = res2.getInt("nu_candidato_posicao_empate");
					lcands.add(DAO_RH4.getCandidato(res2.getString("cd_chave_candidato")));
					String[] splitChave =  lcands.get(0).getChave().split(":");
					
					if(Integer.parseInt(splitChave[1]) > 0  && empate > 0) {
						System.out.println(Integer.parseInt(splitChave[1]));
						//Obtendo os nomes dos candidato que estão empatados(Ultima parte da chave)
						PreparedStatement ps1 = (PreparedStatement) c.prepareStatement("SELECT cc.nm_nome_completo, cc.cd_chave_candidato,cc.nu_candidato_posicao_empate FROM concurso_candidato as cc \r\n" + 
								"								WHERE SUBSTRING_INDEX(cc.cd_chave_candidato,':',-1) = ?\r\n" + 
								"								AND SUBSTRING_INDEX(cc.cd_chave_candidato,':',1) != ?\r\n" + 
								"								AND cc.id_situacao = 5\r\n" + 
								"								AND cc.nu_candidato_posicao_empate > 0;\r\n" + 
								"                                ");						
						ps1.setString(1, splitChave[1]);
						ps1.setString(2, splitChave[0]);
						ResultSet res1 = ps1.executeQuery();
						while(res1.next()) {
							lcands.add(DAO_RH4.getCandidato(res1.getString("cd_chave_candidato")));
						}
						res1.close();
						ps1.close();
					}
					res2.close();
					ps2.close();
				}
			}
			pes.close();
			res.close();
			c.close();
			a.fechaBd();
			return lcands;
		}catch (SQLException s) {
			s.printStackTrace();
			return null;
		}
	}
	public static synchronized boolean alteraFunc(Funcionario f,String unidade,String lotacao, String localizacao) {
		try {
			ConexaoBD a = new ConexaoBD();
			a.iniciaBd();
			Connection c = a.getConexao();
			//Obtendo o nome do candidato a ser chamado
			System.gc();
			PreparedStatement ps = (PreparedStatement) c.prepareStatement("UPDATE concurso_candidato SET ds_Unidade = ?,ds_lotacao = ?,ds_localizacao = ? WHERE id_matricula = ?");
			ps.setString(1, unidade);
			ps.setString(2, lotacao);
			ps.setString(3, localizacao);
			ps.setString(4, f.getMatricula());
			
			ps.executeUpdate();
			
			return true;
		}catch(SQLException s) {
			s.printStackTrace();
			return false;
		}
		
	}
}
