package es.udc.tfgproject.backend.rest.dtos;

public class DniDto {

    private String dni;

    public DniDto() {

    }

    public DniDto(String dni) {
	this.dni = dni;
    }

    public final String getDni() {
	return dni;
    }

    public final void setDni(String dni) {
	this.dni = dni;
    }

}
