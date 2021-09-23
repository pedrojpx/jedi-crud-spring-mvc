package com.gftbootcamp.springwebmvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.validation.Valid;

import com.gftbootcamp.springwebmvc.model.Jedi;
import com.gftbootcamp.springwebmvc.repository.JediRepository;

@Controller
public class JediController {
	
	@Autowired
	private JediRepository repository;
	
	//retorn apenas o html cru
//	@GetMapping("/jedi")
//	public String jedi() {
//		return "jedi"; //ao invés de retornar a string, o spring vai renderizar o jedi.html que está em templates
//	}
	
	@GetMapping("/jedi")
	public ModelAndView jedi() {
		
		final ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("jedi");
		
		
		modelAndView.addObject("allJedi", repository.getAllJedi());
		
		
		return modelAndView;
	}
	
	@GetMapping("/new-jedi")
	public ModelAndView newJedi() {
		final ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("new-jedi");
		
		modelAndView.addObject("jedi", new Jedi());
		
		return modelAndView;
		
	}
	
	@PostMapping("/jedi")
	public String createJedi(@Valid @ModelAttribute Jedi jedi, BindingResult result, RedirectAttributes redirect) { //não consigo importar o @valid, ainda não sei pq
		
		if(result.hasErrors()) {
			return "new-jedi";
		}
		
		repository.add(jedi);
		redirect.addFlashAttribute("message", "jedi cadastrado com sucesso");
		
		return "redirect:jedi"; //redireciona para um get em /jedi 
	}

}
