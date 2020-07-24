<jsp:include page="header.jsp"></jsp:include>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isErrorPage="true"%>
<%
	String statusCode = request.getParameter("statusCode");
	String requestUri = request.getParameter("requestUri");
	String servletName = request.getParameter("servletName");
	String exceptionName = request.getParameter("exceptionName");
	String exceptionMessage = request.getParameter("exceptionMessage");
%>

<div class="container">
	<div class="col-md-12">
		<div class="error-template">
			<div class="card">
				<div class="card-header">
					<h2>Oops!</h2>
				</div>
				<div class="card-body">
					<h5 class="card-title">StatusCode: ${statusCode} Not Found</h5>
					<p class="card-text">
					<ul class="list-group list-group-flush">
						<li class="list-group-item">Requested Page: <c:out
								value="${requestUri}" /></li>
						<li class="list-group-item">ServletName: <c:out
								value="${servletName}" /></li>
						<li class="list-group-item">exceptionName: <c:out
								value="${exceptionName}" /></li>
						<li class="list-group-item">exceptionMessage: <c:out
								value="${exceptionMessage}" /></li>
						<li class="list-group-item">Error: <c:out
								value="${pageContext.exception}" /></li>
						<li class="list-group-item">Stack trace: <c:forEach
								var="trace" items="${pageContext.exception.stackTrace}">
								<p>${trace}</p>
							</c:forEach>
						</li>
					</ul>
					</p>
					<a href="<%=request.getContextPath()%>/home"
						class="btn btn-primary btn-lg"><span
						class="glyphicon glyphicon-home"></span>Take Me Home</a>
				</div>
			</div>
		</div>
	</div>
</div>

<jsp:include page="footer.jsp"></jsp:include>
