package actividadMail;

import javax.inject.Named;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;

@Named
@SessionScoped
public class holaMundo implements Serializable {
	public String getSaludo() {
		return "hola";
	}
}
