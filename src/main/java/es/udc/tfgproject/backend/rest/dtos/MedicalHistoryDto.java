package es.udc.tfgproject.backend.rest.dtos;

import java.util.ArrayList;

import es.udc.tfgproject.backend.model.entities.Sexo;

public class MedicalHistoryDto {

    private int edad;
    private Sexo sexo;
    private boolean embarazo;
    private boolean lactancia;
    private int glomerularFiltration;
    private boolean liverFailure;
    private ArrayList<TreatmentDto> actualTreatments;
    private ArrayList<AllergyDto> allergies;
    private ArrayList<DiseaseDto> diseases;
    private ArrayList<IntoleranceDto> intolerances;

    public MedicalHistoryDto(int edad, Sexo sexo, boolean embarazo, boolean lactancia, int glomerularFiltration,
	    boolean liverFailure, ArrayList<TreatmentDto> actualTreatments, ArrayList<AllergyDto> allergies,
	    ArrayList<DiseaseDto> diseases, ArrayList<IntoleranceDto> intolerances) {
	this.edad = edad;
	this.sexo = sexo;
	this.embarazo = embarazo;
	this.lactancia = lactancia;
	this.glomerularFiltration = glomerularFiltration;
	this.liverFailure = liverFailure;
	this.actualTreatments = actualTreatments;
	this.allergies = allergies;
	this.diseases = diseases;
	this.intolerances = intolerances;
    }

    public final int getEdad() {
	return edad;
    }

    public final void setEdad(int edad) {
	this.edad = edad;
    }

    public final Sexo getSexo() {
	return sexo;
    }

    public final void setSexo(Sexo sexo) {
	this.sexo = sexo;
    }

    public final boolean isEmbarazo() {
	return embarazo;
    }

    public final void setEmbarazo(boolean embarazo) {
	this.embarazo = embarazo;
    }

    public final boolean isLactancia() {
	return lactancia;
    }

    public final void setLactancia(boolean lactancia) {
	this.lactancia = lactancia;
    }

    public final int getGlomerularFiltration() {
	return glomerularFiltration;
    }

    public final void setGlomerularFiltration(int glomerularFiltration) {
	this.glomerularFiltration = glomerularFiltration;
    }

    public final boolean isLiverFailure() {
	return liverFailure;
    }

    public final void setLiverFailure(boolean liverFailure) {
	this.liverFailure = liverFailure;
    }

    public final ArrayList<TreatmentDto> getActualTreatments() {
	return actualTreatments;
    }

    public final void setActualTreatments(ArrayList<TreatmentDto> actualTreatments) {
	this.actualTreatments = actualTreatments;
    }

    public final ArrayList<AllergyDto> getAllergies() {
	return allergies;
    }

    public final void setAllergies(ArrayList<AllergyDto> allergies) {
	this.allergies = allergies;
    }

    public final ArrayList<DiseaseDto> getDiseases() {
	return diseases;
    }

    public final void setDiseases(ArrayList<DiseaseDto> diseases) {
	this.diseases = diseases;
    }

    public final ArrayList<IntoleranceDto> getIntolerances() {
	return intolerances;
    }

    public final void setIntolerances(ArrayList<IntoleranceDto> intolerances) {
	this.intolerances = intolerances;
    }

}
