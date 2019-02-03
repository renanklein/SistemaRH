package br.uerj.rh.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.uerj.rh.BDconfig.DAO_RH1;
import br.uerj.rh.model.Funcionario;

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
		String pagina = "";
		//Consultando sua matricula a partir dos dados fornecidos
		Funcionario func = DAO_RH1.consultaFunc(matricula);
		HttpSession session = request.getSession();
		if(func == null || !func.isStatus()) {
			
			if(func == null) {
				session.setAttribute("menssagem", "Funcionario não encontrado");
			}else {
				session.setAttribute("menssagem", "Esse funcionario já foi exonerado");
			}
			pagina = "pages/errorPages/exonerarError.jsp";
		}
		session.setAttribute("Funcionario", func);
		//Realizando as devidas operacoes de acordo com a opcao selecionada pelo usuario
		session.setAttribute("Funcionario",func);
		if(operacao.equals("alterar") && pagina.equals("")) {
			pagina = "pages/alteraFunc.jsp";
			//Passando o objeto funcionario para o servlet da pagina de resposta:
			//request.setAttribute("Func", func);
			//RequestDispatcher req = request.getRequestDispatcher("mfuncionarioexoServlet");
			//req.forward(request, response);
		} else if(operacao.equals("exonerar") && pagina.equals("")) {
			pagina = "pages/mfuncionarioexo.jsp";
		}
		response.sendRedirect(pagina);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
