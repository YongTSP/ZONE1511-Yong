<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="javax.servlet.http.HttpSession" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Error</title>
</head>
<body>

<h2> Something wrong happened</h2>

<%String error = (String)request.getAttribute("error");
	if(error.equals("emptyFields")){%>
	<h3>Please fill in all the fields.</h3>
	<%} else if(error.equals("notInProgress")){ %>
	<h3>Please start the signup progress from step 1</h3>
	<%} else if(error.equals("wrongDateFormat")) {%>
	<h3>You have entered wrong date format</h3>
	<%} else if(error.equals("unmatchedPasswords")){ %>
	<h3>Your password and repeated password do not match</h3>
	<%} else if(error.equals("existPassport")){ %>
	<h3>Passport numbers has been used by another user</h3>
	<%} else if(error.equals("existEmail")){ %>
	<h3>Email address has been used by another user</h3>
	<%} else if(error.equals("emptyClients")){ %>
	<h3>You have not entered any phone number</h3>
	<%} else if(error.equals("shortClients")){ %>
	<h3>Your phone number(s) is shorter than expected</h3>
	<%} else if(error.equals("existCreditCard")){ %>
	<h3>Credit Card number has been used by another user</h3>
	<%} else if(error.equals("existAccount")){ %>
	<h3>Bank Account Number has been used by another user</h3>
	<%} else if(error.equals("noProfileSelected")){ %>
	<h3>You have not chosen any member to view</h3>
	<%} else if(error.equals("invalidSigninInfo")){ %>
	<h3>Phone number and password do not match, please try again</h3>
	<%} else if(error.equals("notSignedIn")){ %>
	<h3>You are not signed in</h3>
	<%} else if(error.equals("noProviderStep1")){ %>
	<h3>You have not chosen an account for which the providers are selected</h3>
	<%} else if(error.equals("duplicatedSelection")){ %>
	<h3>No two carriers or more can be selected for the same order</h3>
	<%} else if(error.equals("noUpdateBilling")){ %>
	<h3>You have not chosen the new type of payment</h3>
	<%} else if(error.equals("invalidPassword")){ %>
	<h3>Entered password is wrong</h3>
	<%} %>

</body>
</html>