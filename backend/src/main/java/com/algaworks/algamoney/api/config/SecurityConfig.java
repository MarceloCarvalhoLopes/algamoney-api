package com.algaworks.algamoney.api.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub
		auth.inMemoryAuthentication()
			.withUser("admin").password("{noop}admin").roles("ROLE");

	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		http.authorizeRequests()
				.antMatchers("/categories").permitAll()
				.anyRequest().authenticated()
			.and()
				.httpBasic()
			.and()//Desabilita a criação de sessão na API
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and()
				.csrf().disable();
				//Desabilita o para fazer um java script injection no serviço		
	}
}