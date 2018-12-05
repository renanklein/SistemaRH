package SistemaRH.BDconfig;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import SistemaRH.model.Funcionario;

public class DAO_RH1 {
	//Os dois primeiros métodos estão relacionados com o caso de uso RH1
		public static synchronized Funcionario consultaFunc(String mat) {
			try {
				ConexaoBD a = new ConexaoBD();
				a.iniciaBd();
				Connection c = a.getConexao();
				PreparedStatement ps = (PreparedStatement) c.prepareStatement("select id_matricula, cd_cpf, st_ativo, id_vaga from funcionario where ? = id_matricula");
				ps.setString(1, mat);
				ResultSet res1 = (ResultSet) ps.executeQuery();
				PreparedStatement ps2 = (PreparedStatement) c.prepareStatement("select nm_nome from pessoa where ? = cpf");
				ps2.setString(1, res1.getString("cpf"));
				ResultSet res2 = (ResultSet) ps.executeQuery();
				Funcionario func = new Funcionario(res1.getString("id_matricula"), res1.getString("cd_cpf"), res2.getString("nm_nome"), res1.getBoolean("st_ativo"), res1.getInt("id_vaga"));
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
				PreparedStatement ps= (PreparedStatement) c.prepareStatement("BEGIN");
				ps.executeQuery();
				ps = (PreparedStatement) c.prepareStatement("UPDATE funcionario SET status = 0 WHERE id_matricula = ?");
				ps.setString(1, func.getMatricula());
				ps.executeQuery();
				ps = (PreparedStatement) c.prepareStatement("UPDATE  concurso_vaga  SET  st_ocupada = 0 WHERE id_vaga = ?");
				ps.setInt(1, func.getIdVaga());
				ps.executeQuery();
				ps = (PreparedStatement) c.prepareStatement("INSERT INTO concurso_vaga_historico ( id_nu_vaga , dt_atualizacao ,  id_situacao ) VALUES (?, ?, 1)");
				//Obtendo o objeto Calendar com a data/horario atual e convertendo para tipo Date da biblioteca java.sql
				//OBS: Estou utilizando a Classe Calendar pois ela é mais facil de ser manipulada
				//Atualizando a data de exoneracao
				Calendar ca = Calendar.getInstance();
				//Setando a data de desligamento no objeto func
				func.setData_desligamento(ca);
				//Criando o date para passar como parâmetro da query acima
				Date d = (Date) func.getData_desligamento().getTime();
				//Atriubindo os valores das variaveis na query (?)
				ps.setInt(1, func.getIdVaga());
				ps.setDate(2,d);
				ps.executeQuery();
				ps= (PreparedStatement) c.prepareStatement("COMMIT");
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
