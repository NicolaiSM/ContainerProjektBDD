package website.controllers;



import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import application.Client;
import application.ContainerApp;
import website.model.ClientForm;
import website.model.CredentialForm;
import website.model.LogisitcCompanyForm;
import website.model.UserForm;
import website.repository.UsersRepository;

@Controller
public class ClientController {
	Client user;
	
	@GetMapping("/index")
	public String index(Model model) {
		
		return "index";
	}
	
	@GetMapping("/createclient")
	public String createClient(UserForm userForm, Model model) {
		model.addAttribute("userForm", new ClientForm());
		return "createclient";
	}
	
	@PostMapping("/createclient")
	public String createClient(@Valid UserForm userForm, BindingResult result, Model model) {
		
		if(result.hasErrors()) {
			return "createclient";
		}
		try {
			ContainerApp.getInstance().registerClient(userForm.getClientName(),userForm.getAddress(),userForm.getContactPerson(),userForm.getEmail(),userForm.getPassword());
		} catch (Exception e) {
			model.addAttribute("message", e.getMessage());
			return "createclient";
		}
		
		return "redirect:/";
		
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
		try {
			user = ContainerApp.getInstance().loggedInClient(credentialForm.getClientName(),credentialForm.getPassword());
		} catch (Exception e) {
			model.addAttribute("message", e.getMessage());
			
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
