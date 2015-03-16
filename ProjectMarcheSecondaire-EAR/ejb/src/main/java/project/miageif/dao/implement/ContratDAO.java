package project.miageif.dao.implement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.JoinColumn;

import project.miageif.beans.Contrat;
import project.miageif.beans.Investisseur;
import project.miageif.beans.Membre;
import project.miageif.dao.GenericDAO;

@Stateless
public class ContratDAO extends GenericDAO<Contrat> {

	public ContratDAO() {
		super(Contrat.class);
	}
	
	public Contrat findContratByID(int id){
		 Map<String, Object> parameters = new HashMap<String, Object>();
		 parameters.put("id", id); 
		 return super.findOneResult(Contrat.FIND_BY_ID, parameters);
	}
	
	public Contrat updateContrat(Contrat u){
		 return super.update(u);
	}
	
	public void createContrat(Contrat u){
		super.save(u);
	}
	public List<Contrat> findAll(){
		return super.findAll();
	}
}
