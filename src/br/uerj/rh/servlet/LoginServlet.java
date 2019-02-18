package br.uerj.rh.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.uerj.rh.model.Usuario;
import br.uerj.rh.BDconfig.DAO_user;
/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String usuario = request.getParameter("username");
		String senha = request.getParameter("password");
		Usuario user = DAO_user.consultaUser(usuario);
		HttpSession session =  request.getSession();
		if(user == null) {
			session.setAttribute("mensagem", "Usuário não encontrado! Tente novamente.");
		}
		else if(senha.equals(user.getSenha())) {
			session.setAttribute("usuario", user);
		}
		else {
			session.setAttribute("mensagem", "Senha Incorreta!");
			user = null;
		}
		response.sendRedirect("index.jsp");
	}

}
