package marchesecondaire.project.servlet;
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
import javax.sql.DataSource;
import java.sql.Connection;


/**
 * Servlet implementation class ServletPrincipal 
 * */
/**
 * @author 
 *
 */
@WebServlet("/ServletAccueil")
public class ServletAccueil extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
	 * @see HttpServlet#HttpServlet() */
	public ServletAccueil() { 
		super();
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse * response)
	 */
	protected void doGet(HttpServletRequest request,
		HttpServletResponse response) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher("/index.html").forward(request, response); 
	}
	@SuppressWarnings("deprecation")
	protected void doGetVersion1(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException { 
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse * response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
			
	}
}