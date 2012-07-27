<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Sign Up - Personal Particulars</title>
</head>
<body>
<h3 align="center">Your personal particulars</h3>
<form action="SignupStep3" method="post">
	<table align="center">
		
		<tr>
			<td>Salutation</td>
			<td>
				<select name="salute">
					<option value="Mr.">Mr.</option>
					<option value="Mrs.">Mrs.</option>
					<option value="Miss.">Miss.</option>
					<option value="Mdm.">Mdm.</option>
					<option value="Dr.">Dr.</option>
					<option value="Prof.">Prof.</option>
					<option value="Mssr.">Mssr.</option>
				</select>
			</td>
		</tr>
		<tr>
			<td>First name:</td>
			<td><input type="text" name="first_name"></td>
		</tr>
		<tr>
			<td>Surname:</td>
			<td><input type="text" name="sur_name"></td>
		</tr>
		<tr>
			<td>NRIC/FIN No.</td>
			<td><input type="text" name="passport_num"></td>
		</tr>
		<tr>
			<td>Date of Birth (dd/MM/yyyy)</td>
			<td><input type="text" name="date_of_birth"></td>
		</tr>
		<tr>
			<td>Gender</td>
			<td>
				<select name="gender">
					<option value="M">Male</option>
					<option value="F">Female</option>
				</select>
			</td>
		</tr>
		<tr>
			<td>Address</td>
			<td><input type="text" name="mailing_address"></td>
		</tr>
		<tr>
			<td>Postal Code</td>
			<td><input type="text" name="postal_code"></td>
		</tr>
		<tr>
			<td>Email Address For Receiving Invoice</td>
			<td><input type="text" name="email_address"></td>
		</tr>
		<tr>
			<td>Email Address For Correspondence</td>
			<td><input type="text" name="email_correspondence"></td>
		</tr>
		<tr>
			<td>Contact No.</td>
			<td><input type="text" name="contact_phone"></td>
		</tr>
		<tr>
			<td>Fax</td>
			<td><input type="text" name="fax"></td>
		</tr>
		<tr>
			<td>Password</td>
			<td><input type="password" name="password"></td>
		</tr>
		<tr>
			<td>Repeat password</td>
			<td><input type="password" name="repeat_password"></td>
		</tr>
		<tr>
			<td>Password reminder question</td>
			<td><input type="text" name="password_reminder_question"></td>
		</tr>
		<tr>
			<td>Password reminder answer</td>
			<td><input type="text" name="password_reminder_answer"></td>
		</tr>
		<tr><td></td><td><input type="submit" value="Continue"></td></tr>
	</table>
</form>

</body>
</html>