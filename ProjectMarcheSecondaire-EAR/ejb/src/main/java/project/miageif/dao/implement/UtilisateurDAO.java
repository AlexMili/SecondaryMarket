package project.miageif.dao.implement;

import java.util.HashMap;
import java.util.Map;

import javax.ejb.Stateless;

import project.miageif.beans.Utilisateur;
import project.miageif.dao.GenericDAO;

@Stateless
public class UtilisateurDAO extends GenericDAO<Utilisateur> {

	public UtilisateurDAO() {
		super(Utilisateur.class);
	}

	public Utilisateur findUserByLoginPass(String login, String pass){
		 Map<String, Object> parameters = new HashMap<String, Object>();
		 parameters.put("login", login); 
		 parameters.put("pass", pass); 
		 //Administrateur adm = super.findOneResult(Administrateur.FIND_BY_ID, parameters);
		 //System.out.println("*** **** *** Login = "+ adm.getLogin() + " ID = "+adm.getId() );
		 return super.findOneResult(Utilisateur.FIND_BY_LOGIN_PASS, parameters);
	}
	
	public Utilisateur userUpdate(Utilisateur u){
		 return super.update(u);
	}
	
	public void createUser(Utilisateur u){
		super.save(u);
	}
}
