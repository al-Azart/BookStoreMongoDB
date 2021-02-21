package com.azart.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.azart.constant.BookStoreConstants;
import com.azart.jwt.JWTUtils;
import com.azart.mongo.UserUtils;
import com.azart.entities.User;


/**
 * Servlet implementation class SignUp
 */
@WebServlet("/SignUp")
public class SignUp extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignUp() {
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
		String email = request.getParameter("email");
		String name = request.getParameter("username");
		String password = request.getParameter("password");
		UserUtils uUtil = new UserUtils();
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();

		
		if(uUtil.isExist("email", email)) {
			
			out.println("<script type=\"text/javascript\">");
			out.println("alert('User is exist. Please login.');");
			out.println("location='loginPage.jsp';");
			out.println("</script>");
			
		} else {
			User user = new User(UUID.randomUUID().toString(), email, name, password, BookStoreConstants.USER_ROLE);
			
			uUtil.create(user);
			
			String jwt = JWTUtils.createJWT(user.get_id(), BookStoreConstants.PROJECT, user.getPassword(), BookStoreConstants.TOKEN_LIVE_TIME);
			
			session.setAttribute("jwt", jwt);
			session.setAttribute("user_id", user.get_id());
			session.setAttribute("userName", user.getName());
					
			request.getRequestDispatcher("mainUserPage.jsp").forward(request, response);				
		}	
	}
}
