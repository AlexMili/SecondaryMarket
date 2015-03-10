package project.miageif.beans;

import javax.enterprise.inject.Produces;
import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import project.miageif.beans.Utilisateur.Status;

@Entity
@Table(name = "INVESTISSEUR")
@Inheritance(strategy = InheritanceType.JOINED)
@NamedQuery(name="Investisseur.findInvestisseurByID", query="select u from Investisseur u where u.id=:id")
public class Investisseur extends Utilisateur
{
	//private static final long serialVersionUID = 1L;

	public static final String FIND_BY_ID = "Investisseur.findInvestisseurByID";

	public Investisseur() { setType(Type.INVEST); }
	
	@Table(name= "APPROVAL")
	public static enum Approval {WAITING, APPROVED};
	
	
	/*@Id @NotNull
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;

	public void setId(Integer id) {
		this.id = id;
	}*/

	@NotNull
	@Enumerated(EnumType.ORDINAL)
	private Approval isApproved=Approval.WAITING;
	
	public void setApproval(Approval status) { this.isApproved = status; }
	public boolean isApproved() { return this.isApproved == Approval.WAITING ? false : true; }

	/*@OneToOne
	@JoinColumn(name="Id_Utilisateur")
	Utilisateur user;*/
	
	private String nom;
	
	public String getNom() { return this.nom; }
	public void setNom(String nom) { this.nom = nom; }
	
	
	private String prenom;
	
	public String getPrenom() { return this.prenom; }
	public void setPrenom(String prenom) { this.prenom = prenom; }
	
	
	private String email;

	public String getEmail() { return this.email; }
	public void setEmail(String email) { this.email = email; }
	

	/*@Override
	public int hashCode() {
		return getId();
	}*/
	
	
	//public Integer getId() {
	//	return id;
	//}

	/*public Utilisateur getUser() {
		return user;
	}
	
	public void setUser(Utilisateur user) {
		this.user = user;
	}*/

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Administrateur) {
			Administrateur adm = (Administrateur) obj;
			return (adm.getId()==getId());
		}

		return false;
	}	
}