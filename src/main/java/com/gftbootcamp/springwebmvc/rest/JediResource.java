package com.gftbootcamp.springwebmvc.rest;

import java.util.List;

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
import com.gftbootcamp.springwebmvc.service.JediService;

@RestController
public class JediResource {
	
	@Autowired
	private JediService service;
	
	@GetMapping("/api/jedi")
	public List<Jedi> getAllJedi() {
		return service.findAll();
	}
	
	@GetMapping("/api/jedi/{id}")
	public ResponseEntity<Jedi> getJedi(@PathVariable("id") Long id) {
		Jedi jedi = service.findById(id);
		return ResponseEntity.ok(jedi);
		//não preciso me preocupar com erro pq o JediNotFoundException já aciona o ResourceAdvice e já faz a resposta apropriada (anotação exception handler)
	}
	
	@PostMapping("/api/jedi")
	@ResponseStatus(HttpStatus.CREATED)
	public Jedi createJedi(@Valid @RequestBody Jedi jedi) { //requestbody é usado ao inves de model attribute porque não há um formulario
		
		return service.save(jedi);
	}
	
	@PutMapping("/api/jedi/{id}")
	public ResponseEntity<Jedi> updateJedi(@PathVariable("id") Long id, @Valid @RequestBody Jedi dto) {
		
		final Jedi jedi = service.update(id, dto);
		
		return ResponseEntity.ok(jedi);
	}
	
	@DeleteMapping("/api/jedi/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT) //define a resposta em caso de sucesso
	public void delete(@PathVariable("id") Long id) {
		
		service.delete(id);
		
	}
}
