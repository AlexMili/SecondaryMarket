package fr.dauphine.mido.as.banquetest.beans;
public class Operation {
private int idOperation;
private String noCompte; 
private String dateOperation; 
private String heureOperation; 
private char operation;
private float valeur;
public Operation() {
	// TODO Auto-generated constructor stub
}
public int getIdOperation() {
	return idOperation; }
public void setIdOperation(int idOperation) { this.idOperation = idOperation;
}
public String getNoCompte() {
	return noCompte; }
public void setNoCompte(String string) {
	noCompte=string;
	
}
public void setDateOperation(String string) {
	dateOperation=string;
}
public void setHeureOperation(String string) {
	heureOperation=string;
}
public void setOperation(char c) {
	operation=c;
}
public void setValeur(Float float1) {
	valeur=float1;
}
public float getValeur() {
	return valeur;
}
public void setValeur(float valeur) {
	this.valeur = valeur;
}
public String getDateOperation() {
	return dateOperation;
}
public String getHeureOperation() {
	return heureOperation;
}
public char getOperation() {
	return operation;
}

}
