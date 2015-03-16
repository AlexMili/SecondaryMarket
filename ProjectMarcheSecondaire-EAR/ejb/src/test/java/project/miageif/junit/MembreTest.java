package project.miageif.junit;

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
import project.miageif.dao.GenericDAO;
import project.miageif.dao.implement.AdministrateurDAO;
import project.miageif.dao.implement.InvestisseurDAO;
import project.miageif.dao.implement.MembreDAO;
import project.miageif.dao.implement.SocieteDAO;
import project.miageif.services.AdministrateurService;
import project.miageif.services.InvestisseurService;
import project.miageif.services.MembreService;
import project.miageif.services.SocieteService;
import project.miageif.services.UtilisateurService;
import project.miageif.services.implement.AdministrateurServiceImp;
import project.miageif.services.implement.InvestisseurServiceImp;
import project.miageif.services.implement.MembreServiceImp;
import project.miageif.services.implement.SocieteServiceImp;

@RunWith(Arquillian.class)
public class MembreTest {
	@Deployment
	public static JavaArchive createDeployment() {
		return ShrinkWrap
				.create(JavaArchive.class, "test.jar")
				.addClasses(Investisseur.class, Utilisateur.class,
						Administrateur.class, Investisseur.class, Membre.class,
						Societe.class, UtilisateurService.class,
						SocieteService.class, MembreService.class,
						MembreServiceImp.class, MembreDAO.class,
						GenericDAO.class)
				.addAsResource("hibernate.cfg.xml", "hibernate.cfg.xml")
				.addAsResource("META-INF/persistence.xml",
						"META-INF/persistence.xml")
				.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
	}

	@Inject
	Membre member;

	@EJB
	MembreService memberServ;

	@Test
	public void findMemberByID() {
		member = memberServ.findMembreByID(3);
		Assert.assertEquals("PHAM", member.getPrenom());
		Assert.assertEquals("Hieu", member.getNom());
	}
	@Test
	public void testCreatemembre() {
		Membre createmembre = new Membre();
		// createinvestisseur.setUser();
		createmembre.setNom("Mili");
		createmembre.setEmail("alex@awesome.com");
		 (createmembre).setPrenom("Alex");
		memberServ.createMembre(createmembre);

		List<Membre> newcreatemembre = memberServ.findAll();

		Assert.assertNotEquals(null, newcreatemembre);
		Assert.assertEquals("Alex",
				((Membre) createmembre).getPrenom());
	}
	
	@Test
	public void testInvestisseurUpdate() {
		Membre createmembre = new Membre();
		// createinvestisseur.setUser();
		createmembre.setNom("zhang");
		createmembre.setEmail("societe@awesome.com");
		createmembre.setPrenom("yujuan");
		memberServ.createMembre(createmembre);

		createmembre.setNom("Change");
		memberServ.updateMembre(createmembre);
		List<Membre> newcreatemembre = memberServ.findAll();

		Assert.assertNotEquals(null, newcreatemembre);
		Assert.assertEquals("Change",
				((Membre) createmembre).getNom());

	}

}
