package project.miageif.manage;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.faces.application.ViewHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.SQLQuery;
import org.hibernate.Session;

import project.miageif.beans.Administrateur;
import project.miageif.beans.Contrat;
import project.miageif.beans.Investisseur;
import project.miageif.beans.Membre;
import project.miageif.beans.Offre;
import project.miageif.beans.Societe;
import project.miageif.beans.Utilisateur;
import project.miageif.beans.Utilisateur.Status;
import project.miageif.beans.Utilisateur.Type;
import project.miageif.beans.Utilisateur.Etat_Offre;
import project.miageif.beans.Utilisateur.Type_Offre;
import project.miageif.services.AdministrateurService;
import project.miageif.services.ContratService;
import project.miageif.services.InvestisseurService;
import project.miageif.services.MembreService;
import project.miageif.services.OffreService;
import project.miageif.services.SocieteService;
import project.miageif.services.UtilisateurService;
import project.miageif.utilitaire.HibernateUtil;


@ManagedBean(name = "userMB")
@SessionScoped
public class UserMB {

	private Utilisateur user;
	private Administrateur Admin;
	private Investisseur investisseur;
	private Membre membre;
	private Societe societe;
	private Contrat contrat;
	private Offre offre;
	private List<Offre> offres;
	private List<Societe> listSociete;
	private boolean isLogged = false;

	@EJB
	private UtilisateurService userService;
	@EJB
	private AdministrateurService AdminService;
	@EJB
	private InvestisseurService investService;
	@EJB
	private SocieteService societeService;
	@EJB
	private MembreService membreService;
	@EJB
	private ContratService contratService;
	@EJB
	private OffreService offreService;

	public UserMB() {
		this.user = new Utilisateur();
		this.investisseur = new Investisseur();
		this.membre = new Membre();
		this.societe = new Societe();
		this.contrat = new Contrat();
		this.offre = new Offre();
	}

	@PostConstruct
	private void init() {
		if(user==null) user = new Utilisateur();
		System.out.println("PostConstructing Session Bean 1");
		listSociete = societeService.findAll();
	}

	public void init2() {
		//this.user = new Utilisateur();
		//this.investisseur = new Investisseur();
		//this.membre = new Membre();
		this.societe = new Societe();
		//this.contrat = new Contrat();
		this.offre = new Offre();
	}

	public Utilisateur getUser() {
		return user;
	}

	public Administrateur getAdmin() {
		return Admin;
	}

	public List<Societe> getListSociete() {
		return this.listSociete;
	}
	
	public void getListSocieteEgear() {
		this.listSociete = societeService.findAll();
	}

	public void setListSociete(List<Societe> lis) {
		this.listSociete = lis;
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

		if (user.getType().equals(Utilisateur.Type.INVEST)) {
			setInvestisseur(investService.findInvestByID(user.getId()));
			if (investisseur == null || investisseur.equals(null))
				return "/pages/public/loginError.xhtml?faces-redirect=true";
			user.setStatus(Status.CONNECTED);
			user = userService.userUpdate(user);
			return "investisseur";
			// isConnected();
		}

		if (user.getType().equals(Utilisateur.Type.MEMBER)) {
			setMembre(membreService.findMembreByID(user.getId()));
//			setSociete(societeService.findSocieteByID(membre.getSociete().getId()));
			if (membre == null || membre.equals(null))
				return "/pages/public/loginError.xhtml?faces-redirect=true";
			user.setStatus(Status.CONNECTED);
			user = userService.userUpdate(user);
			return "membre";
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
		return "/pages/public/login.xhtml?faces-redirect=true";
	}

	public String createInvest() {
		user.setType(Type.INVEST);
		userService.createUser(user);
		user = userService.findUserByLoginPass(user.getLogin(),
				user.getPassword());
		investisseur.setUser(user);
		investService.createInvest(investisseur);
		getRequest().getSession().invalidate();
		return "login";
	}
	
	public String create_offre(){
		System.out.println("**************************** ID = "+contrat.getId());
		contrat = contratService.findContratByID(contrat.getId());
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
			offre.setContrat(contrat);
			offre.setDate(new Date());
			if(offre.getType()==Type_Offre.ENCHERE_NON){
				offre.setDatedispo(new Date());
			}
			offreService.createOffre(offre);
			contrat.getOffres().add(offre);
			session.update(contrat);
		session.getTransaction().commit();
		return "investisseur.xhtml?faces-redirect=true";
	}

	private HttpServletRequest getRequest() {
		return (HttpServletRequest) FacesContext.getCurrentInstance()
				.getExternalContext().getRequest();
	}
	
	public String transaction(){
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		societe = offre.getContrat().getSociete();
		contrat = offre.getContrat();
		Investisseur inv =  offre.getContrat().getUser();
		offre.setEtat(Etat_Offre.VENDUE);
		offre.setId_acheteur(investisseur.getId());
		inv.setSolde(offre.getContrat().getUser().getSolde()+offre.getPrix()*offre.getQuantiteTitre());
		contrat.setQuantite(offre.getContrat().getQuantite()-offre.getQuantiteTitre());
		offreService.updateOffre(offre);
		Contrat c = new Contrat();
		c.setDate(new Date());
		c.setSociete(societe);
		c.setUser(investisseur);
		c.setQuantite(offre.getQuantiteTitre());
		contratService.createContrat(c);
		investisseur.setSolde(investisseur.getSolde()-offre.getQuantiteTitre()*offre.getPrix());
		investisseur.getContrats().add(c);
		session.update(inv);
		session.update(contrat);
		session.update(investisseur);
		session.update(societe);
		session.getTransaction().commit();
		return "investisseur.xhtml?faces-redirect=true";
	}

	public void setAdmin(Administrateur admin) {
		Admin = admin;
	}

	public boolean isLogged() {
		return isLogged;
	}

	/* Quand la session est détruite on met à jour le status en BD */
	@PreDestroy
	public void destroy() {
		isLogged = false;
		System.out.println("****************** Destruction Session Bean 1");
		if (user != null) {
			user.setStatus(Status.DISCONNECTED);
			try {
				Session session = HibernateUtil.getSessionFactory()
						.openSession();
				session.beginTransaction();
				SQLQuery q = session
						.createSQLQuery("update UTILISATEUR u set u.status='0' where u.Id_Utilisateur='"
								+ user.getId() + "'");
				int result = q.executeUpdate();
				session.getTransaction().commit();
				// user = userService.userUpdate(user);
			} catch (Exception e) {

			}
		}
	}

	public String incriptionMembre() {
		try {
			user.setType(Type.MEMBER);
			userService.createUser(user);
			user = userService.findUserByLoginPass(user.getLogin(),
					user.getPassword());
			societe = societeService.findSocieteByName(societe.getNom());
			membre.setUser(user);
			membre.setSociete(societe);
			membreService.createMembre(membre);
			membre = membreService.findMembreByID(user.getId());
			Session session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			societe.getMenbre().add(membre);
			session.update(societe);
			session.getTransaction().commit();
			init();
			getRequest().getSession().invalidate();
			return "login";
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}

	}
	
	/*Permet de réaliser un achat*/
	public String achatTitres(){
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		contrat.setSociete(societe);
		contrat.setUser(investisseur);
		contrat.setDate(new Date());
		contratService.createContrat(contrat);
		investisseur.setSolde(investisseur.getSolde()-(contrat.getQuantite()*societe.getPrixTitre()));
		societe.setNbTitreVendue(societe.getNbTitreVendue()+contrat.getQuantite());
		societe.getContrat().add(contrat);
		investisseur.getContrats().add(contrat);
		session.update(investisseur);
		session.update(societe);
		session.getTransaction().commit();
		return "investisseur.xhtml";
	}
	
	public String update_invest(){
		Investisseur i = investService.findInvestByID(investisseur.getUser().getId());
		investisseur.setSolde(i.getSolde()+investisseur.getSolde());
		investisseur = investService.updateInvest(investisseur);
		return "investisseur.xhtml";
	}
	
	public String update_soc(){
		societe = membre.getSociete();
		societe = societeService.updateSociete(societe);
		return "membre.xhtml";
	}
	
	public String update_memb(){
		membre = membreService.updateMembre(membre);
		return "membre.xhtml";
	}

	public void refresh() {
		init();
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

	public Societe getSociete() {
		return societe;
	}

	public void setSociete(Societe societe) {
		this.societe = societe;
	}

	public Membre getMembre() {
		return membre;
	}

	public void setMembre(Membre membre) {
		this.membre = membre;
	}

	/* Creation société */
	public String create_soc() {
		societeService.createSociete(societe);
		init();
		return "soc_creer";
	}
	
	public Utilisateur getInstanceUser(){
		this.user = new Utilisateur();
		return this.user;
	}
	
	public void setSocieterEager(){
		investisseur = investService.findInvestByID(user.getId());
		societe = societeService.findSocieteByName(societe.getNom());
		if(societe == null) {
			societe = new Societe();
			societe.setNom("selectionner");
		}
	}
	
	public void offresEager(){
		offres=offreService.findAll();
	}
	
	public void offreEager(){
		try{
		offre=offreService.findOffreByID(offre.getId());
		}catch(Exception e){
			//return "";
			offre.setId(-1);
		}
		
	}
	
	/*a charger avant chaque page*/
	public void setInvestEager(){
		investisseur = investService.findInvestByID(user.getId());
		this.contrat = new Contrat();
		this.offre = new Offre();
		this.societe = new Societe();
		listSociete = societeService.findAll();
		offres=offreService.findAll();
//		societe.setNom("selectionner");
//		HttpServletRequest origRequest = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
//		System.out.println("test : "+origRequest.getRequestURL() +"?faces-redirect=true");
//		return origRequest.getRequestURL() +"?faces-redirect=true";
	}
	
	public void setContratEager(){
			contrat = contratService.findContratByID(contrat.getId());
	}
	
	public void setMembEager(){
		membre = membreService.findMembreByID(user.getId());
	}
	
	public Contrat getContrat() {
		return contrat;
	}

	public void setContrat(Contrat contrat) {
		this.contrat = contrat;
	}

	public Offre getOffre() {
		return offre;
	}

	public void setOffre(Offre offre) {
		this.offre = offre;
	}

	public List<Offre> getOffres() {
		return offres;
	}

	public void setOffres(List<Offre> offres) {
		this.offres = offres;
	}
	
}