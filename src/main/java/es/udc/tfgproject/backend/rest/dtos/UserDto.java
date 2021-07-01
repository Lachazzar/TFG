package es.udc.tfgproject.backend.rest.dtos;

import es.udc.tfgproject.backend.model.entities.UserBD.RoleType;

public class UserDto {

    private String userName;
    private String password;
    private String email;
    private RoleType role;

    public UserDto() {

    }

    public UserDto(String userName, String email, RoleType role) {
	this.userName = userName;
	this.email = email;
	this.role = role;
    }

    public final String getUserName() {
	return userName;
    }

    public final void setUserName(String userName) {
	this.userName = userName;
    }

    public final String getPassword() {
	return password;
    }

    public final void setPassword(String password) {
	this.password = password;
    }

    public final String getEmail() {
	return email;
    }

    public final void setEmail(String email) {
	this.email = email;
    }

    public final RoleType getRole() {
	return role;
    }

    public final void setRole(RoleType role) {
	this.role = role;
    }
}
