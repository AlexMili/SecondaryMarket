-- ADMIN

INSERT INTO `UTILISATEUR` (`Id_Utilisateur`, `login`, `password`, `status`, `type`, `email`) VALUES
(0, 'admin', 'admin', 0, 0, 'yann.dalmat@live.fr');
INSERT INTO `ADMINISTRATEUR` VALUES(1, 'ADMIN', 'Administrateur', 0);
INSERT INTO `SOCIETE` (`Id_Societe`, `adresse`, `codePostal`, `email`, `isApproved`, `nom`, `prixTitre`, `quantiteTitre`, `telephone`, `ville`) VALUES
(0, 'Test adresse société BNP', 56565, 'testsocieteBNP@societe.com', 0, 'BNP Paribas Cardif', 54.89, 1000, 323232121, 'Test adress');



--- SOCIETE
INSERT INTO `SOCIETE` (`Id_Societe`, `adresse`, `codePostal`, `email`, `isApproved`, `nom`, `prixTitre`, `quantiteTitre`, `telephone`, `ville`) VALUES
(0, 'Test adresse société BNP', 56565, 'testsocieteBNP@societe.com', 0, 'BNP Paribas Cardif', 54.89, 1000, 323232121, 'Test adress');
--- INVEST
INSERT INTO `UTILISATEUR` (`Id_Utilisateur`, `login`, `password`, `status`, `type`, `email`) VALUES
(2, 'invest', 'invest', 0, 1, 'invest@live.fr');
INSERT INTO `INVESTISSEUR` VALUES(1, 0, 'invest nom', 'invest prenom', 2);
--- MEMBRE
INSERT INTO `UTILISATEUR` (`Id_Utilisateur`, `login`, `password`, `status`, `type`, `email`) VALUES
(3, 'membre', 'membre', 0, 2, 'membre@live.fr');
INSERT INTO `MEMBRE` VALUES(0, 0, 'membre nom', 'membre prenom', 0, 3);


INSERT INTO `SOCIETE` (`Id_Societe`, `adresse`, `codePostal`, `email`, `isApproved`, `nom`, `prixTitre`, `quantiteTitre`, `telephone`, `ville`) VALUES(0, 'Test adresse société', 56565, 'testsociete@societe.com', 0, 'Test Société', 54.89, 1000, 323232121, 'Adress test');
INSERT INTO `SOCIETE` (`Id_Societe`, `adresse`, `codePostal`, `email`, `isApproved`, `nom`, `prixTitre`, `quantiteTitre`, `telephone`, `ville`) VALUES(1, 'paris 16', 99999, 'dauphine@daup.fr', 0, 'DAUPHINE', 0, 0, 0, NULL);
INSERT INTO `SOCIETE` (`Id_Societe`, `adresse`, `codePostal`, `email`, `isApproved`, `nom`, `prixTitre`, `quantiteTitre`, `telephone`, `ville`) VALUES(2, 'Paris', 75001, 'sfr@sfr.fr', 0, 'SFR', 0, 0, 0, NULL);
INSERT INTO `SOCIETE` (`Id_Societe`, `adresse`, `codePostal`, `email`, `isApproved`, `nom`, `prixTitre`, `quantiteTitre`, `telephone`, `ville`) VALUES(3, 'marseille', 60606, 'bouygue@band.fr', 0, 'BOUYGUE', 0, 0, 0, NULL);

