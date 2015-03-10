package project.miageif.dao.implement;

import java.util.HashMap;
import java.util.Map;

import javax.ejb.Stateless;

import project.miageif.beans.Investisseur;
import project.miageif.beans.Membre;
import project.miageif.dao.GenericDAO;

@Stateless
public class MembreDAO extends GenericDAO<Membre> {

	public MembreDAO() {
		super(Membre.class);
	}

	public Membre findMembreByID(int id){
		 Map<String, Object> parameters = new HashMap<String, Object>();
		 parameters.put("id", id); 
		 return super.findOneResult(Membre.FIND_BY_ID, parameters);
	}
	
	public Membre membreUpdate(Membre u){
		 return super.update(u);
	}
	
	public void createMembre(Membre u){
		super.save(u);
	}
}
