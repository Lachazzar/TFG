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
import es.udc.tfgproject.backend.model.services.SecService;
import es.udc.tfgproject.backend.rest.dtos.AllergyDto;

@Transactional
@ControllerAdvice
@RequestMapping("/administracion/alergias")
public class allergyController {

    @Autowired
    private ListService listService;

    @Autowired
    private SecService secService;

    @GetMapping("")
    public String allergies(Model model) {

	boolean security = secService.checkSecurity("ROLE_ADMIN");

	if (!security) {
	    return "forbidden";
	}

	ArrayList<AllergyDto> allergiesList = listService.listAllAllergiesDto();

	model.addAttribute("allergies", allergiesList);

	return "admin/allergies";
    }

    @GetMapping("/eliminar/{code}")
    public String deleteAllergy(@PathVariable("code") String code, Model model) {

	boolean security = secService.checkSecurity("ROLE_ADMIN");

	if (!security) {
	    return "forbidden";
	}

	ArrayList<AllergyDto> allergiesList = listService.listAllAllergiesDto();

	listService.deleteAllergyByCode(allergiesList, code);

	allergiesList.clear();

	allergiesList = listService.listAllAllergiesDto();

	model.addAttribute("allergies", allergiesList);

	return "admin/allergies";
    }

    @GetMapping("/editar/{code}")
    public String editAllergy(@PathVariable("code") String code, Model model) {

	boolean security = secService.checkSecurity("ROLE_ADMIN");

	if (!security) {
	    return "forbidden";
	}

	AllergyDto allergy = listService.getAllergyByCode(code);

	if (allergy.getAllergyName() == null || allergy.getAllergyName() == "") {
	    model.addAttribute("title", "Nueva Alergia");
	    model.addAttribute("err", "No se ha encontrado la alergia, si guarda se insertará una nueva");
	    model.addAttribute("allergy", allergy);
	    return "admin/allergyDetails";
	} else {
	    model.addAttribute("title", "Detalle Alergia");
	    model.addAttribute("allergy", allergy);
	    model.addAttribute("oldAllergyName", allergy.getAllergyName());
	}

	return "admin/allergyDetails";
    }

    @GetMapping("/nuevo")
    public String newAllergy(Model model) {

	boolean security = secService.checkSecurity("ROLE_ADMIN");

	if (!security) {
	    return "forbidden";
	}

	model.addAttribute("title", "Nueva Alergia");

	AllergyDto allergy = new AllergyDto();
	model.addAttribute("allergy", allergy);

	return "admin/allergyDetails";
    }

    @PostMapping("/guardar")
    public String saveAllergy(@ModelAttribute("allergy") AllergyDto allergyDto,
	    @RequestParam(value = "oldAllergyName", required = false) String oldAllergyName, Model model) {

	boolean security = secService.checkSecurity("ROLE_ADMIN");

	if (!security) {
	    return "forbidden";
	}

	Boolean hasError = listService.checkAndSaveAllergy(oldAllergyName, allergyDto.getAllergyName());

	if (hasError) {
	    model.addAttribute("err", "Ya existe una alergia con ese nombre");
	    if (oldAllergyName == "") {
		model.addAttribute("title", "Nueva Alergia");
	    } else {
		model.addAttribute("title", "Detalle Alergia");
		model.addAttribute("allergy", allergyDto);
		model.addAttribute("oldAllergyName", oldAllergyName);
	    }
	    return "admin/allergyDetails";
	}

	ArrayList<AllergyDto> allergiesList = listService.listAllAllergiesDto();

	model.addAttribute("allergies", allergiesList);

	return "admin/allergies";
    }

}