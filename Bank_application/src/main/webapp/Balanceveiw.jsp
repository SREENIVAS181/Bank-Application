<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> Balance view page</title>
<link rel="stylesheet" href="arjun.css">
</head>
<body style="background-image: url('images/sbilogoe.jpg'); background-size: cover; 
	background-position: center;">
	<div id="mainpage">
	<div id="balance">
	<%
		session = request.getSession();
	    out.println("ACCOUNT BALANCE : ");
		out.println(session.getAttribute("cust_bank_bal"));
	%>
	</div>
	</div>
</body>
</html>