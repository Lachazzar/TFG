package es.udc.tfgproject.backend.model.entities;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class ComponentRestriction extends Restriction {

    private ChemicalComponent component1;
    private ChemicalComponent component2;

    public ComponentRestriction(Long id, String name, String code, Severity severity, ChemicalComponent component1,
	    ChemicalComponent component2) {
	super(id, name, code, severity);
	this.component1 = component1;
	this.component2 = component2;
    }

    @ManyToOne
    @JoinColumn(name = "component1Id")
    public final ChemicalComponent getComponent1() {
	return component1;
    }

    public final void setComponent1(ChemicalComponent component1) {
	this.component1 = component1;
    }

    @ManyToOne
    @JoinColumn(name = "component2Id")
    public final ChemicalComponent getComponent2() {
	return component2;
    }

    public final void setComponent2(ChemicalComponent component2) {
	this.component2 = component2;
    }

}
