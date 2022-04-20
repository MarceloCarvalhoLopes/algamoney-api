package com.algaworks.algamoney.api.repository.launching;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.algaworks.algamoney.api.model.Launching;
import com.algaworks.algamoney.api.repository.filter.LaunchingFilter;
import com.algaworks.algamoney.api.repository.projection.LaunchingResume;

public interface LaunchingRepositoryQuery {

	public Page<Launching> filter(LaunchingFilter launchingFilter, Pageable pageable);
	public Page<LaunchingResume> resume(LaunchingFilter launchingFilter, Pageable pageable);
	
}
