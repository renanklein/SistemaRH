<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="br.uerj.rh.model.*" %>
<%@ page import="br.uerj.rh.BDconfig.*" %>
<%@ page import="java.util.*" %>
<%@ page import="java.text.SimpleDateFormat" %>	
<%
		if(request.getParameter("novaSituacao")== null){
			session.setAttribute("mensagem", "É NECESSÁRIO SELECIONAR UMA NOVA SITUAÇÃO PARA PROSSEGUIR!");
			response.sendRedirect("ManutencaoCandidato.jsp?cpf="+request.getParameter("cpf")+
								  "&nome="+request.getParameter("nome")+
								  "&processo="+request.getParameter("processo")+
								  "&unidade="+request.getParameter("unidade")+
								  "&perfil="+request.getParameter("perfil")+
								  "&espec="+request.getParameter("espec")+
								  "&regiao="+request.getParameter("regiao")+
								  "&situacao="+request.getParameter("situacao")+
								  "&loacaliz="+request.getParameter("loacaliz")+
								  "&lotacao="+request.getParameter("lotacao")+
								  "&vaga="+request.getParameter("vaga")+
								  "&idConcurso="+request.getParameter("idConcurso"));
								
		}
		String cpf, nome, processo, perfil, espec, regiao, stAtual, stNova, matricula, 
		unidade, lotacao, localiz, portaria, data;
		int idConcurso, idVaga;
				
		cpf = request.getParameter("cpf");
		nome=request.getParameter("nome");
		processo=request.getParameter("processo");
		unidade=request.getParameter("unidade");
		lotacao=request.getParameter("lotacao");
		localiz=request.getParameter("localiz");
		perfil=request.getParameter("perfil");
		espec=request.getParameter("espec");
		stAtual=request.getParameter("situacao");
		stNova=request.getParameter("novaSituacao");
		portaria=request.getParameter("portaria");
		matricula=request.getParameter("matricula");
		data=request.getParameter("data");
		if(request.getParameter("idConcurso") != null){
			idConcurso=Integer.parseInt(request.getParameter("idConcurso").replaceAll(" ", ""));
		}
		else{
			idConcurso=0;
		}
		if(request.getParameter("vaga") != null){
			idVaga=Integer.parseInt(request.getParameter("vaga").replaceAll(" ", ""));
		}
		else{
			idVaga=0;
		}
		
		
		
		if(stNova.equals("Apto")||stNova.equals("Nomeado")){
			if(data != null){
				if(DAO_mCand.nomearFuncionario(cpf, portaria, data, request.getParameter("unidade1"),
						request.getParameter("lotacao1"), request.getParameter("localiz1"))){
					boolean hist = DAO_mCand.escreverHistoricoCand(idConcurso, cpf, 8, 4, portaria);
					hist = DAO_mCand.escreverHistoricoVaga(idVaga, cpf, 4);
					hist = DAO_mCand.alterarStatusVaga(idVaga, 1);
					hist = DAO_mCand.atualizarBancoNomeacao(idConcurso);
					session.setAttribute("mensagem", "Nomeação concluída com sucesso!");
				}
				else{
					session.setAttribute("mensagem", "Não foi possível conectar ao banco de dados!\n"+
						"Tente novamente, se o problema persistir entre em contato com o suporte."
					);
				}
				response.sendRedirect("ManutencaoCandidato.jsp");
			}
			else if(matricula != null){
				if(DAO_mCand.alterarEliminadoApto(cpf, 8, matricula, idVaga)){
					boolean hist = DAO_mCand.escreverHistoricoCand(idConcurso, cpf, 2, 8, "");
					hist = DAO_mCand.escreverHistoricoVaga(idVaga, cpf, 8);
					session.setAttribute("mensagem", "Alteração realizada com sucesso!");
				}
				else{
					session.setAttribute("mensagem", "Não foi possível conectar ao banco de dados!\n"+
							"Tente novamente, se o problema persistir entre em contato com o suporte."
						);
				}
				response.sendRedirect("ManutencaoCandidato.jsp");
			}
			else{
				response.sendRedirect("ManutencaoCandidato.jsp?cpf="+request.getParameter("cpf")+
									  "&nome="+request.getParameter("nome")+
									  "&processo="+request.getParameter("processo")+
									  "&unidade="+request.getParameter("unidade")+
									  "&perfil="+request.getParameter("perfil")+
									  "&espec="+request.getParameter("espec")+
									  "&regiao="+request.getParameter("regiao")+
									  "&situacao="+request.getParameter("situacao")+
									  "&loacaliz="+request.getParameter("loacaliz")+
									  "&lotacao="+request.getParameter("lotacao")+
									  "&vaga="+request.getParameter("vaga")+
									  "&idConcurso="+request.getParameter("idConcurso")+
									  "&novaSituacao="+request.getParameter("novaSituacao"));
			}
			
		}
		else if(stNova.equals("Fim de Fila")){
			if(DAO_mCand.processaFimFila(cpf)){
				session.setAttribute("mensagem", "Situação alterada com sucesso!");
				boolean hist = DAO_mCand.escreverHistoricoVaga(idVaga, cpf, 5);
				if(stAtual.equals("Candidato")){
					hist = DAO_mCand.escreverHistoricoCand(idConcurso, cpf, 1, 5, "");
				}
				else if(stAtual.equals("Fim de Fila")){
					hist = DAO_mCand.escreverHistoricoCand(idConcurso, cpf, 5, 5, "");
				}
				else{
					hist = DAO_mCand.escreverHistoricoCand(idConcurso, cpf, 2, 5, "");
					hist = DAO_mCand.alterarStatusVaga(idVaga, 4);
					
					//INCLUIR O METODO DE SELECAO DE CANDIDATO
				}	
				
			}
			else{
				session.setAttribute("mensagem", "Não foi possível conectar ao banco de dados!\n"+
						"Tente novamente, se o problema persistir entre em contato com o suporte."
					);
			}
			
		}
		else if(stNova.equals("Eliminado")){
			if(DAO_mCand.alterarEliminadoApto(cpf, 7, null, 0)){
				session.setAttribute("mensagem", "Situação alterada com sucesso!");
				boolean hist = DAO_mCand.escreverHistoricoVaga(idVaga, cpf, 7);
				hist = DAO_mCand.alterarStatusVaga(idVaga, 4);
				hist = DAO_mCand.atualizarBancoEliminacao(idConcurso);
				if(stAtual.equals("Convocado")){
					hist = DAO_mCand.escreverHistoricoCand(idConcurso, cpf, 2, 7, "");
				}
				else if(stAtual.equals("Apto")){
					hist = DAO_mCand.escreverHistoricoCand(idConcurso, cpf, 8, 5, "");
				}
				//INCLUIR O METODO DE SELECAO DE CANDIDATO
				
			}
			else{
				session.setAttribute("mensagem", "Não foi possível conectar ao banco de dados!\n"+
						"Tente novamente, se o problema persistir entre em contato com o suporte."
					);
			}
			
			response.sendRedirect("ManutencaoCandidato.jsp");
		}
		else if(stNova.equals("Convocado")){
			if(DAO_mCand.alterarStatusCandidato(cpf, 2)){
				boolean hist = DAO_mCand.escreverHistoricoCand(idConcurso, cpf, 3, 2, "");
				hist = DAO_mCand.escreverHistoricoVaga(idVaga, cpf, 2);
				session.setAttribute("mensagem", "Situação alterada com sucesso!");
			}
			else{
				session.setAttribute("mensagem", "Não foi possível conectar ao banco de dados!\n"+
						"Tente novamente, se o problema persistir entre em contato com o suporte."
					);
			}
			response.sendRedirect("ManutencaoCandidato.jsp");
		}
		
		
%>
	
