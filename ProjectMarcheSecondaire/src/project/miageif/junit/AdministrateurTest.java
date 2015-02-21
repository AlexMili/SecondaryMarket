package project.miageif.junit;

import static org.junit.Assert.*;

import java.util.Properties;

import javax.ejb.EJB;
import javax.ejb.embeddable.EJBContainer;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import project.miageif.beans.Administrateur;
import project.miageif.services.AdministrateurService;

@ManagedBean
@RequestScoped
public class AdministrateurTest {
	
	@Inject
	private Administrateur user = new Administrateur();
	
	 @Test
	 public void test() {
		 assertEquals(0, user.getId());
	 }

}
