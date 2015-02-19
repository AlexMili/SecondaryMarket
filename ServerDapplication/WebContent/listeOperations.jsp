<%@page import="java.util.ArrayList"%>
<%@page import="fr.dauphine.mido.as.banquetest.beans.Operation"%> <%@page import="fr.dauphine.mido.as.banquetest.beans.Compte"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:useBean id="_COMPTE_COURANT" class="fr.dauphine.mido.as.banquetest.beans.Compte"
scope="session"></jsp:useBean>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"> <title>Etat du Compte</title>
</head>
<body>
<CENTER> <H4>
Compte numero : <jsp:getProperty name="_COMPTE_COURANT" property="nocompte" />
</H4>
<TABLE border="1">
<TBODY> <TR>
<TD align="center">ID operation</TD> <TD align="center">Numero de compte</TD> <TD align="center">Operation</TD> <TD align="center">Montant</TD>
<TD align="center">date</TD>
<TD align="center">Heure</TD> </TR>
</TBODY>
<%
Operation uneOperation = null;
ArrayList<Operation> resultat = (ArrayList<Operation>) session.getAttribute("_LISTE_OPERATIONS");
if (resultat == null) {
System.out.println("Liste operations est a null");
} else {
for (int i = 0; i < resultat.size(); i++) { uneOperation = (Operation) resultat.get(i);
%> <TBODY>
<TR>
<TD align="center"><%=uneOperation.getIdOperation()%></TD> <TD align="center"><%=uneOperation.getNoCompte()%></TD>
<TD align="center"><%=uneOperation.getOperation()%></TD>
<TD align="center"><%=uneOperation.getValeur()%></TD>
<TD align="center"><%=uneOperation.getDateOperation()%></TD> <TD align="center"><%=uneOperation.getHeureOperation()%></TD>
</TR> <%
} }
%>
</TABLE> </body>
</html>