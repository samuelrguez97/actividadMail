package actividadMail;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter(urlPatterns = {"/infoRegistro.jsp", "/perfil.jsp", "/noticias-recientes", "/noticias-nueva.jsp", "/insertar-noticia"})
public class loginFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpSession sesion = ((HttpServletRequest) request).getSession(false);
		if (sesion == null || sesion.getAttribute("usuario") == null) {
			((HttpServletResponse) response).sendRedirect("login.html");
		} else {
			chain.doFilter(request, response);
		}
	}
}
