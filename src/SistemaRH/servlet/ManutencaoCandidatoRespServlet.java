package SistemaRH.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import SistemaRH.BDconfig.DAO_RH4;
import SistemaRH.BDconfig.DAO_Util;
import SistemaRH.model.Candidato;
import SistemaRH.model.Funcionario;

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
		if(status.equals("eliminado")) {
			//Código...
		}else if(status.equals("efetivado")) {
			Funcionario novoFunc = DAO_RH4.candEfetivado(c, 000);
			//OBS: Falta pensar em uma maneira de obter o id da vaga a ser preenchida pelo candidato efetivado
			response.sendRedirect("");
		}else if(status.equals("selecionado")) {
			DAO_Util.setStatusCandidato("Selecionado",c.getCPF());
			response.sendRedirect("");
		}else if(status.equals("emespera")) {
			DAO_Util.setStatusCandidato("Em espera",c.getCPF());
			response.sendRedirect("");
		}
	}

}
