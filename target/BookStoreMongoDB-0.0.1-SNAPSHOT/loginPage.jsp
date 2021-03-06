<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

	<head>
		<title>Book Store</title>
		<meta charset="UTF-8">

		<!-- Scripts -->
		<script type="text/javascript" src="librarys/jQuery/jQuery3.5.1.min.js"></script>
		<script type="text/javascript" src="librarys/bootstrap_4/js/bootstrap.min.js"></script>
		<script type="text/javascript" src="js/loginPage.js"></script>
		<!-- Scripts -->

		<!-- Styles -->
		<link rel="stylesheet" type="text/css" href="librarys/DataTables-1.10.23/css/jquery.dataTables.min.css"/>
		<link rel="stylesheet" type="text/css" href="librarys/bootstrap_4/css/bootstrap.min.css"/>
		<link rel="stylesheet" type="text/css" href="css/loginpage.css">
		<!-- Styles -->
	</head>
	<body>
		<div class="d-flex justify-content-center align-middle vertical-center container_login_page">
			<form id="form" action="Login" method="post">
				<h3 class="vertical_center_label">Login or <a href="signUpPage.jsp"> Sign up</a></h3>
				<div>
					<input type="text" class="form-control email" id="email" name="email" placeholder="example@example.com" onkeyup="validation()">
					<span id="text"></span>
				</div>		
				<div class="password-wrapper">		
    				<input type="password" value="" placeholder="Enter Password" name="password" id="password" class="form-control password">
    				<button class="unmask" type="button" onclick="showHidePassword()">Show</button>
  				</div>
                <br>                    
				<button id="login_btn" class="btn btn-lg btn-outline-primary btn-block" type="submit" disabled>Login</button>		
			</form>
		</div>
	</body>
</html>

