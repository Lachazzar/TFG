package es.udc.tfgproject.frontend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.DefaultAuthenticationEventPublisher;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import es.udc.tfgproject.backend.model.services.UserServiceImpl;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserServiceImpl userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
	return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
	auth.authenticationEventPublisher(authenticationEventPublisher());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
	web.ignoring().antMatchers("/css/").antMatchers("/images/");

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
	http.authorizeRequests().anyRequest().authenticated().and().formLogin().loginPage("/login").permitAll().and()
		.logout().permitAll();
//	.antMatchers("/administracion", "/administracion/**").hasRole("ADMIN")
    }

    @Bean
    public DefaultAuthenticationEventPublisher authenticationEventPublisher() {
	return new DefaultAuthenticationEventPublisher();
    }
}