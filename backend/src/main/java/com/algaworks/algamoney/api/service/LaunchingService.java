package com.algaworks.algamoney.api.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
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
	
	public Launching update(Long id, Launching launching) {
		Launching savedLaunching = findByExistLaunching(id);
		if (!launching.getPerson().equals(savedLaunching.getPerson())) {
			validatePerson(launching);
		}
		
		BeanUtils.copyProperties(launching, savedLaunching,"id");
		return launchingRepository.save(savedLaunching);
		
	}

	private void validatePerson(Launching launching) {
		Optional<Person> person = null;
		
		if(launching.getPerson().getId()!= null) {
			person = personRepository.findById(launching.getPerson().getId());
		}
		
		if(person.isEmpty() || person.get().isInactive()) {
			throw new NonExistentOrActivePersonException();
		}
	}

	private Launching findByExistLaunching(Long id) {
		return launchingRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException());
		
	}
	
}
