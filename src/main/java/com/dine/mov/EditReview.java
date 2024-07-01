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



public class EditReview extends HttpServlet {
	
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String title = req.getParameter("selectedReviews");
        
 
            String q = "SELECT TITLE,REVIEW,RATING FROM INFO WHERE TITLE = ?";
            Connection con = null;
            PreparedStatement smt = null;
            ResultSet rs=null;
            String tit = null;
            String tr = null;
            String tra = null;


            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/movies?user=root&password=dinesh");

                
                    smt = con.prepareStatement(q);
                    smt.setString(1, title);
                    rs = smt.executeQuery();
                    while (rs.next()) {
                         tit =rs.getString("title");
                         tr=rs.getString("review");
                         tra=rs.getString("rating");
                    } 
                    req.setAttribute("tit", tit);
                    req.setAttribute("tr", tr);
                    req.setAttribute("tra", tra);
                    
                

                RequestDispatcher rd = req.getRequestDispatcher("edit1.jsp");
                rd.forward(req, resp);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (smt != null) {
                        smt.close();
                    }
                    if (con != null) {
                        con.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        
    }
}

