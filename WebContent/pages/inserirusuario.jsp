<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*" %>
<%@ page import="br.uerj.rh.model.*" %>
<%@ page import="br.uerj.rh.BDconfig.DAO_user" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<meta name="description" content="">
	<meta name="author" content="">
	<link rel="icon" href="img/favicon.ico">
	<link rel="Stylesheet" type="text/css" href="pages/css/style.css">

	<title>Manutenção de Usuário</title>

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
								Inicio <span class="sr-only">(current)</span>
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
								<span data-feather="users"></span>
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
  		<h1 class="h2">Manutenção de Usuário - Inserir Usuário</h1>
		</div>

		<!--<canvas class="my-4 w-100" id="myChart" width="900" height="380"></canvas>-->

		<main role="main" class="col-md-15 ml-sm-auto col-lg-10 px-0">
			
		<% 
			Usuario criar = new Usuario();
			
			String nomelogin ="", senhalogin ="", senhalogincf="", permissaolog="";
			int aux_permissao=0;
		
			nomelogin = request.getParameter("nomelogin");
			//out.println(nomelogin);
			senhalogin = request.getParameter("senhalogin");
			//out.println(senhalogin);
			senhalogincf = request.getParameter("senhalogincf");
			//out.println(senhalogincf);
			permissaolog = request.getParameter("permissaolog");
			
			String valor1="1";
			String valor2="2";
			
			/**out.println(permissaolog);**/
			
			if(request.getParameter("permissaolog").equals(valor1))
			{
				aux_permissao = 1;	
			} else {
				if (request.getParameter("permissaolog").equals(valor2)){
					aux_permissao = 2;
				}
			}
							
			
			/**out.println(aux_permissao);**/
			
		%>	  			 
		
		<%
		
		//Testa usuario e senha
		if(senhalogin.equals(senhalogincf)) {
			criar.setUsuario(nomelogin);
			criar.setSenha(senhalogin);
			criar.setPermissao(aux_permissao);
			
			/**out.println(criar.getUsuario());
			out.println(criar.getSenha());
			out.println(criar.getPermissao());**/
			
			DAO_user.inserir(criar); %>
		
			<div style="margin-left:-100px; class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
				<h4 style="color:green;" class="h4">O novo usuário foi cadastrado com sucesso !</h4><br>
				<a class="btn btn-danger" href="manutencao_usuario.jsp">Voltar</a>
			</div>
		<% } else {%>
			<div style="margin-left:-100px; class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
				<h4 class="h4">As duas senhas digitadas precisam ser iguais, tente novamente !</h4><br>
				<a class="btn btn-danger" href="manutencao_usuario.jsp">Voltar </a>
			</div>
		<% }%>
		
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
