package es.udc.tfgproject.backend.model.entities.restrictions;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import es.udc.tfgproject.backend.model.entities.disease_intolerance_allergy.Allergy;

@Entity
public class AllergyRestriction {
    private Long id;
    private String restrictionName;
    private String code;
    private Allergy allergy;

    public AllergyRestriction() {
    }

    public AllergyRestriction(Long id, String restrictionName, String code) {
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
    @JoinColumn(name = "allergyId")
    public Allergy getAllergy() {
	return allergy;
    }

    public void setAllergy(Allergy allergy) {
	this.allergy = allergy;
    }

}
