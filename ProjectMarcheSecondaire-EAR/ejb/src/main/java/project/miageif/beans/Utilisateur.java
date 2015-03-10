package project.miageif.beans;

import javax.annotation.PreDestroy;
import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "UTILISATEUR")
@Inheritance(strategy = InheritanceType.JOINED)
@NamedQuery(name="Utilisateur.findUserByLoginPass", query="select u from Utilisateur u where u.login = :login "
		+ "and u.password=:pass")
public class Utilisateur {

	public Utilisateur() {}
	
	@Table(name= "TYPE")
	public static enum Type { ADMIN, INVEST, MEMBER}; 
	@Table(name= "STATUS")
	public static enum Status {DISCONNECTED, CONNECTED}; 
	
	public static final String FIND_BY_LOGIN_PASS = "Utilisateur.findUserByLoginPass";
	
	@Id @NotNull
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Id_Utilisateur")
	private Integer id;

	@Override
	public int hashCode() { return getId(); }
	public void setId(int id) { this.id = id; }
	public int getId() { return this.id; }
	
	@NotNull
	private String login;

	public String getLogin() { return this.login; }
	public void setLogin(String login) { this.login = login; }
	
	@NotNull
	private String password;

	public String getPassword() { return this.password; }
	public void setPassword(String password) { this.password = password; }
	
	@Enumerated(EnumType.ORDINAL)
	private Type type;

	public Type getType() { return this.type; }
	public void setType(Type type) { this.type = type; }
	
	@NotNull
	@Enumerated(EnumType.ORDINAL)
	private Status status=Status.DISCONNECTED;

	public Status getStatus() { return this.status; }
	public void setStatus(Status status) { this.status = status; }

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Utilisateur) {
			Utilisateur adm = (Utilisateur) obj;
			return (adm.getId()==getId());
		}

		return false;
	}
	
	@PreDestroy
	public void cleanUp() throws Exception
	{
		this.status=Status.DISCONNECTED;
		System.out.println("Utilisateur détruit");
	}	
}