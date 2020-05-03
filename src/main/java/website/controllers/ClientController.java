package website.controllers;




import java.lang.module.FindException;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import application.Client;
import application.ContainerApp;
import application.LogisticCompany;
import application.User;
import website.model.CredentialForm;
import website.model.JourneyForm;
import website.model.KeywordForm;
import website.model.UserForm;

@Controller
public class ClientController {
	User user;
	
	@GetMapping("/index")
	public String index(Model model) {
		try {
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "index";
	}
	
	@GetMapping("/createclient")
	public String createClient(UserForm userForm, Model model) {
		model.addAttribute("userForm", new UserForm());
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
		
		try {
			ContainerApp.getInstance().registerClient("a","a" ,"a","a@a.com","a");
		
		ContainerApp.getInstance().registerPort("a");
		ContainerApp.getInstance().registerPort("b");
		ContainerApp.getInstance().createContainer("a");
		ContainerApp.getInstance().createContainer("b");
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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
//			user = ContainerApp.loggedInLC
//			if (user==null) 
				user = ContainerApp.getInstance().loggedInUser(credentialForm.getClientName(),credentialForm.getPassword());
		} catch (Exception e) {
			model.addAttribute("message", e.getMessage());
			
			return "login";
		}
//		if (user instanceof LogisticCompany) {
//			return "redirect:/logisticcompanyview";
//		}
		return "redirect:/clientview";
		
		
		
	}
	
	@GetMapping("/clientview")
	public String clientView(Model model) {
		model.addAttribute("userForm", new UserForm(user.get("clientName"), user.get("address"), user.get("email"), user.get("contactPerson"), user.get("password")));
		model.addAttribute("test", user.get("clientName"));
		model.addAttribute("journeyForm", new JourneyForm());
		model.addAttribute("ports", ContainerApp.getInstance().getPorts());
		model.addAttribute("keywordForm", new KeywordForm());
		
		try {
			model.addAttribute("containers", ContainerApp.getInstance().getContainers());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "clientview";
		
		
	}
	
	@GetMapping("/logisitccompanyview")
	public String logisticCompany(Model model) {
//		model.addAttribute("logisitccompanyForm", (LogisitcCompanyForm) user);
		
		return "logisitccompanyview";
	}
	
	@PostMapping("/registercontainer")
	public String registerContainer(@Valid JourneyForm journeyForm, BindingResult result, Model model, UserForm userForm, KeywordForm keywordForm) {
		try { 
			ContainerApp.getInstance().registerContainer(journeyForm.getPortOfOrigin(), journeyForm.getDestination(), journeyForm.getContent(), user);
		} catch (Exception e) {
			model.addAttribute("registercontainermessage", e.getMessage());
			e.printStackTrace();
			System.out.println(user.get("clientName"));
			return "clientview";	
		}
		
		model.addAttribute("registercontainermessage", "Container successfully registered");
		return clientView(model);
	}

	@PostMapping("/updateclient")
	public String updateClient(@Valid UserForm userForm, BindingResult result, Model model, JourneyForm journeyForm, KeywordForm keywordForm) {
		try {
			ContainerApp.getInstance().updateClient(user, "clientName", userForm.getClientName());
			ContainerApp.getInstance().updateClient(user, "address", userForm.getAddress());
			ContainerApp.getInstance().updateClient(user, "email", userForm.getEmail());
			ContainerApp.getInstance().updateClient(user, "contactPerson", userForm.getContactPerson());
			ContainerApp.getInstance().updateClient(user, "password", userForm.getPassword());
		} catch (Exception e) {
			model.addAttribute("updateclientmessage", e.getMessage());
			e.printStackTrace();
		}
		model.addAttribute("updateclientmessage", "Your information has been updated");
		return "clientview";
		
	}
	
	@PostMapping("/findport")
	public String findPort(@Valid UserForm userForm, BindingResult result, Model model, JourneyForm journeyForm, KeywordForm keywordForm) {
		try {
			ContainerApp.getInstance().findPort(port);
			
		} catch (Exception e) {
			
		}
//		model.addAttribute("findportmessage", "Ports found");
		return "clientview";
		
	}
	
	
	
	
	
	 
	
	
	
	
	
	
}
