<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.tsp.zone.CarrierState, java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Select Service Providers</title>
</head>
<% List<CarrierState> allCarriers = (List<CarrierState>)request.getAttribute("allCarriers");
	List<CarrierState> selectedCarriers = (List<CarrierState>)request.getAttribute("selectedCarriers"); %>
<body>
<h2 align="center">Current Selection</h2>
<table align="center">
	<tr>
		<th>Carrier's Name</th>
		<th>Fax Supported</th>
	</tr>
	<%for (CarrierState carrier: selectedCarriers){ %>
	<tr>
		<td><%= carrier.getCarrierName() %></td>
		<td><%= carrier.getGoodForFax() %></td>
	</tr>
	<%} %>
	
</table>
<h2 align="center">Update by selecting new providers from this list</h2>
<form action="ProviderStep3" method="post">
<table align="center">
	<tr>
		<th>Order of Selection</th>
		<th>Carrier's Name</th>
		<th>Fax Supported</th>
	</tr>
	<%for (CarrierState carrier: allCarriers){ %>
	<tr>
		<td>
			<select name="<%= carrier.getCarrierId() %>">
				<%for (int i=0;i<6;i++) {%>
				<option value="<%= i%>"><%= i %></option>
				<%} %>
			</select>
		</td>
		<td><%= carrier.getCarrierName() %></td>
		<td><%= carrier.getGoodForFax() %></td>
	</tr>
	<%} %>
	<tr>
		<td></td>
		<td><input type="submit" value="Update" ></td>
	</tr>
</table>
</form>
</body>
</html>