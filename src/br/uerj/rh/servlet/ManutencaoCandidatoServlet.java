package br.uerj.rh.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.uerj.rh.BDconfig.DAO_RH4;
import br.uerj.rh.model.Candidato;

/**
 * Servlet implementation class ManutencaoCandidatoServlet
 */
public class ManutencaoCandidatoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManutencaoCandidatoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//Obtendo o cpf digitado pelo usuario
				String cpf = request.getParameter("CpfCandidato");
				//Obtendo o candidato atraves do cpf
				Candidato c = DAO_RH4.getCandidato(cpf);
				HttpSession session = request.getSession();
				//Mandando o objeto do referido candidato para a pagina de resposta
				session.setAttribute("Candidato", c);
				response.sendRedirect("ManutencaoCandidatoPagResposta.jsp");
				//Agora enviando o objeto do candidato para o servlet da pagina de resposta
				request.setAttribute("Cand", c);
				RequestDispatcher req = request.getRequestDispatcher("ManutencaoCandidatoRespServlet");
				req.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

}
