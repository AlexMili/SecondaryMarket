package project.miageif.beans;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "UTILISATEUR")
@NamedQuery(name="Utilisateur.findUserByLoginPass", query="select u from Utilisateur u where u.login = :login "
		+ "and u.password=:pass")
public class Utilisateur {
	
	public static final String FIND_BY_LOGIN_PASS = "Utilisateur.findUserByLoginPass";
	
	@Id @NotNull
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Id_Utilisateur")
	private Integer id;
	
	@NotNull
	private String login;

	@NotNull
	private String password;
	
	private String type;
	
	@NotNull
	@Column(columnDefinition="varchar(255) default 'DisConnect'")
	private String status="DisConnect";

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
	
	public void setType(String type) {
		this.type = type;
	}
	
	public String getType() {
		return type;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
	
	}


