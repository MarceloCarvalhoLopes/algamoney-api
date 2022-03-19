package com.algaworks.algamoney.api.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algamoney.api.model.Person;
import com.algaworks.algamoney.api.repository.PersonRepository;

@RestController
@RequestMapping("/people")
public class PersonResource {

	@Autowired
	PersonRepository personRepository;
	
	
	@GetMapping("/{id}")
	public ResponseEntity<Person> findById(Long id){
		return personRepository.findById(id)
				.map(person -> ResponseEntity.ok(person))
				.orElse(ResponseEntity.notFound().build());
		
	}
}
