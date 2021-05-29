package es.udc.tfgproject.backend.rest.controllers;

import java.util.ArrayList;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import es.udc.tfgproject.backend.model.services.ListService;
import es.udc.tfgproject.backend.rest.dtos.IntoleranceDto;

@Transactional
@ControllerAdvice
@RequestMapping("/administracion/intolerancias")
public class intoleranceController {

    @Autowired
    private ListService listService;

    @GetMapping("")
    public String intolerances(Model model) {
	ArrayList<IntoleranceDto> intolerancesList = listService.listAllIntolerancesDto();

	model.addAttribute("intolerances", intolerancesList);

	return "admin/intolerances";
    }

    @GetMapping("/eliminar/{code}")
    public String deleteIntolerance(@PathVariable("code") String code, Model model) {
	ArrayList<IntoleranceDto> intolerancesList = listService.listAllIntolerancesDto();

	listService.deleteIntoleranceByCode(intolerancesList, code);

	intolerancesList.clear();

	intolerancesList = listService.listAllIntolerancesDto();

	model.addAttribute("intolerances", intolerancesList);

	return "admin/intolerances";
    }

    @GetMapping("/editar/{code}")
    public String editIntolerance(@PathVariable("code") String code, Model model) {

	IntoleranceDto intolerance = listService.getIntoleranceByCode(code);

	if (intolerance.getIntoleranceName() == null || intolerance.getIntoleranceName() == "") {
	    model.addAttribute("title", "Nueva Intolerancia");
	    model.addAttribute("err", "No se ha encontrado la intolerancia, si guarda se insertará una nueva");
	    model.addAttribute("intolerance", intolerance);
	    return "admin/intoleranceDetails";
	} else {
	    model.addAttribute("title", "Detalle Intolerancia");
	    model.addAttribute("intolerance", intolerance);
	    model.addAttribute("oldIntoleranceName", intolerance.getIntoleranceName());
	}

	return "admin/intoleranceDetails";
    }

    @GetMapping("/nuevo")
    public String newIntolerance(Model model) {

	model.addAttribute("title", "Nueva Intolerancia");

	IntoleranceDto intolerance = new IntoleranceDto();
	model.addAttribute("intolerance", intolerance);

	return "admin/intoleranceDetails";
    }

    @PostMapping("/guardar")
    public String saveIntolerance(@ModelAttribute("intolerance") IntoleranceDto intoleranceDto,
	    @RequestParam(value = "oldIntoleranceName", required = false) String oldIntoleranceName, Model model) {

	Boolean hasError = listService.checkAndSaveIntolerance(oldIntoleranceName, intoleranceDto.getIntoleranceName());

	if (hasError) {
	    model.addAttribute("err", "Ya existe una intolerancia con ese nombre");
	    if (oldIntoleranceName == "") {
		model.addAttribute("title", "Nueva Intolerancia");
	    } else {
		model.addAttribute("title", "Detalle Intolerancia");
		model.addAttribute("intolerance", intoleranceDto);
		model.addAttribute("oldIntoleranceName", oldIntoleranceName);
	    }
	    return "admin/intoleranceDetails";
	}

	ArrayList<IntoleranceDto> intolerancesList = listService.listAllIntolerancesDto();

	model.addAttribute("intolerances", intolerancesList);

	return "admin/intolerances";
    }

}