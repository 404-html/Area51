<%@page import="javaMeasure.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Noliac Form</title>
<link rel="stylesheet" type="text/css" href="view.css" media="all" />
<script type="text/javascript" src="view.js"></script>
<script type="text/javascript" src="calendar.js"></script>
</head>
<body id="main-body">

	<div id="wrapper">

		<div id="header">
			<img src="noliac_logo.png" alt="Logo">
		</div>

		<div id="form_container">

			<form id="login_form" class="appnitro" method="post" action="LoginServlet">
				<div class="form_description">
					<h1>Noliac User login</h1>

					<p>Indtast Brugernavn og password</p>
					<%
						if (request.getAttribute("loginFail") != null) {
							out.println(" <p>Forkert brugernavn eller adgangskode! Prøv igen.</p>");
						}
					%>
				</div>
				<ul>
					<li id="li_3"><label class="description" for="username" >User
							name</label>
						<div>
							<input id="username" name="username" class="element text medium"
								type="text" maxlength="255" value="guest" />
						</div>
						<p class="guidelines" id="guide_3">
							<small>Indtast brugernavn</small>
						</p></li>
					<li id="li_"><label class="description" for="element_4">Password</label>
						<div>
							<input id="password" name="password" type="password" class="element text medium"
								type="text" maxlength="255" value="123456" />
						</div>
						<p class="guidelines" id="guide_">
							<small>Indtast kode</small>
						</p></li>
					<li class="buttons">
					<input type="hidden" name="cmd"
						value="tryLogin" /> 
						<input id="saveForm" class="button_text"
						type="submit" name="login" value="Login" /></li>
				</ul>
			</form>
			<div id="footer">
				By Area51
			</div>

		</div>
	</div>
</body>
</html>

