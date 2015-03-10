//package project.miageif.junit;
//
//import static org.junit.Assert.*;
//
//import javax.validation.constraints.NotNull;
//
//import org.hibernate.Session;
//import org.junit.Test;
//
//import project.miageif.beans.Administrateur;
//import project.miageif.utilitaire.HibernateUtil;
//
//public class AdminTest {
//
//	private Administrateur user;
//	
//	
//	@Test
//	public void test() {
//		 Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//	     session.beginTransaction();
//	     user = (Administrateur) session.load(Administrateur.class, 0);
//	    // System.out.println("**************************** admin = "+ user.getLogin());
//	     session.getTransaction().commit();
//		 assertEquals(0, user.getId());
//	}
//
//}
