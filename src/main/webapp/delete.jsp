<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="java.sql.*"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Movie Review</title>
    <%@ include file="header.jsp"%>
    <style type="text/css">
          .c:hover {
            text-align:center;
            font-size:20px;
            font-weight:bold;
            border-radius:10px;
            background-color:green;
        }
        .b {
            color: green;
            font-size: 25px;
        }
        .d {
            font-size: 25px;
            color: #00394d;
            font-weight: bold;
        }
        .review-card {
            background-color: white;
            padding: 20px;
            margin: 20px;
            border-radius: 10px;
            
            max-width: 600px;
            margin-left: auto;
            margin-right: auto;
        }
        .review-title {
            font-size: 22px;
            font-weight: bold;
            color: #00394d;
        }
        .review-content {
            font-size: 18px;
            margin-top: 5px;
        }
        .review-rating {
            font-size: 18px;
            color: green;
            margin-top: 10px;
        }
        body {
            background-color: #bfbfbf;
            font-family: Arial, sans-serif;
        }
       
        form {
            margin: 0 auto;
            text-align: center;
        }
    </style>
</head>
<body>
    <div align="center" >
        <h1>Movie Review</h1>
        <br>
    </div>
    <form action="DeleteReview" method="Post" class="d">
        <div>
            <%
                String title = null;
                String rating = null;
                String review = null;
                PreparedStatement smt = null;
                String q = "SELECT * FROM MOVIES.INFO WHERE USERNAME=? AND PASSWORD=? ORDER BY title IS NULL, rating IS NULL, review IS NULL";
                Connection con = null;
                ResultSet rs = null;
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/MOVIES?user=root&password=dinesh");
                    smt = con.prepareStatement(q);
                    String username = (String) request.getSession().getAttribute("u");
                    String password = (String) request.getSession().getAttribute("p");
                    System.out.println("Username: " + username);
                    System.out.println("Password: " + password);
                    smt.setString(1, username);
                    smt.setString(2, password);
                    rs = smt.executeQuery();
                    while(rs.next()) {
                        title = rs.getString("title");
                        rating = rs.getString("rating");
                        review = rs.getString("review");
            %>
                        <div class="review-card">
                            <input type="checkbox" name="selectedReviews" value="<%=title%>">
                            <span class="review-title"><%=title%></span><br>
                            <div class="review-content"><%=review%></div>
                            <div class="review-rating">Rating: <%=rating%></div>
                        </div>
            <%
                    }
            %>
                   <input type="submit" value="Delete" class="c"><br>
                   <br>
                    <% if (request.getAttribute("re") != null) { %>
                       <b class="b"><%=request.getAttribute("re") %></b>
                   <% } %>
            <%
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    try { if (rs != null) rs.close(); } catch (SQLException e) {  }
                    try { if (smt != null) smt.close(); } catch (SQLException e) {  }
                    try { if (con != null) con.close(); } catch (SQLException e) {  }
                }
            %>
        </div>
    </form>
</body>
</html>
