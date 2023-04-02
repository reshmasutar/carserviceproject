<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<% 
		String ctype = request.getParameter("cab");
		String meter = request.getParameter("kmeter");
		String mini1="mini";
		String prime1="prime";
		String micro1="micro";
		double amt=0.0;
		
		//parsing 
		double kmeter = Double.parseDouble(meter);		

			if(ctype.equals(mini1)){
				amt = kmeter*10+0.05;		 
			}else if(ctype.equals(prime1)){
				amt = kmeter*10+0.010;	
			}else if(ctype.equals(micro1)){
				amt = kmeter*10+0.08;	
			}		
			
	%>
	<h1>Cab Booked Successfully</h1>
	<table border='2px'><tr><th>CAB_TYPE</th><th>DISTANCE</th><th>FAIR_AMT</th>
	<tr>
	<td><%=ctype %></td>
	<td><%=meter %>km</td>
	<td><%=amt %></td></tr></table>
	
	
	
</body>
</html>