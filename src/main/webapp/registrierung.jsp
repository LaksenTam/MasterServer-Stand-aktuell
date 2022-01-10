<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<form method="POST" action="Registrierung">
		<table>
			<tr>
				<td>Matrikelnummer*:</td>
				<td><input name="loginname" type="text"></td>
			</tr>
			<tr>
				<td>Passwort*:</td>
				<td><input name="passwort" type="password" min="4" required></td>
			</tr>
			<tr>
				<td>Passwort best&auml;tigen*:</td>
				<td><input name="pBestaetigen" type="password" required></td>
			</tr>
			<tr>
				<td></td>
				<td><input type="submit" value="Registrieren"></td>
			</tr>
			<tr>
				<td>* Pflichtfelder</td>
			</tr>
		</table>
		<br>
		<p style="color:red" class="text-danger">${ error } </p>
		</form>
	

</body>
</html>