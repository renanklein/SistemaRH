package SistemaRH.BDconfig;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import SistemaRH.model.Candidato;
import SistemaRH.model.Especialidade;
import SistemaRH.model.Funcionario;
import SistemaRH.model.Vaga;

public class DAO_RH4 {
	//Metodo(s) relacionado(s) ao Caso de Uso RH4
		public static synchronized Candidato exibeCandidato(String cpf) {
			Candidato cand = null;
			try {
				//Informacoes necessarias para popular o objeto candidato
				//Nome...
				String nome,status;
				Especialidade esp = new Especialidade();
				ConexaoBD a = new ConexaoBD();
				a.iniciaBd();
				Connection c = a.getConexao();
				PreparedStatement ps = (PreparedStatement) c.prepareStatement("SELECT nm_nome_completo FROM pessoa WHERE cd_cpf = ?;");
				ps.setString(1, cpf);
				ResultSet rs = ps.executeQuery();
				nome = rs.getString("nm_nome_completo");
				//Status..
				ps = (PreparedStatement) c.prepareStatement("SELECT ct.ds_situacao FROM concurso_candidato_situacao_tipo as ct "
						+ "JOIN concurso_candidato_historico as cc ON cc.id_situacao_nova = ct.id_candidato_situacao_tipo;"
						+ "WHERE cc.cd_cpf = ?");
				ps.setString(1,cpf);
				rs = ps.executeQuery();
				status = rs.getString("ds_situacao");
				//Especialidade ... 
				esp = DAO_Util.getEspCand(cpf);
				
				cand = new Candidato(cpf,esp,status,nome);
				
			}catch(SQLException s) {
				s.getStackTrace();
			}
			return cand;
		}
		public static synchronized Funcionario candEfetivado(Candidato efetivado) {
			Funcionario novo_func = null;
			try {
				ConexaoBD a = new ConexaoBD();
				a.iniciaBd();
				Connection c = a.getConexao();
				PreparedStatement ps = (PreparedStatement) c.prepareStatement("");
				//Continua ...
			}catch(SQLException s) {
				s.getStackTrace();
			}
			return novo_func;
		}
}
