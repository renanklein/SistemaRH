<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="br.uerj.rh.model.Funcionario" %>
<%@page import="br.uerj.rh.model.Usuario" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<meta name="description" content="">
	<meta name="author" content="">
	<link rel="icon" href="img/favicon.ico">
	<link rel="Stylesheet" type="text/css" href="css/style.css">

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
								Manuten��o de Concursos
							</a>
						</li>
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
  		<h1 class="h2">Informa��es do Funcionario</h1>
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
        
		<div class="table-responsive">
			<table class="table table-striped table-sm">
				<thead>
					<tr>
						<th>Nome</th>
						<th>Processo</th>
						<th>Especialidade</th>
						<th>Perfil</th>
						<th>Unidade</th>
						<th>Lotacao</th>
						<th>Localizacao</th>
						<th>Regiao</th>
					</tr>
				</thead>
				<tbody>
				<%Funcionario exo = (Funcionario) session.getAttribute("Funcionario");
				//System.out.println(exo.getNome().equals(null));
				//System.out.println(exo.getProcesso().equals(null));
				//System.out.println(exo.getUnidade().equals(null));
				//System.out.println(exo.getEsp_func().getNome_espec().equals(null));
				//System.out.println(exo.getEsp_func().getRegiao().equals(null));
				%>
					<!--<tr>
						<td>JOS� DA SILVA</td>
						<td>987.654.321.98</td>
						<td>37</td>
						<td>Convocado</td>
						<td>123456</td>
					</tr> -->
					<tr>
						<td><%=exo.getNome() %></td>
						<td><%=exo.getProcesso() %></td>
						<td><%=exo.getNm_especialidade() %></td>
						<td><%=exo.getPerfil() %></td>
						<td><%=exo.getUnidade() %></td>
						<td><%=exo.getLotacao() %></td>
						<td><%=exo.getLocalizacao() %></td>
						<td><%=exo.getRegiao() %></td>
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
        
		<h2>Manuten��o de Funcion�rio -> Exonera��o</h2>
		<div class="table-responsive">	

			<table class="table table-striped table-sm">
				<div class="divform">
					
					<h5>Confirmar exonera��o do Funcion�rio?</h5><br>
					<form class="formlugar" method="post" action="../mfuncionarioexoServlet">
						
						<label>
							<input type="radio" id="opcao" name="opcao" value="sim"> Sim
  							<input type="radio" id="opcao" name="opcao" value="nao"> N�o<br>
						</label>
						<br><br>
						<label>
							<span><p>Coment�rio de exonera��o:</p></span>
						</label>
						<br>
						<label>
							<span>
								<textarea name="contatocomentario" id="contatocomentario"  placehoder="Coment�rios/Sugest�o" required></textarea>
							</span>
						</label>
						
						<!--<br>
						<label>
							<span>	
								<input class="input" type="text" name="matricula" maxlength="8" placeholder="Digite a matr�cula" required/>
							</span>
						</label>	
						<br><br>
						<label>
							<span><p>A��o:</p></span>
						</label>
						<br>
						<label>
							<span>	
								<select class="input" name="acao" required>
                                    <option value="selecione">-- Selecione --</option>
                                    <option value="exonerar">Exonerar Funcion�rio</option>
								    <option value="alterar">Alterar dados Cadastrais</option>
                                </select>
							</span>
						</label> -->
						<br>
						<label>
							<span>
								<input type="submit" class="btn btn-primary" id="enviar" name="enviar" value="Enviar" style="margin:15;"/>
							    <input type="reset" class="btn btn-danger" value="Apagar" style="margin:15;"/>
							</span>
						</label>						
						<%session.setAttribute("func",exo); %>
					</form>
					
				
				</div>
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