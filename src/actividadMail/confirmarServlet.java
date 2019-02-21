package actividadMail;

import java.io.IOException;
import java.security.MessageDigest;
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
import javax.servlet.http.HttpSession;
import javax.xml.bind.DatatypeConverter;

@WebServlet(urlPatterns = "/confirmar")
public class confirmarServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String usuario = req.getParameter("usuario");
		Connection conexion = null;
		try {
			
			conexion = DriverManager.getConnection ("jdbc:mysql://localhost/mail", "root", "practicas");
			Statement s = conexion.createStatement();
			ResultSet rs = s.executeQuery ("SELECT hashEmail FROM usuarios WHERE usuario = '" + usuario + "'");
			
			if (rs.next()) {
				String hashEmail = rs.getString(1);
				String hashEmail_id = req.getParameter("id");
				
				if (hashEmail.equals(hashEmail_id)) {
					PreparedStatement ps = conexion.prepareStatement("UPDATE usuarios SET hashEmail = '' WHERE usuario = '" + usuario + "'");
					ps.executeUpdate();
					resp.sendRedirect("infoRegistro.jsp");
				}
				
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
