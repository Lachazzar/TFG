package es.udc.tfgproject.backend.rest.dtos;

import java.util.ArrayList;

import es.udc.tfgproject.backend.model.entities.restrictions.RegularRestriction;

public class ChemicalComponentCompleteDto {

    private String componentName;
    private FamilyDto family;
    private String code;
    private ArrayList<RegularRestriction> regularRestrictions;
    private ArrayList<DiseaseDto> diseases;
    private ArrayList<AllergyDto> allergies;
    private ArrayList<IntoleranceDto> intolerances;
    private ArrayList<ChemicalComponentSimpleDto> chemicalComponents;

    public ChemicalComponentCompleteDto() {
    }

    public ChemicalComponentCompleteDto(String componentName, FamilyDto family) {
	this.componentName = componentName;
	this.family = family;
	this.code = componentName.replaceAll("\\s", "");
    }

    public final String getComponentName() {
	return componentName;
    }

    public final void setComponentName(String componentName) {
	this.componentName = componentName;
    }

    public final FamilyDto getFamily() {
	return family;
    }

    public final void setFamily(FamilyDto family) {
	this.family = family;
    }

    public final String getCode() {
	return code;
    }

    public final void setCode(String code) {
	this.code = code;
    }

    public final ArrayList<RegularRestriction> getRegularRestrictions() {
	return regularRestrictions;
    }

    public final void setRegularRestrictions(ArrayList<RegularRestriction> regularRestrictions) {
	this.regularRestrictions = regularRestrictions;
    }

    public final ArrayList<DiseaseDto> getDiseases() {
	return diseases;
    }

    public final void setDiseases(ArrayList<DiseaseDto> diseases) {
	this.diseases = diseases;
    }

    public final ArrayList<AllergyDto> getAllergies() {
	return allergies;
    }

    public final void setAllergies(ArrayList<AllergyDto> allergies) {
	this.allergies = allergies;
    }

    public final ArrayList<IntoleranceDto> getIntolerances() {
	return intolerances;
    }

    public final void setIntolerances(ArrayList<IntoleranceDto> intolerances) {
	this.intolerances = intolerances;
    }

    public final ArrayList<ChemicalComponentSimpleDto> getChemicalComponents() {
	return chemicalComponents;
    }

    public final void setChemicalComponents(ArrayList<ChemicalComponentSimpleDto> chemicalComponents) {
	this.chemicalComponents = chemicalComponents;
    }

}
