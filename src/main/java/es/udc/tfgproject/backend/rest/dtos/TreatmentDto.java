package es.udc.tfgproject.backend.rest.dtos;

public class TreatmentDto {

    private String commercialMedicamentName;
    private String code;

    public TreatmentDto(String commercialMedicamentName) {
	this.commercialMedicamentName = commercialMedicamentName;
	this.code = commercialMedicamentName.replaceAll("\\s", "");
    }

    public final String getCommercialMedicamentName() {
	return commercialMedicamentName;
    }

    public final void setCommercialMedicamentName(String commercialMedicamentName) {
	this.commercialMedicamentName = commercialMedicamentName;
    }

    public final String getCode() {
	return code;
    }

    public final void setCode(String code) {
	this.code = code;
    }

}
