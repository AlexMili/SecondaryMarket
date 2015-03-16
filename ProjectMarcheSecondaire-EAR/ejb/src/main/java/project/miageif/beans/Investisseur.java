package project.miageif.beans;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import project.miageif.beans.Utilisateur.Approval;

@Entity
@Table(name = "INVESTISSEUR")
@NamedQuery(name="Investisseur.findInvestByID", query="select u from Investisseur u where u.user.id=:id")
public class Investisseur {
	
	public static final String FIND_BY_ID = "Investisseur.findInvestByID";
	
	
	@Id @NotNull
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_investisseur")
	private Integer id;
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	@OneToMany(fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	private List<Contrat> contrats = new ArrayList<Contrat>();

	@OneToOne
	@JoinColumn(name="Id_Utilisateur")
	Utilisateur user;
	
	@NotNull
	@Enumerated(EnumType.ORDINAL)
	private Approval isApproved=Approval.WAITING;
	
	private String nom;
	private String prenom;
	private String adresse;
	private String ville;
	private String pays;
	private int codepost;
	private double solde=0;
	private int telephone;
	
	public int getTelephone() { return this.telephone; }
	public void setTelephone(int tel) { this.telephone = tel; }

	public Investisseur() {}

	@Override
	public int hashCode() {
		return getId();
	}
	
	
	public Integer getId() {
		return id;
	}

	public Utilisateur getUser() {
		return user;
	}
	
	public void setUser(Utilisateur user) {
		this.user = user;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Investisseur) {
			Investisseur adm = (Investisseur) obj;
			return (adm.getId()==getId());
		}

		return false;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public Approval getIsApproved() {
		return isApproved;
	}
	
	public void setIsApproved(Approval p) {
		this.isApproved = p;
	}
	
	public List<Contrat> getContrats() {
		return contrats;
	}
	
	public void setContrats(List<Contrat> contrats) {
		this.contrats = contrats;
	}

	public double getSolde() {
		return solde;
	}

	public void setSolde(double solde) {
		this.solde = solde;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public int getCodepost() {
		return codepost;
	}

	public void setCodepost(int codepost) {
		this.codepost = codepost;
	}
	public String getPays() {
		return pays;
	}
	public void setPays(String pays) {
		this.pays = pays;
	}
	
	}


