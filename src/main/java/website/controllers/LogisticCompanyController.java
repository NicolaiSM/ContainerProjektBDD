package website.controllers;

import java.util.Collection;


import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import application.ContainerApp;
import application.models.Container;
import application.models.User;
import website.model.ContainerForm;
import website.model.JourneyForm;
import website.model.JourneyInformationForm;
import website.model.KeywordForm;
import website.model.PortForm;
import website.model.UserForm;

@Controller
public class LogisticCompanyController {
	
	Collection<User> list = (Collection<User>) ContainerApp.getInstance().getUsers();
	Collection<Container> list2 = ContainerApp.getInstance().getContainers();
	Container container = null;

	@ModelAttribute("clients")
	public Collection<User> clientList() {
		return list;
	}
	
	@ModelAttribute("userForm")
	public UserForm userForm() {
		return new UserForm();
	}
	
	@ModelAttribute("containerForm")
	public ContainerForm containerForm() {
		return new ContainerForm();
	}
	
	@ModelAttribute("keywordForm")
	public KeywordForm keywordForm() {
		return new KeywordForm();
	}
	
	@ModelAttribute("container")
	public Container container() {
		return container;
	}
	
	@ModelAttribute("containers")
	public Collection<Container> containerList() {
		return list2;
	}
	
	@ModelAttribute("journeyInformationForm")
	public JourneyInformationForm journeyInformationForm() {
		return new JourneyInformationForm();
	}
	
	@ModelAttribute("portForm")
	public PortForm portForm() {
		return new PortForm();
	}
	
	
	
	@GetMapping("/logisticcompanyview")
	public String logisticComapnyView(Model model) {
		return "logisticcompanyview";
	}
	
	@PostMapping("/createcontainer") 
	public String createContainer(@Valid ContainerForm containerForm, BindingResult result, Model model) {
		if(result.hasErrors()) {
			return "redirect:/logisticcompanyview";
		}
		
		try {
			ContainerApp.getInstance().createContainer(containerForm.getPort());
		} catch (Exception e) {
			model.addAttribute("createcontainermessage", e.getMessage());
			return "logisticcompanyview";
		}
		
		return "redirect:/logisticcompanyview";
		
	}
	
	@PostMapping("/createclient")
	public String createClient(@Valid UserForm userForm, BindingResult result, Model model) {
		
		if(result.hasErrors()) {
			return "redirect:/logisticcompanyview";
		}
		try {
			ContainerApp.getInstance().registerClient(userForm.getClientName(),userForm.getAddress(),userForm.getContactPerson(),userForm.getEmail(),userForm.getPassword());
		} catch (Exception e) {
			model.addAttribute("createclientmessage", e.getMessage());
			return "logisticcompanyview";
		}
		
		return "redirect:/logisticcompanyview";
		
	}
	
	@PostMapping("/findclient")
	public String findUsers(KeywordForm keywordForm, BindingResult result, Model model) {
		
		if (keywordForm.getKeyword() == null) {
			list = (Collection<User>) ContainerApp.getInstance().getUsers();
		}
		
		
		try {
			list = (ContainerApp.getInstance().findClient(keywordForm.getKeyword().split(" ")));
		} catch (Exception e) {
			model.addAttribute("findclientmessage", e.getMessage());
			return "logisticcompanyview";
		}
		
		return "redirect:/logisticcompanyview";
	}
	 
	
	@PostMapping("/updatejourney")
	public String updateJourney(JourneyInformationForm journeyInformationForm, BindingResult result, Model model) {
		
		try {
			ContainerApp.getInstance().updateJourney(container, journeyInformationForm.getTimes(), journeyInformationForm.getLocations(), journeyInformationForm.getTemperatures(), journeyInformationForm.getHumidities(), journeyInformationForm.getPressures());
		} catch (Exception e) {
			model.addAttribute("updatejourneymessage", e.getMessage());
			return "logisticcompanyview";
		}
		
		return "redirect:/logisticcompanyview";
	}
	
	@GetMapping("/viewlc/{id}") 
	public String containerview(@PathVariable("id") String id, Model model) {
		
		try {
			this.container = (Container) ContainerApp.getInstance().findContainer(id).get(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "redirect:/logisticcompanyview";
	}
	
	@PostMapping("/findcontainerlc")
	public String findContainerLc(KeywordForm keywordForm, BindingResult result, Model model, JourneyForm journeyForm) {
		
		System.out.println(keywordForm.getKeyword());
		
		if (keywordForm.getKeyword() == null | keywordForm.getKeyword().isEmpty() ) {
			list2 = ContainerApp.getInstance().getContainers();
			
		} else {
		
			try {
				
				list2 = (Collection<Container>)ContainerApp.getInstance().getContainers().findElements(keywordForm.getKeyword().split(" "));
			} catch (Exception e) {
				e.printStackTrace();
				model.addAttribute("findcontainerlcmessage", e.getMessage());
				return "logisticcompanyview";
				
			}
		
		}
		
		return "redirect:/logisticcompanyview";
	}
	
	
	@PostMapping("/createport")
	public String createPort(PortForm portForm, BindingResult result, Model model) {
		try {
			ContainerApp.getInstance().registerPort(portForm.getPort());
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("createportmessage", e.getMessage());
			return "logisticcompanyview";
		}
		return "redirect:/logisticcompanyview";
		
		
	}
	
	
	
	
	
	
	
	
	
	
	

		
}
