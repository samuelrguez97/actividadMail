<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Perfil</title>
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
			<div class="cabecera"><h1>Perfil</h1></div>
			<%
			
				Connection conexion = DriverManager.getConnection ("jdbc:mysql://localhost/mail", "root", "practicas");
				Statement s = conexion.createStatement();
				ResultSet rs = s.executeQuery ("SELECT * FROM usuarios WHERE usuario = '" + usuario + "'");
				
				if (rs.next()) {
			%>
			<p><strong>ID de usuario: </strong><% out.println(rs.getString(1)); %></p>
			<p><strong>Email: </strong><% out.println(rs.getString(7)); %></p>
			<p><strong>Nombre: </strong><% out.println(rs.getString(4)); %></p>
			<p><strong>Apellidos: </strong><% out.println(rs.getString(5)); %></p>
			<p><strong>Teléfono: </strong><% out.println(rs.getString(6)); %></p>
			<%
					if (rs.getString(8).equals("")) {
						
				
			%>
			
			<p><% out.println("Ha confirmado el email."); %></p>
			
			<%
					} else {
				
			%>
			
			<p><% out.println("No ha confirmado el email."); %></p>
			<form method="post" action="reenviarMail" name="form">
				<input name="usuario" type="hidden" value="<% rs.getString(2); %>" />
				<input name="email" type="hidden" value="<% rs.getString(7); %>" />
				<a href="javascript:document.form.submit()">Reenviar mail de confirmacion</a>
			</form>
			
			<%
					}
				}
			%>
		</div>
	</div>
</body>
</html>