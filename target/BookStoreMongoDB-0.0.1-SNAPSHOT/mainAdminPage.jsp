<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="com.azart.entities.User"%>
<%@page import="java.util.ArrayList"%>
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
		<script type="text/javascript" src="js/mainAdminPage.js"></script>
		<!-- Scripts -->

		<!-- Styles -->
		<link rel="stylesheet" type="text/css" href="librarys/DataTables-1.10.23/css/jquery.dataTables.min.css"/>
		<link rel="stylesheet" type="text/css" href="librarys/bootstrap_4/css/bootstrap.min.css"/>
		<link rel="stylesheet" type="text/css" href="css/mainAdminPage.css">
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
				<input type="hidden" id="action" name="action" value=""/>
				<input type="hidden" id="book_id" name="book_id" value=""/>
				
				<div class="d-flex justify-content-between">
					<div class="d-flex justify-content-start">
						<button class="btn btn-lg btn-outline-primary users" type="button" onclick="window.location='usersList.jsp';">Users</button>
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
    			<div class="container">
  					<div class="row justify-content-start">
    					<div class="col-4">
      						<table id="add_book" class="display table">
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
            						<tr>
                						<td><input type="text" id="title" name="title"></td>
                						<td><input type="text" id="authors" name="authors"></td>
                						<td><input type="text" id="publisher" name="publisher"></td>
                						<td><input type="text" id="year" name="year"></td>
                						<td><input type="text" id="price" name="price"></td>
                						<td>
                							<button class="btn btn-lg btn-outline-primary add_book" type="submit" formaction="Admin" onclick='document.getElementById("action").value="add_book"'>Add book</button>
                						</td>
            						</tr>
            					</tbody>
   			 				</table>
    					</div>
  					</div>
    			</div>
    			<br>
    			<div class="container">
  					<div class="row">
      					<table id="main_table" class="display table main_table">
    						<thead>
        						<tr>
            						<th>Book ID</th>
                					<th>Title</th>
                					<th>Authors</th>
                					<th>Publisher</th>
                					<th>Year</th>
                					<th>Price</th>
                					<th></th>
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
                							<td><%=" "%></td>
                							<td>
                								<button class="btn btn-lg btn-outline-primary change_price" type="button">Change Price</button>
                							</td>
                							<td>
                								<button class="btn btn-lg btn-outline-primary delete" type="submit">Delete</button>
                							</td>
                						</tr>        										
        						<%
        							} else {
        								
        								for(Book b: books){
        						%>
        									<tr>
                								<td><%=b.get_id() %></td>
                								<td><%=b.getTitle() %></td>
                								<td><%=b.getAuthors().toString().replace("[", "").replace("]", "") %></td>
                								<td><%=b.getPublisher() %></td>
                								<td><%=b.getYearOfPublication() %></td>
                								<td><%=b.getPrice() %></td>
                								<td>
                									<button class="btn btn-lg btn-outline-primary change_price" type="button" data-toggle="modal" data-target=".bd-example-modal-sm" onclick='document.getElementById("book_id").value="<%=b.get_id() %>"'>Change Price</button>
                								</td>
                								<td>
                									<button class="btn btn-lg btn-outline-primary delete" type="submit" formaction="Admin" onclick="deleteBook('<%=b.get_id() %>')">Delete</button>
                								</td>
                							</tr>
                				<%} 	}%>
            				</tbody>
        					<tfoot>
            					<tr>
                					<th>Book ID</th>
                					<th>Title</th>
                					<th>Authors</th>
                					<th>Publisher</th>
                					<th>Year</th>
                					<th>Price</th>
                					<th></th>
                					<th></th>
            					</tr>
        					</tfoot>
   			 			</table>
  					</div>
    			</div>
    			<!-- Modal -->
  				<div class="modal fade bd-example-modal-sm" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true">
  					<div class="modal-dialog  modal-dialog-centered modal-sm">
    					 <div class="modal-content">
      						<div class="modal-header align-self-center">
        						<h3 class="modal-title" id="exampleModalLongTitle">Change Price</h3>
      						</div>
      						<div class="modal-body align-self-center">
        						<h5><span class="label label-primary">New price:</span></h5>
        						<input type="text" id="new_price" name="new_price">
      						</div>
      						<div class="modal-footer align-self-center">
        						<button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
        						<button type="submit" class="btn btn-primary" formaction="Admin" onclick='document.getElementById("action").value="change_price"'>Save changes</button>
      						</div>
    					</div>
  					</div>
				</div>	
			</form>
		</div>
	</body>
</html>