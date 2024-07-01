<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Movie Review Sign Up</title>
</head>
<body>
<%@include file="header2.jsp"%>
<style type="text/css">
.b{
outline:none;
border:none;
border-bottom:1px solid #000000;
}
.c:hover{
text-align:center;
font-size:20px;
font-weight:bold;
border-radius:10px;
background-color:green;
}
.e{
color:green;
font-size:25px;
}
</style>
</head>
<body bgcolor="#bfbfbf">
<div align="center">
<h1>Sign Up</h1>
<br>
</div>
<form action="ChangePass" method="post">
<div align="center" class="e">
<b>Username</b>:&nbsp<input type="text" class="b" name="un" >
<br>
<br>
<b>New Password</b>:&nbsp<input type="password" class="b" name="pw">
<br>
<br>
<b>Confirm Password</b>: <input type="password" class="b" name="cpw">
<br>
<br>
<input type="submit" value="Register" class="c">
<br>
<br>
<b>${nu}</b>
</div>
</div>
</form>
</body>
</html>