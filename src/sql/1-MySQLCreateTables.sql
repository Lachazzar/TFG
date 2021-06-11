-- Indexes for primary keys have been explicitly created.

DROP TABLE Medicament_ChemicalComponent;
DROP TABLE ChemicalComponent_RegularRestriction;
DROP TABLE ChemicalComponent_Disease;
DROP TABLE ChemicalComponent_Allergy;
DROP TABLE ChemicalComponent_Intolerance;
DROP TABLE ChemicalComponent_ChemicalComponent;
DROP TABLE RegularRestriction;
DROP TABLE CommercialMedicament;
DROP TABLE ChemicalComponent;
DROP TABLE Family;
DROP TABLE Disease;
DROP TABLE Allergy;
DROP TABLE Intolerance;
DROP TABLE Medicament;
DROP TABLE UserBD;

CREATE TABLE Medicament (
	id BIGINT NOT NULL AUTO_INCREMENT,
	name VARCHAR(100) NOT NULL,
	CONSTRAINT medicamentNameUniqueKey UNIQUE (name),
	CONSTRAINT MedicamentPK PRIMARY KEY (id)
) ENGINE = InnoDB;

CREATE TABLE CommercialMedicament (
	id BIGINT NOT NULL AUTO_INCREMENT,
	name VARCHAR(100) NOT NULL,
	medicamentId BIGINT NOT NULL,
	CONSTRAINT CommercialMedicinePK PRIMARY KEY (id),
	CONSTRAINT commercialMedicamentNameUniqueKey UNIQUE (name),
	CONSTRAINT MedicamentFK FOREIGN KEY (medicamentId)
        REFERENCES Medicament (id) ON DELETE CASCADE
)ENGINE = InnoDB;

CREATE INDEX CommercialMedicamentIndexByName ON CommercialMedicament (name);

CREATE TABLE Family (
	id BIGINT NOT NULL AUTO_INCREMENT,
	familyName VARCHAR(100) NOT NULL,
	CONSTRAINT UserPK PRIMARY KEY (id)
) ENGINE = InnoDB;

CREATE TABLE ChemicalComponent (
	id BIGINT NOT NULL AUTO_INCREMENT,
	componentName VARCHAR(60) NOT NULL,
	familyId BIGINT NOT NULL,
	CONSTRAINT ChemicalComponentPK PRIMARY KEY (id),
	CONSTRAINT componentNameUniqueKey UNIQUE (componentName),
	CONSTRAINT FamilyFK FOREIGN KEY (familyId) 
        REFERENCES Family (id) ON DELETE CASCADE
) ENGINE = InnoDB;

CREATE INDEX ChemicalComponentByName ON ChemicalComponent (componentName);

CREATE TABLE Medicament_ChemicalComponent (
	medicament_id BIGINT NOT NULL,
	component_id BIGINT NOT NULL,
	CONSTRAINT MedicamentChemicalComponentPK PRIMARY KEY (medicament_id, component_id),
	CONSTRAINT MedicamentChemicalComponentFK FOREIGN KEY (component_id) 
        REFERENCES ChemicalComponent (id) ON DELETE CASCADE,
	CONSTRAINT ChemicalComponentMedicamentFK FOREIGN KEY (medicament_id) 
        REFERENCES Medicament (id) ON DELETE CASCADE
) ENGINE = InnoDB;

CREATE TABLE Disease (
	id BIGINT NOT NULL AUTO_INCREMENT,
	diseaseName VARCHAR(60) NOT NULL,
	CONSTRAINT DiseasePK PRIMARY KEY (id),
	CONSTRAINT diseaseNameUniqueKey UNIQUE (diseaseName)
) ENGINE = InnoDB;

CREATE TABLE Allergy (
	id BIGINT NOT NULL AUTO_INCREMENT,
	allergyName VARCHAR(60) NOT NULL,
	CONSTRAINT AllergyPK PRIMARY KEY (id),
	CONSTRAINT allergyNameUniqueKey UNIQUE (allergyName)
) ENGINE = InnoDB;

CREATE TABLE Intolerance (
	id BIGINT NOT NULL AUTO_INCREMENT,
	intoleranceName VARCHAR(60) NOT NULL,
	CONSTRAINT IntolerancePK PRIMARY KEY (id),
	CONSTRAINT intoleranceNameUniqueKey UNIQUE (intoleranceName)
) ENGINE = InnoDB;

CREATE TABLE RegularRestriction (
	id BIGINT NOT NULL AUTO_INCREMENT,
	code VARCHAR(10) NOT NULL,
	restrictionName VARCHAR(60) NOT NULL,
	CONSTRAINT RegularRestrictionPK PRIMARY KEY (id)
) ENGINE = InnoDB;

CREATE TABLE ChemicalComponent_RegularRestriction (
	chemicalComponent_id BIGINT NOT NULL,
	regularRestriction_id BIGINT NOT NULL,
	CONSTRAINT ChemicalComponentRegularRestrictionPK PRIMARY KEY (chemicalComponent_id, regularRestriction_id),
	CONSTRAINT RegularRestrictionChemicalComponentFK FOREIGN KEY (chemicalComponent_id) 
        REFERENCES ChemicalComponent (id) ON DELETE CASCADE,
	CONSTRAINT RegularRestrictionFK FOREIGN KEY (regularRestriction_id) 
        REFERENCES RegularRestriction (id) ON DELETE CASCADE
) ENGINE = InnoDB;

CREATE TABLE ChemicalComponent_Disease (
	chemicalComponent_id BIGINT NOT NULL,
	disease_id BIGINT NOT NULL,
	CONSTRAINT ChemicalComponentDiseasePK PRIMARY KEY (chemicalComponent_id, disease_id),
	CONSTRAINT DiseaseChemicalComponentFK FOREIGN KEY (chemicalComponent_id) 
        REFERENCES ChemicalComponent (id) ON DELETE CASCADE,
	CONSTRAINT DiseaseFK FOREIGN KEY (disease_id) 
        REFERENCES Disease (id) ON DELETE CASCADE
) ENGINE = InnoDB;

CREATE TABLE ChemicalComponent_Allergy (
	chemicalComponent_id BIGINT NOT NULL,
	allergy_id BIGINT NOT NULL,
	CONSTRAINT ChemicalComponentAllergyPK PRIMARY KEY (chemicalComponent_id, allergy_id),
	CONSTRAINT AllergyChemicalComponentFK FOREIGN KEY (chemicalComponent_id) 
        REFERENCES ChemicalComponent (id) ON DELETE CASCADE,
	CONSTRAINT AllergyFK FOREIGN KEY (allergy_id) 
        REFERENCES Allergy (id) ON DELETE CASCADE
) ENGINE = InnoDB;

CREATE TABLE ChemicalComponent_Intolerance (
	chemicalComponent_id BIGINT NOT NULL,
	intolerance_id BIGINT NOT NULL,
	CONSTRAINT ChemicalComponentIntolerancePK PRIMARY KEY (chemicalComponent_id, intolerance_id),
	CONSTRAINT IntoleranceChemicalComponentFK FOREIGN KEY (chemicalComponent_id) 
        REFERENCES ChemicalComponent (id) ON DELETE CASCADE,
	CONSTRAINT IntoleranceFK FOREIGN KEY (intolerance_id) 
        REFERENCES Intolerance (id) ON DELETE CASCADE
) ENGINE = InnoDB;

CREATE TABLE ChemicalComponent_ChemicalComponent (
	chemicalComponent_id BIGINT NOT NULL,
	chemicalComponent2_id BIGINT NOT NULL,
	CONSTRAINT ChemicalComponentChemicalComponentPK PRIMARY KEY (chemicalComponent_id, chemicalComponent2_id),
	CONSTRAINT ChemicalComponent1FK FOREIGN KEY (chemicalComponent_id) 
        REFERENCES ChemicalComponent (id) ON DELETE CASCADE,
	CONSTRAINT ChemicalComponent2FK FOREIGN KEY (chemicalComponent2_id) 
        REFERENCES ChemicalComponent (id) ON DELETE CASCADE
) ENGINE = InnoDB;

CREATE TABLE UserBD (
    id BIGINT NOT NULL AUTO_INCREMENT,
    userName VARCHAR(60) COLLATE latin1_bin NOT NULL,
    password VARCHAR(100) NOT NULL,
    email VARCHAR(60) NOT NULL,
    role TINYINT NOT NULL,
    CONSTRAINT UserPK PRIMARY KEY (id),
    CONSTRAINT UserNameUniqueKey UNIQUE (userName)
) ENGINE = InnoDB;
