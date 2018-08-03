<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
</head>
<body>
<form action="${pageContext.request.contextPath }/login.action" method="post">
<table>
<tr>
<td style="text-align:center;vertical-align:middle">ID:</td><td><input type="text" name="userName" /></td><br/>
</tr>
<tr>
<td>Password:</td><td><input type="password" name="password" /></td><br/>
</tr>
<tr>
<td></td><td><input type="submit" value="Login"/></td>
</tr>
</table>
</form>

</body>
</html>
