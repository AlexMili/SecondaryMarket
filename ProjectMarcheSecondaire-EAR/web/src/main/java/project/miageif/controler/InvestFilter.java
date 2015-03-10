package project.miageif.controler;

/*
 * Inspiré du site http://stackoverflow.com/questions/24319453/login-logout-in-jsf-2
 * 
 * Permet de filtrer les utilisateurs qui ont accès au dossier Admin
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
import project.miageif.beans.Utilisateur.Type;

@WebFilter("/pages/protected/investisseur/*")
public class InvestFilter implements Filter{
	private ServletContext context;
	
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    	this.context = filterConfig.getServletContext();
        this.context.log("AdminFilter initialized");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    	  HttpServletRequest req = (HttpServletRequest) request;
    	  HttpServletResponse res = (HttpServletResponse) response; 
          Utilisateur auth = (Utilisateur) req.getSession().getAttribute("CURRENT_USER");
     
        if (auth != null) {
        	if(!auth.getType().equals(Type.INVEST)){
        	res.sendRedirect(req.getContextPath() + "/pages/public/accueil.xhtml"); 
        	}
        	else{
        		chain.doFilter(req, res);
        	}
        } else {
            chain.doFilter(req, res); // Logged-in user found, so just continue request.
        }
    }

    @Override
    public void destroy() {
    }
    
}