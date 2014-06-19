<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="java.util.Enumeration"%>
<%@page import="com.mysql.jdbc.util.LRUCache"%>
<%@page import="javaMeasure.BatchSetting"%>
<%@page import="javaMeasure.Batch"%>
<%@page import="java.io.*"%>´

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Noliac : <%//out.println(username);%> | Final report</title>
<link rel="stylesheet" type="text/css" href="view.css" media="all" />
<script type="text/javascript" src="view.js"></script>
<script type="text/javascript" src="calendar.js"></script>
</head>
<%	
	// batch and profile for table input
	String[] reportData = (String[]) request.getAttribute("reportData");  %>

<body id="main-body">
	<div id="wrapper">

		<div id="header">
			<img src="noliac_logo.png" alt="Logo">
		</div>

		<div id="form_container">

			<!--<h1><a>Noliac</a></h1>-->
			<form id="form_812583" class="appnitro" method="post" action="ReportServlet">
				<div class="form_description">
					<h1>Noliac Batch report</h1>
				</div>
				<ul>
					<li id="li_2" name="li_2">
						<table width="100%" border="1" style="border-collapse:collapse;">
							<tr>
								<td colspan="5"><h3>Final Inspection and Test Report<br>Certificate of Conformance</h3></td>

								<td>Batch id:</td>
								<td colspan="3"> <% out.print(reportData[0]); %></td>

							</tr>
							<tr>
								<td>Customer</td>
								<td colspan="8"> <% out.print(reportData[1]); %> </td>
							</tr>
							<tr>
								<td>Item description:</td>
								<td colspan="4"> <% out.print(reportData[2]); %> </td>
								<td>Drawing number:</td>
								<td colspan="3"> <% out.print(reportData[5]); %> </td>
							</tr>
							<tr>
								<td>Item code:</td>
								<td colspan="4"> <% out.print(reportData[3]); %> </td>
								<td>Specification:</td>
								<td colspan="3"> <% out.print(reportData[6]); %> </td>
							</tr>
							<tr>
								<td>Internal order number:</td>
								<td colspan="4"> <% out.print(reportData[4]); %> </td>
								<td>Visual Inspection:</td>
								<td colspan="3"> <% out.print(reportData[7]); %> </td>
							</tr>
							<tr>
								<td>Parameter</td>
								<td colspan="3">Specification</td>
								<td>Inspection level</td>
								<td colspan="4">Measured</td>
							</tr>
							<tr>
								<td>&nbsp;</td>
								<td>Norm value</td>
								<td>Min</td>
								<td>Max</td>
								<td>&nbsp;</td>
								<td>Average</td>
								<td>Min</td>
								<td>Max</td>
								<td>Approved</td>
							</tr>
							<tr>
								<td> Length (mm): </td>
								<%for(int i = 8; i < 16; i++){ %>
								<td>
								<%
									if(reportData[i] == null || reportData[i] == ""){
										out.println("\t");
									}else
									out.println(reportData[i]);
								%>
								</td>
								<% } %>
							</tr>
							<tr>
								<td>Width (mm):</td>
								<%for(int i = 16; i < 24; i++){ %>
								<td>
								<%
									if(reportData[i] == null || reportData[i] == ""){
										out.println("\t");
									}else
									out.println(reportData[i]);
								%>
								</td>
								<% } %>
							</tr>
							<tr>
								<td> Thickness (mm):</td>
								<%for(int i = 24; i < 32; i++){ %>
								<td>
								<%
									if(reportData[i] == null || reportData[i] == ""){
										out.println("\t");
									}else
									out.println(reportData[i]);
								%>
								</td>
								<% } %>
							</tr>
							<tr>
								<td> Capacitans (nF): </td>
								<%for(int i = 32; i < 40; i++){ %>
								<td>
								<%
									if(reportData[i] == null || reportData[i] == ""){
										out.println("\t");
									}else
									out.println(reportData[i]);
								%>
								</td>
								<% } %>
							</tr>
							<tr>
								<td> Stroke (um): </td>
								<%for(int i = 40; i < 48; i++){ %>
								<td>
								<%
									if(reportData[i] == null || reportData[i] == ""){
										out.println("\t");
									}else
									out.println(reportData[i]);
								%>
								</td>
								<% } %>
							</tr>
							<tr>
								<td>Leakage current:</td>
								<%for(int i = 8; i < 16; i++){ %>
								<td>
								<%
									if(reportData[i] == null || reportData[i] == ""){
										out.println("\t");
									}else
									out.println(reportData[i]);
								%>
								</td>
								<% } %>
							</tr>
							<tr>
								<td>Visual inspection:</td>
								<%for(int i = 8; i < 16; i++){ %>
								<td>
								<%
									if(reportData[i] == null || reportData[i] == ""){
										out.println("\t");
									}else
									out.println(reportData[i]);
								%>
								</td>
								<% } %>
							</tr>
							<tr>
								<td colspan="9">&nbsp;</td>
							</tr>
							<tr>
								<td colspan="5">Comments:</td>
								<td>&nbsp;approved date</td>
								<td colspan="3">&nbsp;</td>
							</tr>
							<tr>
								<td colspan="5" rowspan="2"></td>
								<td>&nbsp;number approved</td>
								<td colspan="3">&nbsp;</td>
							</tr>
							<tr>
								<td>&nbsp;approved by</td>
								<td colspan="3">&nbsp;</td>
							</tr>
						</table>
					</li>

					<li class="buttons">
					<input id="newReport" class="button_text" type="submit" name="newReport" value="Ny rapport" />
                    <input id="saveAsExcel" class="button_text" type="submit" name="saveAsExcel" value="gem som Excel" />
					</li>
				</ul>
			</form>
			<div id="footer">
				By Area51
				<!--Generated by <a href="http://www.phpform.org">pForm</a>-->
			</div>

		</div>
	</div>
</body>


</html>
