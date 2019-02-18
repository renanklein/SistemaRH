package br.uerj.rh.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.uerj.rh.BDconfig.DAO_Util;
import br.uerj.rh.model.Candidato;

/**
 * Servlet implementation class CandidatoSelecionadoServlet
 */
public class CandidatoSelecionadoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CandidatoSelecionadoServlet() {
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
		// TODO Auto-generated method stub
		Candidato selecionado = (Candidato) request.getAttribute("Selecionado");
		//int numero_vaga = Integer.parseInt(request.getParameter("IdVaga"));
		DAO_Util.setStatusCandidato(2,selecionado);
		response.sendRedirect("DadosCand.jsp");
	}

}
