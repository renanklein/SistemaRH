<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

<%@ page import="br.uerj.rh.model.*" %>
<%@ page import="java.util.*" %>
<%@ page import="br.uerj.rh.BDconfig.*" %>

<% 
	int idCand = Integer.parseInt(request.getParameter("cpf").replaceAll(" ", ""));
	int idConcurso = Integer.parseInt(request.getParameter("idConcurso").replaceAll(" ", ""));
	int pos = Integer.parseInt(request.getParameter("pos").replaceAll(" ", ""));
	String nome = request.getParameter("nome");
	
	if(DAO_mCand.salvarDesempata(idConcurso, idCand, pos)){
		session.setAttribute("mensagem3", "Nova posição de "+nome+" salva com sucesso!");
	}
	else{
		session.setAttribute("mensagem3", "Erro ao tentar salvar a nova posição de "+nome+"!");
	}
	response.sendRedirect("desempate.jsp?idConcurso="+idConcurso);




%>