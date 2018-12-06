package SistemaRH.BDconfig;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import SistemaRH.model.Candidato;
import SistemaRH.model.Especialidade;

public class DAO_RH3 {
	//Metodo(s) do caso de uso RH3
		public static synchronized String getPrimeiroCPF(String esp) {
			String cpf;
			try {
				ConexaoBD a = new ConexaoBD();
				a.iniciaBd();
				Connection c = a.getConexao();
				//Descobrindo o cpf do primeiro candidato a ser chamado
				PreparedStatement ps = (PreparedStatement) c.prepareStatement("SELECT cc.cd_cpf FROM concurso_candidato as cc,concurso_especialidade as ce "
						+ "WHERE ce.ds_especialidade = ? and cc.min(nu_candidato_posicao)"
						+ "JOIN concurso_candidato_historico as ch ON cc.id_situacao = ch.id_situacao_nova"
						+ "JOIN concurso_candidato_situacao_tipo as ct ON ch.id_situacao_nova = ct.candidato_situacao and ct.ds_situacao = 'Em espera'");
				//OBS:Verificar se o status do candidado ainda nao selecionado e 'Em espera'
				ps.setString(1,esp);
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
		public static synchronized Candidato selecionaCandidato(String esp) {
			Candidato selected;
			try {
				//Obtendo o CPF do primeiro candidato da classificacao da especialidade
				String cpf = DAO_RH3.getPrimeiroCPF(esp);
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
				//E isso ...
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
