package project.miageif.beans;

import java.io.Serializable;

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

@Entity
@Table(name = "ADMINISTRATEUR")
@NamedQuery(name="Administrateur.findAdminByID", query="select u from Administrateur u where u.user.id=:id")
public class Administrateur {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final String FIND_BY_ID = "Administrateur.findAdminByID";
	
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
	
	private String nom;
	private String prenom;
	private String email;

	public Administrateur() {}

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


