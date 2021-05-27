package es.udc.tfgproject.backend.model.entities.disease_intolerance_allergy;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Intolerance {
    private Long id;
    private String intoleranceName;

    public Intolerance() {
    }

    public Intolerance(String intoleranceName) {
	this.intoleranceName = intoleranceName;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public String getIntoleranceName() {
	return intoleranceName;
    }

    public void setIntoleranceName(String intoleranceName) {
	this.intoleranceName = intoleranceName;
    }

}
