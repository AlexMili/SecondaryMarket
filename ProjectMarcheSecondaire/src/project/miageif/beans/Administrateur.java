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
@Table(name = "ADMINISTRATEUR")
@NamedQuery(name="Administrateur.findUserByLoginPass", query="select u from Administrateur u where u.login = :login "
		+ "and u.password=:pass")
public class Administrateur {
	
	public static final String FIND_BY_ID = "Administrateur.findUserByLoginPass";
	
	@Id @NotNull @Size(min=1, max=2)
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Id_Administrateur")
	private int id;
	
	@NotNull
	private String login;
	//@Column(name="password")
	@NotNull
	private String password;

	public Administrateur() {
	}

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
		if (obj instanceof Administrateur) {
			Administrateur adm = (Administrateur) obj;
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
	
	}


