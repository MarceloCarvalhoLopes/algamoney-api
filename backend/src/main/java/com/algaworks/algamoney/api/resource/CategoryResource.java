package com.algaworks.algamoney.api.resource;

import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.algaworks.algamoney.api.model.Category;
import com.algaworks.algamoney.api.repository.CategoryRepository;

@RestController
@RequestMapping("/categories")
public class CategoryResource {

	@Autowired
	private CategoryRepository categoryRepository;

	@GetMapping
	public List<Category> list() {
		return categoryRepository.findAll();
	}
	
	@PostMapping
	public ResponseEntity<Category> create(@RequestBody Category category, HttpServletResponse response) {
		Category savedCategory = categoryRepository.save(category);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
				.buildAndExpand(savedCategory.getId()).toUri();
		//response.setHeader("Location", uri.toASCIIString());
		
		return ResponseEntity.created(uri).body(savedCategory);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Category> findById(@PathVariable Long id) {
		
		return categoryRepository.findById(id)
				.map(category -> ResponseEntity.ok(category))
				.orElse(ResponseEntity.notFound().build());
	
	
//		Optional<Categoria> categoria = categoriaRepository.findById(codigo);
//
//        return categoria.isPresent() ? ResponseEntity.ok(categoria.get()) : ResponseEntity.notFound().build();
	
	}
	

	
	
}
