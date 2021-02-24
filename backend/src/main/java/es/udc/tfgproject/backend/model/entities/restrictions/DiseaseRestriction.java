package es.udc.tfgproject.backend.model.entities.restrictions;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import es.udc.tfgproject.backend.model.entities.disease_intolerance_allergy.Disease;

@Entity
public class DiseaseRestriction extends RegularRestriction {

    private Disease disease;

    public DiseaseRestriction() {

    }

    public DiseaseRestriction(Long id, String name, String code, Disease disease) {
	super(id, name, code);
	this.disease = disease;
    }

    @OneToOne
    @JoinColumn(name = "diseaseId")
    public Disease getDisease() {
	return disease;
    }

    public void setDisease(Disease disease) {
	this.disease = disease;
    }

}
