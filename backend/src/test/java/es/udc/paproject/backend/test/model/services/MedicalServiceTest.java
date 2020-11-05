package es.udc.paproject.backend.test.model.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import es.udc.tfgproject.backend.model.entities.User;
import es.udc.tfgproject.backend.model.entities.disease_intolerance_allergy.Allergy;
import es.udc.tfgproject.backend.model.entities.disease_intolerance_allergy.Disease;
import es.udc.tfgproject.backend.model.entities.disease_intolerance_allergy.Intolerance;
import es.udc.tfgproject.backend.model.entities.patientInformation.Anticoagulated;
import es.udc.tfgproject.backend.model.entities.patientInformation.MedicalHistory;
import es.udc.tfgproject.backend.model.entities.patientInformation.MedicalHistory.Sexo;
import es.udc.tfgproject.backend.model.entities.patientInformation.Treatment;
import es.udc.tfgproject.backend.model.exceptions.InstanceNotFoundException;
import es.udc.tfgproject.backend.model.services.MedicalService;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class MedicalServiceTest {

    private final Long NON_EXISTENT_ID = new Long(-1);

    @Autowired
    private MedicalService medicalService;

    private MedicalHistory createMedicalHistory(int edad, Sexo sexo, boolean embarazo, boolean lactancia,
	    Anticoagulated anticoagulated, int glomerularFiltration, boolean liverFailure,
	    Set<Treatment> actualTreatments, Set<Allergy> allergies, Set<Disease> diseases,
	    Set<Intolerance> intolerances) {
	MedicalHistory history = new MedicalHistory(edad, sexo, embarazo, lactancia, anticoagulated,
		glomerularFiltration, liverFailure, actualTreatments, allergies, diseases, intolerances);
	return history;
    }

    @Test
    public void testtherapeuticGroupFilter() throws InstanceNotFoundException {

	User user = createUser("user");

	medicalService.completeReport(history, treatment, false);

	User loggedInUser = userService.loginFromId(user.getId());

	assertEquals(user, loggedInUser);
	assertEquals(User.RoleType.USER, user.getRole());

    }
}
