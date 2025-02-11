package com.p1;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/GetStatement")
public class GetStatement extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		int cust_acc_no=(int) session.getAttribute("cust_acc_no");
		 
		 try {
			 Model m = new Model();
			 m.setCust_acc_no(cust_acc_no);
			 ArrayList al = m.getstatement();
			 
			 if(al.isEmpty()==true) {
				 response.sendRedirect("/Bank_application/StatementFail.html");
			 }
			 else {
				 session.setAttribute("s_al",m.s_al);
				 session.setAttribute("r_al",m.r_al);
				 session.setAttribute("al",m.al);
				 response.sendRedirect("/Bank_application/StatementSuccess.jsp");
			 }
			 
		 }
		 catch (Exception e) {
			e.printStackTrace();
		}
	}
}
