package project.miageif.beans;

import javax.annotation.PreDestroy;
import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "UTILISATEUR")
@NamedQuery(name="Utilisateur.findUserByLoginPass", query="select u from Utilisateur u where u.login = :login "
		+ "and u.password=:pass")
public class Utilisateur {
	
	@Table(name= "TYPE")
	public static enum Type { ADMIN, INVEST, MEMBER}; 
	@Table(name= "STATUS")
	public static enum Status {DISCONNECTED, CONNECTED}; 
	
	public static final String FIND_BY_LOGIN_PASS = "Utilisateur.findUserByLoginPass";
	
	@Id @NotNull
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Id_Utilisateur")
	private Integer id;
	
	@NotNull
	private String login;

	@NotNull
	private String password;
	
	@Enumerated(EnumType.ORDINAL)
	private Type type;
	
	@NotNull
	@Enumerated(EnumType.ORDINAL)
	private Status status=Status.DISCONNECTED;

	public Utilisateur() {}

	@Override
	public int hashCode() {
		return getId();
	}
	
	public void setId(int id) {
		this.id = id;
	}
	public int getId() {
		return id;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Utilisateur) {
			Utilisateur adm = (Utilisateur) obj;
			return (adm.getId()==getId());
		}

		return false;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setType(Type type) {
		this.type = type;
	}
	
	public Type getType() {
		return type;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
	
	@PreDestroy
	public void cleanUp() throws Exception {
	  this.status=Status.DISCONNECTED;
	  System.out.println("Utilisateur détruit");
	}
	
	
	}


