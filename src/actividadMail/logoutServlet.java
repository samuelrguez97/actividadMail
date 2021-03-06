package actividadMail;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet(urlPatterns = "/logOut")

public class logoutServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession sesion = req.getSession(false);
		if(sesion.getAttribute("usuario") == null) {
			resp.sendRedirect("login.html");
		} else {
//			sesion.removeAttribute("usuario");
			sesion.invalidate();
			resp.sendRedirect("login.html");
		}
	}
}
