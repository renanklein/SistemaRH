<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*"%>
<%@ page import="br.uerj.rh.model.*"%>
<%@ page import="br.uerj.rh.BDconfig.DAO_user"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
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
		if (user == null) {
			String mensagem = (String) session.getAttribute("mensagem");
			if (mensagem == null) {
				mensagem = "� NECESS�RIO SE LOGAR PARA ACESSAR O SISTEMA.";
				session.setAttribute("mensagem", mensagem);
			}
			response.sendRedirect("login.jsp");
		}
	%>
	<nav
		class="navbar navbar-light fixed-top bg-light flex-md-nowrap p-0 shadow">
		<a class="navbar-brand col-sm-3 col-md-2 mr-0" href="#">Sistema RH
			UERJ</a> <img src="img/logo_uerj_cor.png" alt="" width="40">
		<ul class="navbar-nav px-3">
			<li class="nav-item text-nowrap"><a class="nav-link"
				href="sair.jsp">Sair</a></li>
		</ul>
	</nav>

	<div class="container-fluid">
		<div class="row">
			<nav class="col-md-2 d-none d-md-block bg-light sidebar">
				<div class="sidebar-sticky">
					<ul class="nav flex-column">
						<li class="nav-item"><a class="nav-link active"
							href="../index.jsp"> <span data-feather="home"></span> Inicio
								<span class="sr-only">(current)</span>
						</a></li>
						<!--<li class="nav-item">
							<a class="nav-link" href="mconcusos.jsp">
								<span data-feather="layers"></span>
								Manuten��o de Concursos
							</a>
						</li>  -->
						<li class="nav-item"><a class="nav-link"
							href="mfuncionario.jsp"> <span data-feather="users"></span>
								Manuten��o de Funcion�rios
						</a></li>
						<li class="nav-item"><a class="nav-link"
							href="ManutencaoCandidato.jsp"> <span data-feather="users"></span>
								Manuten��o de Candidatos
						</a></li>
						<li class="nav-item"><a class="nav-link" href="#"> <span
								data-feather="users"></span> Manuten��o de Usu�rios
						</a></li>
						<li class="nav-item"><a class="nav-link" href="#"> <span
								data-feather="bar-chart-2"></span> Relat�rios
						</a></li>
					</ul>
				</div>
			</nav>

			<main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-4">

			<div
				class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
				<h1 class="h2">Manuten��o de Usu�rios - Altera��o de Senha</h1>
			</div>

			<!--<canvas class="my-4 w-100" id="myChart" width="900" height="380"></canvas>-->

			<main role="main" class="col-md-15 ml-sm-auto col-lg-10 px-0">

			<%
				//Criando Vari�vel tipo Usu�rio para rececer o usu�rio e senha no qual ser� alterado
				Usuario atualizar = new Usuario();

				//Definindo uma String usersenha que pegar� o par�metro contendo 
				//o usu�rio do formul�rio da p�gina de manuten��o de usu�rio.
				String usersenha = "";

				//Caso o valor do par�metro for diferente de vazio ele atribui este par�metro a vari�vel usersenha
				if (request.getParameter("usuariosenha") != null) {
					usersenha = request.getParameter("usuariosenha");
				}

				//Vari�veis de senha que pegam os par�metros digitados da nova senha do formul�rio da p�gina de
				//manuten��o de usu�rios
				String senha1 = request.getParameter("nova_senha");
				String senha2 = request.getParameter("confirma_senha");
				
				//Caso as duas senhas digitadas forem iguais ele faz a troca de senhas e a altera��o no BD
				if (senha1.equals(senha2)) {
					atualizar.setUsuario(usersenha);
					atualizar.setSenha(senha2);
					
					//Testes para ver se o par�metro est� pegando o valor correto
					//out.println(atualizar.getUsuario());
					//out.println(atualizar.getSenha());
					
					DAO_user.updateSenha(atualizar);

					%>
					<div style="margin-left:-100px; class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
						<h4 style="color:green;" class="h4">Senha alterada com sucesso !</h4><br>
						<a class="btn btn-danger" href="manutencao_usuario.jsp">Voltar</a>
					</div>
				<% } else {%>
					<div style="margin-left:-100px; class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
						<h4 class="h4">As duas senhas precisam ser iguais, tente novamente !</h4><br>
						<a class="btn btn-danger" href="manutencao_usuario.jsp">Voltar </a>
					</div>
				<% }
				//response.sendRedirect("manutencao_usuario.jsp");
			%> </main>
		</div>
	</div>

	<!-- Bootstrap core JavaScript
    	================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
		integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
		crossorigin="anonymous"></script>
	<script>
		window.jQuery
				|| document
						.write('<script src="../../assets/js/vendor/jquery-slim.min.js"><\/script>')
	</script>
	<script src="../../assets/js/vendor/popper.min.js"></script>
	<script src="../../dist/js/bootstrap.min.js"></script>

	<!-- Icons -->
	<script src="https://unpkg.com/feather-icons/dist/feather.min.js"></script>
	<script>
		feather.replace()
	</script>

	<!-- Graphs -->
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.7.1/Chart.min.js"></script>
	<script>
		var ctx = document.getElementById("myChart");
		var myChart = new Chart(ctx, {
			type : 'line',
			data : {
				labels : [ "Sunday", "Monday", "Tuesday", "Wednesday",
						"Thursday", "Friday", "Saturday" ],
				datasets : [ {
					data : [ 15339, 21345, 18483, 24003, 23489, 24092, 12034 ],
					lineTension : 0,
					backgroundColor : 'transparent',
					borderColor : '#007bff',
					borderWidth : 4,
					pointBackgroundColor : '#007bff'
				} ]
			},
			options : {
				scales : {
					yAxes : [ {
						ticks : {
							beginAtZero : false
						}
					} ]
				},
				legend : {
					display : false,
				}
			}
		});
	</script>
</body>