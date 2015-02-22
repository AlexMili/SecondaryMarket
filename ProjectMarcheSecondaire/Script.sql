CREATE TABLE IF NOT EXISTS `ADMINISTRATEUR` (
  `Id_Administrateur` int(11) NOT NULL AUTO_INCREMENT,
  `login` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  PRIMARY KEY(`Id_Administrateur`)
);

INSERT INTO `ADMINISTRATEUR` (`Id_Administrateur`, `login`, `password`) VALUES (NULL, 'yann', 'yann');