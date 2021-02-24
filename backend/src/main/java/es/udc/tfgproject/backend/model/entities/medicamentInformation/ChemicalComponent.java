package es.udc.tfgproject.backend.model.entities.medicamentInformation;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import es.udc.tfgproject.backend.model.entities.relationInformation.ChemicalComponentRegularRestriction;

@Entity
public class ChemicalComponent {

    private Long id;
    private String componentName;
    private Family family;
    private Set<ChemicalComponentRegularRestriction> chemicalComponentRegularRestrictions;

//    private Set<AllergyRestriction> allergyRestrictions;
//    private Set<ComponentRestriction> componentRestrictions;
//    private Set<IntoleranceRestriction> intoleranceRestrictions;
//    private Set<DiseaseRestriction> diseaseRestrictions;

    public ChemicalComponent() {
    }

    public ChemicalComponent(String componentName, Family family) {
	this.componentName = componentName;
	this.family = family;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public String getComponentName() {
	return componentName;
    }

    public void setComponentName(String componentName) {
	this.componentName = componentName;
    }

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "familyId")
    public Family getFamily() {
	return family;
    }

    public void setFamily(Family family) {
	this.family = family;
    }

    @ManyToMany
    @JoinTable(name = "ChemicalComponentRegularRestriction", joinColumns = @JoinColumn(name = "componentId"), inverseJoinColumns = @JoinColumn(name = "regularRestrictionId"))
    public final Set<ChemicalComponentRegularRestriction> getChemicalComponentRegularRestrictions() {
	return chemicalComponentRegularRestrictions;
    }

    public final void setChemicalComponentRegularRestrictions(
	    Set<ChemicalComponentRegularRestriction> chemicalComponentRegularRestrictions) {
	this.chemicalComponentRegularRestrictions = chemicalComponentRegularRestrictions;
    }

//
//    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH })
//    @JoinTable(name = "ChemicalComponentAllergyRestriction", joinColumns = {
//	    @JoinColumn(name = "componentId") }, inverseJoinColumns = { @JoinColumn(name = "allergyRestrictionId") })
//    public Set<AllergyRestriction> getAllergyRestrictions() {
//	return allergyRestrictions;
//    }
//
//    public void setAllergyRestrictions(Set<AllergyRestriction> allergyRestrictions) {
//	this.allergyRestrictions = allergyRestrictions;
//    }
//
//    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH })
//    @JoinTable(name = "ChemicalComponentComponentRestriction", joinColumns = {
//	    @JoinColumn(name = "componentId") }, inverseJoinColumns = { @JoinColumn(name = "componentRestrictitonId") })
//    public Set<ComponentRestriction> getComponentRestrictions() {
//	return componentRestrictions;
//    }
//
//    public void setComponentRestrictions(Set<ComponentRestriction> componentRestrictions) {
//	this.componentRestrictions = componentRestrictions;
//    }
//
//    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH })
//    @JoinTable(name = "ChemicalComponentIntoleranceRestriction", joinColumns = {
//	    @JoinColumn(name = "componentId") }, inverseJoinColumns = {
//		    @JoinColumn(name = "intoleranceRestrictionId") })
//    public Set<IntoleranceRestriction> getIntoleranceRestrictions() {
//	return intoleranceRestrictions;
//    }
//
//    public void setIntoleranceRestrictions(Set<IntoleranceRestriction> intoleranceRestrictions) {
//	this.intoleranceRestrictions = intoleranceRestrictions;
//    }
//
//    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH })
//    @JoinTable(name = "ChemicalComponentDiseaseRestriction", joinColumns = {
//	    @JoinColumn(name = "componentId") }, inverseJoinColumns = { @JoinColumn(name = "diseaseRestrictionId") })
//    public Set<DiseaseRestriction> getDiseaseRestrictions() {
//	return diseaseRestrictions;
//    }
//
//    public void setDiseaseRestrictions(Set<DiseaseRestriction> diseaseRestrictions) {
//	this.diseaseRestrictions = diseaseRestrictions;
//    }

}
