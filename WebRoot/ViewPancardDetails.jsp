<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml2/DTD/xhtml1-strict.dtd">
<head>
	<script language="JavaScript"
		src="<%=request.getContextPath()
							+ "/scripts/gen_validatorv31.js"%>"
		type="text/javascript">
</script>
</head>
<body>
	&nbsp;
	<jsp:include page="Header.jsp"></jsp:include>
	<center>
		<h1>
			PANCARD DETAILS
		</h1>
	</center>

	<form name='deleteuser' action='./deleteItemAction.do'>
		<center>
			<table>
				<tr bgcolor='skyblue'>
					<td>
					</td>
					<td align='center'>
						<b>ACC HOLDER NAME</b>
					</td>
					<td align='center'>
						<b>PAN CARD NO </b>
					</td>
					<td align='center'>
						<b>ISSUD DATE </b>
					</td>
				</tr>
				<c:set var="color" value="0" scope="request" />
				<c:forEach var="panncard" items="${vPancardTOs}">
					<c:choose>
						<c:when test="${requestScope.color eq '0'}">
							<tr bgcolor='#F5FFFA'>
								<c:set var="color" value="${color+1}" scope="request" />
						</c:when>

						<c:otherwise>
							<tr bgcolor='#FFE4E1'>
								<c:set var="color" value="${color-1}" scope="request" />
						</c:otherwise>
					</c:choose>
					<td>
						<input name="ch" type="checkbox" id="checkbox2" onClick="check1()"
							value="${panncard.panid}" />
					</td>
					<td>
						<a href="./viewUpdatePanDetailsAction.do?panid=${panncard.panid}"><b>${panncard.accname}</b>
						</a>
					</td>
					<td>
						<b>${panncard.pancardno }</b>
					</td>
					<td>
						<b>${panncard.issuedate }</b>
					</td>
					</tr>
				</c:forEach>
				<tr>
					<td align='center' colspan='8'>
						<input type='hidden' name="useridref"
							value="${sessionScope.userid}" />
						<input type='hidden' name="delete" value="pancard" />
						<input type="submit" value="Delete" />
					</td>
				</tr>
			</table>
		</center>
	</form>
	<jsp:include page="/Footer.jsp"></jsp:include>
</body>
