package website.controllers;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import application.ContainerApp;
import application.models.Client;
import application.models.LogisticCompany;
import application.models.User;
import website.model.CredentialForm;
import website.model.UserForm;

@Controller
public class LoginController {

	@ModelAttribute("credentialForm") 
	public CredentialForm credentialForm() {
		return new CredentialForm();
	}
	
	@GetMapping("/logout")
	public String logout() {
		ActiveUser.setEmptyUser();
		return "redirect:/";
	}
	
	
	@GetMapping("/")
	public String login(CredentialForm credentialForm , Model model) {
		
		return "login";
	}
	
	@GetMapping("/login")
	public String login2(CredentialForm credentialForm , Model model) {
		return "login";
	}
	
	
	@PostMapping("/login")
	public String login(@Valid CredentialForm credentialForm, BindingResult result, Model model) {	
		if(result.hasErrors()) {
			return "login";
		}
		try {
				ActiveUser.setUser(ContainerApp.getInstance().loginUser(credentialForm.getClientName(),credentialForm.getPassword()));
		} catch (Exception e) {
			model.addAttribute("message", e.getMessage());
			
			return "login";
		}
		if (ActiveUser.getUser() instanceof LogisticCompany) {
			return "redirect:/logisticcompanyview";
		}
		return "redirect:/clientview";
		
		
		
	}
	

}
