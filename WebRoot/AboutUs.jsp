<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml2/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
	<html>
		<head>
			<base href="<%=basePath%>">

			<title>My JSP 'AboutUs.jsp' starting page</title>

			<meta http-equiv="pragma" content="no-cache">
			<meta http-equiv="cache-control" content="no-cache">
			<meta http-equiv="expires" content="0">
			<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
			<meta http-equiv="description" content="This is my page">
			<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

		</head>

		<body>
			<jsp:include page="Header.jsp"></jsp:include>
			<!-- ImageReady Slices (home page.jpg) -->
			<div align="center">

				<center>
				<table>
					<tr>
						
					<tr>
						<td>
							<p> 
								&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; <h3>Westminster Site Security System Features 
							</h3>
						</td>
					</tr>
					<tr>
						<td aling='le'>
							<p>
								<br>
									* Configurable to identify objects above a given size so that
									irrelevant background movements are filtered out; <br>
										* Discriminates between threats and background clutter based
										on heading, speed and geographical area; <br>
											* Detects small objects including a walking, crouching or
											crawling person; <br>
												* Provides a record of movements over time across the entire
												survey area, or it can focus on specific areas, for example
												the walkways between buildings; <br>
													* Directs a CCTV camera to a detected threat and
													automatically controls the camera to follow the threat in
													head/shoulders or wide area settings; * Records AVI files
													and raw data sets or screen shot bitmaps; * Overlays a
													detected intruder position on a background map or aerial
													photograph of the area; <br>
														* Selectable zones of interest to display in the field of
														view; <br>
															* Assignment of detection zones, providing different
															levels of alarm in sensitive areas; <br>
																* Assignment of non critical zones to allow for normal
																site operation; <br>
																	* Automatically compensates for background changes to
																	the environment e.g. moving trees, grass; <br>
																		* Able to calculate heading, position and velocity of
																		intruders and plots their track. </br>
																</br>
															</br>
														</br>
													</br>
												</br>
											</br>
										</br>
									</br>
								</br>
						</td>
					</tr>
					</p>
				</center>
				</table>
		</body>

	</html>