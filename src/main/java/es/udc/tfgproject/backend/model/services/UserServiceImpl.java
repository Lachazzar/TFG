package es.udc.tfgproject.backend.model.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.udc.tfgproject.backend.model.entities.UserBD;
import es.udc.tfgproject.backend.model.entities.UserBD.RoleType;
import es.udc.tfgproject.backend.model.entities.UserDao;

@Service
@Transactional
public class UserServiceImpl implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

	UserBD user = userDao.findByUserName(username).get();
	if (user == null) {
	    throw new UsernameNotFoundException(username);
	}
	ArrayList<GrantedAuthority> roles = new ArrayList<>();
	if (user.getRole() == RoleType.ADMIN) {
	    roles.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
	    roles.add(new SimpleGrantedAuthority("ROLE_USER"));
	}
	if (user.getRole() == RoleType.USER) {
	    roles.add(new SimpleGrantedAuthority("ROLE_USER"));
	}

	return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(), roles);

    }

}
