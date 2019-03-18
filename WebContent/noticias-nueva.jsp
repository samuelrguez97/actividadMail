<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insertar noticia</title>
<link rel="stylesheet" type="text/css" href="estilo.css">
</head>
<body>
	<%
		HttpSession sesion = request.getSession();
		Object usuario_obj = sesion.getAttribute("usuario");
		String usuario = usuario_obj.toString();
	%>
	<div class='cabecera'>
		<h1 id='titulo_noticias'>Insertar noticia</h1>
		<a id='boton-volver' href='/actividadMail/infoRegistro.jsp'><button>Volver al menú</button></a>
	</div>
	<div class="cuerpo">
	<div class="contenido">
	<%
		Connection conexion = DriverManager.getConnection ("jdbc:mysql://localhost/mail", "root", "practicas");
		Statement s = conexion.createStatement();
		ResultSet rs = s.executeQuery ("SELECT hashEmail FROM usuarios WHERE usuario = '" + usuario + "'");
		
		if(rs.next()) {
			
			if (rs.getString(1).equals("")) {
						
	%>
	<form action="insertar-noticia" method="post">
		<table>
			<tr>
				<td><strong>Titulo:</strong></td>
				<td><input type="text" name="titulo" required/></td>
			</tr>
			<tr>
				<td><strong>Localizacion:</strong></td>
				<td><input type="text" name="localizacion" required/></td>
			</tr>
			<tr>
				<td><strong>Fecha:</strong></td>
				<td><input type="date" name="fecha" required/></td>
			</tr>
			<tr>
				<td><strong>Descripción:</strong></td>
				<td><input type="text" name="descripcion" required/></td>
			</tr>
			<tr>
				<td><strong>Fuente:</strong></td>
				<td><input type="text" name="fuente" required/></td>
			</tr>
		</table>
		<button type="submit" name="submit">Enviar</button>
	</form>
	<%
			} else {
	%>
	<p class="error">Lo sentimos, este área solo es accesible a usuarios con la confirmación por email.</p>
	<%
			}
		}
	%>
	</div>
	</div>
</body>
</html>