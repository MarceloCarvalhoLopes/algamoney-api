package com.algaworks.algamoney.api.repository.lauching;

import java.util.List;

import com.algaworks.algamoney.api.model.Launching;
import com.algaworks.algamoney.api.repository.filter.LaunchingFilter;

public interface LaunchingRepositoryQuery {

	public List<Launching> filter(LaunchingFilter launchingFilter);
	
}
