package es.udc.tfgproject.backend.model.entities.medicamentInformation;

import java.util.HashSet;
import java.util.Set;

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
    private String name;
    private Set<CommercialMedicament> commercialMedicaments;
    private Set<ChemicalComponent> chemicalComponents;

    public Medicament() {
    }

    public Medicament(String name) {
	this.name = name;
	this.setCommercialMedicaments(new HashSet<>());
    }

    public Medicament(String name, Set<CommercialMedicament> commercialMedicaments) {
	this.name = name;
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

    public String getname() {
	return name;
    }

    public void setname(String name) {
	this.name = name;
    }

    @OneToMany(mappedBy = "medicament")
    public Set<CommercialMedicament> getCommercialMedicaments() {
	return commercialMedicaments;
    }

    public void setCommercialMedicaments(Set<CommercialMedicament> commercialMedicaments) {
	this.commercialMedicaments = commercialMedicaments;
    }

    @ManyToMany
    @JoinTable(name = "Medicament_ChemicalComponent", joinColumns = {
	    @JoinColumn(name = "medicament_id") }, inverseJoinColumns = { @JoinColumn(name = "component_id") })
    public Set<ChemicalComponent> getChemicalComponents() {
	return chemicalComponents;
    }

    public void setChemicalComponents(Set<ChemicalComponent> chemicalComponents) {
	this.chemicalComponents = chemicalComponents;
    }

}