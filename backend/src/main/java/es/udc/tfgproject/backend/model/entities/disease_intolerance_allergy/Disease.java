package es.udc.tfgproject.backend.model.entities.disease_intolerance_allergy;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Disease {
    private Long id;
    private String name;

    public Disease() {

    }

    public Disease(String name) {
	this.name = name;
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
}
