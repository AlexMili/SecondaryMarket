package project.miageif.beans;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2015-03-03T13:47:56.286+0100")
@StaticMetamodel(Investisseur.class)
public class Investisseur_ {
	public static volatile SingularAttribute<Investisseur, Integer> id;
	public static volatile SingularAttribute<Investisseur, Utilisateur> user;
	public static volatile SingularAttribute<Investisseur, String> nom;
	public static volatile SingularAttribute<Investisseur, String> prenom;
	public static volatile SingularAttribute<Investisseur, String> email;
}
