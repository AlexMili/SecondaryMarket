package project.miageif.controler;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

/*Class adapté de http://www.tutorialspoint.com/jsf/jsf_page_navigation.htm */

@ManagedBean(name = "navigationController", eager = true)
@RequestScoped
public class NavigationController implements Serializable {

	private static final long serialVersionUID = 1L;
	
   //this managed property will read value from request parameter pageId
   @ManagedProperty(value="#{param.pageId}")
   private String pageId;

   public String pageAcceuil(){
      return "acceuil";
   }
   
   public String refreshPan(){
	   return "refresh";
   }
   
   public String pageLogin(){
	      return "login";
	   }
   
   public String pageInscriptionSociete(){
	      return "inscriptionSociete";
	   }
   public String pageInscriptionInvest(){
	      return "inscriptionInvestisseur";
	   }
   
   public String mm_inv_validator(){
	      return "validator";
   }
   
   
//   public String showPage(){
//      if(pageId == null){
//         return "accueil";
//      }
//      if(pageId.equals("login")){
//         return "login";
//      }else if(pageId.equals("inscriptionSociete")){
//         return "inscriptionSociete";
//      }else{
//         return "acceuil";
//      }
//   }
   
   public String getPageId() {
	      return pageId;
	   }

	   public void setPageId(String pageId) {
	      this.pageId = pageId;
	   }
}