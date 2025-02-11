package com.p1;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/Loan")
public class Loan extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		int cust_acc_no = (int) session.getAttribute("cust_acc_no");
		String cust_name = (String) session.getAttribute("cust_name");
		
		try {
			Model m = new Model();
			m.setCust_acc_no(cust_acc_no);
			m.setCust_name(cust_name);
			
			boolean b = m.applyloan();
			if(b==true) {
				session.setAttribute("cust_name",m.getCust_name());
				session.setAttribute("cust_email",m.getCust_email());
				response.sendRedirect("/Bank_application/LoanSuccess.jsp");
			}
			else {
				response.sendRedirect("/Bank_application/LoanFail.html");
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}
