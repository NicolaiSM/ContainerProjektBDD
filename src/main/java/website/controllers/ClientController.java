package website.controllers;



import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import application.ContainerApp;
import website.model.ClientForm;
import website.model.CredentialForm;
import website.model.LogisitcCompanyForm;
import website.model.UserForm;
import website.repository.UsersRepository;

@Controller
public class ClientController {

	
	@GetMapping("/index")
	public String index(Model model) {
		
		return "index";
	}
	
	@GetMapping("/createclient")
	public String createClient(ClientForm userForm, Model model) {
		model.addAttribute("clientForm", new ClientForm());
		return "createclient";
	}
	
	@PostMapping("/createclient")
	public String createClient(@Valid UserForm userForm, BindingResult result, Model model) {
		
		if(result.hasErrors()) {
			return "createclient";
		}
		
		
		return "redirect:/login";
		
	}
	
	@GetMapping("/")
	public String login(CredentialForm credentialForm , Model model) {
		model.addAttribute("credentialForm", new CredentialForm());
		model.addAttribute("message", "Valid form");
		
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
		
		return "redirect:/clientview";
		
		
		
	}
	
	@GetMapping("/clientview")
	public String clientView(Model model) {
//		model.addAttribute("clientForm", (ClientForm) user);
		
		return "clientview";
	}
	
	@GetMapping("/logisitccompanyview")
	public String logisticCompany(Model model) {
//		model.addAttribute("logisitccompanyForm", (LogisitcCompanyForm) user);
		
		return "logisitccompanyview";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
