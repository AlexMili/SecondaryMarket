package project.miageif.services.implement;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import project.miageif.beans.Administrateur;
import project.miageif.beans.Investisseur;
import project.miageif.beans.Societe;
import project.miageif.dao.implement.AdministrateurDAO;
import project.miageif.dao.implement.InvestisseurDAO;
import project.miageif.dao.implement.SocieteDAO;
import project.miageif.services.AdministrateurService;
import project.miageif.services.InvestisseurService;
import project.miageif.services.SocieteService;

@Stateless
public class SocieteServiceImp implements SocieteService {

	@EJB
	private SocieteDAO societe;

	@Override
	public Societe findSocieteByID(int id) {
		return societe.findSocieteByID(id);
	}

	@Override
	public Societe updateSociete(Societe u) {
		return societe.update(u);
	}

	@Override
	public void createSociete(Societe u) {
		societe.createSociete(u);
	}

	@Override
	public List getAllSocieteApprouvees() {
		return societe.getAllSocieteApprouvees();
	}
}
