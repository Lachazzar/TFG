package es.udc.tfgproject.backend.test.model.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import es.udc.tfgproject.backend.Application;
import es.udc.tfgproject.backend.model.entities.Alert;
import es.udc.tfgproject.backend.model.entities.Sexo;
import es.udc.tfgproject.backend.model.entities.disease_intolerance_allergy.Allergy;
import es.udc.tfgproject.backend.model.entities.disease_intolerance_allergy.Disease;
import es.udc.tfgproject.backend.model.entities.disease_intolerance_allergy.Intolerance;
import es.udc.tfgproject.backend.model.entities.patientInformation.MedicalHistory;
import es.udc.tfgproject.backend.model.entities.patientInformation.Treatment;
import es.udc.tfgproject.backend.model.exceptions.InstanceNotFoundException;
import es.udc.tfgproject.backend.model.services.MedicalService;

@SpringBootTest(classes = Application.class)
@ActiveProfiles("test")
@Transactional
public class MedicalServiceTest {

    @Autowired
    private MedicalService medicalService;

    private MedicalHistory createMedicalHistory(int edad, Sexo sexo, boolean embarazo, boolean lactancia,
	    int glomerularFiltration, boolean liverFailure, ArrayList<Treatment> actualTreatments,
	    ArrayList<Allergy> allergies, ArrayList<Disease> diseases, ArrayList<Intolerance> intolerances) {
	MedicalHistory history = new MedicalHistory(edad, sexo, embarazo, lactancia, glomerularFiltration, liverFailure,
		actualTreatments, allergies, diseases, intolerances);
	return history;
    }

    private ArrayList<Treatment> createActualTreatments(String[] treatmentNames) {
	ArrayList<Treatment> actualTreatments = new ArrayList<>();
	for (String s : treatmentNames) {
	    Treatment t = new Treatment(s);
	    actualTreatments.add(actualTreatments.size(), t);
	}
	return actualTreatments;
    }

    private ArrayList<Allergy> createAllergies(String[] allergyNames) {
	ArrayList<Allergy> allergies = new ArrayList<>();
	for (String s : allergyNames) {
	    Allergy a = new Allergy(s);
	    allergies.add(allergies.size(), a);
	}
	return allergies;
    }

    private ArrayList<Disease> createDiseases(String[] diseaseNames) {
	ArrayList<Disease> diseases = new ArrayList<>();
	for (String s : diseaseNames) {
	    Disease d = new Disease(s);
	    diseases.add(diseases.size(), d);
	}
	return diseases;
    }

    private ArrayList<Intolerance> createIntolerances(String[] intoleranceNames) {
	ArrayList<Intolerance> intolerances = new ArrayList<>();
	for (String s : intoleranceNames) {
	    Intolerance i = new Intolerance(s);
	    intolerances.add(intolerances.size(), i);
	}
	return intolerances;
    }

    @Test
    public void testInstanceNotFoundActualList() throws InstanceNotFoundException {
	int edad = 20;
	Sexo sexo = Sexo.MASCULINO;
	boolean embarazo = false;
	boolean lactancia = false;
	int glomerularFiltration = 70;
	boolean liverFailure = false;
	String[] treatmentNames = new String[] { "Inventado" };
	ArrayList<Treatment> actualTreatments = createActualTreatments(treatmentNames);
	ArrayList<Allergy> allergies = null;
	ArrayList<Disease> diseases = null;
	ArrayList<Intolerance> intolerances = null;
	MedicalHistory history = createMedicalHistory(edad, sexo, embarazo, lactancia, glomerularFiltration,
		liverFailure, actualTreatments, allergies, diseases, intolerances);

	assertThrows(InstanceNotFoundException.class, () -> medicalService.actualChemicalComponentList(history));

    }

    @Test
    public void testInstanceNotFoundNewList() throws InstanceNotFoundException {

	Treatment treatment = new Treatment("Inventado");

	assertThrows(InstanceNotFoundException.class, () -> medicalService.newChemicalComponentList(treatment));

    }

    @Test
    public void testTherapeuticGroupFilter() throws InstanceNotFoundException {
	int edad = 20;
	Sexo sexo = Sexo.MASCULINO;
	boolean embarazo = false;
	boolean lactancia = false;
	int glomerularFiltration = 70;
	boolean liverFailure = false;
	String[] treatmentNames = new String[] { "Propecia" };
	ArrayList<Treatment> actualTreatments = createActualTreatments(treatmentNames);
	ArrayList<Allergy> allergies = null;
	ArrayList<Disease> diseases = null;
	ArrayList<Intolerance> intolerances = null;
	MedicalHistory history = createMedicalHistory(edad, sexo, embarazo, lactancia, glomerularFiltration,
		liverFailure, actualTreatments, allergies, diseases, intolerances);

	Treatment treatment = new Treatment("Avidart");

	List<Alert> result = medicalService.completeReport(history, treatment, false);

	List<Alert> resultCompare = new ArrayList<>();

	String title = "Grupo terapeutico";
	boolean aRecetar = true;
	String therapeuticGroupAlert = "Los grupos terapeuticos de los componentes (a recetar - recetado):"
		+ " Dutasteride y Finasteride son de la misma familia: Inhibidores de la 5 alfa reductasa";
	Alert alert = new Alert(title, therapeuticGroupAlert, aRecetar);
	resultCompare.add(resultCompare.size(), alert);

	ArrayList<Alert> resultArray = new ArrayList<Alert>();
	resultArray.addAll(result);
	ArrayList<Alert> resultCompareArray = new ArrayList<Alert>();
	resultCompareArray.addAll(resultCompare);

	System.out.println("testTherapeuticGroupFilter");
	assertEquals(resultArray.size(), resultCompareArray.size());
	for (int i = 0; i < resultArray.size(); i++) {
	    System.out.println("Result: " + resultArray.get(i).getMessage());
	    System.out.println("Expected: " + resultCompareArray.get(i).getMessage());
	    assertEquals(resultArray.get(i).getMessage(), resultCompareArray.get(i).getMessage());
	    assertEquals(resultArray.get(i).getTitle(), resultCompareArray.get(i).getTitle());
	    assertEquals(resultArray.get(i).isaRecetar(), resultCompareArray.get(i).isaRecetar());
	}

    }

    @Test
    public void testTherapeuticGroupFilterRecetados() throws InstanceNotFoundException {

	int edad = 20;
	Sexo sexo = Sexo.MASCULINO;
	boolean embarazo = false;
	boolean lactancia = false;
	int glomerularFiltration = 70;
	boolean liverFailure = false;
	String[] treatmentNames = new String[] { "Propecia", "Avidart", "Diflucan", "VFEND" };
	ArrayList<Treatment> actualTreatments = createActualTreatments(treatmentNames);
	ArrayList<Allergy> allergies = null;
	ArrayList<Disease> diseases = null;
	ArrayList<Intolerance> intolerances = null;
	MedicalHistory history = createMedicalHistory(edad, sexo, embarazo, lactancia, glomerularFiltration,
		liverFailure, actualTreatments, allergies, diseases, intolerances);

	List<Alert> result = medicalService.completeReport(history, null, true);

	List<Alert> resultCompare = new ArrayList<>();

	String title = "Grupo terapeutico";
	boolean aRecetar = false;
	String therapeuticGroupAlert = "Los grupos terapeuticos de los componentes (ya recetados): "
		+ "Finasteride y Dutasteride son de la misma familia: Inhibidores de la 5 alfa reductasa";
	Alert alert = new Alert(title, therapeuticGroupAlert, aRecetar);
	resultCompare.add(resultCompare.size(), alert);

	String therapeuticGroupAlert2 = "Los grupos terapeuticos de los componentes (ya recetados): "
		+ "Fluconazol y Voriconazol son de la misma familia: Antifúngicos";
	Alert alert2 = new Alert(title, therapeuticGroupAlert2, aRecetar);
	resultCompare.add(resultCompare.size(), alert2);

	ArrayList<Alert> resultArray = new ArrayList<Alert>();
	resultArray.addAll(result);
	ArrayList<Alert> resultCompareArray = new ArrayList<Alert>();
	resultCompareArray.addAll(resultCompare);

	System.out.println("testTherapeuticGroupFilterRecetados");
	assertEquals(resultArray.size(), resultCompareArray.size());
	for (int i = 0; i < resultArray.size(); i++) {
	    System.out.println("Result: " + resultArray.get(i).getMessage());
	    System.out.println("Expected: " + resultCompareArray.get(i).getMessage());
	    assertEquals(resultArray.get(i).getMessage(), resultCompareArray.get(i).getMessage());
	    assertEquals(resultArray.get(i).getTitle(), resultCompareArray.get(i).getTitle());
	    assertEquals(resultArray.get(i).isaRecetar(), resultCompareArray.get(i).isaRecetar());
	}

    }

    @Test
    public void testTherapeuticGroupFilterRecetadosARecetar() throws InstanceNotFoundException {

	int edad = 20;
	Sexo sexo = Sexo.MASCULINO;
	boolean embarazo = false;
	boolean lactancia = false;
	int glomerularFiltration = 70;
	boolean liverFailure = false;
	String[] treatmentNames = new String[] { "Propecia", "Diflucan", "VFEND" };
	ArrayList<Treatment> actualTreatments = createActualTreatments(treatmentNames);
	ArrayList<Allergy> allergies = null;
	ArrayList<Disease> diseases = null;
	ArrayList<Intolerance> intolerances = null;
	MedicalHistory history = createMedicalHistory(edad, sexo, embarazo, lactancia, glomerularFiltration,
		liverFailure, actualTreatments, allergies, diseases, intolerances);

	Treatment treatment = new Treatment("Avidart");

	List<Alert> result = medicalService.completeReport(history, treatment, true);

	List<Alert> resultCompare = new ArrayList<>();

	String title = "Grupo terapeutico";
	boolean aRecetar = false;
	String therapeuticGroupAlert = "Los grupos terapeuticos de los componentes (ya recetados): "
		+ "Fluconazol y Voriconazol son de la misma familia: Antifúngicos";
	Alert alert = new Alert(title, therapeuticGroupAlert, aRecetar);
	resultCompare.add(resultCompare.size(), alert);

	aRecetar = true;
	String therapeuticGroupAlert2 = "Los grupos terapeuticos de los componentes (a recetar - recetado):"
		+ " Dutasteride y Finasteride son de la misma familia: Inhibidores de la 5 alfa reductasa";
	Alert alert2 = new Alert(title, therapeuticGroupAlert2, aRecetar);
	resultCompare.add(resultCompare.size(), alert2);

	ArrayList<Alert> resultArray = new ArrayList<Alert>();
	resultArray.addAll(result);
	ArrayList<Alert> resultCompareArray = new ArrayList<Alert>();
	resultCompareArray.addAll(resultCompare);

	System.out.println("testTherapeuticGroupFilterRecetadosARecetar");
	assertEquals(resultArray.size(), resultCompareArray.size());
	for (int i = 0; i < resultArray.size(); i++) {
	    System.out.println("Result: " + resultArray.get(i).getMessage());
	    System.out.println("Expected: " + resultCompareArray.get(i).getMessage());
	    assertEquals(resultArray.get(i).getMessage(), resultCompareArray.get(i).getMessage());
	    assertEquals(resultArray.get(i).getTitle(), resultCompareArray.get(i).getTitle());
	    assertEquals(resultArray.get(i).isaRecetar(), resultCompareArray.get(i).isaRecetar());
	}

    }

    @Test
    public void testGenericReportFilter() throws InstanceNotFoundException {

	int edad = 20;
	Sexo sexo = Sexo.FEMENINO;
	boolean embarazo = false;
	boolean lactancia = false;
	int glomerularFiltration = 70;
	boolean liverFailure = false;
	String[] treatmentNames = new String[] {};
	ArrayList<Treatment> actualTreatments = createActualTreatments(treatmentNames);
	ArrayList<Allergy> allergies = null;
	ArrayList<Disease> diseases = null;
	ArrayList<Intolerance> intolerances = null;
	MedicalHistory history = createMedicalHistory(edad, sexo, embarazo, lactancia, glomerularFiltration,
		liverFailure, actualTreatments, allergies, diseases, intolerances);

	Treatment treatment = new Treatment("Propecia");

	List<Alert> result = medicalService.completeReport(history, treatment, false);

	List<Alert> resultCompare = new ArrayList<>();

	String title = "Restricciones genericas";
	boolean aRecetar = true;
	String genericReportAlert = "El componente químico (a recetar) Finasteride no debería recetarse en mujeres";
	Alert alert = new Alert(title, genericReportAlert, aRecetar);
	resultCompare.add(resultCompare.size(), alert);

	ArrayList<Alert> resultArray = new ArrayList<Alert>();
	resultArray.addAll(result);
	ArrayList<Alert> resultCompareArray = new ArrayList<Alert>();
	resultCompareArray.addAll(resultCompare);

	System.out.println("testGenericReportFilter");
	assertEquals(resultArray.size(), resultCompareArray.size());
	for (int i = 0; i < resultArray.size(); i++) {
	    System.out.println("Result: " + resultArray.get(i).getMessage());
	    System.out.println("Expected: " + resultCompareArray.get(i).getMessage());
	    assertEquals(resultArray.get(i).getMessage(), resultCompareArray.get(i).getMessage());
	    assertEquals(resultArray.get(i).getTitle(), resultCompareArray.get(i).getTitle());
	    assertEquals(resultArray.get(i).isaRecetar(), resultCompareArray.get(i).isaRecetar());
	}

    }

    @Test
    public void testGenericReportFilterRecetadoARecetar() throws InstanceNotFoundException {

	int edad = 20;
	Sexo sexo = Sexo.FEMENINO;
	boolean embarazo = false;
	boolean lactancia = false;
	int glomerularFiltration = 10;
	boolean liverFailure = false;
	String[] treatmentNames = new String[] { "Januvia" };
	ArrayList<Treatment> actualTreatments = createActualTreatments(treatmentNames);
	ArrayList<Allergy> allergies = null;
	ArrayList<Disease> diseases = null;
	ArrayList<Intolerance> intolerances = null;
	MedicalHistory history = createMedicalHistory(edad, sexo, embarazo, lactancia, glomerularFiltration,
		liverFailure, actualTreatments, allergies, diseases, intolerances);

	Treatment treatment = new Treatment("Propecia");

	List<Alert> result = medicalService.completeReport(history, treatment, true);

	List<Alert> resultCompare = new ArrayList<>();

	String title = "Restricciones genericas";
	boolean aRecetar = false;
	String genericReportAlert = "El componente químico (ya recetado) Sitaglipina necesita un ajuste de dósis debido a que el nivel glomerular del "
		+ "paciente es menor a 30 (Incontinencia renal grave)";
	Alert alert = new Alert(title, genericReportAlert, aRecetar);
	resultCompare.add(resultCompare.size(), alert);

	aRecetar = true;
	String genericReportAlert2 = "El componente químico (a recetar) Finasteride no debería recetarse en mujeres";
	Alert alert2 = new Alert(title, genericReportAlert2, aRecetar);
	resultCompare.add(resultCompare.size(), alert2);

	ArrayList<Alert> resultArray = new ArrayList<Alert>();
	resultArray.addAll(result);
	ArrayList<Alert> resultCompareArray = new ArrayList<Alert>();
	resultCompareArray.addAll(resultCompare);

	System.out.println("testGenericReportFilterRecetadoARecetar");
	assertEquals(resultArray.size(), resultCompareArray.size());
	for (int i = 0; i < resultArray.size(); i++) {
	    System.out.println("Result: " + resultArray.get(i).getMessage());
	    System.out.println("Expected: " + resultCompareArray.get(i).getMessage());
	    assertEquals(resultArray.get(i).getMessage(), resultCompareArray.get(i).getMessage());
	    assertEquals(resultArray.get(i).getTitle(), resultCompareArray.get(i).getTitle());
	    assertEquals(resultArray.get(i).isaRecetar(), resultCompareArray.get(i).isaRecetar());
	}

    }

    @Test
    public void testChemicalComponentInteractionFilterARecetarRecetado() throws InstanceNotFoundException {

	int edad = 20;
	Sexo sexo = Sexo.FEMENINO;
	boolean embarazo = false;
	boolean lactancia = false;
	int glomerularFiltration = 70;
	boolean liverFailure = false;
	String[] treatmentNames = new String[] { "Plavix" };
	ArrayList<Treatment> actualTreatments = createActualTreatments(treatmentNames);
	ArrayList<Allergy> allergies = null;
	ArrayList<Disease> diseases = null;
	ArrayList<Intolerance> intolerances = null;
	MedicalHistory history = createMedicalHistory(edad, sexo, embarazo, lactancia, glomerularFiltration,
		liverFailure, actualTreatments, allergies, diseases, intolerances);

	Treatment treatment = new Treatment("Losec");

	List<Alert> result = medicalService.completeReport(history, treatment, false);

	List<Alert> resultCompare = new ArrayList<>();

	String title = "Restricciones entre componentes químicos";
	boolean aRecetar = true;
	String chemicalComponentRestrictionAlert = "Uno de los componentes químicos Omeprazol o Clopridogel no debería administrarse mientras permanezca vigente el otro";
	Alert alert = new Alert(title, chemicalComponentRestrictionAlert, aRecetar);
	resultCompare.add(resultCompare.size(), alert);

	ArrayList<Alert> resultArray = new ArrayList<Alert>();
	resultArray.addAll(result);
	ArrayList<Alert> resultCompareArray = new ArrayList<Alert>();
	resultCompareArray.addAll(resultCompare);

	System.out.println("testChemicalComponentInteractionFilterARecetarRecetado");
	assertEquals(resultArray.size(), resultCompareArray.size());
	for (int i = 0; i < resultArray.size(); i++) {
	    System.out.println("Result: " + resultArray.get(i).getMessage());
	    System.out.println("Expected: " + resultCompareArray.get(i).getMessage());
	    assertEquals(resultArray.get(i).getMessage(), resultCompareArray.get(i).getMessage());
	    assertEquals(resultArray.get(i).getTitle(), resultCompareArray.get(i).getTitle());
	    assertEquals(resultArray.get(i).isaRecetar(), resultCompareArray.get(i).isaRecetar());
	}

    }

    @Test
    public void testChemicalComponentInteractionFilterRecetados() throws InstanceNotFoundException {

	int edad = 20;
	Sexo sexo = Sexo.FEMENINO;
	boolean embarazo = false;
	boolean lactancia = false;
	int glomerularFiltration = 70;
	boolean liverFailure = false;
	String[] treatmentNames = new String[] { "Losec", "Plavix" };
	ArrayList<Treatment> actualTreatments = createActualTreatments(treatmentNames);
	ArrayList<Allergy> allergies = null;
	ArrayList<Disease> diseases = null;
	ArrayList<Intolerance> intolerances = null;
	MedicalHistory history = createMedicalHistory(edad, sexo, embarazo, lactancia, glomerularFiltration,
		liverFailure, actualTreatments, allergies, diseases, intolerances);

	List<Alert> result = medicalService.completeReport(history, null, true);

	List<Alert> resultCompare = new ArrayList<>();

	String title = "Restricciones entre componentes químicos";
	boolean aRecetar = false;
	String chemicalComponentRestrictionAlert = "Uno de los componentes químicos Omeprazol o Clopridogel no debería administrarse mientras permanezca vigente el otro";
	Alert alert = new Alert(title, chemicalComponentRestrictionAlert, aRecetar);
	resultCompare.add(resultCompare.size(), alert);

	ArrayList<Alert> resultArray = new ArrayList<Alert>();
	resultArray.addAll(result);
	ArrayList<Alert> resultCompareArray = new ArrayList<Alert>();
	resultCompareArray.addAll(resultCompare);

	System.out.println("testChemicalComponentInteractionFilterRecetados");
	assertEquals(resultArray.size(), resultCompareArray.size());
	for (int i = 0; i < resultArray.size(); i++) {
	    System.out.println("Result: " + resultArray.get(i).getMessage());
	    System.out.println("Expected: " + resultCompareArray.get(i).getMessage());
	    assertEquals(resultArray.get(i).getMessage(), resultCompareArray.get(i).getMessage());
	    assertEquals(resultArray.get(i).getTitle(), resultCompareArray.get(i).getTitle());
	    assertEquals(resultArray.get(i).isaRecetar(), resultCompareArray.get(i).isaRecetar());
	}

    }

    @Test
    public void testAllergyReportRecetados() throws InstanceNotFoundException {

	int edad = 20;
	Sexo sexo = Sexo.FEMENINO;
	boolean embarazo = false;
	boolean lactancia = false;
	int glomerularFiltration = 70;
	boolean liverFailure = false;
	String[] treatmentNames = new String[] { "Penicilina" };
	String[] allergyNames = new String[] { "Penicilina" };
	ArrayList<Treatment> actualTreatments = createActualTreatments(treatmentNames);
	ArrayList<Allergy> allergies = createAllergies(allergyNames);
	ArrayList<Disease> diseases = null;
	ArrayList<Intolerance> intolerances = null;
	MedicalHistory history = createMedicalHistory(edad, sexo, embarazo, lactancia, glomerularFiltration,
		liverFailure, actualTreatments, allergies, diseases, intolerances);

	List<Alert> result = medicalService.completeReport(history, null, true);

	List<Alert> resultCompare = new ArrayList<>();

	String title = "Restricciones con alergias";
	boolean aRecetar = false;
	String allergyReportAlert = "El componente químico (ya recetado) Penicilina no debería recetarse debido a su alergia: Penicilina";
	Alert alert = new Alert(title, allergyReportAlert, aRecetar);
	resultCompare.add(resultCompare.size(), alert);

	ArrayList<Alert> resultArray = new ArrayList<Alert>();
	resultArray.addAll(result);
	ArrayList<Alert> resultCompareArray = new ArrayList<Alert>();
	resultCompareArray.addAll(resultCompare);

	System.out.println("testAllergyReportRecetados");
	assertEquals(resultArray.size(), resultCompareArray.size());
	for (int i = 0; i < resultArray.size(); i++) {
	    System.out.println("Result: " + resultArray.get(i).getMessage());
	    System.out.println("Expected: " + resultCompareArray.get(i).getMessage());
	    assertEquals(resultArray.get(i).getMessage(), resultCompareArray.get(i).getMessage());
	    assertEquals(resultArray.get(i).getTitle(), resultCompareArray.get(i).getTitle());
	    assertEquals(resultArray.get(i).isaRecetar(), resultCompareArray.get(i).isaRecetar());
	}

    }

    @Test
    public void testAllergyReportARecetar() throws InstanceNotFoundException {

	int edad = 20;
	Sexo sexo = Sexo.FEMENINO;
	boolean embarazo = false;
	boolean lactancia = false;
	int glomerularFiltration = 70;
	boolean liverFailure = false;
	String[] treatmentNames = new String[] {};
	String[] allergyNames = new String[] { "Penicilina" };
	ArrayList<Treatment> actualTreatments = createActualTreatments(treatmentNames);
	ArrayList<Allergy> allergies = createAllergies(allergyNames);
	ArrayList<Disease> diseases = null;
	ArrayList<Intolerance> intolerances = null;
	MedicalHistory history = createMedicalHistory(edad, sexo, embarazo, lactancia, glomerularFiltration,
		liverFailure, actualTreatments, allergies, diseases, intolerances);

	Treatment treatment = new Treatment("Penicilina");

	List<Alert> result = medicalService.completeReport(history, treatment, false);

	List<Alert> resultCompare = new ArrayList<>();

	String title = "Restricciones con alergias";
	boolean aRecetar = true;
	String allergyReportAlert = "El componente químico (a recetar) Penicilina no debería recetarse debido a su alergia: Penicilina";
	Alert alert = new Alert(title, allergyReportAlert, aRecetar);
	resultCompare.add(resultCompare.size(), alert);

	ArrayList<Alert> resultArray = new ArrayList<Alert>();
	resultArray.addAll(result);
	ArrayList<Alert> resultCompareArray = new ArrayList<Alert>();
	resultCompareArray.addAll(resultCompare);

	System.out.println("testAllergyReportARecetar");
	assertEquals(resultArray.size(), resultCompareArray.size());
	for (int i = 0; i < resultArray.size(); i++) {
	    System.out.println("Result: " + resultArray.get(i).getMessage());
	    System.out.println("Expected: " + resultCompareArray.get(i).getMessage());
	    assertEquals(resultArray.get(i).getMessage(), resultCompareArray.get(i).getMessage());
	    assertEquals(resultArray.get(i).getTitle(), resultCompareArray.get(i).getTitle());
	    assertEquals(resultArray.get(i).isaRecetar(), resultCompareArray.get(i).isaRecetar());
	}

    }

    @Test
    public void testIntoleranceReportRecetados() throws InstanceNotFoundException {

	int edad = 20;
	Sexo sexo = Sexo.FEMENINO;
	boolean embarazo = false;
	boolean lactancia = false;
	int glomerularFiltration = 70;
	boolean liverFailure = false;
	String[] treatmentNames = new String[] { "Ibuprofeno" };
	String[] allergyNames = new String[] {};
	String[] intoleranceNames = new String[] { "Gastritis" };
	ArrayList<Treatment> actualTreatments = createActualTreatments(treatmentNames);
	ArrayList<Allergy> allergies = createAllergies(allergyNames);
	ArrayList<Disease> diseases = null;
	ArrayList<Intolerance> intolerances = createIntolerances(intoleranceNames);
	MedicalHistory history = createMedicalHistory(edad, sexo, embarazo, lactancia, glomerularFiltration,
		liverFailure, actualTreatments, allergies, diseases, intolerances);

	List<Alert> result = medicalService.completeReport(history, null, true);

	List<Alert> resultCompare = new ArrayList<>();

	String title = "Restricciones con intolerancias";
	boolean aRecetar = false;
	String intoleranceReportAlert = "El componente químico (ya recetado) Ibuprofeno no debería recetarse debido a su intolerancia: Gastritis";
	Alert alert = new Alert(title, intoleranceReportAlert, aRecetar);
	resultCompare.add(resultCompare.size(), alert);

	ArrayList<Alert> resultArray = new ArrayList<Alert>();
	resultArray.addAll(result);
	ArrayList<Alert> resultCompareArray = new ArrayList<Alert>();
	resultCompareArray.addAll(resultCompare);

	System.out.println("testIntoleranceReportRecetados");
	assertEquals(resultArray.size(), resultCompareArray.size());
	for (int i = 0; i < resultArray.size(); i++) {
	    System.out.println("Result: " + resultArray.get(i).getMessage());
	    System.out.println("Expected: " + resultCompareArray.get(i).getMessage());
	    assertEquals(resultArray.get(i).getMessage(), resultCompareArray.get(i).getMessage());
	    assertEquals(resultArray.get(i).getTitle(), resultCompareArray.get(i).getTitle());
	    assertEquals(resultArray.get(i).isaRecetar(), resultCompareArray.get(i).isaRecetar());
	}

    }

    @Test
    public void testIntoleranceReportARecetar() throws InstanceNotFoundException {

	int edad = 20;
	Sexo sexo = Sexo.FEMENINO;
	boolean embarazo = false;
	boolean lactancia = false;
	int glomerularFiltration = 70;
	boolean liverFailure = false;
	String[] treatmentNames = new String[] {};
	String[] allergyNames = new String[] {};
	String[] intoleranceNames = new String[] { "Gastritis" };
	ArrayList<Treatment> actualTreatments = createActualTreatments(treatmentNames);
	ArrayList<Allergy> allergies = createAllergies(allergyNames);
	ArrayList<Disease> diseases = null;
	ArrayList<Intolerance> intolerances = createIntolerances(intoleranceNames);
	MedicalHistory history = createMedicalHistory(edad, sexo, embarazo, lactancia, glomerularFiltration,
		liverFailure, actualTreatments, allergies, diseases, intolerances);

	Treatment treatment = new Treatment("Ibuprofeno");

	List<Alert> result = medicalService.completeReport(history, treatment, true);

	List<Alert> resultCompare = new ArrayList<>();

	String title = "Restricciones con intolerancias";
	boolean aRecetar = true;
	String intoleranceReportAlert = "El componente químico (a recetar) Ibuprofeno no debería recetarse debido a su intolerancia: Gastritis";
	Alert alert = new Alert(title, intoleranceReportAlert, aRecetar);
	resultCompare.add(resultCompare.size(), alert);

	ArrayList<Alert> resultArray = new ArrayList<Alert>();
	resultArray.addAll(result);
	ArrayList<Alert> resultCompareArray = new ArrayList<Alert>();
	resultCompareArray.addAll(resultCompare);

	System.out.println("testIntoleranceReportARecetar");
	assertEquals(resultArray.size(), resultCompareArray.size());
	for (int i = 0; i < resultArray.size(); i++) {
	    System.out.println("Result: " + resultArray.get(i).getMessage());
	    System.out.println("Expected: " + resultCompareArray.get(i).getMessage());
	    assertEquals(resultArray.get(i).getMessage(), resultCompareArray.get(i).getMessage());
	    assertEquals(resultArray.get(i).getTitle(), resultCompareArray.get(i).getTitle());
	    assertEquals(resultArray.get(i).isaRecetar(), resultCompareArray.get(i).isaRecetar());
	}

    }

    @Test
    public void testDiseaseReportRecetados() throws InstanceNotFoundException {

	int edad = 20;
	Sexo sexo = Sexo.FEMENINO;
	boolean embarazo = false;
	boolean lactancia = false;
	int glomerularFiltration = 70;
	boolean liverFailure = false;
	String[] treatmentNames = new String[] { "Aspirina" };
	String[] allergyNames = new String[] {};
	String[] intoleranceNames = new String[] {};
	String[] diseaseNames = new String[] { "Sindrome de Reye" };
	ArrayList<Treatment> actualTreatments = createActualTreatments(treatmentNames);
	ArrayList<Allergy> allergies = createAllergies(allergyNames);
	ArrayList<Disease> diseases = createDiseases(diseaseNames);
	ArrayList<Intolerance> intolerances = createIntolerances(intoleranceNames);
	MedicalHistory history = createMedicalHistory(edad, sexo, embarazo, lactancia, glomerularFiltration,
		liverFailure, actualTreatments, allergies, diseases, intolerances);

	List<Alert> result = medicalService.completeReport(history, null, true);

	List<Alert> resultCompare = new ArrayList<>();

	String title = "Restricciones con enfermedades";
	boolean aRecetar = false;
	String diseaseReportAlert = "El componente químico (ya recetado) Ácido acetilsalicílico no debería recetarse debido a su enfermedad: Sindrome de Reye";
	Alert alert = new Alert(title, diseaseReportAlert, aRecetar);
	resultCompare.add(resultCompare.size(), alert);

	ArrayList<Alert> resultArray = new ArrayList<Alert>();
	resultArray.addAll(result);
	ArrayList<Alert> resultCompareArray = new ArrayList<Alert>();
	resultCompareArray.addAll(resultCompare);

	System.out.println("testDiseaseReportRecetados");
	assertEquals(resultArray.size(), resultCompareArray.size());
	for (int i = 0; i < resultArray.size(); i++) {
	    System.out.println("Result: " + resultArray.get(i).getMessage());
	    System.out.println("Expected: " + resultCompareArray.get(i).getMessage());
	    assertEquals(resultArray.get(i).getMessage(), resultCompareArray.get(i).getMessage());
	    assertEquals(resultArray.get(i).getTitle(), resultCompareArray.get(i).getTitle());
	    assertEquals(resultArray.get(i).isaRecetar(), resultCompareArray.get(i).isaRecetar());
	}

    }

    @Test
    public void testDiseaseReportARecetar() throws InstanceNotFoundException {

	int edad = 20;
	Sexo sexo = Sexo.FEMENINO;
	boolean embarazo = false;
	boolean lactancia = false;
	int glomerularFiltration = 70;
	boolean liverFailure = false;
	String[] treatmentNames = new String[] {};
	String[] allergyNames = new String[] {};
	String[] intoleranceNames = new String[] {};
	String[] diseaseNames = new String[] { "Sindrome de Reye" };
	ArrayList<Treatment> actualTreatments = createActualTreatments(treatmentNames);
	ArrayList<Allergy> allergies = createAllergies(allergyNames);
	ArrayList<Disease> diseases = createDiseases(diseaseNames);
	ArrayList<Intolerance> intolerances = createIntolerances(intoleranceNames);
	MedicalHistory history = createMedicalHistory(edad, sexo, embarazo, lactancia, glomerularFiltration,
		liverFailure, actualTreatments, allergies, diseases, intolerances);

	Treatment treatment = new Treatment("Aspirina");

	List<Alert> result = medicalService.completeReport(history, treatment, true);

	List<Alert> resultCompare = new ArrayList<>();

	String title = "Restricciones con enfermedades";
	boolean aRecetar = true;
	String diseaseReportAlert = "El componente químico (a recetar) Ácido acetilsalicílico no debería recetarse debido a su enfermedad: Sindrome de Reye";
	Alert alert = new Alert(title, diseaseReportAlert, aRecetar);
	resultCompare.add(resultCompare.size(), alert);

	ArrayList<Alert> resultArray = new ArrayList<Alert>();
	resultArray.addAll(result);
	ArrayList<Alert> resultCompareArray = new ArrayList<Alert>();
	resultCompareArray.addAll(resultCompare);

	System.out.println("testDiseaseReportARecetar");
	assertEquals(resultArray.size(), resultCompareArray.size());
	for (int i = 0; i < resultArray.size(); i++) {
	    System.out.println("Result: " + resultArray.get(i).getMessage());
	    System.out.println("Expected: " + resultCompareArray.get(i).getMessage());
	    assertEquals(resultArray.get(i).getMessage(), resultCompareArray.get(i).getMessage());
	    assertEquals(resultArray.get(i).getTitle(), resultCompareArray.get(i).getTitle());
	    assertEquals(resultArray.get(i).isaRecetar(), resultCompareArray.get(i).isaRecetar());
	}

    }

    @Test
    public void testCompleteReport() throws InstanceNotFoundException {

	int edad = 20;
	Sexo sexo = Sexo.FEMENINO;
	boolean embarazo = false;
	boolean lactancia = false;
	int glomerularFiltration = 10;
	boolean liverFailure = false;
	String[] treatmentNames = new String[] { "Penicilina", "Ibuprofeno", "Losec", "Plavix", "Januvia", "Diflucan",
		"VFEND" };
	String[] allergyNames = new String[] { "Penicilina" };
	String[] intoleranceNames = new String[] { "Gastritis" };
	String[] diseaseNames = new String[] { "Sindrome de Reye" };
	ArrayList<Treatment> actualTreatments = createActualTreatments(treatmentNames);
	ArrayList<Allergy> allergies = createAllergies(allergyNames);
	ArrayList<Disease> diseases = createDiseases(diseaseNames);
	ArrayList<Intolerance> intolerances = createIntolerances(intoleranceNames);
	MedicalHistory history = createMedicalHistory(edad, sexo, embarazo, lactancia, glomerularFiltration,
		liverFailure, actualTreatments, allergies, diseases, intolerances);

	Treatment treatment = new Treatment("Aspirina");

	List<Alert> result = medicalService.completeReport(history, treatment, true);

	List<Alert> resultCompare = new ArrayList<>();

	String title;
	boolean aRecetar = false;
	Alert alert;

	title = "Grupo terapeutico";
	String therapeuticGroupAlert2 = "Los grupos terapeuticos de los componentes (ya recetados): "
		+ "Fluconazol y Voriconazol son de la misma familia: Antifúngicos";
	alert = new Alert(title, therapeuticGroupAlert2, aRecetar);
	resultCompare.add(resultCompare.size(), alert);

	title = "Restricciones genericas";
	String genericReportAlert = "El componente químico (ya recetado) Sitaglipina necesita un ajuste de dósis debido a que el nivel glomerular del "
		+ "paciente es menor a 30 (Incontinencia renal grave)";
	alert = new Alert(title, genericReportAlert, aRecetar);
	resultCompare.add(resultCompare.size(), alert);

	String genericReportAlert2 = "El componente químico (ya recetado) Fluconazol necesita un ajuste de dósis debido a que el nivel glomerular del "
		+ "paciente es menor a 60 (Incontinencia renal leve)";
	alert = new Alert(title, genericReportAlert2, aRecetar);
	resultCompare.add(resultCompare.size(), alert);

	title = "Restricciones entre componentes químicos";
	String chemicalComponentRestrictionAlert = "Uno de los componentes químicos Omeprazol o Clopridogel no debería administrarse mientras permanezca vigente el otro";
	alert = new Alert(title, chemicalComponentRestrictionAlert, aRecetar);
	resultCompare.add(resultCompare.size(), alert);

	title = "Restricciones con alergias";
	String allergyReportAlert = "El componente químico (ya recetado) Penicilina no debería recetarse debido a su alergia: Penicilina";
	alert = new Alert(title, allergyReportAlert, aRecetar);
	resultCompare.add(resultCompare.size(), alert);

	title = "Restricciones con intolerancias";
	String intoleranceReportAlert = "El componente químico (ya recetado) Ibuprofeno no debería recetarse debido a su intolerancia: Gastritis";
	alert = new Alert(title, intoleranceReportAlert, aRecetar);
	resultCompare.add(resultCompare.size(), alert);

	title = "Restricciones con enfermedades";
	aRecetar = true;
	String diseaseReportAlert = "El componente químico (a recetar) Ácido acetilsalicílico no debería recetarse debido a su enfermedad: Sindrome de Reye";
	alert = new Alert(title, diseaseReportAlert, aRecetar);
	resultCompare.add(resultCompare.size(), alert);

	ArrayList<Alert> resultArray = new ArrayList<Alert>();
	resultArray.addAll(result);
	ArrayList<Alert> resultCompareArray = new ArrayList<Alert>();
	resultCompareArray.addAll(resultCompare);

	System.out.println("testDiseaseReportARecetar");
	assertEquals(resultArray.size(), resultCompareArray.size());
	for (int i = 0; i < resultArray.size(); i++) {
	    System.out.println("Result: " + resultArray.get(i).getMessage());
	    System.out.println("Expected: " + resultCompareArray.get(i).getMessage());
	    assertEquals(resultArray.get(i).getMessage(), resultCompareArray.get(i).getMessage());
	    assertEquals(resultArray.get(i).getTitle(), resultCompareArray.get(i).getTitle());
	    assertEquals(resultArray.get(i).isaRecetar(), resultCompareArray.get(i).isaRecetar());
	}

    }

}
