package project.miageif.beans;

import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@NamedQuery(name="Investisseur.findAdminByID", query="select u from Investisseur u where u.user.id=:id")
public class Investisseur {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final String FIND_BY_ID = "Investisseur.findAdminByID";

	@Table(name= "APPROVAL")
	public static enum Approval {WAITING, APPROVED};
	
	
	@Id @NotNull
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;

	public void setId(Integer id) {
		this.id = id;
	}

	@NotNull
	@Enumerated(EnumType.ORDINAL)
	private Approval isApproved=Approval.WAITING;
	
	public void setApproval(Approval status) { this.isApproved = status; }
	public boolean isApproved() { return this.isApproved == Approval.WAITING ? false : true; }

	@OneToOne
	@JoinColumn(name="Id_Utilisateur")
	Utilisateur user;
	
	private String nom;
	private String prenom;
	private String email;

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
		if (obj instanceof Administrateur) {
			Administrateur adm = (Administrateur) obj;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
	}


