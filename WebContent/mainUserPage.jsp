<%@page import="com.azart.entities.Book"%>
<%@page import="java.util.List"%>
<%@page import="com.azart.mongo.BookUtils"%>
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
		<script type="text/javascript" src="js/mainUserPage.js"></script>
		<!-- Scripts -->

		<!-- Styles -->
		<link rel="stylesheet" type="text/css" href="librarys/DataTables-1.10.23/css/jquery.dataTables.min.css"/>
		<link rel="stylesheet" type="text/css" href="librarys/bootstrap_4/css/bootstrap.min.css"/>
		<link rel="stylesheet" type="text/css" href="css/mainUserPage.css">
		<!-- Styles -->

		<% 		
			String userName = (String) session.getAttribute("userName");
			String jwt = (String) session.getAttribute("jwt");
		%>
	</head>

	<body>
		<div class="p-2">
			<form  id="form" name="form" action="" method="post">		
				<input type="hidden" id="jwt" name="jwt" value="<%=jwt %>"/>
				<input type="hidden" id="book_id" name="book_id" value=""/>
				<input type="hidden" id="book_title" name="book_title" value=""/>
				<input type="hidden" id="action" name="action" value=""/>
			
				<div class="d-flex justify-content-between">
					<div class="d-flex justify-content-start">
						<button class="btn btn-lg btn-outline-primary my_orders" type="button" onclick="window.location='userOrderList.jsp';">My orders</button>
					</div>
					<div class="d-flex justify-content-end">
						<div class="p-2 align-self-center">
							<h4><span class="label label-primary"><%=userName %></span></h4>
						</div>
						<button class="btn btn-lg btn-outline-primary logout" type="submit" formaction="Logout">Logout</button>
					</div>
				</div>
				<br>
    			<div class="container">
  					<div class="row justify-content-center">
      					<table id="main_table" class="display table main_table">
    						<thead>
        						<tr>
                					<th>Title</th>
                					<th>Authors</th>
                					<th>Publisher</th>
                					<th>Year</th>
                					<th>Price</th>
                					<th></th>
            					</tr>
        					</thead>
        					<tbody>		
        						<% 
        							BookUtils bUtils = new BookUtils();
        							List<Book> books = bUtils.getAllBooks();
 											
        							if(books == null || books.isEmpty()){
        						%>        										 
        								<tr>
                							<td><%=" "%></td>
                							<td><%=" "%></td>
                							<td><%=" "%></td>
                							<td><%=" "%></td>
                							<td><%=" "%></td>
                							<td>
                								<button class="btn btn-lg btn-outline-primary buy" type="submit">Buy</button>
                							</td>
                						</tr>        										
        						<%
        							} else {
        								
        								for(Book b: books){
        						%>
        									<tr>
                								<td><%=b.getTitle() %></td>
                								<td><%=b.getAuthors().toString().replace("[", "").replace("]", "") %></td>
                								<td><%=b.getPublisher() %></td>
                								<td><%=b.getYearOfPublication() %></td>
                								<td><%=b.getPrice() %></td>
                								<td>
                									<button class="btn btn-lg btn-outline-primary buy" type="submit" formaction="BuyBook" onclick="buyBook('<%=b.get_id() %>','<%=b.getTitle() %>')">Buy</button>
                								</td>
                							</tr>
                				<%} 	}%>
            				</tbody>
        					<tfoot>
            					<tr>
                					<th>Title</th>
                					<th>Authors</th>
                					<th>Publisher</th>
                					<th>Year</th>
                					<th>Price</th>
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