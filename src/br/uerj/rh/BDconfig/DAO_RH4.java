package br.uerj.rh.BDconfig;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import br.uerj.rh.model.*;


public class DAO_RH4 {
	//Metodo(s) relacionado(s) ao Caso de Uso RH4
		public static synchronized Candidato getCandidato(String chave) {
			System.out.println("Procurando candidato");
			try {
				Candidato cand = null;
				//Informacoes necessarias para popular o objeto candidato
				ConexaoBD a = new ConexaoBD();
				a.iniciaBd();
				Connection c = a.getConexao();
				//Montando o objeto candidato a partir do nome informado
				PreparedStatement ps = (PreparedStatement) c.prepareStatement("SELECT cc.nm_nome_completo,cc.id_situacao,cc.ds_Unidade,ce.cd_processo\r\n" + 
						"						,ce.ds_perfil,ce.ds_especialidade,ce.ds_regiao,cs.ds_situacao,ce.id_concurso_especialidade\r\n" + 
						"						FROM concurso_candidato as cc\r\n" + 
						"						JOIN concurso_especialidade as ce on cc.id_concurso_especialidade = ce.id_concurso_especialidade\r\n" + 
						"						JOIN concurso_candidato_situacao_tipo as cs on cs.id_candidato_situacao = cc.id_situacao\r\n" + 
						"						WHERE cc.cd_chave_candidato = ?;");
				ps.setString(1, chave);
				ResultSet rs = ps.executeQuery();
				if(rs.next()) {
					cand = new Candidato(
							chave,
							rs.getString("nm_nome_completo"),
							rs.getString("ds_situacao"),
							rs.getString("ds_Unidade"),
							rs.getString("cd_processo"),
							rs.getString("ds_perfil"),
							rs.getString("ds_especialidade"),
							rs.getString("ds_regiao")
							);
					cand.setId_situacao(rs.getInt("id_situacao"));
					cand.setId_espec(rs.getInt("id_concurso_especialidade"));
				}
				rs.close();
				ps.close();
				c.close();
				a.fechaBd();
				return cand;
			}catch(SQLException s) {
				s.getStackTrace();
				return null;
			}
		}
		public static synchronized Funcionario candEfetivado(Candidato efetivado,int id_vaga,String mat) {
			Funcionario novo_func = null;
			//Vaga v = DAO_Util.getVaga(id_vaga);
			try {
				ConexaoBD a = new ConexaoBD();
				a.iniciaBd();
				Connection c = a.getConexao();
				//Mudando o status do candidato e da vaga
				//Alterando os dados do status da vaga e do candidato
				DAO_Util.setStatusCandidato(4, efetivado);
				DAO_Util.setStatusVaga(1, id_vaga, efetivado.getChave());
				
				//Atualizando o Banco com a matricula e data de portaria do novo funcionario
				
				PreparedStatement ps = (PreparedStatement) c.prepareStatement("UPDATE concurso_candidato SET id_Matricula = ?, dt_portaria_Nomeacao = ?"
						+ "WHERE cd_chave_candidato = ?");
				ps.setString(1, mat);
				Calendar ca = Calendar.getInstance();
				java.util.Date d = ca.getTime();
				ps.setDate(2, new java.sql.Date(d.getTime()));
				ps.setString(2, efetivado.getChave());
				
				int teste = ps.executeUpdate();
				
				ps.close();
				c.close();
				a.fechaBd();
				
			}catch(SQLException s) {
				s.getStackTrace();
			}
			return novo_func;
		}
}
