package es.udc.tfgproject.backend.model.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.udc.tfgproject.backend.model.entities.Alert;
import es.udc.tfgproject.backend.model.entities.appSettings.Constants;
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
import es.udc.tfgproject.backend.model.entities.medicamentInformation.MedicamentDao;
import es.udc.tfgproject.backend.model.entities.patientInformation.MedicalHistory;
import es.udc.tfgproject.backend.model.entities.patientInformation.MedicalHistory.Sexo;
import es.udc.tfgproject.backend.model.entities.patientInformation.Treatment;
import es.udc.tfgproject.backend.model.entities.restrictions.AllergyRestriction;
import es.udc.tfgproject.backend.model.entities.restrictions.ComponentRestriction;
import es.udc.tfgproject.backend.model.entities.restrictions.DiseaseRestriction;
import es.udc.tfgproject.backend.model.entities.restrictions.IntoleranceRestriction;
import es.udc.tfgproject.backend.model.entities.restrictions.Restriction;
import es.udc.tfgproject.backend.model.exceptions.InstanceNotFoundException;

@Service
@Transactional
public class MedicalServiceImpl implements MedicalService {

    @Autowired
    private AllergyDao allergyDao;

    @Autowired
    private DiseaseDao diseaseDao;

    @Autowired
    private IntoleranceDao intoleranceDao;

    @Autowired
    private ChemicalComponentDao chemicalComponentDao;

    @Autowired
    private MedicamentDao medicamentDao;

    @Autowired
    private CommercialMedicamentDao commercialMedicamentDao;

    @Override
    public List<Alert> completeReport(MedicalHistory history, Treatment treatment, boolean comprobarRecetados)
	    throws InstanceNotFoundException {
	List<Alert> completeReport = new ArrayList<>();

	// Recuperamos los componentes químicos de los tratamientos del historial médico
	// y del nuevo tratamiento
	List<ChemicalComponent> actualChemicalComponents = actualChemicalComponentList(history);
	List<ChemicalComponent> newChemicalComponents = newChemicalComponentList(treatment);

	// Obtenemos las restricciones por familia según esos componentes químicos
	List<Alert> therapeuticGroupReport = therapeuticGroupFilter(actualChemicalComponents, newChemicalComponents,
		comprobarRecetados);

	completeReport.addAll(therapeuticGroupReport);

	// Obtenemos las restricciones genéricas para esos componentes químicos
	List<Alert> genericReport = genericRestrictionFilter(actualChemicalComponents, newChemicalComponents,
		comprobarRecetados, history);

	completeReport.addAll(genericReport);

	// Obtenemos las restricciones entre componentes para esos componentes químicos
	List<Alert> chemicalComponentsInteractionsReport = chemicalComponentsInteractionsRestrictionFilter(
		actualChemicalComponents, newChemicalComponents, comprobarRecetados);

	completeReport.addAll(chemicalComponentsInteractionsReport);

	// Obtenemos las restricciones con enfermedades, intelorenacias o alergias para
	// esos componentes químicos
	List<Alert> diseaseAllergyIntolerationReport = diseaseAllergyIntolerationRestrictionFilter(
		actualChemicalComponents, newChemicalComponents, comprobarRecetados, history);

	completeReport.addAll(diseaseAllergyIntolerationReport);

	return completeReport;
    }

    @Override
    public List<ChemicalComponent> actualChemicalComponentList(MedicalHistory history)
	    throws InstanceNotFoundException {
	List<ChemicalComponent> actualChemicalComponents = new ArrayList<ChemicalComponent>();
	Set<ChemicalComponent> ChemicalComponents;
	List<ChemicalComponent> ChemicalComponentsList;
	Set<Treatment> actualTreatments = history.getActualTreatments();
	CommercialMedicament comercialMedicament;
	for (Treatment t : actualTreatments) {
	    comercialMedicament = checkCommercialMedicamentByName(t.getCommercialMedicamentName());
	    ChemicalComponents = comercialMedicament.getMedicament().getChemicalComponents();
	    ChemicalComponentsList = new ArrayList<ChemicalComponent>(ChemicalComponents);
	    for (ChemicalComponent cc : ChemicalComponentsList) {
		if (!actualChemicalComponents.contains(cc)) {
		    actualChemicalComponents.add(cc);
		}
	    }
	}
	return actualChemicalComponents;
    }

    @Override
    public List<ChemicalComponent> newChemicalComponentList(Treatment treatment) throws InstanceNotFoundException {
	List<ChemicalComponent> newChemicalComponents = new ArrayList<ChemicalComponent>();
	CommercialMedicament comercialMedicament = checkCommercialMedicamentByName(
		treatment.getCommercialMedicamentName());
	Set<ChemicalComponent> ChemicalComponents = comercialMedicament.getMedicament().getChemicalComponents();
	List<ChemicalComponent> ChemicalComponentsList = new ArrayList<ChemicalComponent>(ChemicalComponents);
	for (ChemicalComponent cc : ChemicalComponentsList) {
	    if (!newChemicalComponents.contains(cc)) {
		newChemicalComponents.add(cc);
	    }
	}
	return newChemicalComponents;
    }

    private CommercialMedicament checkCommercialMedicamentByName(String name) throws InstanceNotFoundException {
	Optional<CommercialMedicament> commercialMedicament = commercialMedicamentDao.findByName(name);
	if (!commercialMedicament.isPresent()) {
	    throw new InstanceNotFoundException("project.entities.CommercialMedicament", name);
	}
	return commercialMedicament.get();
    }

    private List<Alert> therapeuticGroupFilter(List<ChemicalComponent> actualList, List<ChemicalComponent> newList,
	    boolean comprobarRecetados) {
	List<Alert> therapeuticGroupReport = new ArrayList<>();
	List<ChemicalComponent> actualListCopy = actualList;
	List<ChemicalComponent> newListCopy = newList;

	boolean aRecetar = false;
	String title = "Grupo terapeutico";
	// Comprobación entre componentes ya recetados
	if (comprobarRecetados) {
	    for (ChemicalComponent cc : actualListCopy) {
		actualListCopy.remove(cc);
		for (ChemicalComponent ccCompare : actualListCopy) {
		    if (cc.getFamily().getFamilyName().equals(ccCompare.getFamily().getFamilyName())) {
			String therapeuticGroupAlert = "Los grupos terapeuticos de los componentes (ya recetados): "
				+ cc.getComponentName() + " y " + ccCompare.getComponentName()
				+ " son de la misma familia: " + cc.getFamily().getFamilyName();
			Alert alert = new Alert(title, therapeuticGroupAlert, aRecetar);
			therapeuticGroupReport.add(alert);
		    }
		}
	    }
	}
	aRecetar = true;
	// Comprobación entre componentes a recetar
	for (ChemicalComponent cc : newListCopy) {
	    newListCopy.remove(cc);
	    for (ChemicalComponent ccCompare : newListCopy) {
		if (cc.getFamily().getFamilyName().equals(ccCompare.getFamily().getFamilyName())) {
		    String therapeuticGroupAlert = "Los grupos terapeuticos de los componentes (a recetar): "
			    + cc.getComponentName() + " y " + ccCompare.getComponentName()
			    + " son de la misma familia: " + cc.getFamily().getFamilyName();
		    Alert alert = new Alert(title, therapeuticGroupAlert, aRecetar);
		    therapeuticGroupReport.add(alert);
		}
	    }
	}
	// Comprobación cruzada
	for (ChemicalComponent cc : newList) {
	    for (ChemicalComponent ccCompare : actualList) {
		if (cc.getFamily().getFamilyName().equals(ccCompare.getFamily().getFamilyName())) {
		    String therapeuticGroupAlert = "Los grupos terapeuticos de los componentes (a recetar - recetado):"
			    + cc.getComponentName() + " y " + ccCompare.getComponentName()
			    + " son de la misma familia: " + cc.getFamily().getFamilyName();
		    Alert alert = new Alert(title, therapeuticGroupAlert, aRecetar);
		    therapeuticGroupReport.add(alert);
		}
	    }
	}

	return therapeuticGroupReport;
    }

    private List<Alert> genericRestrictionFilter(List<ChemicalComponent> actualList, List<ChemicalComponent> newList,
	    boolean comprobarRecetados, MedicalHistory history) {
	List<Alert> genericRestrictionReport = new ArrayList<>();
	boolean aRecetar = false;
	String title = "Restricciones genericas";
	String genericAlert = null;
	Alert alert = null;
	if (comprobarRecetados) {
	    for (ChemicalComponent cc : actualList) {
		if (cc.getRestrictions() != null) {
		    for (Restriction r : cc.getRestrictions()) {
			switch (r.getCode()) {
			    case "ADIRG":
				if (history.getGlomerularFiltration() < Constants.renalInsufficiencySevere) {
				    genericAlert = "El componente químico " + cc.getComponentName()
					    + " necesita un ajuste de dósis debido a que el nivel glomerular del paciente es menor a "
					    + Constants.renalInsufficiencySevere;
				    alert = new Alert(title, genericAlert, aRecetar);
				    genericRestrictionReport.add(alert);
				}
				break;
			    case "ADIRL":
				if (history.getGlomerularFiltration() < Constants.renalInsufficiencyMild) {
				    genericAlert = "El componente químico " + cc.getComponentName()
					    + " necesita un ajuste de dósis debido a que el nivel glomerular del paciente es menor a "
					    + Constants.renalInsufficiencyMild;
				    alert = new Alert(title, genericAlert, aRecetar);
				    genericRestrictionReport.add(alert);
				}
				break;
			    case "ADIH":
				if (history.isLiverFailure()) {
				    genericAlert = "El componente químico " + cc.getComponentName()
					    + " necesita un ajuste de dósis debido a la insuficiencia hepática";
				    alert = new Alert(title, genericAlert, aRecetar);
				    genericRestrictionReport.add(alert);
				}
				break;
			    case "ADKP":
				genericAlert = "El componente químico " + cc.getComponentName()
					+ " necesita un ajuste de dósis por Kg de peso";
				alert = new Alert(title, genericAlert, aRecetar);
				genericRestrictionReport.add(alert);
				break;
			    case "NUH":
				if (history.getSexo() == Sexo.MASCULINO) {
				    genericAlert = "El componente químico " + cc.getComponentName()
					    + " no debería recetarse en hombres";
				    alert = new Alert(title, genericAlert, aRecetar);
				    genericRestrictionReport.add(alert);
				}
				break;
			    case "NUM":
				if (history.getSexo() == Sexo.FEMENINO) {
				    genericAlert = "El componente químico " + cc.getComponentName()
					    + " no debería recetarse en mujeres";
				    alert = new Alert(title, genericAlert, aRecetar);
				    genericRestrictionReport.add(alert);
				}
				break;
			    case "NUN":
				if (history.getEdad() < Constants.edadNiño) {
				    genericAlert = "El componente químico " + cc.getComponentName()
					    + " no debería recetarse en niños menores a " + Constants.edadNiño
					    + " años";
				    alert = new Alert(title, genericAlert, aRecetar);
				    genericRestrictionReport.add(alert);
				}
				break;
			    case "NUE":
				if (history.isEmbarazo()) {
				    genericAlert = "El componente químico " + cc.getComponentName()
					    + " no debería recetarse en mujeres embarazadas";
				    alert = new Alert(title, genericAlert, aRecetar);
				    genericRestrictionReport.add(alert);
				}
				break;
			    case "NUL":
				if (history.isLactancia()) {
				    genericAlert = "El componente químico " + cc.getComponentName()
					    + " no debería recetarse en mujeres durante la lactancia";
				    alert = new Alert(title, genericAlert, aRecetar);
				    genericRestrictionReport.add(alert);
				}
				break;
			}

		    }
		}
	    }
	} else {
	    aRecetar = true;
	    for (ChemicalComponent cc : newList) {
		if (cc.getRestrictions() != null) {
		    for (Restriction r : cc.getRestrictions()) {
			switch (r.getCode()) {
			    case "ADIRG":
				if (history.getGlomerularFiltration() < Constants.renalInsufficiencySevere) {
				    genericAlert = "El componente químico " + cc.getComponentName()
					    + " necesita un ajuste de dósis debido a que el nivel glomerular del paciente es menor a "
					    + Constants.renalInsufficiencySevere;
				    alert = new Alert(title, genericAlert, aRecetar);
				    genericRestrictionReport.add(alert);
				}
				break;
			    case "ADIRL":
				if (history.getGlomerularFiltration() < Constants.renalInsufficiencyMild) {
				    genericAlert = "El componente químico " + cc.getComponentName()
					    + " necesita un ajuste de dósis debido a que el nivel glomerular del paciente es menor a "
					    + Constants.renalInsufficiencyMild;
				    alert = new Alert(title, genericAlert, aRecetar);
				    genericRestrictionReport.add(alert);
				}
				break;
			    case "ADIH":
				if (history.isLiverFailure()) {
				    genericAlert = "El componente químico " + cc.getComponentName()
					    + " necesita un ajuste de dósis debido a la insuficiencia hepática";
				    alert = new Alert(title, genericAlert, aRecetar);
				    genericRestrictionReport.add(alert);
				}
				break;
			    case "ADKP":
				genericAlert = "El componente químico " + cc.getComponentName()
					+ " necesita un ajuste de dósis por Kg de peso";
				alert = new Alert(title, genericAlert, aRecetar);
				genericRestrictionReport.add(alert);
				break;
			    case "NUH":
				if (history.getSexo() == Sexo.MASCULINO) {
				    genericAlert = "El componente químico " + cc.getComponentName()
					    + " no debería recetarse en hombres";
				    alert = new Alert(title, genericAlert, aRecetar);
				    genericRestrictionReport.add(alert);
				}
				break;
			    case "NUM":
				if (history.getSexo() == Sexo.FEMENINO) {
				    genericAlert = "El componente químico " + cc.getComponentName()
					    + " no debería recetarse en mujeres";
				    alert = new Alert(title, genericAlert, aRecetar);
				    genericRestrictionReport.add(alert);
				}
				break;
			    case "NUN":
				if (history.getEdad() < Constants.edadNiño) {
				    genericAlert = "El componente químico " + cc.getComponentName()
					    + " no debería recetarse en niños menores a " + Constants.edadNiño
					    + " años";
				    alert = new Alert(title, genericAlert, aRecetar);
				    genericRestrictionReport.add(alert);
				}
				break;
			    case "NUE":
				if (history.isEmbarazo()) {
				    genericAlert = "El componente químico " + cc.getComponentName()
					    + " no debería recetarse en embarazadas";
				    alert = new Alert(title, genericAlert, aRecetar);
				    genericRestrictionReport.add(alert);
				}
				break;
			    case "NUL":
				if (history.isLactancia()) {
				    genericAlert = "El componente químico " + cc.getComponentName()
					    + " no debería recetarse en mujeres durante la lactancia";
				    alert = new Alert(title, genericAlert, aRecetar);
				    genericRestrictionReport.add(alert);
				}
				break;
			}

		    }
		}
	    }
	}
	return genericRestrictionReport;
    }

    private List<Alert> chemicalComponentsInteractionsRestrictionFilter(List<ChemicalComponent> actualList,
	    List<ChemicalComponent> newList, boolean comprobarRecetados) {
	List<Alert> chemicalComponentsInteractionsRestrictionReport = new ArrayList<>();
	boolean aRecetar = false;
	String title = "Restricciones entre componentes químicos";
	String chemicalComponentsInteractionAlert = null;
	Alert alert = null;

	if (comprobarRecetados) {
	    for (ChemicalComponent cc : actualList) {
		for (ComponentRestriction cr : cc.getComponentRestrictions()) {
		    List<ChemicalComponent> parRestriction = new ArrayList<ChemicalComponent>(cr.getComponents());
		    if (parRestriction.contains(cc)) {
			parRestriction.remove(cc);
		    }
		    for (ChemicalComponent ccRestriction : parRestriction) {
			if (actualList.contains(ccRestriction)) {
			    chemicalComponentsInteractionAlert = "El componente químico " + cc.getComponentName()
				    + " no debería recetarse mientras se le este recetando el componente químico "
				    + ccRestriction.getComponentName();
			    alert = new Alert(title, chemicalComponentsInteractionAlert, aRecetar);
			    chemicalComponentsInteractionsRestrictionReport.add(alert);
			}
		    }
		}
	    }
	} else {
	    aRecetar = true;
	    for (ChemicalComponent cc : newList) {
		for (ComponentRestriction cr : cc.getComponentRestrictions()) {
		    List<ChemicalComponent> parRestriction = new ArrayList<ChemicalComponent>(cr.getComponents());
		    if (parRestriction.contains(cc)) {
			parRestriction.remove(cc);
		    }
		    for (ChemicalComponent ccRestriction : parRestriction) {
			if (actualList.contains(ccRestriction)) {
			    chemicalComponentsInteractionAlert = "El componente químico " + cc.getComponentName()
				    + " no debería recetarse mientras esté vigente el componente químico "
				    + ccRestriction.getComponentName();
			    alert = new Alert(title, chemicalComponentsInteractionAlert, aRecetar);
			    chemicalComponentsInteractionsRestrictionReport.add(alert);
			}
		    }
		}
	    }
	}
	return chemicalComponentsInteractionsRestrictionReport;
    }

    private List<Alert> diseaseAllergyIntolerationRestrictionFilter(List<ChemicalComponent> actualList,
	    List<ChemicalComponent> newList, boolean comprobarRecetados, MedicalHistory history) {

	List<Alert> diseaseAllergyIntolerationRestrictionReport = new ArrayList<>();
	boolean aRecetar = false;
	String title = "";
	String diseaseAllergyIntolerationAlert = null;
	Alert alert = null;

	Set<Allergy> Allergies = history.getAllergies();
	Set<Disease> Diseases = history.getDiseases();
	Set<Intolerance> Intolerances = history.getIntolerances();
	List<Allergy> allergyList = new ArrayList<Allergy>(Allergies);
	List<Disease> diseaseList = new ArrayList<Disease>(Diseases);
	List<Intolerance> intoleranceList = new ArrayList<Intolerance>(Intolerances);

	if (comprobarRecetados) {
	    for (ChemicalComponent cc : actualList) {
		for (AllergyRestriction ar : cc.getAllergyRestrictions()) {
		    if (allergyList.contains(ar.getAllergy())) {
			title = "Restricciones con alergias";
			diseaseAllergyIntolerationAlert = "El componente químico " + cc.getComponentName()
				+ " no debería recetarse debido a su alergia: " + ar.getName();
			alert = new Alert(title, diseaseAllergyIntolerationAlert, aRecetar);
			diseaseAllergyIntolerationRestrictionReport.add(alert);
		    }
		}
		for (DiseaseRestriction dr : cc.getDiseaseRestrictions()) {
		    if (diseaseList.contains(dr.getDisease())) {
			title = "Restricciones con enfermedaes";
			diseaseAllergyIntolerationAlert = "El componente químico " + cc.getComponentName()
				+ " no debería recetarse debido a su enfermedad: " + dr.getName();
			alert = new Alert(title, diseaseAllergyIntolerationAlert, aRecetar);
			diseaseAllergyIntolerationRestrictionReport.add(alert);
		    }
		}
		for (IntoleranceRestriction ir : cc.getIntoleranceRestrictions()) {
		    if (intoleranceList.contains(ir.getIntolerance())) {
			title = "Restricciones con intolerancias";
			diseaseAllergyIntolerationAlert = "El componente químico " + cc.getComponentName()
				+ " no debería recetarse debido a su intolerancia: " + ir.getName();
			alert = new Alert(title, diseaseAllergyIntolerationAlert, aRecetar);
			diseaseAllergyIntolerationRestrictionReport.add(alert);
		    }
		}
	    }
	} else {
	    aRecetar = true;
	    for (ChemicalComponent cc : newList) {
		for (AllergyRestriction ar : cc.getAllergyRestrictions()) {
		    if (allergyList.contains(ar.getAllergy())) {
			title = "Restricciones con alergias";
			diseaseAllergyIntolerationAlert = "El componente químico " + cc.getComponentName()
				+ " no debería recetarse debido a su alergia: " + ar.getName();
			alert = new Alert(title, diseaseAllergyIntolerationAlert, aRecetar);
			diseaseAllergyIntolerationRestrictionReport.add(alert);
		    }
		}
		for (DiseaseRestriction dr : cc.getDiseaseRestrictions()) {
		    if (diseaseList.contains(dr.getDisease())) {
			title = "Restricciones con enfermedaes";
			diseaseAllergyIntolerationAlert = "El componente químico " + cc.getComponentName()
				+ " no debería recetarse debido a su enfermedad: " + dr.getName();
			alert = new Alert(title, diseaseAllergyIntolerationAlert, aRecetar);
			diseaseAllergyIntolerationRestrictionReport.add(alert);
		    }
		}
		for (IntoleranceRestriction ir : cc.getIntoleranceRestrictions()) {
		    if (intoleranceList.contains(ir.getIntolerance())) {
			title = "Restricciones con intolerancias";
			diseaseAllergyIntolerationAlert = "El componente químico " + cc.getComponentName()
				+ " no debería recetarse debido a su intolerancia: " + ir.getName();
			alert = new Alert(title, diseaseAllergyIntolerationAlert, aRecetar);
			diseaseAllergyIntolerationRestrictionReport.add(alert);
		    }
		}
	    }
	}

	return diseaseAllergyIntolerationRestrictionReport;

    }

}
