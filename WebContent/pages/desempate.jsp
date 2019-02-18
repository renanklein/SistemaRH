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

	<title>Desempatar Candidatos</title>

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
		<a class="navbar-brand col-sm-3 col-md-2 mr-0" href="#">Sistema RH UERJ</a>
		<img src="img/logo_uerj_cor.png" alt="" width="40" >
		<ul class="navbar-nav px-3">
			<li class="nav-item text-nowrap">
				<a class="nav-link" href="sair.jsp">Sair</a>
			</li>
		</ul>
	</nav>

	<div class="container-fluid">
		<div class="row">
			<nav class="col-md-2 d-none d-md-block bg-light sidebar">
				<div class="sidebar-sticky">
					<ul class="nav flex-column">
						<li class="nav-item">
							<a class="nav-link active" href="../index.jsp">
								<span data-feather="home"></span>
								Inicio <span class="sr-only"></span>
							</a>
						</li>
						<!--<li class="nav-item">
							<a class="nav-link" href="mconcusos.jsp">
								<span data-feather="layers"></span>
								Manutenção de Concursos
							</a>
						</li>  -->
						<li class="nav-item">
							<a class="nav-link" href="mfuncionario.jsp">
								<span data-feather="users"></span>
								Manutenção de Funcionários
							</a>
						</li>
						<li class="nav-item">
							<a class="nav-link" href="ManutencaoCandidato.jsp">
								<span data-feather="users">(current)</span>
								Manutenção de Candidatos
							</a>
						</li>
						<li class="nav-item">
							<a class="nav-link" href="#">
								<span data-feather="users"></span>
								Manutenção de Usuários
							</a>
						</li>
						<li class="nav-item">
							<a class="nav-link" href="#">
								<span data-feather="bar-chart-2"></span>
								Relatórios
							</a>
						</li>
					</ul>
      </div>
  </nav>

		  <main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-4">
		
		  	<div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
		  		<h1 class="h2">Desempatar Candidatos</h1>
			</div>
				<%
				int idConcurso = 1;  //Integer.parseInt(request.getParameter("idConcurso").replaceAll(" ", ""));
			  	LinkedList<Candidato> cand = DAO_Dash.ListarCandidatosEmpatados(idConcurso);
			%>
			   
				  <h4>Candidatos Empatados</h4>
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
						  <th>Processo</th>
						  <th>Perfil</th>
						  <th>Especialidade</th>
						  <th>Região</th>
						  <th>Posição Empatada</th>
						  <th>Posição Desempatada</th>
						  <th>-</th>
						</tr>
					  </thead>
					  
					  <tbody id="invisivel" class="table table-striped table-sm" style="">
					 
					  <%if (cand!=null && !cand.isEmpty()){
					  		String nomeacao = "nomeacao";
						  	int cont = 1;%>
						  <%for(Candidato aux2: cand){//Cada iteracao gera uma linha na tabela%>
							<form class="formlugar" id="<%=nomeacao + cont %>" method="get" action="desempateProc.jsp">
								<input type="text" class="form-control" value="<%=aux2.getCPF() %>"
									id="cpf" name="cpf" readonly style="display:none;">
								<input type="text" class="form-control" value="<%=aux2.getNome() %>"
									id="nome" name="nome" readonly style="display:none;">
								<input type="text" class="form-control" value="<%=aux2.getIdconcurso() %>"
									id="idConcurso" name="idConcurso" readonly style="display:none;">
																
								<tr>
								  <td><%=aux2.getNome() %></td>
								  <td><%=aux2.getProcesso() %></td>
								  <td><%=aux2.getPerfil() %></td>
								  <td><%=aux2.getEspecialidade() %></td>
								  <td><%=aux2.getRegiao() %></td>
								   <td><%=aux2.getEmpate() %></td>
								  <td><input type="text" class="form-control" size="5" id="pos" name="pos" required 
										placeholder="Posição" value=""></td>
								 
								 
								  
								  <td><input type="submit" class="btn btn-primary" value="Salvar"
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
			
		  </main>
 	</div>
 </div>
  
  <!-- Bootstrap core JavaScript
    	================================================== -->
    	<!-- Placed at the end of the document so the pages load faster -->
    	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    	<script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery-slim.min.js"><\/script>')</script>
    	<script src="../../assets/js/vendor/popper.min.js"></script>
    	<script src="../../dist/js/bootstrap.min.js"></script>

    	<!-- Icons -->
    	<script src="https://unpkg.com/feather-icons/dist/feather.min.js"></script>
    	<script>
    		feather.replace()
    	</script>
</body>
</html>