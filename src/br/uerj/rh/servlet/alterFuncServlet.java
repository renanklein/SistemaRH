package br.uerj.rh.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.uerj.rh.BDconfig.DAO_Util;
import br.uerj.rh.model.Funcionario;

/**
 * Servlet implementation class alterFuncServlet
 */
public class alterFuncServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public alterFuncServlet() {
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
		String Unidade = request.getParameter("unidade");
		String Lotacao = request.getParameter("lotacao");
		String Localizacao = request.getParameter("localizacao");
		String pagina = "";
		
		HttpSession session = request.getSession();
		
		Funcionario func = (Funcionario) session.getAttribute("func");
		
		
		if(DAO_Util.alteraFunc(func, Unidade, Lotacao, Localizacao)) {
			session.setAttribute("menssagem","Alteração de dados realizada com sucesso !");
			pagina = "pages/respPages/SucessoAlterar.jsp";
		} else {
			session.setAttribute("menssagem", "Não foi possivel alterar dados do funcionário");
			pagina = "pages/errorPages/exonerarError.jsp";
		}
		response.sendRedirect(pagina);
	}

}
