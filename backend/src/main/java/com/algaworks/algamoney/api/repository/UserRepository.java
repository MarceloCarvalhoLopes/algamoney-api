package com.algaworks.algamoney.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.algaworks.algamoney.api.model.SystemUser;

public interface UserRepository extends JpaRepository<SystemUser, Long> {

	public Optional<SystemUser> findByEmail(String email);
}
