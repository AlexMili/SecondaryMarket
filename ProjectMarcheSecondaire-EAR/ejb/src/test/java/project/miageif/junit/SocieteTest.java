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
import project.miageif.beans.Societe;
import project.miageif.beans.Utilisateur;
import project.miageif.dao.GenericDAO;
import project.miageif.dao.implement.AdministrateurDAO;
import project.miageif.dao.implement.SocieteDAO;
import project.miageif.services.AdministrateurService;
import project.miageif.services.SocieteService;
import project.miageif.services.implement.AdministrateurServiceImp;
import project.miageif.services.implement.SocieteServiceImp;

@RunWith(Arquillian.class)
public class SocieteTest{
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class, "test.jar")
            .addClasses(Societe.class,SocieteService.class, SocieteServiceImp.class, SocieteDAO.class, GenericDAO.class)
            .addAsResource("hibernate.cfg.xml", "hibernate.cfg.xml") 
            .addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml") 
            .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }
    
    @Inject
    Societe societe;
    
    @EJB
    SocieteService societeServ;
    
    @Test
    public void testAllSocieteApprouvees() {
    	//Query test = societeServ.getAllSocieteApprouvees();
    	//Assert.assertNotEquals(null, test);
	}

    @Test
    public void testSocieteCreateDelete() {
    	Societe mySociete = new Societe();
    	mySociete.setAdresse("42 place du marechal");
    	mySociete.setCodePostal(75016);
    	mySociete.setVille("Paris");
    	mySociete.setNom("Enterprise");
    	mySociete.setEmail("societe@awesome.com");
    	mySociete.setTelephone(123789436);
    	societeServ.createSociete(mySociete);
    	
    	Societe newmySociete = societeServ.findSocieteByName("Enterprise");

    	Assert.assertNotEquals(null, newmySociete);
    	Assert.assertEquals("Paris", newmySociete.getVille());
    	
    	societeServ.deleteSociete(mySociete);

    	mySociete = societeServ.findSocieteByName("Enterprise");
    	Assert.assertEquals(null, mySociete);
    }
    
    @Test
    public void testSocieteUpdate()
    {
    	Societe mySociete = new Societe();
    	mySociete.setAdresse("42 place du marechal");
    	mySociete.setCodePostal(75016);
    	mySociete.setVille("Paris");
    	mySociete.setNom("Enterprise");
    	mySociete.setEmail("societe@awesome.com");
    	mySociete.setTelephone(123789436);
    	societeServ.createSociete(mySociete);
    	
    	mySociete.setNom("Change Test");
    	societeServ.updateSociete(mySociete);
    	Societe newmySociete = societeServ.findSocieteByName("Change Test");

    	Assert.assertNotEquals(null, newmySociete);
    	Assert.assertEquals("Paris", newmySociete.getVille());
    	
    	societeServ.deleteSociete(mySociete);

    	mySociete = societeServ.findSocieteByName("Enterprise");
    	Assert.assertEquals(null, mySociete);
    }
}