package project.miageif.dao.implement;

import java.util.HashMap;
import java.util.Map;

import javax.ejb.Stateless;

import org.hibernate.Session;

import project.miageif.beans.Administrateur;
import project.miageif.beans.Utilisateur;
import project.miageif.dao.GenericDAO;
import project.miageif.utilitaire.HibernateUtil;

@Stateless
public class AdministrateurDAO extends GenericDAO<Administrateur> {

	public AdministrateurDAO() {
		super(Administrateur.class);
	}

	public Administrateur findAdminByID(int id){
		 Map<String, Object> parameters = new HashMap<String, Object>();
		 parameters.put("id", id); 
		 //Administrateur adm = super.findOneResult(Administrateur.FIND_BY_ID, parameters);
		 //System.out.println("*** **** *** Login = "+ adm.getLogin() + " ID = "+adm.getId() );
		 return super.findOneResult(Administrateur.FIND_BY_ID, parameters);
	}
	
	public Administrateur updateAdmin(Administrateur u){
		return super.update(u);
	}
}
