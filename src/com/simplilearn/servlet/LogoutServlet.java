package com.simplilearn.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LogoutServlet
 */
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogoutServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter writer = response.getWriter();
		HttpSession session = request.getSession(true);
		if(session!=null) {
			session.removeAttribute("username");
			session.invalidate();
			request.getRequestDispatcher("login.html").include(request, response);
			writer.println("<div class=\"row\">\r\n" + 
					"    <div class=\"col-lg-3 col-md-2\"></div>\r\n" + 
					"    <div class=\"alert alert-success\" role=\"alert\">\r\n" + 
					"		Logout Success\r\n" + 
					"	</div>\r\n" + 
					"</div>");
		}
		else {
			writer.println("Session doesn't exists");
		}
	}
}
