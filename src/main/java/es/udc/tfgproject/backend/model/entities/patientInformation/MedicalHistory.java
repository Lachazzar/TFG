package es.udc.tfgproject.backend.model.entities.patientInformation;

import java.util.ArrayList;

import es.udc.tfgproject.backend.model.entities.Sexo;
import es.udc.tfgproject.backend.model.entities.disease_intolerance_allergy.Allergy;
import es.udc.tfgproject.backend.model.entities.disease_intolerance_allergy.Disease;
import es.udc.tfgproject.backend.model.entities.disease_intolerance_allergy.Intolerance;

public class MedicalHistory {

    private int edad;
    private Sexo sexo;
    private boolean embarazo;
    private boolean lactancia;
    private int glomerularFiltration;
    private boolean liverFailure;
    private ArrayList<Treatment> actualTreatments;
    private ArrayList<Allergy> allergies;
    private ArrayList<Disease> diseases;
    private ArrayList<Intolerance> intolerances;

    public MedicalHistory(int edad, Sexo sexo, boolean embarazo, boolean lactancia, int glomerularFiltration,
	    boolean liverFailure, ArrayList<Treatment> actualTreatments, ArrayList<Allergy> allergies,
	    ArrayList<Disease> diseases, ArrayList<Intolerance> intolerances) {
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

    public final ArrayList<Treatment> getActualTreatments() {
	return actualTreatments;
    }

    public final void setActualTreatments(ArrayList<Treatment> actualTreatments) {
	this.actualTreatments = actualTreatments;
    }

    public final ArrayList<Allergy> getAllergies() {
	return allergies;
    }

    public final void setAllergies(ArrayList<Allergy> allergies) {
	this.allergies = allergies;
    }

    public final ArrayList<Disease> getDiseases() {
	return diseases;
    }

    public final void setDiseases(ArrayList<Disease> diseases) {
	this.diseases = diseases;
    }

    public final ArrayList<Intolerance> getIntolerances() {
	return intolerances;
    }

    public final void setIntolerances(ArrayList<Intolerance> intolerances) {
	this.intolerances = intolerances;
    }

}
