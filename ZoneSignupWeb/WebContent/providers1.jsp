<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@page import="com.tsp.zone.AccountState, java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Select an Account</title>
</head>
<body>
<% List<AccountState> accounts = (List<AccountState>)request.getAttribute("accounts"); %>
<h3 align="center">Select An Account</h3>
<form action="ProviderStep2" method="post">
	<table align="center">
		<tr>
			<td>
				<select name="selected_account">
					<% for(AccountState account: accounts){ %>
						<option value=<%= account.getAccountNum() %>>
						<%= account.getAccountNum()+" - " +account.getPlanCode() %>
						</option>
					<%} %>
				</select>
			</td>
		</tr>
		<tr>
			<td>
				<input type="submit" value="Submit">
			</td>
		</tr>
	</table>
</form>
</body>
</html>