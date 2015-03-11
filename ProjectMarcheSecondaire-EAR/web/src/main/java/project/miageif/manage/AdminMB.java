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

@ManagedBean(name = "adminMB")
@SessionScoped
public class AdminMB {

	
	
}