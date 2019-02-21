package actividadMail;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
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

@WebServlet(urlPatterns = "/login")

public class loginServlet extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		PrintWriter pw;
		Connection conexion = null;
		ResultSet rs = null;
		String usuario = null;
		String clave = null;
		HttpSession sesion = null;
		try {
			usuario = req.getParameter("usuario");
			clave = req.getParameter("clave");
			MessageDigest md = MessageDigest.getInstance("SHA-512");
			byte [] a = md.digest((usuario + clave).getBytes());
			clave = DatatypeConverter.printHexBinary(a);
			pw = resp.getWriter();
			conexion = DriverManager.getConnection ("jdbc:mysql://localhost/mail", "root", "practicas");
			Statement s = conexion.createStatement();
			rs = s.executeQuery ("SELECT usuario FROM usuarios WHERE usuario = '"+ usuario +"' AND clave = '"+ clave +"'");
			if(rs.next()) {
				sesion = req.getSession();
				sesion.setAttribute("usuario", usuario);
				resp.sendRedirect("infoRegistro.jsp");
			} else {
				resp.sendRedirect("error.html");
			}
		} catch (SQLException | NoSuchAlgorithmException e) {
			resp.resetBuffer();
			throw new ServletException(e);
		} finally {
			try {
				if(rs != null) {
					rs.close();
				}
				if(conexion != null) {
					conexion.close();
				}
			} catch (SQLException e) {
				throw new ServletException(e);
			}
			
		}
	}
}
