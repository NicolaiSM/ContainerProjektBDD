package website.controllers;

import java.util.Collection;
import java.util.LinkedList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import application.ContainerApp;
import application.data.Element;
import application.models.Client;

@Controller
public class LogisticCompanyController {
	
	Collection<Element> list = new LinkedList<Element>((Collection<? extends Element>) ContainerApp.getInstance().getPorts());
	Collection<Element> list2 = new LinkedList<Element>(ContainerApp.getInstance().getContainers());

	@ModelAttribute("ports")
	public Collection<Element> portList() {
		return list;
	}
	
	@ModelAttribute("containers")
	public Collection<Element> containerList() {
	  return list2;
	}
	
	
	
	@GetMapping("/logisticcompanyview")
	 public String logisticComapnyView(Model model) {
		 return "logisticcompanyview";
	 }
	 

		
}
