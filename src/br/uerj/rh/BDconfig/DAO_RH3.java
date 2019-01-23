package br.uerj.rh.BDconfig;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import br.uerj.rh.model.Candidato;
import br.uerj.rh.model.Especialidade;
import br.uerj.rh.model.Vaga;

public class DAO_RH3 {
	//Metodo(s) do caso de uso RH3
		public static synchronized String getPrimeiroCPF(String nome_esp) {
			String cpf;
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
				cpf = rs.getString("cd_cpf");

				ps.close();
				c.close();
				a.fechaBd();
				
			}catch(SQLException s) {
				s.getStackTrace();
				return null;
			}
			return cpf;
		}
		public static synchronized Candidato selecionaCandidato(String cpf,int nu_vaga) {
			Candidato selected;
			try {
				//Obtendo o CPF do primeiro candidato da classificacao da especialidade
				ConexaoBD a = new ConexaoBD();
				a.iniciaBd();
				Connection c = a.getConexao();
				//Realizando as alteracoes de status no banco de dados
				DAO_Util.setStatusCandidato("Selecionado", cpf);
				//Montando o objeto Candidato
				//Especialidade ...
				Especialidade espec = DAO_Util.getEspCand(cpf);
				//Pegando o nome do candango ...
				PreparedStatement ps = (PreparedStatement) c.prepareStatement("SELECT nm_nome_completo FROM pessoa WHERE cd_cpf = ? ");
				ResultSet rs = ps.executeQuery();
				String nome_cand = rs.getString("nm_nome_completo");
				//Montando o objeto candidato
				selected = new Candidato(cpf,espec,"Selecionado",nome_cand);
				//Vinculando possivel vaga as ser preenchida pelo candidato através do id_vaga
				//Obtendo o objeto vaga do numero da vaga informado
				Vaga v = DAO_Util.getVaga(nu_vaga);
				//Linkando a possivel vaga a ser preenchida pelo candidato e atualizando o historico da vaga
				ps = (PreparedStatement) c.prepareStatement("SELECT id_vaga FROM concurso_vaga WHERE nu_vaga = ?");
				ps.setInt(1, nu_vaga);
				rs = ps.executeQuery();
				int id_vaga = rs.getInt("id_vaga");
				ps = (PreparedStatement) c.prepareStatement("UPDATE concurso_vaga SET id_situacao = concurso_candidato_situacao_tipo.id_candidato_situacao"
						+ "WHERE concurso_candidato_situacao_tipo.id_candidato_situacao = concurso_candidato.id_situacao"
						+ "AND concurso_candidato.cd_cpf = ?;INSERT INTO concurso_vaga_historico (id_vaga,dt_atualizacao,cd_cpf,id_situacao) VALUES (?,?,?,?);");
				ps.setString(1,cpf);
				ps.setInt(2,id_vaga);
				Calendar ca = Calendar.getInstance();
				Date d = (Date) ca.getTime();
				ps.setDate(3, d);
				ps.setString(4, cpf);
				//Atribuindo a query o valor inteiro que representa o status 'Em espera'
				ps.setInt(5, 2);
				//Atualizando o status da vaga no BD
				DAO_Util.setStatusVaga("Em espera", nu_vaga,cpf);
				//Executando a query acima...
				int up = ps.executeUpdate();
				ps.close();
				c.close();
				a.fechaBd();
				
			}catch(SQLException s) {
				s.getStackTrace();
				return null;
			}
			return selected;
		}
}
