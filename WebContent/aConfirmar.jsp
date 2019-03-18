<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Confirmación del mail</title>
<link rel="stylesheet" type="text/css" href="estilo.css">
</head>
<body>
	<%
		String usuario = request.getParameter("usuario");
	%>
	<div class="cuerpo">
		<div class="cabecera"><h1>Confirmación mail</h1></div>
		<p>Estimado <% out.println(usuario); %>, se le ha enviado un mail de confirmación.</p>
		<p>Hasta que no sea confirmado, puede tener limitaciones en la navegación.</p>
		<p><a href="/actividadMail/index.html">Volver al index</a></p>
	</div>
</body>
</html>