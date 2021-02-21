package com.azart.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.azart.entities.Order;
import com.azart.entities.User;
import com.azart.jwt.JWTUtils;
import com.azart.mongo.OrderUtils;
import com.azart.mongo.UserUtils;

import io.jsonwebtoken.Claims;

/**
 * Servlet implementation class BuyBook
 */
@WebServlet("/BuyBook")
public class BuyBook extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BuyBook() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

//		String action = (String) request.getParameter("action");
		String jwt = (String) request.getParameter("jwt");
		String book_id = (String) request.getParameter("book_id");
		String book_title = (String) request.getParameter("book_title");
		UserUtils uUtil = new UserUtils();
		OrderUtils oUtil = new OrderUtils();
		PrintWriter out = response.getWriter();

		SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date(System.currentTimeMillis());
		
		Claims claims = JWTUtils.decodeJWT(jwt);

		String id = claims.getId();
		String subject = claims.getSubject();

		if (uUtil.isExist("_id", id)) {
			User user = uUtil.getUser("_id", id);

			if (user.getPassword().equals(subject)) {
				
				Order order = new Order(UUID.randomUUID().toString(), user.get_id(), user.getName(), book_id, book_title, formatter.format(date));

				oUtil.create(order);

				out.println("<script type=\"text/javascript\">");
				out.println("location='mainUserPage.jsp';");
				out.println("</script>");
			}
		}
	}
}
