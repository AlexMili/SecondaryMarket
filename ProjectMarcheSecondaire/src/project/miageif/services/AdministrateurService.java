package project.miageif.services;

import javax.ejb.Local;

import project.miageif.beans.Administrateur;

@Local
public interface AdministrateurService {
 public Administrateur findUserByLoginPass(String login, String pass);
}
