package fr.dauphine.mido.as.banquetest.beans;

import java.io.Serializable;
import java.math.BigDecimal; 
import java.sql.Connection;
import java.sql.PreparedStatement; 
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped; 
import javax.naming.InitialContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory; 
import javax.persistence.EntityTransaction; 
import javax.persistence.Persistence;
import javax.sql.DataSource;

@ManagedBean
@SessionScoped
public class GestionCompte implements Serializable {
	static final long serialVersionUID = 1L;
	private final static String _SQL_SELECT_OPERATIONS = "select * from BANQUE_TEST.OPERATIONS where NOCOMPTE like ?";
	private final static String _SQL_INSERT_COMPTE = "INSERT INTO BANQUE_TEST.COMPTES (NOCOMPTE,NOM,PRENOM,SOLDE) VALUES(?,?,?,?)";
	private Compte compte = null;

	public static ArrayList<Operation> rechercheOperations( DataSource datasource, Compte unCompte) {
		Connection connection = null; 
		PreparedStatement preparedStatement = null; 
		ResultSet resultSet = null;
		Operation operation = null; 
		ArrayList<Operation> resultat = null;
		try {
			connection = datasource.getConnection(); 
			preparedStatement = connection
					.prepareStatement(_SQL_SELECT_OPERATIONS); 
			preparedStatement.setString(1, unCompte.getNocompte()); 
			resultSet = preparedStatement.executeQuery();
			resultat = new ArrayList<Operation>();
			while (resultSet.next()) {
				operation = new Operation();
				operation.setIdOperation(new Integer(resultSet.getString(1))
				.intValue()); 
				operation.setNoCompte(resultSet.getString(2)); 
				operation.setDateOperation(resultSet.getString(3)); 
				operation.setHeureOperation(resultSet.getString(4)); 
				operation.setOperation(resultSet.getString(5).toCharArray()[0]); 
				operation.setValeur(new Float(resultSet.getString(6))); resultat.add(operation);
			}
		} catch (Exception e) {
			System.err.println(e.getMessage().toString()); } finally {try {
				resultSet.close();
				preparedStatement.close();
				connection.close();
			} catch (Exception e2) {
				System.err.println(e2.getMessage().toString()); }
			}
		return resultat; }
	public String getResponse() { 
		String retour = null;
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("BanqueTestWeb");
		EntityManager em = emf.createEntityManager(); 
		EntityTransaction et = em.getTransaction(); 
		et.begin();
		em.persist(getCompte());
		et.commit();
		setCompte(em.find(Compte.class, getNocompte()));
		retour = "insertion ok";
		return retour; }
	public Compte getCompte() { 
		return compte;
	}
	public void setCompte(Compte compte) { this.compte = compte;
	}
	public String getNocompte() { 
		if (this.compte == null) {
			this.compte = new Compte(); 
		}
		return getCompte().getNocompte(); 
	}
	public void setNocompte(String noDeCompte) { 
		getCompte().setNocompte(noDeCompte);
	}
	public String getNom() {
		return getCompte().getNom();
	}
	public void setNom(String nom) {
		getCompte().setNom(nom);
	}
	public void setPrenom(String nom) {
		getCompte().setPrenom(nom);
	}
	public void setSolde(BigDecimal solde) {
		getCompte().setSolde(solde);
	}
	public BigDecimal getSolde() {
		return getCompte().getSolde();
	}
	public String getPrenom() {
		return getCompte().getPrenom();
	}
	public GestionCompte() { }
}
