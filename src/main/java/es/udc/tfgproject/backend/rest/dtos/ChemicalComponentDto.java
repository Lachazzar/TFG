package es.udc.tfgproject.backend.rest.dtos;

import es.udc.tfgproject.backend.model.entities.medicamentInformation.Family;

public class ChemicalComponentDto {

    private String componentName;
    private Family family;
    private String code;

    public ChemicalComponentDto(String componentName, Family family) {
	this.componentName = componentName;
	this.family = family;
	this.code = componentName.replaceAll("\\s", "");
    }

    public final String getComponentName() {
	return componentName;
    }

    public final void setComponentName(String componentName) {
	this.componentName = componentName;
    }

    public final Family getFamily() {
	return family;
    }

    public final void setFamily(Family family) {
	this.family = family;
    }

    public final String getCode() {
	return code;
    }

    public final void setCode(String code) {
	this.code = code;
    }

}
