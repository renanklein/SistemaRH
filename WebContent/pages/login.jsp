<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!doctype html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="img/favicon.ico">

    <title>Sistema de Gestão de RH</title>

    <!-- Bootstrap core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="css/signin.css" rel="stylesheet">
  </head>

  <body class="text-center">
    <form class="form-signin" action="loginproc.jsp" method="post">
      <img class="mb-4" src="img/logo_uerj_cor.png" alt="" width="150" >
      <h1 class="h3 mb-3 font-weight-normal">Faça o login</h1>
      <p><font color="red"><i>
      <%
      		String mensagem = (String) session.getAttribute("mensagem");
      		if(mensagem != null){
      			out.print(mensagem);
      			session.setAttribute("mensagem", null);
      		}
      %>
      </i></font></p>
      
      <label for="username" class="sr-only">Matrícula</label>
      <input type="text" id="username" name="username" class="form-control" placeholder="Matrícula" required autofocus>
      <label for="password" class="sr-only">Senha</label>
      <input type="password" id="password" name="password" class="form-control" placeholder="Senha" required>
      
      <button class="btn btn-lg btn-primary btn-block" type="submit">Entrar</button>
      <p class="mt-5 mb-3 text-muted">&copy; 2018</p>
    </form>
    
  </body>
</html>