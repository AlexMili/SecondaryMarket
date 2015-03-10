package project.miageif.manage;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.SQLQuery;
import org.hibernate.Session;

import project.miageif.beans.Administrateur;
import project.miageif.beans.Investisseur;
import project.miageif.beans.Utilisateur;
import project.miageif.beans.Utilisateur.Status;
import project.miageif.beans.Utilisateur.Type;
import project.miageif.services.AdministrateurService;
import project.miageif.services.InvestisseurService;
import project.miageif.services.UtilisateurService;
import project.miageif.utilitaire.HibernateUtil;

@ManagedBean(name = "userMB")
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
	@EJB
	private InvestisseurService investService;

	public UserMB() {
		this.user = new Utilisateur();
		this.investisseur = new Investisseur();
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
				|| user.getLogin().equals("Nom utilisateur"))
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


		if (user.getType().equals(Utilisateur.Type.ADMIN)) {
			setAdmin(AdminService.findAdminByID(user.getId()));
			if (Admin == null || Admin.equals(null))
				return "/pages/public/loginError.xhtml?faces-redirect=true";
			user.setStatus(Status.CONNECTED);
			user = userService.userUpdate(user);
			isLogged = true;
			return "AdminConf";
			// isConnected();
		}
		
		if (user.getType().equals(Utilisateur.Type.INVEST)) {
			setInvestisseur(investService.findInvestByID(user.getId()));
			if (investisseur == null || investisseur.equals(null))
				return "/pages/public/loginError.xhtml?faces-redirect=true";
			user.setStatus(Status.CONNECTED);
			investisseur.setStatus(Status.CONNECTED);
			user = userService.userUpdate(user);
			isLogged = true;
			return "investisseur";
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
		System.out.println("******** Fin logout ****");
		return "logout";
	}

	public String createInvest(){
		//investisseur.setType(Type.INVEST);
		//userService.createUser(user);
		//user=userService.findUserByLoginPass(user.getLogin(), user.getPassword());
		//investisseur.setUser(user);
		
		investService.createInvest(investisseur);
		return "login";
	}

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
		System.out.println("****************** Destruction Session Bean 1");
		if (user != null) {
			user.setStatus(Status.DISCONNECTED);
			try{
				Session session = HibernateUtil.getSessionFactory().openSession();
		        session.beginTransaction();
		        SQLQuery q = session.createSQLQuery("update UTILISATEUR u set u.status='0' where u.Id_Utilisateur='"+user.getId()+"'");
		        int result =q.executeUpdate();
		        session.getTransaction().commit();
				//user = userService.userUpdate(user);
			}catch(Exception e){
			
			}
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