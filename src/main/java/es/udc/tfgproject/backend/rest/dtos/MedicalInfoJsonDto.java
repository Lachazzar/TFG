package es.udc.tfgproject.backend.rest.dtos;

import java.util.ArrayList;

import es.udc.tfgproject.backend.model.entities.Sexo;

public class MedicalInfoJsonDto {

    private int edad;
    private Sexo sexo;
    private boolean embarazo;
    private boolean lactancia;
    private int glomerularFiltration;
    private boolean liverFailure;
    private ArrayList<String> actualTreatments;
    private ArrayList<String> allergies;
    private ArrayList<String> diseases;
    private ArrayList<String> intolerances;
    private String medRecet;
    private Boolean compRecetados;

    public MedicalInfoJsonDto() {
	
    }
    
    public MedicalInfoJsonDto(int edad, Sexo sexo, boolean embarazo, boolean lactancia, int glomerularFiltration,
	    boolean liverFailure, ArrayList<String> actualTreatments, ArrayList<String> allergies,
	    ArrayList<String> diseases, ArrayList<String> intolerances, String medRecet, Boolean compRecetados) {
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
	this.medRecet = medRecet;
	this.compRecetados = compRecetados;
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

    public final ArrayList<String> getActualTreatments() {
	return actualTreatments;
    }

    public final void setActualTreatments(ArrayList<String> actualTreatments) {
	this.actualTreatments = actualTreatments;
    }

    public final ArrayList<String> getAllergies() {
	return allergies;
    }

    public final void setAllergies(ArrayList<String> allergies) {
	this.allergies = allergies;
    }

    public final ArrayList<String> getDiseases() {
	return diseases;
    }

    public final void setDiseases(ArrayList<String> diseases) {
	this.diseases = diseases;
    }

    public final ArrayList<String> getIntolerances() {
	return intolerances;
    }

    public final void setIntolerances(ArrayList<String> intolerances) {
	this.intolerances = intolerances;
    }

    public final String getMedRecet() {
	return medRecet;
    }

    public final void setMedRecet(String medRecet) {
	this.medRecet = medRecet;
    }

    public final Boolean getCompRecetados() {
	return compRecetados;
    }

    public final void setCompRecetados(Boolean compRecetados) {
	this.compRecetados = compRecetados;
    }

}
