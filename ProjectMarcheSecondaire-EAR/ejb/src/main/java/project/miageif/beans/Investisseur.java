package project.miageif.beans;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.NamedQuery;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "INVESTISSEUR")
@PrimaryKeyJoinColumn(name = "id_investisseur", referencedColumnName = "Id_Utilisateur")
@NamedQuery(name="Investisseur.findInvestisseurByID", query="select u from Investisseur u where u.id=:id")
public class Investisseur extends Utilisateur
{
	//private static final long serialVersionUID = 1L;

	public static final String FIND_BY_ID = "Investisseur.findInvestisseurByID";

	public Investisseur() { setType(Type.INVEST); }
	
	@Table(name= "APPROVAL")
	public static enum Approval {WAITING, APPROVED};
	

	@NotNull
	@Enumerated(EnumType.ORDINAL)
	private Approval isApproved=Approval.WAITING;
	
	public void setApproval(Approval status) { this.isApproved = status; }
	public boolean isApproved() { return this.isApproved == Approval.WAITING ? false : true; }

	
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
		if (obj instanceof Administrateur) {
			Administrateur adm = (Administrateur) obj;
			return (adm.getId()==getId());
		}

		return false;
	}	
}