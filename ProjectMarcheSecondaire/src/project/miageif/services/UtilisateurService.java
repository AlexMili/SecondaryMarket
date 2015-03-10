package project.miageif.services;

import javax.ejb.Local;

import project.miageif.beans.Utilisateur;

@Local
public interface UtilisateurService {
 public Utilisateur findUserByLoginPass(String login, String pass);
 public Utilisateur userUpdate(Utilisateur u);
}
