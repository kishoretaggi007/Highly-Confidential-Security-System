<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml2/DTD/xhtml1-strict.dtd">
<head>
	<script language="JavaScript"
		src="<%=request.getContextPath()
							+ "/scripts/gen_validatorv31.js"%>"
		type="text/javascript">
</script>
	<SCRIPT language="JavaScript">
function anchor_test(pass) {
	window.alert("Password : "+pass)
}
</SCRIPT>

</head>
<body>
	<jsp:include page="Header.jsp"></jsp:include>
	<center>
		<h1>
			BANK DETAILS
		</h1>
	</center>

	<form name='deleteuser' action='./deleteItemAction.do'>
		<center>
			<table>
				<tr bgcolor='skyblue'>
					<td>
					</td>
					<td align='center'>
						<b>BANK NAME</b>
					</td>
					<td align='center'>
						<b>BRANCH NAME </b>
					</td>
					<td align='center'>
						<b>ACCNUMBER </b>
					</td>
					<td align='center'>
						<b>USERNAME </b>
					</td>
					<td align='center'>
						<b>PASSWORD </b>
					</td>
					<td align='center'>
						<b>ATMCARDNO </b>
					</td>
					<td align='center'>
						<b>ATMPINNO </b>
					</td>
					<td align='center'>
						<b>SITEURL </b>
					</td>
				</tr>
				<c:set var="color" value="0" scope="request" />
				<c:forEach var="vBankTOs" items="${vBankTOs}">
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
							value="${vBankTOs.bankid}" />
					</td>
					<td>
						<a href="./viewUpdateBankAction.do?bankid=${vBankTOs.bankid}"><b>${vBankTOs.bankName
								}</b> </a>
					</td>
					<td>
						<b>${vBankTOs.branchName }</b>
					</td>
					<td>
						<b>${vBankTOs.accnumber }</b>
					</td>
					<td>
						<b>${vBankTOs.username }</b>
					</td>
					<td>
						<a HREF="javascript:anchor_test('${vBankTOs.password }')"
							style="text-decoration: none;"><b><h3>
									********
								</h3>
						</b> </a>
					</td>
					<td>
						<b>${vBankTOs.atmcardno }</b>
					</td>
					<td>
						<a HREF="javascript:anchor_test('${vBankTOs.atmpin }')"
							style="text-decoration: none;"><b><h3>
									********
								</h3>
						</b> </a>
					</td>
					<td>
						<b>${vBankTOs.siteurl }</b>
					</td>

					</tr>
				</c:forEach>
				<tr>
					<td align='center' colspan='8'>
						<input type='hidden' name="useridref"
							value="${sessionScope.userid}" />
						<input type='hidden' name="delete" value="bank" />
						<input type="submit" value="Delete" />
					</td>
				</tr>
			</table>
		</center>
	</form>
	<jsp:include page="/Footer.jsp"></jsp:include>
</body>
