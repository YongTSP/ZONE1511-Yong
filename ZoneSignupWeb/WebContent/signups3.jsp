<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Sign Up - Payment Details</title>
</head>
<body>
<h3 align="center">Phone numbers to use with ZONE 1511</h3>
<form action="Result" method="post">
<table align="center">


<tr>
	<td>
		1. <input type="text" maxlength=8 size=8 name="cli[]">
	</td>
	<td><input type="checkbox" name="fax[]" value="0"></td>
	<td>
		2. <input type="text" maxlength=8 size=8 name="cli[]">
	</td>
	<td><input type="checkbox" name="fax[]" value="1"></td>
</tr>
<tr>
	<td>
		3. <input type="text" maxlength=8 size=8 name="cli[]">
	</td>
	<td><input type="checkbox" name="fax[]" value="2"></td>
	<td>
		4. <input type="text" maxlength=8 size=8 name="cli[]">
	</td>
	<td><input type="checkbox" name="fax[]" value="3"></td>
</tr>
<tr>
	<td>
		5. <input type="text" maxlength=8 size=8 name="cli[]">
	</td>
	<td><input type="checkbox" name="fax[]" value="4"></td>
	<td>
		6. <input type="text" maxlength=8 size=8 name="cli[]">
	</td>
	<td><input type="checkbox" name="fax[]" value="5"></td>
</tr>
</table>


<%
Character payment_mode_ind = ((String)request.getSession().getAttribute("payment_mode_ind")).charAt(0);
if(payment_mode_ind=='C'){%>
<h3 align="center">Enter Credit Card Details</h3>
<table align="center">
<tr>
	<td>Credit Card Type</td>
	<td>
		<select name="credit_card_type">
			<option value="VISA">VISA</option>
			<option value="MASTERCARD">MASTERCARD</option>
			<option value="AMERICAN EXPRESS">AMERICAN EXPRESS</option>
		</select>
	</td>
</tr>
<tr>
	<td>Credit Card Number</td>
	<td><input type="text" name="field1" maxlength=4 size=4>-<input type="text" name="field2" maxlength=4 size=4>-<input type="text" name="field3" maxlength=4 size=4>-<input type="text" name="field4" maxlength=4 size=4></td>
</tr>
<tr>
	<td>CVV</td>
	<td><input type="text" name="cvv" maxlength=4 size=4></td>
</tr>
<tr>
	<td>Cardholder's Name</td>
	<td><input type="text" name="credit_card_member_name"></td>
</tr>
<tr>
	<td>Valid Through (MM/yyyy)</td>
	<td><input type="text" name="date_of_expiration"></td>
</tr>
<tr>
	<td></td>
	<td><input type="submit" value="Register"></td>
</tr>
</table>
<%} else if(payment_mode_ind=='G') {%>
<h3 align="center">Enter GIRO Details</h3>
<table align="center">
<tr>
	<td>Bank Name</td>
	<td><input type="text" name="bank_name"></td>
</tr>
<tr>
	<td>Bank Account Number</td>
	<td><input type="text" name="bank_account_num"></td>
</tr>
<tr>
	<td>Account Name</td>
	<td><input type="text" name="account_name"></td>
</tr>
<tr>
	<td></td>
	<td><input type="submit" value="Register"></td>
</tr>
</table>
<%} else {%>
<h3 align="center"> You have chosen to pay by cheque. Please contact the customer service at 9999-9999 for more details.</h3>
<table align="center">
<tr><td><input type="submit" value="Register"><td></tr>
</table>


<%} %>

</form>
</body>
</html>