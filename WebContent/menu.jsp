<%
//allow access only if session exists
String user = null;
if(session.getAttribute("username") == null){
	response.sendRedirect("login.html");
}else user = (String) session.getAttribute("username");


String userName = null;
String sessionID = null;

Cookie[] cookies = request.getCookies();
if(cookies !=null){
	for(Cookie cookie : cookies){
		if(cookie.getName().equals("user")) userName = cookie.getValue();
		if(cookie.getName().equals("JSESSIONID")) sessionID = cookie.getValue();
	}

}else{
	sessionID = session.getId();
}

String activeClassHome="active",activeClassUser="",activeClassCustomer="",activeClassAbout="";

try{
	switch(request.getAttribute("title").toString().toLowerCase()){
	case "user":
		activeClassUser="active";
		break;
	case "customer":
		activeClassCustomer="active";
		break;
	default :
		activeClassHome="active";
		break;
	}	
}
catch(Exception e){
	if(request.getServletPath().toString().toLowerCase().contains("about")){
		activeClassAbout="active";
	}
}


%>
<div class="container">
	<nav class="navbar navbar-expand-lg navbar-light bg-light">
		<a class="navbar-brand" href="<%=request.getContextPath()%>/home"> 
		<%
// 			out.print("Welcome " + user + ", SessionID: " + sessionID);
 			out.print("Welcome " + user);
 		%></a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>

		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav mr-auto">
				<li class="nav-item <%=activeClassHome%>"><a class="nav-link" href="<%=request.getContextPath()%>/home">Home
						<span class="sr-only">(current)</span>
				</a></li>
				<li class="nav-item <%=activeClassUser%>"><a class="nav-link" href="<%=request.getContextPath()%>/user.index">Manage
						User</a></li>
				<li class="nav-item <%=activeClassCustomer%>"><a class="nav-link" href="<%=request.getContextPath()%>/customer.index">Manage
						Customer</a></li>
				<li class="nav-item <%=activeClassAbout%>"><a class="nav-link" href="<%=request.getContextPath()%>/about">About</a></li>
			</ul>
			<form class="form-inline my-2 my-lg-0" action="logout" name="logout" method="post">
				<button class="btn btn-outline-success my-2 my-sm-0" type="submit"
					value="logout">Logout</button>
			</form>
		</div>
	</nav>
</div>