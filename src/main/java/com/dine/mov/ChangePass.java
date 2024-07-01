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


public class ChangePass extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	String username =  req.getParameter("un");

        String password = req.getParameter("pw");
        String confirmPassword = req.getParameter("cpw");

        if (password.equals(confirmPassword)) {
            String q = "UPDATE INFO SET password = ? WHERE USERNAME = ?";
            		
            Connection con = null;
            PreparedStatement smt = null;
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/movies?user=root&password=dinesh");
                smt = con.prepareStatement(q);
                smt.setString(1, password);
                smt.setString(2, username);
                int n = smt.executeUpdate();
                if (n > 0) {
                    String nu = "Password changed Successfully";
                    req.setAttribute("nu", nu);
                    RequestDispatcher rd = req.getRequestDispatcher("fp.jsp");
                    rd.forward(req, resp);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (smt != null) smt.close();
                    if (con != null) con.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else {
            String nu = "Passwords do not match. Please try again.";
            req.setAttribute("nu", nu);
            RequestDispatcher rd = req.getRequestDispatcher("fp.jsp");
            rd.forward(req, resp);
        }
    }
}

