package project.miageif.manage;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import project.miageif.beans.Investisseur;
import project.miageif.beans.Membre;
import project.miageif.beans.Societe;
import project.miageif.beans.Utilisateur.Approval;
import project.miageif.services.InvestisseurService;
import project.miageif.services.MembreService;
import project.miageif.services.SocieteService;

@ManagedBean(name = "panneauAdminMB")
@SessionScoped
public class PanneauAdminMB {
	
	private List<Investisseur> invests;
	private List<Societe> societes;
	private List<Membre> membres;
	
	@EJB
	private InvestisseurService investService;
	@EJB
	private SocieteService societeService;
	@EJB
	private MembreService memberService;
	
	@PostConstruct
	public void init() {
		invests = investService.findAll();
		societes = societeService.findAll();
		membres = memberService.findAll();
	}
	
	public void validatorI(){
		for (Investisseur investisseur : invests) {
			if(investisseur.getIsApproved().equals(Approval.APPROVED));
			investService.updateInvest(investisseur);
		}
		refreshPan();
	}
	
	public void validatorM(){
		for (Membre memb : membres) {
			if(memb.getIsApproved().equals(Approval.APPROVED));
			memberService.updateMembre(memb);
		}
		refreshPan();
	}
	
	public int getNbSocieteToCheck() {
		init();
		int i = 0;
		for (Societe soc : societes) {
			if(soc.getIsApproved().equals(Approval.WAITING))System.out.println("************* VALEUR "+soc.getIsApproved());
			if(soc.getIsApproved().equals(Approval.WAITING))
				i++;
		}
		return i;
	}
	
	public int getNbInvestToCheck() {
		init();
		int i = 0;
		for (Investisseur investisseur : invests) {
			if(investisseur.getIsApproved().equals(Approval.WAITING))
				i++;
		}
		return i;
	}
	
	public int getNbMembreToCheck() {
		init();
		int i = 0;
		for (Membre memb : membres) {
			if(memb.getIsApproved().equals(Approval.WAITING))
				i++;
		}
		return i;
	}
	
	public List<Investisseur> getInvests() {
		return invests;
	}
	
	public List<Societe> getSocietes() {
		return societes;
	}
	
	public List<Membre> getMembres() {
		return membres;
	}
	
	public String refreshPan(){
		   invests.clear();
		   societes.clear();
		   membres.clear();
		   init();
		   return "refresh";
	   }
}