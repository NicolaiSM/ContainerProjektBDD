package website.controllers;

import java.util.Collection;
import java.util.LinkedList;

import javax.validation.Valid;
import website.controllers.ActiveUser;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import application.ContainerApp;
import application.data.Element;
import application.data.QueryHashSet;
import application.data.QueryLinkedList;
import application.models.Client;
import application.models.Container;
import application.models.Journey;
import application.models.Port;
import website.model.JourneyForm;
import website.model.KeywordForm;
import website.model.UserForm;
/**/
@Controller
public class ClientController {
	
	Collection<Port> list = (Collection<Port>) ContainerApp.getInstance().getPorts();
	Collection<Container> list2 = ((Client) ActiveUser.getUser()).getClientContainers();
	Collection<Journey> list3 = ((Client) ActiveUser.getUser()).getClientJourneys();
	Container container = null;
	
	@ModelAttribute("userForm")
	public UserForm populateUser() {
	  UserForm userForm = new UserForm(ActiveUser.getUser().get("clientName"), ActiveUser.getUser().get("address"), ActiveUser.getUser().get("email"), ActiveUser.getUser().get("contactPerson"), ActiveUser.getUser().get("password"));
	  return userForm;
	}
	
	@ModelAttribute("ports")
	public Collection<Port> portList() {
		return list;
	}
	
	@ModelAttribute("containers")
	public Collection<Container> containerList() {
		return list2;
	}
	
	@ModelAttribute("findportmessage")
	public String findPortMessage(String string) {
		return string;
	}
	
	@ModelAttribute("container")
	public Container container() {
		return container;
	}
	
	@ModelAttribute("journeyForm")
	public JourneyForm journeyForm() {
		return new JourneyForm();
	}
	
	@ModelAttribute("keywordForm")
	public KeywordForm keywordForm() {
		return new KeywordForm();
	}
	
	@ModelAttribute("journeys")
	public Collection<Journey> journeys() {
		return list3;
	}
	
	
	
	
	@GetMapping("/clientview")
	public String clientView(Model model) {
		
		return "clientview";
	}
	
	
	@PostMapping("/registercontainer")
	public String registerContainer(@Valid JourneyForm journeyForm, BindingResult result, Model model) {
		try { 
			ContainerApp.getInstance().registerContainer(journeyForm.getPortOfOrigin(), journeyForm.getDestination(), journeyForm.getContent(), (Client) ActiveUser.getUser());
		} catch (Exception e) {
			model.addAttribute("registercontainermessage", e.getMessage());
			e.printStackTrace();
			System.out.println(ActiveUser.getUser().get("clientName"));
			return "redirect:/clientview";
		}
		
		model.addAttribute("registercontainermessage", "Container successfully registered");
		return "redirect:/clientview";
	}

	@PostMapping("/updateclient")
	public String updateClient(@Valid UserForm userForm, BindingResult result, Model model) {
		try {
			ContainerApp.getInstance().updateClient((Client) ActiveUser.getUser(), "clientName", userForm.getClientName());
			ContainerApp.getInstance().updateClient((Client) ActiveUser.getUser(), "address", userForm.getAddress());
			ContainerApp.getInstance().updateClient((Client) ActiveUser.getUser(), "email", userForm.getEmail());
			ContainerApp.getInstance().updateClient((Client) ActiveUser.getUser(), "contactPerson", userForm.getContactPerson());
			ContainerApp.getInstance().updateClient((Client) ActiveUser.getUser(), "password", userForm.getPassword());
		} catch (Exception e) {
			model.addAttribute("updateclientmessage", e.getMessage());
			e.printStackTrace();
		}
		model.addAttribute("updateclientmessage", "Your information has been updated");
		return "redirect:/clientview";
		
	}
	
	@PostMapping("/findport")
	public String findPort( KeywordForm keywordForm, BindingResult result, Model model) {
		try {
			list.clear();
			list.addAll(ContainerApp.getInstance().findPorts(keywordForm.getKeyword().split(" ")));
			
		} catch (Exception e) {
			findPortMessage(e.getMessage());
			return "redirect:/clientview";
		}
		
		return "redirect:/clientview";
	}
	
	@PostMapping("/findcontainer")
	public String findContainer(KeywordForm keywordForm, BindingResult result, Model model) {
		try {
			list2 = ((QueryHashSet<Container>) ((Client)ActiveUser.getUser()).getClientContainers()).findElements(keywordForm.getKeyword().split(" "));
			System.out.println(list2);
		} catch (Exception e) {
			model.addAttribute("findcontainermessage", e.getMessage());
			return "redirect:/clientview";
		}
		
		return "redirect:/clientview";
	}
	
	@GetMapping("/view/{id}") 
	public String containerview(@PathVariable("id") String id, Model model) {
		
		try {
			this.container = (Container) ContainerApp.getInstance().findContainer(id).get(0);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "redirect:/clientview";
	}
	
	@PostMapping("/findjourney")
	public String findJourney(KeywordForm keywordForm, BindingResult result, Model model) {
		
		if(keywordForm.getKeyword() == null | keywordForm.getKeyword().isEmpty()) {
			list3 = ((Client) ActiveUser.getUser()).getClientJourneys();
		} else {
		
			try {
				list3 = ((QueryLinkedList<Journey>) ((Client) ActiveUser.getUser()).getClientJourneys()).findElements(keywordForm.getKeyword().split(" "));
			} catch (Exception e) {
				model.addAttribute("findcontainermessage", e.getMessage());
				return "redirect:/clientview";
			}
		
		}
		
		return "redirect:/clientview";
	}
	

}
