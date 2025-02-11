package com.p1;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/Transfer")
public class Transfer extends HttpServlet {
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false); // Get the session without creating a new one

        // Check if the session is null or if the attribute is not set
        if (session == null || session.getAttribute("cust_acc_no") == null) {
            response.sendRedirect("/Bank_application/login.html"); // Redirect to login or error page
            return;
        }

        // Retrieve the customer account number
        int cust_acc_no = (int) session.getAttribute("cust_acc_no");
        System.out.println(cust_acc_no);

        String s_amt = request.getParameter("amt");
        int amt = Integer.parseInt(s_amt);
        String s_r_cust_acc_no = request.getParameter("r_cust_acc_no");
        int r_cust_acc_no = Integer.parseInt(s_r_cust_acc_no);

        try {
            Model m = new Model();
            m.setCust_acc_no(cust_acc_no);
            m.setCust_bank_bal(amt);
            m.setR_cust_acc_no(r_cust_acc_no);
            boolean b = m.transfer();

            if (b) {
                response.sendRedirect("/Bank_application/TransferSuccess.html");
            } else {
                response.sendRedirect("/Bank_application/TransferFail.html");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("/Bank_application/Error.html"); // Redirect to an error page in case of an exception
        }
    }
}
