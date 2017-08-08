<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	if (session.getAttribute("userid") == null) {
		RequestDispatcher rd = request
				.getRequestDispatcher("./LoginForm.jsp");
		rd.forward(request, response);
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml2/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
	<head>

<script language="JavaScript"
			src="<%=request.getContextPath()
							+ "/scripts/gen_validatorv31.js"%>"
			type="text/javascript"></script>
	</head>
	<body>
		<jsp:include page="Header.jsp"></jsp:include>
		<center>
		<b><h4>
				Update Career Details Form
			</h4> </b>
		</center>
		<form method='post' action="./updateEduDetailsAction.do"
			name="education">
			<table border='1' align="center" >
				<c:if test="${not empty educationTO}">
					<tr>
						<td align='right'>
							<b>Study Name :</b>
						</td>
						<input type='hidden' name='useridref'
							value='${sessionScope.userid}' />
						<input type='hidden' name='educationid'
							value='${educationTO.educationid}' />
						<td>
							<input type='text' name='educationname'
								value="${educationTO.educationname}" />
						</td>
					</tr>
					<tr>
						<td align='right'>
							<b>Hall Ticket No :</b>
						</td>
						<td>
							<input type='text' name='hallticketno'
								value="${educationTO.hallticketno}"  />
						</td>
					<tr>
						<tr>
							<td align='right'>
								<b>Year of Pass :</b>
							</td>
							<td>
								<select name='yearofpass'>
									<option value="${educationTO.yearofpass}">
										${educationTO.yearofpass}
									</option>
									<option value="1980">
										1980
									</option>
									<option value="1981">
										1981
									</option>
									<option value="1982">
										1982
									</option>
									<option value="1983">
										1983
									</option>
									<option value="1984">
										1984
									</option>
									<option value="1985">
										1985
									</option>
									<option value="1986">
										1986
									</option>
									<option value="1987">
										1987
									</option>
									<option value="1988">
										1988
									</option>
									<option value="1989">
										1989
									</option>
									<option value="1990">
										1990
									</option>
									<option value="1991">
										1991
									</option>
									<option value="1992">
										1992
									</option>
									<option value="1993">
										1993
									</option>
									<option value="1994">
										1994
									</option>
									<option value="1995">
										1995
									</option>
									<option value="1996">
										1996
									</option>
									<option value="1997">
										1997
									</option>
									<option value="1998">
										1998
									</option>
									<option value="1999">
										1999
									</option>
									<option value="2000">
										2000
									</option>
									<option value="2001">
										2001
									</option>
									<option value="2002">
										2002
									</option>
									<option value="2003">
										2003
									</option>
									<option value="2004">
										2004
									</option>
									<option value="2005">
										2005
									</option>
									<option value="2006">
										2006
									</option>
									<option value="2007">
										2007
									</option>
									<option value="2008">
										2008
									</option>
									<option value="2009">
										2009
									</option>
									<option value="2010">
										2010
									</option>
									<option value="2011">
										2011
									</option>
									<option value="2012">
										2012
									</option>
									<option value="2013">
										2013
									</option>
									<option value="2014">
										2014
									</option>
									<option value="2015">
										2015
									</option>
									<option value="2016">
										2016
									</option>
									<option value="2017">
										2017
									</option>
									<option value="2018">
										2018
									</option>
									<option value="2019">
										2019
									</option>
									<select>
							</td>
						<tr>
							<td align='right'>
								<b>Total Marks :</b>
							</td>
							<td>
								<input type='text' name="totalmarks"
									value="${educationTO.totalmarks}" />
							</td>
							<td></td>
						</tr>
						<tr>
							<td align='right'>
								<b>Aggrigation :</b>
							</td>
							<td>
								<input type='text' name="aggregation"
									value="${educationTO.aggregation}" height=50 wedth=10>
							</td>
							<td>
							</td>
						</tr>
						<tr>
							<td align='right'>
								<b>Percentage :</b>
							</td>
							<td>
								<input type='text' name="percentage"
									value="${educationTO.percentage}" height=50 wedth=10>
							</td>
							<td>
							</td>
						</tr>
						<tr>
							<td align='right'>
								<b>ScanedMemo :</b>
							</td>
							<td>
								<input type='file' name="scanedmemo1"
									value="${educationTO.scanedmemo1}" height=50 wedth=10>
							</td>
							<td>
							</td>
						</tr>
				</c:if>
				<tr>
					<td align='right'>
						<input type=submit value=Add>
					</td>
					<td>
						<input type=reset value=Clear>
					</td>
				</tr>
			</table>
		</form>
		<script language="JavaScript" type="text/javascript">
//You should create the validator only after the definition of the HTML form
var frmvalidator = new Validator("education");

frmvalidator.addValidation("educationname", "req", "Please enter your study Name");

frmvalidator.addValidation("hallticketno", "req", "Please enter your Hall Ticket Number");


frmvalidator.addValidation("yearofpass", "dontselect=0");

frmvalidator.addValidation("totalmarks", "maxlen=5");
frmvalidator.addValidation("totalmarks", "numeric");


frmvalidator.addValidation("aggregation", "maxlen=5");
frmvalidator.addValidation("aggregation", "numeric");

frmvalidator.addValidation("percentage", "maxlen=5");
frmvalidator.addValidation("percentage", "numeric");

frmvalidator.addValidation("scanedmemo1", "req", "Please select path for your MEMO");

</script>
		<br />
		<jsp:include page="Footer.jsp"></jsp:include>
	</body>
</html>
