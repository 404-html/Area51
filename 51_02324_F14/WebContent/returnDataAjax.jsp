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

		String created_by = (batch.getCreated_by()=="null" ? batch.getCreated_by() : "&nbsp");
		String approved_by = (batch.getApproved_by()=="null" ? batch.getApproved_by() : "&nbsp");
		//table row  class=\"returnRow\"
		returnString = returnString + ("<tr>\n");
		
		//batch navn
		returnString = returnString + "\t<td id=\"" + batch.getBatchString() + "\">" + batch.getBatchString() + "</td>\n"; 
		//created by
		returnString = returnString + "\t<td id=\"" + batch.getBatchString() + "\">" + created_by	+ "</td>\n"; 
		//created date
		returnString = returnString + "\t<td id=\"" + batch.getBatchString() + "\">&nbsp</td>\n"; 
		//returnString = returnString + "\t<td>" + batch.getDateAsString(batch.getCreated_date()) + "</td>\n"; 
		//approved by
		
		returnString = returnString + "\t<td id=\"" + batch.getBatchString() + "\">" + approved_by + "</td>\n";  	
		//approved date
		returnString = returnString + "\t<td id=\"" + batch.getBatchString() + "\">&nbsp</td>\n"; 
		//returnString = returnString + "\t<td>" + batch.getDateAsString(batch.getApproved_date()) + "</td>\n";
		
		//end table row
		returnString = returnString + "</tr>";
		
		return returnString;
	}

%>

<%
String input = request.getParameter("input");
BatchDAO b = new BatchDAO(new SQLConnector());



//out.println("test");
	out.println("<div  class=\"tableContainer\">");
	out.println("	<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" class=\"scrollTable\">");
	out.println("		<thead class=\"fixedHeader\">");
	out.println("			<tr>");
	out.println("				<th><a href=\"#\">Batch navn</a></th>");
	out.println("				<th><a href=\"#\">Oprettet af</a></th>");
	out.println("				<th><a href=\"#\">Oprettet dato</a></th>");	
	out.println("				<th><a href=\"#\">Godkendt af</a></th>");
	out.println("				<th><a href=\"#\">Godkendt dato</a></th>");	
	out.println("			</tr>");
	out.println("		</thead>");
	out.println("		<tbody class=\"scrollContent\">");

	for(Batch b2 : b.getBatches(input)){
		out.println(createTableRow(b2));
	}	
	
	out.println("		</tbody>");
	//out.println("	<th class=\"None\">&nbsp&nbspBatch navn&nbsp&nbsp</th>");
	//out.println("	<th class=\"None\">&nbsp&nbspOprettet af&nbsp&nbsp</th>");
	//out.println("	<th class=\"None\">&nbsp&nbspOprettet dato&nbsp&nbsp</th>");
	//out.println("	<th class=\"None\">&nbsp&nbspGodkendt af&nbsp&nbsp</th>");
	//out.println("	<th class=\"None\">&nbsp&nbspGodkendt dato&nbsp&nbsp</th>");


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
 	out.println("	</table>");
 	out.println("</div>");
%>