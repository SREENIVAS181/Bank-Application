package com.p1;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/Question_verify")
public class Question_verify extends HttpServlet {
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("hi from QV");
        
        // Initialize the session here
        HttpSession session = request.getSession(false); // Use false to avoid creating a new session if it doesn't exist
        if (session == null) {
            response.sendRedirect("/Bank_application/Error.html"); // Handle the case where the session is null
            return;
        }

        // Retrieve cust_acc_no as Integer
        Integer cust_acc_no = (Integer) session.getAttribute("cust_acc_no");
        System.out.println(cust_acc_no);
        if (cust_acc_no == null) {
            response.sendRedirect("/Bank_application/Error.html"); // Handle the case where the account number is not found
            return;
        }
        String s_answer = request.getParameter("s_answer");
        System.out.println(s_answer);

        try {
            Model m = new Model();
            m.setCust_acc_no(cust_acc_no); 
            m.setS_answer(s_answer);
            
            boolean b = m.question_verify();
            
            if (b) {
                response.sendRedirect("/Bank_application/change_pwd.html");
            } else {
                response.sendRedirect("/Bank_application/incorrect.html");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("/Bank_application/Error.html"); // Redirect to an error page in case of an exception
        }
    }
}


