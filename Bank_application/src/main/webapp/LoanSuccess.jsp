<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Loan Success</title>
<link rel="stylesheet" href="arjun.css">
</head>
<body style="background-image: url('images/sbilogoe.jpg'); background-size:cover;height:350px;
	">
	<div id="mainpage">
	<div id="loansuc">
	<%
	session = request.getSession();
	out.println("Dear " +session.getAttribute("cust_name")+" thank you for showing your interest on the loans from");
	out.println("<br>");
	out.println("Our executive will contact you soon on your email adrress mentioned below:");
	out.println("<br>");
	%>
	<h3> <% out.println(session.getAttribute("cust_email")); %></h3>
	
	</div>
	</div>
</body>
</html>