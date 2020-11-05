package es.udc.tfgproject.backend.model.entities.restrictions;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import es.udc.tfgproject.backend.model.entities.medicamentInformation.ChemicalComponent;

@Entity
public class ComponentRestriction extends Restriction {

    private Set<ChemicalComponent> components;

    public ComponentRestriction(Long id, String name, String code, Set<ChemicalComponent> components) {
	super(id, name, code);
	this.components = components;
    }

    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH })
    @JoinTable(name = "ChemicalComponentComponentRestriction", joinColumns = {
	    @JoinColumn(name = "componentRestrictitonId") }, inverseJoinColumns = { @JoinColumn(name = "componentId") })
    public final Set<ChemicalComponent> getComponents() {
	return components;
    }

    public final void setComponents(Set<ChemicalComponent> components) {
	this.components = components;
    }
}
