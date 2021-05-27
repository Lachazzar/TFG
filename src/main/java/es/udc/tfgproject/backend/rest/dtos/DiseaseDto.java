package es.udc.tfgproject.backend.rest.dtos;

public class DiseaseDto {
    private String diseaseName;
    private String code;

    public DiseaseDto() {

    }

    public DiseaseDto(String diseaseName) {
	this.diseaseName = diseaseName;
	this.code = diseaseName.replaceAll("\\s", "");
    }

    public String getDiseaseName() {
	return diseaseName;
    }

    public void setDiseaseName(String diseaseName) {
	this.diseaseName = diseaseName;
    }

    public final String getCode() {
	return code;
    }

    public final void setCode(String code) {
	this.code = code;
    }

}
