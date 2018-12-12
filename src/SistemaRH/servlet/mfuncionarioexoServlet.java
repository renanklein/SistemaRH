package SistemaRH.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import SistemaRH.BDconfig.DAO_RH1;
import SistemaRH.model.Funcionario;

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
		//Obtendo as informacoes digitadas pelo usuario:
		String radioValue = request.getParameter("opcao");
		String textAreaValue = request.getParameter("contatocomentario");
		//Obtendo o objeto do funcionario do servlet mfuncionario:
		Funcionario f = (Funcionario) request.getAttribute("Func");
		//Realizando operacoes de acordo com a opcao marcada pelo usuario:
		if(radioValue.equals("sim")) {
			boolean result = DAO_RH1.exoneraFunc(f.getMatricula());
			//Rotina para redirecionar o usuario caso a operacao de exoneracao foi um sucesso ou nao:
			if(result) response.sendRedirect("");
			else response.sendRedirect("deu ruim");
		}else if(radioValue.equals("nao")) response.sendRedirect("../index.jsp");
	}

}
