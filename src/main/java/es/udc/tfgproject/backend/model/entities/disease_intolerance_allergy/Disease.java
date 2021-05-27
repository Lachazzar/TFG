package es.udc.tfgproject.backend.model.entities.disease_intolerance_allergy;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Disease {
    private Long id;
    private String diseaseName;

    public Disease() {

    }

    public Disease(String diseaseName) {
	this.diseaseName = diseaseName;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public String getDiseaseName() {
	return diseaseName;
    }

    public void setDiseaseName(String diseaseName) {
	this.diseaseName = diseaseName;
    }

}
