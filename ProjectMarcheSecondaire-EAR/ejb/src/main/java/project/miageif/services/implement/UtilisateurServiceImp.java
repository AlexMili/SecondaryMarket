package project.miageif.services.implement;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import project.miageif.beans.Administrateur;
import project.miageif.beans.Utilisateur;
import project.miageif.dao.implement.AdministrateurDAO;
import project.miageif.dao.implement.UtilisateurDAO;
import project.miageif.services.AdministrateurService;
import project.miageif.services.UtilisateurService;

@Stateless
public class UtilisateurServiceImp implements UtilisateurService {

	@EJB
	private UtilisateurDAO user;

	@Override
	public Utilisateur findUserByLoginPass(String login, String pass) {
		return user.findUserByLoginPass(login, pass);
	}

	@Override
	public Utilisateur userUpdate(Utilisateur u) {
		return user.userUpdate(u);
	}

	@Override
	public void createUser(Utilisateur u) {
		user.createUser(u);
	}
}
