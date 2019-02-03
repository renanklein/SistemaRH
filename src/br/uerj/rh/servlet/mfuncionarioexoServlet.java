package br.uerj.rh.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import br.uerj.rh.BDconfig.ConexaoBD;
import br.uerj.rh.BDconfig.DAO_RH1;
import br.uerj.rh.BDconfig.DAO_Util;
import br.uerj.rh.model.Candidato;
import br.uerj.rh.model.Funcionario;

/**
 * Servlet implementation class mfuncionarioexoServlet
 */
public class mfuncionarioexoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public mfuncionarioexoServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Obtendo as informacoes digitadas pelo usuario:
		String radioValue = request.getParameter("opcao");
		//String textAreaValue = request.getParameter("contatocomentario");
		//Obtendo o objeto do funcionario do jsp
		HttpSession session = request.getSession();
		Funcionario f= (Funcionario) session.getAttribute("func");
		//Realizando operacoes de acordo com a opcao marcada pelo usuario:
		System.out.println("1");
		if(radioValue.equals("sim")) {
			System.out.println("3");
			int id_vaga = DAO_RH1.exoneraFunc(f);
			if(id_vaga < 0 ) {
				session.setAttribute("menssagem","Funcionario não encontrado");
				response.sendRedirect("pages/errorPages/exonerarError.jsp");
			}
			else {
				if(DAO_Util.isConcursoValido(f.getProcesso())) {
					ArrayList<Candidato> lcands = DAO_Util.ProximoCand(f);				
					if(lcands.size() == 0) {
						session.setAttribute("menssagem", "Funcionário exonerado mas não há candidatos disponíveis");
						response.sendRedirect("pages/errorPages/exonerarError.jsp");
					}else if(lcands.size() > 1) {
						session.setAttribute("candidatos", lcands);
						session.setAttribute("menssagem", "Ocorreu um empate");
						DAO_Util.setStatusVaga(3, id_vaga, f.getChave());
						//OBS: Verificar se a vaga ainda estará vinculada ao func, mesmo após a exoneracao
						response.sendRedirect("pages/respPages/ProximoCand.jsp");//Página para mostrar os candidatos que estão aptos a ocupar a v
					}else {
						Candidato ca = lcands.get(0);
						DAO_Util.setStatusVaga(2, f.getIdVaga(), ca.getChave());
						try {
							ConexaoBD a = new ConexaoBD();
							a.iniciaBd();
							Connection c = a.getConexao();
							PreparedStatement ps1 = (PreparedStatement) c.prepareStatement("UPDATE concurso_especialidade "
									+ "SET nu_vacancia = nu_vacancia -  1 where id_concurso_especialidade = ?");
							ps1.setInt(1, ca.getId_espec());
							int teste = ps1.executeUpdate();
							ps1 = (PreparedStatement) c.prepareStatement("UPDATE concurso_candidato SET id_situacao = ? WHERE cd_chave_candidato = ?;");
							
							ps1.setInt(1, 2);
							ps1.setString(2,ca.getChave());
							
							teste = ps1.executeUpdate();
							
							System.out.println("Opa, mudou");
							lcands.get(0).setId_vaga(2);
							lcands.get(0).setEspecialidade("Convocado");
							
							ps1.close();
							c.close();
							a.fechaBd();
							
						}catch(SQLException s) {
							s.printStackTrace();
						}
						session.setAttribute("candidatos", lcands);
						session.setAttribute("menssagem", "O devido candidato foi convocado. Clique em ok para ir ao menu principal ");
						response.sendRedirect("pages/respPages/ProximoCand.jsp");
					}
				}
				else {
					System.out.println("2");
					DAO_Util.setStatusVaga(5, f.getIdVaga(),f.getChave());
					session.setAttribute("menssagem", "Funcionário exonerado mas o concurso expirou");
					response.sendRedirect("pages/errorPages/exonerarError.jsp");
				}
			}
			
	}else if(radioValue.equals("nao")) {
		System.out.println("Testando ...");
		response.sendRedirect("index.jsp");
	}
  }

}
