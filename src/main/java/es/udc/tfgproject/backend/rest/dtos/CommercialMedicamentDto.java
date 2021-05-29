package es.udc.tfgproject.backend.rest.dtos;

public class CommercialMedicamentDto {

    private String commercialMedicamentName;
    private String code;
    private String medicamentName;

    public CommercialMedicamentDto() {

    }

    public CommercialMedicamentDto(String commercialMedicamentName) {
	this.commercialMedicamentName = commercialMedicamentName;
	this.code = commercialMedicamentName.replaceAll("\\s", "");
    }

    public String getCommercialMedicamentName() {
	return commercialMedicamentName;
    }

    public void setCommercialMedicamentName(String commercialMedicamentName) {
	this.commercialMedicamentName = commercialMedicamentName;
    }

    public final String getCode() {
	return code;
    }

    public final void setCode(String code) {
	this.code = code;
    }

    public final String getMedicamentName() {
	return medicamentName;
    }

    public final void setMedicamentName(String medicamentName) {
	this.medicamentName = medicamentName;
    }

}
