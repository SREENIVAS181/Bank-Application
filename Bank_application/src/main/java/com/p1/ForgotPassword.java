package com.p1;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.Session;

import java.io.IOException;

@WebServlet("/ForgotPassword")
public class ForgotPassword extends HttpServlet {
	private  HttpSession session;
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("hi from forgot");
		String s_cust_acc_no = request.getParameter("cust_acc_no");
		int cust_acc_no = (int)Integer.parseInt(s_cust_acc_no);
		session = request.getSession(true);
		
		try {
			Model m = new Model();
			m.setCust_acc_no(cust_acc_no);
			
			boolean b = m.forgotpassword();
			
			if(b==true) {
				session.setAttribute("cust_acc_no",m.getCust_acc_no());
				session.setAttribute("s_question",m.getS_question());
				response.sendRedirect("/Bank_application/secret_question.jsp");	
			}
			else {
				response.sendRedirect("/Bank_application/incorrect.html");
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

}
