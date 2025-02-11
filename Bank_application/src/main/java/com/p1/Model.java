package com.p1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.tomcat.dbcp.dbcp2.DriverManagerConnectionFactory;

public class Model {
	private String cust_name;
	private String cust_id;
	private int cust_acc_no;
	private String cust_pwd;
	private int cust_bank_bal;
	private String cust_email;
	private int r_cust_acc_no;
	private String s_question;
	private String s_answer;
	
	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet res;
	public ArrayList al = new ArrayList();
	public ArrayList s_al = new ArrayList();
	public ArrayList r_al = new ArrayList();
	
	public String getS_question() {
		return s_question;
	}
	public void setS_question(String s_question) {
		this.s_question = s_question;
	}
	public String getS_answer() {
		return s_answer;
	}
	public void setS_answer(String s_answer) {
		this.s_answer = s_answer;
	}
	
	public int getR_cust_acc_no() {
		return r_cust_acc_no;
	}
	public void setR_cust_acc_no(int r_cust_acc_no) {
		this.r_cust_acc_no = r_cust_acc_no;
	}
	public String getCust_name() {
		return cust_name;
	}
	public void setCust_name(String cust_name) {
		this.cust_name = cust_name;
	}
	public String getCust_id() {
		return cust_id;
	}
	public void setCust_id(String cust_id) {
		this.cust_id = cust_id;
	}
	public int getCust_acc_no() {
		return cust_acc_no;
	}
	public void setCust_acc_no(int cust_acc_no) {
		this.cust_acc_no = cust_acc_no;
	}
	public String getCust_pwd() {
		return cust_pwd;
	}
	public void setCust_pwd(String cust_pwd) {
		this.cust_pwd = cust_pwd;
	}
	public int getCust_bank_bal() {
		return cust_bank_bal;
	}
	public void setCust_bank_bal(int cust_bank_bal) {
		this.cust_bank_bal = cust_bank_bal;
	}
	public String getCust_email() {
		return cust_email;
	}
	public void setCust_email(String cust_email) {
		this.cust_email = cust_email;
	}
	public Model() throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/banking_application","root","Arjun181");
		System.out.println("driver is loaded ");
	}
	public boolean Register() throws Exception{
		String s = "insert into SBI_DEMO_APPLICATION values(?,?,?,?,?,?,?,?)";
		pstmt = con.prepareStatement(s);
		pstmt.setString(1,cust_name);
		pstmt.setString(2,cust_id);
		pstmt.setInt(3,cust_acc_no);
		pstmt.setString(4,cust_pwd);
		pstmt.setInt(5,cust_bank_bal);
		pstmt.setString(6,cust_email);
		pstmt.setString(7,s_question);
		pstmt.setString(8,s_answer);
		
		int x = pstmt.executeUpdate();
		if(x>0) {
			return true;
		}
		return false;
	}
	public boolean login() throws Exception {
		String s = "select * from SBI_DEMO_APPLICATION where cust_id=? and cust_pwd=?";
		pstmt=con.prepareStatement(s);
		pstmt.setString(1,cust_id);
		pstmt.setString(2,cust_pwd);
		
		res = pstmt.executeQuery();
		
		while(res.next()) {
			cust_acc_no=res.getInt("cust_acc_no");
			return true;
		}
		return false;
	}
	public boolean check_balance() throws Exception {
		String s = "select cust_bank_bal from SBI_DEMO_APPLICATION where cust_acc_no=?";
		pstmt = con.prepareStatement(s);
		pstmt.setInt(1,cust_acc_no);
		res = pstmt.executeQuery();
		
		if(res.next()==true) {
			cust_bank_bal = res.getInt("cust_bank_bal");
			return true;
		}
		return false;
	}
	public boolean change_pwd() throws Exception {
		String s = "update SBI_DEMO_APPLICATION set cust_pwd=? where cust_acc_no=?";
		pstmt = con.prepareStatement(s);
		pstmt.setString(1,cust_pwd);
		pstmt.setInt(2,cust_acc_no);
		int x = pstmt.executeUpdate();
		if(x>0) {
			return true;
		}
		return false;
	}
	public boolean transfer() throws Exception {
		String s1 = "select * from SBI_DEMO_APPLICATION where cust_acc_no=?"; 
		pstmt = con.prepareStatement(s1);
		pstmt.setInt(1,r_cust_acc_no);
		res = pstmt.executeQuery();
	    while(res.next()==true) {
	    	String s5 = "select * from SBI_DEMO_APPLICATION where cust_acc_no=?";
			pstmt = con.prepareStatement(s5);
			pstmt.setInt(1,cust_acc_no);
			res = pstmt.executeQuery();
			while(res.next()==true) {
				int x = res.getInt("cust_bank_bal");
				if(x>cust_bank_bal) {
					String s2 = "update SBI_DEMO_APPLICATION set cust_bank_bal=cust_bank_bal-? where cust_acc_no=?";
					pstmt = con.prepareStatement(s2);
					pstmt.setInt(1,cust_bank_bal);
					pstmt.setInt(2,cust_acc_no);
					int z = pstmt.executeUpdate();
					if(z>0) {
						String s3 = "update SBI_DEMO_APPLICATION set cust_bank_bal=cust_bank_bal+? where cust_acc_no=?";
						pstmt = con.prepareStatement(s3);
						pstmt.setInt(1,cust_bank_bal);
						pstmt.setInt(2,r_cust_acc_no);
						int y1 = pstmt.executeUpdate();
						if(y1>0) {
							String s4 = "insert into GetStatement values(?,?,?)";
							pstmt = con.prepareStatement(s4);
							pstmt.setInt(1,cust_acc_no);
							pstmt.setInt(2,r_cust_acc_no);
							pstmt.setInt(3,cust_bank_bal);
							int y = pstmt.executeUpdate();
							if(y>0) {
								return true;
							}
							else {
								return false;
							}
						}
					}
					else {
						return false;
					}
				}
				else {
					return false;
				}
			}
	    }
		return false;
		
	}
	public ArrayList getstatement() throws Exception {
		String s = "select * from GetStatement where cust_acc_no=?";
		pstmt = con.prepareStatement(s);
		pstmt.setInt(1,cust_acc_no);
		res = pstmt.executeQuery();
		while(res.next()==true) {
			al.add(res.getInt("amount_transferred"));
			s_al.add(res.getInt("cust_acc_no"));
			r_al.add(res.getInt("r_cust_acc_no"));
		}
		return al;
		
	}
	public boolean applyloan() throws Exception {
		String s = "select * from SBI_DEMO_APPLICATION where cust_acc_no=?";
		pstmt = con.prepareStatement(s);
		pstmt.setInt(1,cust_acc_no);
		res = pstmt.executeQuery();
		while(res.next()==true) {
			 cust_name = res.getString("cust_name");
			 cust_email = res.getString("cust_email");
			return true;
		}
		return false;
	}
	public boolean forgotpassword() throws Exception {
		String s1 = "select * from SBI_DEMO_APPLICATION where cust_acc_no=?";
		pstmt = con.prepareStatement(s1);
		pstmt.setInt(1, cust_acc_no);
		res = pstmt.executeQuery();
		while(res.next()==true) {
			 s_question = res.getString("s_question");
			 return true;
		}
		return false;
		
		
	}
	public boolean question_verify() throws Exception {
	    String s1 = "select * from SBI_DEMO_APPLICATION where cust_acc_no=?";
	    pstmt = con.prepareStatement(s1);
	    pstmt.setInt(1, cust_acc_no); // Use the actual variable here
	    res = pstmt.executeQuery();
	    
	    while (res.next()) {
	        String s_answer = res.getString("s_answer");
	        if (s_answer.equals(this.s_answer)) { // Use the instance variable
	            return true;
	        }
	    }
	    return false;
	}


}
