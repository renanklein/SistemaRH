package SistemaRH.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import SistemaRH.model.Candidato;

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
		//Continua...
	}

}
