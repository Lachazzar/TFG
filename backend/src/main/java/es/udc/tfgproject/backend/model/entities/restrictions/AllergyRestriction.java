package es.udc.tfgproject.backend.model.entities.restrictions;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import es.udc.tfgproject.backend.model.entities.disease_intolerance_allergy.Allergy;

@Entity
public class AllergyRestriction extends Restriction {

    private Allergy allergy;

    public AllergyRestriction(Long id, String name, String code, Allergy allergy) {
	super(id, name, code);
	this.allergy = allergy;
    }

    @OneToOne
    @JoinColumn(name = "allergyId")
    public final Allergy getAllergy() {
	return allergy;
    }

    public final void setAllergy(Allergy allergy) {
	this.allergy = allergy;
    }

}
