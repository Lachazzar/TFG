-- Indexes for primary keys have been explicitly created.

DROP TABLE ComponentRestriction;
DROP TABLE MedicamentChemicalComponent;
DROP TABLE ChemicalComponentDiseaseRestriction;
DROP TABLE ChemicalComponentAllergyRestriction;
DROP TABLE ChemicalComponentIntoleranceRestriction;
DROP TABLE ChemicalComponentRegularRestriction;
DROP TABLE DiseaseRestriction;
DROP TABLE AllergyRestriction;
DROP TABLE IntoleranceRestriction;
DROP TABLE ChemicalComponentsRestriction;
DROP TABLE ChemicalComponent;
DROP TABLE RegularRestriction;
DROP TABLE CommercialMedicament;
DROP TABLE Family;
DROP TABLE Disease;
DROP TABLE Allergy;
DROP TABLE Intolerance;
DROP TABLE Medicament;


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
        REFERENCES Medicament (id)
)ENGINE = InnoDB;

CREATE INDEX CommercialMedicamentIndexByName ON CommercialMedicament (name);

CREATE TABLE Family (
	id BIGINT NOT NULL AUTO_INCREMENT,
	familyName VARCHAR(60) NOT NULL,
	subfamilyName VARCHAR(60),
	CONSTRAINT UserPK PRIMARY KEY (id)
) ENGINE = InnoDB;

CREATE TABLE ChemicalComponent (
	id BIGINT NOT NULL AUTO_INCREMENT,
	componentName VARCHAR(60) NOT NULL,
	familyId BIGINT NOT NULL,
	CONSTRAINT ChemicalComponentPK PRIMARY KEY (id),
	CONSTRAINT componentNameUniqueKey UNIQUE (componentName),
	CONSTRAINT FamilyFK FOREIGN KEY (familyId) 
        REFERENCES Family (id)
) ENGINE = InnoDB;

CREATE INDEX ChemicalComponentByName ON ChemicalComponent (componentName);


CREATE TABLE MedicamentChemicalComponent (
	medicamentId BIGINT NOT NULL,
	componentId BIGINT NOT NULL,
	CONSTRAINT MedicamentChemicalComponentPK PRIMARY KEY (medicamentId, componentId),
	CONSTRAINT MedicamentChemicalComponentFK FOREIGN KEY (componentId) 
        REFERENCES ChemicalComponent (id),
	CONSTRAINT ChemicalComponentMedicamentFK FOREIGN KEY (medicamentId) 
        REFERENCES Medicament (id)
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
	id BIGINT NOT NULL,
	code VARCHAR(10) NOT NULL,
	restrictionName VARCHAR(60) NOT NULL,
	severity TINYINT NOT NULL,
	CONSTRAINT RegularRestrictionPK PRIMARY KEY (id)
) ENGINE = InnoDB;

CREATE TABLE DiseaseRestriction (
	id BIGINT NOT NULL AUTO_INCREMENT,
	code VARCHAR(10) NOT NULL,
	restrictionName VARCHAR(60) NOT NULL,
	severity TINYINT NOT NULL,
	diseaseId BIGINT NOT NULL,
	CONSTRAINT DiseaseRestrictionPK PRIMARY KEY (id),
	CONSTRAINT DiseaseFK FOREIGN KEY (diseaseId) 
        REFERENCES Disease (id)
) ENGINE = InnoDB;

CREATE TABLE AllergyRestriction (
	id BIGINT NOT NULL AUTO_INCREMENT,
	code VARCHAR(10) NOT NULL,
	restrictionName VARCHAR(60) NOT NULL,
	severity TINYINT NOT NULL,
	allergyId BIGINT NOT NULL,
	CONSTRAINT AllergyRestrictionPK PRIMARY KEY (id),
	CONSTRAINT AllergyFK FOREIGN KEY (allergyId) 
        REFERENCES Allergy (id)
) ENGINE = InnoDB;

CREATE TABLE IntoleranceRestriction (
	id BIGINT NOT NULL AUTO_INCREMENT,
	code VARCHAR(10) NOT NULL,
	restrictionName VARCHAR(60) NOT NULL,
	severity TINYINT NOT NULL,
	intoleranceId BIGINT NOT NULL,
	CONSTRAINT IntoleranceRestrictionPK PRIMARY KEY (id),
	CONSTRAINT IntoleranceFK FOREIGN KEY (intoleranceId) 
        REFERENCES Intolerance (id)
) ENGINE = InnoDB;

CREATE TABLE ChemicalComponentsRestriction (
	id BIGINT NOT NULL AUTO_INCREMENT,
	code VARCHAR(10) NOT NULL,
	restrictionName VARCHAR(60) NOT NULL,
	severity TINYINT NOT NULL,
	component1Id BIGINT NOT NULL,
	component2Id BIGINT NOT NULL,
	CONSTRAINT ChemicalComponentsRestrictionPK PRIMARY KEY (id),
	CONSTRAINT Component1FK FOREIGN KEY (component1Id) 
        REFERENCES ChemicalComponent (id),
	CONSTRAINT Component2FK FOREIGN KEY (component2Id) 
        REFERENCES ChemicalComponent (id)
) ENGINE = InnoDB;

CREATE TABLE ChemicalComponentRegularRestriction (
	componentId BIGINT NOT NULL,
	regularRestrictionId BIGINT NOT NULL,
	CONSTRAINT ChemicalComponentRegularRestrictionPK PRIMARY KEY (componentId, regularRestrictionId),
	CONSTRAINT RegularRestrictionChemicalComponentFK FOREIGN KEY (componentId) 
        REFERENCES ChemicalComponent (id),
	CONSTRAINT RegularRestrictionFK FOREIGN KEY (regularRestrictionId) 
        REFERENCES RegularRestriction (id)
) ENGINE = InnoDB;

CREATE TABLE ChemicalComponentDiseaseRestriction (
	componentId BIGINT NOT NULL,
	diseaseRestrictionId BIGINT NOT NULL,
	CONSTRAINT ChemicalComponentDiseaseRestrictionPK PRIMARY KEY (componentId, diseaseRestrictionId),
	CONSTRAINT DiseaseRestrictionChemicalComponentFK FOREIGN KEY (componentId) 
        REFERENCES ChemicalComponent (id),
	CONSTRAINT DiseaseRestrictionFK FOREIGN KEY (diseaseRestrictionId) 
        REFERENCES DiseaseRestriction (id)
) ENGINE = InnoDB;

CREATE TABLE ChemicalComponentAllergyRestriction (
	componentId BIGINT NOT NULL,
	allergyRestrictionId BIGINT NOT NULL,
	CONSTRAINT ChemicalComponentAllergyRestrictionPK PRIMARY KEY (componentId, allergyRestrictionId),
	CONSTRAINT AllergyRestrictionChemicalComponentFK FOREIGN KEY (componentId) 
        REFERENCES ChemicalComponent (id),
	CONSTRAINT AllergyRestrictionFK FOREIGN KEY (allergyRestrictionId) 
        REFERENCES AllergyRestriction (id)
) ENGINE = InnoDB;

CREATE TABLE ChemicalComponentIntoleranceRestriction (
	componentId BIGINT NOT NULL,
	intoleranceRestrictionId BIGINT NOT NULL,
	CONSTRAINT ChemicalComponentIntoleranceRestrictionPK PRIMARY KEY (componentId, intoleranceRestrictionId),
	CONSTRAINT IntoleranceRestrictionChemicalComponentFK FOREIGN KEY (componentId) 
        REFERENCES ChemicalComponent (id),
	CONSTRAINT IntoleranceRestrictionFK FOREIGN KEY (intoleranceRestrictionId) 
        REFERENCES IntoleranceRestriction (id)
) ENGINE = InnoDB;
