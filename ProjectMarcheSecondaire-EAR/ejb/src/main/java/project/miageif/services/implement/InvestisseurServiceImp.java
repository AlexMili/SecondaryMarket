package project.miageif.services.implement;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import project.miageif.beans.Administrateur;
import project.miageif.beans.Investisseur;
import project.miageif.dao.implement.AdministrateurDAO;
import project.miageif.dao.implement.InvestisseurDAO;
import project.miageif.services.AdministrateurService;
import project.miageif.services.InvestisseurService;

@Stateless
public class InvestisseurServiceImp implements InvestisseurService {

	@EJB
	private InvestisseurDAO invest;

	@Override
	public Investisseur findInvestByID(int id) {
		return invest.findInvestByID(id);
	}

	@Override
	public Investisseur updateInvest(Investisseur u) {
		return invest.update(u);
	}

	@Override
	public void createInvest(Investisseur u) {
		invest.createInvest(u);
	}

	@Override
	public List<Investisseur> findAll() {
		return invest.findAll();
	}
}
