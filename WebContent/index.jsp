<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!doctype html>
<html lang="en">
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
    <nav class="navbar navbar-light fixed-top bg-light flex-md-nowrap p-0 shadow">
      <a class="navbar-brand col-sm-3 col-md-2 mr-0" href="#">Sistema RH UERJ</a>
      <img src="pages/img/logo_uerj_cor.png" alt="" width="40" >
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
                <a class="nav-link" href="mconcursos.jsp">
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
				<h1 class="h2">Dashboard</h1>
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
			  
			  <h2>Vagas em Aberto</h2>
			  <div class="table-responsive">
				<table class="table table-striped table-sm">
				  <thead>
					<tr>
					  <th>Id da Vaga</th>
					  <th>Unidade</th>
					  <th>Setor</th>
					  <th>Especialidade</th>
					  <th>Concurso</th>
					</tr>
				  </thead>
				  <tbody>
					<tr>
					  <td>37</td>
					  <td>Maracanã</td>
					  <td>Informática</td>
					  <td>Técnico</td>
					  <td>123456</td>
					</tr>
					<tr>
					  <td>120</td>
					  <td>Maracanã</td>
					  <td>Faculdade de Medicina</td>
					  <td>Professor</td>
					  <td>123456</td>
					</tr>
				  </tbody>
				</table>
			  </div>
				  
			  <h2>Candidatos Convocados</h2>
			  <div class="table-responsive">
				<table class="table table-striped table-sm">
				  <thead>
					<tr>
					  <th>Nome</th>
					  <th>CPF</th>
					  <th>ID da Vaga</th>
					  <th>Status</th>
					  <th>Concurso</th>
					</tr>
				  </thead>
				  <tbody>
					<tr>
					  <td>JOSÉ DA SILVA</td>
					  <td>987.654.321.98</td>
					  <td>37</td>
					  <td>Convocado</td>
					  <td>123456</td>
					</tr>
					<tr>
					  <td>MARIA DA SILVA</td>
					  <td>123.456.789.12</td>
					  <td>120</td>
					  <td>Convocado</td>
					  <td>123456</td>
					</tr>
					<!--
					<tr>
					  <td>1,003</td>
					  <td>Integer</td>
					  <td>nec</td>
					  <td>odio</td>
					  <td>Praesent</td>
					</tr>
					<tr>
					  <td>1,003</td>
					  <td>libero</td>
					  <td>Sed</td>
					  <td>cursus</td>
					  <td>ante</td>
					</tr>
					<tr>
					  <td>1,004</td>
					  <td>dapibus</td>
					  <td>diam</td>
					  <td>Sed</td>
					  <td>nisi</td>
					</tr>
					<tr>
					  <td>1,005</td>
					  <td>Nulla</td>
					  <td>quis</td>
					  <td>sem</td>
					  <td>at</td>
					</tr>
					<tr>
					  <td>1,006</td>
					  <td>nibh</td>
					  <td>elementum</td>
					  <td>imperdiet</td>
					  <td>Duis</td>
					</tr>
					<tr>
					  <td>1,007</td>
					  <td>sagittis</td>
					  <td>ipsum</td>
					  <td>Praesent</td>
					  <td>mauris</td>
					</tr>
					<tr>
					  <td>1,008</td>
					  <td>Fusce</td>
					  <td>nec</td>
					  <td>tellus</td>
					  <td>sed</td>
					</tr>
					<tr>
					  <td>1,009</td>
					  <td>augue</td>
					  <td>semper</td>
					  <td>porta</td>
					  <td>Mauris</td>
					</tr>
					<tr>
					  <td>1,010</td>
					  <td>massa</td>
					  <td>Vestibulum</td>
					  <td>lacinia</td>
					  <td>arcu</td>
					</tr>
					<tr>
					  <td>1,011</td>
					  <td>eget</td>
					  <td>nulla</td>
					  <td>Class</td>
					  <td>aptent</td>
					</tr>
					<tr>
					  <td>1,012</td>
					  <td>taciti</td>
					  <td>sociosqu</td>
					  <td>ad</td>
					  <td>litora</td>
					</tr>
					<tr>
					  <td>1,013</td>
					  <td>torquent</td>
					  <td>per</td>
					  <td>conubia</td>
					  <td>nostra</td>
					</tr>
					<tr>
					  <td>1,014</td>
					  <td>per</td>
					  <td>inceptos</td>
					  <td>himenaeos</td>
					  <td>Curabitur</td>
					</tr>
					<tr>
					  <td>1,015</td>
					  <td>sodales</td>
					  <td>ligula</td>
					  <td>in</td>
					  <td>libero</td>
					</tr>-->
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
