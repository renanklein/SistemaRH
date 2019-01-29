<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*" %>
<%@ page import="br.uerj.rh.model.*" %>
<%@ page import="br.uerj.rh.BDconfig.DAO_Dash" %>
<!doctype html>
<html>
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="pages/img/favicon.ico">

    <title>Sistema RH UERJ</title>

    <!-- Bootstrap core CSS -->
    <link href="pages/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="pages/css/dashboard.css" rel="stylesheet">
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
      <a class="navbar-brand col-sm-3 col-md-2 mr-0" href="index.jsp">Sistema RH UERJ</a>
      <img src="pages/img/logo_uerj_cor.png" alt="" width="40" >
      <ul class="navbar-nav px-3">
      	<li class="nav-item text-nowrap">
          <a class="nav-link" href="pages/sair.jsp">Sair</a>
        </li>
        
      </ul>
    </nav>

    <div class="container-fluid">
      <div class="row">
        <nav class="col-md-2 d-none d-md-block bg-light sidebar">
          <div class="sidebar-sticky">
            <ul class="nav flex-column">
              <li class="nav-item">
                <a class="nav-link active" href="index.jsp">
                  <span data-feather="home"></span>
                  Inicio <span class="sr-only">(current)</span>
                </a>
              </li>
           <!--<li class="nav-item">
                <a class="nav-link" href="pages/mconcursos.jsp">
                  <span data-feather="layers"></span>
                  Manutenção de Concursos
                </a>
              </li>  -->
              <li class="nav-item">
                <a class="nav-link" href="pages/mfuncionario.jsp">
                  <span data-feather="users"></span>
                  Manutenção de Funcionários
                </a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="pages/ManutencaoCandidato.jsp">
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
			<!--
            <h6 class="sidebar-heading d-flex justify-content-between align-items-center px-3 mt-4 mb-1 text-muted">
              <span>Saved reports</span>
              <a class="d-flex align-items-center text-muted" href="#">
                <span data-feather="plus-circle"></span>
              </a>
            </h6>
            <ul class="nav flex-column mb-2">
              <li class="nav-item">
                <a class="nav-link" href="#">
                  <span data-feather="file-text"></span>
                  Current month
                </a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="#">
                  <span data-feather="file-text"></span>
                  Last quarter
                </a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="#">
                  <span data-feather="file-text"></span>
                  Social engagement
                </a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="#">
                  <span data-feather="file-text"></span>
                  Year-end sale
                </a>
              </li>
            </ul>-->
          </div>
        </nav>
			
        <main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-4">
			  
			  <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
				<h1 class="h2">Inicio</h1>
				<!--
				<div class="btn-toolbar mb-2 mb-md-0">
				  <div class="btn-group mr-2">
					<button class="btn btn-sm btn-outline-secondary">Share</button>
					<button class="btn btn-sm btn-outline-secondary">Export</button>
				  </div>
				  <button class="btn btn-sm btn-outline-secondary dropdown-toggle">
					<span data-feather="calendar"></span>
					This week
				  </button>
				</div>
				-->
			  </div>
			  
			  <!--<canvas class="my-4 w-100" id="myChart" width="900" height="380"></canvas>-->
			  <%
			  	LinkedList<Vaga> vagas = DAO_Dash.ListarVagasAbertas();
    			LinkedList<Candidato> cand = DAO_Dash.ListarCandidatosConvocados();
			  %>
			  <!-- <h2>Vagas em Aberto</h2>
			  <div class="table-responsive">
				<table class="table table-striped table-sm">
				  <thead>
					<tr>
					  <th>Id Vaga</th>
					  <th>Id Concurso</th>
					  <th>Processo</th>
					  <th>Especialidade</th>
					  <th>Situação</th>
					</tr>
				  </thead>
				  <tbody>
				  	
				  <%for(Vaga aux: vagas){ %>
				  	<tr>
					  <td><%=aux.getId_vaga() %></td>
					  <td><%=aux.getId_concurso() %></td>
					  <td><%=aux.getProcesso() %></td>
					  <td><%=aux.getEspecialidade() %></td>
					  <td><%=aux.getStatus() %></td>
					</tr>
				  <%} %> 
					
				  </tbody>
				</table>
			  </div> -->
				  
			  <h4>Candidatos em Processo de Convocação</h4>
			  <div class="table-responsive">
				<table class="table table-striped table-sm">
				  <thead>
					<tr>
					  <th>Nome</th>
					  <!-- <th>CPF</th> -->
					  <th>Processo</th>
					  <th>Unidade</th>
					  <th>Perfil</th>
					  <th>Especialidade</th>
					  <th>Região</th>
					  <th>Sitiação</th>
					</tr>
				  </thead>
				  <tbody>
				  <%if (cand!=null){ %>
				  <%for(Candidato aux2: cand){ %>
					<tr>
					  <td><a href="pages/ManutencaoCandidato.jsp?cpf=<%=aux2.getChave()%>&nome=<%=aux2.getNome()%>"><%=aux2.getNome() %></a></td>
					  <!-- <td><%=aux2.getChave() %></td> -->
					  <td><%=aux2.getProcesso() %></td>
					  <td><%=aux2.getUnidade() %></td>
					  <td><%=aux2.getPerfil() %></td>
					  <td><%=aux2.getEspecialidade() %></td>
					  <td><%=aux2.getRegiao() %></td>
					  <td><%=aux2.getStatus() %></td>
					</tr>
				  <%}%>
				  <%} %>		
				  </tbody>
				</table>
			  </div>
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
</html>
