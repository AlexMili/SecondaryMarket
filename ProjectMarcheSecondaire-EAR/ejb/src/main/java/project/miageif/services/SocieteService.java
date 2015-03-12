package project.miageif.services;

import java.util.List;

import javax.ejb.Local;
import javax.persistence.Query;

import project.miageif.beans.Administrateur;
import project.miageif.beans.Investisseur;
import project.miageif.beans.Societe;
import project.miageif.beans.Utilisateur;

@Local
public interface SocieteService {
 public Societe findSocieteByID(int id);
 public Societe findSocieteByName(String name);
 public Societe updateSociete(Societe u);
 public void createSociete(Societe u);
 public void deleteSociete(Societe u);
 public List getAllSocieteApprouvees();
}
