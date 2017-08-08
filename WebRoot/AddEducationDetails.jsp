



<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml2/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<head>
		<script language="JavaScript" src="scripts/gen_validatorv31.js"
			type="text/javascript">
</script>
	</head>
	<body>
		<jsp:include page="Header.jsp"></jsp:include>
		<center><h3>
			<span class=subHead><br /> <font color='white'>Add
				Career Details</font> </span>
		</h3>
		</center>

		<form action="./addEduDetailAction.do" name="education" method='post'
			onsubmit="return validate()">
			<!--<table border="1"><tr><td></td></tr></table>-->


			<table border='0' align="center" width=60% >
				<th colspan="6" bgcolor="white">
					Academic Details
				</th>
				<tr></tr>
				<tr></tr>
				<tr>
					<td align='right'>
						Name of Study :
					</td>
					<td width="303">
						<input type="text" name="educationname" value="" size="20" />
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</td>
					
				</tr>
				<tr>
					<td align='right'>
						HallTicketNo :
					</td>
					<td>
						<input type="text" name="hallticketno" />
					</td>
					
				</tr>
				
				<tr>
					<td align='right'>
						Year of Pass :
					</td>
					<td>
						<select name='yearofpass'>
							<option value="">
								--select--
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
					
				</tr>
				<tr>
					<td align='right'>
						Total Marks :
					</td>
					<td width="276">
						<input type="text" name="totalmarks" value="" size="20" />
					</td>
					<tr>
						<td align='right'>
							Aggregate :
						</td>
						<td>
							<input type="text" name="aggregation" value="" size="20" />
						</td>
					</tr>
					
					<tr>
						<td align='right'> 
							Pass Percentage(%) : 
						</td>
						<td>
							<input type="text" name="percentage" />
						</td>
					</tr>
					<tr>
						<td align='right'>
							Scanned Certificate :
						</td>
						<td>
							<input type="file" name="scanedmemo1" value="" size="20" />
						</td>
					</tr>

					<input type='hidden' name='useridref'
						value='<%=session.getAttribute("userid")%>' />
					<tr>
						<td align="right">
							<font size="3" face="Verdana"> <input type="submit"
								name="Add" value="Add" /> &nbsp; </font>
						</td>
						<td align="left">
							<font size="3" face="Verdana"> <input type="reset"
								name="cancel" value="Cancel" /> </font>
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
var frmvalidator = new Validator("education");

frmvalidator.addValidation("educationname", "req",
		"Please enter your study Name");
		frmvalidator.addValidation("educationname", "alpha"," Education Name Alphabetic chars only");

frmvalidator.addValidation("hallticketno", "req",
		"Please enter your Hall Ticket Number");

frmvalidator.addValidation("yearofpass", "dontselect=0");
frmvalidator.addValidation("totalmarks", "req", "Please enter your Totalmarks");

frmvalidator.addValidation("totalmarks", "maxlen=5");
frmvalidator.addValidation("totalmarks", "numeric");
frmvalidator.addValidation("aggregation", "req", "Please enter your Aggregation");

frmvalidator.addValidation("aggregation", "maxlen=5");
frmvalidator.addValidation("aggregation", "numeric");


frmvalidator.addValidation("percentage", "req", "Please enter your Percentage");

frmvalidator.addValidation("percentage", "maxlen=5");
frmvalidator.addValidation("percentage", "numeric");

frmvalidator
		.addValidation("scanedmemo1", "req", "Please enter your study Name");
</script>
		<jsp:include page="Footer.jsp"></jsp:include>
	</body>
</html>