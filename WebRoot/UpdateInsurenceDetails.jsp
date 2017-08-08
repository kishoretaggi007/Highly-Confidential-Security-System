<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml2/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<head>
<script language="JavaScript" src="scripts/gen_validatorv31.js"
			type="text/javascript"></script>
		<script language="JavaScript" type="text/javascript"
			src="scripts/ts_picker.js"></script>
		<script language="JavaScript1.1" src="scripts/pass.js">
</script>
		<script type="text/javascript" src="scripts/image.js"> </script>
		<script type="text/javascript" src="scripts/general.js"> </script>
		<script type="text/javascript" src="scripts/adi.js"> </script>
		<script type="text/javascript" src="scripts/form_validation.js"> </script>

		<script language="JavaScript" src="images/javascripts.js"></script>
		<script language="JavaScript" src="images/pop-closeup.js"></script>
</head>
	<body>
		<jsp:include page="Header.jsp"></jsp:include>
		<center>
		<h3>
			<span class=subHead><br />Add Insurance Details</span>
		</h3>
		</center>

		<form action="./updateInsuranceDetailsActtion.do" name="insurance"
			method='post' onsubmit="return validate()">
			<!--<table border="1"><tr><td></td></tr></table>-->
			<table border='0' align="center" width=60% >
				<th colspan="6" bgcolor="skyblue">
					Insurance Details
				</th>
				<tr></tr>
				<tr></tr>
				<c:if test="${not empty insuranceTO}">
					<tr>
						<td align='right'>
							Insurance Company Name :
						</td>
						<td>
							<input type="text" name="companyname"
								value="${insuranceTO.companyname }" size="20" />
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td align='right'>
							Policy Name :
						</td>
						<td>
							<input type="text" name="policyname"
								value="${insuranceTO.policyname }" />
						</td>
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td align='right'>
							Policy No:
						</td>
						<td>
							<input type="text" name="policyno"
								value="${insuranceTO.policyno }" size="20" />
						</td>
					</tr>
					<tr>
						<td align='right'>
							Premium Amount&nbsp; :
						</td>
						<td>
							<input type="text" name="premiumamount"
								value="${insuranceTO.premiumamount }" size="20" />
										
						</td>
					</tr>
					<tr>
						<td colspan="2" align='center'>

						</td>
					</tr>
					<tr>
						<td align='right'>
							PremiumDate :
						</td>
						<td>
							<input type="text" name="premiumdate"
								value="${insuranceTO.premiumdate }" />
								<a
									href="javascript:show_calendar('document.insurance.premiumdate', document.insurance.premiumdate.value);">
									<img src="images/cal.gif" alt="a" width="18" height="18"
										border="0" /> </a>
						</td>
					</tr>
					<tr>
						<td align="right">
							<input type="submit" name="Add" value="Update" />
						</td>
						<td>
							<input type='hidden' name='useridref'
								value='<%=session.getAttribute("userid")%>' />
							<input type='hidden' name='insurenceid'
								value='${insuranceTO.insurenceid }' />
						</td>
					</tr>
				</c:if>
			</table>
			<p>
				<br />
				<br />
				<br />
			</p>
			<p>
				&nbsp;
			</p>
			<p>
				&nbsp;
			</p>
			<p>
				&nbsp;
			</p>
			<p>
				<br />
			</p>
		</form>

		<script language="JavaScript" type="text/javascript">
//You should create the validator only after the definition of the HTML form
var frmvalidator = new Validator("insurance");

frmvalidator.addValidation("companyname", "req", "Please enter your First Name");
frmvalidator.addValidation("policyname", "req", "Please enter your Last Name");
frmvalidator.addValidation("policyno", "req", "Please enter your Last Name");
frmvalidator.addValidation("premiumamount", "numeric");
frmvalidator.addValidation("premiumdate", "req", "Please enter Select Date");
</script>
		<jsp:include page="Footer.jsp"></jsp:include>


	</body>
</html>