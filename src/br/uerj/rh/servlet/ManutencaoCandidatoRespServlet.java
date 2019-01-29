package br.uerj.rh.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.uerj.rh.BDconfig.DAO_Util;
import br.uerj.rh.model.Candidato;

/**
 * Servlet implementation class ManutencaoCandidatoRespServlet
 */
public class ManutencaoCandidatoRespServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManutencaoCandidatoRespServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Obtendo a opcao marcada pelo usuario
		String status = request.getParameter("opcoes");
		//Obtendo o objeto candidato o servlet ManutencaoCandidato
		Candidato c = (Candidato) request.getAttribute("Cand");
		//Realizando as devidas operacoes de acordo com o que o usuario selecionou:
		HttpSession session = request.getSession();
		session.setAttribute("Candidato",c);
		if(status.equals("eliminado")) {
			DAO_Util.setStatusCandidato(7,c);
			response.sendRedirect("pages/respPages/DadosCand.jsp");
		}else if(status.equals("efetivado")) {
			//Repassando a responsabilidade para executar a ação para um outro servlet
			request.setAttribute("Efetivado", c);
			RequestDispatcher req = request.getRequestDispatcher("CandidatoEfetivadoServlet");
			req.forward(request, response);
			//OBS: Falta pensar em uma maneira de obter o id da vaga a ser preenchida pelo candidato efetivado
			response.sendRedirect("pages/respPages/CandidatoEfetivado.jsp");
		}else if(status.equals("selecionado")) {
			//Repassando a responsabilidade para executar a ação para um outro servlet
			request.setAttribute("Selecionado",c);
			RequestDispatcher req = request.getRequestDispatcher("CandidatoSelecionadoServlet");
			req.forward(request, response);
			response.sendRedirect("pages/respPages/DadosCand.jsp");
		}else if(status.equals("fimdefila")) {
			DAO_Util.setStatusCandidato(5,c);
			response.sendRedirect("pages/respPages/DadosCand.jsp");
		}
	}

}
