package fr.dauphine.mido.as.banquetest.beans;

import java.sql.Connection;
import java.sql.PreparedStatement; import java.sql.ResultSet;
import java.util.ArrayList;
import javax.sql.DataSource;
public class GestionCompte1 {
private final static String _SQL_SELECT_OPERATIONS = "select * from BANQUE_TEST.OPERATIONS where NOCOMPTE like ?";

public static ArrayList<Operation> rechercheOperations(DataSource datasource, Compte unCompte) {
	Connection connection = null; PreparedStatement preparedStatement = null; ResultSet resultSet = null;
	Operation operation = null; ArrayList<Operation> resultat = null;
	try {
		connection = datasource.getConnection(); 
		preparedStatement = connection.prepareStatement(_SQL_SELECT_OPERATIONS); 
		preparedStatement.setString(1, unCompte.getNocompte()); 
		resultSet = preparedStatement.executeQuery();
		resultat = new ArrayList<Operation>();
		while (resultSet.next()) {
			operation = new Operation();
			operation.setIdOperation(new Integer(resultSet.getString(1))
			.intValue()); 
			operation.setNoCompte(resultSet.getString(2)); 
			operation.setDateOperation(resultSet.getString(3)); 
			operation.setHeureOperation(resultSet.getString(4)); 
			operation.setOperation(resultSet.getString(5).toCharArray()[0]); 
			operation.setValeur(new Float(resultSet.getString(6))); 
			resultat.add(operation);
		}
	} catch (Exception e) {
		System.err.println(e.getMessage().toString()); } finally {
			try {
				resultSet.close(); preparedStatement.close(); connection.close();
			} catch (Exception e2) { System.err.println(e2.getMessage().toString());
			} }
	return resultat; 
}
}
