package website.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import website.model.ClientForm;
import website.model.CredentialForm;
import website.model.UserForm;
import website.repository.UsersRepository;

@Controller
public class ClientController {
	
	@Autowired
	private UsersRepository userrepository;
	
	@GetMapping("/index")
	public String index(Model model) {
		
		return "index";
	}
	
	@GetMapping("/createclient")
	public String createClient(UserForm userForm, Model model) {
		model.addAttribute("userForm", (UserForm) new ClientForm());
		return "createclient";
	}
	
	@PostMapping("/createclient")
	public String createClient(@Valid UserForm userForm, BindingResult result, Model model) {
		
		if(result.hasErrors()) {
			return "createclient";
		}
		
		userrepository.save(userForm);
		
		return "redirect:/index";
		
	}
	
	@GetMapping("/")
	public String login(CredentialForm credentialForm , Model model) {
		model.addAttribute("credentialForm", new CredentialForm());
		return "login";
	}
	
	@GetMapping("/login")
	public String login2(CredentialForm credentialForm , Model model) {
		model.addAttribute("credentialForm", new CredentialForm());
		return "login";
	}
	
	@PostMapping("/login")
	public String login(@Valid CredentialForm credentialForm, BindingResult result, Model model) {		
		if(result.hasErrors()) {
			return "login";
		}
		
		return "redirect:/index";
	}
	
	
	
}
