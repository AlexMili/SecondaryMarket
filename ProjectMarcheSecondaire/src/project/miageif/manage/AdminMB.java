package project.miageif.manage;

import static org.junit.Assert.assertEquals;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.junit.Test;

import project.miageif.beans.Administrateur;
import project.miageif.services.AdministrateurService;

@ManagedBean
@RequestScoped
public class AdminMB {

	private Administrateur user;

	@EJB
	private AdministrateurService userFacade;

	public AdminMB() {
		this.user = new Administrateur();
	}

	public Administrateur getUser() {
		return user;
	}

	public boolean isUserAdmin() {
		return getRequest().isUserInRole("ADMIN");
	}
	
	/*Permet à l'administrateur de se connecter*/
	@Test
	public String login() {
		Administrateur a = userFacade.findUserByLoginPass(this.user.getLogin(),this.user.getPassword());
		if (a == null || a.equals(null))
			return "loginError";
		
		//System.out.println("************************** "+user.getId());
		return "loginOk";
	}

	public String logOut() {
		getRequest().getSession().invalidate();
		return "logout";
	}

	private HttpServletRequest getRequest() {
		return (HttpServletRequest) FacesContext.getCurrentInstance()
				.getExternalContext().getRequest();
	}
}