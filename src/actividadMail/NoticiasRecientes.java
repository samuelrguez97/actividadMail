package actividadMail;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/noticias-recientes")

public class NoticiasRecientes extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.setContentType("text/html");
		PrintWriter pw = null;
		Connection conexion = null;
		ResultSet rs = null;
		
		try {
			
			pw = resp.getWriter();
			conexion = DriverManager.getConnection ("jdbc:mysql://localhost/mail", "root", "practicas");
			Statement s = conexion.createStatement();
			rs = s.executeQuery ("SELECT * FROM noticias");
			
			pw.println("<html>");
			pw.println("<head>");
			pw.println("<meta charset=\'UTF-8\'>");
			pw.println("<title>Noticias recientes</title>");
			pw.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"estilo.css\">");
			pw.println("</head>");
			pw.println("<body>");
			pw.println("<div class='cabecera'>"
					+ "<h1 id='titulo_noticias'>Noticias recientes</h1>"
					+ "<a id='boton-volver' href='/actividadMail/infoRegistro.jsp'><button>Volver al menú</button></a>"
					+ "</div>");
			while (rs.next()) {
				pw.println("<section class='noticia'>");
				pw.println("<p><strong>Titulo:</strong> "+ rs.getString(2) +"</p>");
				pw.println("<p><strong>Localización: </strong> "+ rs.getString(3) +"</p>");
				pw.println("<p><strong>Fecha: </strong> "+ rs.getDate(4) +"</p>");
				pw.println("<p><strong>Descripción: </strong> "+ rs.getString(5) +"</p>");
				pw.println("<p><strong>Fuente: </strong><a href="+ rs.getString(6) +">"+ rs.getString(6) +"</a></p>");
				pw.println("<hr>");
				pw.println("</section>");
			}
			pw.println("</body>");
			pw.println("</html>");
			
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
