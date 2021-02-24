package es.udc.paproject.backend.test.model.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

import java.sql.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import es.udc.tfgproject.backend.Application;
import es.udc.tfgproject.backend.model.entities.Alert;
import es.udc.tfgproject.backend.model.entities.disease_intolerance_allergy.Allergy;
import es.udc.tfgproject.backend.model.entities.disease_intolerance_allergy.Disease;
import es.udc.tfgproject.backend.model.entities.disease_intolerance_allergy.Intolerance;
import es.udc.tfgproject.backend.model.entities.patientInformation.MedicalHistory;
import es.udc.tfgproject.backend.model.entities.patientInformation.MedicalHistory.Sexo;
import es.udc.tfgproject.backend.model.entities.patientInformation.Treatment;
import es.udc.tfgproject.backend.model.exceptions.InstanceNotFoundException;
import es.udc.tfgproject.backend.model.services.MedicalService;

@SpringBootTest(classes = Application.class)
@ActiveProfiles("test")
@Transactional
public class MedicalServiceTest {

    private final Long NON_EXISTENT_ID = new Long(-1);

    @Autowired
    private MedicalService medicalService;

    private MedicalHistory createMedicalHistory(int edad, Sexo sexo, boolean embarazo, boolean lactancia,
	    int glomerularFiltration, boolean liverFailure, Set<Treatment> actualTreatments, Set<Allergy> allergies,
	    Set<Disease> diseases, Set<Intolerance> intolerances) {
	MedicalHistory history = new MedicalHistory(edad, sexo, embarazo, lactancia, glomerularFiltration, liverFailure,
		actualTreatments, allergies, diseases, intolerances);
	return history;
    }

    private Set<Treatment> createActualTreatments(String[] treatmentNames) {
	Set<Treatment> actualTreatments = new HashSet<Treatment>();
	for (String s : treatmentNames) {
	    Treatment t = new Treatment(s);
	    actualTreatments.add(t);
	}
	return actualTreatments;
    }

    @Test
    public void testtherapeuticGroupFilter() throws InstanceNotFoundException {

	int edad = 20;
	Sexo sexo = Sexo.MASCULINO;
	boolean embarazo = false;
	boolean lactancia = false;
	int glomerularFiltration = 70;
	boolean liverFailure = false;
	String[] treatmentNames = new String[] { "Propecia" };
	Set<Treatment> actualTreatments = createActualTreatments(treatmentNames);
	Set<Allergy> allergies = null;
	Set<Disease> diseases = null;
	Set<Intolerance> intolerances = null;
	MedicalHistory history = createMedicalHistory(edad, sexo, embarazo, lactancia, glomerularFiltration,
		liverFailure, actualTreatments, allergies, diseases, intolerances);

	Treatment treatment = new Treatment("Avidart");

	List<Alert> result = medicalService.completeReport(history, treatment, false);

	List<Alert> resultCompare = new ArrayList<>();

	String title = "Grupo terapeutico";
	boolean aRecetar = false;
	String therapeuticGroupAlert = "Los grupos terapeuticos de los componentes (a recetar - recetado):"
		+ " Dutasteride y Finasteride son de la misma familia: Inhibidores de la 5 alfa reductasa";
	Alert alert = new Alert(title, therapeuticGroupAlert, aRecetar);
	resultCompare.add(alert);

	ArrayList<Alert> resultArray = new ArrayList<Alert>();
	resultArray.addAll(result);
	ArrayList<Alert> resultCompareArray = new ArrayList<Alert>();
	resultCompareArray.addAll(resultCompare);
	
	for(int i = 0; i == resultArray.size(); i++) {
		assertEquals(resultArray.get(i).getMessage(), resultCompareArray.get(i).getMessage());
	}
	
    }
}
