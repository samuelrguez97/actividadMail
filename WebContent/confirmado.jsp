<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Email confirmado</title>
<link rel="stylesheet" type="text/css" href="estilo.css">
</head>
<body>
	<%
		String usuario = request.getParameter("usuario");
	%>
	<div class="cuerpo">
		<div class="cabecera"><h1>Confirmación mail</h1></div>
		<p class="success">Enhorabuena <% out.println(usuario); %>, ha confirmado su mail!</p>
		<p><a href="/actividadMail/login.html">Entra desde aquí</a></p>
	</div>
</body>
</html>