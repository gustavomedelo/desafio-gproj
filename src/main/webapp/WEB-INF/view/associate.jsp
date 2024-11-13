<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

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
	</head>
	
	<body>
   		<div class="container-xl">
	        <div class="table-responsive">
	            <div class="table-wrapper">
	                <div class="table-title">
	                	<div class="row">
		                	<div class="col-sm-12">
		                            <h2><b>Associar Membros</b></h2><br/><br/>
	                        </div>
						</div>

						<c:if test="${not empty error}">
							<div class="alert alert-danger">${error}</div>
						</c:if>

						<form action="/members/associate" method="post">
							<div class="row">
							    <div class="col-sm-6">
							    	<label><b>Membros <span class="field-required">*</span></b></label>
									<form:select class="form-control" path="associate.idMember" id="selectMember" name="selectMember" required="required">
										<form:option value="" label="Selecione" />
								        <form:options items="${members}" itemValue="id" itemLabel="name"/>
									</form:select><br /><br />
							    </div>
							     <div class="col-sm-6">
							    	<label><b>Projetos <span class="field-required">*</span></b></label>
									<form:select class="form-control" path="associate.idProject" id="selectProject" name="selectProject" required="required">
										<form:option value="" label="Selecione" />
								        <form:options items="${projects}" itemValue="id" itemLabel="name"/>
									</form:select><br />
							    </div>
							</div>
							<div class="row">
			                	<div class="col-sm-12">
			                		<input type="submit" class="btn btn-success" value="Associar"></input>
		                        </div>
							</div>
						</form>
	                </div>
	        	</div>
	    	</div>
	    </div>
	</body>
</html>
