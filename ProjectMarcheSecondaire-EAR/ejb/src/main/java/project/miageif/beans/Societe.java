package project.miageif.beans;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import project.miageif.beans.Utilisateur.Approval;


@Entity
@Table(name = "SOCIETE")
@NamedQuery(name="Societe.findSocieteByID", query="select u from Societe u where u.id=:id")
public class Societe {
	
	public Societe() {}
	
	public static final String FIND_BY_ID = "Societe.findSocieteByID";
	
	@Id @NotNull
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Id_Societe")
	private Integer id;
	
	public void setId(int id) { this.id = id; }
	public int getId() { return this.id; }
	
	private int quantiteTitre;
	
	private int nbTitreVendue = 0;
	
	@OneToMany(fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	private List<Contrat> contrat = new ArrayList<Contrat>();
	
	@OneToMany(fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	private List<Membre> menbre = new ArrayList<Membre>();
	
	@Column(precision = 2)
	private double prixTitre;
	
	@Enumerated(EnumType.ORDINAL)
	private Approval isApproved=Approval.WAITING;
	
	@Column(unique = true)
	private String nom;
	
	public String getNom() { return this.nom; }
	public void setNom(String nom) { this.nom = nom; }
	

	private String adresse;
	
	public String getAdresse() { return this.adresse; }
	public void setAdresse(String adr) { this.adresse = adr; }
	
	
	private int codePostal;
	
	public int getCodePostal() { return this.codePostal; }
	public void setCodePostal(int cp) { this.codePostal = cp; }
	
	
	private String ville;
	
	public String getVille() { return this.ville; }
	public void setVille(String ville) { this.ville = ville; }
	
	private String pays;
	
	private String email;

	public String getEmail() { return this.email; }
	public void setEmail(String email) { this.email = email; }
	
	
	private int telephone;
	
	public int getTelephone() { return this.telephone; }
	public void setTelephone(int tel) { this.telephone = tel; }
	
	public Approval getIsApproved() {
		return isApproved;
	}
	
	public void setIsApproved(Approval isApproved) {
		this.isApproved = isApproved;
	}
	
	public double getPrixTitre() {
		return prixTitre;
	}
	
	public int getQuantiteTitre() {
		return quantiteTitre;
	}
	
	public void setPrixTitre(double prixTitre) {
		this.prixTitre = prixTitre;
	}
	
	public void setQuantiteTitre(int quantiteTitre) {
		this.quantiteTitre = quantiteTitre;
	}
	
	public List<Contrat> getContrat() {
		return contrat;
	}
	
	public void setContrat(List<Contrat> contrat) {
		this.contrat = contrat;
	}
	
	public List<Membre> getMenbre() {
		return menbre;
	}
	
	public void setMenbre(List<Membre> menbre) {
		this.menbre = menbre;
	}
	public int getNbTitreVendue() {
		return nbTitreVendue;
	}
	public void setNbTitreVendue(int nbTitreVendue) {
		this.nbTitreVendue = nbTitreVendue;
	}
	public String getPays() {
		return pays;
	}
	public void setPays(String pays) {
		this.pays = pays;
	}
}
