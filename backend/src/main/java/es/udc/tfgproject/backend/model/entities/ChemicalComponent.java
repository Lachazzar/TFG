package es.udc.tfgproject.backend.model.entities;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class ChemicalComponent {

    private Long id;
    private String componentName;
    private Family family;
    private Set<Restriction> restrictions;
    private Set<AllergyRestriction> allergyRestrictions;
    private Set<ComponentRestriction> componentRestrictions;
    private Set<IntoleranceRestriction> intoleranceRestrictions;
    private Set<DiseaseRestriction> diseaseRestrictions;

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

    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH })
    @JoinTable(name = "ChemicalComponentRegularRestriction", joinColumns = {
	    @JoinColumn(name = "componentId") }, inverseJoinColumns = { @JoinColumn(name = "regularRestrictionId") })
    public final Set<Restriction> getRestrictions() {
	return restrictions;
    }

    public final void setRestrictions(Set<Restriction> restrictions) {
	this.restrictions = restrictions;
    }

    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH })
    @JoinTable(name = "ChemicalComponentAllergyRestriction", joinColumns = {
	    @JoinColumn(name = "componentId") }, inverseJoinColumns = { @JoinColumn(name = "allergyRestrictionId") })
    public final Set<AllergyRestriction> getAllergyRestrictions() {
	return allergyRestrictions;
    }

    public final void setAllergyRestrictions(Set<AllergyRestriction> allergyRestrictions) {
	this.allergyRestrictions = allergyRestrictions;
    }

    @OneToMany(mappedBy = "chemicalComponent")
    public final Set<ComponentRestriction> getComponentRestrictions() {
	return componentRestrictions;
    }

    public final void setComponentRestrictions(Set<ComponentRestriction> componentRestrictions) {
	this.componentRestrictions = componentRestrictions;
    }

    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH })
    @JoinTable(name = "ChemicalComponentIntoleranceRestriction", joinColumns = {
	    @JoinColumn(name = "componentId") }, inverseJoinColumns = {
		    @JoinColumn(name = "intoleranceRestrictionId") })
    public final Set<IntoleranceRestriction> getIntoleranceRestrictions() {
	return intoleranceRestrictions;
    }

    public final void setIntoleranceRestrictions(Set<IntoleranceRestriction> intoleranceRestrictions) {
	this.intoleranceRestrictions = intoleranceRestrictions;
    }

    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH })
    @JoinTable(name = "ChemicalComponentDiseaseRestriction", joinColumns = {
	    @JoinColumn(name = "componentId") }, inverseJoinColumns = { @JoinColumn(name = "diseaseRestrictionId") })
    public final Set<DiseaseRestriction> getDiseaseRestrictions() {
	return diseaseRestrictions;
    }

    public final void setDiseaseRestrictions(Set<DiseaseRestriction> diseaseRestrictions) {
	this.diseaseRestrictions = diseaseRestrictions;
    }

}
