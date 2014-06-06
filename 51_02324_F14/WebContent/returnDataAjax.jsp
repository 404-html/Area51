<%@page import="java.util.ArrayList"%>
<%@page import="javaMeasure.control.interfaces.IDatabaseController.DataBaseException"%>
<%@page import="javaMeasure.control.MainController"%>
<%@page import="javaMeasure.control.DataBaseController"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
String username = (String) session.getAttribute("username");

//Loads databasecontroller for session
	DataBaseController database = (DataBaseController) session.getAttribute("database");
	javaMeasure.Batch batch = null;
	if (username == null) 
	{
		response.sendRedirect("userlogin.jsp");
	} 
	else 
	{
		// Checks if request parameters are set
		String batchname = request.getParameter("batchname");
		if(batchname == ""){
			//response
		}
	}



%>