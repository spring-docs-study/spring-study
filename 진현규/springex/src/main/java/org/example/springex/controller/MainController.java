package org.example.springex.controller;

import org.example.springex.service.LoggedUserManagementService;
import org.example.springex.service.LoginCountService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {
	private final LoggedUserManagementService loggedUserManagementService;
	private final LoginCountService loginCountService;

	public MainController (LoggedUserManagementService loggedUserManagementService, LoginCountService loginCountService) {
		this.loggedUserManagementService = loggedUserManagementService;
		this.loginCountService = loginCountService;
	}

	@GetMapping("/main")
	public String home(@RequestParam(required = false) String logout, Model model) {
		if (logout != null) {
			loggedUserManagementService.setUsername(null);
		}

		String username = loggedUserManagementService.getUsername();
		int count = loginCountService.getCount();

		if (username == null) {
			return "redirect:/";
		}

		model.addAttribute("count",count);
		model.addAttribute("username", username);

		return "main.html";
	}

	@RequestMapping("/home3/{id}")
	public String home3(@PathVariable("id") String id, Model model) {
		model.addAttribute("id", id);
		return "index.html";
	}

	@RequestMapping("/home2")
	public String home2(
			@RequestParam(required=false) String name,
			@RequestParam(required=false) String color,
			Model model) {
		model.addAttribute("name", name);
		model.addAttribute("color", color);
		return "index.html";
	}

	@GetMapping("/home")
	public String index(Model model) {
		model.addAttribute("userName","Kyu");
		model.addAttribute("age",25);
		return "index.html";
	}

	@GetMapping("/home1")
	public String home0(@RequestParam String color, Model model) {
		model.addAttribute("color",color);
		model.addAttribute("userName","kyu");
		return "index.html";
	}
}
