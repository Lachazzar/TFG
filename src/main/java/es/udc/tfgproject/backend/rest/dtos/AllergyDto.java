package es.udc.tfgproject.backend.rest.dtos;

public class AllergyDto {

    private String allergyName;
    private String code;

    public AllergyDto() {

    }

    public AllergyDto(String allergyName) {
	this.allergyName = allergyName;
	this.code = allergyName.replaceAll("\\s", "");
    }

    public String getAllergyName() {
	return allergyName;
    }

    public void setAllergyName(String allergyName) {
	this.allergyName = allergyName;
    }

    public final String getCode() {
	return code;
    }

    public final void setCode(String code) {
	this.code = code;
    }

}
