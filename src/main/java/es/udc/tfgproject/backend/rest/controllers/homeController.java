package es.udc.tfgproject.backend.rest.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class homeController {

    @GetMapping("")
    public String index() {
	return "home";
    }

    @GetMapping("/home")
    public String home() {
	return "home";
    }

}