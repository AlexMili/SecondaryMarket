package fr.dauphine.mido.as.banquetest.web;
import java.io.IOException; 
import java.io.PrintWriter; 
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet; 
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest; 
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.naming.InitialContext; 
import javax.naming.NamingException;
import javax.sql.DataSource; 

import fr.dauphine.mido.as.banquetest.beans.Compte;
import fr.dauphine.mido.as.banquetest.beans.Operation;
import fr.dauphine.mido.as.banquetest.beans.GestionCompte1;

import javax.sql.DataSource;

import java.sql.Connection;


/**
 * Servlet implementation class ServletPrincipal 
 * */
/**
 * @author yanndalmat
 *
 */
@WebServlet("/ServletPrincipal")
public class ServletPrincipal extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
	 * @see HttpServlet#HttpServlet() */
	public ServletPrincipal() { 
		super();
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse * response)
	 */
	protected void doGet(HttpServletRequest request,
		HttpServletResponse response) throws ServletException, IOException {
		doGetVersion1(request, response);
		doGetVersion2(request, response);
	}
	@SuppressWarnings("deprecation")
	protected void doGetVersion1(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException { 
			PrintWriter printWriter = response.getWriter();
			printWriter.println("<h2>");
			printWriter.println("Entrée dans la servlet à̀ "
					+ new Date().toLocaleString()); 
			printWriter.println("</h2>");
	}
	
	protected void doPostVersion1(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			response.setContentType("text/html");
			String noDeCompte = request.getParameter("noDeCompte"); 
			Compte unCompte = new Compte(); 
			unCompte.setNocompte(noDeCompte);
			PrintWriter printWriter = response.getWriter();
			printWriter
			.println("<h2>instanciation d'un compte avec n° de compte = " + unCompte.getNocompte() + "</h2>");
		} catch (IOException ioException) { ioException.printStackTrace();
		} 
	}
	
	
	@SuppressWarnings("deprecation")
	protected void doGetVersion2(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException { 
		try {
			PrintWriter printWriter = response.getWriter();
			InitialContext initialContext = new InitialContext(); 
			javax.sql.DataSource dataSource = (DataSource) initialContext.lookup("java:jboss/datasources/MaDSMySQL"); 
			Connection connection = dataSource.getConnection(); 
			connection.close();
			printWriter.println("<h2>"); 
			printWriter.println("Connexion à la DB ok à "
					+ new Date().toLocaleString()); 
			printWriter.println("</h2>");
		} catch (Exception e2) { 
			System.out.println(e2.getMessage().toString());
		}
		}

	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse * response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
			//doPostVersion1(request, response); 
			doPostVersion3(request, response); 
	}
	
	protected void doPostVersion2(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			response.setContentType("text/html");
			String noDeCompte = request.getParameter("noDeCompte"); 
			Compte unCompte = new Compte(); 
			unCompte.setNocompte(noDeCompte);
			PrintWriter printWriter = response.getWriter();
			InitialContext initialContext = new InitialContext(); 
			javax.sql.DataSource dataSource = (DataSource) initialContext.lookup("java:jboss/datasources/MaDSMySQL");
			ArrayList<Operation> listeOperations = GestionCompte1.rechercheOperations(dataSource, unCompte);
			printWriter.println("<h2>Liste des opérations sur ce compte : </h2>");
			Operation op = null;
			for (int i = 0; i < listeOperations.size(); i++) {
				op = (Operation) listeOperations.get(i); printWriter.println("<h2>Opération n° " + op.getIdOperation()
						+ " de type '" + op.getOperation() + "' le "
						+ op.getDateOperation() + " à "
						+ op.getHeureOperation() + " d'un montant de " + op.getValeur() + " euros</h2>");
			}
		} catch (IOException ioException) {
			ioException.printStackTrace(); } catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		protected void doPostVersion3(HttpServletRequest request, 
				HttpServletResponse response) throws ServletException, IOException {
			try {
				response.setContentType("text/html");
				String noDeCompte = request.getParameter("noDeCompte"); 
				Compte unCompte = new Compte(); 
				unCompte.setNocompte(noDeCompte); 
				InitialContext initialContext = new InitialContext(); 
				javax.sql.DataSource dataSource = (DataSource) initialContext.lookup("java:jboss/datasources/MaDSMySQL");
				ArrayList<Operation> listeOperations = GestionCompte1
						.rechercheOperations(dataSource, unCompte);
				System.out.println("TEST : " + listeOperations.get(0).getOperation());
				HttpSession session = request.getSession(); 
				session.setAttribute("_COMPTE_COURANT", unCompte); 
				session.setAttribute("_LISTE_OPERATIONS", listeOperations); 
				getServletContext().getRequestDispatcher("/listeOperations.jsp")
				.forward(request, response); } catch (IOException ioException) {
					ioException.printStackTrace(); } catch (NamingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		
	
}