package es.udc.tfgproject.backend.model.entities.restrictions;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Restriction {
    private Long id;
    private String name;
    private String code;

    public Restriction(Long id, String name, String code) {
	this.id = id;
	this.name = name;
	this.code = code;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public final Long getId() {
	return id;
    }

    public final void setId(Long id) {
	this.id = id;
    }

    public final String getName() {
	return name;
    }

    public final void setName(String name) {
	this.name = name;
    }

    public final String getCode() {
	return code;
    }

    public final void setCode(String code) {
	this.code = code;
    }
}
