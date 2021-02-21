package com.azart.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.azart.constant.BookStoreConstants;
import com.azart.jwt.JWTUtils;
import com.azart.mongo.UserUtils;
import com.azart.entities.User;

import io.jsonwebtoken.Claims;

/**
 * Servlet implementation class AdminUsers
 */
@WebServlet("/AdminUsers")
public class AdminUsers extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminUsers() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String action_user = (String) request.getParameter("action_user");
		String jwt = (String) request.getParameter("jwt");
		UserUtils uUtil = new UserUtils();

		PrintWriter out = response.getWriter();

		Claims claims = JWTUtils.decodeJWT(jwt);
		
		String id = claims.getId();
		String subject = claims.getSubject();
		
		if(uUtil.isExist("_id", id)) {
			User user = uUtil.getUser("_id", id);
			
			if(user.getPassword().equals(subject)) {
				if(BookStoreConstants.ADMIN_ROLE.equals(user.getRole())) {
				
					if("delete_user".equals(action_user)) {
						String user_id = (String) request.getParameter("user_id");					
						uUtil.delete("_id", user_id);
					}
					
					out.println("<script type=\"text/javascript\">");
					out.println("location='usersList.jsp';");
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
