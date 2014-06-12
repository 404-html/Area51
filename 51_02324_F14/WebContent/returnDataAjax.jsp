<%@page import="java.util.ArrayList"%>
<%@page import="javaMeasure.control.interfaces.IDatabaseController.DataBaseException"%>
<%@page import="javaMeasure.control.MainController"%>
<%@page import="javaMeasure.control.DataBaseController"%>
<%@page import="javaMeasure.control.SQLConnector"%>
<%@page import="javaMeasure.BatchDAO"%>
<%@page import="javaMeasure.Batch"%>
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

	String createTableRow(Batch batch)
	{
		String returnString = "";

		//table row
		returnString = returnString + ("<tr class=\"returnRow\" id=\"" + batch.getBatchString() + "\">\n");
		
		//batch navn
		returnString = returnString + "\t<td>" + batch.getBatchString() + "</td>\n"; 
		//created by
		returnString = returnString + "\t<td>" + batch.getCreated_by() + "</td>\n"; 
		//created date
		returnString = returnString + "\t<td>&nbsp</td>\n"; 
		//returnString = returnString + "\t<td>" + batch.getDateAsString(batch.getCreated_date()) + "</td>\n"; 
		//approved by
		
		returnString = returnString + "\t<td>" + batch.getApproved_by() + "</td>\n";  	
		//approved date
		returnString = returnString + "\t<td>&nbsp</td>\n"; 
		//returnString = returnString + "\t<td>" + batch.getDateAsString(batch.getApproved_date()) + "</td>\n";
		
		//end table row
		returnString = returnString + "</tr>";
		
		return returnString;
	}

%>

<%
String input = request.getParameter("input");
System.out.println("input:" +input);
BatchDAO b = new BatchDAO(new SQLConnector());



//out.println("test");
	out.println("<table class=\"returnTable\">");
	out.println("	<th class=\"None\">Batch navn</th>");
	out.println("	<th class=\"None\">Oprettet af</th>");
	out.println("	<th class=\"None\">Oprettet dato</th>");
	out.println("	<th class=\"None\">Godkendt af</th>");
	out.println("	<th class=\"None\">Godkendt dato</th>");

	for(Batch b2 : b.getBatches(input)){
		System.out.println("for loop");
		out.println(createTableRow(b2));
	}
	//out.println("<tr>");
// 	out.println("<tr class=\"returnRow\">");
// 	out.print(createTableRow(5,1));
// 	out.println("</tr>");
// 	out.println("<tr class=\"returnRow\">");
// 	out.print(createTableRow(5,2));
// 	out.println("</tr>");
// 	out.println("<tr class=\"returnRow\">");
// 	out.print(createTableRow(5,3));
// 	out.println("</tr>");
 	out.println("</table>");
%>