package project.miageif.dao.implement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.Query;

import project.miageif.beans.Investisseur;
import project.miageif.beans.Membre;
import project.miageif.beans.Societe;
import project.miageif.dao.GenericDAO;

@Stateless
public class SocieteDAO extends GenericDAO<Societe> {

	public SocieteDAO() {
		super(Societe.class);
	}

	public Societe findSocieteByID(int id){
		 Map<String, Object> parameters = new HashMap<String, Object>();
		 parameters.put("id", id); 
		 return super.findOneResult(Societe.FIND_BY_ID, parameters);
	}
	
	public Query getAllSocieteApprouvees(){
		 Query result = null;

		 try {
			 result = getManager().createQuery("SELECT * FROM Societe u WHERE u.isApproved=1");
		 }
		 catch (Exception e) {
				System.out.println("Error while running query: " + e.getMessage());
				e.printStackTrace();
		 }

		 return result;
	}
	
	public Societe societeUpdate(Societe u){
		 return super.update(u);
	}
	
	public void createSociete(Societe u){
		super.save(u);
	}
}
