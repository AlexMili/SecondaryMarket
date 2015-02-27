package project.miageif.manage;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;

import project.miageif.beans.Administrateur;
import project.miageif.beans.Utilisateur;
import project.miageif.controler.NavigationController;
import project.miageif.services.AdministrateurService;
import project.miageif.services.UtilisateurService;
import project.miageif.utilitaire.HibernateUtil;

@ManagedBean
@RequestScoped
public class UserMB {

	private Utilisateur user;
	private Administrateur Admin;
	private boolean isLogged = false;
	
	@EJB
	private UtilisateurService userService;
	
	@EJB
	private AdministrateurService AdminService;

	public UserMB() {
		this.user=new Utilisateur();
	}

	public Utilisateur getUser() {
		return user;
	}
	
	public Administrateur getAdmin() {
		return Admin;
	}

	
	/*Permet à l'administrateur de se connecter*/
	public String login() {
		user = userService.findUserByLoginPass(this.user.getLogin(),this.user.getPassword());
		//user.setStatus("Connected");
		//userService.userUpdate(user);
		if (user == null || user.equals(null))
			return "/pages/public/loginError.xhtml?faces-redirect=truer";
		if(user.getType().equals("ADMIN")){
			//getRequest().login(user.getLogin(), user.getPassword());
//			SessionFactory sessionFactory =  HibernateUtil.getSessionFactory();
//			Session session = sessionFactory.getCurrentSession();
//	        session.beginTransaction();
//	        Admin = (Administrateur) session.get(Administrateur.class, user.getId());
			setAdmin(AdminService.findAdminByID(user.getId()));
	        if (Admin == null || Admin.equals(null))
				return "/pages/public/loginError.xhtml?faces-redirect=true";
			user.setStatus("Connected");
			user = userService.userUpdate(user);
//			Admin =(Administrateur) session.merge(Admin);
//			System.out.println("******** Dans logout *** * " + Admin.getStatus());
//	        session.getTransaction().commit();
//			this.Admin = new Administrateur();
//			this.setAdmin(new Administrateur());
//			setAdmin(AdminService.findAdminByID(user.getId()));
			
//			Admin = AdminService.updateAdmin(Admin);
//			System.out.println("******** Dans logout ****"+ user.getStatus());
//			HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().commit();
			
			//System.out.println("******************* "+isUserAdmin());
		    ///getRequest().getSession().setAttribute("role", "ADMIN");
			FacesContext facesContext = FacesContext.getCurrentInstance();
			HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
			session.setAttribute("CURRENT_USER", user);
			System.out.println("******** Connecter go redirect ****");
			return "AdminConf";
			//isConnected();
			
		}
		return "Acceuil";
	}

	public String userLogout() {
		isLogged=false;
		System.out.println("******** Dans logout ****");
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
		user = (Utilisateur) session.getAttribute("CURRENT_USER");
		user.setStatus("DisConnected");
		user = userService.userUpdate(user);
		session.removeAttribute("CURRENT_USER");
		getRequest().getSession().invalidate();
		return "logout";
		}
	

	private HttpServletRequest getRequest() {
		return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
	}

	public void setAdmin(Administrateur admin) {
		Admin = admin;
	}

	public boolean isLogged() {
		return isLogged;
	}
	
	public void setLogged(boolean isLogged) {
		this.isLogged = isLogged;
	}
}