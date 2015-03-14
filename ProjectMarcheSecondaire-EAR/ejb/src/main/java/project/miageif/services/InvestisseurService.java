package project.miageif.services;

import java.util.List;

import javax.ejb.Local;

import project.miageif.beans.Administrateur;
import project.miageif.beans.Investisseur;
import project.miageif.beans.Utilisateur;

@Local
public interface InvestisseurService {
 public Investisseur findInvestByID(int id);
 public Investisseur updateInvest(Investisseur u);
 public void createInvest(Investisseur u);
 public List<Investisseur> findAll();
}
