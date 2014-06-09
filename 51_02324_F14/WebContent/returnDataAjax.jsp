<%@page import="java.util.ArrayList"%>
<%@page import="javaMeasure.control.interfaces.IDatabaseController.DataBaseException"%>
<%@page import="javaMeasure.control.MainController"%>
<%@page import="javaMeasure.control.DataBaseController"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%-- //user validation. Disabled for test
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



--%>
<%! //methods
	String createTableRow(int numCols, int rowNum)
	{
		String returnString = "";
		for(int i = 0;i<numCols;i++)
		{
			returnString = returnString + "\t<td id=\"tab" + rowNum + "\">" + "row" + rowNum + "|col" + (i+1) + "</td>\n"; 	
		}
		return returnString;
	}

//response.write

%>

<%
//out.println("test");
	out.println("<table class=\"returnTable\">");
	out.println("	<th class=\"None\">heading1</th>");
	out.println("	<th class=\"None\">heading2</th>");
	out.println("	<th class=\"None\">heading3</th>");
	out.println("	<th class=\"None\">heading4</th>");
	out.println("	<th class=\"None\">heading5</th>");

	//out.println("<tr>");
	out.println("<tr class=\"returnRow\">");
	out.print(createTableRow(5,1));
	out.println("</tr>");
	out.println("<tr class=\"returnRow\">");
	out.print(createTableRow(5,2));
	out.println("</tr>");
	out.println("<tr class=\"returnRow\">");
	out.print(createTableRow(5,3));
	out.println("</tr>");
	out.println("</table>");
%>