<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="com.azart.entities.User"%>
<%@page import="com.azart.entities.Order"%>
<%@page import="java.util.List"%>
<%@page import="com.azart.mongo.OrderUtils"%>
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
		<script type="text/javascript" src="js/mainAdminPage.js"></script>
		<!-- Scripts -->

		<!-- Styles -->
		<link rel="stylesheet" type="text/css" href="librarys/DataTables-1.10.23/css/jquery.dataTables.min.css"/>
		<link rel="stylesheet" type="text/css" href="librarys/bootstrap_4/css/bootstrap.min.css"/>
		<link rel="stylesheet" type="text/css" href="css/mainAdminPage.css">
		<!-- Styles -->

		<% 		
			String userName = (String) session.getAttribute("userName");
			String userId = (String) session.getAttribute("user_id"); 
		%>
	</head>

	<body>
		<div class="p-2">
			<form id="form" name="form" action="" method="post">		
				
				<div class="d-flex justify-content-between">
					<div class="d-flex justify-content-start">
						<button class="btn btn-lg btn-outline-primary main" type="button" onclick="window.location='mainUserPage.jsp';">Main</button>
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
      					<table id="main_table" class="display table main_table" style="width:100%">
    						<thead>
        						<tr>
            						<th>Order ID</th>
                					<th>Book title</th>
                					<th>Date</th>
            					</tr>
        					</thead>
        					<tbody>		
        						<% 
        							OrderUtils oUtils = new OrderUtils();
        							List<Order> orders = oUtils.getOrdersByCriteria("userId", userId);
 											
        							if(orders == null || orders.isEmpty()){
        						%>        										 
        								<tr>
                							<td><%=" "%></td>
                							<td><%=" "%></td>
                							<td><%=" "%></td>
                						</tr>        										
        						<%
        							} else {
        								
        								for(Order o: orders){
        						%>
        									<tr>
                								<td><%=o.get_id() %></td>
                								<td><%=o.getBookTitle() %></td>
                								<td><%=o.getDate() %></td>
                							</tr>
                				<%} 	}%>
            				</tbody>
        					<tfoot>
            					<tr>
                					<th>Order ID</th>
                					<th>Book title</th>
                					<th>Date</th>
            					</tr>
        					</tfoot>
   			 			</table>
  					</div>
    			</div>			
			</form>
		</div>
	</body>
</html>