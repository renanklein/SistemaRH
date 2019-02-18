<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="br.uerj.rh.model.*" %>
<%@ page import="java.util.*" %>
<%@ page import="br.uerj.rh.BDconfig.*" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%
		
		String unidade="", lotacao="", localiz="", processo;
		int idVaga, idConcurso;
		
		idVaga=Integer.parseInt(request.getParameter("idVaga").replaceAll(" ", ""));
		idConcurso=Integer.parseInt(request.getParameter("idConcurso").replaceAll(" ", ""));
		processo=request.getParameter("processo");
		
		Calendar ca = Calendar.getInstance();
		java.util.Date d = ca.getTime();
		Date hoje = new java.sql.Date(d.getTime());
		//new SimpleDateFormat("yyyy-MM-dd").format(getTime());
		Date validade = DAO_mCand.SelecionarValidadeProcesso(processo);
		boolean hist;
		if(validade.before(hoje)){
			hist = DAO_mCand.alterarStatusVaga(idVaga, 5);
			session.setAttribute("mensagem2", "Não foi possível selecionar candidado - Concurso expirado!");
		}
		else if(DAO_mCand.sepecionarBanco(idConcurso) < 1){
			hist = DAO_mCand.alterarStatusVaga(idVaga, 5);
			session.setAttribute("mensagem2", "Não foi possível selecionar candidado - Banco Esgotado!");
		}
		else{
			Candidato cand = DAO_mCand.SelecionarCandidato(idConcurso);
			if(cand.getEmpate() > 0){
				hist = DAO_mCand.alterarStatusVaga(idVaga, 3);
				session.setAttribute("mensagem2", "Não foi possível selecionar candidado - Necessário realizar desempate!");
			}
			else if(DAO_mCand.confirmaSelecao(idConcurso, cand.getCPF(), idVaga, unidade, lotacao, localiz)){
				hist = DAO_mCand.escreverHistoricoCand(idConcurso, cand.getCPF(), 2, 3, "");
				hist = DAO_mCand.escreverHistoricoVaga(idVaga, cand.getCPF(), 3);
				hist = DAO_mCand.alterarStatusVaga(idVaga, 2);
				session.setAttribute("mensagem2", "O candidato "+cand.getNome()+" foi selecionado para a vaga!");
			}
		}

		response.sendRedirect("../index.jsp");
















%>