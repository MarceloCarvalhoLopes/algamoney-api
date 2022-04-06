package com.algaworks.algamoney.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.algaworks.algamoney.api.model.Launching;
import com.algaworks.algamoney.api.repository.launching.LaunchingRepositoryQuery;

public interface LaunchingRepository extends JpaRepository<Launching, Long>, LaunchingRepositoryQuery {

}
