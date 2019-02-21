<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Menu principal</title>
</head>
<body>
	<%
		HttpSession sesion = request.getSession();
		Object usuario_obj = sesion.getAttribute("usuario");
		String usuario = usuario_obj.toString();
	%>
	<h1>Menu principal</h1>
	<p>Hola <% out.println(usuario); %></p>
	<p>Bienvenido a la página principal de la web.</p>
	<%
	
		Connection conexion = DriverManager.getConnection ("jdbc:mysql://localhost/mail", "root", "practicas");
		Statement s = conexion.createStatement();
		ResultSet rs = s.executeQuery ("SELECT hashEmail FROM usuarios WHERE usuario = '" + usuario + "'");
		
		if (rs.next()) {
			if (rs.getString(1).equals("")) {
				
		
	%>
	
	<p><% out.println("Ha confirmado el email."); %></p>
	
	<%
			} else {
		
	%>
	
	<p><% out.println("No ha confirmado el email."); %></p>
	
	<%
			}
		}
	%>
	<a href="logOut">Salir</a>
</body>
</html>