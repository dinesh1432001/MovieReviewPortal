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
import java.util.StringTokenizer;



public class DeleteReview extends HttpServlet {
	
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] titles = req.getParameterValues("selectedReviews");
        
        if (titles != null && titles.length > 0) {
            String q = "DELETE FROM INFO WHERE TITLE = ?";
            Connection con = null;
            PreparedStatement smt = null;

            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/movies?user=root&password=dinesh");

                for (String title : titles) {
                    smt = con.prepareStatement(q);
                    smt.setString(1, title);
                    int n = smt.executeUpdate();
                    if (n > 0) {
                        String re = "Review deleted successfully";
                        req.setAttribute("re", re);
                    } else {
                        String re = "Review not found";
                        req.setAttribute("re", re);
                    }
                }

                RequestDispatcher rd = req.getRequestDispatcher("delete.jsp");
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
}

