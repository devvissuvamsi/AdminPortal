<jsp:include page="header.jsp"></jsp:include>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page errorPage="/error"%>

	<!--  content begin -->
	<div class="container">
		<br />
		<h2>Add new User</h2>
		<br />
		<form action="<%=request.getContextPath()%>/user.create" method="post">
			<div class="form-group">
				<label for="name">User Name</label> <input type="text"
					class="form-control" name="name" />
			</div>
			<div class="form-group">
				<label for="email">User Email</label> <input type="text"
					class="form-control" name="email">
			</div>
			<button type="submit" class="btn btn-primary" name="submit">Submit</button>
		</form>
	</div>
	<!--  content end -->

<jsp:include page="footer.jsp"></jsp:include>