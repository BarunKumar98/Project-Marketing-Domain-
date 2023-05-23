<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>create</title>
</head>
<body>
	<h2>create new lead</h2>
	<form action="saveLead" method="post">
		<pre>
First Name <input type="text" name="firstName" />
last Name <input type="text" name="lastName" />
Email <input type="text" name="email" />
mobile <input type="text" name="mobile" />
<input type="submit" value="save" />
</pre>
	</form>
	${msg}
</body>
</html>