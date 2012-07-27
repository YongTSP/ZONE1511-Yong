<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="com.tsp.zone.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>My Membership Details</title>
</head>
<% 	MemberState memberState = (MemberState)request.getAttribute("memberState");
	BillingState billingState = (BillingState)request.getAttribute("billingState");%>
<body>
<h2 align="center">Personal Details</h2>
<form action="UpdateMember" method="post">
<table align="center">
	<tr>
		<td>Member Number</td>
		<td><%= memberState.getMemberNum() %></td>
	</tr>
	<tr>
		<td>Name</td>
		<td><%= memberState.getFirstName() %></td>
	</tr>
	<tr>
		<td>Surname</td>
		<td><%= memberState.getSurName() %></td>
	</tr>
	<tr>
		<td>NRIC/FIN No.</td>
		<td><%= memberState.getPassportNum() %></td>
	</tr>
	<tr>
		<td>Date of Birth</td>
		<td><%= memberState.getDateOfBirth() %></td>
	</tr>
	<tr>
		<td>Gender</td>
		<td><%= memberState.getGender() %></td>
	</tr>
	<tr>
		<td>Contact No.</td>
		<td><input type="text" name="contact_phone" value="<%= memberState.getContactPhone() %>"></td>
	</tr>
	<tr>
		<td>Fax No.</td>
		<td><input type="text" name="fax" value="<%= memberState.getFax() %>"></td>
	</tr>
	<tr>
		<td>Address</td>
		<td><input type="text" name="mailing_address" value="<%= memberState.getMailingAddress() %>"></td>
	</tr>
	<tr>
		<td>Postal Code</td>
		<td><input type="text" name="postal_code" value="<%= memberState.getPostalCode() %>"></td>
	</tr>
	<tr>
		<td>Email (for billing)</td>
		<td><input type="text" name="email_address" value="<%= memberState.getEmailAddress() %>"></td>
	</tr>
	<tr>
		<td>Email (for correspondence)</td>
		<td><input type="text" name="email_correspondence" value="<%= memberState.getEmailCorrespondence() %>"></td>
	</tr>
	<tr>
		<td></td>
		<td><input type="submit" value="Update"></td>
	</tr>
</table>
</form>
<h2 align="center">Change Password</h2>
<form action="UpdatePassword" method="post">
<table align="center">
	<tr>
		<td>Old Password</td>
		<td><input type="password" name="password" ></td>
	</tr>
	<tr>
		<td>New Password</td>
		<td><input type="password" name="new_password" ></td>
	</tr>
	<tr>
		<td>Confirm New Password</td>
		<td><input type="password" name="repeat_new_password" ></td>
	</tr>
	<tr>
		<td></td>
		<td><input type="submit" name="Update" ></td>
	</tr>
</table>
</form>
<h2 align="center">Payment Details</h2>
<form action="UpdateBilling" method="post">
<table align="center">
<%if(billingState.getPaymentModeInd().equals("C")) {%>
	<tr>
		<th>Current Payment Method</th>
		<td>Credit Card</td>
	</tr>
	<tr>
		<td>Credit Card Type</td>
		<td><%= billingState.getCreditCardType() %></td>
	</tr>
	<tr>
		<td>Credit Card No.</td>
		<td><%= billingState.getCreditCardNum() %></td>
	</tr>
	<tr>
		<td>Expiry Date</td>
		<td><%= billingState.getDateOfExpiration() %></td>
	</tr>
<%} else if(billingState.getPaymentModeInd().equals("G")) { %>
	<tr>
		<th>Current Payment Method</th>
		<td>GIRO</td>
	</tr>
	<tr>
		<td>Bank Name</td>
		<td><%= billingState.getBankName() %></td>
	</tr>
	<tr>
		<td>Bank Account No.</td>
		<td><%= billingState.getBankAccountNum() %></td>
	</tr>
	<tr>
		<td>Bank Account Name</td>
		<td><%= billingState.getAccountName() %></td>
	</tr>

<%} else if(billingState.getPaymentModeInd().equals("Q")) { %>
	<tr>
		<th>Current Payment Method</th>
		<td>Cheque</td>
	</tr>
<%} %>
	<tr>
		<th>Change Payment Method</th>
	</tr>
	<tr>
		<td><input type="radio" name="payment_mode_ind" value="C"></td>
		<td>Credit Card</td>
	</tr>
	<tr>
		<td><input type="radio" name="payment_mode_ind" value="G"></td>
		<td>GIRO</td>
	</tr>
	<tr>
		<td></td>
		<td><input type="submit" value="Continue"></td>
	</tr>
</table>
</form>
</body>
</html>