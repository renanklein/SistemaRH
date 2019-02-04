package br.uerj.rh.BDconfig;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import br.uerj.rh.model.*;
import br.uerj.rh.BDconfig.ConexaoBD;

public class DAO_mCand {
	
	public static synchronized boolean processaFimFila(String idCand){
		try{
			ConexaoBD a = new ConexaoBD();
			a.iniciaBd();
			Connection c = a.getConexao();
			PreparedStatement ps = (PreparedStatement) c.prepareStatement(
					"SET @conta = ( SELECT COUNT( id_situacao ) \r\n" +
					"FROM concurso_candidato\r\n" + 
					"WHERE id_situacao =5 );\r\n" + //Captura a ultima posi��o de fim de fila
					"\r\n" + 
					"UPDATE concurso_candidato SET id_situacao= 5, nu_candidato_posicao_fim= (@conta + 1) , id_VAGA= null\r\n" + 
					"WHERE cd_chave_candidato= ?;");
			ps.setString(1, idCand);
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
	public static synchronized boolean escreverHistoricoCand(int idConcurso, String idCand, int stAntiga, int stNova, String doc){
		try{
			ConexaoBD a = new ConexaoBD();
			a.iniciaBd();
			Connection c = a.getConexao();
			PreparedStatement ps = (PreparedStatement) c.prepareStatement(
					"INSERT INTO concurso_candidato_historico(id_concurso_especialidade, cd_chave_candidato,\r\n" + 
					"id_situacao_antiga,id_situacao_nova, dt_mudanca_situacao, ds_documento_autorizacao)\r\n" + 
					"VALUES ( ?, ?, ?, ?, NOW(), ?)"
			);
			ps.setInt(1, idConcurso);
			ps.setString(2, idCand);
			ps.setInt(3, stAntiga);
			ps.setInt(4, stNova);
			ps.setString(5, doc);
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
	public static synchronized boolean escreverHistoricoVaga(int idVaga, String idCand, int situacao){
		try{
			ConexaoBD a = new ConexaoBD();
			a.iniciaBd();
			Connection c = a.getConexao();
			PreparedStatement ps = (PreparedStatement) c.prepareStatement(
					"INSERT INTO concurso_vaga_historico(id_vaga, dt_atualizacao, cd_chave_candidato, id_situacao)\r\n" + 
					"VALUES ( ?, NOW(), ?, ?)"
			);
			ps.setInt(1, idVaga);
			ps.setString(2, idCand);
			ps.setInt(3, situacao);
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
	public static synchronized boolean alterarStatusCandidato(String idCand, int situacao){
		try{
			ConexaoBD a = new ConexaoBD();
			a.iniciaBd();
			Connection c = a.getConexao();
			PreparedStatement ps = (PreparedStatement) c.prepareStatement("UPDATE concurso_candidato SET id_situacao = ? WHERE cd_chave_candidato = ?");
			ps.setInt(1, situacao);
			ps.setString(2, idCand);
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
	public static synchronized boolean alterarStatusVaga(int idVaga, int situacao){
		try{
			ConexaoBD a = new ConexaoBD();
			a.iniciaBd();
			Connection c = a.getConexao();
			PreparedStatement ps = (PreparedStatement) c.prepareStatement("UPDATE concurso_vaga SET id_situacao = ? WHERE id_vaga = ?");
			ps.setInt(1, situacao);
			ps.setInt(2, idVaga);
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
	public static synchronized boolean alterarEliminadoApto(String idCand, int situacao, String matricula, int vaga){
		try{
			ConexaoBD a = new ConexaoBD();
			a.iniciaBd();
			Connection c = a.getConexao();
			PreparedStatement ps = (PreparedStatement) c.prepareStatement(
					"UPDATE concurso_candidato SET id_situacao=?, id_Matricula = ?, id_VAGA = ? WHERE cd_chave_candidato = ?"
			);
			ps.setInt(1, situacao);
			ps.setString(2, matricula);
			ps.setInt(3, vaga);
			ps.setString(4, idCand);
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
	public static synchronized boolean nomearFuncionario(String idCand, String portaria, String data, String unidade, String lotacao, String localizacao){
		try{
			ConexaoBD a = new ConexaoBD();
			a.iniciaBd();
			Connection c = a.getConexao();
			PreparedStatement ps = (PreparedStatement) c.prepareStatement(
					"UPDATE concurso_candidato\r\n" + 
					"SET id_situacao= 4, ds_codigo_portaria = ?, dt_portaria_Nomeacao = ?,\r\n" + 
					"ds_Unidade = ?, ds_lotacao = ?, ds_localizacao = ?\r\n" + 
					"WHERE cd_chave_candidato = ?"
			);
			
			ps.setString(1, portaria);
			ps.setString(2, data);
			ps.setString(3, unidade);
			ps.setString(4, lotacao);
			ps.setString(5, localizacao);
			ps.setString(6, idCand);
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
	public static synchronized boolean atualizarBancoNomeacao(int idConcurso){
		try{
			ConexaoBD a = new ConexaoBD();
			a.iniciaBd();
			Connection c = a.getConexao();
			PreparedStatement ps = (PreparedStatement) c.prepareStatement(
					"UPDATE  concurso_especialidade \r\n" + 
					"SET  nu_vacancia =  nu_vacancia -1,nu_nomeados =  nu_nomeados +1\r\n" + 
					"WHERE  id_concurso_especialidade = ?"
			);
			
			ps.setInt(1, idConcurso);
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
	public static synchronized boolean atualizarBancoEliminacao(int idConcurso){
		try{
			ConexaoBD a = new ConexaoBD();
			a.iniciaBd();
			Connection c = a.getConexao();
			PreparedStatement ps = (PreparedStatement) c.prepareStatement(
					"UPDATE concurso_especialidade\r\n" + 
					"SET nu_eliminados_exonerados = nu_eliminados_exonerados +1\r\n" + 
					"WHERE id_concurso_especialidade = ?"
			);
			
			ps.setInt(1, idConcurso);
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
	public static synchronized LinkedList<String> SelecionarCandidato(int idConcurso) {
		try {
			LinkedList<String> Lproc = new LinkedList<String>();

			ConexaoBD a = new ConexaoBD();
			a.iniciaBd();
			Connection c = a.getConexao();
			PreparedStatement ps = (PreparedStatement) c.prepareStatement(
					"SELECT  cd_processo FROM  concurso_processo WHERE  dt_validade_concurso > NOW( )"
			);

			ResultSet res = (ResultSet) ps.executeQuery();
			while (res.next()) {
				Lproc.add(res.getString("cd_processo"));
			}

			ps.close();
			c.close();
			a.fechaBd();
			return Lproc;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	public static synchronized boolean salvarDesempata(int idConcurso, int idCand, int pos){
		try{
			ConexaoBD a = new ConexaoBD();
			a.iniciaBd();
			Connection c = a.getConexao();
			PreparedStatement ps = (PreparedStatement) c.prepareStatement(
					"UPDATE concurso_candidato SET nu_candidato_posicao = ?, nu_candidato_posicao_empate = 0\r\n" + 
					"WHERE id_concurso_especialidade = ?\r\n" + 
					"AND cd_chave_candidato = ?"
			);
			
			ps.setInt(1, pos);
			ps.setInt(2, idConcurso);
			ps.setInt(3, idCand);
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
}
