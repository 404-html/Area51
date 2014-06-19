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
<link rel="stylesheet" type="text/css" href="menu.css" media="all" />
<script type="text/javascript" src="view.js"></script>
<script type="text/javascript" src="calendar.js"></script>

<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script>
	//AJAX
	$(document).on('keyup', '#element_1_1, #element_1_2, #element_1_3, #element_2_1, #element_2_2, #element_2_3', function() {
		//henter siden returnDataAjax 
		search();
	});

	//lytter på keyup event i elementet med id = batchname
	$(document).on('keyup', '#batchname', function() {
		//henter siden returnDataAjax 
		if($("#batchname").val().length>=1){
			search();
		}
	});
	
	//click event er affyres, når bruger klikker på en række i tabellen
	$(document).on('click', '.scrollContent tr', function(event) {
		//det skjulte form element "batchNameSubmit" sættes til batchnavnet på den række man har klikket på
		$('input[name="batchNameSubmit"]').val(event.target.id);
		document.batchform.submit();
	});

	//udfører søgningen og sætter det skjulte element "batchNameSubmit" til den første rækkes ID
	function search(){
		$.get(getFormString(),function(data,status){
		    if(status = "success"){
		    	//indsæt de hentede data i div element
		    	$("#return_data").html(data);
		    	var batch = $(".scrollContent tr:first").children('td:first').attr('id');
		    	if(batch!=""){
		    		$('input[name="batchNameSubmit"]').val(batch);
		    	}
		    }
		});
	}	
	
	
	//sørger for at fange enter-tast - submitter form med den første række der er i tabellen
	$(document).keypress( function(e) {
        var key = e.charCode ? e.charCode : e.keyCode ? e.keyCode : 0;
        if(key == 13) {
            e.preventDefault();
            if($('input[name="batchNameSubmit"]').val()!=""){
            	document.batchform.submit();
            }
        }
    });
	
	//laver den streng der skal bruges til GET request
	function getFormString(){
		return "returnDataAjax.jsp?input="+$("#batchname").val()+ "&fieldName="+ getFieldName() + "&startDate="+getFormDate(true)+"&endDate="+getFormDate(false);
	}
	
	//finder ud af hvilken radiobutton der er valgt
	function getFieldName(){
		return $('input[name="rdoCreatedApproved"]:checked').val();
	}
	

	
	//Date functions for input fields
	function getFormDate(getStartDate){
		if(getStartDate){
			var dd = document.getElementById('element_1_2').value;
			var mm = document.getElementById('element_1_1').value;
			var yyyy = document.getElementById('element_1_3').value;
		}
		else{
			var dd = document.getElementById('element_2_2').value;
			var mm = document.getElementById('element_2_1').value;
			var yyyy = document.getElementById('element_2_3').value;
		}
		if(dd && mm && yyyy){
			return yyyy + "-" + mm + "-" + dd;
		}
		else{
			return "";
		}
	}
	
	function fillToday(){
		var today = new Date();
		var dd = today.getDate();
		var mm = today.getMonth()+1; //January is 0!
		var yyyy = today.getFullYear();

		fillElement(today, today);
	};
	
	function fillYesterday(){
		var today = new Date();
		console.log(today.getDay());
		if(today.getDay() == 1){
			today.setDate(today.getDate() - 3); //if monday, get last friday
		}
		else{
			today.setDate(today.getDate() - 1);
		}
		
		var dd = today.getDate();
		var mm = today.getMonth()+1; //January is 0!
		var yyyy = today.getFullYear();

		fillElement(today, today);
	};
	
	function fillLastWeek(){
		var today = new Date();
		var startday = new Date();
		var endday = new Date();

		startday.setDate(today.getDate() - today.getDay() -6); //get last monday
		endday.setDate(startday.getDate()+4); //get last friday
		
		fillElement(startday, endday);
	};
	
	function fillThisWeek(){
		var today = new Date();
		var startday = new Date();

		startday.setDate(today.getDate() - today.getDay()+1); //get monday in this week
		
		fillElement(startday, today);
	};
	
	function fillElement(startdate, enddate){
		document.getElementById('element_1_2').value = startdate.getDate();
		document.getElementById('element_1_1').value = startdate.getMonth()+1; //January is 0!
		document.getElementById('element_1_3').value = startdate.getFullYear();
		
		document.getElementById('element_2_2').value = enddate.getDate();
		document.getElementById('element_2_1').value = enddate.getMonth()+1; //January is 0!
		document.getElementById('element_2_3').value = enddate.getFullYear();
		
		search();
	}
	
	//input restriction
	var digitsOnly = /[1234567890]/g;
	var integerOnly = /[0-9\.]/g;
	var alphaOnly = /[A-Za-z]/g;

	function restrictCharacters(myfield, e, restrictionType) {
		if (!e) var e = window.event
		if (e.keyCode) code = e.keyCode;
		else if (e.which) code = e.which;
		var character = String.fromCharCode(code);
	
		// if they pressed esc... remove focus from field...
		if (code==27) { this.blur(); return false; }
		
		// ignore if they are press other keys
		// strange because code: 39 is the down key AND ' key...
		// and DEL also equals .
		if (!e.ctrlKey && code!=9 && code!=8 && code!=36 && code!=37 && code!=38 && (code!=39 || (code==39 && character=="'")) && code!=40) {
			if (character.match(restrictionType)) {
				return true;
			} else {
				return false;
			}
		}
	}

	function test(){
		alert($('input[name="batchNameSubmit"]').val());
	}
</script>




</head>
<body id="main-body">
	<div id="wrapper">
		<!-- wrapper for whole page  -->
		<div id="header">
			<a href="http://localhost:8080/51_02324_F14/"><img src="noliac_logo.png" alt="Logo"></a>
		</div>
		<!-- Form begins-->
		<div id="form_container">
			<form id="batchform" name="batchform" class="appnitro" method="post" action="MenuServlet">
				<div class="form_description">
					<h1>Noliac Batch-udtræk</h1>
					<p>Indtast Batch ID eller vælg Batch ID fra rullemenu</p>
				</div>

				<!-- Input Fields contained in table-->
				<table width="100%" border="0">
					<tr>
						<td>
							<ul>
								<li id="li_3" name="li_3">
									<label class="description" for="batchname"> 
									<% //Checks if Batch was found
										if (request.getParameter("fail") != null) out.print("Batch navn ikke genkendt! -");
									%> Indtast Batch navn
									</label>
									<div>
										<input id="batchname" name="batchname" class="element text medium" type="text" maxlength="255"
											list="batches" autocomplete="on" value="" />
										<datalist id="batches"> 
										<% //TODO replace with AJAX call
 											ArrayList<String> batchNames = ((DataBaseController) request.getSession().getAttribute("database")).getBatchNames();

 											for (String batchName : batchNames){
 										%>
											<option>
												<%=batchName%>
											</option>
										<%
											}
										%> 
										</datalist>
									</div>
								</li>
								<li>
									<label class="description" for="rdoCreatedApproved">Dato:</label>
										<span class="radio2">
											<input id="rdoCreated" name="rdoCreatedApproved" type="radio" value="created_date" checked>Oprettet<input id="rdoApproved" name="rdoCreatedApproved" type="radio" value="approved_date">Godkendt
										</span>
								</li>
								<li id="li_1">
									<label class="description" for="element_1">Start dato</label> 
									<span> 
										<input id="element_1_2" name="element_1_2" class="element text" size="2" maxlength="2" value="" type="text" onkeypress="return restrictCharacters(this, event, digitsOnly);"> / <label for="element_1_2">DD</label>
									</span> 
									<span>
										<input id="element_1_1" name="element_1_1" class="element text" size="2" maxlength="2" value="" type="text" onkeypress="return restrictCharacters(this, event, digitsOnly);"> / <label for="element_1_1">MM</label>
									</span>
									<span>
										<input id="element_1_3" name="element_1_3" class="element text" size="4" maxlength="4" value="" type="text" onkeypress="return restrictCharacters(this, event, digitsOnly);"> <label for="element_1_3">YYYY</label>
									</span>
									<span id="calendar_1">
										<img id="cal_img_1" class="datepicker" src="calendar.gif" alt="Pick a date." />
									</span> 
									<script type="text/javascript">
										Calendar.setup({
											inputField : "element_1_3",
											baseField : "element_1",
											displayArea : "calendar_1",
											button : "cal_img_1",
											ifFormat : "%B %e, %Y",
											onSelect : selectDate
										});
									</script>
								</li>
								<li id="li_2" name="li_2">
									<label class="description" for="element_2">Slut dato </label> 
									<span>
										<input id="element_2_2" name="element_2_2" class="element text" size="2" maxlength="2" value="" type="text" onkeypress="return restrictCharacters(this, event, digitsOnly);"> / <label for="element_2_2">DD</label>
									</span> 
									<span> 
										<input id="element_2_1" name="element_2_1" class="element text" size="2" maxlength="2" value=""	type="text" onkeypress="return restrictCharacters(this, event, digitsOnly);"> / <label for="element_2_1">MM</label>
									</span>
									<span>
										<input id="element_2_3" name="element_2_3" class="element text" size="4" maxlength="4" value=""	type="text" onkeypress="return restrictCharacters(this, event, digitsOnly);"> <label for="element_2_3">YYYY</label>
									</span>
									<span id="calendar_2"> 
										<img id="cal_img_2"	class="datepicker" src="calendar.gif" alt="Pick a date.">
									</span>
									<script type="text/javascript">
									Calendar.setup({
										inputField : "element_2_3",
										baseField : "element_2",
										displayArea : "calendar_2",
										button : "cal_img_2",
										ifFormat : "%B %e, %Y",
										onSelect : selectDate
									});
								</script>
								</li>
								<li>
									<button class="text" type="button" onClick="fillToday()">I dag</button>
									<button class="text" type="button" onClick="fillThisWeek()">Denne uge</button>
									<button class="text" type="button" onClick="fillYesterday()">I går</button>
									<button class="text" type="button" onClick="fillLastWeek()">Sidste uge</button>
								</li>
							</ul>
						</td>
						<td width="50%" id="return_data">
							<div  class="tableContainer">
								<table border="0" cellpadding="0" cellspacing="0" width="100%" class="scrollTable">
									<thead class="fixedHeader">
										<tr>
											<th><a href="#">Batch navn</a></th>
											<th><a href="#">Oprettet af</a></th>
											<th><a href="#">Oprettet dato</a></th>
											<th><a href="#">Godkendt af</a></th>
											<th><a href="#">Godkendt dato</a></th>
										</tr>
									</thead>
									<tbody class="scrollContent">
									</tbody>
								</table>
							</div>
						
						
						</td>
					</tr>
				</table>


 			    <input type="hidden" name="batchNameSubmit" id="batchNameSubmit" value="" />
		
			    <button id="btnSearch" class="text" type="button" name="btnSearch" value="Søg" onClick="search()">Søg</button>
			  <!--  <button id="testsdf" class="text" type="button" name="testsdf" value="testsd" onClick="test()">test</button> -->
				<input id="logout" class="button_text" type="submit" name="logout" value="logout" /> 
					
				<input id="edit" class="button_text" type="submit" name="edit" value="Edit Users" /> 
		</form>
			<div id="footer">By Area51</div>

		</div>
	</div>
</body>
</html>
