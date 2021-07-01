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
import es.udc.tfgproject.backend.rest.dtos.FamilyDto;

@Transactional
@ControllerAdvice
@RequestMapping("/administracion/familias")
public class familyController {

    @Autowired
    private ListService listService;
    @Autowired
    private SecService secService;

    @GetMapping("")
    public String families(Model model) {
	boolean security = secService.checkSecurity("ROLE_ADMIN");

	if (!security) {
	    return "forbidden";
	}

	ArrayList<FamilyDto> familiesList = listService.listAllFamiliesDto();

	model.addAttribute("families", familiesList);

	return "admin/families";
    }

    @GetMapping("/eliminar/{code}")
    public String deleteFamily(@PathVariable("code") String code, Model model) {
	boolean security = secService.checkSecurity("ROLE_ADMIN");

	if (!security) {
	    return "forbidden";
	}

	ArrayList<FamilyDto> familiesList = listService.listAllFamiliesDto();

	listService.deleteFamilyByCode(familiesList, code);

	familiesList.clear();

	familiesList = listService.listAllFamiliesDto();

	model.addAttribute("families", familiesList);

	return "admin/families";
    }

    @GetMapping("/editar/{code}")
    public String editFamily(@PathVariable("code") String code, Model model) {

	boolean security = secService.checkSecurity("ROLE_ADMIN");

	if (!security) {
	    return "forbidden";
	}

	FamilyDto family = listService.getFamilyByCode(code);

	if (family.getFamilyName() == null || family.getFamilyName() == "") {
	    model.addAttribute("title", "Nueva Familia");
	    model.addAttribute("err", "No se ha encontrado la familia, si guarda se insertará una nueva");
	    model.addAttribute("family", family);
	    return "admin/intoleranceDetails";
	} else {
	    model.addAttribute("title", "Detalle Familia");
	    model.addAttribute("family", family);
	    model.addAttribute("oldFamilyName", family.getFamilyName());
	}

	return "admin/familyDetails";
    }

    @GetMapping("/nuevo")
    public String newFamily(Model model) {

	boolean security = secService.checkSecurity("ROLE_ADMIN");

	if (!security) {
	    return "forbidden";
	}

	model.addAttribute("title", "Nueva Familia");

	FamilyDto family = new FamilyDto();
	model.addAttribute("family", family);

	return "admin/familyDetails";
    }

    @PostMapping("/guardar")
    public String saveFamily(@ModelAttribute("family") FamilyDto familyDto,
	    @RequestParam(value = "oldFamilyName", required = false) String oldFamilyName, Model model) {

	boolean security = secService.checkSecurity("ROLE_ADMIN");

	if (!security) {
	    return "forbidden";
	}

	Boolean hasError = listService.checkAndSaveFamily(oldFamilyName, familyDto.getFamilyName());

	if (hasError) {
	    model.addAttribute("err", "Ya existe una familia con ese nombre");
	    if (oldFamilyName == "") {
		model.addAttribute("title", "Nueva Familia");
	    } else {
		model.addAttribute("title", "Detalle Familia");
		model.addAttribute("family", familyDto);
		model.addAttribute("oldFamilyName", oldFamilyName);
	    }
	    return "admin/familyDetails";
	}

	ArrayList<FamilyDto> familiesList = listService.listAllFamiliesDto();

	model.addAttribute("families", familiesList);

	return "admin/families";
    }

}