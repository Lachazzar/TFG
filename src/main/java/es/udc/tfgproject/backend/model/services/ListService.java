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

    List<Medicament> listAllMedicaments();

    ArrayList<MedicamentExtendedDto> listAllMedicamentsExtendedDto();

    List<ChemicalComponent> listAllChemicalComponents();

    ArrayList<ChemicalComponentDto> listAllChemicalComponentsDto();

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
    void saveIntolerance(Intolerance intolerance);

    void deleteIntolerance(Intolerance intolerance);

    Intolerance getIntolerance(String intoleranceName);

    // FAMILIAS
    void saveFamily(Family family);

    void deleteFamily(Family family);

    Family getFamily(String familyName);

    // COMPONENTES QUIMICOS
    void saveChemicalComponent(ChemicalComponent chemicalComponent);

    void deleteChemicalComponentByCode(ArrayList<ChemicalComponentCompleteDto> chemicalComponentList, String code);

    void deleteChemicalComponent(ChemicalComponent chemicalComponent);

    ChemicalComponent getChemicalComponent(String componentName);

    ChemicalComponentCompleteDto getChemicalComponentByCode(String code);

    Boolean checkAndSaveChemicalComponent(String oldComponentName, String componentName, String family,
	    String[] componentsL, String[] intolerancesL, String[] allergiesL, String[] diseasesL,
	    String[] rRestrictionsL);

    // MEDICAMENTOS COMERCIALES
    void saveCommercialMedicament(CommercialMedicament commercialMedicament);

    void deleteCommercialMedicament(CommercialMedicament commercialMedicament);

    CommercialMedicament getCommercialMedicament(String name);

    // MEDICAMENTOS
    void saveMedicament(Medicament medicament);

    void deleteMedicament(Medicament medicament);

    Medicament getMedicament(String name);

    void deleteMedicamentByCode(ArrayList<MedicamentExtendedDto> medicamentList, String code);

    MedicamentExtendedDto getMedicamentByCode(String code);

    Boolean checkAndSaveMedicament(String oldMedicamentName, String medicamentName, String[] componentsL);

}
