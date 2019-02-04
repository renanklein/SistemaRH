<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="br.uerj.rh.model.*" %>
<%@ page import="java.util.*" %>
<%@ page import="br.uerj.rh.BDconfig.*" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<meta name="description" content="">
	<meta name="author" content="">
	<link rel="icon" href="img/favicon.ico">
	<link rel="Stylesheet" type="text/css" href="pages/css/style.css">

	<title>Nomeação em Bloco</title>

	<!-- Bootstrap core CSS -->
	<link href="css/bootstrap.min.css" rel="stylesheet">

	<!-- Custom styles for this template -->
	<link href="css/dashboard.css" rel="stylesheet">
</head>
<body>
	<%
  		Usuario user = (Usuario) session.getAttribute("usuario");
    	if(user == null){
    		String mensagem = (String) session.getAttribute("mensagem");
    		if(mensagem == null){
    			mensagem = "É NECESSÁRIO SE LOGAR PARA ACESSAR O SISTEMA.";
    			session.setAttribute("mensagem", mensagem);
    		}
    		response.sendRedirect("login.jsp");
    	}
  	%>
  	<nav class="navbar navbar-light fixed-top bg-light flex-md-nowrap p-0 shadow">
		<a class="navbar-brand col-sm-3 col-md-2 mr-0" href="../index.jsp">Sistema RH UERJ</a>
		<img src="img/logo_uerj_cor.png" alt="" width="40" >
		<ul class="navbar-nav px-3">
			<li class="nav-item text-nowrap">
				<a class="nav-link" href="sair.jsp">Sair</a>
			</li>
		</ul>
	</nav>
  	<main style="padding-left:15px;padding-right:15px;" role="main"x>
  		<div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
  			<h1 class="h2">Nomeação em Bloco</h1>
		</div>
			<%
			  	LinkedList<Candidato> cand = DAO_Dash.ListarCandidatosAptos();
			%>
			   
				  <h4>Candidatos Aptos para Nomeação</h4>
				  <input type="button" class="btn btn-danger" value="Voltar" 
					onClick="history.go(-1)" style="margin:15;"/>
				  <%
						String mensagem3 = (String) session.getAttribute("mensagem3");
						if(mensagem3 != null){%>
							<font color="red" style="margin-left:25px;"><%=mensagem3 %></font>
						<%	session.setAttribute("mensagem3", null);
						}
				  %>	
					<br><br>
					<table class="table table-striped table-sm">
					  <thead>
						<tr>
						  <th>Nome</th>
						  <!-- <th>CPF</th> -->
						  <th>Matrícula</th>
						  <th>Processo</th>
						  <th>Perfil</th>
						  <th>Especialidade</th>
						  <th>Região</th>
						  <th>Unidade</th>
						  <th>Lotação</th>
						  <th>Localização</th>
						  <th>Portaria</th>
						  <th>Data Portaria</th>
						  <th>-</th>
						</tr>
					  </thead>
					  
					  <tbody id="invisivel" class="table table-striped table-sm" style="">
					 
					  <%if (cand!=null && !cand.isEmpty()){
					  		String nomeacao = "nomeacao";
						  	int cont = 1;%>
						  <%for(Candidato aux2: cand){//Cada iteracao gera uma linha na tabela%>
							<form class="formlugar" id="<%=nomeacao + cont %>" method="get" action="nomeacaoProc.jsp">
								<input type="text" class="form-control" value="<%=aux2.getCPF() %>"
									id="cpf" name="cpf" readonly style="display:none;">
								<input type="text" class="form-control" value="<%=aux2.getNome() %>"
									id="nome" name="nome" readonly style="display:none;">
								<input type="text" class="form-control" value="<%=aux2.getIdconcurso() %>"
									id="idConcurso" name="idConcurso" readonly style="display:none;">
								<input type="text" class="form-control" value="<%=aux2.getIdVaga() %>"
									id="idVaga" name="idVaga" readonly style="display:none;">
								
								<tr>
								  <td><%=aux2.getNome() %></td>
								  <!-- <td><%=aux2.getCPF() %></td> -->
								  <td><%=aux2.getMatricula() %></td>
								  <td><%=aux2.getProcesso() %></td>
								  <td><%=aux2.getPerfil() %></td>
								  <td><%=aux2.getEspecialidade() %></td>
								  <td><%=aux2.getRegiao() %></td>
								  <td><input type="text" class="form-control" size="8" id="unidade" name="unidade" required 
										placeholder="Unidade" value="<%=aux2.getUnidade() %>"></td>
								  <td><input type="text" class="form-control" size="8" id="lotacao" name="lotacao" required 
										placeholder="Lotação" value="<%=aux2.getLotacao() %>"></td>
								  <td><input type="text" class="form-control" size="8" id="localiz" name="localiz" required 
										placeholder="Localização" value="<%=aux2.getLocalizacao() %>"></td>
								  <td><input type="text" class="form-control" size="8" id="portaria" name="portaria" required 
										placeholder="Portaria" value="<%=aux2.getLotacao() %>"></td>
								  <td><input type="date" class="form-control" size="6" id="data" name="data" required 
										placeholder="" value=""></td>
								  
								  <td><input type="submit" class="btn btn-primary" value="Nomear"
								  		id="enviar" name="enviar" style="margin:15;"/>
								  </td>
								</tr>
							</form>
						  <%cont++;
						  }%>
					  <%}
					  	else{%>
					  		<tr>
					  			<td>Sem dados a serem exibidos.</td>
					  		</tr>
					  	<%}%>		
					  </tbody>
					</table>
  				
	</main>

</body>
</html>