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
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "INVESTISSEUR")
@NamedQuery(name="Investisseur.findInvestByID", query="select u from Investisseur u where u.user.id=:id")
public class Investisseur {
	
	public static final String FIND_BY_ID = "Investisseur.findInvestByID";
	
	@Table(name= "APPROVAL")
	public static enum Approval {WAITING, APPROVED};
	
	@Id @NotNull
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;
	
	public void setId(Integer id) {
		this.id = id;
	}

	@OneToOne
	@JoinColumn(name="Id_Utilisateur")
	Utilisateur user;
	
	@NotNull
	@Enumerated(EnumType.ORDINAL)
	private Approval isApproved=Approval.WAITING;
	
	public void setApproval(Approval status) { this.isApproved = status; }
	public boolean isApproved() { return this.isApproved == Approval.WAITING ? false : true; }
	
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


