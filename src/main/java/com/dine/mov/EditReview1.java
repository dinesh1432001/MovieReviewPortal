package com.dine.mov;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class EditReview1 extends HttpServlet {
	
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String title = req.getParameter("t");
        String review = req.getParameter("re");
        String rating = req.getParameter("ra");
        String title1=req.getParameter("selectedReviews");
        
        
 
            String q = "UPDATE INFO SET TITLE=?,REVIEW=?,RATING=? WHERE TITLE=?";
            Connection con = null;
            PreparedStatement smt = null;
            ResultSet rs=null;
            


            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/movies?user=root&password=dinesh");

                
                    smt = con.prepareStatement(q);
                    smt.setString(1, title);
                    smt.setString(2, review);
                    smt.setString(3, rating);
                    smt.setString(4, title1);
                    int n = smt.executeUpdate();
                    if (n>0) {
                         String successMessage="review edited successfully";
                         req.setAttribute("successMessage", successMessage);
                         RequestDispatcher rd = req.getRequestDispatcher("edit1.jsp");
                         rd.forward(req,resp);
                    }
                    else {
                    	String errorMessage="review edit unsuccesfull";
                        req.setAttribute("errorMessage", errorMessage);
                RequestDispatcher rd = req.getRequestDispatcher("edit1.jsp");
                rd.forward(req, resp);
                    }
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




