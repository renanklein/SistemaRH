package br.uerj.rh.BDconfig;

/*import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import br.uerj.rh.model.Candidato;
import br.uerj.rh.model.Especialidade;
import br.uerj.rh.model.Vaga;*/

public class DAO_RH3 {
	//Metodo(s) do caso de uso RH3
		/*public static synchronized String getPrimeiroCPF(String nome_esp) {
			String cpf = "";
			try {
				ConexaoBD a = new ConexaoBD();
				a.iniciaBd();
				Connection c = a.getConexao();
				//Descobrindo o cpf do primeiro candidato a ser chamado
				PreparedStatement ps = (PreparedStatement) c.prepareStatement("SELECT cc.cd_cpf FROM concurso_candidato as cc "
						+ "WHERE concurso_especialidade.ds_especialidade = ? and cc.min(nu_candidato_posicao) "
						+ "AND cc.id_situacao = concurso_candidato_historico.id_situacao_nova "
						+ "AND concurso_candidato_historic.id_situacao_nova = concurso_candidato_situacao_tipo.id_candidato_situacao "
						+ "AND concurso_candidato_situacao_tipo.ds_situacao = 'Em espera'");
				//OBS:Verificar se o status do candidado ainda nao selecionado e 'Em espera'
				ps.setString(1,nome_esp);
				ResultSet rs =  ps.executeQuery();
				if(rs.next()) {
					cpf = rs.getString("cd_cpf");
				}

				ps.close();
				c.close();
				a.fechaBd();
				
			}catch(SQLException s) {
				s.getStackTrace();
				return null;
			}
			return cpf;
		}*/
		/*public static synchronized Candidato convocaCandidato(String nome,int id_vaga) {
			try {
				Candidato selected = DAO_RH4.getCandidato(nome);
				//Obtendo o CPF do primeiro candidato da classificacao da especialidade
				ConexaoBD a = new ConexaoBD();
				a.iniciaBd();
				Connection c = a.getConexao();
				//Montando o objeto Candidato
				//Especialidade ...
				Especialidade espec = DAO_Util.getEspCand(selected.getChave());
				//Vinculando possivel vaga as ser preenchida pelo candidato através do id_vaga
				//Obtendo o objeto vaga do numero da vaga informado
				Vaga v = DAO_Util.getVaga(id_vaga);
				//Linkando a possivel vaga a ser preenchida pelo candidato e atualizando o historico da vaga e do candidato			
				PreparedStatement ps = (PreparedStatement) c.prepareStatement("UPDATE concurso_vaga SET id_situacao = 2"
						+ "WHERE id_vaga = ?;INSERT INTO concurso_vaga_historico (id_vaga,dt_atualizacao,cd_chave_candidato,id_situacao) VALUES (?,?,?,2);"
						+ "INSERT INTO concurso_candidato_historico (id_concurso_especialidade,cd_chave_concurso,id_situacao_antiga,id_situacao_nova,dt_mudanca_situacao)\"\r\n" + 
						"						+ \"VALUES (?,?,?,2,?);");
				ps.setInt(1,id_vaga);
				ps.setInt(2,id_vaga);
				Calendar ca = Calendar.getInstance();
				java.util.Date d = ca.getTime();
				ps.setDate(3, new java.sql.Date(d.getTime()));
				ps.setString(4, selected.getChave());
				ps.setInt(5,selected.getId_espec());
				ps.setString(6, selected.getChave());
				ps.setInt(7,selected.getId_situacao());
				ps.setDate(8, new java.sql.Date(d.getTime()));
				//Atualizando o status da vaga no BD
				DAO_Util.setStatusVaga("Em espera", id_vaga ,selected.getChave());
				//Realizando as alteracoes de status no banco de dados
				DAO_Util.setStatusCandidato("Selecionado", selected.getChave());
				//Executando a query acima...
				int up = ps.executeUpdate();
				ps.close();
				c.close();
				a.fechaBd();
				return selected;
				
			}catch(SQLException s) {
				s.getStackTrace();
				return null;
			}
		}*/
	}
