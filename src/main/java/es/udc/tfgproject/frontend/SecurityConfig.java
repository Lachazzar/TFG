package es.udc.tfgproject.frontend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.DefaultAuthenticationEventPublisher;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import es.udc.tfgproject.backend.model.services.UserServiceImpl;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserServiceImpl userDetailsService;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
	return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
	auth.authenticationEventPublisher(authenticationEventPublisher());
    }

    @Bean
    public DefaultAuthenticationEventPublisher authenticationEventPublisher() {
	return new DefaultAuthenticationEventPublisher();
    }
}