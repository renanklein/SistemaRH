package SistemaRH.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import SistemaRH.BDconfig.DAO_RH1;
import SistemaRH.model.Funcionario;

/**
 * Servlet implementation class mfuncionarioServlet
 */
public class mfuncionarioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public mfuncionarioServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//Obtendo as informacoes digitadas pelo usuario
		String matricula = request.getParameter("matricula");
		String operacao = request.getParameter("opcoes");
		//Consultando sua matricula a partir dos dados fornecidos
		Funcionario func = DAO_RH1.consultaFunc(matricula);
		String status;
		if(func.isStatus()) {
			status = "Ativo";
		}else status = "Inativo";
		HttpSession session = request.getSession();
		session.setAttribute("Funcionario", func);
		//Realizando as devidas operacoes de acordo com a opcao selecionada pelo usuario
		if(operacao.equals("exonerado")) {
			session.setAttribute("status", status);
			response.sendRedirect("mfuncionarioexo.jsp");
			//Passando o objeto funcionario para o servlet da pagina de resposta:
			request.setAttribute("Func", func);
			RequestDispatcher req = request.getRequestDispatcher("mfuncionarioexoServlet");
			req.forward(request, response);
		}else if(operacao.equals("alterar")) {
			response.sendRedirect("");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
