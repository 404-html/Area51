<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User Edit</title>
</head>
<body>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Noliac Form</title>
<link rel="stylesheet" type="text/css" href="view.css" media="all" />
<script type="text/javascript" src="view.js"></script>
<script type="text/javascript" src="calendar.js"></script>
</head>
<body id="main-body">

	<jsp:useBean id="database" class="javaMeasure.control.DataBaseController" scope="session" />

	<div id="wrapper">

		<div id="header">
			<img src="noliac_logo.png" alt="Logo">
		</div>

		<div id="form_container">

			<form id="user_edit" class="appnitro" method="post">
				<div class="form_description">
					<h1>Noliac User Edit</h1>
					
				</div>
				
				
	<table width="100%" border="0" cellpadding="2" cellspacing="0">
		<tr>
			<td>
				<ul>
					<li id="li_3"><label class="description" for="username" >User
							name</label>
						<div>
							<input id="username" name="username" class="element text medium"
								type="text" maxlength="255" value="guest" />
						</div>

						
					<li id="li_"><label class="description" for="element_4">Password</label>
						<div>
							<input id="element_4" name="pass" class="element text medium"
								type="text" maxlength="255" value="123456" />
						  <input type="hidden" name="form_id"
						value="812583" />
				  </div>                    
				</ul>
          </td>
		  <td width="50%">
                            <input type="checkbox" name="active" value="active"> active<BR>
		  <input type="checkbox" name="super" value="super"> super<BR></tr>
		  </table>
				<form ACTION="jspCheckBox.jsp">
       <table width="100%" border="0" cellpadding="2" cellspacing="0">
		<tr>
			<td>
				  <span class="buttons">
				  <input id="saveForm" class="button_text"
						type="submit" name="save" value="save" />
                         </td>
				  </span>
                  <td width="95%"><span class="buttons">
                
				  <input id="cancel" class="button_text"
						type="submit" name="cancel" value="cancel" />
				  </span>
                  </table>
				</form>
				
				
				
			</form>
			<div id="footer">
				By Area51
				<!--Generated by <a href="http://www.phpform.org">pForm</a>-->
			</div>

		</div>
	</div>
</body>
</html>