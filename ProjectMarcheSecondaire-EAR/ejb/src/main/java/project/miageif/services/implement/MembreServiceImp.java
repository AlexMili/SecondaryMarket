package project.miageif.services.implement;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import project.miageif.beans.Administrateur;
import project.miageif.beans.Investisseur;
import project.miageif.beans.Membre;
import project.miageif.dao.implement.AdministrateurDAO;
import project.miageif.dao.implement.InvestisseurDAO;
import project.miageif.dao.implement.MembreDAO;
import project.miageif.services.AdministrateurService;
import project.miageif.services.InvestisseurService;
import project.miageif.services.MembreService;

@Stateless
public class MembreServiceImp implements MembreService {

	@EJB
	private MembreDAO membre;

	@Override
	public Membre findMembreByID(int id) {
		return membre.findMembreByID(id);
	}

	@Override
	public Membre updateMembre(Membre u) {
		return membre.update(u);
	}

	@Override
	public void createMembre(Membre u) {
		membre.createMembre(u);
	}
}
