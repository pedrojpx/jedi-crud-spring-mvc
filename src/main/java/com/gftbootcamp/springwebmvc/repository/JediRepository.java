package com.gftbootcamp.springwebmvc.repository;

import java.util.ArrayList;
import java.util.List;

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
}
