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
		PrintWriter pw;
		Connection conexion = null;
		String usuario = null, clave = null, nombre = null, apellidos = null, telefono = null, hashEmail = null, email = null;
		try {
			
			pw = resp.getWriter();
			
			usuario = req.getParameter("usuario");
			clave = req.getParameter("clave");
			MessageDigest md = MessageDigest.getInstance("SHA-512");
			byte [] a = md.digest((usuario + clave).getBytes());
			clave = DatatypeConverter.printHexBinary(a);
			nombre = req.getParameter("nombre");
			apellidos = req.getParameter("apellidos");
			telefono = req.getParameter("telefono");
			email = req.getParameter("email");
			MessageDigest md2 = MessageDigest.getInstance("SHA-512");
			byte [] a2 = md2.digest((req.getParameter("usuario") + req.getParameter("email")).getBytes());
			hashEmail = DatatypeConverter.printHexBinary(a2);
			
			conexion = DriverManager.getConnection ("jdbc:mysql://localhost/mail", "root", "practicas");
			PreparedStatement ps = conexion.prepareStatement("INSERT INTO usuarios(usuario, clave, nombre, apellidos, telefono, email, hashEmail) VALUES(?, ?, ?, ?, ?, ?, ?)");
			
			ps.setString(1, usuario);
			ps.setString(2, clave);
			ps.setString(3, nombre);
			ps.setString(4, apellidos);
			ps.setString(5, telefono);
			ps.setString(6, email);
			ps.setString(7, hashEmail);
			
			ps.executeUpdate();
			
			req.getRequestDispatcher("/registroConf").forward(req, resp);
			
		} catch (SQLException | NoSuchAlgorithmException e) {
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
