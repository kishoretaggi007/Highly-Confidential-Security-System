<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml2/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<head>

		<script language="JavaScript" src="scripts/gen_validatorv31.js"
			type="text/javascript"></script></head>
	<body>

		<jsp:include page="Header.jsp"></jsp:include>
		<center>
		<h3>
			<span class=subHead><br />
				<font color='white'>Add Imp Files Details</font>
			</span>
		</h3>
		</center>

		<form action="./addImpFilesAction.do" name="impfiles" method='post'
			onsubmit="return validate()">
			<!--<table border="1"><tr><td></td></tr></table>-->


			<table border='0' align="center" width=60% >
				<th colspan="4" bgcolor="white">
					&nbsp; Imp Files Details
				</th>
				<tr></tr>
				<tr></tr>
				<tr>
					<td align='right'>
						Name of File :
					</td>
					<td width="303">
						<input type="text" name="filename" value="" size="20" />
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td align='right'>
						&nbsp; Purpose Of File :
					</td>
					<td>
						<input type="text" name="filedescription" />
					</td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td></td>
					<td align='right'>

					</td>
					<td>

					</td>

					<td></td>
				</tr>

				<tr>
					<td align='right'>
						Browse File :
					</td>
					<td width="276">
						<input type="file" name="filedatapath" value="" />
					</td>


					<input type='hidden' name='useridref'
						value='<%=session.getAttribute("userid")%>' />
					<tr>

						<td align="right">
							<font size="3" face="Verdana">
							<input type="submit" name="Add" value="Add" />
							&nbsp;
							</font>
						</td>
						<td align="left">
							<font size="3" face="Verdana">
							<input type="reset" name="cancel" value="Cancel" />
							</font>
						</td>
						<td></td>
					</tr>
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
var frmvalidator = new Validator("impfiles");

frmvalidator.addValidation("filename", "req", "Please enter your File Name");
frmvalidator.addValidation("filedescription", "req", "Please enter your File Description");
frmvalidator.addValidation("filedatapath", "req", "Browse File");

</script>
		<jsp:include page="Footer.jsp"></jsp:include>


	</body>
</html>
		<jsp:include page="Footer.jsp"></jsp:include>


	</body>
</html>