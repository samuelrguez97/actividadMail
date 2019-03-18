package actividadMail;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/insertar-noticia")

public class NuevaNoticia extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.setContentType("text/html");
		PrintWriter pw = null;
		Connection conexion = null;
		ResultSet rs = null;
		
		try {
			
			pw = resp.getWriter();
			String titulo = req.getParameter("titulo");
			String localizacion = req.getParameter("localizacion");
			
			String fecha1 = req.getParameter("fecha");
			SimpleDateFormat sdf1 = new SimpleDateFormat("dd-mm-yyyy");
			java.util.Date fecha = sdf1.parse(fecha1);
			java.sql.Date sqlfecha = new java.sql.Date(fecha.getTime());
			
			String descripcion = req.getParameter("descripcion");
			String fuente = req.getParameter("fuente");
			
			conexion = DriverManager.getConnection ("jdbc:mysql://localhost/mail", "root", "practicas");
			
			PreparedStatement ps = conexion.prepareStatement("INSERT INTO noticias(titulo, localizacion, fecha, descripcion, fuente) VALUES(?, ?, ?, ?, ?)");
			
			ps.setString(1, titulo);
			ps.setString(2, localizacion);
			ps.setDate(3, sqlfecha);
			ps.setString(4, descripcion);
			ps.setString(5, fuente);
			
			if (ps.executeUpdate() > 0) {
				pw.println("<p>Se ha isnertado la noticia correctamente, <a href='/actividadMail/infoRegistro.jsp'>volver al menu</a>.</p>");
			} else {
				pw.println("<p>Lo sentimos, no se ha podido insertar la noticia correctamente, <a href='/actividadMail/noticias-nueva.jsp'>intentelo de nuevo</a>.</p>");
			}
			
			
		} catch (SQLException | ParseException e) {
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

