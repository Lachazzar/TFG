package es.udc.tfgproject.backend.model.services;

import java.util.List;

import es.udc.tfgproject.backend.model.entities.Alert;
import es.udc.tfgproject.backend.model.entities.medicamentInformation.ChemicalComponent;
import es.udc.tfgproject.backend.model.entities.patientInformation.MedicalHistory;
import es.udc.tfgproject.backend.model.entities.patientInformation.Treatment;
import es.udc.tfgproject.backend.model.exceptions.InstanceNotFoundException;

public interface MedicalService {

    List<Alert> completeReport(MedicalHistory history, Treatment treatment, boolean comprobarRecetados)
	    throws InstanceNotFoundException;

    List<ChemicalComponent> actualChemicalComponentList(MedicalHistory history) throws InstanceNotFoundException;

    List<ChemicalComponent> newChemicalComponentList(Treatment treatment) throws InstanceNotFoundException;

}
