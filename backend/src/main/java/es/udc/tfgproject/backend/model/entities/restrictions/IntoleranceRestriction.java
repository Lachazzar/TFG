package es.udc.tfgproject.backend.model.entities.restrictions;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import es.udc.tfgproject.backend.model.entities.disease_intolerance_allergy.Intolerance;

@Entity
public class IntoleranceRestriction extends Restriction {

    private Intolerance intolerance;

    public IntoleranceRestriction(Long id, String name, String code, Intolerance intolerance) {
	super(id, name, code);
	this.intolerance = intolerance;
    }

    @OneToOne
    @JoinColumn(name = "intoleranceId")
    public final Intolerance getIntolerance() {
	return intolerance;
    }

    public final void setIntolerance(Intolerance intolerance) {
	this.intolerance = intolerance;
    }

}
