<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Sign Up - Plan and Payment Selection</title>
</head>
<body>
<h3 align="center">Select plan and payment method</h3>
<form action="SignupStep2" method="post">
	<table align="center">
		<tr><td>Select your plan:</td></tr>
		<tr><td><input type="radio" name="plan_code" value="123free" checked>123FREE</td></tr>
		<tr><td><input type="radio" name="plan_code" value="1511nopeak">1511NOPEAK</td></tr>
		
		<tr><td>Select your payment mode:</td></tr>
		<tr><td><input type="radio" name="payment_mode_ind" value="C" checked>Credit Card</td></tr>
		<tr><td><input type="radio" name="payment_mode_ind" value="G">GIRO</td></tr>
		<tr><td><input type="radio" name="payment_mode_ind" value="Q">Cheque</td></tr>
		
		<tr><td><input type="submit" value="Continue"></td></tr>
	</table>
</form>

</body>
</html>