package es.udc.tfgproject.backend.model.entities;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class DiseaseRestriction extends Restriction {

    private Disease disease;

    public DiseaseRestriction(Long id, String name, String code, Severity severity, Disease disease) {
	super(id, name, code, severity);
	this.disease = disease;
    }

    @OneToOne
    @JoinColumn(name = "diseaseId")
    public final Disease getDisease() {
	return disease;
    }

    public final void setDisease(Disease disease) {
	this.disease = disease;
    }

}
