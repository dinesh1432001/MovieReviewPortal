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
import java.sql.SQLException;


public class PostReview extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = (String) req.getSession().getAttribute("u");
        String password = (String) req.getSession().getAttribute("p");
        String title = req.getParameter("t");
        String rating = req.getParameter("ra");
        String review = req.getParameter("re");
 String query = "INSERT INTO INFO (USERNAME, PASSWORD, TITLE, RATING, REVIEW) VALUES (?, ?, ?, ?, ?)";
        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/movies?user=root&password=dinesh");
            pstmt = con.prepareStatement(query);
            pstmt.setString(1,username);
            pstmt.setString(2,password);
            pstmt.setString(3,title);
            pstmt.setString(4,rating);
            pstmt.setString(5,review);
            System.out.println("Executing query: " + pstmt.toString());
            pstmt.executeUpdate();
            String successMessage = "Review uploaded successfully";
            req.setAttribute("successMessage", successMessage);
            RequestDispatcher rd = req.getRequestDispatcher("preview.jsp");
            rd.forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
            String errorMessage = "Error uploading review.";
            req.setAttribute("errorMessage", errorMessage);
            RequestDispatcher rd = req.getRequestDispatcher("preview.jsp");
            rd.forward(req, resp);
        } 
        finally {
            try {
                if (pstmt != null) pstmt.close();
                if (con != null) con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

