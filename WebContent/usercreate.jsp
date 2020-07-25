<jsp:include page="header.jsp"></jsp:include>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page errorPage="/error"%>
<%
	String title = request.getAttribute("title").toString();
	String uri = "/" + title.toLowerCase() + ".create";
%>
	<!--  content begin -->
	<div class="container">
		<br />
		<h2>Add new <%=title%></h2>
		<br />
		<form action="<%=request.getContextPath()%><%=uri%>" method="post">
			<div class="form-group">
				<label for="name"><%=title %> Name</label> <input type="text"
					class="form-control" name="name" />
			</div>
			<div class="form-group">
				<label for="email"><%=title %> Email</label> <input type="text"
					class="form-control" name="email">
			</div>
			<button type="submit" class="btn btn-primary" name="submit">Submit</button>
		</form>
	</div>
	<!--  content end -->

<jsp:include page="footer.jsp"></jsp:include>