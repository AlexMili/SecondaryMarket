package project.miageif.services;

import java.util.List;

import javax.ejb.Local;
import project.miageif.beans.Societe;

@Local
public interface SocieteService {
 public Societe findSocieteByID(int id);
 public Societe findSocieteByName(String name);
 public Societe updateSociete(Societe u);
 public void createSociete(Societe u);
 public void deleteSociete(Societe u);
 public List getAllSocieteApprouvees();
 public List<Societe> findAll();
}
