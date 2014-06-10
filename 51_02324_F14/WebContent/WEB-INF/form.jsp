<%@page import="javaMeasure.User"%>
<%@page import="java.util.ArrayList"%>
<%@page
	import="javaMeasure.control.interfaces.IDatabaseController.DataBaseException"%>
<%@page import="javaMeasure.control.MainController"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="javaMeasure.control.DataBaseController"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<%
	User user = (User) session.getAttribute("user");
%>
<title>Noliac : <%
	out.println(user.getUserName());
%> | udtræk
</title>
<link rel="stylesheet" type="text/css" href="view.css" media="all" />
<script type="text/javascript" src="view.js"></script>
<script type="text/javascript" src="calendar.js"></script>
</head>
<body id="main-body">
	<div id="wrapper">
		<!-- wrapper for whole page  -->
		<div id="header">
			<img src="noliac_logo.png" alt="Logo">
		</div>
		<!-- Form begins-->
		<div id="form_container">
			<form id="batch_form" class="appnitro" method="post" action="MenuServlet">
				<div class="form_description">
					<h1>Noliac Batch-udtræk</h1>
					<p>Indtast Batch ID eller vælg Batch ID fra rullemenu</p>
				</div>

				<!-- Input Fields contained in table-->
				<table width="100%" border="0">
					<tr>
						<td>
							<ul>
								<li id="li_3" name="li_3"><label class="description"
									for="batchname"> <% //Checks if Batch was found
											if (request.getParameter("fail") != null) out.print("Batch navn ikke genkendt! -");
										%> Indtast Batch navn
								</label>
									<div>
										<input id="batchname" name="batchname"
											class="element text medium" type="text" maxlength="255"
											list="batches" autocomplete="on" value="" />
										<datalist id="batches"> <% //TODO replace with AJAX call
 	ArrayList<String> batchNames = ((DataBaseController) request.getSession().getAttribute("database")).getBatchNames();

 						for (String batchName : batchNames){
 %>
										<option>
											<%=batchName%></option>
										<%
											}
										%> </datalist>
									</div>
									</li>
								<li id="li_1"><label class="description" for="element_1">Start
										dato </label> <span> <input id="element_1_2" name="element_1_2"
										class="element text" size="2" maxlength="2" value=""
										type="text"> / <label for="element_1_2">DD</label>
								</span> <span> <input id="element_1_1" name="element_1_1"
										class="element text" size="2" maxlength="2" value=""
										type="text"> / <label for="element_1_1">MM</label>
								</span> <span> <input id="element_1_3" name="element_1_3"
										class="element text" size="4" maxlength="4" value=""
										type="text"> <label for="element_1_3">YYYY</label>
								</span> <span id="calendar_1"> <img id="cal_img_1"
										class="datepicker" src="calendar.gif" alt="Pick a date." />
								</span> <script type="text/javascript">
									Calendar.setup({
										inputField : "element_1_3",
										baseField : "element_1",
										displayArea : "calendar_1",
										button : "cal_img_1",
										ifFormat : "%B %e, %Y",
										onSelect : selectDate
									});
								</script></li>
								<li id="li_2" name="li_2"><label class="description"
									for="element_2">Slut dato </label> <span> <input
										id="element_2_2" name="element_2_2" class="element text"
										size="2" maxlength="2" value="" type="text"> / <label
										for="element_2_2">DD</label>
								</span> <span> <input id="element_2_1" name="element_2_1"
										class="element text" size="2" maxlength="2" value=""
										type="text"> / <label for="element_2_1">MM</label>
								</span> <span> <input id="element_2_3" name="element_2_3"
										class="element text" size="4" maxlength="4" value=""
										type="text"> <label for="element_2_3">YYYY</label>
								</span> <span id="calendar_2"> <img id="cal_img_2"
										class="datepicker" src="calendar.gif" alt="Pick a date.">
								</span> <script type="text/javascript">
									Calendar.setup({
										inputField : "element_2_3",
										baseField : "element_2",
										displayArea : "calendar_2",
										button : "cal_img_2",
										ifFormat : "%B %e, %Y",
										onSelect : selectDate
									});
								</script></li>
							</ul>
						</td>
						<td width="50%"><label class="description" for="selectedbatch">
								Fundne batches </label> <select name="selectedbatch" id="selectedbatch"
							size="10">
								<% //TODO replace with AJAX
								for (String batchName : batchNames){ %>
								<option>
									<%=batchName%></option>
								<%
											}%>
						</select></td>
					</tr>
				</table>


			  <li class="buttons"><input type="hidden" name="form_id"
					value="812583" /> <input id="submitForm" class="button_text"
					type="submit" name="submitForm" value="FormSubmit" /> <input
					id="logout" class="button_text" type="submit" name="logout"
					value="logout" /></li>

			</form>
			<div id="footer">By Area51</div>

		</div>
	</div>
</body>
</html>
