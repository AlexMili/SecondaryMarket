package project.miageif.services.implement;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import project.miageif.beans.Administrateur;
import project.miageif.beans.Contrat;
import project.miageif.beans.Investisseur;
import project.miageif.beans.Membre;
import project.miageif.beans.Offre;
import project.miageif.dao.implement.AdministrateurDAO;
import project.miageif.dao.implement.ContratDAO;
import project.miageif.dao.implement.InvestisseurDAO;
import project.miageif.dao.implement.MembreDAO;
import project.miageif.dao.implement.OffreDAO;
import project.miageif.services.AdministrateurService;
import project.miageif.services.ContratService;
import project.miageif.services.InvestisseurService;
import project.miageif.services.MembreService;
import project.miageif.services.OffreService;

@Stateless
public class OffreServiceImp implements OffreService {

	@EJB
	private OffreDAO offre;
	
	@Override
	public List<Offre> findAll() {
		return offre.findAll();
	}

	@Override
	public Offre findOffreByID(int id) {
		return offre.findContratByID(id);
	}

	@Override
	public Offre updateOffre(Offre u) {
		return offre.update(u);
	}

	@Override
	public void createOffre(Offre u) {
		offre.createContrat(u);
	}
}
