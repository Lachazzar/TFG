package es.udc.tfgproject.backend.model.entities.restrictions;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import es.udc.tfgproject.backend.model.entities.medicamentInformation.ChemicalComponent;

@Entity
public class ComponentRestriction {
    private Long id;
    private String restrictionName;
    private String code;
    private Set<ChemicalComponent> chemicalComponentPair;

    public ComponentRestriction() {
    }

    public ComponentRestriction(Long id, String restrictionName, String code) {
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

    @ManyToMany(mappedBy = "componentRestrictions")
    public Set<ChemicalComponent> getChemicalComponentPair() {
	return chemicalComponentPair;
    }

    public void setChemicalComponentPair(Set<ChemicalComponent> chemicalComponentPair) {
	this.chemicalComponentPair = chemicalComponentPair;
    }

}
