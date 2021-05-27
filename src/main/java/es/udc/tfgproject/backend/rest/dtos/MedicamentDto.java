package es.udc.tfgproject.backend.rest.dtos;

import java.util.List;

public class MedicamentDto {

    private String commercialMedicamentName;
    private List<ChemicalComponentDto> components;

    public MedicamentDto(String commercialMedicamentName, List<ChemicalComponentDto> components) {
	this.commercialMedicamentName = commercialMedicamentName;
	this.components = components;
    }

    public final String getCommercialMedicamentName() {
	return commercialMedicamentName;
    }

    public final void setCommercialMedicamentName(String commercialMedicamentName) {
	this.commercialMedicamentName = commercialMedicamentName;
    }

    public final List<ChemicalComponentDto> getComponents() {
	return components;
    }

    public final void setComponents(List<ChemicalComponentDto> components) {
	this.components = components;
    }

}
