package es.udc.tfgproject.backend.rest.dtos;

public class FamilyDto {

    private String familyName;
    private String code;

    public FamilyDto() {
    }

    public FamilyDto(String familyName) {
	this.familyName = familyName;
	this.code = familyName.replaceAll("\\s", "");
    }

    public String getFamilyName() {
	return familyName;
    }

    public void setFamilyName(String familyName) {
	this.familyName = familyName;
    }

    public final String getCode() {
	return code;
    }

    public final void setCode(String code) {
	this.code = code;
    }

}
