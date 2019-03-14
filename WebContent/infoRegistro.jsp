<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Menu principal</title>
<link rel="stylesheet" type="text/css" href="estilo.css">
</head>
<body>
	<%
		HttpSession sesion = request.getSession();
		Object usuario_obj = sesion.getAttribute("usuario");
		String usuario = usuario_obj.toString();
	%>
	<div class="cuerpo">
		<div class="nav-izq">
			<p>Usuario: <label id="label-user"><% out.println(usuario); %></label></p>
			<a href="infoRegistro.jsp">Menu principal</a>
			<a href="perfil.jsp">Mi perfil</a>
			<a href="logOut">Cerrar sesión</a>
		</div>
		<div class="contenido">
			<div class="cabecera"><h1>Menu principal</h1></div>
			<p><a href="noticias-recientes">Noticias recientes</a></p>
		</div>
	</div>
</body>
</html>