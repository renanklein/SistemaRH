<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*" %>
<%@ page import="br.uerj.rh.model.*" %>
<%@ page import="br.uerj.rh.BDconfig.DAO_Dash" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<meta name="description" content="">
	<meta name="author" content="">
	<link rel="icon" href="img/favicon.ico">
	<link rel="Stylesheet" type="text/css" href="pages/css/style.css">

	<title>Manuten��o de Usu�rio</title>

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
    			mensagem = "� NECESS�RIO SE LOGAR PARA ACESSAR O SISTEMA.";
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
								Inicio <span class="sr-only">(current)</span>
							</a>
						</li>
						<!--<li class="nav-item">
							<a class="nav-link" href="mconcusos.jsp">
								<span data-feather="layers"></span>
								Manuten��o de Concursos
							</a>
						</li>  -->
						<li class="nav-item">
							<a class="nav-link" href="mfuncionario.jsp">
								<span data-feather="users"></span>
								Manuten��o de Funcion�rios
							</a>
						</li>
						<li class="nav-item">
							<a class="nav-link" href="ManutencaoCandidato.jsp">
								<span data-feather="users"></span>
								Manuten��o de Candidatos
							</a>
						</li>
						<li class="nav-item">
							<a class="nav-link" href="#">
								<span data-feather="users"></span>
								Manuten��o de Usu�rios
							</a>
						</li>
						<li class="nav-item">
							<a class="nav-link" href="#">
								<span data-feather="bar-chart-2"></span>
								Relat�rios
							</a>
						</li>
					</ul>
      </div>
  </nav>

  <main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-4">

  	<div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
  		<h1 class="h2">Manuten��o de Usu�rios</h1>
		</div>

		<!--<canvas class="my-4 w-100" id="myChart" width="900" height="380"></canvas>-->

		<main role="main" class="col-md-15 ml-sm-auto col-lg-10 px-0">
			  			  
			  <div style="margin-left:-100px;" class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
				<h1 class="h2">Inicio</h1>
				<%
			  		LinkedList<Usuario> usuarios = DAO_Dash.ListarUsuarios();
					String aux_nome, nome_admin = "admin";
			  		String login = "admin";
			  		int permissao = 1;
			  	%>
			  	
			  	
			  </div>
			  <%if (user.getPermissao() == permissao) {%>
			  <h4 style="margin-left:-100px;">Usu�rios Cadastrados</h4>
			  <input style="margin-left:-100px;" class="btn btn-primary" type="button" onClick="document.getElementById('invisivel_v').setAttribute('style', '');" 
			  value="Mostrar" />
			  <input type="button" class="btn btn-danger" onClick="document.getElementById('invisivel_v').setAttribute('style', 'display:none');" 
			  value="Ocultar" /><br><br>
			  <div style="margin-left:-100px;" class="table-responsive">
				<table class="table table-striped table-sm">
				  <thead>
					<tr>
					  <th>Usu�rio</th>
					  <!-- <th>Senha</th> -->
					  <!-- <th>Nome Usu�rio</th> -->
					  <th>Permissao</th>
					  <th>N�vel Permiss�o</th>
					</tr>
				  </thead>
				  <tbody id="invisivel_v" class="table table-striped table-sm" style="">
				  	
				  <%for(Usuario aux: usuarios){ %>
				  <%aux_nome = aux.getUsuario(); %>
				  <% if (aux_nome != nome_admin) { %>
				  	<tr>
					  <td><%=aux.getUsuario() %></td>
					 
					  <% 
					  
					  %>
					  <td><%=aux.getPermissao() %></td>
					  <td>
					  	<% 
					     int pmr = aux.getPermissao();
					  	 if (pmr == 1) {
					  		 out.println("Administrador");
					  	 }  else {
					  	 
					  	 	if (pmr == 2) {
					  	 		out.println("Usu�rio");
					  	 		}
					  	 		
					  	 }%>
				  <%} %>	 
					  </td>					 
					</tr>
				  <%} %> 
					
				  </tbody>
				</table>
			  </div>
			  
			  <br>
				<h4 style="margin-left:-100px;" >Manuten��o do Usu�rio</h4>
			 
			  
			  <div style="margin-left:-100px;" class="table-responsive">
				<table class="table table-striped table-sm">
				  <thead>
					<tr>
					  <th>Usu�rio</th>
					  <th>Senha</th>
					  <th>Manuten��o</th>
					</tr>
				  </thead>
				  <tbody id="invisivel_v" class="table table-striped table-sm" style="">
				  
				  <%for(Usuario aux: usuarios){ %>
				  
				  	<tr>
					  <td><%=aux.getUsuario() %></td>
					  <td><a href="manutencao_usuario_senha.jsp?senhausuario=<%=aux.getUsuario()%>"><input type="submit" class="btn btn-primary" id="alterar" name="alterar" value="Alterar Senha" style="margin:15;"/></a></td>
					  <%if(aux.getPermissao() == permissao){ %>
					  		<td><input type="button" class="btn btn-secondary" id="excluir" name="excluir" value="User Default" style="margin:15;" disabled/></td>
					  <%} else {%>
					  		<td><a href="manutencao_usuario_excluir.jsp?excluirusuario=<%=aux.getUsuario()%>"><input type="submit" class="btn btn-danger" id="excluir" name="excluir" value="Excluir Usu�rio" style="margin:15;"/></a></td>
					  <%} %>
					</tr>
					
				  <%} %> 
					
				  </tbody>
				</table>
			  </div>
			  	<br>
			  	 
				<h4 style="margin-left:-100px;" >Inserir Novo Usu�rio</h4>
			  
			  <div style="margin-left:-100px;" class="table-responsive">
				<table class="table table-striped table-sm">
				  <tbody id="invisivel_v" class="table table-striped table-sm" style="">
				  
					<tr>
						<td><a href="manutencao_usuario_inserir.jsp?"><input type="submit" class="btn btn-primary" id="inserir" name="inserir" value="Inserir novo Usu�rio" style="margin:15;"/></a></td>
					</tr>
					
				  </tbody>
				</table>
			  </div>
			  
			  <%} else {%>
				  <h4 style="margin-left:-100px;" >Manuten��o do Usu�rio</h4>
					 
				  
				  <div style="margin-left:-100px;" class="table-responsive">
					<table class="table table-striped table-sm">
					  <thead>
						<tr>
						  <th>Usu�rio</th>
						  <th>Senha</th>
						</tr>
					  </thead>
					  <tbody id="invisivel_v" class="table table-striped table-sm" style="">
					  
					 
					  
					  	<tr>
						  <td><%=user.getUsuario() %></td>
						  <td><a href="manutencao_usuario_senha.jsp?senhausuario=<%=user.getUsuario()%>"><input type="submit" class="btn btn-primary" id="alterar" name="alterar" value="Alterar Senha" style="margin:15;"/></a></td>	 
						</tr>
						
					  
						
					  </tbody>
					</table>
				  </div>
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

    	<!-- Graphs -->
    	<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.7.1/Chart.min.js"></script>
    	<script>
    		var ctx = document.getElementById("myChart");
    		var myChart = new Chart(ctx, {
    			type: 'line',
    			data: {
    				labels: ["Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"],
    				datasets: [{
    					data: [15339, 21345, 18483, 24003, 23489, 24092, 12034],
    					lineTension: 0,
    					backgroundColor: 'transparent',
    					borderColor: '#007bff',
    					borderWidth: 4,
    					pointBackgroundColor: '#007bff'
    				}]
    			},
    			options: {
    				scales: {
    					yAxes: [{
    						ticks: {
    							beginAtZero: false
    						}
    					}]
    				},
    				legend: {
    					display: false,
    				}
    			}
    		});
    	</script>
</body>
