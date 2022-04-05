package com.algaworks.algamoney.api.resource;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algamoney.api.event.ResourceCreatedEvent;
import com.algaworks.algamoney.api.exceptionhandler.AlgamoneyExceptionHandler.Erro;
import com.algaworks.algamoney.api.model.Launching;
import com.algaworks.algamoney.api.repository.LaunchingRepository;
import com.algaworks.algamoney.api.repository.filter.LaunchingFilter;
import com.algaworks.algamoney.api.service.LaunchingService;
import com.algaworks.algamoney.api.service.exception.NonExistentOrActivePersonException;

@RestController
@RequestMapping("/launchings")
public class LaunchingResource {

	@Autowired
	private LaunchingRepository launchingRepository;
	
	@Autowired
	private LaunchingService launchingService;
	
	@Autowired
	private ApplicationEventPublisher applicationEventPublisher;
	
	@Autowired
	private MessageSource messageSource;
	
	@GetMapping
	public List<Launching> research(LaunchingFilter lauchingFilter){
		return launchingRepository.filter(lauchingFilter);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Launching> findById(@PathVariable Long id){
		return launchingRepository.findById(id)
				.map(launching -> ResponseEntity.ok(launching))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping
	public ResponseEntity<Launching> create(@Valid @RequestBody Launching launching, HttpServletResponse respose){
		Launching savedLaunching = launchingService.save(launching);
		 
		applicationEventPublisher.publishEvent(new ResourceCreatedEvent(this, respose, savedLaunching.getId()));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(savedLaunching);
	}
	
	@ExceptionHandler({NonExistentOrActivePersonException.class})
	public ResponseEntity<Object> handleNonExistentOrActivePersonException(NonExistentOrActivePersonException ex){
	
		String userMessage = messageSource.getMessage("person.nonexistent-or-active", null, LocaleContextHolder.getLocale());
		String devMessage = ex.toString();
		List<Erro> errors = Arrays.asList(new Erro(userMessage,devMessage));
		
		return ResponseEntity.badRequest().body(errors);
	}
}
