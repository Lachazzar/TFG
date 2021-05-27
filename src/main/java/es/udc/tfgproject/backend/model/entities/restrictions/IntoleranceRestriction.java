package es.udc.tfgproject.backend.model.entities.restrictions;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import es.udc.tfgproject.backend.model.entities.disease_intolerance_allergy.Intolerance;

@Entity
public class IntoleranceRestriction {
    private Long id;
    private String restrictionName;
    private String code;
    private Intolerance intolerance;

    public IntoleranceRestriction() {
    }

    public IntoleranceRestriction(Long id, String restrictionName, String code) {
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
    @JoinColumn(name = "intoleranceId")
    public Intolerance getIntolerance() {
	return intolerance;
    }

    public void setIntolerance(Intolerance intolerance) {
	this.intolerance = intolerance;
    }

}
