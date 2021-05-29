package es.udc.tfgproject.backend.model.services;

import java.util.ArrayList;
import java.util.List;

import es.udc.tfgproject.backend.model.entities.disease_intolerance_allergy.Allergy;
import es.udc.tfgproject.backend.model.entities.disease_intolerance_allergy.Disease;
import es.udc.tfgproject.backend.model.entities.disease_intolerance_allergy.Intolerance;
import es.udc.tfgproject.backend.model.entities.medicamentInformation.ChemicalComponent;
import es.udc.tfgproject.backend.model.entities.medicamentInformation.CommercialMedicament;
import es.udc.tfgproject.backend.model.entities.medicamentInformation.Family;
import es.udc.tfgproject.backend.model.entities.medicamentInformation.Medicament;
import es.udc.tfgproject.backend.model.entities.restrictions.RegularRestriction;
import es.udc.tfgproject.backend.rest.dtos.AllergyDto;
import es.udc.tfgproject.backend.rest.dtos.ChemicalComponentCompleteDto;
import es.udc.tfgproject.backend.rest.dtos.ChemicalComponentDto;
import es.udc.tfgproject.backend.rest.dtos.CommercialMedicamentDto;
import es.udc.tfgproject.backend.rest.dtos.DiseaseDto;
import es.udc.tfgproject.backend.rest.dtos.FamilyDto;
import es.udc.tfgproject.backend.rest.dtos.IntoleranceDto;
import es.udc.tfgproject.backend.rest.dtos.MedicamentExtendedDto;

public interface ListService {

    // LISTAS
    List<Allergy> listAllAllergies();

    ArrayList<AllergyDto> listAllAllergiesDto();

    List<Intolerance> listAllIntolerances();

    ArrayList<IntoleranceDto> listAllIntolerancesDto();

    List<Disease> listAllDiseases();

    ArrayList<DiseaseDto> listAllDiseasesDto();

    List<CommercialMedicament> listAllCommercialMedicaments();

    ArrayList<CommercialMedicamentDto> listAllCommercialMedicamentDto();

    List<Medicament> listAllMedicaments();

    ArrayList<MedicamentExtendedDto> listAllMedicamentsExtendedDto();

    List<ChemicalComponent> listAllChemicalComponents();

    ArrayList<ChemicalComponentDto> listAllChemicalComponentsDto();

    ArrayList<ChemicalComponentDto> listAllChemicalComponentsDtoExceptComponent(String code);

    ArrayList<ChemicalComponentCompleteDto> listAllChemicalComponentsCompleteDto();

    List<Family> listAllFamilies();

    ArrayList<FamilyDto> listAllFamiliesDto();

    List<RegularRestriction> listAllRegularRestrictions();

    // ALERGIAS
    Allergy saveAllergy(Allergy allergy);

    void deleteAllergy(Allergy allergy);

    void deleteAllergyByCode(ArrayList<AllergyDto> allergiesList, String code);

    AllergyDto getAllergyByCode(String code);

    Allergy getAllergy(String allergyName);

    Boolean checkAndSaveAllergy(String oldAllergyName, String allergyName);

    // ENFERMEDADES
    Disease saveDisease(Disease disease);

    void deleteDisease(Disease disease);

    void deleteDiseaseByCode(ArrayList<DiseaseDto> diseasesList, String code);

    DiseaseDto getDiseaseByCode(String code);

    Disease getDisease(String diseaseName);

    Boolean checkAndSaveDisease(String oldDiseaseName, String diseaseName);

    // INTOLERANCIAS
    Intolerance saveIntolerance(Intolerance intolerance);

    void deleteIntolerance(Intolerance intolerance);

    void deleteIntoleranceByCode(ArrayList<IntoleranceDto> intolerancesList, String code);

    IntoleranceDto getIntoleranceByCode(String code);

    Intolerance getIntolerance(String intoleranceName);

    Boolean checkAndSaveIntolerance(String oldIntoleranceName, String intoleranceName);

    // FAMILIAS
    Family saveFamily(Family family);

    void deleteFamily(Family family);

    void deleteFamilyByCode(ArrayList<FamilyDto> familyList, String code);

    FamilyDto getFamilyByCode(String code);

    Family getFamily(String familyName);

    Boolean checkAndSaveFamily(String oldFamilyName, String familyName);

    // COMPONENTES QUIMICOS
    ChemicalComponent saveChemicalComponent(ChemicalComponent chemicalComponent);

    void deleteChemicalComponentByCode(ArrayList<ChemicalComponentCompleteDto> chemicalComponentList, String code);

    void deleteChemicalComponent(ChemicalComponent chemicalComponent);

    ChemicalComponent getChemicalComponent(String componentName);

    ChemicalComponentCompleteDto getChemicalComponentByCode(String code);

    Boolean checkAndSaveChemicalComponent(String oldComponentName, String componentName, String family,
	    String[] componentsL, String[] intolerancesL, String[] allergiesL, String[] diseasesL,
	    String[] rRestrictionsL);

    // MEDICAMENTOS COMERCIALES
    CommercialMedicament saveCommercialMedicament(CommercialMedicament commercialMedicament);

    void deleteCommercialMedicament(CommercialMedicament commercialMedicament);

    void deleteCommercialMedicamentByCode(ArrayList<CommercialMedicamentDto> medicamentList, String code);

    CommercialMedicamentDto getCommercialMedicamentByCode(String code);

    CommercialMedicament getCommercialMedicament(String name);

    Boolean checkAndSaveCommercialMedicament(String oldCommercialMedicamentName, String commercialMedicamentName,
	    String medicamentName);

    // MEDICAMENTOS
    void saveMedicament(Medicament medicament);

    void deleteMedicament(Medicament medicament);

    Medicament getMedicament(String name);

    void deleteMedicamentByCode(ArrayList<MedicamentExtendedDto> medicamentList, String code);

    MedicamentExtendedDto getMedicamentByCode(String code);

    Boolean checkAndSaveMedicament(String oldMedicamentName, String medicamentName, String[] componentsL);

}
