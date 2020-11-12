-- ----------------------------------------------------------------------------
-- Put here INSERT statements for inserting data required by the application
-- in the "pa-project" database.
-------------------------------------------------------------------------------

-- INSERT INTO Family(familyName, subfamilyName)
--	VALUES ("");
INSERT INTO Family(familyName)
	VALUES ("Antihistamínicos"); --1
INSERT INTO Family(familyName)
	VALUES ("Inhibidores de la 5 alfa reductasa); --2
INSERT INTO Family(familyName)
	VALUES ("Alcaloides"); --3
INSERT INTO Family(familyName)
	VALUES ("Antifúngicos"); --4
INSERT INTO Family(familyName)
	VALUES ("Antidiabéticos"); --5
INSERT INTO Family(familyName)
	VALUES ("Anticoagulantes"); --6 
INSERT INTO Family(familyName)
	VALUES ("Ansiolíticos"); --7
INSERT INTO Family(familyName)
	VALUES ("Antiinflamatorios no esteroideos"); --8
INSERT INTO Family(familyName)
	VALUES ("Glucosidos Cardíacos"); --9
INSERT INTO Family(familyName)
	VALUES ("Vasodilatadores"); --10
INSERT INTO Family(familyName)
	VALUES ("Antibióticos"); --11
INSERT INTO Family(familyName)
	VALUES ("Beta-lactámicos"); --12
INSERT INTO Family(familyName)
	VALUES ("Inhibidores Beta3"); --13
INSERT INTO Family(familyName)
	VALUES ("Inhibidores bomba de protones"); --14
INSERT INTO Family(familyName)
	VALUES ("Vitaminas"); --15
INSERT INTO Family(familyName)
	VALUES ("Anticolinergico"); --16
INSERT INTO Family(familyName)
	VALUES ("Salicilatos"); --17
INSERT INTO Family(familyName)
	VALUES ("Antiepiléptico"); --18
		
-- INSERT INTO Medicament(name)
--	VALUES ("");
INSERT INTO Medicament(name)
	VALUES ("Torecan");
INSERT INTO Medicament(name)
	VALUES ("Propecia");
INSERT INTO Medicament(name)
	VALUES ("Proscar");
INSERT INTO Medicament(name)
	VALUES ("Avidart");
INSERT INTO Medicament(name)
	VALUES ("Duodart");
INSERT INTO Medicament(name)
	VALUES ("Dostinex");
INSERT INTO Medicament(name)
	VALUES ("Diflucan");
INSERT INTO Medicament(name)
	VALUES ("Januvia");
INSERT INTO Medicament(name)
	VALUES ("VFEND");
INSERT INTO Medicament(name)
	VALUES ("Sintrom");
INSERT INTO Medicament(name)
	VALUES ("Valium");
INSERT INTO Medicament(name)
	VALUES ("Neobrufem");
INSERT INTO Medicament(name)
	VALUES ("Nitroglicerina");
INSERT INTO Medicament(name)
	VALUES ("Digoxina");
INSERT INTO Medicament(name)
	VALUES ("Quinina");
INSERT INTO Medicament(name)
	VALUES ("Tetraciclina");
INSERT INTO Medicament(name)
	VALUES ("Voltaren");
INSERT INTO Medicament(name)
	VALUES ("Ibuprofeno");
INSERT INTO Medicament(name)
	VALUES ("Indometacina");
INSERT INTO Medicament(name)
	VALUES ("Diclofenacoketropofeno");
INSERT INTO Medicament(name)
	VALUES ("Penicilina");
INSERT INTO Medicament(name)
	VALUES ("Ceftriaxona");
INSERT INTO Medicament(name)
	VALUES ("Cefditoreno");
INSERT INTO Medicament(name)
	VALUES ("Vesicare");
INSERT INTO Medicament(name)
	VALUES ("Toviaz");
INSERT INTO Medicament(name)
	VALUES ("Betmiga");
INSERT INTO Medicament(name)
	VALUES ("Omeprazol");
INSERT INTO Medicament(name)
	VALUES ("Plavix");
INSERT INTO Medicament(name)
	VALUES ("VIT B12");
INSERT INTO Medicament(name)
	VALUES ("Aspirina");
INSERT INTO Medicament(name)
	VALUES ("Fenitoina");
INSERT INTO Medicament(name)
	VALUES ("Eliquis");
INSERT INTO Medicament(name)
	VALUES ("HeparinasBPM");
INSERT INTO Medicament(name)
	VALUES ("Antiagregantes");



-- INSERT INTO CommercialMedicament(name, medicamentId)
--	VALUES ();
INSERT INTO CommercialMedicament(name, medicamentId)
	VALUES ("Torecan", 1);
INSERT INTO CommercialMedicament(name, medicamentId)
	VALUES ("Propecia", 2);
INSERT INTO CommercialMedicament(name, medicamentId)
	VALUES ("Proscar", 3);
INSERT INTO CommercialMedicament(name, medicamentId)
	VALUES ("Avidart", 4);
INSERT INTO CommercialMedicament(name, medicamentId)
	VALUES ("Duodart", 5);
INSERT INTO CommercialMedicament(name, medicamentId)
	VALUES ("Dostinex", 6);
INSERT INTO CommercialMedicament(name, medicamentId)
	VALUES ("Diflucan", 7);
INSERT INTO CommercialMedicament(name, medicamentId)
	VALUES ("Januvia", 8);
INSERT INTO CommercialMedicament(name, medicamentId)
	VALUES ("VFEND", 9);
INSERT INTO CommercialMedicament(name, medicamentId)
	VALUES ("Sintrom", 10);
INSERT INTO CommercialMedicament(name, medicamentId)
	VALUES ("Valium", 11);
INSERT INTO CommercialMedicament(name, medicamentId)
	VALUES ("Neobrufem", 12);
INSERT INTO CommercialMedicament(name, medicamentId)
	VALUES ("Nitroglicerina", 13);
INSERT INTO CommercialMedicament(name, medicamentId)
	VALUES ("Digoxina", 14);
INSERT INTO CommercialMedicament(name, medicamentId)
	VALUES ("Quinina", 15);
INSERT INTO CommercialMedicament(name, medicamentId)
	VALUES ("Tetraciclina", 16);
INSERT INTO CommercialMedicament(name, medicamentId)
	VALUES ("Voltaren", 17);
INSERT INTO CommercialMedicament(name, medicamentId)
	VALUES ("Ibuprofeno", 18);
INSERT INTO CommercialMedicament(name, medicamentId)
	VALUES ("Indometacina", 19);
INSERT INTO CommercialMedicament(name, medicamentId)
	VALUES ("Diclofenacoketropofeno", 20);
INSERT INTO CommercialMedicament(name, medicamentId)
	VALUES ("Penicilina", 21);
INSERT INTO CommercialMedicament(name, medicamentId)
	VALUES ("Ceftriaxona", 23);
INSERT INTO CommercialMedicament(name, medicamentId)
	VALUES ("Cefditoreno", 24);
INSERT INTO CommercialMedicament(name, medicamentId)
	VALUES ("Vesicare", 25);
INSERT INTO CommercialMedicament(name, medicamentId)
	VALUES ("Toviaz", 26);
INSERT INTO CommercialMedicament(name, medicamentId)
	VALUES ("Betmiga", 27);
INSERT INTO CommercialMedicament(name, medicamentId)
	VALUES ("Losec", 28);
INSERT INTO CommercialMedicament(name, medicamentId)
	VALUES ("Plavix", 29);
INSERT INTO CommercialMedicament(name, medicamentId)
	VALUES ("VIT B12", 30);
INSERT INTO CommercialMedicament(name, medicamentId)
	VALUES ("Aspirina", 31);
INSERT INTO CommercialMedicament(name, medicamentId)
	VALUES ("Fenitoina", 32);
INSERT INTO CommercialMedicament(name, medicamentId)
	VALUES ("Eliquis", 33);
--INSERT INTO CommercialMedicament(name, medicamentId)
--	VALUES ("HeparinasBPM, 34");
--INSERT INTO CommercialMedicament(name, medicamentId)
--	VALUES ("Antiagregantes", 35);



-- INSERT INTO ChemicalComponent(componentName, familyId)
--	VALUES ();
INSERT INTO ChemicalComponent(componentName, familyId)
	VALUES ("Tietilperazina", 1); --1 
INSERT INTO ChemicalComponent(componentName, familyId)
	VALUES ("Finasteride", 2); --2
INSERT INTO ChemicalComponent(componentName, familyId)
	VALUES ("Dutasteride", 2); --3
INSERT INTO ChemicalComponent(componentName, familyId)
	VALUES ("Tamsulosina", 2); --4
INSERT INTO ChemicalComponent(componentName, familyId)
	VALUES ("Cabergolina", 3); --5
INSERT INTO ChemicalComponent(componentName, familyId)
	VALUES ("Fluconazol", 4); --6
INSERT INTO ChemicalComponent(componentName, familyId)
	VALUES ("Sitaglipina", 5); --7
INSERT INTO ChemicalComponent(componentName, familyId)
	VALUES ("Voriconazol", 4); --8
INSERT INTO ChemicalComponent(componentName, familyId)
	VALUES ("Acenocumarol", 6); --9
INSERT INTO ChemicalComponent(componentName, familyId)
	VALUES ("Diazepam", 7); --10
INSERT INTO ChemicalComponent(componentName, familyId)
	VALUES ("Ibuprofeno", 8); --11
INSERT INTO ChemicalComponent(componentName, familyId)
	VALUES ("Nitroglicerina", 10); --12
INSERT INTO ChemicalComponent(componentName, familyId)
	VALUES ("Digoxina", 9); --13
INSERT INTO ChemicalComponent(componentName, familyId)
	VALUES ("Quinina", 3); --14
INSERT INTO ChemicalComponent(componentName, familyId)
	VALUES ("Tetraciclina", 11); --15
INSERT INTO ChemicalComponent(componentName, familyId)
	VALUES ("Apixaban", 6); --16
INSERT INTO ChemicalComponent(componentName, familyId)
	VALUES ("Diclofenaco sódico", 8); --17
INSERT INTO ChemicalComponent(componentName, familyId)
	VALUES ("Indometacina sódico", 8); --18
INSERT INTO ChemicalComponent(componentName, familyId)
	VALUES ("Diclofenacoketropofeno", 8); --19
INSERT INTO ChemicalComponent(componentName, familyId)
	VALUES ("Ceftriaxona", 11); --20
INSERT INTO ChemicalComponent(componentName, familyId)
	VALUES ("Penicilina", 12); --21
INSERT INTO ChemicalComponent(componentName, familyId)
	VALUES ("Cefditoreno", 12); --22
INSERT INTO ChemicalComponent(componentName, familyId)
	VALUES ("Solifenacina", 16); --23
INSERT INTO ChemicalComponent(componentName, familyId)
	VALUES ("Fesoteracina", 16); --24
INSERT INTO ChemicalComponent(componentName, familyId)
	VALUES ("Mirabegrom", 13); --25
INSERT INTO ChemicalComponent(componentName, familyId)
	VALUES ("Omeprazol", 14); --26
INSERT INTO ChemicalComponent(componentName, familyId)
	VALUES ("Clopridogel", 6); --27
INSERT INTO ChemicalComponent(componentName, familyId)
	VALUES ("VIT B12", 15); --28
INSERT INTO ChemicalComponent(componentName, familyId)
	VALUES ("Ácido acetilsalicílico", 17); --29
INSERT INTO ChemicalComponent(componentName, familyId)
	VALUES ("Fenitoína sódica", 18); --30		
		
-- INSERT INTO MedicamentChemicalComponent(medicamentId, componentId)
--	VALUES ();
INSERT INTO MedicamentChemicalComponent(medicamentId, componentId)
	VALUES (1, 1);
INSERT INTO MedicamentChemicalComponent(medicamentId, componentId)
	VALUES (2, 2);
INSERT INTO MedicamentChemicalComponent(medicamentId, componentId)
	VALUES (3, 2);
INSERT INTO MedicamentChemicalComponent(medicamentId, componentId)
	VALUES (4, 3);
INSERT INTO MedicamentChemicalComponent(medicamentId, componentId)
	VALUES (5, 3);
INSERT INTO MedicamentChemicalComponent(medicamentId, componentId)
	VALUES (5, 4);
INSERT INTO MedicamentChemicalComponent(medicamentId, componentId)
	VALUES (6, 5);
INSERT INTO MedicamentChemicalComponent(medicamentId, componentId)
	VALUES (7, 6);
INSERT INTO MedicamentChemicalComponent(medicamentId, componentId)
	VALUES (8, 7);
INSERT INTO MedicamentChemicalComponent(medicamentId, componentId)
	VALUES (9, 8);
INSERT INTO MedicamentChemicalComponent(medicamentId, componentId)
	VALUES (10, 9);
INSERT INTO MedicamentChemicalComponent(medicamentId, componentId)
	VALUES (11, 10);
INSERT INTO MedicamentChemicalComponent(medicamentId, componentId)
	VALUES (12, 11);
INSERT INTO MedicamentChemicalComponent(medicamentId, componentId)
	VALUES (13, 12);
INSERT INTO MedicamentChemicalComponent(medicamentId, componentId)
	VALUES (14, 13);
INSERT INTO MedicamentChemicalComponent(medicamentId, componentId)
	VALUES (15, 14);
INSERT INTO MedicamentChemicalComponent(medicamentId, componentId)
	VALUES (16, 15);
INSERT INTO MedicamentChemicalComponent(medicamentId, componentId)
	VALUES (17, 17);
INSERT INTO MedicamentChemicalComponent(medicamentId, componentId)
	VALUES (18, 11);
INSERT INTO MedicamentChemicalComponent(medicamentId, componentId)
	VALUES (19, 18);
INSERT INTO MedicamentChemicalComponent(medicamentId, componentId)
	VALUES (20, 19);		
INSERT INTO MedicamentChemicalComponent(medicamentId, componentId)
	VALUES (23, 20);
INSERT INTO MedicamentChemicalComponent(medicamentId, componentId)
	VALUES (21, 21);
INSERT INTO MedicamentChemicalComponent(medicamentId, componentId)
	VALUES (24, 22);
INSERT INTO MedicamentChemicalComponent(medicamentId, componentId)
	VALUES (25, 23);
INSERT INTO MedicamentChemicalComponent(medicamentId, componentId)
	VALUES (26, 24);
INSERT INTO MedicamentChemicalComponent(medicamentId, componentId)
	VALUES (27, 25);
INSERT INTO MedicamentChemicalComponent(medicamentId, componentId)
	VALUES (28, 26);
INSERT INTO MedicamentChemicalComponent(medicamentId, componentId)
	VALUES (29, 27);		
INSERT INTO MedicamentChemicalComponent(medicamentId, componentId)
	VALUES (30, 28);
INSERT INTO MedicamentChemicalComponent(medicamentId, componentId)
	VALUES (31, 29);
INSERT INTO MedicamentChemicalComponent(medicamentId, componentId)
	VALUES (32, 30);
INSERT INTO MedicamentChemicalComponent(medicamentId, componentId)
	VALUES (33, 16);
		
-- INSERT INTO Disease(diseaseName)
--	VALUES ();
INSERT INTO Disease(diseaseName)
	VALUES ("Sindrome de Reye");--1
		
-- INSERT INTO Allergy(allergyName)
--	VALUES ();
INSERT INTO Allergy(allergyName)
	VALUES ("Penicilina");--1

-- INSERT INTO Intolerance(intoleranceName)
--	VALUES ();
INSERT INTO Intolerance(intoleranceName)
	VALUES ("Gastritis");--1

-- INSERT INTO RegularRestriction(code, restrictionName)
--	VALUES ();
INSERT INTO RegularRestriction(code, restrictionName)
	VALUES ("NUN","NO USO EN NIÑOS"); --1
INSERT INTO RegularRestriction(code, restrictionName)
	VALUES ("NUM","NO USO EN MUJERES"); --2
INSERT INTO RegularRestriction(code, restrictionName)
	VALUES ("NUH","NO USO EN HOMBRES"); --3
INSERT INTO RegularRestriction(code, restrictionName)
	VALUES ("ADIRL","AJUSTE DE DOSIS INCONTINENCIA RENAL LEVE"); --4
INSERT INTO RegularRestriction(code, restrictionName)
	VALUES ("ADIRG","AJUSTE DE DOSIS INCONTINENCIA RENAL GRAVE"); --5
INSERT INTO RegularRestriction(code, restrictionName)
	VALUES ("ADIH","AJUSTE DE DOSIS INSUFICIENCIA HEPÁTICA"); --6
INSERT INTO RegularRestriction(code, restrictionName)
	VALUES ("NUE","NO USO EMBARAZO"); --7
INSERT INTO RegularRestriction(code, restrictionName)
	VALUES ("NUL","NO USO EN LACTANCIA"); --8
		
-- INSERT INTO DiseaseRestriction(code, restrictionName, diseaseId)
--	VALUES ();
INSERT INTO DiseaseRestriction(code, restrictionName, diseaseId)
	VALUES ("Sindrome de Reye","Sindrome de Reye",1);

-- INSERT INTO AllergyRestriction(code, restrictionName, allergyId)
--	VALUES ();
INSERT INTO AllergyRestriction(code, restrictionName, allergyId)
	VALUES ("Penicilina", "Penicilina", 1);
		
-- INSERT INTO IntoleranceRestriction(code, restrictionName, intoleranceId)
--	VALUES ();
INSERT INTO IntoleranceRestriction(code, restrictionName, intoleranceId)
	VALUES ("Gastritis","Gastritis", 1);

-- INSERT INTO ComponentRestriction(code, restrictionName)
--	VALUES ();
INSERT INTO ComponentRestriction(code, restrictionName)
	VALUES ("ADIF1","Ajuste de dósis por interacción farmacológica 1");
INSERT INTO ComponentRestriction(code, restrictionName)
	VALUES ("ADIF2", "Ajuste de dósis por interacción farmacológica 2");
INSERT INTO ComponentRestriction(code, restrictionName)
	VALUES ("ADIF3", "Ajuste de dósis por interacción farmacológica 3");
INSERT INTO ComponentRestriction(code, restrictionName)
	VALUES ("ADIF4", "Ajuste de dósis por interacción farmacológica 4");
INSERT INTO ComponentRestriction(code, restrictionName)
	VALUES ("ADIF5","Ajuste de dósis por interacción farmacológica 5");
		
-- INSERT INTO ChemicalComponentComponentRestriction(componentId, componentRestrictitonId)
--	VALUES ();

-- INSERT INTO ChemicalComponentRegularRestriction(componentId, regularRestrictionId)
--	VALUES ();

-- INSERT INTO ChemicalComponentDiseaseRestriction(componentId, diseaseRestrictionId)
--	VALUES ();

-- INSERT INTO ChemicalComponentAllergyRestriction(componentId, allergyRestrictionId)
--	VALUES ();

-- INSERT INTO ChemicalComponentIntoleranceRestriction(componentId, intoleranceRestrictionId)
--	VALUES ();

