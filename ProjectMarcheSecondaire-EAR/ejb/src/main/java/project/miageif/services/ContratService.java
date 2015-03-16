package project.miageif.services;

import java.util.List;

import javax.ejb.Local;

import project.miageif.beans.Administrateur;
import project.miageif.beans.Contrat;
import project.miageif.beans.Investisseur;
import project.miageif.beans.Membre;
import project.miageif.beans.Utilisateur;

@Local
public interface ContratService {
 public Contrat findContratByID(int id);
 public Contrat updateContrat(Contrat u);
 public void createContrat(Contrat u);
 public List<Contrat> findAll();
}
