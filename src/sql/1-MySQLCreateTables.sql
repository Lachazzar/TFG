-- Indexes for primary keys have been explicitly created.

DROP TABLE MedicamentChemicalComponent;
DROP TABLE ChemicalComponent_RegularRestriction;
DROP TABLE ChemicalComponent_DiseaseRestriction;
DROP TABLE ChemicalComponent_AllergyRestriction;
DROP TABLE ChemicalComponent_IntoleranceRestriction;
DROP TABLE ChemicalComponent_ComponentRestriction;
DROP TABLE ComponentRestriction;
DROP TABLE DiseaseRestriction;
DROP TABLE AllergyRestriction;
DROP TABLE IntoleranceRestriction;
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

CREATE TABLE MedicamentChemicalComponent (
	medicamentId BIGINT NOT NULL,
	componentId BIGINT NOT NULL,
	CONSTRAINT MedicamentChemicalComponentPK PRIMARY KEY (medicamentId, componentId),
	CONSTRAINT MedicamentChemicalComponentFK FOREIGN KEY (componentId) 
        REFERENCES ChemicalComponent (id) ON DELETE CASCADE,
	CONSTRAINT ChemicalComponentMedicamentFK FOREIGN KEY (medicamentId) 
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

CREATE TABLE DiseaseRestriction (
	id BIGINT NOT NULL AUTO_INCREMENT,
	code VARCHAR(60) NOT NULL,
	restrictionName VARCHAR(60) NOT NULL,
	diseaseId BIGINT NOT NULL,
	CONSTRAINT DiseaseRestrictionPK PRIMARY KEY (id),
	CONSTRAINT DiseaseFK FOREIGN KEY (diseaseId)
        REFERENCES Disease (id) ON DELETE CASCADE
) ENGINE = InnoDB;

CREATE TABLE AllergyRestriction (
	id BIGINT NOT NULL AUTO_INCREMENT,
	code VARCHAR(60) NOT NULL,
	restrictionName VARCHAR(60) NOT NULL,
	allergyId BIGINT NOT NULL,
	CONSTRAINT AllergyRestrictionPK PRIMARY KEY (id),
	CONSTRAINT AllergyFK FOREIGN KEY (allergyId) 
        REFERENCES Allergy (id) ON DELETE CASCADE
) ENGINE = InnoDB;

CREATE TABLE IntoleranceRestriction (
	id BIGINT NOT NULL AUTO_INCREMENT,
	code VARCHAR(60) NOT NULL,
	restrictionName VARCHAR(60) NOT NULL,
	intoleranceId BIGINT NOT NULL,
	CONSTRAINT IntoleranceRestrictionPK PRIMARY KEY (id),
	CONSTRAINT IntoleranceFK FOREIGN KEY (intoleranceId) 
        REFERENCES Intolerance (id) ON DELETE CASCADE
) ENGINE = InnoDB;

CREATE TABLE ComponentRestriction (
	id BIGINT NOT NULL AUTO_INCREMENT,
	code VARCHAR(10) NOT NULL,
	restrictionName VARCHAR(60) NOT NULL,
	CONSTRAINT ChemicalComponentsRestrictionPK PRIMARY KEY (id)
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

CREATE TABLE ChemicalComponent_DiseaseRestriction (
	chemicalComponent_id BIGINT NOT NULL,
	diseaseRestriction_id BIGINT NOT NULL,
	CONSTRAINT ChemicalComponentDiseaseRestrictionPK PRIMARY KEY (chemicalComponent_id, diseaseRestriction_id),
	CONSTRAINT DiseaseRestrictionChemicalComponentFK FOREIGN KEY (chemicalComponent_id) 
        REFERENCES ChemicalComponent (id) ON DELETE CASCADE,
	CONSTRAINT DiseaseRestrictionFK FOREIGN KEY (diseaseRestriction_id) 
        REFERENCES DiseaseRestriction (id) ON DELETE CASCADE
) ENGINE = InnoDB;

CREATE TABLE ChemicalComponent_AllergyRestriction (
	chemicalComponent_id BIGINT NOT NULL,
	allergyRestriction_id BIGINT NOT NULL,
	CONSTRAINT ChemicalComponentAllergyRestrictionPK PRIMARY KEY (chemicalComponent_id, allergyRestriction_id),
	CONSTRAINT AllergyRestrictionChemicalComponentFK FOREIGN KEY (chemicalComponent_id) 
        REFERENCES ChemicalComponent (id) ON DELETE CASCADE,
	CONSTRAINT AllergyRestrictionFK FOREIGN KEY (allergyRestriction_id) 
        REFERENCES AllergyRestriction (id) ON DELETE CASCADE
) ENGINE = InnoDB;

CREATE TABLE ChemicalComponent_IntoleranceRestriction (
	chemicalComponent_id BIGINT NOT NULL,
	intoleranceRestriction_id BIGINT NOT NULL,
	CONSTRAINT ChemicalComponentIntoleranceRestrictionPK PRIMARY KEY (chemicalComponent_id, intoleranceRestriction_id),
	CONSTRAINT IntoleranceRestrictionChemicalComponentFK FOREIGN KEY (chemicalComponent_id) 
        REFERENCES ChemicalComponent (id) ON DELETE CASCADE,
	CONSTRAINT IntoleranceRestrictionFK FOREIGN KEY (intoleranceRestriction_id) 
        REFERENCES IntoleranceRestriction (id) ON DELETE CASCADE
) ENGINE = InnoDB;

CREATE TABLE ChemicalComponent_ComponentRestriction (
	chemicalComponent_id BIGINT NOT NULL,
	componentRestriction_id BIGINT NOT NULL,
	CONSTRAINT ChemicalComponentComponentRestrictionPK PRIMARY KEY (chemicalComponent_id, componentRestriction_id),
	CONSTRAINT ComponentRestrictionChemicalComponentFK FOREIGN KEY (chemicalComponent_id) 
        REFERENCES ChemicalComponent (id) ON DELETE CASCADE,
	CONSTRAINT ComponentRestrictionFK FOREIGN KEY (componentRestriction_id) 
        REFERENCES ComponentRestriction (id) ON DELETE CASCADE
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
