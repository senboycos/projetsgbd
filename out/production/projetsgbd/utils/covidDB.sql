CREATE TABLE `coviddb`.`communiques`
( `id` INT NOT NULL AUTO_INCREMENT ,
`nbreTest` INT NOT NULL ,
`nbNouveauxCas` INT NOT NULL ,
`nbCasContacts` INT NOT NULL ,
`nbCasCommunautaires` INT NOT NULL ,
`nbrGueris` INT NOT NULL ,
`nbDeces` INT NOT NULL ,
`nomFichierSource` VARCHAR(50) NOT NULL ,
`dateHeureExtraction` VARCHAR(50) NOT NULL ,
 PRIMARY KEY (`id`)
) ENGINE = MyISAM;





CREATE TABLE `coviddb`.`localite`
( `id` INT(25) NOT NULL ,
 `nomLocalite` VARCHAR(255) NOT NULL ,
 `nbCas` INT(20) NOT NULL ,
 `id_communique` INT NOT NULL ,
PRIMARY KEY (`id`),FOREIGN KEY fk_communique(id_communique)
REFERENCES communiques(id_communique)
ON UPDATE CASCADE
ON DELETE RESTRICT
) ENGINE = MyISAM;