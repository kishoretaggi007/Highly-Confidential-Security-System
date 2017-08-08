<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ page import="com.hcss.bean.UserProfileFormBean;"%>
<%@ page errorPage="UserExceptionHandler.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml2/DTD/xhtml1-strict.dtd">
<%
	String path;
	String usertype;
	String s;
	Vector c;
%>

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
	<br />
	<center>

		<form name='deleteuser' action='./deleteUserAction.do'>
			<table border='1'>
				<tr bgcolor='skyblue'>
					<%
						if (session.getAttribute("role").equals("admin")) {
					%><td>
						&nbsp
					</td>
					<%
						}
					%>
					<td align='center'>
						<b>Sno</b>
					</td>
					<td align='center'>
						<b>Name </b>
					</td>
					<td align='center'>
						<b>Gender </b>
					</td>
					<td align='center'>
						<b>Contact </b>
					</td>
					<td align='center'>
						<b>Address </b>
					</td>
					<td align='center'>
						<b>Photo </b>
					</td>

					<td align='center'>
						<b>Status</b>
					</td>
					<%
						c = (Vector) request.getAttribute("userinfo");
						Iterator it = c.listIterator();
						int count = 1;
						while (it.hasNext()) {
							System.out.println(count);
							UserProfileFormBean pro = (UserProfileFormBean) it.next();
							path = request.getRealPath("/images");
							s = pro.getPath();
							System.out.println(s);
					%>


				</tr>


				<tr>
					<%
						if (session.getAttribute("role").equals("admin")) {
					%>
					<td>
						<input name="ch" type="checkbox" id="checkbox2" onClick="check1()"
							value="<%=pro.getUserid()%>">
					</td>
					<%
						}
					%><td>
						<b><%=count++%></b>
					</td>
					<td>
						<b><%=pro.getFirstName()%></b>
					</td>
					<td>
						<b><%=pro.getGender()%></b>
					</td>
					<td>
						<b>mail :<%=pro.getEmailId()%></b>
						<br>
						<b>Ph :<%=pro.getPhoneno()%></b>
						<br>
						<b>Fax :<%=pro.getFax()%></b>
					</td>
					<td>
						<b>Hno :<%=pro.getHouseno()%>,<%=pro.getStreet()%> street</b>
						<br>
						<b><%=pro.getCity()%>,<%=pro.getDistrict()%>(District)</b>
						<br>
						<b><%=pro.getState()%>,<%=pro.getCountry()%><br> <b>pin
								:</b><%=pro.getPincode()%></b>
					</td>
					<td>
						<img src="<%=pro.getPhotograph()%>" height='100' width='100'>
					</td>
					<%
						if (pro.getStatus().equals("Request")) {
					%>
					<td>
						<b> <a
							href="./updateUserStatusAction.do?userid=<%=pro.getUserid()%>">Request</a>
						</b>
					</td>
					<%
						} else {
					%><td><%=pro.getStatus()%></td>
					<%
						}
						}
					%>
					<%
						c = (Vector) request.getAttribute("userinfo");
						if (!c.isEmpty()) {
							if (session.getAttribute("role").equals("admin")) {
					%>

					<tr>
						<td align='center' colspan='8'>
							<input type="submit" value="Delete" />
						</td>
					</tr>
					<%
						}
						}
					%>
				
			</table>
	</center>
	<jsp:include page="/Footer.jsp"></jsp:include>
</body>
