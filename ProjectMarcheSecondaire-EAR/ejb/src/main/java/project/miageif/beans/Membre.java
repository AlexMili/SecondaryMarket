package project.miageif.beans;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "MEMBRE")
@NamedQuery(name="Membre.findMembreByID", query="select u from Membre u where u.id=:id")
public class Membre extends Utilisateur
{
	//private static final long serialVersionUID = 1L;

	public static final String FIND_BY_ID = "Membre.findMembreByID";

	public Membre() { setType(Type.MEMBER); }
	
	@Table(name= "APPROVAL")
	public static enum Approval {WAITING, APPROVED};

	@NotNull
	@Enumerated(EnumType.ORDINAL)
	private Approval isApproved=Approval.WAITING;
	
	public void setApproval(Approval status) { this.isApproved = status; }
	public Approval getApproval() { return this.isApproved; }
	public boolean isApproved() { return this.isApproved == Approval.WAITING ? false : true; }

	@ManyToOne
	@JoinColumn(name="id_societe")
	Societe societe;
	
	public Societe getUser() { return this.societe; }
	public void setUser(Societe soc) { this.societe = soc; }
	
	
	private String nom;
	
	public String getNom() { return this.nom; }
	public void setNom(String nom) { this.nom = nom; }
	
	
	private String prenom;
	
	public String getPrenom() { return this.prenom; }
	public void setPrenom(String prenom) { this.prenom = prenom; }
	
	
	private String email;

	public String getEmail() { return this.email; }
	public void setEmail(String email) { this.email = email; }
	

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Membre) {
			Membre mbr = (Membre) obj;
			return (mbr.getId()==getId());
		}

		return false;
	}	
}