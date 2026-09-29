package com.eventoapp.eventoapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
	
	@RequestMapping("/")//retorna a página index.html
	public String index() {
		return "index";
	}

}
