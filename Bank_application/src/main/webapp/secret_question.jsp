<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Secret Question Page</title>
    <link rel="stylesheet" href="arjun.css">
</head>
<body style="background-image: url('images/sbilogoe.jpg'); background-size: cover; height: 350px">
    <div id="mainpage">
        <div id="sec_que">
            <%
            session = request.getSession();
            String question = (String) session.getAttribute("s_question");
            out.println(question);
            out.println("<br>");
            %>
        
        <br>
        <form action="Question_verify">
            <label>Your Answer:</label><br>
            <input type="text" id="s_answer" name="s_answer" required><br><br>
            <input type="submit" value="Submit" style="border-radius: 25px; background-color: #2b0057; color: white; height: 40px; width: 100px;">
        </form>
        </div>
    </div>
</body>
</html>
