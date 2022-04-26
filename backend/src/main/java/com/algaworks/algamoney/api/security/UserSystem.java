package com.algaworks.algamoney.api.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.algaworks.algamoney.api.model.SystemUser;

public class UserSystem extends User {
	
	private SystemUser systemUser;
	
	public UserSystem(SystemUser systemUser, Collection<? extends GrantedAuthority> authorities) {
		super(systemUser.getEmail(), systemUser.getPassword(), authorities);
		this.systemUser = systemUser;
	}

	public SystemUser getSystemUser() {
		return systemUser;
	}
}
