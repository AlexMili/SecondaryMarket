package project.miageif.manage;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import project.miageif.beans.Administrateur;
import project.miageif.beans.Investisseur;
import project.miageif.beans.Utilisateur;
import project.miageif.beans.Utilisateur.Status;
import project.miageif.services.AdministrateurService;
import project.miageif.services.UtilisateurService;

@ManagedBean(name = "userMB")
// @RequestScoped
@SessionScoped
public class UserMB {

	private Utilisateur user;
	private Administrateur Admin;
	private Investisseur investisseur;
	private boolean isLogged = false;

	@EJB
	private UtilisateurService userService;

	@EJB
	private AdministrateurService AdminService;

	public UserMB() {
		this.user = new Utilisateur();
	}

	public Utilisateur getUser() {
		return user;
	}

	public Administrateur getAdmin() {
		return Admin;
	}

	/* Permet à l'administrateur de se connecter */
	public String login() {
		if (user == null || user.getLogin().equals(" ")
				|| user.getLogin() == null
				|| user.getLogin().equals("Nom d'utilisateur"))
			return "loginError";
		if (user == null || user.getPassword().equals(" ")
				|| user.getPassword() == null
				|| user.getPassword().equals("Password"))
			return "loginError";
		user = userService.findUserByLoginPass(this.user.getLogin(),
				this.user.getPassword());
		// user.setStatus("Connected");
		// userService.userUpdate(user);
		if (user == null || user.equals(null))
			return "/pages/public/loginError.xhtml?faces-redirect=truer";

		/* Création d'une session */
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) facesContext.getExternalContext()
				.getSession(true);
		session.setAttribute("CURRENT_USER", user);

		isLogged = true;

		if (user.getType().equals(Utilisateur.Type.ADMIN)) {
			setAdmin(AdminService.findAdminByID(user.getId()));
			if (Admin == null || Admin.equals(null))
				return "/pages/public/loginError.xhtml?faces-redirect=true";
			user.setStatus(Status.CONNECTED);
			user = userService.userUpdate(user);
			return "AdminConf";
			// isConnected();
		}

		return "Acceuil";
	}

	public String userLogout() {
		isLogged = false;
		System.out.println("******** Dans logout ****");
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) facesContext.getExternalContext()
				.getSession(true);
		// user = (Utilisateur) session.getAttribute("CURRENT_USER");
		// if(user==null) return "/pages/public/loginError.xhtml";
		user.setStatus(Status.DISCONNECTED);
		user = userService.userUpdate(user);
		session.removeAttribute("CURRENT_USER");
		session.removeAttribute("userMB");
		getRequest().getSession().invalidate();
		return "logout";
	}

	// @PostConstruct @PreDestroy
	// public void sessionInitialized() {
	// // ...
	// }

	// @PostConstruct
	// public String sessionDestroyed() {
	// isLogged=false;
	// System.out.println("******** Dans logout ****");
	// FacesContext facesContext = FacesContext.getCurrentInstance();
	// HttpSession session = (HttpSession)
	// facesContext.getExternalContext().getSession(true);
	// user = (Utilisateur) session.getAttribute("CURRENT_USER");
	// if(user==null) return "/pages/public/loginError.xhtml";
	// user.setStatus(Status.DISCONNECTED);
	// user = userService.userUpdate(user);
	// session.removeAttribute("CURRENT_USER");
	// session.removeAttribute("CURRENT_USER_ADMIN");
	// getRequest().getSession().invalidate();
	// return "Acceuil";
	// }

	private HttpServletRequest getRequest() {
		return (HttpServletRequest) FacesContext.getCurrentInstance()
				.getExternalContext().getRequest();
	}

	public void setAdmin(Administrateur admin) {
		Admin = admin;
	}

	public boolean isLogged() {
		return isLogged;
	}

	@PostConstruct
	private void pconstruct() {
		System.out.println("PostConstructing Session Bean 1");
	}

	/* Quand la session est détruite on met à jour le status en BD */
	@PreDestroy
	public void destroy() {
		isLogged = false;
		if (user != null) {
			user.setStatus(Status.DISCONNECTED);
			user = userService.userUpdate(user);
		}
	}

	public void setLogged(boolean isLogged) {
		this.isLogged = isLogged;
	}

	public Investisseur getInvestisseur() {
		return investisseur;
	}

	public void setInvestisseur(Investisseur investisseur) {
		this.investisseur = investisseur;
	}
}