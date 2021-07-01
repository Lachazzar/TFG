package es.udc.tfgproject.backend.rest.common;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PassEncoder {

    public static void main(String[] args) {
	System.out.println(encryptPassword("medic"));
    }

    public static String encryptPassword(String password) {
	BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	return encoder.encode(password);
    }
}
