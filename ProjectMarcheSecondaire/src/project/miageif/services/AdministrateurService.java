package project.miageif.services;

import javax.ejb.Local;

import project.miageif.beans.Administrateur;
import project.miageif.beans.Utilisateur;

@Local
public interface AdministrateurService {
 public Administrateur findAdminByID(int id);
 public Administrateur updateAdmin(Administrateur u);
}
