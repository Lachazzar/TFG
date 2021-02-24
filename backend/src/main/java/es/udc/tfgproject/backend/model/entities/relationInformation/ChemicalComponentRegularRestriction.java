package es.udc.tfgproject.backend.model.entities.relationInformation;

import javax.persistence.Entity;

@Entity
public class ChemicalComponentRegularRestriction {
    private Long componentId;
    private Long regularRestrictionId;

    public ChemicalComponentRegularRestriction() {

    }

    public ChemicalComponentRegularRestriction(Long componentId, Long regularRestrictionId) {
	this.componentId = componentId;
	this.regularRestrictionId = regularRestrictionId;
    }

    public final Long getComponentId() {
	return componentId;
    }

    public final void setComponentId(Long componentId) {
	this.componentId = componentId;
    }

    public final Long getRegularRestrictionId() {
	return regularRestrictionId;
    }

    public final void setRegularRestrictionId(Long regularRestrictionId) {
	this.regularRestrictionId = regularRestrictionId;
    }

}
