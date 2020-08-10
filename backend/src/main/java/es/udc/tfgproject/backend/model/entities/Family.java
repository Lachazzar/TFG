package es.udc.tfgproject.backend.model.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Family {
	
	private Long id;
	private String familyName;
	private String subFamilyName;

	public Family() {}

	public Family(String familyName, String subFamilyName) {
		this.familyName = familyName;
		this.subFamilyName = subFamilyName;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFamilyName() {
		return familyName;
	}

	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}

	public String getSubFamilyName() {
		return subFamilyName;
	}

	public void setSubFamilyName(String subFamilyName) {
		this.subFamilyName = subFamilyName;
	}
}
