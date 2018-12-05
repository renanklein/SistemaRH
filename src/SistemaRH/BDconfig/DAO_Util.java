package SistemaRH.BDconfig;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import SistemaRH.model.Especialidade;
import SistemaRH.model.Vaga;

public class DAO_Util {
	public static synchronized Especialidade getEspCand(String cpf) {
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
			ps.close();
			c.close();
			a.fechaBd();
		}catch(SQLException s) {
			s.getStackTrace();
			return null;
		}
		return espec;
	}
	public static synchronized Vaga getVaga(Especialidade espec) {
		Vaga v = null;
		try {
			ConexaoBD a = new ConexaoBD();
			a.iniciaBd();
			Connection c = a.getConexao();
			PreparedStatement ps = (PreparedStatement) c.prepareStatement("SELECT * FROM concurso_vaga_historico as vh "
					+ "JOIN concurso_especialidade as ce ON ce.id_concurso_especialidade = vt.id_concurso_especialidade"
					+ "WHERE ce.ds_especialidade = ?");
			ps.setString(1,espec.getNome_espec());
			ResultSet rs = ps.executeQuery();
			//Continua ...
		}catch(SQLException s) {
			s.getStackTrace();
		}
		return v;
	}
}
