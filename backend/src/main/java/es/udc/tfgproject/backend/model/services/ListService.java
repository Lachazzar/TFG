package es.udc.tfgproject.backend.model.services;

import java.util.List;

import es.udc.tfgproject.backend.model.entities.disease_intolerance_allergy.Allergy;
import es.udc.tfgproject.backend.model.entities.disease_intolerance_allergy.Disease;
import es.udc.tfgproject.backend.model.entities.disease_intolerance_allergy.Intolerance;

public interface ListService {

    List<Allergy> listAllAllergies();

    List<Intolerance> listAllIntolerances();

    List<Disease> listAllDiseases();

}
