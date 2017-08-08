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
			USER IMP FILE DETAILS
		</h1>
	</center>

	<form name='deleteuser' action='./deleteItemAction.do'>
		<center>
			<table>
				<tr bgcolor='skyblue'>
					<td>
					</td>
					<td align='center'>
						<b>FILE NAME</b>
					</td>
					<td align='center'>
						<b>FILE DESCRIPTION </b>
					</td>
					<td align='center'>
						<b>FILE </b>
					</td>

				</tr>
				<c:set var="color" value="0" scope="request" />
				<c:forEach var="impfiles" items="${vUserImpFilesTOs}">
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
							value="${impfiles.fileid}" />
					</td>
					<td>
						<a href="./viewUpdateImpFiles.do?fileid=${impfiles.fileid}"><b>${impfiles.filename
								}</b> </a>
					</td>
					<td>
						${impfiles.filedescription}
						
					</td>
					<td>
						<a href="${impfiles.filedatapath}"><b>DOWNLOAD<b />
						</a>
					</td>

					</tr>
				</c:forEach>
				<tr>
					<td align='center' colspan='8'>
						<input type='hidden' name="useridref"
							value="${sessionScope.userid}" />
						<input type='hidden' name="delete" value="impfiles" />
						<input type="submit" value="Delete" />
					</td>
				</tr>
			</table>
		</center>
	</form>
	<jsp:include page="/Footer.jsp"></jsp:include>
</body>
