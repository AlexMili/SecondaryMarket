package project.miageif.services;

import java.util.List;

import javax.ejb.Local;

import project.miageif.beans.Administrateur;
import project.miageif.beans.Investisseur;
import project.miageif.beans.Societe;
import project.miageif.beans.Utilisateur;

@Local
public interface SocieteService {
 public Societe findSocieteByID(int id);
 public Societe updateSociete(Societe u);
 public void createSociete(Societe u);
 public List getAllSocieteApprouvees();
}
