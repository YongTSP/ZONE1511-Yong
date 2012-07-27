<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Sign In</title>
</head>
<body>
<form action="Home" method="post">
	<table align="center">
		<tr>
			<td>Phone Number</td>
			<td><input type="text" maxlength=8 size=8 name="signin_phone_num"></td>
		</tr>
		<tr>
			<td>Password</td>
			<td><input type="password" name="signin_password"></td>
		</tr>
		<tr>
			<td></td>
			<td><input type="submit" value="Sign In"></td>
		</tr>
	</table>
</form>
<h3 align="center"><a href="SignupStep1">Sign up</a></h3>
</body>
</html>