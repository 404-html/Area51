<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="javaMeasure.BatchSetting"%>
<%@page import="javaMeasure.Batch"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title>Noliac Form</title>
<link rel="stylesheet" type="text/css" href="view.css" media="all"/>
<script type="text/javascript" src="view.js"></script>
<script type="text/javascript" src="calendar.js"></script>
</head>
<%
	javaMeasure.Batch batch = (javaMeasure.Batch) session.getAttribute("batch");
	javaMeasure.BatchProfile profile = (javaMeasure.BatchProfile) session.getAttribute("profile");
%>

<body id="main-body">
	<div id="wrapper">
	
	<div id="header"><img src="noliac_logo.png" alt="Logo"></div>
	
	<div id="form_container">
	
		<!--<h1><a>Noliac</a></h1>-->
	  <form id="form_812583" class="appnitro"  method="post" action="">
		<div class="form_description">
			<h1>Noliac Batch report</h1>
		</div>						
		  <ul ><li id="li_2" name="li_2" >
		    <table width="100%" border="1">
		      <tr>
		        <td colspan="5"><h3>Final Inspection and Test Report</h3></td>
		        <td>&nbsp;</td>
		        <td>&nbsp;</td>
		        <td>&nbsp;</td>
		        <td>&nbsp;</td>
	          </tr>
		      <tr>
		        <td>Parameter</td>
		        <td colspan="3">Specification</td>
		        <td>Inspection level</td>
		        <td colspan="3">Measured</td>
		        <td>&nbsp;</td>
	          </tr>
		      <tr>
		        <td>&nbsp;</td>
		        <td>Norm value</td>
		        <td>Min</td>
		        <td>Max</td>
		        <td>&nbsp;</td>
		        <td>Average</td>
		        <td>Min</td>
		        <td>Max</td>
		        <td>&nbsp;</td>
	          </tr>
		      <tr>
		        <td>Length (mm):</td>
		        <td> <% out.println(profile.getProfileSettings().get(0).getValue()); %> </td>
		        <td> <% out.println(profile.getProfileSettings().get(1).getValue()); %> </td>
		        <td> <% out.println(profile.getProfileSettings().get(2).getValue()); %> </td>
		        <td> <% out.println(profile.getProfileSettings().get(3).getValue()); %> </td>
		        <td> <% out.println(profile.getProfileSettings().get(4).getValue()); %> </td>
		        <td> <% out.println(profile.getProfileSettings().get(5).getValue()); %> </td>
		        <td> <% out.println(profile.getProfileSettings().get(6).getValue()); %> </td>
		        <td>&nbsp;</td>
	          </tr>
		      <tr>
		        <td>Width (mm):</td>
		        <td>5,0</td>
		        <td>4,9</td>
		        <td>5,1</td>
		        <td>Min. 5</td>
		        <td>5,01</td>
		        <td>5,00</td>
		        <td>5,03</td>
		        <td>&nbsp;</td>
	          </tr>
		      <tr>
		        <td>Thickness (mm):</td>
		        <td>2,00</td>
		        <td>1,95</td>
		        <td>2,05</td>
		        <td>Min. 5</td>
		        <td>2,00</td>
		        <td>2,00</td>
		        <td>2,01</td>
		        <td>&nbsp;</td>
	          </tr>
		      <tr>
		        <td>Capacitance (nF):</td>
		        <td>107</td>
		        <td>86</td>
		        <td>128</td>
		        <td>Min. 10</td>
		        <td>113</td>
		        <td>102</td>
		        <td>120</td>
		        <td>&nbsp;</td>
	          </tr>
		      <tr>
		        <td>Stroke (&mu;m):</td>
		        <td>3,10</td>
		        <td>2,64</td>
		        <td>3,56</td>
		        <td>AQL 0.65</td>
		        <td>2,94</td>
		        <td>2,69</td>
		        <td>3,09</td>
		        <td>&nbsp;</td>
	          </tr>
		      <tr>
		        <td>Leakage current:</td>
		        <td>&nbsp;</td>
		        <td>&nbsp;</td>
		        <td>&nbsp;</td>
		        <td>Min. 20</td>
		        <td>&nbsp;</td>
		        <td>&nbsp;</td>
		        <td>&nbsp;</td>
		        <td>&nbsp;</td>
	          </tr>
		      <tr>
		        <td>Visual inspection:</td>
		        <td>&nbsp;</td>
		        <td>&nbsp;</td>
		        <td>&nbsp;</td>
		        <td>AQL 0.65</td>
		        <td>&nbsp;</td>
		        <td>&nbsp;</td>
		        <td>&nbsp;</td>
		        <td>&nbsp;</td>
	          </tr>
		      <tr>
		        <td>&nbsp;</td>
		        <td>&nbsp;</td>
		        <td>&nbsp;</td>
		        <td>&nbsp;</td>
		        <td>&nbsp;</td>
		        <td>&nbsp;</td>
		        <td>&nbsp;</td>
		        <td>&nbsp;</td>
		        <td>&nbsp;</td>
	          </tr>
		      <tr>
		        <td>&nbsp;</td>
		        <td>&nbsp;</td>
		        <td>&nbsp;</td>
		        <td>&nbsp;</td>
		        <td>&nbsp;</td>
		        <td>&nbsp;</td>
		        <td>&nbsp;</td>
		        <td>&nbsp;</td>
		        <td>&nbsp;</td>
	          </tr>
		      <tr>
		        <td>&nbsp;</td>
		        <td>&nbsp;</td>
		        <td>&nbsp;</td>
		        <td>&nbsp;</td>
		        <td>&nbsp;</td>
		        <td>&nbsp;</td>
		        <td>&nbsp;</td>
		        <td>&nbsp;</td>
		        <td>&nbsp;</td>
	          </tr>
		      <tr>
		        <td>&nbsp;</td>
		        <td>&nbsp;</td>
		        <td>&nbsp;</td>
		        <td>&nbsp;</td>
		        <td>&nbsp;</td>
		        <td>&nbsp;</td>
		        <td>&nbsp;</td>
		        <td>&nbsp;</td>
		        <td>&nbsp;</td>
	          </tr>
		      <tr>
		        <td>&nbsp;</td>
		        <td>&nbsp;</td>
		        <td>&nbsp;</td>
		        <td>&nbsp;</td>
		        <td>&nbsp;</td>
		        <td>&nbsp;</td>
		        <td>&nbsp;</td>
		        <td>&nbsp;</td>
		        <td>&nbsp;</td>
	          </tr>
		      <tr>
		        <td>&nbsp;</td>
		        <td>&nbsp;</td>
		        <td>&nbsp;</td>
		        <td>&nbsp;</td>
		        <td>&nbsp;</td>
		        <td>&nbsp;</td>
		        <td>&nbsp;</td>
		        <td>&nbsp;</td>
		        <td>&nbsp;</td>
	          </tr>
		      <tr>
		        <td>&nbsp;</td>
		        <td>&nbsp;</td>
		        <td>&nbsp;</td>
		        <td>&nbsp;</td>
		        <td>&nbsp;</td>
		        <td>&nbsp;</td>
		        <td>&nbsp;</td>
		        <td>&nbsp;</td>
		        <td>&nbsp;</td>
	          </tr>
	        </table>
		  </li>
			
				  <li class="buttons">
			    <input type="hidden" name="form_id" value="812583" />
			    
				<input id="saveForm" class="button_text" type="submit" name="submit" value="Ny rapport" />
				<input id="saveAsExcel" class="button_text" type="submit" name="saveForm" value="Gem som Excel-fil" />
				  </li>
		  </ul>
		</form>	
		<div id="footer">
			By Area51<!--Generated by <a href="http://www.phpform.org">pForm</a>-->
		</div>
	
	</div>
	</div>
	</body>
</html>