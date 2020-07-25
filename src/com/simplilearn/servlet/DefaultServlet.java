package com.simplilearn.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

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
			case "/user.index":
			case "/customer.index":
				userIndex(request,response);
				break;
			case "/user.create":
			case "/customer.create":
				userCreate(request,response);
				break;
			case "/user.edit":
			case "/customer.edit":
				userEdit(request,response);
				break;						
			case "/user.delete":
			case "/customer.delete":
				userDelete(request,response);
				break;					
			case "/user.view":
				userView(request,response);
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
	
	private void userView(HttpServletRequest request, HttpServletResponse response) {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getServletPath();
		try{
			switch(action) {
			case "/login":
				login(request,response);
				break;
			case "/logout":
				logout(request,response);
				break;
			case "/user.create":
			case "/customer.create":
				userCreate(request,response);
				break;
			case "/user.edit":
			case "/customer.edit":
				userEdit(request,response);
				break;					
			case "/user.delete":
			case "/customer.delete":
				userDelete(request,response);
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

	private void userDelete(HttpServletRequest request, HttpServletResponse response) throws NumberFormatException, ServletException, IOException, SQLException {
		if(checkSession(request,response)) {

			boolean isCustomer = false;
			String roleKey = "user";
			if(request.getServletPath().contains("customer")) {
				isCustomer = true;
				roleKey="customer";
			}
			
			int id = Integer.parseInt(request.getParameter("id"));
			if(userDAO.deleteUser(id))
				if(isCustomer) {
					response.sendRedirect("customer.index");
				}else {
					response.sendRedirect("user.index");
				}
			else
				System.out.println("There is some error in deleting user");
		}
	}

	private void userEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		boolean isCustomer = false;
		String roleKey = "user";
		request.setAttribute("title", "User");
		
		if(request.getServletPath().contains("customer")) {
			isCustomer = true;
			roleKey="customer";
			request.setAttribute("title", "Customer");
		}

		if(checkSession(request,response)) {
			if(request.getParameter("submit")!= null) {
				int id = Integer.parseInt(request.getParameter("id"));
				String name = request.getParameter("name");
				String email = request.getParameter("email");
				User modifiedUser = new User(id,name,email);
				if(userDAO.updateUser(modifiedUser)) {
					if(isCustomer) {
						response.sendRedirect("customer.index");
					}else {
						response.sendRedirect("user.index");
					}				
				}
				else {
					System.out.println("Some Errors...");
				}
			}
			else {
				int id = Integer.parseInt(request.getParameter("id"));
				User existingUser = userDAO.findUserById(id);
				RequestDispatcher rd = request.getRequestDispatcher("useredit.jsp");
				request.setAttribute("user", existingUser);
				rd.forward(request, response);
			}
		}
	}

	private void userCreate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		if(checkSession(request,response)) {
			boolean isCustomer = false;
			String roleKey = "user";
			request.setAttribute("title", "User");
			
			if(request.getServletPath().contains("customer")) {
				isCustomer = true;
				roleKey="customer";
				request.setAttribute("title", "Customer");
			}
			
			if(request.getParameter("submit")!= null) {
				String name = request.getParameter("name");
				String email = request.getParameter("email");
				User newUser = new User(name, email,"Password1");
				userDAO.insertUser(newUser,roleKey);
				response.sendRedirect(roleKey+".index");				
			}
			else {
				RequestDispatcher rd = request.getRequestDispatcher("usercreate.jsp");
				rd.forward(request, response);
			}
		}
	}

	private void userIndex(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		if(checkSession(request,response)) {
			boolean isCustomer = false;
			String roleKey = "user";
			
			if(request.getServletPath().contains("customer")) {
				isCustomer = true;
				roleKey="customer";
			}
			
			List<User> userList = userDAO.selectAllUser(roleKey);
			request.setAttribute("listUser", userList);
			request.setAttribute("createButtonLabel", "Create User");
			request.setAttribute("gridTitle", "User List");
			request.setAttribute("title", "user");
			if(isCustomer) {
				request.setAttribute("createButtonLabel", "Create Customer");
				request.setAttribute("gridTitle", "Customer List");
				request.setAttribute("title", "customer");
			}
			RequestDispatcher rd = request.getRequestDispatcher("userindex.jsp");
			rd.forward(request, response);
		}
	}
	
	private void logout(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		PrintWriter writer = response.getWriter();
		HttpSession session = request.getSession(true);
		String logoutMessage = "Logout Success";
		
		if(session!=null) {
//			remove session 
			session.removeAttribute("username");
			session.invalidate();
			
	        // if not contains logout then it must be due to session expire
	        if (!request.getParameterMap().containsKey("logout")) {
	        	logoutMessage = "Session Expired ! Please login again";
	        }			
			request.getRequestDispatcher("login.html").include(request, response);
			writer.println("<div class=\"row\">\r\n" + 
					"    <div class=\"col-lg-3 col-md-2\"></div>\r\n" + 
					"    <div class=\"alert alert-success\" role=\"alert\">\r\n" + 
					"		" + logoutMessage + "\r\n" + 
					"	</div>\r\n" + 
					"</div>");
		}
		else {
			writer.println("Session doesn't exists");
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

	private void about(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(checkSession(request,response)) {
			RequestDispatcher rd = request.getRequestDispatcher("about.jsp");
			rd.include(request, response);
		}
	}

	private void home(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(checkSession(request,response)) {
			RequestDispatcher rd = request.getRequestDispatcher("home.jsp");
			rd.include(request, response);
		}
	}

	private User findUser(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		String name = request.getParameter("username");
		String email = request.getParameter("userpass");
		User newUser = new User(name, email);
		return this.userDAO.findUser(newUser);
	}
	
	private boolean checkSession(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean sessionValid = false;
		HttpSession session = request.getSession(true);
		
		if(session!=null) {
			sessionValid = true;
		}
		else {
			request.getRequestDispatcher("logout").include(request, response);
		}
		return sessionValid;		
	}

}
