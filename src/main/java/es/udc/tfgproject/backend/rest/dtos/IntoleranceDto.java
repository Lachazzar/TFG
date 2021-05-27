package es.udc.tfgproject.backend.rest.dtos;

public class IntoleranceDto {
    private String intoleranceName;
    private String code;

    public IntoleranceDto() {
    }

    public IntoleranceDto(String intoleranceName) {
	this.intoleranceName = intoleranceName;
	this.code = intoleranceName.replaceAll("\\s", "");
    }

    public String getIntoleranceName() {
	return intoleranceName;
    }

    public void setIntoleranceName(String intoleranceName) {
	this.intoleranceName = intoleranceName;
    }

    public final String getCode() {
	return code;
    }

    public final void setCode(String code) {
	this.code = code;
    }

}
