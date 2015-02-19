package project.miageif.beans;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table( name = "ADMINISTRATEUR" )
public class Administrateur {
	private Long id;
	private String login;
	private String password;
	
	public Administrateur() {	}
	
	@Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
	public Long getId() {
		return id;
	}
	
	public String getLogin() {
		return login;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public void setLogin(String login) {
		this.login = login;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
}
