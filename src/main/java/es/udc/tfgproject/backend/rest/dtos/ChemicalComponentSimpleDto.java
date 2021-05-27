package es.udc.tfgproject.backend.rest.dtos;

public class ChemicalComponentSimpleDto {

    private String componentName;
    private String code;

    public ChemicalComponentSimpleDto(String componentName) {
	this.componentName = componentName;
	this.code = componentName.replaceAll("\\s", "");
    }

    public final String getComponentName() {
	return componentName;
    }

    public final void setComponentName(String componentName) {
	this.componentName = componentName;
    }

    public final String getCode() {
	return code;
    }

    public final void setCode(String code) {
	this.code = code;
    }

}
