package es.udc.tfgproject.backend.model.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.udc.tfgproject.backend.model.entities.Alert;
import es.udc.tfgproject.backend.model.entities.Sexo;
import es.udc.tfgproject.backend.model.entities.appSettings.Constants;
import es.udc.tfgproject.backend.model.entities.disease_intolerance_allergy.Allergy;
import es.udc.tfgproject.backend.model.entities.disease_intolerance_allergy.Disease;
import es.udc.tfgproject.backend.model.entities.disease_intolerance_allergy.Intolerance;
import es.udc.tfgproject.backend.model.entities.medicamentInformation.ChemicalComponent;
import es.udc.tfgproject.backend.model.entities.medicamentInformation.CommercialMedicament;
import es.udc.tfgproject.backend.model.entities.medicamentInformation.CommercialMedicamentDao;
import es.udc.tfgproject.backend.model.entities.patientInformation.MedicalHistory;
import es.udc.tfgproject.backend.model.entities.patientInformation.Treatment;
import es.udc.tfgproject.backend.model.entities.restrictions.RegularRestriction;
import es.udc.tfgproject.backend.model.exceptions.InstanceNotFoundException;

@Service
@Transactional(readOnly = true)
public class MedicalServiceImpl implements MedicalService {

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
	ArrayList<Treatment> actualTreatments = history.getActualTreatments();
	CommercialMedicament comercialMedicament;
	if (actualTreatments.isEmpty()) {
	    return actualChemicalComponents;
	}
	for (Treatment t : actualTreatments) {
	    comercialMedicament = checkCommercialMedicamentByName(t.getCommercialMedicamentName());
	    ChemicalComponents = comercialMedicament.getMedicament().getChemicalComponents();
	    ChemicalComponentsList = new ArrayList<ChemicalComponent>(ChemicalComponents);
	    for (ChemicalComponent cc : ChemicalComponentsList) {
		if (!actualChemicalComponents.contains(cc)) {
		    actualChemicalComponents.add(actualChemicalComponents.size(), cc);
		}
	    }
	}
	return actualChemicalComponents;
    }

    @Override
    public List<ChemicalComponent> newChemicalComponentList(Treatment treatment) throws InstanceNotFoundException {
	List<ChemicalComponent> newChemicalComponents = new ArrayList<ChemicalComponent>();
	if (treatment == null) {
	    return newChemicalComponents;
	}
	CommercialMedicament comercialMedicament = checkCommercialMedicamentByName(
		treatment.getCommercialMedicamentName());
	Set<ChemicalComponent> ChemicalComponents = comercialMedicament.getMedicament().getChemicalComponents();
	List<ChemicalComponent> ChemicalComponentsList = new ArrayList<ChemicalComponent>(ChemicalComponents);

	if (ChemicalComponentsList.isEmpty()) {
	    return newChemicalComponents;
	}
	for (ChemicalComponent cc : ChemicalComponentsList) {
	    if (!newChemicalComponents.contains(cc)) {
		newChemicalComponents.add(newChemicalComponents.size(), cc);
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
	List<ChemicalComponent> checkedList = new ArrayList<>();

	boolean aRecetar = false;
	String title = "Grupo terapeutico";
	// Comprobación entre componentes ya recetados
	if (comprobarRecetados) {
	    for (ChemicalComponent cc : actualListCopy) {
		for (ChemicalComponent ccCompare : actualListCopy) {
		    if (!checkedList.contains(ccCompare)) {
			if (!cc.getId().equals(ccCompare.getId())) {
			    if (cc.getFamily().getFamilyName().equals(ccCompare.getFamily().getFamilyName())) {
				String therapeuticGroupAlert = "Los grupos terapeuticos de los componentes (ya recetados): "
					+ cc.getComponentName() + " y " + ccCompare.getComponentName()
					+ " son de la misma familia: " + cc.getFamily().getFamilyName();
				Alert alert = new Alert(title, therapeuticGroupAlert, aRecetar);
				therapeuticGroupReport.add(therapeuticGroupReport.size(), alert);
			    }
			}
		    }
		}
		checkedList.add(cc);
	    }
	}
	aRecetar = true;
	// Comprobación cruzada
	for (ChemicalComponent cc : newList) {
	    for (ChemicalComponent ccCompare : actualList) {
		if (cc.getFamily().getFamilyName().equals(ccCompare.getFamily().getFamilyName())) {
		    String therapeuticGroupAlert = "Los grupos terapeuticos de los componentes (a recetar - recetado): "
			    + cc.getComponentName() + " y " + ccCompare.getComponentName()
			    + " son de la misma familia: " + cc.getFamily().getFamilyName();
		    Alert alert = new Alert(title, therapeuticGroupAlert, aRecetar);
		    therapeuticGroupReport.add(therapeuticGroupReport.size(), alert);
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
		if (cc.getRegularRestrictions() != null) {
		    for (RegularRestriction r : cc.getRegularRestrictions()) {
			switch (r.getCode()) {
			    case "ADIRG":
				if (history.getGlomerularFiltration() < Constants.renalInsufficiencySevere) {
				    genericAlert = "El componente químico (ya recetado) " + cc.getComponentName()
					    + " necesita un ajuste de dósis debido a que el nivel glomerular del paciente es menor a "
					    + Constants.renalInsufficiencySevere + " (Incontinencia renal grave)";
				    alert = new Alert(title, genericAlert, aRecetar);
				    genericRestrictionReport.add(genericRestrictionReport.size(), alert);
				}
				break;
			    case "ADIRL":
				if (history.getGlomerularFiltration() < Constants.renalInsufficiencyMild) {
				    genericAlert = "El componente químico (ya recetado) " + cc.getComponentName()
					    + " necesita un ajuste de dósis debido a que el nivel glomerular del paciente es menor a "
					    + Constants.renalInsufficiencyMild + " (Incontinencia renal leve)";
				    alert = new Alert(title, genericAlert, aRecetar);
				    genericRestrictionReport.add(genericRestrictionReport.size(), alert);
				}
				break;
			    case "ADIH":
				if (history.isLiverFailure()) {
				    genericAlert = "El componente químico (ya recetado) " + cc.getComponentName()
					    + " necesita un ajuste de dósis debido a la insuficiencia hepática";
				    alert = new Alert(title, genericAlert, aRecetar);
				    genericRestrictionReport.add(genericRestrictionReport.size(), alert);
				}
				break;
			    case "ADKP":
				genericAlert = "El componente químico (ya recetado) " + cc.getComponentName()
					+ " necesita un ajuste de dósis por Kg de peso";
				alert = new Alert(title, genericAlert, aRecetar);
				genericRestrictionReport.add(genericRestrictionReport.size(), alert);
				break;
			    case "NUH":
				if (history.getSexo() == Sexo.MASCULINO) {
				    genericAlert = "El componente químico (ya recetado) " + cc.getComponentName()
					    + " no debería recetarse en hombres";
				    alert = new Alert(title, genericAlert, aRecetar);
				    genericRestrictionReport.add(genericRestrictionReport.size(), alert);
				}
				break;
			    case "NUM":
				if (history.getSexo() == Sexo.FEMENINO) {
				    genericAlert = "El componente químico (ya recetado) " + cc.getComponentName()
					    + " no debería recetarse en mujeres";
				    alert = new Alert(title, genericAlert, aRecetar);
				    genericRestrictionReport.add(genericRestrictionReport.size(), alert);
				}
				break;
			    case "NUN":
				if (history.getEdad() < Constants.edadNiño) {
				    genericAlert = "El componente químico (ya recetado) " + cc.getComponentName()
					    + " no debería recetarse en niños menores a " + Constants.edadNiño
					    + " años";
				    alert = new Alert(title, genericAlert, aRecetar);
				    genericRestrictionReport.add(genericRestrictionReport.size(), alert);
				}
				break;
			    case "NUE":
				if (history.isEmbarazo()) {
				    genericAlert = "El componente químico (ya recetado) " + cc.getComponentName()
					    + " no debería recetarse en mujeres embarazadas";
				    alert = new Alert(title, genericAlert, aRecetar);
				    genericRestrictionReport.add(genericRestrictionReport.size(), alert);
				}
				break;
			    case "NUL":
				if (history.isLactancia()) {
				    genericAlert = "El componente químico (ya recetado) " + cc.getComponentName()
					    + " no debería recetarse en mujeres durante la lactancia";
				    alert = new Alert(title, genericAlert, aRecetar);
				    genericRestrictionReport.add(genericRestrictionReport.size(), alert);
				}
				break;
			}

		    }
		}
	    }
	}
	aRecetar = true;
	for (ChemicalComponent cc : newList) {
	    if (cc.getRegularRestrictions() != null) {
		for (RegularRestriction r : cc.getRegularRestrictions()) {
		    switch (r.getCode()) {
			case "ADIRG":
			    if (history.getGlomerularFiltration() < Constants.renalInsufficiencySevere) {
				genericAlert = "El componente químico (a recetar) " + cc.getComponentName()
					+ " necesita un ajuste de dósis debido a que el nivel glomerular del paciente es menor a "
					+ Constants.renalInsufficiencySevere + " (Incontinencia renal grave)";
				alert = new Alert(title, genericAlert, aRecetar);
				genericRestrictionReport.add(genericRestrictionReport.size(), alert);
			    }
			    break;
			case "ADIRL":
			    if (history.getGlomerularFiltration() < Constants.renalInsufficiencyMild) {
				genericAlert = "El componente químico (a recetar) " + cc.getComponentName()
					+ " necesita un ajuste de dósis debido a que el nivel glomerular del paciente es menor a "
					+ Constants.renalInsufficiencyMild + " (Incontinencia renal leve)";
				alert = new Alert(title, genericAlert, aRecetar);
				genericRestrictionReport.add(genericRestrictionReport.size(), alert);
			    }
			    break;
			case "ADIH":
			    if (history.isLiverFailure()) {
				genericAlert = "El componente químico (a recetar) " + cc.getComponentName()
					+ " necesita un ajuste de dósis debido a la insuficiencia hepática";
				alert = new Alert(title, genericAlert, aRecetar);
				genericRestrictionReport.add(genericRestrictionReport.size(), alert);
			    }
			    break;
			case "ADKP":
			    genericAlert = "El componente químico (a recetar) " + cc.getComponentName()
				    + " necesita un ajuste de dósis por Kg de peso";
			    alert = new Alert(title, genericAlert, aRecetar);
			    genericRestrictionReport.add(genericRestrictionReport.size(), alert);
			    break;
			case "NUH":
			    if (history.getSexo() == Sexo.MASCULINO) {
				genericAlert = "El componente químico (a recetar) " + cc.getComponentName()
					+ " no debería recetarse en hombres";
				alert = new Alert(title, genericAlert, aRecetar);
				genericRestrictionReport.add(genericRestrictionReport.size(), alert);
			    }
			    break;
			case "NUM":
			    if (history.getSexo() == Sexo.FEMENINO) {
				genericAlert = "El componente químico (a recetar) " + cc.getComponentName()
					+ " no debería recetarse en mujeres";
				alert = new Alert(title, genericAlert, aRecetar);
				genericRestrictionReport.add(genericRestrictionReport.size(), alert);
			    }
			    break;
			case "NUN":
			    if (history.getEdad() < Constants.edadNiño) {
				genericAlert = "El componente químico (a recetar) " + cc.getComponentName()
					+ " no debería recetarse en niños menores a " + Constants.edadNiño + " años";
				alert = new Alert(title, genericAlert, aRecetar);
				genericRestrictionReport.add(genericRestrictionReport.size(), alert);
			    }
			    break;
			case "NUE":
			    if (history.isEmbarazo()) {
				genericAlert = "El componente químico (a recetar) " + cc.getComponentName()
					+ " no debería recetarse en embarazadas";
				alert = new Alert(title, genericAlert, aRecetar);
				genericRestrictionReport.add(genericRestrictionReport.size(), alert);
			    }
			    break;
			case "NUL":
			    if (history.isLactancia()) {
				genericAlert = "El componente químico (a recetar) " + cc.getComponentName()
					+ " no debería recetarse en mujeres durante la lactancia";
				alert = new Alert(title, genericAlert, aRecetar);
				genericRestrictionReport.add(genericRestrictionReport.size(), alert);
			    }
			    break;
		    }

		}
	    }
	}
	return genericRestrictionReport;
    }

    private List<Alert> chemicalComponentsInteractionsRestrictionFilter(List<ChemicalComponent> actualList,
	    List<ChemicalComponent> newList, boolean comprobarRecetados) {
	List<Alert> chemicalComponentsInteractionsRestrictionReport = new ArrayList<>();
	List<ChemicalComponent> checkedList = new ArrayList<ChemicalComponent>();
	boolean aRecetar = false;
	String title = "Restricciones entre componentes químicos";
	String chemicalComponentsInteractionAlert = null;
	Alert alert = null;

	List<ChemicalComponent> totalList = new ArrayList<ChemicalComponent>();

	for (ChemicalComponent cc : actualList) {
	    if (!totalList.contains(cc)) {
		totalList.add(cc);
	    }
	}

	for (ChemicalComponent cnew : newList) {
	    if (!totalList.contains(cnew)) {
		totalList.add(cnew);
	    }
	}

	if (comprobarRecetados) {
	    for (ChemicalComponent cc : actualList) {
		for (ChemicalComponent cr : cc.getChemicalComponents()) {
		    if (!checkedList.contains(cr)) {
			if (totalList.contains(cr)) {
			    chemicalComponentsInteractionAlert = "Uno de los componentes químicos "
				    + cc.getComponentName() + " o " + cr.getComponentName()
				    + " no debería administrarse mientras permanezca vigente el otro";
			    alert = new Alert(title, chemicalComponentsInteractionAlert, aRecetar);
			    chemicalComponentsInteractionsRestrictionReport
				    .add(chemicalComponentsInteractionsRestrictionReport.size(), alert);
			}
		    }
		}
		checkedList.add(cc);
	    }
	}

	aRecetar = true;
	for (ChemicalComponent cc : newList) {
	    for (ChemicalComponent cr : cc.getChemicalComponents()) {
		if (!checkedList.contains(cr)) {
		    if (totalList.contains(cr)) {
			chemicalComponentsInteractionAlert = "Uno de los componentes químicos " + cc.getComponentName()
				+ " o " + cr.getComponentName()
				+ " no debería administrarse mientras permanezca vigente el otro";
			alert = new Alert(title, chemicalComponentsInteractionAlert, aRecetar);
			chemicalComponentsInteractionsRestrictionReport
				.add(chemicalComponentsInteractionsRestrictionReport.size(), alert);
		    }
		}
		checkedList.add(cc);
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

	List<Allergy> allergyList = history.getAllergies();
	List<String> allergyListNames = new ArrayList<String>();
	if (allergyList != null) {
	    for (Allergy a : allergyList) {
		allergyListNames.add(a.getAllergyName());
	    }
	}
	List<Disease> diseaseList = history.getDiseases();
	List<String> diseaseListNames = new ArrayList<String>();
	if (diseaseList != null) {
	    for (Disease d : diseaseList) {
		diseaseListNames.add(d.getDiseaseName());
	    }
	}
	List<Intolerance> intoleranceList = history.getIntolerances();
	List<String> intoleranceListNames = new ArrayList<String>();
	if (intoleranceList != null) {
	    for (Intolerance i : intoleranceList) {
		intoleranceListNames.add(i.getIntoleranceName());
	    }
	}

	if (comprobarRecetados) {
	    for (ChemicalComponent cc : actualList) {
		for (Allergy ar : cc.getAllergies()) {
		    if (allergyListNames.contains(ar.getAllergyName())) {
			title = "Restricciones con alergias";
			diseaseAllergyIntolerationAlert = "El componente químico (ya recetado) " + cc.getComponentName()
				+ " no debería recetarse debido a su alergia: " + ar.getAllergyName();
			alert = new Alert(title, diseaseAllergyIntolerationAlert, aRecetar);
			diseaseAllergyIntolerationRestrictionReport
				.add(diseaseAllergyIntolerationRestrictionReport.size(), alert);
		    }
		}
		for (Disease dr : cc.getDiseases()) {
		    if (diseaseListNames.contains(dr.getDiseaseName())) {
			title = "Restricciones con enfermedades";
			diseaseAllergyIntolerationAlert = "El componente químico (ya recetado) " + cc.getComponentName()
				+ " no debería recetarse debido a su enfermedad: " + dr.getDiseaseName();
			alert = new Alert(title, diseaseAllergyIntolerationAlert, aRecetar);
			diseaseAllergyIntolerationRestrictionReport
				.add(diseaseAllergyIntolerationRestrictionReport.size(), alert);
		    }
		}
		for (Intolerance ir : cc.getIntolerances()) {
		    if (intoleranceListNames.contains(ir.getIntoleranceName())) {
			title = "Restricciones con intolerancias";
			diseaseAllergyIntolerationAlert = "El componente químico (ya recetado) " + cc.getComponentName()
				+ " no debería recetarse debido a su intolerancia: " + ir.getIntoleranceName();
			alert = new Alert(title, diseaseAllergyIntolerationAlert, aRecetar);
			diseaseAllergyIntolerationRestrictionReport
				.add(diseaseAllergyIntolerationRestrictionReport.size(), alert);
		    }
		}
	    }
	}
	aRecetar = true;
	for (ChemicalComponent cc : newList) {
	    for (Allergy ar : cc.getAllergies()) {
		if (allergyListNames.contains(ar.getAllergyName())) {
		    title = "Restricciones con alergias";
		    diseaseAllergyIntolerationAlert = "El componente químico (a recetar) " + cc.getComponentName()
			    + " no debería recetarse debido a su alergia: " + ar.getAllergyName();
		    alert = new Alert(title, diseaseAllergyIntolerationAlert, aRecetar);
		    diseaseAllergyIntolerationRestrictionReport.add(diseaseAllergyIntolerationRestrictionReport.size(),
			    alert);
		}
	    }
	    for (Disease dr : cc.getDiseases()) {
		if (diseaseListNames.contains(dr.getDiseaseName())) {
		    title = "Restricciones con enfermedades";
		    diseaseAllergyIntolerationAlert = "El componente químico (a recetar) " + cc.getComponentName()
			    + " no debería recetarse debido a su enfermedad: " + dr.getDiseaseName();
		    alert = new Alert(title, diseaseAllergyIntolerationAlert, aRecetar);
		    diseaseAllergyIntolerationRestrictionReport.add(diseaseAllergyIntolerationRestrictionReport.size(),
			    alert);
		}
	    }
	    for (Intolerance ir : cc.getIntolerances()) {
		if (intoleranceListNames.contains(ir.getIntoleranceName())) {
		    title = "Restricciones con intolerancias";
		    diseaseAllergyIntolerationAlert = "El componente químico (a recetar) " + cc.getComponentName()
			    + " no debería recetarse debido a su intolerancia: " + ir.getIntoleranceName();
		    alert = new Alert(title, diseaseAllergyIntolerationAlert, aRecetar);
		    diseaseAllergyIntolerationRestrictionReport.add(diseaseAllergyIntolerationRestrictionReport.size(),
			    alert);
		}
	    }
	}

	return diseaseAllergyIntolerationRestrictionReport;

    }

}
