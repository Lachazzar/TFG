package es.udc.tfgproject.backend.model.entities;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class AllergyRestriction extends Restriction {

    private Allergy allergy;

    public AllergyRestriction(Long id, String name, String code, Severity severity, Allergy allergy) {
	super(id, name, code, severity);
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
