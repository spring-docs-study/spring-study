package org.example.springex.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

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
	public String home(@RequestParam String color, Model model) {
		model.addAttribute("color",color);
		model.addAttribute("userName","kyu");
		return "index.html";
	}
}
