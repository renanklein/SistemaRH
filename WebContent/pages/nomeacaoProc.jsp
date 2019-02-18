<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

<%@ page import="br.uerj.rh.model.*" %>
<%@ page import="java.util.*" %>
<%@ page import="br.uerj.rh.BDconfig.*" %>

<%
	String cpf=request.getParameter("cpf");
	String nome=request.getParameter("nome");
	String unidade=request.getParameter("unidade");
	String lotacao=request.getParameter("lotacao");
	String localiz=request.getParameter("localiz");
	String portaria=request.getParameter("portaria");
	String data=request.getParameter("data");
	int idConcurso=Integer.parseInt(request.getParameter("idConcurso").replaceAll(" ", ""));
	int idVaga=Integer.parseInt(request.getParameter("idVaga").replaceAll(" ", ""));
	
	if(DAO_mCand.nomearFuncionario(cpf, portaria, data, unidade,
			lotacao, localiz)){
		boolean hist = DAO_mCand.escreverHistoricoCand(idConcurso, cpf, 8, 4, portaria);
		hist = DAO_mCand.escreverHistoricoVaga(idVaga, cpf, 4);
		hist = DAO_mCand.alterarStatusVaga(idVaga, 1);
		session.setAttribute("mensagem3", nome +" nomeado(a) com sucesso!");
	}
	else{
		session.setAttribute("mensagem3", "Não foi possível conectar ao banco de dados!\n"+
			"Tente novamente, se o problema persistir entre em contato com o suporte."
		);
	}
	
	response.sendRedirect("nomeacao_bloco.jsp");


%>
