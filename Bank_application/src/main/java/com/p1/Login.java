 package com.p1;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.Session;

import java.io.IOException;

@WebServlet("/Login")
public class Login extends HttpServlet {
   
	private  HttpSession session;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cust_id = request.getParameter("cust_id");
		String cust_pwd = request.getParameter("cust_pwd");
		session = request.getSession(true);
		try {
			Model m = new Model();
			m.setCust_id(cust_id);
			m.setCust_pwd(cust_pwd);
			
			boolean b = m.login();
			
			if(b==true) {
				session.setAttribute("cust_acc_no",m.getCust_acc_no());
				response.sendRedirect("/Bank_application/Home.html");
				
			}
			else {
				response.sendRedirect("/Bank_application/Loginfail.html");
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

}
