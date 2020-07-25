<jsp:include page="header.jsp"></jsp:include>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page errorPage = "/error" %>
<!--  content begin -->
<%
	String createButtonLabel = request.getAttribute("createButtonLabel").toString();
	String gridTitle = request.getAttribute("gridTitle").toString();
%>
<div class="container">
	<br />
	<br />
	<table class="table">
		<tr>
			<th><a href="<%=request.getContextPath()%>/user.create"	class="btn btn-success">${createButtonLabel}</a></th>
		</tr>
	</table>
		
	<table class="table table-striped table-hover">
		<caption>${gridTitle}</caption>
		<thead class="thead-dark">
			<tr>
				<th>Id</th>
				<th>Name</th>
				<th>Email</th>
				<th>Actions</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="user" items="${listUser}">
				<tr>
					<td><c:out value="${user.userId}"></c:out></td>
					<td><c:out value="${user.userName}"></c:out></td>
					<td><c:out value="${user.userEmail}"></c:out></td>
					<td>
						<a href="user.edit?id=<c:out value='${user.userId}'/>" class="btn btn-secondary">Edit</a>
						<a href="user.delete?id=<c:out value='${user.userId}'/>" class="btn btn-danger">Delete</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

</div>
<!--  content end -->
<jsp:include page="footer.jsp"></jsp:include>