package website.controllers;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import application.Listener;
import website.model.Credentials;
import website.repository.ContainersRepository;

@Controller
public class ContainerController {
	
	Listener buttomlistener = new Listener();
	private  ContainersRepository repository;
	
	@GetMapping("/")
	public String login(Model model) {
		return "login";
	}
	
	
	@GetMapping("/login")
	public String login(Credentials credentials,Model model) {
		
		buttomlistener.buttomlistener();
		
		return "redirect:index";
	}
	
	@PostMapping("/login")
	public String login(@Valid Credentials credentials, BindingResult bindingresult,Model model) {
		
		
		return "redirect:index";
	}
	

	
}
