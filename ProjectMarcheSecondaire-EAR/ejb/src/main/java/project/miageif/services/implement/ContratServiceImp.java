package project.miageif.services.implement;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import project.miageif.beans.Administrateur;
import project.miageif.beans.Contrat;
import project.miageif.beans.Investisseur;
import project.miageif.beans.Membre;
import project.miageif.dao.implement.AdministrateurDAO;
import project.miageif.dao.implement.ContratDAO;
import project.miageif.dao.implement.InvestisseurDAO;
import project.miageif.dao.implement.MembreDAO;
import project.miageif.services.AdministrateurService;
import project.miageif.services.ContratService;
import project.miageif.services.InvestisseurService;
import project.miageif.services.MembreService;

@Stateless
public class ContratServiceImp implements ContratService {

	@EJB
	private ContratDAO contrat;
	
	@Override
	public List<Contrat> findAll() {
		return contrat.findAll();
	}

	@Override
	public Contrat findContratByID(int id) {
		return contrat.findContratByID(id);
	}

	@Override
	public Contrat updateContrat(Contrat u) {
		return contrat.update(u);
	}

	@Override
	public void createContrat(Contrat u) {
		contrat.createContrat(u);
	}
}
