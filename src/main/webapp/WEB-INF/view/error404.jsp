<!DOCTYPE html>
<html lang="en">
	<head>
		<link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
		<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
		<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
		
		<style>
			<%@include file="/WEB-INF/css/error.css"%>
		</style>
	</head>
	<body>
		<div class="error">
		    <div class="error-code m-b-10 m-t-20">404 <i class="fa fa-warning"></i></div>
		    <h2 class="font-bold">Oops 404! Página não encontrada.</h2>

		    <div class="error-desc">
		        <div><br/>
		            <!-- <a class=" login-detail-panel-button btn" href="http://vultus.de/"> -->
		            <a href="/home" class="error-btn btn btn-primary"><span class="glyphicon glyphicon-home"></span> Home</a>
		        </div>
		    </div>
		</div>
	</body>
</html>