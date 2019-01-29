package br.uerj.rh.BDconfig;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import br.uerj.rh.model.Funcionario;

public class DAO_RH1 {
	//Os dois primeiros métodos estão relacionados com o caso de uso RH1
		public static synchronized Funcionario consultaFunc(String mat) {
			try {
				Funcionario func = null;
				ConexaoBD a = new ConexaoBD();
				a.iniciaBd();
				Connection c = a.getConexao();
				PreparedStatement ps = (PreparedStatement) c.prepareStatement("SELECT cc.id_concurso_especialidade,cc.cd_chave_candidato,cc.nm_nome_completo ,\r\n" + 
						"						ce.cd_processo,ce.ds_especialidade,ce.ds_perfil,cc.id_vaga,cc.id_situacao,cc.ds_Unidade,cc.ds_lotacao,cc.ds_localizacao FROM concurso_candidato as cc\r\n" + 
						"						JOIN concurso_especialidade as ce on ce.id_concurso_especialidade = cc.id_concurso_especialidade\r\n" + 
						"					       WHERE cc.id_Matricula =  ?;");
				ps.setString(1, mat);
				ResultSet res1 = (ResultSet) ps.executeQuery();
				//PreparedStatement ps2 = (PreparedStatement) c.prepareStatement("select nm_nome from pessoa where ? = cpf");
				//ps2.setString(1, res1.getString("cpf"));
				//ResultSet res2 = (ResultSet) ps.executeQuery();
				//ps = (PreparedStatement) c.prepareStatement("SELECT cv.nu_vaga FROM concurso_vaga as cv WHERE concurso_vaga_historico.id_vaga = cv.id_vaga "
				//		+ "AND concurso_vaga_historico.cd_cpf = funcionario.cd_cpf "
				//		+ "AND funcionario.cd_cpf = ?");
				//ResultSet res3 = ps.executeQuery();
				if(res1.next()) {
					boolean ativo = false;
					if(res1.getInt("id_situacao") == 4) ativo = true;
					else if(res1.getInt("id_situacao") == 6) ativo = false;
					func = new Funcionario(
							mat,
							res1.getString("cd_chave_candidato"),
							res1.getString("nm_nome_completo"),
							res1.getString("cd_processo"),
							ativo,
							res1.getInt("id_vaga"),
							res1.getInt("id_concurso_especialidade"));
					func.setPerfil(res1.getString("ds_perfil"));
					func.setNm_especialidade(res1.getString("ds_especialidade"));
					func.setUnidade(res1.getString("ds_Unidade"));
					func.setLotacao(res1.getString("ds_lotacao"));
					func.setLocalizacao(res1.getString("ds_localizacao"));
				}
				ps.close();
				c.close();
				a.fechaBd();
				return func;

			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}

		}
		public static synchronized int exoneraFunc(Funcionario func){
			//Esse método retorna o id_vaga na qual o funcionario ocupava
			//Retorna um valor negativo caso ocorra uma Exception
			System.out.println("Entrou");
			try {
				ConexaoBD a = new ConexaoBD();
				a.iniciaBd();
				Connection c = a.getConexao();
				//String query = "UPDATE concurso_candidato SET id_situacao = 6 WHERE cd_chave_candidato = ?;UPDATE  concurso_vaga  SET  id_situacao = 4 WHERE id_vaga = ?;\r\n" + 
					//	"						INSERT INTO concurso_vaga_historico ( id_vaga , dt_atualizacao , cd_chave_candidato,id_situacao) VALUES (?, ?, ?,4);\r\n" + 
						//"						INSERT INTO concurso_candidato_historico (id_concurso_especialidade,cd_chave_candidato,id_situacao_antiga,id_situacao_nova,dt_mudanca_situacao)\r\n" + 
						//"						VALUES (?,?,4,6,?);";
				PreparedStatement ps = (PreparedStatement) c.prepareStatement("UPDATE concurso_candidato SET id_situacao = 6 WHERE cd_chave_candidato = ?");
				ps.setString(1, func.getChave());
				ps.executeUpdate();
				ps = (PreparedStatement) c.prepareStatement("UPDATE  concurso_vaga  SET  id_situacao = 4 WHERE id_vaga = ?;");
				ps.setInt(1, func.getIdVaga());
				ps.executeUpdate();
				//Obtendo o objeto Calendar com a data/horario atual e convertendo para tipo Date da biblioteca java.sql
				//OBS: Estou utilizando a Classe Calendar pois ela é mais facil de ser manipulada
				//Atualizando a data de exoneracao
				ps = (PreparedStatement) c.prepareStatement("INSERT INTO concurso_vaga_historico ( id_vaga , dt_atualizacao , cd_chave_candidato,id_situacao) VALUES (?, ?, ?,4);");
				Calendar ca = Calendar.getInstance();
				//Setando a data de desligamento no objeto func
				func.setData_desligamento(ca);				
				java.util.Date d = ca.getTime();
				//Atriubindo os valores das variaveis na query (?)
				ps.setInt(1, func.getIdVaga());
				ps.setDate(2,new java.sql.Date(d.getTime()));
				ps.setString(3,func.getChave());
				ps.executeUpdate();
				ps = (PreparedStatement) c.prepareStatement("INSERT INTO concurso_candidato_historico (id_concurso_especialidade,cd_chave_candidato,id_situacao_antiga,id_situacao_nova,dt_mudanca_situacao)" + 
					"VALUES (?,?,4,6,?);");
				ps.setInt(1, func.getId_especialidade());
				ps.setString(2, func.getChave());
				ps.setDate(3, new java.sql.Date(d.getTime()));
				ps.executeUpdate();
				
				System.out.println("Executou as querys acima");
				//Atualizando os dados da tabela de especialidade
				
				ps = (PreparedStatement) c.prepareStatement("UPDATE concurso_especialidade SET nu_eliminados_exonerados = nu_eliminados_exonerados + 1 WHERE id_concurso_especialidade = ?");
				ps.setInt(1,func.getId_especialidade());
				
				int teste = ps.executeUpdate();
				/*//Atualizando o status da vaga que o funcionario ocupava
				func.getVaga_func().getHist().atualiza_historico(func);
			
				func.setData_desligamento(ca);
				//Atualizando status da vaga que estava ocupada
				func.getVaga_func().getHist().atualiza_historico(func);
				//Atualizando seu status
				func.setStatus(false);
				
				//Alterando os dados quando o func era candidato
				ps = (PreparedStatement) c.prepareStatement("UPDATE concurso_candidato SET id_situacao = 6 WHERE cd_cpf = ?");
				ps.setString(1,func.getChave());*/
				
				ps.close();
				c.close();
				a.fechaBd();
				
				return func.getIdVaga();
			}catch(SQLException e) {
				e.getStackTrace();
				return -1;
			}
			
		}
		
}
