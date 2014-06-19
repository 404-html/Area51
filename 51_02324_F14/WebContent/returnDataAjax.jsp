<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Date"%>
<%@page import="java.sql.Timestamp"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="javaMeasure.control.interfaces.IDatabaseController.DataBaseException"%>
<%@page import="javaMeasure.control.MainController"%>
<%@page import="javaMeasure.control.DataBaseController"%>
<%@page import="javaMeasure.control.SQLConnector"%>
<%@page import="javaMeasure.BatchDAO"%>
<%@page import="javaMeasure.Batch"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<!-- //user validation. Disabled for test -->
<!-- String username = (String) session.getAttribute("username"); -->

<!-- //Loads databasecontroller for session -->

<!-- 	DataBaseController database = (DataBaseController) session.getAttribute("database"); -->
<!-- 	javaMeasure.Batch batch = null; -->
<!-- 	if (username == null)  -->
<!-- 	{ -->
<!-- 		response.sendRedirect("userlogin.jsp"); -->
<!-- 	}  -->
<!-- 	else  -->
<!-- 	{ -->
<!-- 		// Checks if request parameters are set -->
<!-- 		String batchname = request.getParameter("batchname"); -->
<!-- 		if(batchname == ""){ -->
<!-- 			//response -->
<!-- 		} -->
<!-- 	} -->




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

		String created_by = (batch.getCreated_by()!=null ? batch.getCreated_by() : "&nbsp");
		String approved_by = (batch.getApproved_by()!=null ? batch.getApproved_by() : "&nbsp");
		//table row  class=\"returnRow\"
		returnString = returnString + ("<tr>\n");
		
		//batch navn
		returnString = returnString + "\t<td id=\"" + batch.getBatchString() + "\">" + batch.getBatchString() + "</td>\n"; 
		//created by
		returnString = returnString + "\t<td id=\"" + batch.getBatchString() + "\">" + created_by	+ "</td>\n"; 
		//created date
		//returnString = returnString + "\t<td id=\"" + batch.getBatchString() + "\">&nbsp</td>\n"; 
		returnString = returnString + "\t<td>" + batch.getDateAsString(batch.getCreated_date()) + "</td>\n"; 
		//approved by
		
		returnString = returnString + "\t<td id=\"" + batch.getBatchString() + "\">" + approved_by + "</td>\n";  	
		//approved date
		//returnString = returnString + "\t<td id=\"" + batch.getBatchString() + "\">&nbsp</td>\n"; 
		returnString = returnString + "\t<td>" + batch.getDateAsString(batch.getApproved_date()) + "</td>\n";
		
		//end table row
		returnString = returnString + "</tr>";
		
		return returnString;
	}

%>

<%
	String input = request.getParameter("input");
	String startDate = request.getParameter("startDate");
	String endDate = request.getParameter("endDate");
	String fieldName = request.getParameter("fieldName");
	
	Timestamp startDateTS = null;
	Timestamp endDateTS = null;
	//BatchDAO b = new BatchDAO(new SQLConnector());
	DataBaseController dbctrl = (DataBaseController) request.getSession().getAttribute("database");

	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//hh:mm:ss.SSS
	//tester om datoer er indtastet korrekt
	try{
		Date parsedDate = dateFormat.parse(startDate);
		startDateTS = new Timestamp(parsedDate.getTime());
	}catch(Exception e){//hvis dato ikke var korrekt, timestamp = null
		startDateTS = null;
	}
	
	try{
		Date parsedDate = dateFormat.parse(endDate);
		endDateTS = new Timestamp(parsedDate.getTime());
	}catch(Exception e){//hvis dato ikke var korrekt, timestamp = null  + 86399000
		endDateTS = null;
	}
	
	if(!(fieldName.equalsIgnoreCase("created_date") || fieldName.equalsIgnoreCase("approved_date"))){ 
		fieldName = null;
	}
	
	//print table header
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

	//print table rows

	
	for(Batch b2 : dbctrl.getBatches(input, fieldName,startDateTS,endDateTS)){
		out.println(createTableRow(b2));
	}	
	
	//end table
	out.println("		</tbody>");
 	out.println("	</table>");
 	out.println("</div>");
%>