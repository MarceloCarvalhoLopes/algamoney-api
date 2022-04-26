package com.algaworks.algamoney.api.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.algaworks.algamoney.api.model.SystemUser;
import com.algaworks.algamoney.api.repository.UserRepository;

@Service
public class AppUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<SystemUser> userOptional = userRepository.findByEmail(email);
		SystemUser systemUser = userOptional.orElseThrow(() -> new UsernameNotFoundException("Usuário e/ou senha incorretos"));
		return new UserSystem(systemUser,getPermissions(systemUser));
	}
	


	private Collection<? extends GrantedAuthority> getPermissions(SystemUser user) {
		Set<SimpleGrantedAuthority> authorities = new HashSet<>();
		user.getPermissions().forEach(p -> authorities.add(new SimpleGrantedAuthority(p.getDescripton().toUpperCase())));
		return authorities;
	}



}
