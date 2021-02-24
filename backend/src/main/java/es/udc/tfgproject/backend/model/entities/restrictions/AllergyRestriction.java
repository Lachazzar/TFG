package es.udc.tfgproject.backend.model.entities.restrictions;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import es.udc.tfgproject.backend.model.entities.disease_intolerance_allergy.Allergy;

@Entity
public class AllergyRestriction extends RegularRestriction {

    private Allergy allergy;

    public AllergyRestriction() {

    }

    public AllergyRestriction(Long id, String name, String code, Allergy allergy) {
	super(id, name, code);
	this.allergy = allergy;
    }

    @OneToOne
    @JoinColumn(name = "allergyId")
    public Allergy getAllergy() {
	return allergy;
    }

    public void setAllergy(Allergy allergy) {
	this.allergy = allergy;
    }

}
