package website.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import website.model.Container;

@Controller
public class ContainerController {
	
	@GetMapping("/")
	public String index(Model model) {
		return "index";
	}
	
	@GetMapping("/registercontainer")
	public String registerContainer(Container container, Model model) {
		return "registercontainer";
	}
	
	
}
