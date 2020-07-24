package com.simplilearn.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.simplilearn.dao.UserDAO;
import com.simplilearn.model.User;

public class DefaultServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDAO userDAO;
       
    public DefaultServlet() {
        super();
        userDAO = new UserDAO();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getServletPath();
		try{
			switch(action) {
			case "/home":
				home(request,response);
				break;
			case "/about":
				about(request,response);
				break;
			default :
				home(request,response);
				break;
			}
		}
		catch(Exception e) {
			System.out.println("Exception caught: " + e.getMessage());
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getServletPath();
		try{
			switch(action) {
			case "/login":
				login(request,response);
				break;
			case "/user":
				user(request,response);
				break;
			case "/customer":
				customer(request,response);
				break;
			default :
				home(request,response);
				break;
			}
		}
		catch(Exception e) {
			System.out.println("Exception caught: " + e.getMessage());
		}
	}


	private void login(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException, ServletException {
		PrintWriter writer = response.getWriter();
		User userObj = null;
		userObj = findUser(request,response);
		if(userObj.getUserId() > 0) {
			HttpSession session = request.getSession();
			session.setAttribute("username", request.getParameter("username"));
			request.setAttribute("loggedInUser", userObj);
			request.getRequestDispatcher("home").forward(request, response);
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

	private void customer(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
	}

	private void user(HttpServletRequest request, HttpServletResponse response) {
	}

	private void about(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}

	private void home(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// It will look for existing session variables by passing true as argument
		HttpSession session = request.getSession(true);
		
		if(session!=null) {
			RequestDispatcher rd = request.getRequestDispatcher("home.jsp");
			rd.include(request, response);
		}
		else {
			request.getRequestDispatcher("logout").include(request, response);
		}
	}

	private User findUser(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		String name = request.getParameter("username");
		String email = request.getParameter("userpass");
		User newUser = new User(name, email);
		return this.userDAO.findUser(newUser);
	}

}
