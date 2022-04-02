package com.algaworks.algamoney.api.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.algaworks.algamoney.api.model.Launching;
import com.algaworks.algamoney.api.model.Person;
import com.algaworks.algamoney.api.repository.LaunchingRepository;
import com.algaworks.algamoney.api.repository.PersonRepository;
import com.algaworks.algamoney.api.service.exception.NonExistentOrActivePersonException;

@Service
public class LaunchingService {

	@Autowired
	private PersonRepository personRepository;
	
	@Autowired
	private LaunchingRepository launchingRepository;
	
	public Launching save( Launching launching) {
		Optional<Person> person = personRepository.findById(launching.getPerson().getId());
		if (person.isEmpty() || person.get().isInactive()) {
			throw new NonExistentOrActivePersonException(); 
		}
		
		return launchingRepository.save(launching);
	}

}
