package es.udc.tfgproject.backend.model.entities.restrictions;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import es.udc.tfgproject.backend.model.entities.disease_intolerance_allergy.Disease;

@Entity
public class DiseaseRestriction {
    private Long id;
    private String restrictionName;
    private String code;
    private Disease disease;

    public DiseaseRestriction() {
    }

    public DiseaseRestriction(Long id, String restrictionName, String code) {
	this.id = id;
	this.restrictionName = restrictionName;
	this.code = code;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public String getRestrictionName() {
	return restrictionName;
    }

    public void setRestrictionName(String restrictionName) {
	this.restrictionName = restrictionName;
    }

    public String getCode() {
	return code;
    }

    public void setCode(String code) {
	this.code = code;
    }

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "diseaseId")
    public Disease getDisease() {
	return disease;
    }

    public void setDisease(Disease disease) {
	this.disease = disease;
    }

}
