package es.udc.tfgproject.backend.model.services;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class SecService {

    public boolean checkSecurity(String role) {
	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	if (auth != null && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals(role))) {
	    return true;
	}

	return false;
    }

}
