package project.miageif.junit;

import static org.junit.Assert.*;

import org.hibernate.Session;
import org.junit.Test;

import project.miageif.beans.Administrateur;
import project.miageif.utilitaire.HibernateUtil;

public class TestInsertAdministrateur {

	@Test
	public void testADD() {
		Session ses = HibernateUtil.getSessionFactory().getCurrentSession();
		ses.beginTransaction();
		Administrateur a = new Administrateur();
		a.setLogin("YANN");
		a.setPassword("YANN");
		ses.persist(a);
		assertNotNull(a.getId());
		ses.getTransaction().commit();
	}

}
