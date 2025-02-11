package com.p1;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.Session;

import java.io.IOException;
@WebServlet("/Change_pwd")
public class Change_pwd extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		int cust_acc_no = (int) session.getAttribute("cust_acc_no");
		String new_pwd = request.getParameter("new_pwd");
		String con_pwd = request.getParameter("con_pwd");
		if(new_pwd.equals(con_pwd)) {
			try {
				
				Model m = new Model();
				
				m.setCust_acc_no(cust_acc_no);
				m.setCust_pwd(new_pwd);
				
				boolean b = m.change_pwd();
				if(b==true) {
					response.sendRedirect("/Bank_application/Change_pwdSuccess.html");
				}
				else {
					response.sendRedirect("/Bank_application/Change_pwdfail.html");
				}
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}

		else {
			((HttpServletResponse)response).sendRedirect("/Bank_application/Change_pwdfail.html");
		}
		
	}	

}
