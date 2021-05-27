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

import es.udc.tfgproject.backend.model.entities.restrictions.AllergyRestriction;
import es.udc.tfgproject.backend.model.entities.restrictions.ComponentRestriction;
import es.udc.tfgproject.backend.model.entities.restrictions.DiseaseRestriction;
import es.udc.tfgproject.backend.model.entities.restrictions.IntoleranceRestriction;
import es.udc.tfgproject.backend.model.entities.restrictions.RegularRestriction;

@Entity
public class ChemicalComponent {

    private Long id;
    private String componentName;
    private Family family;
    private Set<RegularRestriction> regularRestrictions;
    private Set<DiseaseRestriction> diseaseRestrictions;
    private Set<AllergyRestriction> allergyRestrictions;
    private Set<IntoleranceRestriction> intoleranceRestrictions;
    private Set<ComponentRestriction> componentRestrictions;

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
    @JoinTable(name = "ChemicalComponent_RegularRestriction", joinColumns = @JoinColumn(name = "chemicalComponent_id"), inverseJoinColumns = @JoinColumn(name = "regularRestriction_id"))
    public Set<RegularRestriction> getRegularRestrictions() {
	return regularRestrictions;
    }

    public void setRegularRestrictions(Set<RegularRestriction> regularRestrictions) {
	this.regularRestrictions = regularRestrictions;
    }

    @ManyToMany
    @JoinTable(name = "ChemicalComponent_DiseaseRestriction", joinColumns = @JoinColumn(name = "chemicalComponent_id"), inverseJoinColumns = @JoinColumn(name = "diseaseRestriction_id"))
    public Set<DiseaseRestriction> getDiseaseRestrictions() {
	return diseaseRestrictions;
    }

    public void setDiseaseRestrictions(Set<DiseaseRestriction> diseaseRestrictions) {
	this.diseaseRestrictions = diseaseRestrictions;
    }

    @ManyToMany
    @JoinTable(name = "ChemicalComponent_AllergyRestriction", joinColumns = @JoinColumn(name = "chemicalComponent_id"), inverseJoinColumns = @JoinColumn(name = "allergyRestriction_id"))
    public Set<AllergyRestriction> getAllergyRestrictions() {
	return allergyRestrictions;
    }

    public void setAllergyRestrictions(Set<AllergyRestriction> allergyRestrictions) {
	this.allergyRestrictions = allergyRestrictions;
    }

    @ManyToMany
    @JoinTable(name = "ChemicalComponent_IntoleranceRestriction", joinColumns = @JoinColumn(name = "chemicalComponent_id"), inverseJoinColumns = @JoinColumn(name = "intoleranceRestriction_id"))
    public Set<IntoleranceRestriction> getIntoleranceRestrictions() {
	return intoleranceRestrictions;
    }

    public void setIntoleranceRestrictions(Set<IntoleranceRestriction> intoleranceRestrictions) {
	this.intoleranceRestrictions = intoleranceRestrictions;
    }

    @ManyToMany
    @JoinTable(name = "ChemicalComponent_ComponentRestriction", joinColumns = @JoinColumn(name = "chemicalComponent_id"), inverseJoinColumns = @JoinColumn(name = "componentRestriction_id"))
    public Set<ComponentRestriction> getComponentRestrictions() {
	return componentRestrictions;
    }

    public void setComponentRestrictions(Set<ComponentRestriction> componentRestrictions) {
	this.componentRestrictions = componentRestrictions;
    }

}
