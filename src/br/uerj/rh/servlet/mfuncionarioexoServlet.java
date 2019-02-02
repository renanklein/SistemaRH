package br.uerj.rh.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
						session.setAttribute("candidatos", lcands);
						session.setAttribute("menssagem", "Segue as informações do candidato apto a ser convocado para a vaga");
						response.sendRedirect("pages/respPages/ProximoCand.jsp");
					}
				}
				else {
					System.out.println("2");
					session.setAttribute("menssagem", "Funcionário exnerado mas o concurso expirou");
					response.sendRedirect("pages/errorPages/exonerarError.jsp");
				}
			}
			
	}else if(radioValue.equals("nao")) {
		System.out.println("Testando ...");
		response.sendRedirect("index.jsp");
	}
  }

}
