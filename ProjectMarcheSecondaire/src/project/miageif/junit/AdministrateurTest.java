package project.miageif.junit;

import static org.junit.Assert.*;

import java.util.Properties;

import javax.ejb.EJB;
import javax.ejb.embeddable.EJBContainer;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
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
	private Administrateur user;
	@EJB
	private AdministrateurService userFacade;
	
	private static EJBContainer    ejbContainer;
	 private static Context        ctx;
	 
	 @BeforeClass
	 public static void setUp() {
	 ejbContainer = EJBContainer.createEJBContainer();
	 ctx = ejbContainer.getContext();
	 }
	 
	 @AfterClass
	 public static void tearDown() {
	 ejbContainer.close();
	 }
	
	 @Test
	 public void test() {
	 try {
	 user = (Administrateur) ctx.lookup("java:jboss/datasources/MARCHE_SECONDAIRE");
	 assertNotNull(user);
	 //List<UserBean> users = userEJB.findAll();
	 //assertNotNull(users);
	 assertEquals(0, user.getId());
	 } catch (NamingException e) {
	 throw new AssertionError(e);
	 }
	 }

}
