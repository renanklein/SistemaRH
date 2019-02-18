<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="java.util.*" %>
<%@ page import="br.uerj.rh.model.*" %>
<%@ page import="br.uerj.rh.BDconfig.DAO_Dash" %>
<%@ page import="java.text.SimpleDateFormat" %>
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
  	<%//Este bloco testa a existencia de uma sessao ativa redirecionando para a pagina de login caso nao exista.
  	  //Colocar em todas as todas as paginas do sistema exceto a de login
  	  
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
                <a class="nav-link" href="pages/manutencao_usuario.jsp">
                  <span data-feather="users"></span>
                  Manutenção de Usuários
                </a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="pages/Relatorios.jsp">
                  <span data-feather="bar-chart-2"></span>
                  Relatórios
                </a>
              </li>
            </ul>
			
          </div>
        </nav>
			
        <main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-4">
			  
			  <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
				<h1 class="h2">Inicio</h1>
				
			<!--<div class="btn-toolbar mb-2 mb-md-0">
				  <div class="btn-group mr-2">
				  	<button class="btn btn-sm btn-outline-secondary">Share</button>
					<button class="btn btn-sm btn-outline-secondary">Export</button>
				  </div>
				  <button class="btn btn-sm btn-outline-secondary dropdown-toggle">
					<span data-feather="calendar"></span>
					This week
				  </button>
				</div> -->
				
			  </div>
			  
			  <%
			  	LinkedList<Vaga> vagas = DAO_Dash.ListarVagasAbertas();
    			LinkedList<Candidato> cand = DAO_Dash.ListarCandidatosConvocados();
			  %>
			    
			  <h4>Candidatos em Processo de Convocação</h4>
			  <input type="button" class="btn btn-primary" onClick="document.getElementById('invisivel').setAttribute('style', '');" 
			  value="Mostrar" />
			  <input type="button" class="btn btn-danger" onClick="document.getElementById('invisivel').setAttribute('style', 'display:none');" 
			  value="Ocultar" />
			  <a class="btn btn-secondary" style="margin-left:25px;" href="pages/nomeacao_bloco.jsp">Nomear em Bloco</a><br><br>
			  <div class="table-responsive">
				<table class="table table-striped table-sm">
				  <thead>
					<tr>
					  <th>Nome</th>
					  <!-- <th>CPF</th> -->
					  <th>Processo</th>
					  <th>Perfil</th>
					  <th>Especialidade</th>
					  <th>Região</th>
					  <th>Sitiação</th>
					</tr>
				  </thead>
				  
				  <tbody id="invisivel" class="table table-striped table-sm" style="">
				  <script>
				  	//document.getElementById('invisivel').setAttribute('style', 'display:none');
				  </script>
				  <%if (cand!=null){%>
					  <%for(Candidato aux2: cand){//Cada iteracao gera uma linha na tabela%>
						<tr>
						  <td><a href="pages/ManutencaoCandidato.jsp?cpf=<%=aux2.getCPF()%>
							  &nome=<%=aux2.getNome()%>
							  &processo=<%=aux2.getProcesso()%>
							  &unidade=<%=aux2.getUnidade()%>
							  &lotacao=<%=aux2.getLotacao()%>
							  &localiz=<%=aux2.getLocalizacao()%>
							  &idConcurso=<%=aux2.getIdconcurso()%>
							  &vaga=<%=aux2.getIdVaga()%>
							  &perfil=<%=aux2.getPerfil()%>
							  &espec=<%=aux2.getEspecialidade()%>
							  &regiao=<%=aux2.getRegiao()%>
							  &situacao=<%=aux2.getStatus()%>" 
						  title="Editar situação do candidato"><%=aux2.getNome() %></a></td>
						  <!-- <td><%=aux2.getCPF() %></td> -->
						  <td><%=aux2.getProcesso() %></td>
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
			  <h4>Vagas com Pendências</h4>
			  <input type="button" class="btn btn-primary" onClick="document.getElementById('invisivel_v').setAttribute('style', '');" 
			  value="Mostrar" />
			  <input type="button" class="btn btn-danger" onClick="document.getElementById('invisivel_v').setAttribute('style', 'display:none');" 
			  value="Ocultar" />
			   <%
						String mensagem2 = (String) session.getAttribute("mensagem2");
						if(mensagem2 != null){%>
							<font color="red" style="margin-left:25px;"><%=mensagem2 %></font>
						<%	session.setAttribute("mensagem2", null);
						}
				  %>
			  
			  <br><br>
			  <div class="table-responsive">
				<table class="table table-striped table-sm">
				  <thead>
					<tr>
					  <th>Processo</th>
					  <th>Perfil</th>
					  <th>Especialidade</th>
					  <th>Região</th>
					  <th>Situação</th>
					  <th>Validade Processo</th>
					  <th>Ação</th>
					</tr>
				  </thead>
				  <tbody id="invisivel_v" class="table table-striped table-sm" style="">
				  	
				  <%for(Vaga aux: vagas){ %>
				  	<tr>
					  <td><%=aux.getProcesso() %></td>
					  <td><%=aux.getPerfil() %></td>
					  <td><%=aux.getEspecialidade() %></td>
					  <td><%=aux.getRegiao() %></td>
					  <td><%=aux.getStatus() %></td>
					  <td><%=new SimpleDateFormat("dd/MM/yyyy").format(aux.getValidade()) %></td>
					  <td><a class="btn btn-secondary" href="pages/selecao.jsp?idVaga=<%=aux.getNum_vaga() %>
					  										&idConcurso=<%=aux.getId_concurso() %>
					  										&processo=<%=aux.getProcesso() %>">Selecionar</a></td>
					  <td>	<%
					  		if(aux.getStatus().equals("Pendente de Desempate")){%>
					  			<a class="btn btn-secondary" href="pages/desempate.jsp?idConcurso=<%=aux.getId_concurso() %>">Desempatar</a>
					  		<%}%>
					  </td>
					</tr>
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
	  </body>
</html>
