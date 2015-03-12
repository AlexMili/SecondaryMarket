package project.miageif.services;

import java.util.List;

import javax.ejb.Local;

import project.miageif.beans.Administrateur;
import project.miageif.beans.Investisseur;
import project.miageif.beans.Membre;
import project.miageif.beans.Utilisateur;

@Local
public interface MembreService {
 public Membre findMembreByID(int id);
 public Membre updateMembre(Membre u);
 public void createMembre(Membre u);
 public List<Membre> findAll();
}
