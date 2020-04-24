package website.controllers;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import website.ListenHandler;
import website.model.Client;
import website.model.Container;
import website.model.Credentials;
import website.model.Port;
import website.repository.ContainersRepository;

@Controller
public class ContainerController {
	
	private  ContainersRepository repository;
	
	@GetMapping("/")
	public String index(Model model) {
		return "index";
	}
	
	@GetMapping("/createcontainer")
	public String createcontainer(Port port, Model model) {
		model.addAttribute("port", new Port());
		ListenHandler.getListenerByKey("createContainer").increment();
		return "createcontainer";
	}
	
	@PostMapping("/createcontainer")
	public String createcontainer(Port port,BindingResult result ,Model model) {
		return "redirect:/";
		
	}
	
	@GetMapping("/createport")
	public String createport(Port port, Model model) {
		model.addAttribute("port", new Port());
		ListenHandler.getListenerByKey("createPort").increment();
		return "createcontainer";
	}
	
	
	@PostMapping("/createport")
	public String createport(Port port,BindingResult result ,Model model) {
		return "redirect:/";
		
	}
	
	
	@GetMapping("/createclient")
	public String createclient(Client client, Model model) {
		model.addAttribute("client", new Client());
		ListenHandler.getListenerByKey("createClient").increment();
		return "createclient";
	}
	
	
	@PostMapping("/createclient")
	public String createclient(Client client, BindingResult result ,Model model) {
		return "redirect:/";
	}
	
	@GetMapping("/registercontainer")
	public String registercontainer(Client client, Model model) {
		model.addAttribute("client", new Client());
		ListenHandler.getListenerByKey("registerContainer").increment();
		return "registercontainer";
	}
	
	
	@PostMapping("/registercontainer")
	public String registercontainer(Client client, BindingResult result ,Model model) {
		
		return "redirect:/";
	}
	
	
	
	
	
	

	
}