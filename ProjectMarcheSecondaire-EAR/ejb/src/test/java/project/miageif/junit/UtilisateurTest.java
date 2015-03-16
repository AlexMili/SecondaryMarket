package project.miageif.junit;

import java.awt.Window.Type;
import java.util.List;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.persistence.Query;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import project.miageif.beans.Administrateur;
import project.miageif.beans.Investisseur;
import project.miageif.beans.Membre;
import project.miageif.beans.Societe;
import project.miageif.beans.Utilisateur;
import project.miageif.beans.Utilisateur.Approval;
import project.miageif.beans.Utilisateur.Status;
import project.miageif.dao.GenericDAO;
import project.miageif.dao.implement.AdministrateurDAO;
import project.miageif.dao.implement.InvestisseurDAO;
import project.miageif.dao.implement.MembreDAO;
import project.miageif.dao.implement.SocieteDAO;
import project.miageif.dao.implement.UtilisateurDAO;
import project.miageif.services.AdministrateurService;
import project.miageif.services.InvestisseurService;
import project.miageif.services.MembreService;
import project.miageif.services.SocieteService;
import project.miageif.services.UtilisateurService;
import project.miageif.services.implement.AdministrateurServiceImp;
import project.miageif.services.implement.InvestisseurServiceImp;
import project.miageif.services.implement.MembreServiceImp;
import project.miageif.services.implement.SocieteServiceImp;
import project.miageif.services.implement.UtilisateurServiceImp;

@RunWith(Arquillian.class)
public class UtilisateurTest {
	@Deployment
	public static JavaArchive createDeployment() {
		return ShrinkWrap
				.create(JavaArchive.class, "test.jar")
				.addClasses(Investisseur.class, Utilisateur.class,
						Administrateur.class, Membre.class, Societe.class,
						UtilisateurService.class, MembreService.class,
						MembreServiceImp.class, UtilisateurServiceImp.class,
						MembreDAO.class, UtilisateurDAO.class, GenericDAO.class)
				.addAsResource("hibernate.cfg.xml", "hibernate.cfg.xml")
				.addAsResource("META-INF/persistence.xml",
						"META-INF/persistence.xml")
				.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
	}

	@Inject
	Utilisateur user;

	@EJB
	UtilisateurService userServ;

	@Test
	public void findUtilisateurByLoginPass() {
		user = userServ.findUserByLoginPass("cici", "cici");
		Assert.assertEquals(Status.DISCONNECTED, user.getStatus());
		Assert.assertEquals(1, user.getId());
	}
	
	@Test
	public void updateUtilisateur(){
		Utilisateur user1 = new Utilisateur();
		user1.setId(10);
		user1.setLogin("hihi");
		user1.setPassword("hihi");
		user1.setStatus(Status.DISCONNECTED);
		userServ.createUser(user1);
		
		Utilisateur user2 = userServ.findUserByLoginPass("hihi", "hihi");
		Assert.assertEquals("hihi", user2.getLogin());
		Assert.assertEquals(10, user2.getId());
	}

}
