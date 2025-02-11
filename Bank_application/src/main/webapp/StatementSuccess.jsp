<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Success Page</title>
<link rel="stylesheet" href="arjun.css">
</head>
<body style="background-image: url('images/sbilogoe.jpg'); background-size:cover;height:350px;
	">
	<div id="mainpage">
	<div id="statesuc">
	<% 
	session = request.getSession();
	out.println("Sender's Account Number" + session.getAttribute("s_al"));
	out.println("<br>");
	out.println("receiver's Account Number" + session.getAttribute("r_al"));
	out.println("<br>");
	out.println("Amount transferred" + session.getAttribute("al"));
	%>
	</div>
	</div>
</body>
</html>