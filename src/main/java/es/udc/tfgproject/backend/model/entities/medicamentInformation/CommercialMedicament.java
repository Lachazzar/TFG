package es.udc.tfgproject.backend.model.entities.medicamentInformation;

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
    private String name;
    private Medicament medicament;

    public CommercialMedicament() {
    }

    public CommercialMedicament(String name, Medicament medicament) {
	this.name = name;
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

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
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