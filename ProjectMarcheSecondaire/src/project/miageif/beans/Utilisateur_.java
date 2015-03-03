package project.miageif.beans;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import project.miageif.beans.Utilisateur.Status;
import project.miageif.beans.Utilisateur.Type;

@Generated(value="Dali", date="2015-03-03T14:26:34.497+0100")
@StaticMetamodel(Utilisateur.class)
public class Utilisateur_ {
	public static volatile SingularAttribute<Utilisateur, Integer> id;
	public static volatile SingularAttribute<Utilisateur, String> login;
	public static volatile SingularAttribute<Utilisateur, String> password;
	public static volatile SingularAttribute<Utilisateur, Status> status;
	public static volatile SingularAttribute<Utilisateur, Type> type;
}
