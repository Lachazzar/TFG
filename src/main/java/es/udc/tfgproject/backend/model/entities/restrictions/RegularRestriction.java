package es.udc.tfgproject.backend.model.entities.restrictions;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class RegularRestriction {
    private Long id;
    private String restrictionName;
    private String code;

    public RegularRestriction() {
    }

    public RegularRestriction(Long id, String restrictionName, String code) {
	this.id = id;
	this.restrictionName = restrictionName;
	this.code = code;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public String getRestrictionName() {
	return restrictionName;
    }

    public void setRestrictionName(String restrictionName) {
	this.restrictionName = restrictionName;
    }

    public String getCode() {
	return code;
    }

    public void setCode(String code) {
	this.code = code;
    }
}
