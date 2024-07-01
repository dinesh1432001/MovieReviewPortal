package com.dine.mov;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Movo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException,IOException {
		String username=req.getParameter("u");
		System.out.println("------------------------"+username);
		String password=req.getParameter("p");
		ResultSet rs=null;
		String q="SELECT * FROM MOVIES.INFO WHERE USERNAME=? AND PASSWORD=?";
		Connection con=null;
		PreparedStatement smt=null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/movies?user=root&password=dinesh");
			smt=con.prepareStatement(q);
			smt.setString(1,username);
			smt.setString(2,password);
			System.out.println("------------------------"+username);
			rs=smt.executeQuery();
			if(rs.next()){
				 HttpSession se = req.getSession();
				 se.setAttribute("u",username);
				 se.setAttribute("p",password);
				String ss="welcome"+" "+" "+username;
				RequestDispatcher rd = req.getRequestDispatcher("loginsucess.jsp");
				req.setAttribute("ss", ss);
				rd.forward(req,resp);
			}
			else {
			    String nf = "User not found";
			    req.setAttribute("nf", nf);
			    RequestDispatcher rd = req.getRequestDispatcher("login.jsp");
			    rd.forward(req, resp);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		
	}





