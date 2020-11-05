package es.udc.tfgproject.backend.model.entities.medicamentInformation;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Medicament {

    private Long id;
    private String medicamentName;
    private Set<CommercialMedicament> commercialMedicaments;
    private Set<ChemicalComponent> chemicalComponents;

    public Medicament() {
    }

    public Medicament(String medicamentName) {
	this.medicamentName = medicamentName;
	this.setCommercialMedicaments(new HashSet<>());
    }

    public Medicament(String medicamentName, Set<CommercialMedicament> commercialMedicaments) {
	this.medicamentName = medicamentName;
	this.setCommercialMedicaments(new HashSet<>());
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public String getMedicamentName() {
	return medicamentName;
    }

    public void setMedicamentName(String medicamentName) {
	this.medicamentName = medicamentName;
    }

    @OneToMany(mappedBy = "medicament")
    public Set<CommercialMedicament> getCommercialMedicaments() {
	return commercialMedicaments;
    }

    public void setCommercialMedicaments(Set<CommercialMedicament> commercialMedicaments) {
	this.commercialMedicaments = commercialMedicaments;
    }

    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH })
    @JoinTable(name = "MedicamentChemicalComponent", joinColumns = {
	    @JoinColumn(name = "medicamentId") }, inverseJoinColumns = { @JoinColumn(name = "componentId") })
    public Set<ChemicalComponent> getChemicalComponents() {
	return chemicalComponents;
    }

    public void setChemicalComponents(Set<ChemicalComponent> chemicalComponents) {
	this.chemicalComponents = chemicalComponents;
    }

}