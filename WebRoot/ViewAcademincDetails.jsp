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
			CAREER DETAILS
		</h1>
	</center>

	<form name='deleteuser' action='./deleteItemAction.do'>
		<center>
			<table>
				<tr bgcolor='skyblue'>
					<td>
					</td>
					<td align='center'>
						<b>STUDY NAME</b>
					</td>
					<td align='center'>
						<b>HALL TICKET NO </b>
					</td>
					<td align='center'>
						<b>YEAR OF PASS </b>
					</td>
					<td align='center'>
						<b>TOTAL MARKS </b>
					</td>
					<td align='center'>
						<b>AGGRIGATION </b>
					</td>
					<td align='center'>
						<b>PERCENTATION</b>
					</td>
					<td align='center'>
						<b>SCANEDDEMO</b>
					</td>
				</tr>
				<c:set var="color" value="0" scope="request" />
				<c:forEach var="education" items="${vEducationTOs}">
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
							value="${education.educationid}" />
					</td>
					<td>
						<a
							href="./updateCareerDetailsAction.do?educationid=${education.educationid}"><b>${education.educationname
								}</b> </a>
					</td>
					<td>
						<b>${education.hallticketno }</b>
					</td>
					<td>
						<b>${education.yearofpass }</b>
					</td>
					<td>
						<b>${education.totalmarks }</b>
					</td>
					<td>
						<b>${education.aggregation }</b>
					</td>
					<td>
						<b>${education.percentage }</b>
					</td>
					<td>
						<a href="${education.scanedmemo1 }"><b>DOWNLOAD<b />
						</a>
					</td>
					</tr>
				</c:forEach>
				<tr>
					<td align='center' colspan='8'>
						<input type='hidden' name="useridref"
							value="${sessionScope.userid}" />
						<input type='hidden' name="delete" value="education" />
						<input type="submit" value="Delete" />
					</td>
				</tr>
			</table>
		</center>
	</form>
	<jsp:include page="/Footer.jsp"></jsp:include>
</body>
