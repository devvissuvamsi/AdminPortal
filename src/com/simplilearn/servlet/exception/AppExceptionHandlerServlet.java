package com.simplilearn.servlet.exception;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AppExceptionHandlerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processError(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processError(request, response);
	}

	private void processError(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		// Analyze the servlet exception
		Throwable throwable = (Throwable) request.getAttribute("javax.servlet.error.exception");
		Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
		String servletName = (String) request.getAttribute("javax.servlet.error.servlet_name");
		String exceptionName="",exceptionMessage="";
		
		if (servletName == null) {
			servletName = "Unknown";
		}
		String requestUri = (String) request.getAttribute("javax.servlet.error.request_uri");
		if (requestUri == null) {
			requestUri = "Unknown";
		}

		// Set response content type
		response.setContentType("text/html");
		RequestDispatcher rd = request.getRequestDispatcher("/error.jsp");
		request.setAttribute("statusCode", statusCode);
		request.setAttribute("requestUri", requestUri);
		request.setAttribute("servletName", servletName);

		if (statusCode == 500) {
			exceptionName = throwable.getClass().getName();
			exceptionMessage = throwable.getMessage();
		} 
		request.setAttribute("exceptionName", exceptionName);
		request.setAttribute("exceptionMessage", exceptionMessage);
		
		rd.forward(request, response);
	}
}