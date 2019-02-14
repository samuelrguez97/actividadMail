package actividadMail;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;

@WebServlet(urlPatterns = "/registro")

public class registroServlet extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		
		// Procedimiento para enviar correo
		Properties properties = new Properties();
		properties.put("mail.transport.protocol", "smtp");
		Session mailSession = Session.getInstance(properties);
		Message msg = new MimeMessage(mailSession);
		try {
			msg.setFrom(new InternetAddress("fpdespliegue7@gmail.com"));
			msg.setRecipient(Message.RecipientType.TO, new InternetAddress(req.getParameter("email")));
			msg.setSentDate(new Date());
			msg.setSubject("Confirmación de registro en loquesea.com");
			StringBuilder sb = new StringBuilder();
			sb.append("<p>Confirma tu dirección de correo</p>");
			sb.append("<p><a href=\"http://localhost:8080/confirmar?id=");
			MessageDigest md = MessageDigest.getInstance("SHA-512");
			byte [] a = md.digest((req.getParameter("usuario") + req.getParameter("email")).getBytes());
			sb.append(DatatypeConverter.printHexBinary(a));
			sb.append("\">Confirmar</a></p>");
			msg.setContent(sb.toString(), "text/html");
			Transport.send(msg);
			req.getRequestDispatcher("inforegistro").forward(req, resp);;
		} catch (MessagingException | NoSuchAlgorithmException e) {
			throw new ServletException(e);
		}
		//
		
		PrintWriter pw;
		Connection conexion = null;
		String usuario = null, clave = null, nombre = null, apellidos = null, telefono = null, email = null;
		try {
			
			pw = resp.getWriter();
			
			usuario = req.getParameter("usuario");
			clave = req.getParameter("clave");
			nombre = req.getParameter("nombre");
			apellidos = req.getParameter("apellidos");
			telefono = req.getParameter("telefono");
			
			conexion = DriverManager.getConnection ("jdbc:mysql://localhost/mail", "root", "practicas");
			PreparedStatement ps = conexion.prepareStatement("INSERT INTO usuarios(usuario, clave, nombre, apellidos, telefono) VALUES(?, ?, ?, ?, ?)");
			
			ps.setString(1, usuario);
			ps.setString(2, clave);
			ps.setString(3, nombre);
			ps.setString(4, apellidos);
			ps.setString(5, telefono);
			
			int comp = ps.executeUpdate();
			
			if (comp == 1) {
				pw.println("<h3>Se ha registrado al usuario con éxito, entre <a href='login.html'>aquí</a></h3>");
			} else {
				pw.println("<h3>No se ha podido registrar al usuario, pruebe de nuevo <a href='registro.html'>aquí</a></h3>");
			}
			
		} catch (SQLException e) {
			resp.resetBuffer();
			throw new ServletException(e);
		} finally {
			try {
				if(conexion != null) {
					conexion.close();
				}
			} catch (SQLException e) {
				throw new ServletException(e);
			}
		}
	}
}
