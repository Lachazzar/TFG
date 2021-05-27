package es.udc.tfgproject.backend.rest.dtos;

import java.util.List;

public class MedicamentExtendedDto {

    private String medicamentName;
    private String code;
    private List<ChemicalComponentSimpleDto> components;

    public MedicamentExtendedDto() {

    }

    public MedicamentExtendedDto(String medicamentName) {
	this.medicamentName = medicamentName;
	this.code = medicamentName.replaceAll("\\s", "");
    }

    public final String getMedicamentName() {
	return medicamentName;
    }

    public final void setMedicamentName(String medicamentName) {
	this.medicamentName = medicamentName;
    }

    public final List<ChemicalComponentSimpleDto> getComponents() {
	return components;
    }

    public final void setComponents(List<ChemicalComponentSimpleDto> components) {
	this.components = components;
    }

    public final String getCode() {
	return code;
    }

    public final void setCode(String code) {
	this.code = code;
    }

}
