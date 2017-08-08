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
		<script language="JavaScript" src="images/pop-closeup.js"></script></head>
	<body>
		<jsp:include page="Header.jsp"></jsp:include>
		<center>
		<h3>
			<span class=subHead><br />Update License Details</span>
		</h3>
		</center>

		<form action="./updateLicenseDetailAction.do" name="license" method='post'
			onsubmit="return validate()">
			<!--<table border="1"><tr><td></td></tr></table>-->
			<table border='0' align="center" width=40% >
				<th colspan="2" bgcolor="skyblue">
					License Details
				</th>
				<tr></tr>
				<tr></tr>
				<c:if test="${not empty drivingLicenseTO}">
					<tr>
						<td align='right'>
							License No :
						</td>
						<td>
							<input type="text" name="liesenceno"
								value="${drivingLicenseTO.liesenceno}" size="20" />
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td align='right'>
							Vehivle Type :
						</td>
						<td>
							<input type="text" name="vechicleType"
								value="${drivingLicenseTO.vechicleType}" />
						</td>
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td align='right'>
							IssedDate :
						</td>
						<td>
							<input type="text" name="issueddate"
								value="${drivingLicenseTO.issueddate}" size="20" />
								<a
									href="javascript:show_calendar('document.license.issueddate', document.license.issueddate.value);">
									<img src="images/cal.gif" alt="a" width="18" height="18"
										border="0" /> </a>
						</td>
					</tr>
					<tr>
						<td align='right'>
							&nbsp; ValidDate :
						</td>
						<td>
							<input type="text" name="validdate"
								value="${drivingLicenseTO.validdate}" size="20" />
								<a
									href="javascript:show_calendar('document.license.validdate', document.license.validdate.value);">
									<img src="images/cal.gif" alt="a" width="18" height="18"
										border="0" /> </a>
						</td>
					</tr>
					<tr>
						<td colspan="2" align='center'>

						</td>
					</tr>
					<tr>
						<td align='right'>
							VehicleNo :
						</td>
						<td>
							<input type="text" name="vehicleno"
								value="${drivingLicenseTO.vehicleno}" />
						</td>
					</tr>

					<tr>
						<td align="right">
							<input type="submit" name="Add" value="Update" />
						</td>
						
						<td>
							<input type='hidden' name='useridref'
								value='<%=session.getAttribute("userid")%>' />
							<input type='hidden' name='liesenceid'
								value='${drivingLicenseTO.liesenceid}' />
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
var frmvalidator = new Validator("license");

frmvalidator.addValidation("liesenceno", "req", "Please enter your License");
frmvalidator.addValidation("vechicleType", "req", "Please enter your VehicleType");
frmvalidator.addValidation("issueddate", "req", "Please enter Issud Date");
frmvalidator.addValidation("validdate", "req", "Please enter your Valid Date");
frmvalidator.addValidation("vehicleno", "req", "Please enter your Vehicleno");


</script>
		<jsp:include page="Footer.jsp"></jsp:include>


	</body>
</html>