package project.miageif.beans;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import project.miageif.beans.Utilisateur.Etat_Offre;
import project.miageif.beans.Utilisateur.Type_Offre;


@Entity
@Table(name = "OFFRE")
@NamedQuery(name="Offre.findOffreByID", query="select u from Offre u where u.id=:id")
public class Offre {
	
	public static final String FIND_BY_ID = "Offre.findOffreByID";
	
	
	@Id @NotNull
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_offre")
	private Integer id;
	
	private int quantiteTitre;
	@Column(precision = 2)
	private double prix;
	private Date date;
	private Date datedispo;
	@NotNull @Column(precision = 2)
	private double plusvalue;
	private Etat_Offre etat = Etat_Offre.EN_VENTE;
	private Type_Offre type = Type_Offre.ENCHERE_NON;
	
	@ManyToOne
	@JoinColumn(name="id_contrat")
	private Contrat contrat;
	
	private int id_acheteur;
	
	public Contrat getContrat() {
		return contrat;
	}
	public void setContrat(Contrat contrat) {
		this.contrat = contrat;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public double getPrix() {
		return prix;
	}
	public void setPrix(double prix) {
		this.prix = prix;
	}
	public int getQuantiteTitre() {
		return quantiteTitre;
	}
	public void setQuantiteTitre(int quantiteTitre) {
		this.quantiteTitre = quantiteTitre;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public double getPlusvalue() {
		return plusvalue;
	}
	public void setPlusvalue(double plusvalue) {
		this.plusvalue = plusvalue;
	}
	
	public Date getDatedispo() {
		return datedispo;
	}
	public void setDatedispo(Date datedispo) {
		this.datedispo = datedispo;
	}
	public Etat_Offre getEtat() {
		return etat;
	}
	public void setEtat(Etat_Offre etat) {
		this.etat = etat;
	}
	public Type_Offre getType() {
		return type;
	}
	public void setType(Type_Offre type) {
		this.type = type;
	}
	public int getId_acheteur() {
		return id_acheteur;
	}
	public void setId_acheteur(int id_acheteur) {
		this.id_acheteur = id_acheteur;
	}

}
