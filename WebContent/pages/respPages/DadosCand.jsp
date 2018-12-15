<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import ="SistemaRH.model.Candidato" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<meta name="description" content="">
	<meta name="author" content="">
	<link rel="icon" href="../img/favicon.ico">
	 <!-- Bootstrap core CSS -->
    <link href="../css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="../css/dashboard.css" rel="stylesheet">
	<title>Confirmação</title>
</head>
<body>
	<nav class="navbar navbar-light fixed-top bg-light flex-md-nowrap p-0 shadow">
		<a class="navbar-brand col-sm-3 col-md-2 mr-0" href="#">Sistema RH UERJ</a>
		<img src="../img/logo_uerj_cor.png" alt="" width="40" >
		<ul class="navbar-nav px-3">
			<li class="nav-item text-nowrap">
				<a class="nav-link" href="#">Sair</a>
			</li>
		</ul>
	</nav>

	<div class="container-fluid">
		<div class="row">
			<nav class="col-md-2 d-none d-md-block bg-light sidebar">
				<div class="sidebar-sticky">
					<ul class="nav flex-column">
						<li class="nav-item">
							<a class="nav-link active" href="#">
								<span data-feather="home"></span>
								Dashboard <span class="sr-only">(current)</span>
							</a>
						</li>
						<li class="nav-item">
							<a class="nav-link" href="mconcurso.jsp">
								<span data-feather="layers"></span>
								Manutenção de Concursos
							</a>
						</li>
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
  		<h1 class="h2">Operação realizada com sucesso</h1>
		</div>

		<!--<canvas class="my-4 w-100" id="myChart" width="900" height="380"></canvas>-->
        <h5>Informações do Candidato</h5>
		<div class="table-responsive">
			<table class="table table-striped table-sm">
				<thead>
					<tr>
						<th>Nome</th>
						<th>CPF</th>
						<th>Status</th>
						<th>Concurso</th>
					</tr>
				</thead>
				<%Candidato confirmacao = (Candidato) session.getAttribute("Candidato");%>
				<tbody>
					<!--<tr>
						<td>JOSÉ DA SILVA</td>
						<td>987.654.321.98</td>
						<td>37</td>
						<td>Convocado</td>
						<td>123456</td>
					</tr> -->
					<tr>
						<td><%=confirmacao.getNome() %></td>
						<td><%=confirmacao.getCPF() %></td>
						<td><%=confirmacao.getStatus() %></td>
						<td><%=confirmacao.getCad_esp().getId_concurso() %></td>
					</tr>
				</tbody>
			</table>
        </div>
        <div>
			<span>
				<input type="button" class="btn btn-primary" id="ok" value="OK" style="margin:15;" onclick="window.location.href= '../../index.jsp'"/>
			</span>
		</div>		
</body>
</html>