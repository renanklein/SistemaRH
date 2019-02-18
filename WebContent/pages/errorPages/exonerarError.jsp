<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import ="br.uerj.rh.model.Candidato" %>
<%@ page import ="br.uerj.rh.model.Usuario" %>
<%@ page import ="java.util.ArrayList"  %>
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
	<title>Error</title>
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
    		response.sendRedirect("pages/login.jsp");
    	}
  	%>
	<nav class="navbar navbar-light fixed-top bg-light flex-md-nowrap p-0 shadow">
		<a class="navbar-brand col-sm-3 col-md-2 mr-0" href="#">Sistema RH UERJ</a>
		<img src="../img/logo_uerj_cor.png" alt="" width="40" >
		<ul class="navbar-nav px-3">
			<li class="nav-item text-nowrap">
				<a class="nav-link" href="#">Sair</a>
			</li>
		</ul>
	</nav>

	<%String msg = (String) session.getAttribute("menssagem"); %>
  <main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-4">

  	<div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
  		
  		<h1 class="h2"><%=msg %></h1>
		</div>

		<!--<canvas class="my-4 w-100" id="myChart" width="900" height="380"></canvas>-->
        <h5>Clique em "Ok" para ir ao início da aplicação</h5>
		
        <div>
			<span>
				<input type="button" class="btn btn-primary" id="ok" value="OK" style="margin:15;" onclick="window.location.href= '../../index.jsp'"/>
			</span>
		</div>		
</body>
</html>