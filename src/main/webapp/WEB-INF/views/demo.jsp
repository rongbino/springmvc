<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Word Cloud</title>

<!-- Bootstrap -->
<link type="text/css" href="css/bootstrap/bootstrap.min.css"
	rel="stylesheet">
<link href="asset/css/monokai.css" rel="stylesheet">

</head>
<body>
	<div style="padding: 100px 100px 10px;">
		<form class="bs-example bs-example-form" role="form">
			<div class="contianer">
				<div class="col-lg-10">
					<div class="input-group">
						<input type="text" class="form-control"
							placeholder="Search for..."> <span
							class="input-group-btn">
							<button class="btn btn-secondary" type="button">Go!</button>
						</span>
					</div>
					<br>
					<div class="card" style="height:500px">
						<div class="card-header">Word Cloud</div>
						<div class="card-block" id="main">
							
						</div>
					</div>
				</div>
				<br>
			</div>
		</form>
	</div>

</body>
<script type="text/javascript" src="js/jquery/jquery-3.1.1.js"></script>
<script type="text/javascript" src="js/tether/tether.min.js"></script>
<script type="text/javascript" src="js/d3/d3.js"></script>
<script type="text/javascript" src="js/bootstrap/bootstrap.min.js"></script>
<script type="text/javascript" src="js/echarts/echarts-all.js"></script>

<script type="text/javascript" src="js/demo/demo.js"></script>