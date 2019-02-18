package br.uerj.rh.BDconfig;

public class DAO_RH2 {
	// Metodo(s) relacionados ao caso de uso RH2
		/*public static synchronized Vaga criaVaga(String num_processo,String nome_especialidade)  {
			Vaga v;
			try {
				ConexaoBD a = new ConexaoBD();
				a.iniciaBd();
				Connection c = a.getConexao();
				//Verificando se os dados digitados pelo usuario realmente constam no bd
				PreparedStatement ps = (PreparedStatement) c.prepareStatement("SELECT ce.cd_processo,ce.ds_perfil from"
						+ "concurso_especialidade as ce WHERE ce.cd_processo = ? and ce.ds_perfil = ? ");
				ps.setString(1,num_processo);
				ps.setString(2,nome_especialidade);
				ResultSet rs = ps.executeQuery();
				//Devera ter um tratamento de erro caso seja retornado uma tabela vazia
				
				//Caso realmente os dados constem no bd ...
				v = new Vaga();
				/*Acessando o banco de dados para receber informacoes da especialidade e carrega-los no
				 * objeto vaga*/ 
				/*ps = (PreparedStatement) c.prepareStatement("SELECT * FROM concurso_especialidade as ce WHERE ce.cd_processo = ? ");
				ps.setString(1,num_processo);
				rs = ps.executeQuery();
				Especialidade esp = new Especialidade();
				esp.setPerfil(rs.getString("ds_perfil"));
				esp.setNome_espec(rs.getString("ds_especialidade"));
				esp.setRegiao(rs.getString("ds_regiao"));
				esp.setVagas_iniciais(rs.getInt("nu_vagas_iniciais"));
				esp.setVagas_amplicadas(rs.getInt("nu_vagas_amplidadas"));
				esp.setQtd_aprovados(rs.getInt("nu_aprovados"));
				esp.setVacancias(rs.getInt("nu_vacancias"));
				esp.setQtd_nomeados(rs.getInt("nu_nomeados"));
				esp.setQtd_exonerados(rs.getInt("nu_eliminados_exonerados"));
				esp.setCand_restantes(rs.getInt("nu_banco_restante"));
				//Settando o objeto Especialidade no obj Vaga
				v.setVaga_espec(esp);
				
				ps.close();
				c.close();
				a.fechaBd();
				
			}catch(SQLException s) {
				s.getStackTrace();
				return null;
			}
			return v;
		}*/
}
