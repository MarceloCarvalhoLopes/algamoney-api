package com.algaworks.algamoney.api.resource;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algamoney.api.event.ResourceCreatedEvent;
import com.algaworks.algamoney.api.model.Launching;
import com.algaworks.algamoney.api.repository.LaunchingRepository;

@RestController
@RequestMapping("/launchings")
public class LaunchingResource {

	@Autowired
	LaunchingRepository launchingRepository;
	
	@Autowired
	private ApplicationEventPublisher applicationEventPublisher;
	
	@GetMapping
	public List<Launching> list(){
		return launchingRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Launching> findById(@PathVariable Long id){
		return launchingRepository.findById(id)
				.map(launching -> ResponseEntity.ok(launching))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping
	public ResponseEntity<Launching> create(@Valid @RequestBody Launching launching, HttpServletResponse respose){
		Launching savedLaunching = launchingRepository.save(launching);
		
		applicationEventPublisher.publishEvent(new ResourceCreatedEvent(this, respose, savedLaunching.getId()));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(savedLaunching);
	}
}
