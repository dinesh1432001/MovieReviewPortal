<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Movie Review</title>
<%@ include file="header.jsp"%>
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
    margin-right: 41%;
    color: black;
    font-size: 14px;
}
.e {
    color: green;
    font-size: 25px;
}
</style>
</head>
<body bgcolor="#bfbfbf">
<div align="center">
<h1>Movie Review</h1>
<br>
</div>
<form action="EditReview1" method="post">
<div align="center">
    <b>Title</b>: <input type="text" class="b" name="t" value="<%=request.getAttribute("tit")%>">
    <br><br>
    <b>Rating</b>: <input type="text" class="b" name="ra" value="<%=request.getAttribute("tra")%>">
    <br><br>
    <b>Review</b>: <input type="text" class="b" name="re" value="<%=request.getAttribute("tr")%>">
    <br><br>
    <input type="hidden" name="selectedReviews" value="<%=request.getParameter("selectedReviews")%>">
    <input type="submit" value="Update" class="c">
    <br><br>
    <b class="e">${successMessage}</b>
    <b class="e" style="color:red;">${errorMessage}</b>
</div>
</form>
</body>
</html>
