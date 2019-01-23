<%@ page import="br.uerj.rh.model.*" %>
<%@ page import="br.uerj.rh.BDconfig.*" %>

<%
		String usuario = request.getParameter("username");
		
		String senha = request.getParameter("password");
		Usuario user = DAO_user.consultaUser(usuario);
		//out.print(usuario);
		
		//Testa usuario e senha
		if(senha.equals(user.getSenha()) && usuario.equals(user.getUsuario())) {
			session.setAttribute("usuario", user);
		}
		else {
			session.setAttribute("mensagem", "USUÁRIO E/OU SENHA INCORRETOS!");
			
		}
		response.sendRedirect("../index.jsp");
		
%>