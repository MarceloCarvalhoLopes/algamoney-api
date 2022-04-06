package com.algaworks.algamoney.api.repository.lauching;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.algaworks.algamoney.api.model.Launching;
import com.algaworks.algamoney.api.repository.filter.LaunchingFilter;

public interface LaunchingRepositoryQuery {

	public Page<Launching> filter(LaunchingFilter launchingFilter, Pageable pageable);
	
}
