package project.miageif.controler;

/*
 * Inspiré du site http://stackoverflow.com/questions/24319453/login-logout-in-jsf-2
 * 
 * Protege l'accès des dossiers protected
 * 
 * */
import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import project.miageif.beans.Utilisateur;
import project.miageif.beans.Utilisateur.Status;

@WebFilter("/pages/protected/*")
public class LoginFilter implements Filter{
	private ServletContext context;
	
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    	this.context = filterConfig.getServletContext();
        this.context.log("LoginFilter initialized");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    	  HttpServletRequest req = (HttpServletRequest) request;
    	  HttpServletResponse res = (HttpServletResponse) response; 
          Utilisateur auth = (Utilisateur) req.getSession().getAttribute("CURRENT_USER");
     
        if (auth == null || auth.getStatus().equals(Status.DISCONNECTED)) {
        	res = (HttpServletResponse) response; 
        	System.out.println("******** "+req.getContextPath() + "logout");
        	res.sendRedirect(req.getContextPath() + "/pages/public/login.xhtml"); 
        } else {
            chain.doFilter(req, res); 
        }
    }

    @Override
    public void destroy() {
    }
    
}