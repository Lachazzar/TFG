package es.udc.tfgproject.backend.model.entities;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class IntoleranceRestriction extends Restriction {

    private Intolerance intolerance;

    public IntoleranceRestriction(Long id, String name, String code, Severity severity, Intolerance intolerance) {
	super(id, name, code, severity);
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
