package project.miageif.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "SOCIETE")
@NamedQuery(name="Societe.findSocieteByID", query="select u from Societe u where u.id=:id")
public class Societe {
	
	public Societe() {}
	
	public static final String FIND_BY_ID = "Membre.findSocieteByID";
	
	@Id @NotNull
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Id_Societe")
	private Integer id;
	
	public void setId(int id) { this.id = id; }
	public int getId() { return this.id; }
	

	@Table(name= "APPROVAL")
	public static enum Approval {WAITING, APPROVED};

	@NotNull
	@Enumerated(EnumType.ORDINAL)
	private Approval isApproved=Approval.WAITING;
	
	public void setApproval(Approval status) { this.isApproved = status; }
	public Approval getApproval() { return this.isApproved; }
	public boolean isApproved() { return this.isApproved == Approval.WAITING ? false : true; }
	
	
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
	
	private String email;

	public String getEmail() { return this.email; }
	public void setEmail(String email) { this.email = email; }
	
	
	private int telephone;
	
	public int getTelephone() { return this.telephone; }
	public void setTelephone(int tel) { this.telephone = tel; }
	
	public Approval getIsApproved() {
		return isApproved;
	}
}
