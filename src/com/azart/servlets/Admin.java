package com.azart.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.azart.constant.BookStoreConstants;
import com.azart.entities.Book;
import com.azart.entities.User;
import com.azart.jwt.JWTUtils;
import com.azart.mongo.BookUtils;
import com.azart.mongo.UserUtils;

import io.jsonwebtoken.Claims;

/**
 * Servlet implementation class Admin
 */
@WebServlet("/Admin")
public class Admin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Admin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String action = (String) request.getParameter("action");
		String jwt = (String) request.getParameter("jwt");
		UserUtils uUtil = new UserUtils();
		BookUtils bUtil = new BookUtils();
		PrintWriter out = response.getWriter();

		Claims claims = JWTUtils.decodeJWT(jwt);
		
		String id = claims.getId();
		String subject = claims.getSubject();
		
		if(uUtil.isExist("_id", id)) {
			User user = uUtil.getUser("_id", id);
			
			if(user.getPassword().equals(subject)) {
				if(BookStoreConstants.ADMIN_ROLE.equals(user.getRole())) {
					String book_id = (String) request.getParameter("book_id");
					
					if("delete_book".equals(action)) {
						bUtil.delete("_id", book_id);
					}
					
					if("change_price".equals(action)) {
						String new_price = (String) request.getParameter("new_price");
						bUtil.update("_id", book_id, "price", new_price);
					}
					
					if("add_book".equals(action)) {
						System.out.println("privet change_price");
						
						String title = (String) request.getParameter("title");
						String authors = (String) request.getParameter("authors");
						String publisher = (String) request.getParameter("publisher");
						String year = (String) request.getParameter("year");
						String price = (String) request.getParameter("price");
							
						List<String> authorsList = Arrays.asList(authors);
						Book book = new Book(UUID.randomUUID().toString(), title, authorsList, publisher, year, Double.parseDouble(price) );
						
						bUtil.create(book);
					}
					
					out.println("<script type=\"text/javascript\">");
					out.println("location='mainAdminPage.jsp';");
					out.println("</script>");
					
				} else {
					out.println("<script type=\"text/javascript\">");
					out.println("alert('You have not permishen for this operation! Please login again.');");
					out.println("location='loginPage.jsp';");
					out.println("</script>");
				}
			}
		}	
	}
}
