package project.miageif.beans;

import java.io.Serializable;

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

@Entity
@Table(name = "ADMINISTRATEUR")
@Inheritance(strategy = InheritanceType.JOINED)
@NamedQuery(name="Administrateur.findAdminByID", query="select u from Administrateur u where u.id=:id")
public class Administrateur extends Utilisateur{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final String FIND_BY_ID = "Administrateur.findAdminByID";

	public Administrateur() {}

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