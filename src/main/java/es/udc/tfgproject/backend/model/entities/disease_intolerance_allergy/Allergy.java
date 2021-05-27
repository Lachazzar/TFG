package es.udc.tfgproject.backend.model.entities.disease_intolerance_allergy;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Allergy {
    private Long id;
    private String allergyName;

    public Allergy() {

    }

    public Allergy(String allergyName) {
	this.allergyName = allergyName;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public String getAllergyName() {
	return allergyName;
    }

    public void setAllergyName(String allergyName) {
	this.allergyName = allergyName;
    }

}
