package project.miageif.services;

import java.util.List;

import javax.ejb.Local;

import project.miageif.beans.Contrat;
import project.miageif.beans.Offre;

@Local
public interface OffreService {
 public Offre findOffreByID(int id);
 public Offre updateOffre(Offre u);
 public void createOffre(Offre u);
 public List<Offre> findAll();
}
