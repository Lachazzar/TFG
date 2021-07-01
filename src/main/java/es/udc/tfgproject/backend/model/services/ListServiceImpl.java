package es.udc.tfgproject.backend.model.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.udc.tfgproject.backend.model.entities.UserBD;
import es.udc.tfgproject.backend.model.entities.UserBD.RoleType;
import es.udc.tfgproject.backend.model.entities.UserDao;
import es.udc.tfgproject.backend.model.entities.disease_intolerance_allergy.Allergy;
import es.udc.tfgproject.backend.model.entities.disease_intolerance_allergy.AllergyDao;
import es.udc.tfgproject.backend.model.entities.disease_intolerance_allergy.Disease;
import es.udc.tfgproject.backend.model.entities.disease_intolerance_allergy.DiseaseDao;
import es.udc.tfgproject.backend.model.entities.disease_intolerance_allergy.Intolerance;
import es.udc.tfgproject.backend.model.entities.disease_intolerance_allergy.IntoleranceDao;
import es.udc.tfgproject.backend.model.entities.medicamentInformation.ChemicalComponent;
import es.udc.tfgproject.backend.model.entities.medicamentInformation.ChemicalComponentDao;
import es.udc.tfgproject.backend.model.entities.medicamentInformation.CommercialMedicament;
import es.udc.tfgproject.backend.model.entities.medicamentInformation.CommercialMedicamentDao;
import es.udc.tfgproject.backend.model.entities.medicamentInformation.Family;
import es.udc.tfgproject.backend.model.entities.medicamentInformation.FamilyDao;
import es.udc.tfgproject.backend.model.entities.medicamentInformation.Medicament;
import es.udc.tfgproject.backend.model.entities.medicamentInformation.MedicamentDao;
import es.udc.tfgproject.backend.model.entities.restrictions.RegularRestriction;
import es.udc.tfgproject.backend.model.entities.restrictions.RegularRestrictionDao;
import es.udc.tfgproject.backend.rest.dtos.AllergyDto;
import es.udc.tfgproject.backend.rest.dtos.ChemicalComponentCompleteDto;
import es.udc.tfgproject.backend.rest.dtos.ChemicalComponentDto;
import es.udc.tfgproject.backend.rest.dtos.ChemicalComponentSimpleDto;
import es.udc.tfgproject.backend.rest.dtos.CommercialMedicamentDto;
import es.udc.tfgproject.backend.rest.dtos.DiseaseDto;
import es.udc.tfgproject.backend.rest.dtos.FamilyDto;
import es.udc.tfgproject.backend.rest.dtos.IntoleranceDto;
import es.udc.tfgproject.backend.rest.dtos.MedicamentExtendedDto;
import es.udc.tfgproject.backend.rest.dtos.UserDto;

@Service
@Transactional(readOnly = true)
public class ListServiceImpl implements ListService {

    @Autowired
    private AllergyDao allergyDao;

    @Autowired
    private DiseaseDao diseaseDao;

    @Autowired
    private IntoleranceDao intoleranceDao;

    @Autowired
    private CommercialMedicamentDao commercialMedicamentDao;

    @Autowired
    private FamilyDao familyDao;

    @Autowired
    private ChemicalComponentDao chemicalComponentDao;

    @Autowired
    private MedicamentDao medicamentDao;

    @Autowired
    private RegularRestrictionDao regularRestrictionDao;

    @Autowired
    private UserDao userDao;

    // LISTAS

    @Override
    public List<Allergy> listAllAllergies() {
	List<Allergy> allergies = new ArrayList<Allergy>();
	allergyDao.findAll().forEach(a -> {
	    allergies.add(a);
	});
	return allergies;
    }

    @Override
    public ArrayList<AllergyDto> listAllAllergiesDto() {
	ArrayList<AllergyDto> allergiesList = new ArrayList<AllergyDto>();
	listAllAllergies().forEach(d -> {
	    AllergyDto allergy = new AllergyDto(d.getAllergyName());
	    allergiesList.add(allergy);
	});
	return allergiesList;
    }

    @Override
    public List<Intolerance> listAllIntolerances() {
	List<Intolerance> intolerances = new ArrayList<Intolerance>();
	intoleranceDao.findAll().forEach(i -> {
	    intolerances.add(i);
	});
	return intolerances;
    }

    @Override
    public ArrayList<IntoleranceDto> listAllIntolerancesDto() {
	ArrayList<IntoleranceDto> intolerancesList = new ArrayList<IntoleranceDto>();
	listAllIntolerances().forEach(d -> {
	    IntoleranceDto intolerance = new IntoleranceDto(d.getIntoleranceName());
	    intolerancesList.add(intolerance);
	});
	return intolerancesList;
    }

    @Override
    public List<Disease> listAllDiseases() {
	List<Disease> diseases = new ArrayList<Disease>();
	diseaseDao.findAll().forEach(d -> {
	    diseases.add(d);
	});
	return diseases;
    }

    @Override
    public ArrayList<DiseaseDto> listAllDiseasesDto() {
	ArrayList<DiseaseDto> diseasesList = new ArrayList<DiseaseDto>();
	listAllDiseases().forEach(d -> {
	    DiseaseDto disease = new DiseaseDto(d.getDiseaseName());
	    diseasesList.add(disease);
	});
	return diseasesList;
    }

    @Override
    public List<CommercialMedicament> listAllCommercialMedicaments() {
	List<CommercialMedicament> medicaments = new ArrayList<CommercialMedicament>();
	commercialMedicamentDao.findAll().forEach(m -> {
	    medicaments.add(m);
	});
	return medicaments;
    }

    @Override
    public ArrayList<CommercialMedicamentDto> listAllCommercialMedicamentDto() {
	ArrayList<CommercialMedicamentDto> medicamentList = new ArrayList<CommercialMedicamentDto>();
	listAllCommercialMedicaments().forEach(d -> {
	    CommercialMedicamentDto medicament = new CommercialMedicamentDto(d.getName());
	    medicament.setMedicamentName(d.getMedicament().getname());
	    medicamentList.add(medicament);
	});
	return medicamentList;
    }

    @Override
    public List<Medicament> listAllMedicaments() {
	List<Medicament> medicaments = new ArrayList<Medicament>();
	medicamentDao.findAll().forEach(m -> {
	    medicaments.add(m);
	});
	return medicaments;
    }

    @Override
    public ArrayList<MedicamentExtendedDto> listAllMedicamentsExtendedDto() {
	ArrayList<MedicamentExtendedDto> medicamentsList = new ArrayList<MedicamentExtendedDto>();
	listAllMedicaments().forEach(d -> {
	    MedicamentExtendedDto medicament = new MedicamentExtendedDto(d.getname());
	    medicamentsList.add(medicament);
	});
	return medicamentsList;
    }

    @Override
    public List<ChemicalComponent> listAllChemicalComponents() {
	List<ChemicalComponent> chemicalComponents = new ArrayList<ChemicalComponent>();
	chemicalComponentDao.findAll().forEach(d -> {
	    chemicalComponents.add(d);
	});
	return chemicalComponents;
    }

    @Override
    public ArrayList<ChemicalComponentDto> listAllChemicalComponentsDto() {
	ArrayList<ChemicalComponentDto> componentList = new ArrayList<ChemicalComponentDto>();
	listAllChemicalComponents().forEach(d -> {
	    ChemicalComponentDto component = new ChemicalComponentDto(d.getComponentName(), d.getFamily());
	    componentList.add(component);
	});
	return componentList;
    }

    @Override
    public ArrayList<ChemicalComponentDto> listAllChemicalComponentsDtoExceptComponent(String code) {
	ArrayList<ChemicalComponentDto> componentList = new ArrayList<ChemicalComponentDto>();
	listAllChemicalComponents().forEach(d -> {
	    ChemicalComponentDto component = new ChemicalComponentDto(d.getComponentName(), d.getFamily());
	    if (!component.getCode().equals(code)) {
		componentList.add(component);
	    }
	});
	return componentList;
    }

    @Override
    public ArrayList<ChemicalComponentCompleteDto> listAllChemicalComponentsCompleteDto() {
	ArrayList<ChemicalComponentCompleteDto> chemicalComponentList = new ArrayList<ChemicalComponentCompleteDto>();
	listAllChemicalComponents().forEach(d -> {
	    FamilyDto family = new FamilyDto(d.getFamily().getFamilyName());
	    ChemicalComponentCompleteDto chemicalComponent = new ChemicalComponentCompleteDto(d.getComponentName(),
		    family);
	    ArrayList<RegularRestriction> regularRestrictions = new ArrayList<RegularRestriction>();
	    ArrayList<DiseaseDto> diseases = new ArrayList<DiseaseDto>();
	    ArrayList<AllergyDto> allergies = new ArrayList<AllergyDto>();
	    ArrayList<IntoleranceDto> intolerances = new ArrayList<IntoleranceDto>();
	    ArrayList<ChemicalComponentSimpleDto> components = new ArrayList<ChemicalComponentSimpleDto>();

	    regularRestrictions.addAll(d.getRegularRestrictions());

	    d.getDiseases().forEach(dRestriction -> {
		diseases.add(new DiseaseDto(dRestriction.getDiseaseName()));
	    });

	    d.getAllergies().forEach(aRestriction -> {
		allergies.add(new AllergyDto(aRestriction.getAllergyName()));
	    });

	    d.getIntolerances().forEach(iRestriction -> {
		intolerances.add(new IntoleranceDto(iRestriction.getIntoleranceName()));
	    });

	    d.getChemicalComponents().forEach(ccRestriction -> {
		components.add(new ChemicalComponentSimpleDto(ccRestriction.getComponentName()));
	    });

	    chemicalComponent.setAllergies(allergies);
	    chemicalComponent.setDiseases(diseases);
	    chemicalComponent.setIntolerances(intolerances);
	    chemicalComponent.setChemicalComponents(components);
	    chemicalComponent.setRegularRestrictions(regularRestrictions);

	    chemicalComponentList.add(chemicalComponent);
	});
	return chemicalComponentList;
    }

    @Override
    public List<Family> listAllFamilies() {
	List<Family> families = new ArrayList<Family>();
	familyDao.findAll().forEach(a -> {
	    families.add(a);
	});
	return families;
    }

    @Override
    public ArrayList<FamilyDto> listAllFamiliesDto() {
	ArrayList<FamilyDto> familiesList = new ArrayList<FamilyDto>();
	listAllFamilies().forEach(d -> {
	    FamilyDto family = new FamilyDto(d.getFamilyName());
	    familiesList.add(family);
	});
	return familiesList;
    }

    @Override
    public List<RegularRestriction> listAllRegularRestrictions() {
	List<RegularRestriction> rRestrictions = new ArrayList<RegularRestriction>();
	regularRestrictionDao.findAll().forEach(a -> {
	    rRestrictions.add(a);
	});
	return rRestrictions;
    }

    @Override
    public List<UserBD> listAllUsers() {
	List<UserBD> users = new ArrayList<UserBD>();
	userDao.findAll().forEach(a -> {
	    users.add(a);
	});
	return users;
    }

    @Override
    public ArrayList<UserDto> listAllUsersDto() {
	ArrayList<UserDto> usersList = new ArrayList<UserDto>();
	listAllUsers().forEach(d -> {
	    UserDto user = new UserDto(d.getUserName(), d.getEmail(), d.getRole());
	    usersList.add(user);
	});
	return usersList;
    }

    // USERS

    @Override
    public UserBD saveUser(UserBD user) {
	return userDao.saveAndFlush(user);
    }

    @Override
    public void deleteUser(UserBD user) {
	userDao.deleteById(user.getId());
    }

    @Override
    public UserBD getUser(String userName) {
	return userDao.findByUserName(userName).get();
    }

    @Override
    public void deleteUserByUserName(ArrayList<UserDto> userList, String userName) {
	userList.forEach(d -> {
	    if (d.getUserName().contentEquals(userName)) {
		UserBD userRemove = getUser(d.getUserName());
		deleteUser(userRemove);
	    }
	});

    }

    @Override
    public UserDto getUserDtoByUserName(String userName) {
	ArrayList<UserDto> usersList = listAllUsersDto();
	UserDto userFind = new UserDto();
	usersList.forEach(d -> {
	    if (d.getUserName().contentEquals(userName)) {
		userFind.setUserName(d.getUserName());
		userFind.setEmail(d.getEmail());
		userFind.setRole(d.getRole());
		userFind.setPassword(d.getPassword());
	    }
	});
	return userFind;
    }

    @Override
    public Boolean checkAndSaveUser(String oldUserName, String userName, String password, String email, RoleType role) {
	UserBD userSave;
	Boolean hasError = false;

	if (oldUserName == "" || oldUserName == null) {
	    for (UserBD d : listAllUsers()) {
		if (d.getUserName().equals(userName)) {
		    hasError = true;
		}
	    }
	    if (hasError == false) {
		userSave = new UserBD();
		userSave.setUserName(userName);
		userSave.setEmail(email);
		String encPassword = "{bcrypt}" + encryptPassword(password);
		userSave.setPassword(encPassword);
		userSave.setRole(role);
		userSave = saveUser(userSave);
	    }
	} else {
	    if (!oldUserName.equals(userName)) {
		for (UserBD d : listAllUsers()) {
		    if (d.getUserName().equals(userName)) {
			hasError = true;
		    }
		}
		if (hasError == false) {
		    userSave = getUser(oldUserName);
		    userSave.setUserName(userName);
		    userSave.setEmail(email);
		    if (password != null && password != "") {
			String encPassword = "{bcrypt}" + encryptPassword(password);
			userSave.setPassword(encPassword);
		    }
		    userSave.setRole(role);
		    userSave = saveUser(userSave);
		}
	    } else {
		userSave = getUser(userName);
		userSave.setEmail(email);
		userSave.setRole(role);
		if (password != null && password != "") {
		    String encPassword = "{bcrypt}" + encryptPassword(password);
		    userSave.setPassword(encPassword);
		}
		userSave = saveUser(userSave);
	    }
	}
	return hasError;
    }

    // ALERGIAS

    @Override
    public Allergy saveAllergy(Allergy allergy) {
	return allergyDao.saveAndFlush(allergy);
    }

    @Override
    public void deleteAllergy(Allergy allergy) {
	allergyDao.deleteById(allergy.getId());
    }

    @Override
    public Allergy getAllergy(String allergyName) {
	return allergyDao.findByAllergyName(allergyName).get();
    }

    @Override
    public void deleteAllergyByCode(ArrayList<AllergyDto> allergiesList, String code) {
	allergiesList.forEach(d -> {
	    if (d.getCode().contentEquals(code)) {
		Allergy allergyRemove = getAllergy(d.getAllergyName());
		deleteAllergy(allergyRemove);
	    }
	});
    }

    @Override
    public AllergyDto getAllergyByCode(String code) {
	ArrayList<AllergyDto> allergiesList = listAllAllergiesDto();
	AllergyDto allergyFind = new AllergyDto();
	allergiesList.forEach(d -> {
	    if (d.getCode().contentEquals(code)) {
		allergyFind.setCode(code);
		allergyFind.setAllergyName(d.getAllergyName());
	    }
	});
	return allergyFind;
    }

    @Override
    public Boolean checkAndSaveAllergy(String oldAllergyName, String allergyName) {
	Allergy allergySave;
	Boolean hasError = false;

	if (oldAllergyName == "" || oldAllergyName == null) {
	    for (Allergy d : listAllAllergies()) {
		if (d.getAllergyName().equals(allergyName)) {
		    hasError = true;
		}
	    }
	    if (hasError == false) {
		allergySave = new Allergy();
		allergySave.setAllergyName(allergyName);
		allergySave = saveAllergy(allergySave);
	    }
	} else {
	    if (!oldAllergyName.equals(allergyName)) {
		for (Allergy d : listAllAllergies()) {
		    if (d.getAllergyName().equals(allergyName)) {
			hasError = true;
		    }
		}
		if (hasError == false) {
		    allergySave = getAllergy(oldAllergyName);
		    allergySave.setAllergyName(allergyName);
		    saveAllergy(allergySave);
		}
	    } else {
		allergySave = getAllergy(allergyName);
		saveAllergy(allergySave);
	    }
	}
	return hasError;
    }

    // ENFERMEDADES

    @Override
    public Disease saveDisease(Disease disease) {
	return diseaseDao.saveAndFlush(disease);
    }

    @Override
    public void deleteDisease(Disease disease) {
	diseaseDao.deleteById(disease.getId());
    }

    @Override
    public void deleteDiseaseByCode(ArrayList<DiseaseDto> diseasesList, String code) {
	diseasesList.forEach(d -> {
	    if (d.getCode().contentEquals(code)) {
		Disease diseaseRemove = getDisease(d.getDiseaseName());
		deleteDisease(diseaseRemove);
	    }
	});

    }

    @Override
    public Disease getDisease(String diseaseName) {
	return diseaseDao.findByDiseaseName(diseaseName).get();
    }

    @Override
    public DiseaseDto getDiseaseByCode(String code) {
	ArrayList<DiseaseDto> diseasesList = listAllDiseasesDto();
	DiseaseDto diseaseFind = new DiseaseDto();
	diseasesList.forEach(d -> {
	    if (d.getCode().contentEquals(code)) {
		diseaseFind.setCode(code);
		diseaseFind.setDiseaseName(d.getDiseaseName());
	    }
	});
	return diseaseFind;
    }

    @Override
    public Boolean checkAndSaveDisease(String oldDiseaseName, String diseaseName) {
	Disease diseaseSave;
	Boolean hasError = false;

	if (oldDiseaseName == "" || oldDiseaseName == null) {
	    for (Disease d : listAllDiseases()) {
		if (d.getDiseaseName().equals(diseaseName)) {
		    hasError = true;
		}
	    }
	    if (hasError == false) {
		diseaseSave = new Disease();
		diseaseSave.setDiseaseName(diseaseName);
		saveDisease(diseaseSave);
	    }
	} else {
	    if (!oldDiseaseName.equals(diseaseName)) {
		for (Disease d : listAllDiseases()) {
		    if (d.getDiseaseName().equals(diseaseName)) {
			hasError = true;
		    }
		}
		if (hasError == false) {
		    diseaseSave = getDisease(oldDiseaseName);
		    diseaseSave.setDiseaseName(diseaseName);
		    saveDisease(diseaseSave);
		}
	    } else {
		diseaseSave = getDisease(diseaseName);
		saveDisease(diseaseSave);
	    }
	}
	return hasError;
    }

    // INTOLERANCIAS

    @Override
    public Intolerance saveIntolerance(Intolerance intolerance) {
	return intoleranceDao.saveAndFlush(intolerance);

    }

    @Override
    public void deleteIntolerance(Intolerance intolerance) {
	intoleranceDao.deleteById(intolerance.getId());
    }

    @Override
    public Intolerance getIntolerance(String intoleranceName) {
	return intoleranceDao.findByIntoleranceName(intoleranceName).get();
    }

    @Override
    public void deleteIntoleranceByCode(ArrayList<IntoleranceDto> intolerancesList, String code) {
	intolerancesList.forEach(d -> {
	    if (d.getCode().contentEquals(code)) {
		Intolerance intoleranceRemove = getIntolerance(d.getIntoleranceName());
		deleteIntolerance(intoleranceRemove);
	    }
	});

    }

    @Override
    public IntoleranceDto getIntoleranceByCode(String code) {
	ArrayList<IntoleranceDto> intolerancesList = listAllIntolerancesDto();
	IntoleranceDto intoleranceFind = new IntoleranceDto();
	intolerancesList.forEach(d -> {
	    if (d.getCode().contentEquals(code)) {
		intoleranceFind.setCode(code);
		intoleranceFind.setIntoleranceName(d.getIntoleranceName());
	    }
	});
	return intoleranceFind;
    }

    @Override
    public Boolean checkAndSaveIntolerance(String oldIntoleranceName, String intoleranceName) {
	Intolerance intoleranceSave;
	Boolean hasError = false;

	if (oldIntoleranceName == "" || oldIntoleranceName == null) {
	    for (Intolerance d : listAllIntolerances()) {
		if (d.getIntoleranceName().equals(intoleranceName)) {
		    hasError = true;
		}
	    }
	    if (hasError == false) {
		intoleranceSave = new Intolerance();
		intoleranceSave.setIntoleranceName(intoleranceName);
		saveIntolerance(intoleranceSave);
	    }
	} else {
	    if (!oldIntoleranceName.equals(intoleranceName)) {
		for (Intolerance d : listAllIntolerances()) {
		    if (d.getIntoleranceName().equals(intoleranceName)) {
			hasError = true;
		    }
		}
		if (hasError == false) {
		    intoleranceSave = getIntolerance(oldIntoleranceName);
		    intoleranceSave.setIntoleranceName(intoleranceName);
		    saveIntolerance(intoleranceSave);
		}
	    } else {
		intoleranceSave = getIntolerance(oldIntoleranceName);
		saveIntolerance(intoleranceSave);
	    }
	}
	return hasError;
    }

    // FAMILIAS

    @Override
    public Family saveFamily(Family family) {
	return familyDao.saveAndFlush(family);
    }

    @Override
    public void deleteFamily(Family family) {
	familyDao.deleteById(family.getId());

    }

    @Override
    public Family getFamily(String familyName) {
	return familyDao.findByFamilyName(familyName).get();
    }

    @Override
    public void deleteFamilyByCode(ArrayList<FamilyDto> familyList, String code) {
	familyList.forEach(d -> {
	    if (d.getCode().contentEquals(code)) {
		Family familyRemove = getFamily(d.getFamilyName());
		deleteFamily(familyRemove);
	    }
	});
    }

    @Override
    public FamilyDto getFamilyByCode(String code) {
	ArrayList<FamilyDto> familiesList = listAllFamiliesDto();
	FamilyDto familyFind = new FamilyDto();
	familiesList.forEach(d -> {
	    if (d.getCode().contentEquals(code)) {
		familyFind.setCode(code);
		familyFind.setFamilyName(d.getFamilyName());
	    }
	});
	return familyFind;
    }

    @Override
    public Boolean checkAndSaveFamily(String oldFamilyName, String familyName) {
	Family familySave;
	Boolean hasError = false;

	if (oldFamilyName == "" || oldFamilyName == null) {
	    for (Family d : listAllFamilies()) {
		if (d.getFamilyName().equals(familyName)) {
		    hasError = true;
		}
	    }
	    if (hasError == false) {
		familySave = new Family();
		familySave.setFamilyName(familyName);
		saveFamily(familySave);
	    }
	} else {
	    if (!oldFamilyName.equals(familyName)) {
		for (Family d : listAllFamilies()) {
		    if (d.getFamilyName().equals(familyName)) {
			hasError = true;
		    }
		}
		if (hasError == false) {
		    familySave = getFamily(oldFamilyName);
		    familySave.setFamilyName(familyName);
		    saveFamily(familySave);
		}
	    } else {
		familySave = getFamily(oldFamilyName);
		saveFamily(familySave);
	    }
	}
	return hasError;
    }

    // COMPONENTES QUIMICOS

    @Override
    public ChemicalComponent saveChemicalComponent(ChemicalComponent chemicalComponent) {
	return chemicalComponentDao.saveAndFlush(chemicalComponent);
    }

    @Override
    public void deleteChemicalComponent(ChemicalComponent chemicalComponent) {
	chemicalComponentDao.deleteById(chemicalComponent.getId());

    }

    @Override
    public ChemicalComponent getChemicalComponent(String componentName) {
	return chemicalComponentDao.findByComponentName(componentName).get();
    }

    @Override
    public void deleteChemicalComponentByCode(ArrayList<ChemicalComponentCompleteDto> chemicalComponentList,
	    String code) {
	chemicalComponentList.forEach(d -> {
	    if (d.getCode().contentEquals(code)) {
		ChemicalComponent componentRemove = getChemicalComponent(d.getComponentName());
		deleteChemicalComponent(componentRemove);
	    }
	});

    }

    @Override
    public ChemicalComponentCompleteDto getChemicalComponentByCode(String code) {
	ArrayList<ChemicalComponentCompleteDto> chemicalComponentList = listAllChemicalComponentsCompleteDto();
	ChemicalComponentCompleteDto componentFind = new ChemicalComponentCompleteDto();
	chemicalComponentList.forEach(d -> {
	    if (d.getCode().contentEquals(code)) {
		componentFind.setCode(code);
		componentFind.setComponentName(d.getComponentName());
		componentFind.setFamily(d.getFamily());
		componentFind.setAllergies(d.getAllergies());
		componentFind.setRegularRestrictions(d.getRegularRestrictions());
		componentFind.setDiseases(d.getDiseases());
		componentFind.setIntolerances(d.getIntolerances());
		componentFind.setChemicalComponents(d.getChemicalComponents());
	    }
	});
	return componentFind;
    }

    @Override
    public Boolean checkAndSaveChemicalComponent(String oldComponentName, String componentName, String family,
	    String[] componentsL, String[] intolerancesL, String[] allergiesL, String[] diseasesL,
	    String[] rRestrictionsL) {
	ChemicalComponent componentSave;
	Boolean hasError = false;

	if (oldComponentName == "" || oldComponentName == null) {
	    for (ChemicalComponent d : listAllChemicalComponents()) {
		if (d.getComponentName().equals(componentName)) {
		    hasError = true;
		}
	    }
	    if (hasError == false) {
		componentSave = new ChemicalComponent();
		componentSave.setComponentName(componentName);
		componentSave.setFamily(getFamily(family));
		componentSave = chemicalComponentDao.saveAndFlush(componentSave);
		componentSave.setRegularRestrictions(rRestrictionArrayToSet(rRestrictionsL));
		componentSave.setIntolerances(intolerancesArrayToSet(intolerancesL));
		componentSave.setAllergies(allergiesArrayToSet(allergiesL));
		componentSave.setDiseases(diseasesArrayToSet(diseasesL));
		componentSave.setChemicalComponents(chemicalComponentsArrayToSet(componentsL));

		ChemicalComponent ccAdd = saveChemicalComponent(componentSave);

		for (ChemicalComponent cc : componentSave.getChemicalComponents()) {
		    Set<ChemicalComponent> ccSet = cc.getChemicalComponents();
		    ccSet.add(ccAdd);
		    cc.setChemicalComponents(ccSet);
		    saveChemicalComponent(cc);
		}
	    }
	} else {
	    if (!oldComponentName.equals(componentName)) {
		for (ChemicalComponent d : listAllChemicalComponents()) {
		    if (d.getComponentName().equals(componentName)) {
			hasError = true;
		    }
		}
		if (hasError == false) {
		    componentSave = getChemicalComponent(oldComponentName);
		    componentSave.setComponentName(componentName);
		    componentSave.setFamily(getFamily(family));
		    componentSave.setRegularRestrictions(rRestrictionArrayToSet(rRestrictionsL));
		    componentSave.setIntolerances(intolerancesArrayToSet(intolerancesL));
		    componentSave.setAllergies(allergiesArrayToSet(allergiesL));
		    componentSave.setDiseases(diseasesArrayToSet(diseasesL));
		    componentSave.setChemicalComponents(chemicalComponentsArrayToSet(componentsL));

		    ChemicalComponent ccAdd = saveChemicalComponent(componentSave);

		    for (ChemicalComponent cc : componentSave.getChemicalComponents()) {
			Set<ChemicalComponent> ccSet = cc.getChemicalComponents();
			ccSet.add(ccAdd);
			cc.setChemicalComponents(ccSet);
			saveChemicalComponent(cc);
		    }
		}
	    } else {
		componentSave = getChemicalComponent(oldComponentName);
		componentSave.setComponentName(componentName);
		componentSave.setFamily(getFamily(family));
		componentSave.setRegularRestrictions(rRestrictionArrayToSet(rRestrictionsL));
		componentSave.setIntolerances(intolerancesArrayToSet(intolerancesL));
		componentSave.setAllergies(allergiesArrayToSet(allergiesL));
		componentSave.setDiseases(diseasesArrayToSet(diseasesL));
		componentSave.setChemicalComponents(chemicalComponentsArrayToSet(componentsL));

		saveChemicalComponent(componentSave);

		for (ChemicalComponent cc : componentSave.getChemicalComponents()) {
		    Set<ChemicalComponent> ccSet = cc.getChemicalComponents();
		    ccSet.add(componentSave);
		    cc.setChemicalComponents(ccSet);
		    saveChemicalComponent(cc);
		}
	    }
	}
	return hasError;
    }

    // MEDICAMENTOS COMERCIALES

    @Override
    public CommercialMedicament saveCommercialMedicament(CommercialMedicament commercialMedicament) {
	return commercialMedicamentDao.saveAndFlush(commercialMedicament);

    }

    @Override
    public void deleteCommercialMedicament(CommercialMedicament commercialMedicament) {
	commercialMedicamentDao.deleteById(commercialMedicament.getId());

    }

    @Override
    public CommercialMedicament getCommercialMedicament(String name) {
	return commercialMedicamentDao.findByName(name).get();
    }

    @Override
    public void deleteCommercialMedicamentByCode(ArrayList<CommercialMedicamentDto> medicamentList, String code) {
	medicamentList.forEach(d -> {
	    if (d.getCode().contentEquals(code)) {
		CommercialMedicament comMedicamentRemove = getCommercialMedicament(d.getCommercialMedicamentName());
		deleteCommercialMedicament(comMedicamentRemove);
	    }
	});
    }

    @Override
    public CommercialMedicamentDto getCommercialMedicamentByCode(String code) {
	ArrayList<CommercialMedicamentDto> comMedicamentList = listAllCommercialMedicamentDto();
	CommercialMedicamentDto comMedicamentFind = new CommercialMedicamentDto();
	comMedicamentList.forEach(d -> {
	    if (d.getCode().contentEquals(code)) {
		comMedicamentFind.setCode(code);
		comMedicamentFind.setCommercialMedicamentName(d.getCommercialMedicamentName());
		comMedicamentFind.setMedicamentName(d.getMedicamentName());
	    }
	});
	return comMedicamentFind;
    }

    @Override
    public Boolean checkAndSaveCommercialMedicament(String oldCommercialMedicamentName, String commercialMedicamentName,
	    String medicamentName) {
	CommercialMedicament comMedicamentSave;
	Boolean hasError = false;

	if (oldCommercialMedicamentName == "" || oldCommercialMedicamentName == null) {
	    for (CommercialMedicament d : listAllCommercialMedicaments()) {
		if (d.getName().equals(commercialMedicamentName)) {
		    hasError = true;
		}
	    }
	    if (hasError == false) {
		comMedicamentSave = new CommercialMedicament();
		comMedicamentSave.setName(commercialMedicamentName);
		if (medicamentName != null && medicamentName != "") {
		    Medicament medicament = getMedicament(medicamentName);
		    comMedicamentSave.setMedicament(medicament);
		}
		saveCommercialMedicament(comMedicamentSave);
	    }
	} else {
	    if (!oldCommercialMedicamentName.equals(commercialMedicamentName)) {
		for (CommercialMedicament d : listAllCommercialMedicaments()) {
		    if (d.getName().equals(commercialMedicamentName)) {
			hasError = true;
		    }
		}
		if (hasError == false) {
		    comMedicamentSave = getCommercialMedicament(oldCommercialMedicamentName);
		    comMedicamentSave.setName(commercialMedicamentName);
		    if (medicamentName != null && medicamentName != "") {
			Medicament medicament = getMedicament(medicamentName);
			comMedicamentSave.setMedicament(medicament);
		    }
		    saveCommercialMedicament(comMedicamentSave);
		}
	    } else {
		comMedicamentSave = getCommercialMedicament(oldCommercialMedicamentName);
		comMedicamentSave.setName(commercialMedicamentName);
		if (medicamentName != null && medicamentName != "") {
		    Medicament medicament = getMedicament(medicamentName);
		    comMedicamentSave.setMedicament(medicament);
		}
		saveCommercialMedicament(comMedicamentSave);
	    }
	}
	return hasError;
    }

    // MEDICAMENTOS

    @Override
    public void saveMedicament(Medicament medicament) {
	medicamentDao.saveAndFlush(medicament);

    }

    @Override
    public void deleteMedicament(Medicament medicament) {
	medicamentDao.deleteById(medicament.getId());
    }

    @Override
    public Medicament getMedicament(String name) {
	return medicamentDao.findByName(name).get();
    }

    @Override
    public MedicamentExtendedDto getMedicamentByCode(String code) {
	ArrayList<MedicamentExtendedDto> medicamentsList = listAllMedicamentsExtendedDto();
	MedicamentExtendedDto medicamentFind = new MedicamentExtendedDto();
	medicamentsList.forEach(d -> {
	    if (d.getCode().contentEquals(code)) {
		medicamentFind.setCode(code);
		medicamentFind.setMedicamentName(d.getMedicamentName());
	    }
	});
	Medicament medicament = getMedicament(medicamentFind.getMedicamentName());

	ArrayList<ChemicalComponentSimpleDto> components = new ArrayList<ChemicalComponentSimpleDto>();
	medicament.getChemicalComponents().forEach(cc -> {
	    ChemicalComponentSimpleDto ccDto = new ChemicalComponentSimpleDto(cc.getComponentName());
	    components.add(ccDto);
	});

	medicamentFind.setComponents(components);

	return medicamentFind;
    }

    @Override
    public void deleteMedicamentByCode(ArrayList<MedicamentExtendedDto> medicamentList, String code) {
	medicamentList.forEach(d -> {
	    if (d.getCode().contentEquals(code)) {
		Medicament medicamentRemove = getMedicament(d.getMedicamentName());
		deleteMedicament(medicamentRemove);
	    }
	});
    }

    @Override
    public Boolean checkAndSaveMedicament(String oldMedicamentName, String medicamentName, String[] componentsL) {
	Medicament medicamentSave;
	Boolean hasError = false;

	if (oldMedicamentName == "" || medicamentName == null) {
	    for (Medicament d : listAllMedicaments()) {
		if (d.getname().equals(medicamentName)) {
		    hasError = true;
		}
	    }
	    if (hasError == false) {
		medicamentSave = new Medicament();
		medicamentSave.setname(medicamentName);
		medicamentSave.setChemicalComponents(chemicalComponentsArrayToSet(componentsL));
		saveMedicament(medicamentSave);
	    }
	} else {
	    if (!oldMedicamentName.equals(medicamentName)) {
		for (Medicament d : listAllMedicaments()) {
		    if (d.getname().equals(medicamentName)) {
			hasError = true;
		    }
		}
		if (hasError == false) {
		    medicamentSave = getMedicament(oldMedicamentName);
		    medicamentSave.setname(medicamentName);
		    medicamentSave.setChemicalComponents(chemicalComponentsArrayToSet(componentsL));
		    saveMedicament(medicamentSave);
		}
	    } else {
		medicamentSave = getMedicament(oldMedicamentName);
		medicamentSave.setChemicalComponents(chemicalComponentsArrayToSet(componentsL));
		saveMedicament(medicamentSave);
	    }
	}
	return hasError;
    }

    // METODOS PRIVADOS

    private Set<RegularRestriction> rRestrictionArrayToSet(String[] rRestrictions) {
	List<RegularRestriction> rRestrictionList = new ArrayList<RegularRestriction>();

	if (rRestrictions != null) {
	    for (int i = 0; i < rRestrictions.length; i++) {
		rRestrictionList.add(regularRestrictionDao.findByCode(rRestrictions[i]).get());
	    }
	}

	Set<RegularRestriction> rRestrictionsSet = new HashSet<>(rRestrictionList);
	return rRestrictionsSet;
    }

    private Set<Intolerance> intolerancesArrayToSet(String[] intolerances) {
	List<Intolerance> intolerancesList = new ArrayList<Intolerance>();
	if (intolerances != null) {
	    for (int i = 0; i < intolerances.length; i++) {
		intolerancesList.add(intoleranceDao.findByIntoleranceName(intolerances[i]).get());
	    }
	}

	Set<Intolerance> intolerancesSet = new HashSet<>(intolerancesList);
	return intolerancesSet;
    }

    private Set<Allergy> allergiesArrayToSet(String[] allergies) {
	List<Allergy> allergiesList = new ArrayList<Allergy>();
	if (allergies != null) {
	    for (int i = 0; i < allergies.length; i++) {
		allergiesList.add(allergyDao.findByAllergyName(allergies[i]).get());
	    }
	}

	Set<Allergy> allergiesSet = new HashSet<>(allergiesList);
	return allergiesSet;
    }

    private Set<Disease> diseasesArrayToSet(String[] diseases) {
	List<Disease> diseasesList = new ArrayList<Disease>();
	if (diseases != null) {
	    for (int i = 0; i < diseases.length; i++) {
		diseasesList.add(diseaseDao.findByDiseaseName(diseases[i]).get());
	    }
	}

	Set<Disease> diseasesSet = new HashSet<>(diseasesList);
	return diseasesSet;
    }

    private Set<ChemicalComponent> chemicalComponentsArrayToSet(String[] components) {
	List<ChemicalComponent> componentsList = new ArrayList<ChemicalComponent>();
	if (components != null) {
	    for (int i = 0; i < components.length; i++) {
		componentsList.add(getChemicalComponent(components[i]));
	    }
	}

	Set<ChemicalComponent> componentsSet = new HashSet<>(componentsList);
	return componentsSet;
    }

    public static String encryptPassword(String password) {
	BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	return encoder.encode(password);
    }

}
