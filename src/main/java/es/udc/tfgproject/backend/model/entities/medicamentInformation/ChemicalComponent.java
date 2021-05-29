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

import es.udc.tfgproject.backend.model.entities.disease_intolerance_allergy.Allergy;
import es.udc.tfgproject.backend.model.entities.disease_intolerance_allergy.Disease;
import es.udc.tfgproject.backend.model.entities.disease_intolerance_allergy.Intolerance;
import es.udc.tfgproject.backend.model.entities.restrictions.RegularRestriction;

@Entity
public class ChemicalComponent {

    private Long id;
    private String componentName;
    private Family family;
    private Set<RegularRestriction> regularRestrictions;
    private Set<Disease> diseases;
    private Set<Allergy> allergies;
    private Set<Intolerance> intolerances;
    private Set<ChemicalComponent> chemicalComponents;

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
    @JoinTable(name = "ChemicalComponent_Disease", joinColumns = @JoinColumn(name = "chemicalComponent_id"), inverseJoinColumns = @JoinColumn(name = "disease_id"))
    public Set<Disease> getDiseases() {
	return diseases;
    }

    public void setDiseases(Set<Disease> diseases) {
	this.diseases = diseases;
    }

    @ManyToMany
    @JoinTable(name = "ChemicalComponent_Allergy", joinColumns = @JoinColumn(name = "chemicalComponent_id"), inverseJoinColumns = @JoinColumn(name = "allergy_id"))
    public Set<Allergy> getAllergies() {
	return allergies;
    }

    public void setAllergies(Set<Allergy> allergies) {
	this.allergies = allergies;
    }

    @ManyToMany
    @JoinTable(name = "ChemicalComponent_Intolerance", joinColumns = @JoinColumn(name = "chemicalComponent_id"), inverseJoinColumns = @JoinColumn(name = "intolerance_id"))
    public Set<Intolerance> getIntolerances() {
	return intolerances;
    }

    public void setIntolerances(Set<Intolerance> intolerances) {
	this.intolerances = intolerances;
    }

    @ManyToMany
    @JoinTable(name = "ChemicalComponent_ChemicalComponent", joinColumns = @JoinColumn(name = "chemicalComponent_id"), inverseJoinColumns = @JoinColumn(name = "chemicalComponent2_id"))
    public Set<ChemicalComponent> getChemicalComponents() {
	return chemicalComponents;
    }

    public void setChemicalComponents(Set<ChemicalComponent> chemicalComponents) {
	this.chemicalComponents = chemicalComponents;
    }

}
