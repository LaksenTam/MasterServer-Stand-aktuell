<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
</head>
<body>
<form method="POST" action="Login">
		<table>
			<tr>
				<td>Matrikelnummer:</td>
				<td><input name="loginname" type="text" required></td>
			</tr>
			<tr id = "loginfenster">
				<td>Passwort:</td>
				<td><input name="passwort" type="password" required></td>
			</tr>
			<tr>
				<td></td>
				<td><input type="submit" value="Einloggen"></td>
			</tr>
		</table>
		<br>
		Noch kein Benutzerkonto? Dann hier <a class="clink" href="registrierungStudent.jsp">registieren</a>.
		<br>
		<p style="color:red" class="text-danger">${ error } </p>
		</form>

</body>
</html>