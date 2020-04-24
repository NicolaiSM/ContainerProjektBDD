package website.controllers;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import website.ListenHandler;
import website.model.Credentials;
import website.repository.ContainersRepository;

@Controller
public class ContainerController {
	
	private  ContainersRepository repository;
	
	@GetMapping("/")
	public String login(Model model) {
		ListenHandler.createListener("login");
		ListenHandler.getListenerByKey("login").increment();
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
