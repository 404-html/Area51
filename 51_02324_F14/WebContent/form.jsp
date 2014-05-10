<%@page import="javaMeasure.control.MainController"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<%
	String username = (String) session.getAttribute("username");
%>
<title>Noliac Form logged in as: <%
	out.println(username);
%></title>
<link rel="stylesheet" type="text/css" href="view.css" media="all" />
<script type="text/javascript" src="view.js"></script>
<script type="text/javascript" src="calendar.js"></script>
</head>
<body id="main-body">

	<jsp:useBean id="database"
		class="javaMeasure.control.DataBaseController" scope="session" />
	<%
		if (username == null) {
			response.sendRedirect("userlogin.jsp");
		} else {

			String batchname = request.getParameter("element_3");
			if (batchname == null) {

			} else if (!batchname.equalsIgnoreCase("")) {
				javaMeasure.Batch batch = database.getBatch(batchname);
				if (batch != null) {

					javaMeasure.BatchProfile profile = database
							.getBatchProfile(batch.getProfileID());
					session.setAttribute("batch", batch);
					session.setAttribute("profile", profile);
					response.sendRedirect("report.jsp");
				}
			}
		}
	%>
	<div id="wrapper">

		<div id="header">
			<img src="noliac_logo.png" alt="Logo">
		</div>

		<div id="form_container">

			<!--<h1><a>Noliac</a></h1>-->
			<form id="form_812583" class="appnitro" method="post">
				<div class="form_description">
					<h1>Noliac Batch-udtræk</h1>
					<p>Indtast selv Batch ID eller vælg Batch ID fra rullemenu</p>
				</div>
				<ul>

					<li id="li_3"><label class="description" for="element_3">Indtast
							Batch ID </label>
						<div>
							<input id="element_3" name="element_3"
								class="element text medium" type="text" maxlength="255" value="webtest" />
						</div>
						<p class="guidelines" id="guide_3">
							<small>Batch # du ønsker</small>
						</p></li>
					<li id="li_1"><label class="description" for="element_1">Start
							dato </label> <span> <input id="element_1_1" name="element_1_1"
							class="element text" size="2" maxlength="2" value="" type="text">
							/ <label for="element_1_1">MM</label>
					</span> <span> <input id="element_1_2" name="element_1_2"
							class="element text" size="2" maxlength="2" value="" type="text">
							/ <label for="element_1_2">DD</label>
					</span> <span> <input id="element_1_3" name="element_1_3"
							class="element text" size="4" maxlength="4" value="" type="text">
							<label for="element_1_3">YYYY</label>
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
					<li id="li_2"><label class="description" for="element_2">Slut
							dato </label> <span> <input id="element_2_1" name="element_2_1"
							class="element text" size="2" maxlength="2" value="" type="text">
							/ <label for="element_2_1">MM</label>
					</span> <span> <input id="element_2_2" name="element_2_2"
							class="element text" size="2" maxlength="2" value="" type="text">
							/ <label for="element_2_2">DD</label>
					</span> <span> <input id="element_2_3" name="element_2_3"
							class="element text" size="4" maxlength="4" value="" type="text">
							<label for="element_2_3">YYYY</label>
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

					<li class="buttons"><input type="hidden" name="form_id"
						value="812583" /> <input id="saveForm" class="button_text"
						type="submit" name="submit" value="Indsend" /></li>
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