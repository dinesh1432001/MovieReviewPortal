<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Movie Review</title>
<style>
.e {
    color: green;
    font-size: 30px;
}
.r {
    border: 1px solid;
    margin-left: -750px;
    margin-right: -700px;
    font-size: 30px;
    color: #00394d;
    font-weight: bold;
    padding: 10px;
    margin-bottom: 10px;
    background-color: #e6e6e6;
}
.di{
 
 text-align:center;
 margin: 0 auto;
    max-width: 800px;
}
</style>
</head>
<body bgcolor="#bfbfbf">
<%@ include file="header1.jsp" %>
<div align="center" >
    <h1>Movie Review</h1>
    <br>
    <br>
</div>
<div align="center">
    <b class="e">${ss}</b>
    <br>
    <br>
    <%
        Connection con = null;
        PreparedStatement smt = null;
        ResultSet rs = null;
        String u = (String) request.getSession().getAttribute("u");
        String p = (String) request.getSession().getAttribute("p");

        String q = "SELECT * FROM MOVIES.INFO WHERE USERNAME=? AND PASSWORD=? ORDER BY title IS NULL, rating IS NULL, review IS NULL";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/movies?user=root&password=dinesh");
            smt = con.prepareStatement(q);
            smt.setString(1, u);
            smt.setString(2, p);
            rs = smt.executeQuery();
    %>
    <br>
    <br>
    
            <h1 align="center"  class="di">Your Reviews</h1>
            <% while (rs.next()) {
                String username = rs.getString("username");
                String title = rs.getString("title");
                String rating = rs.getString("rating");
                String review = rs.getString("review");
            %>
                <div class="r">
                    <br>
                    <% if (title != null && rating != null && review != null) { %>
                        <%= username %>: <br>
                    <% } %>
                    <% if (title != null) { %>
                        <strong>Title:</strong> <%= title %><br>
                    <% } %>
                    <% if (rating != null) { %>
                        <strong>Rating:</strong> <%= rating %><br>
                    <% } %>
                    <% if (review != null) { %>
                        <strong>Review:</strong> <%= review %><br>
                    <% } %>
                </div>
            <% } %>
    <%
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (smt != null) smt.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    %>
</div>
</body>
</html>
