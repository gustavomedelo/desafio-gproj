<%@taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="../common/header.jsp"/>

<!DOCTYPE html>
<html lang="en">
	<head>
	    <meta charset="utf-8">
	    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	    <title>GPROJ</title>
	    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
	    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
	    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
	    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
	    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
	    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
	    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
		
		<style>
			<%@include file="/WEB-INF/css/style.css"%>
		</style>
		
		<script>
			$(document).ready(function(){

			});
		</script>
	</head>
	
	<body>
   		<div class="container-xl">
	        <div class="table-responsive">
	            <div class="table-wrapper">
	                <div class="table-title">
	                    <div class="row">
	                        <div class="col-sm-6">
	                            <h2><b>Projetos</b></h2>
	                        </div>
	                        <div class="col-sm-6">
	                            <a class="btn btn-success" href="/projects/create"><i class="material-icons">&#xE147;</i><span>Adicionar</span></a>
	                        </div>
	                    </div>
	                </div>

					<c:if test="${not empty error}">
						<div class="alert alert-danger">${error}</div>
					</c:if>
	
	                <table class="table table-striped table-hover">
	                	<colgroup>
					       <col span="1" style="width: 15%;">
					       <col span="1" style="width: 15%;">
					       <col span="1" style="width: 15%;">
					       <col span="1" style="width: 15%;">
					       <col span="1" style="width: 10%;">
					       <col span="1" style="width: 15%;">
					       <col span="1" style="width: 15%;">
					    </colgroup>
	                
	                    <tr>
	                        <th>NOME</th>
	                        <th>DATA INICIO</th>
	                        <th>STATUS</th>
	                        <th>ORÇAMENTO</th>
	                        <th>RISCO</th>
	                        <th>GERENTE</th>
	                        <th ></th>
	                    </tr>
	                    <c:forEach var="project" items="${projects.pageList}">
							<tr>
								<td>${project.name}</td>
								<td>${project.initDate}</td>
								<td>${project.status}</td>
								<td>
									<c:if test="${not empty project.budget}">
									    R$ ${project.budget}
									</c:if>
								</td>
								<td>${project.risk}</td>
								<td>${project.manager.name}</td>
								<td>
									<a href="/projects/${project.id}" class="edit">
											<i class="material-icons" data-toggle="tooltip" title="Visualizar">&#xe8f4;</i></a>
									<a href="/projects/${project.id}/update" class="edit">
										<i class="material-icons" data-toggle="tooltip" title="Editar">&#xE254;</i></a>
									<c:if test="${project.allowDelete}">
										<a href="" data-target="#confirmarExclusaoModal_${project.id}" class="delete" data-toggle="modal">
											<i class="material-icons" data-toggle="tooltip" title="Deletar">&#xE872;</i>
										</a>
									</c:if>
								</td>
							</tr>
							
							 <!-- Delete Modal HTML -->
							<div id="confirmarExclusaoModal_${project.id}" class="modal fade">
								<div class="modal-dialog">
									<div class="modal-content">
										<form action="/projects/${project.id}/delete" method="post">
											<div class="modal-header">						
												<h4 class="modal-title">Deletar projeto</h4>
												<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
											</div>
											<div class="modal-body">					
												<p>Tem certeza que deseja deletar o projeto ${project.name}?</p>
											</div>
											<div class="modal-footer">
									           <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancelar</button>
									           <button type="submit" class="btn btn-danger">Deletar</button>
									         </div>
										</form>
									</div>
								</div>
							</div>
						</c:forEach>
	            	</table>
	            
		            <div class="clearfix">
						<ul class="pagination">
							<!-- Anterior -->
							<li class="page-item ${currentPage == 0 ? 'disabled' : ''}">
								<a href="/projects?page=${currentPage - 1}" class="page-link">Anterior</a>
							</li>

							<!-- P?ginas -->
							<c:forEach var="page" begin="${projects.firstLinkedPage}" end="${projects.lastLinkedPage}" step="1">
								<li class="page-item ${currentPage == page ? 'active' : ''}">
									<a href="/projects?page=${page}" class="page-link">${page}</a>
								</li>
							</c:forEach>

							<!-- Pr?xima -->
							<li class="page-item ${currentPage == totalPages - 1 ? 'disabled' : ''}">
								<a href="/projects?page=${currentPage + 1}" class="page-link">Próxima</a>
							</li>
						</ul>
		            </div>            
	        	</div>
	    	</div>
	    </div>
	</body>
</html>
