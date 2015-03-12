package project.miageif.manage;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.hibernate.Session;

import project.miageif.beans.Investisseur;
import project.miageif.beans.Investisseur.Approval;
import project.miageif.beans.Societe;
import project.miageif.services.InvestisseurService;
import project.miageif.services.SocieteService;
import project.miageif.utilitaire.HibernateUtil;

@ManagedBean(name = "panneauAdminMB")
@SessionScoped
public class PanneauAdminMB {
	
	private List<Investisseur> invests;
	private List<Societe> societes;
	
	@EJB
	private InvestisseurService investService;
	@EJB
	private SocieteService societeService;
	
	private int nbProfileToCheck;
	private int nbSocieteToCheck;
	
	@PostConstruct
	public void init() {
		invests = investService.findAll();
		societes = societeService.findAll();
	}
	
	public void validatorI(){
		for (Investisseur investisseur : invests) {
			if(investisseur.getIsApproved().equals(Approval.APPROVED));
			investService.updateInvest(investisseur);
		}
		refreshPan();
	}
	
	public int getNbSocieteToCheck() {
		init();
		int i = 0;
		for (Societe soc : societes) {
			if(soc.getIsApproved().equals(Approval.WAITING))
				i++;
		}
		nbSocieteToCheck=i;
		return nbSocieteToCheck;
	}
	
	public int getNbProfileToCheck() {
		init();
		int i = 0;
		for (Investisseur investisseur : invests) {
			if(investisseur.getIsApproved().equals(Approval.WAITING))
				i++;
		}
		nbProfileToCheck=i;
		return nbProfileToCheck;
	}
	
	public List<Investisseur> getInvests() {
		return invests;
	}
	
	public List<Societe> getSocietes() {
		return societes;
	}
	 public String refreshPan(){
		   invests.clear();;
		   societes.clear(); 
		   init();
		   return "refresh";
	   }
}