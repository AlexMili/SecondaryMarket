

import javax.ejb.EJB;
import javax.enterprise.inject.AmbiguousResolutionException;
import javax.inject.Inject;

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
import project.miageif.beans.Utilisateur;
import project.miageif.dao.GenericDAO;
import project.miageif.dao.implement.AdministrateurDAO;
import project.miageif.manage.PanneauAdminMB;
import project.miageif.services.AdministrateurService;
import project.miageif.services.implement.AdministrateurServiceImp;

@RunWith(Arquillian.class)
public class AdminMBTest {
	
	@Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class, "test.jar")
            .addClasses(Investisseur.class,PanneauAdminMB.class,Utilisateur.class,Administrateur.class, AdministrateurService.class,
            		AdministrateurServiceImp.class, AdministrateurDAO.class, GenericDAO.class)
            .addAsResource("hibernate.cfg.xml", "hibernate.cfg.xml") 
            .addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml") 
            .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }
	
	@Test
	public void TestNombreProfilInvestApproval()
	{
		PanneauAdminMB admb = new PanneauAdminMB();
		//Assert.assertEquals(0, admb.getInvest().size());
		
	}
	
}