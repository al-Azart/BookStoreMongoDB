package com.azart.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.azart.mongo.UserUtils;
import com.azart.constant.BookStoreConstants;
import com.azart.entities.User;
import com.azart.jwt.JWTUtils;


/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
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
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		UserUtils uUtil = new UserUtils();
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		
		if(!uUtil.isExist("email", email)) {
			
			out.println("<script type=\"text/javascript\">");
			out.println("alert('User is not exist. Please sign up.');");
			out.println("location='loginPage.jsp';");
			out.println("</script>");
			
		} else {
			User user = uUtil.getUser("email", email);
			
			if(!password.equals(user.getPassword())) {
				
				out.println("<script type=\"text/javascript\">");
				out.println("alert('Password is not correct.');");
				out.println("location='loginPage.jsp';");
				out.println("</script>");
				
			} else { 
				
				if (BookStoreConstants.ADMIN_ROLE.equals(user.getRole())) {
					String jwt = JWTUtils.createJWT(user.get_id(), BookStoreConstants.PROJECT, user.getPassword(), BookStoreConstants.TOKEN_LIVE_TIME);
					session.setAttribute("jwt", jwt);
					session.setAttribute("userName", user.getName());				
					request.getRequestDispatcher("mainAdminPage.jsp").forward(request, response);
				} 
			
				if (BookStoreConstants.USER_ROLE.equals(user.getRole())) {
					String jwt = JWTUtils.createJWT(user.get_id(), BookStoreConstants.PROJECT, user.getPassword(), BookStoreConstants.TOKEN_LIVE_TIME);
					session.setAttribute("jwt", jwt);
					session.setAttribute("userName", user.getName());	
					session.setAttribute("user_id", user.get_id());
					request.getRequestDispatcher("mainUserPage.jsp").forward(request, response);
				} 
			}
		}	
	}
}
