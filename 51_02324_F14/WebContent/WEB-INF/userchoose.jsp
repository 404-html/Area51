<%@page import="java.util.ArrayList"%>
<%@page
	import="javaMeasure.control.interfaces.IDatabaseController.DataBaseException"%>
<%@page import="javaMeasure.control.MainController"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="javaMeasure.control.DataBaseController"%>

<%@page import="javaMeasure.User"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;  charset=UTF-8">
<%
	String username = (String) session.getAttribute("username");
%>
<title>User Edit</title>
</head>

<title>Noliac : <%
	out.println(username);
%> | udtræk
</title>
					
<link rel="stylesheet" type="text/css" href="view.css" media="all" />
<script type="text/javascript" src="view.js"></script>
<script type="text/javascript" src="calendar.js"></script>
</head>
<body id="main-body">
				<%
						if (request.getAttribute("usernotfound") != null) {
							out.println(" <p>Finder ikke valgte bruger.</p>");
						}
					%>
	<jsp:useBean id="database" class="javaMeasure.control.DataBaseController" scope="session" />

	<div id="wrapper">

		<div id="header">
			<img src="noliac_logo.png" alt="Logo">
		</div>

		<div id="form_container">

			<form id="user_edit" class="appnitro" method="post" action=UserChooseServlet>
				<div class="form_description">
					<h1>Choose user</h1>
					
				
				
				<ul>
				<%
						if (request.getAttribute("usernotfound") != null) {
							out.println(" <p>Finder ikke valgte bruger.</p>");
						}
					%>
					<li id="li_3"><label class="description" for="username" >
					 <% //Checks if User was found
											if (request.getParameter("fail") != null) out.print("Bruger ikke genkendt! -");
											%>User name</label>
						<div>
							<input id="chosen" name="chosen" class="element text medium"
								type="text" maxlength="255" 
								list="batches" autocomplete="on" value="" />
										<datalist id="batches"> <%
 						ArrayList<User> userNames = database.getUserList();
 						for (User u : userNames){
 							String name=u.getUserName();
 							%>
 
										<option>
											<%=name%></option>
										<%
											}
										%> </datalist>
						</div>
</li>

						
		  </ul>
		    <p><BR>
		    
	        </p>	
		 

				

                  
                  
                  <span class="buttons">
                  <input id="Choose" class="button_text"
						type="submit" name="Choose" value="Choose" />
                  <input id="Choose" class="button_text"
						type="submit" name="Done" value="Done" />
                  </span>
                  
		</div>
		</form>
		
			<div id="footer">
				By Area51
				<!--Generated by <a href="http://www.phpform.org">pForm</a>-->
			</div>

		</div>
	</div>
</body>
</html>