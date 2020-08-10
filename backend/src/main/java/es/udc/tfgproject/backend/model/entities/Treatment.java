package es.udc.tfgproject.backend.model.entities;

public class Treatment {

    CommercialMedicament medicament;
    float dose;
    int periodicity;
    PeriodicityUnit periodicityUnit;
    AplicationRoute aplicationRoute;

    public Treatment(CommercialMedicament medicament, float dose, int periodicity, PeriodicityUnit periodicityUnit,
	    AplicationRoute aplicationRoute) {
	this.medicament = medicament;
	this.dose = dose;
	this.periodicity = periodicity;
	this.periodicityUnit = periodicityUnit;
	this.aplicationRoute = aplicationRoute;
    }

    public final CommercialMedicament getMedicament() {
	return medicament;
    }

    public final void setMedicament(CommercialMedicament medicament) {
	this.medicament = medicament;
    }

    public final float getDose() {
	return dose;
    }

    public final void setDose(float dose) {
	this.dose = dose;
    }

    public final int getPeriodicity() {
	return periodicity;
    }

    public final void setPeriodicity(int periodicity) {
	this.periodicity = periodicity;
    }

    public final PeriodicityUnit getPeriodicityUnit() {
	return periodicityUnit;
    }

    public final void setPeriodicityUnit(PeriodicityUnit periodicityUnit) {
	this.periodicityUnit = periodicityUnit;
    }

    public final AplicationRoute getAplicationRoute() {
	return aplicationRoute;
    }

    public final void setAplicationRoute(AplicationRoute aplicationRoute) {
	this.aplicationRoute = aplicationRoute;
    }

}
