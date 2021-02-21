<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="com.azart.entities.User"%>
<%@page import="java.util.List"%>
<%@page import="com.azart.mongo.UserUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<title>Book Store</title>
		<meta charset="UTF-8">

		<!-- Scripts -->
		<script type="text/javascript" src="librarys/jQuery/jQuery3.5.1.min.js"></script>
		<script type="text/javascript" src="librarys/DataTables-1.10.23/js/jquery.dataTables.min.js"></script>
		<script type="text/javascript" src="librarys/bootstrap_4/js/bootstrap.min.js"></script>
		<script type="text/javascript" src="js/usersList.js"></script>
		<!-- Scripts -->

		<!-- Styles -->
		<link rel="stylesheet" type="text/css" href="librarys/DataTables-1.10.23/css/jquery.dataTables.min.css"/>
		<link rel="stylesheet" type="text/css" href="librarys/bootstrap_4/css/bootstrap.min.css"/>
		<link rel="stylesheet" type="text/css" href="css/usersList.css">
		<!-- Styles -->
		
		<% 			
			String userName = (String) session.getAttribute("userName");
			String jwt = (String) session.getAttribute("jwt");
		%>
		
	</head>

	<body>
		<div class="p-2">
			<form id="form" name="form" action="" method="post">		
				<input type="hidden" id="jwt" name="jwt" value="<%=jwt %>"/>
				<input type="hidden" id="user_id" name="user_id" value=""/>
				<input type="hidden" id="action_user" name="action_user" value=""/>
				
				<div class="d-flex justify-content-between">
					<div class="d-flex justify-content-start">
						<button class="btn btn-lg btn-outline-primary main" type="button" onclick="window.location='mainAdminPage.jsp';">Main</button>
						&nbsp;
						&nbsp;
 						<button class="btn btn-lg btn-outline-primary orders" type="button" onclick="window.location='allordersList.jsp';">Orders</button>
					</div>
					<div class="d-flex justify-content-end">
						<div class="p-2 align-self-center">
							<h4><span class="label label-primary"><%=userName %></span></h4>
						</div>
						<button class="btn btn-lg btn-outline-primary logout" type="submit" formaction="Logout">Logout</button>
					</div>
				</div>
				<br>
    			<br>
    			<div class="container">
  					<div class="row justify-content-center">
      					<table id="main_table" class="display table main_table">
    						<thead>
        						<tr>
            						<th>User ID</th>
                					<th>Email</th>
                					<th>Name</th>
                					<th>Role</th>
                					<th></th>
            					</tr>
        					</thead>
        					<tbody>		
        						<% 
        							UserUtils bUtils = new UserUtils();
        							List<User> users = bUtils.getAllUsers();
 											
        							if(users == null || users.isEmpty()){
        						%>        										 
        								<tr>
                							<td><%=" "%></td>
                							<td><%=" "%></td>
                							<td><%=" "%></td>
                							<td><%=" "%></td>
                							<td>
                								<button class="btn btn-lg btn-outline-primary delete" type="submit">Delete</button>
                							</td>
                						</tr>        										
        						<%
        							} else {
        								
        								for(User u: users){
        						%>
        									<tr>
                								<td><%=u.get_id() %></td>
                								<td><%=u.getEmail() %></td>
                								<td><%=u.getName() %></td>
                								<td><%=u.getRole() %></td>
                								<td>
                									<button class="btn btn-lg btn-outline-primary delete" type="submit" formaction="AdminUsers" onclick="deleteUser('<%=u.get_id() %>')">Delete</button>
                								</td>
                							</tr>
                				<%} 	}%>
            				</tbody>
        					<tfoot>
            					<tr>
                					<th>User ID</th>
                					<th>Email</th>
                					<th>Name</th>
                					<th>Role</th>
                					<th></th>
            					</tr>
        					</tfoot>
   			 			</table>
  					</div>
    			</div>			
			</form>
		</div>
	</body>
</html>