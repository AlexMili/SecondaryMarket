package project.miageif.beans;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2015-03-03T14:11:11.917+0100")
@StaticMetamodel(Administrateur.class)
public class Administrateur_ {
	public static volatile SingularAttribute<Administrateur, Integer> id;
	public static volatile SingularAttribute<Administrateur, Utilisateur> user;
	public static volatile SingularAttribute<Administrateur, String> nom;
	public static volatile SingularAttribute<Administrateur, String> prenom;
	public static volatile SingularAttribute<Administrateur, String> email;
}
