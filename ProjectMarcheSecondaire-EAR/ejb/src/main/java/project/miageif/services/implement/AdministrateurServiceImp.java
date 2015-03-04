package project.miageif.services.implement;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import project.miageif.beans.Administrateur;
import project.miageif.dao.implement.AdministrateurDAO;
import project.miageif.services.AdministrateurService;

@Stateless
public class AdministrateurServiceImp implements AdministrateurService {

	@EJB
	private AdministrateurDAO admin;
	
	@Override
	public Administrateur findAdminByID(int id) {
		return admin.findAdminByID(id);
	}

	@Override
	public Administrateur updateAdmin(Administrateur u) {
		return admin.updateAdmin(u);
		
	}
}
