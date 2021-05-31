package es.udc.tfgproject.backend.rest.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/menu")
public class menuController {

    @GetMapping("")
    public String menu() {
	return "fragments/menu";
    }

}