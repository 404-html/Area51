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
		//skriver ud i div elementet der hedder return_data
		$("#return_data").html("<b>Hello world! "+$("#input_text").val() + "</b>");
	});

</script>
</head>
<body id="main-body">
	<div id="wrapper">
		<p>Test af ajax</p>
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

		</div>
		<div id="return_data">
		
		</div>
	</div>
</body>
</html>