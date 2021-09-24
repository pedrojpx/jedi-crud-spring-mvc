package com.gftbootcamp.springwebmvc.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.gftbootcamp.springwebmvc.model.Jedi;

@Repository
public class JediRepository {
	
	private List<Jedi> jedis;

	public JediRepository() {
		super();
		jedis = new ArrayList<>();
		jedis.add(new Jedi("Luke", "Skywalker"));
	}
	
	public List<Jedi> getAllJedi() {
		
		return jedis;
	}

	public void add(Jedi jedi) {
		jedis.add(jedi);
	}

	public Optional<Jedi> findById(Long id) {
		for(Jedi j : jedis) {
			if(j.getId().equals(id)) {
				return Optional.of(j);
			}
		}
		return Optional.empty();
	}

	public Jedi save(Jedi jedi) {
		Jedi j;
		
		if(jedi.getId() == null) {
			j = new Jedi(jedi);
		} else {
			Optional<Jedi> foundJedi = findById(jedi.getId());
			if(foundJedi.isPresent()) {
				foundJedi.get().setName(jedi.getName());
				foundJedi.get().setLastName(jedi.getLastName());
				return foundJedi.get();
			} else {
				j = new Jedi(jedi);
			}
			
		}
		jedis.add(j);
		return j;
	}

	public void delete(Jedi jedi) {
		jedis.remove(jedi);
		
	}
}
