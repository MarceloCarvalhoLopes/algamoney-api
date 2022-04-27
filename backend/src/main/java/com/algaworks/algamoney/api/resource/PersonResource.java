package com.algaworks.algamoney.api.resource;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algamoney.api.event.ResourceCreatedEvent;
import com.algaworks.algamoney.api.model.Person;
import com.algaworks.algamoney.api.repository.PersonRepository;
import com.algaworks.algamoney.api.service.PersonService;

@RestController
@RequestMapping("/people")
public class PersonResource {

	@Autowired
	private PersonRepository personRepository;
	
	@Autowired
	private PersonService personService;
	
	@Autowired
	private ApplicationEventPublisher applicationEventPublisher;
	
	@GetMapping
	@PreAuthorize("hasAuthority('ROLE_RESEARCH_PERSON') and hasAuthority('SCOPE_read')")
	public Page<Person> research(@RequestParam(required = false, defaultValue = "") String name, Pageable pageable){
		return personRepository.findByNameContaining(name, pageable);
	}

	
//	@GetMapping
//	@PreAuthorize("hasAuthority('ROLE_RESEARCH_PERSON') and hasAuthority('SCOPE_read')")
//	public List<Person>list(){
//		return personRepository.findAll();
//	}
	
	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('ROLE_RESEARCH_PERSON') and hasAuthority('SCOPE_read')")
	public ResponseEntity<Person> findById(@PathVariable Long id){
		return personRepository.findById(id)
				.map(person -> ResponseEntity.ok(person))
				.orElse(ResponseEntity.notFound().build());
		
	}
	
	@PostMapping
	@PreAuthorize("hasAuthority('ROLE_REGISTER_PERSON') and hasAuthority('SCOPE_write')")
	public ResponseEntity<Person>create(@Valid @RequestBody Person person, HttpServletResponse response ){
		Person savedPerson = personRepository.save(person);
		
		applicationEventPublisher.publishEvent(new ResourceCreatedEvent(this, response, savedPerson.getId()));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(savedPerson);
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasAuthority('ROLE_DELETE_PERSON') and hasAuthority('SCOPE_write')")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		personRepository.deleteById(id);
	}
	
	@PutMapping("/{id}")
	@PreAuthorize("hasAuthority('ROLE_REGISTER_PERSON') and hasAuthority('SCOPE_write')")
	public ResponseEntity<Person> update(@PathVariable Long id, @Valid @RequestBody Person person){
		
		Person savedPerson = personService.update(id, person);	
		
		return ResponseEntity.ok(savedPerson);
		
	}

	@PutMapping("{id}/active")
	@PreAuthorize("hasAuthority('ROLE_REGISTER_PERSON') and hasAuthority('SCOPE_write')")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updatePropertyActive(@PathVariable Long id, @RequestBody Boolean active){
		
		personService.updatePropertyActive(id,active);
		
	}
	

}
