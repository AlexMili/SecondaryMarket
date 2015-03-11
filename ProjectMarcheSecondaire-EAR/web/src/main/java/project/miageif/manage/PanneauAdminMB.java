package project.miageif.manage;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.hibernate.Session;

import project.miageif.beans.Investisseur;
import project.miageif.services.InvestisseurService;
import project.miageif.utilitaire.HibernateUtil;

@ManagedBean(name = "adminMB")
@SessionScoped
public class PanneauAdminMB {
	
	private List<Investisseur> invest;
	@EJB
	private InvestisseurService investService;
	
	@PostConstruct
	public void init() {
		invest = investService.findAll();
	}
	
	
	public String nbProfileNoAcredite(){
		return ""+invest.size();
	}
	
	public void setInvest(List<Investisseur> invest) {
		this.invest = invest;
	}
	
	
	
}