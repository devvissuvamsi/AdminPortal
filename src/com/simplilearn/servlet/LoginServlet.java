package com.simplilearn.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.simplilearn.dao.UserDAO;
import com.simplilearn.model.User;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDAO userDAO;
       
    public LoginServlet() {
        super();
        userDAO = new UserDAO();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = null;
		PrintWriter writer = response.getWriter();
		String username = request.getParameter("username");
		String userpass = request.getParameter("userpass");
		
		try {
			findUser(request,response);
		} catch (SQLException e) {
			System.out.println("Exception caught: " + e.getMessage());
			e.printStackTrace();
		}
		
		if(username.equals("admin") && userpass.equals("admin")) {
			HttpSession session = request.getSession();
			session.setAttribute("username", username);
			request.getRequestDispatcher("home.jsp").forward(request, response);
		}
		else {
			request.getRequestDispatcher("login.html").include(request, response);
			writer.println
			("<div class=\"row\">\r\n" + 
					"    <div class=\"col-lg-3 col-md-2\"></div>\r\n" + 
					"    <div class=\"alert alert-warning\" role=\"alert\">\r\n" + 
					"  Invalid Login!\r\n" + 
					"</div></div>");
		}
	}

	private void findUser(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		String name = request.getParameter("username");
		String email = request.getParameter("userpass");
		User newUser = new User(name, email);
		this.userDAO.findUser(newUser);
	}

}
