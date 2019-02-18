<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*" %>
<%@ page import="br.uerj.rh.model.*" %>
<%@ page import="br.uerj.rh.BDconfig.DAO_Concurso" %>
<!doctype html>
<html>
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="img/favicon.ico">

    <title>Sistema RH UERJ</title>

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
    		response.sendRedirect("pages/login.jsp");
    	}
  	%>
    <nav class="navbar navbar-light fixed-top bg-light flex-md-nowrap p-0 shadow">
      <a class="navbar-brand col-sm-3 col-md-2 mr-0" href="index.jsp">Sistema RH UERJ</a>
      <img src="img/logo_uerj_cor.png" alt="" width="40" >
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
                <a class="nav-link" href="Relatorios.jsp">
                  <span data-feather="bar-chart-2"></span>
                  Relatórios
                </a>
              </li>
            </ul>
          </div>
        </nav>
			
        <main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-4">
			  
			  <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
				<h1 class="h2">Relatórios</h1>
			  </div>
			  
			  <%
    			LinkedList<Concurso> concur = DAO_Concurso.ListarConcursos();
			    LinkedList<Especialidade> vagas = DAO_Concurso.ListarEspecialidades();
			    Date data = new Date(System.currentTimeMillis());	  
			   %> 
						  				  
			  <h4>Concursos</h4>
			  <div class="table-responsive">
				<table class="table table-striped table-sm">
				  <thead>
					<tr>
					  <th>Processo</th>
					  <th>Status</th>
					  <th>Valido até</th>
					</tr>
				  </thead>
				  <tbody>
				  <%if (concur != null){ %>
				  <%for(Concurso aux: concur){ %>
					<tr>
					  <td><%=aux.getNum_processo()%></td>
					  <td><%=aux.getStatus()%></td>
					  <td><%=aux.getDT_validade()%></td>			  
					</tr>
				  <%}%>
				  <%}%>	
				  </tbody>
				</table>
			  </div>
			  <h4>Vagas</h4>
			  <div class="table-responsive">
				<table class="table table-striped table-sm">
				<thead>
					<tr>
					  <th>Processo</th>
					  <th>Vagas Iniciais</th>
					  <th>Vagas Ampliadas</th>
					  <th>Vagas em Aberto</th>
					  <th>Banco Restante</th>
					</tr>
				  </thead>
				  <tbody>
				  <%if (vagas != null){ %>
				  <%for(Especialidade aux2: vagas){ %>
					<tr>
					  <td><%=aux2.getProcesso()%></td>
					  <td><%=aux2.getVagas_iniciais()%></td>
					  <td><%=aux2.getVagas_amplicadas()%></td>
					  <td><%=aux2.getVacancias()%></td>
					  <td><%=aux2.getCand_restantes()%></td>				  
					</tr>
				  <%}%>
				  <%}%>	
				  </tbody>
				 </table>
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
