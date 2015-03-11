package project.miageif.dao.implement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;

import project.miageif.beans.Investisseur;
import project.miageif.dao.GenericDAO;

@Stateless
public class InvestisseurDAO extends GenericDAO<Investisseur> {

	public InvestisseurDAO() {
		super(Investisseur.class);
	}

	public Investisseur findInvestByID(int id){
		 Map<String, Object> parameters = new HashMap<String, Object>();
		 parameters.put("id", id); 
		 return super.findOneResult(Investisseur.FIND_BY_ID, parameters);
	}
	
	public Investisseur investUpdate(Investisseur u){
		 return super.update(u);
	}
	
	public void createInvest(Investisseur u){
		super.save(u);
	}
	
	public List<Investisseur> findAll(){
		return super.findAll();
	}
}
