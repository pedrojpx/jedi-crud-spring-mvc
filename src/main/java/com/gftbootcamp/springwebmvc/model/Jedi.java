package com.gftbootcamp.springwebmvc.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class Jedi {
	
	
	static Long idCounter = 0L;
	private Long id;
	
	@NotBlank //funcionam junto com o @Valid no controller para validar o campo
    @Size(min = 3, max = 20, message = "Name must have between 3 and 20 letters")
	private String name;
	
	@NotBlank
	@Size(max = 20, message = "Last Name must not have more than 20 letters")
	private String lastName;
	
	public Jedi(String name, String lastName) {
		this.name = name;
		this.lastName = lastName;
		this.id = ++idCounter;
	}

	public Jedi() {
	}

	public Jedi(Jedi jedi) {
		this.name = jedi.name;
		this.lastName = jedi.lastName;
		this.id = ++idCounter;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
}
