<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml2/DTD/xhtml1-strict.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
	<body >
		<table>
			<tr>
				<td><jsp:include page="SlideImages.jsp"></jsp:include>
				</td>
			</tr>
		</table>
		<c:choose>
			<c:when test="${sessionScope.role eq 'admin'}">
				<jsp:include page="AdminMenu.jsp" />
			</c:when>

			<c:when test="${sessionScope.role eq 'user'}">
				<jsp:include page="UserMenu.jsp" />
			</c:when>
			<c:otherwise>
				<jsp:include page="HomeMenu.jsp"></jsp:include>
			</c:otherwise>
		</c:choose>
		<center>
			<font color="red"><b> <c:if
						test="${requestScope.status!='null'}">
						<c:out value="${requestScope.status}">
						</c:out>
						
					</c:if>
					 </b> </font>
		</center>
	</body>
</html>