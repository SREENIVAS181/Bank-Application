package com.p1;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/Check_balance")
public class Check_balance extends HttpServlet {
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		int cust_acc_no = (int) session.getAttribute("cust_acc_no");
		
		try {
			Model m = new Model();
			m.setCust_acc_no(cust_acc_no);
			
			boolean b = m.check_balance();
			
			if(b==true) {
				session.setAttribute("cust_bank_bal",m.getCust_bank_bal());
				response.sendRedirect("/Bank_application/Balanceveiw.jsp");
			}
			else {
				response.sendRedirect("/Bank_application/Balancefail.html");
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}
