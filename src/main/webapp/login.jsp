<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ page import="java.io.PrintWriter"%>
<%@ page import="java.sql.*"%>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Movie Review</title>
    <style type="text/css">
        .b {
            outline: none;
            border: none;
            border-bottom: 1px solid #000000;
        }
        .c:hover {
            text-align: center;
            font-size: 20px;
            font-weight: bold;
            border-radius: 10px;
            background-color: green;
        }
        .d {
            text-decoration: none;
            float: right;
            margin-right: 38%;
            color: black;
            font-size: 14px;
        }
        .e {
            color: red;
            font-size: 25px;
            text-align: center;
            display: block;
            margin: 0 auto;
        }
        .r {
            border: 1px solid;
            font-size: 30px;
            color: #00394d;
            font-weight: bold;
            padding: 10px;
            margin-bottom: 10px;
            background-color: #e6e6e6;
            width: 100%;
            margin: 10px auto;
            text-align: center;
            
        }
        .di {
            text-align: center;
        }
    </style>
</head>
<body bgcolor="#bfbfbf">
<%@ include file="header.jsp"%>
    <div align="center" >
        <h1>Movie Review</h1>
        <br>
    </div>
    <form action="Movo" method="Post">
        <div align="center">
            <b>Username</b>:&nbsp;<input type="text" class="b" name="u">
            <br>
            <br>
            <b>Password</b>:&nbsp;<input type="password" class="b" name="p">
            <br>
            <br>
            <input type="submit" value="login" class="c">
            <br>
            <br>
            &nbsp;&nbsp;&nbsp;&nbsp;<a href="fp.jsp"><b class="d">forgot password</b></a>
            <br>
            <br>
            <br>
            <b class="e">${nf}</b>
            <%
                Connection con = null;
                Statement smt = null;
                ResultSet rs = null;
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/movies?user=root&password=dinesh");
                    smt = con.createStatement();
                    rs = smt.executeQuery("SELECT * FROM MOVIES.INFO ORDER BY title IS NULL, rating IS NULL, review IS NULL");
            %>
            
            <h1 align="center" class="di">Previous Reviews</h1>
            <% while(rs.next()) {
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
    </form>
</body>
</html>
