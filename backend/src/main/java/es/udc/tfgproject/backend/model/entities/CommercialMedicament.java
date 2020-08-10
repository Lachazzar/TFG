package es.udc.tfgproject.backend.model.entities;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class CommercialMedicament {

    private Long id;
    private String commercialMedicamentName;
    private Medicament medicament;

    public CommercialMedicament() {
    }

    public CommercialMedicament(String commercialMedicamentName, Medicament medicament) {
	this.commercialMedicamentName = commercialMedicamentName;
	this.medicament = medicament;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public String getCommercialMedicamentName() {
	return commercialMedicamentName;
    }

    public void setCommercialMedicamentName(String commercialMedicamentName) {
	this.commercialMedicamentName = commercialMedicamentName;
    }

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "medicamentId")
    public Medicament getMedicament() {
	return medicament;
    }

    public void setMedicament(Medicament medicament) {
	this.medicament = medicament;
    }

}