package com.p1;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/Register")
public class Register extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String name = request.getParameter("cust_name");
		System.out.println(name);
		String id = request.getParameter("cust_id");
		
		String s_acc_no = request.getParameter("cust_acc_no");
		int acc_no = Integer.parseInt(s_acc_no);
		
		String pwd = request.getParameter("cust_pwd");
		String s_bal = request.getParameter("cust_bank_bal");
		int bal = Integer.parseInt(s_bal);
		
		String email = request.getParameter("cust_email");
		String s_question = request.getParameter("s_question");
		String s_answer = request.getParameter("s_answer");
		try {
			Model m = new Model();
			m.setCust_name(name);
			m.setCust_id(id); 
			m.setCust_acc_no(acc_no);
			m.setCust_pwd(pwd);
			m.setCust_bank_bal(bal);
			m.setCust_email(email);
			m.setS_question(s_question);
			m.setS_answer(s_answer);
			
			boolean b = m.Register();
			
			if(b==true) {
				response.sendRedirect("/Bank_application/SuccessReg.html");
			}else {
				response.sendRedirect("/Bank_application/FailureReg.html");
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
