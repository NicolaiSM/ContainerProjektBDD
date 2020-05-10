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
	
	
	@GetMapping("/")
	public String login(CredentialForm credentialForm , Model model) {
		
//		try {
//		ContainerApp.getInstance().registerClient("a","a" ,"a","a@a.com","a");
//		ContainerApp.getInstance().registerClient("GuideUser","GuideStreet 13" ,"Group B","Guide@user.com","1234");
//		ContainerApp.getInstance().getUsers().add(new LogisticCompany("admin", "admin"));
//		
//		ContainerApp.getInstance().registerPort("a");
//		ContainerApp.getInstance().registerPort("b");
//		ContainerApp.getInstance().registerPort("c");
//		ContainerApp.getInstance().registerPort("Shanghai");
//		ContainerApp.getInstance().registerPort("Antwerp");
//		ContainerApp.getInstance().registerPort("Singapore");
//				
//		ContainerApp.getInstance().createContainer("a");
//		ContainerApp.getInstance().createContainer("b");
//		ContainerApp.getInstance().createContainer("Shanghai");
//		ContainerApp.getInstance().createContainer("Antwerp");
//		
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
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
