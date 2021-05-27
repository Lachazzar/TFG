package es.udc.tfgproject.backend.rest.dtos;

public class CompleteInformationDto {
    private MedicalHistoryDto history;
    private TreatmentDto treatment;

    public CompleteInformationDto(MedicalHistoryDto history, TreatmentDto treatment) {
	this.history = history;
	this.treatment = treatment;
    }

    public final MedicalHistoryDto getHistory() {
	return history;
    }

    public final void setHistory(MedicalHistoryDto history) {
	this.history = history;
    }

    public final TreatmentDto getTreatment() {
	return treatment;
    }

    public final void setTreatment(TreatmentDto treatment) {
	this.treatment = treatment;
    }

}
