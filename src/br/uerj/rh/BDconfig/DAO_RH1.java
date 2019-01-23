package br.uerj.rh.BDconfig;

import java.sql.Date;
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
				ConexaoBD a = new ConexaoBD();
				a.iniciaBd();
				Connection c = a.getConexao();
				PreparedStatement ps = (PreparedStatement) c.prepareStatement(
						"SELECT VAGA.id_vaga, VAGA.cd_cpf, p.nm_nome_completo, f.fl_ativo, CE.cd_processo\r\n" + 
						"FROM funcionario F\r\n" + 
						"INNER JOIN (\r\n" + 
						"\r\n" + 
						"SELECT CVH.id_vaga, CVH.cd_cpf, CV.id_concurso_especialidade, MAX( CVH.dt_atualizacao )  'dt_atualizacao'\r\n" + 
						"FROM concurso_vaga_historico CVH\r\n" + 
						"INNER JOIN concurso_vaga CV ON CV.id_vaga = CVH.id_vaga\r\n" + 
						"GROUP BY CVH.id_vaga, CVH.cd_cpf, CV.id_concurso_especialidade\r\n" + 
						")VAGA ON VAGA.cd_cpf = f.cd_cpf\r\n" + 
						"AND VAGA.id_concurso_especialidade = f.id_concurso_especialidade\r\n" + 
						"INNER JOIN pessoa P ON p.cd_cpf = f.cd_cpf\r\n" + 
						"INNER JOIN concurso_especialidade CE ON CE.id_concurso_especialidade = f.id_concurso_especialidade\r\n" + 
						"WHERE f.id_matricula = '" + mat + "'");
				ResultSet res1 = (ResultSet) ps.executeQuery();
				//PreparedStatement ps2 = (PreparedStatement) c.prepareStatement("select nm_nome from pessoa where ? = cpf");
				//ps2.setString(1, res1.getString("cpf"));
				//ResultSet res2 = (ResultSet) ps.executeQuery();
				//ps = (PreparedStatement) c.prepareStatement("SELECT cv.nu_vaga FROM concurso_vaga as cv WHERE concurso_vaga_historico.id_vaga = cv.id_vaga "
				//		+ "AND concurso_vaga_historico.cd_cpf = funcionario.cd_cpf "
				//		+ "AND funcionario.cd_cpf = ?");
				//ResultSet res3 = ps.executeQuery();
				Funcionario func = new Funcionario(
						mat,
						res1.getString("cd_cpf"),
						res1.getString("nm_nome_completo"),
						res1.getString("cd_processo"),
						res1.getBoolean("fl_ativo"),
						res1.getInt("id_vaga"));
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
				Funcionario func = DAO_RH1.consultaFunc(mat);
				String query = "UPDATE funcionario SET fl_ativo = 0 WHERE id_matricula = ?;UPDATE  concurso_vaga  SET  st_ocupada = 0 WHERE nu_vaga = ?;"
						+ "INSERT INTO concurso_vaga_historico ( nu_vaga , dt_atualizacao ,  id_situacao ) VALUES (?, ?, 1)";
				PreparedStatement ps = (PreparedStatement) c.prepareStatement(query);
				ps.setString(1, func.getMatricula());
				ps.setInt(2, func.getIdVaga());
				//Obtendo o objeto Calendar com a data/horario atual e convertendo para tipo Date da biblioteca java.sql
				//OBS: Estou utilizando a Classe Calendar pois ela é mais facil de ser manipulada
				//Atualizando a data de exoneracao
				Calendar ca = Calendar.getInstance();
				//Setando a data de desligamento no objeto func
				func.setData_desligamento(ca);
				//Criando o date para passar como parâmetro da query acima
				Date d = (Date) func.getData_desligamento().getTime();
				//Atriubindo os valores das variaveis na query (?)
				ps.setInt(3, func.getIdVaga());
				ps.setDate(4,d);
				ps.executeQuery();
				//Atualizando o status da vaga que o funcionario ocupava
				func.getVaga_func().getHist().atualiza_historico(func);
			
				func.setData_desligamento(ca);
				//Atualizando status da vaga que estava ocupada
				func.getVaga_func().getHist().atualiza_historico(func);
				//Atualizando seu status
				func.setStatus(false);
				
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
