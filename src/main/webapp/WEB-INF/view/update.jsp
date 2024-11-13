<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
	<head>
	    <meta charset="utf-8">
	    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	    <title>GPROJ</title>
	    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
	    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
	    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
	    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
	    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
	    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
		
		<style>
			<%@include file="/WEB-INF/css/style.css"%>
		</style>
	</head>
	
	<body>
   		<div class="container-xl">
	        <div class="table-responsive">
	            <div class="table-wrapper">
	                <div class="table-title">
	                    <div class="row">
	                        <div class="col-sm-6">
	                            <h2><b>Editar Projeto</b></h2>
	                        </div>
	                    </div>
	                </div>
		            
		            <div class="container">					
						<form action="/projects/${project.id}/update" method="post">
							<jsp:include page="../common/project.jsp"/>
	                        
	                        <div>
	                        	<a class="btn btn-secondary" href="/projects"><span>Cancelar</span></a>
	                            <input type="submit" class="btn btn-success" value="Atualizar"></input>
	                        </div>
						</form>
					</div>
	        	</div>
	    	</div>
	    </div>
	</body>
</html>