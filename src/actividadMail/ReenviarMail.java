package actividadMail;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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

@WebServlet(urlPatterns = "/reenviarMail")

public class ReenviarMail extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// Procedimiento para enviar correo
		Properties properties = new Properties();
		properties.put("mail.transport.protocol", "smtp");
		Session mailSession = Session.getInstance(properties);
		Message msg = new MimeMessage(mailSession);
		
		try {
			
			msg.setFrom(new InternetAddress("fpdespliegue7@gmail.com"));
			msg.setRecipient(Message.RecipientType.TO, new InternetAddress(req.getParameter("email")));
			msg.setSentDate(new Date());
			msg.setSubject("Confirmación de registro en samuel_mail.com");
		
			StringBuilder sb = new StringBuilder();
			
			sb.append("<p>Confirma tu dirección de correo</p>");
			sb.append("<p><a href='http://localhost:8080/actividadMail/confirmar?id=");
			MessageDigest md2 = MessageDigest.getInstance("SHA-512");
			byte [] a2 = md2.digest((req.getParameter("usuario") + req.getParameter("email")).getBytes());
			sb.append(DatatypeConverter.printHexBinary(a2));
			sb.append("&usuario=");
			sb.append(req.getParameter("usuario"));
			sb.append("'>Confirmar</a></p>");
			
			msg.setContent(sb.toString(), "text/html");
			Transport.send(msg);
					
			req.getRequestDispatcher("/infoRegistro.jsp").forward(req, resp);
						
		} catch (MessagingException | NoSuchAlgorithmException e) {
			throw new ServletException(e);
		}
		//
	}
	
}
