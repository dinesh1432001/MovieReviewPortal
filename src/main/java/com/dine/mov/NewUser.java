package com.dine.mov;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
public class NewUser extends HttpServlet{
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException,IOException{
		String username=req.getParameter("un");
		System.out.println("------------------------"+username);
		String password=req.getParameter("pw");
		String q="INSERT INTO INFO(username,password) VALUES(?,?)";
		Connection con=null;
		PreparedStatement smt=null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/movies?user=root&password=dinesh");
			smt=con.prepareStatement(q);
			smt.setString(1,username);
			smt.setString(2,password);
			int n=smt.executeUpdate();
			if(n>0) {
			String nu="Registered Succesfully";
			req.setAttribute("nu",nu);
			RequestDispatcher rd = req.getRequestDispatcher("newuser.jsp");
			rd.forward(req,resp);
			}
	}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
