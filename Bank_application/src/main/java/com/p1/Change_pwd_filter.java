package com.p1;

import jakarta.servlet.DispatcherType;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.net.http.HttpRequest;

@WebFilter("/Change_pwd_filter")
public class Change_pwd_filter extends HttpFilter implements Filter {
       
   
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		String new_pwd = request.getParameter("new_pwd");
		String con_pwd = request.getParameter("con_pwd");
		System.out.println("hi from filter");
		if(new_pwd.equals(con_pwd)) {
			chain.doFilter(request, response);
		}
		else {
			((HttpServletResponse)response).sendRedirect("/Bank_application/Change_pwdfail.html");
		}
	}


}
