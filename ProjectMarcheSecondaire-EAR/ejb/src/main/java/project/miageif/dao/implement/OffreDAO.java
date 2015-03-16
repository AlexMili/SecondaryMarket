package project.miageif.dao.implement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.JoinColumn;

import project.miageif.beans.Contrat;
import project.miageif.beans.Investisseur;
import project.miageif.beans.Membre;
import project.miageif.beans.Offre;
import project.miageif.dao.GenericDAO;

@Stateless
public class OffreDAO extends GenericDAO<Offre> {

	public OffreDAO() {
		super(Offre.class);
	}
	
	public Offre findContratByID(int id){
		 Map<String, Object> parameters = new HashMap<String, Object>();
		 parameters.put("id", id); 
		 return super.findOneResult(Offre.FIND_BY_ID, parameters);
	}
	
	public Offre updateContrat(Offre u){
		 return super.update(u);
	}
	
	public void createContrat(Offre u){
		super.save(u);
	}
	public List<Offre> findAll(){
		return super.findAll();
	}
}
