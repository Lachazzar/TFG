package es.udc.tfgproject.backend.model.entities.patientInformation;

public class Treatment {

    private String commercialMedicamentName;

    public Treatment(String commercialMedicamentName) {
	this.commercialMedicamentName = commercialMedicamentName;
    }

    public final String getCommercialMedicamentName() {
	return commercialMedicamentName;
    }

    public final void setCommercialMedicamentName(String commercialMedicamentName) {
	this.commercialMedicamentName = commercialMedicamentName;
    }

}
