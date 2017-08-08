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
			LICENSE DETAILS
		</h1>
	</center>

	<form name='deleteuser' action='./deleteItemAction.do'>
		<center>
			<table>
				<tr bgcolor='skyblue'>
					<td>
					</td>
					<td align='center'>
						<b>LICENSE NO</b>
					</td>
					<td align='center'>
						<b>VEHICLE TYPE </b>
					</td>
					<td align='center'>
						<b>VEHICLE NO </b>
					</td>
					<td align='center'>
						<b>ISSUED DATE </b>
					</td>
					<td align='center'>
						<b>VALIDATY </b>
					</td>
				</tr>
				<c:set var="color" value="0" scope="request" />
				<c:forEach var="vehicle" items="${vDrivingLicenseTOs}">
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
							value="${vehicle.liesenceid}" />
					</td>
					<td>
						<a
							href="./viewUpdateLicenseDetails.do?liesenceid=${vehicle.liesenceid}"><b>${vehicle.liesenceno
								}</b>
						</a>
					</td>
					<td>
						<b>${vehicle.vechicleType }</b>
					</td>
					<td>
						<b>${vehicle.vehicleno }</b>
					</td>
					<td>
						<b>${vehicle.issueddate }</b>
					</td>
					<td>
						<b>${vehicle.validdate }</b>
					</td>
					</tr>
				</c:forEach>
				<tr>
					<td align='center' colspan='8'>
						<input type='hidden' name="useridref"
							value="${sessionScope.userid}" />
						<input type='hidden' name="delete" value="license" />
						<input type="submit" value="Delete" />
					</td>
				</tr>
			</table>
		</center>
	</form>
	<jsp:include page="/Footer.jsp"></jsp:include>
</body>
