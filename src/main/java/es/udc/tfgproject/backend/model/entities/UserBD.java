package es.udc.tfgproject.backend.model.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class UserBD {

    public enum RoleType {
	USER, ADMIN
    };

    private Long id;
    private String userName;
    private String password;
    private String email;
    private RoleType role;

    public UserBD() {
    }

    public UserBD(String userName, String password, String email) {

	this.userName = userName;
	this.password = password;
	this.email = email;

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public String getUserName() {
	return userName;
    }

    public void setUserName(String userName) {
	this.userName = userName;
    }

    public String getPassword() {
	return password;
    }

    public void setPassword(String password) {
	this.password = password;
    }

    public String getEmail() {
	return email;
    }

    public void setEmail(String email) {
	this.email = email;
    }

    public RoleType getRole() {
	return role;
    }

    public void setRole(RoleType role) {
	this.role = role;
    }

}
