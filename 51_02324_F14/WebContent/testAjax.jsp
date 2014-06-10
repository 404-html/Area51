<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Test ajax</title>
<link rel="stylesheet" type="text/css" href="view.css" media="all"/>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script>
	$(document).on('keyup', '#input_text', function() {
		//skriver det indtastede ud i div elementet der hedder test
		$("#test").html($("#input_text").val());
		
		//henter siden returnDataAjax 
		$.get("returnDataAjax.jsp",function(data,status){
		    //alert("Data: " + data + "\nStatus: " + status);
		    if(status = "success")
		    {
		    	//indsæt de hentede data i div element
		    	$("#return_data").html(data);
		    }
		});
		
	});
	
	$(document).on('click', 'tr.returnRow', function(event) {
		alert(event.target.id);
		console.log("click");
		//$(this).not(':first-child').css("background","green");
		//skriver det indtastede ud i div elementet der hedder test
		//$("#test").html($("#input_text").val());
		
		//$("#return_data").html("<b>Hello world! "+$("#input_text").val() + "</b>");
		
		//henter siden returnDataAjax 
/* 		$.get("returnDataAjax.jsp",function(data,status){
		    //alert("Data: " + data + "\nStatus: " + status);
		    if(status = "success")
		    {
		    	//indsæt de hentede data i div element
		    	$("#return_data").html(data);
		    }
		});
		 */
	});

</script>

</head>
<body id="main-body">
	<div id="wrapper">
		<p id="test">Test af ajax</p>
		<div id="form_container">
			<form id="formGetAjax" class="appnitro"  method="post" action="">
			<ul>
				<li id="li_3">
					<label for="input_text" class="description">Indtast batch id</label>
				</li>
				<li>
					<input id="input_text" name="input_text" class="element text medium" type="text" maxlength="255" value=""/>
				</li>
	
			</ul>
			</form>
		<p>
		</div>
		<div id="return_data">
		
		</div>
	</div>
</body>
</html>