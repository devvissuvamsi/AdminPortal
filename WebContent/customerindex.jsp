<jsp:include page="header.jsp"></jsp:include>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page errorPage = "/error" %>
<!--  content begin -->

<div class="container">
	<br />
	<br /> <a href="<%=request.getContextPath()%>/customer.create"
		class="btn btn-success">Create New User</a>
	<h3>User List</h3>
	<table class="table table-striped table-hover">
		<thead>
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
						<a href="customer.edit?id=<c:out value='${user.userId}'/>" class="btn btn-secondary">Edit</a>
						<a href="customer.delete?id=<c:out value='${user.userId}'/>" class="btn btn-danger">Delete</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

</div>
<!--  content end -->
<jsp:include page="footer.jsp"></jsp:include>