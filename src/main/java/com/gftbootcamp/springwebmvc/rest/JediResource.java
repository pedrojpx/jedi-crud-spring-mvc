package com.gftbootcamp.springwebmvc.rest;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.gftbootcamp.springwebmvc.model.Jedi;
import com.gftbootcamp.springwebmvc.repository.JediRepository;

@RestController
public class JediResource {

	@Autowired
	private JediRepository repository;
	
	@GetMapping("/api/jedi")
	public List<Jedi> getAllJedi() {
		return repository.getAllJedi();
	}
	
	@GetMapping("/api/jedi/{id}")
	public ResponseEntity<Jedi> getJedi(@PathVariable("id") Long id) {
		final Optional<Jedi> jedi = repository.findById(id);
		
		if(jedi.isPresent()) {
			return ResponseEntity.ok(jedi.get());
		} else {
			return ResponseEntity.notFound().build();
//			throw new JediNotFoundException();
		}
	}
	
	@PostMapping("/api/jedi")
	@ResponseStatus(HttpStatus.CREATED)
	public Jedi createJedi(@Valid @RequestBody Jedi jedi) { //requestbody é usado ao inves de model attribute porque não há um formulario
		
		return repository.save(jedi);
	}
	
	@PutMapping("/api/jedi/{id}")
	public ResponseEntity<Jedi> updateJedi(@PathVariable("id") Long id, @Valid @RequestBody Jedi dto) {
		final Optional<Jedi> jediEntity = repository.findById(id);
		final Jedi jedi;
		
		if(jediEntity.isPresent()) {
			jedi = jediEntity.get();
		} else {
			return ResponseEntity.notFound().build();
		}
		
		jedi.setName(dto.getName());
		jedi.setLastName(dto.getLastName());
		
		return ResponseEntity.ok(repository.save(jedi));
	}
	
	@DeleteMapping("/api/jedi/{id}")
	public ResponseEntity<Jedi> delete(@PathVariable("id") Long id) {
		
		final Optional<Jedi> jedi = repository.findById(id);
		
		if(jedi.isPresent()) {
			repository.delete(jedi.get());
			return ResponseEntity.noContent().build(); //no content é um status OK (204), indicando que foi deletado
		} else {
			return ResponseEntity.notFound().build();
		}
		
	}
}
