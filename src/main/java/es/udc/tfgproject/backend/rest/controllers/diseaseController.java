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
import es.udc.tfgproject.backend.rest.dtos.DiseaseDto;

@Transactional
@ControllerAdvice
@RequestMapping("/administracion/enfermedades")
public class diseaseController {

    @Autowired
    private ListService listService;
    @Autowired
    private SecService secService;

    @GetMapping("")
    public String diseases(Model model) {
	boolean security = secService.checkSecurity("ROLE_ADMIN");

	if (!security) {
	    return "forbidden";
	}
	ArrayList<DiseaseDto> diseasesList = listService.listAllDiseasesDto();

	model.addAttribute("diseases", diseasesList);

	return "admin/diseases";
    }

    @GetMapping("/eliminar/{code}")
    public String deleteDisease(@PathVariable("code") String code, Model model) {
	boolean security = secService.checkSecurity("ROLE_ADMIN");

	if (!security) {
	    return "forbidden";
	}
	ArrayList<DiseaseDto> diseasesList = listService.listAllDiseasesDto();

	listService.deleteDiseaseByCode(diseasesList, code);

	diseasesList.clear();

	diseasesList = listService.listAllDiseasesDto();

	model.addAttribute("diseases", diseasesList);

	return "admin/diseases";
    }

    @GetMapping("/editar/{code}")
    public String editDisease(@PathVariable("code") String code, Model model) {

	boolean security = secService.checkSecurity("ROLE_ADMIN");

	if (!security) {
	    return "forbidden";
	}

	DiseaseDto disease = listService.getDiseaseByCode(code);

	if (disease.getDiseaseName() == null || disease.getDiseaseName() == "") {
	    model.addAttribute("title", "Nueva Enfermedad");
	    model.addAttribute("err", "No se ha encontrado la enfermedad, si guarda se insertará una nueva");
	    model.addAttribute("disease", disease);
	    return "admin/diseaseDetails";
	} else {
	    model.addAttribute("title", "Detalle Enfermedad");
	    model.addAttribute("disease", disease);
	    model.addAttribute("oldDiseaseName", disease.getDiseaseName());
	}

	return "admin/diseaseDetails";
    }

    @GetMapping("/nuevo")
    public String newDisease(Model model) {
	boolean security = secService.checkSecurity("ROLE_ADMIN");

	if (!security) {
	    return "forbidden";
	}

	model.addAttribute("title", "Nueva Enfermedad");

	DiseaseDto disease = new DiseaseDto();
	model.addAttribute("disease", disease);

	return "admin/diseaseDetails";
    }

    @PostMapping("/guardar")
    public String saveDisease(@ModelAttribute("disease") DiseaseDto diseaseDto,
	    @RequestParam(value = "oldDiseaseName", required = false) String oldDiseaseName, Model model) {
	boolean security = secService.checkSecurity("ROLE_ADMIN");

	if (!security) {
	    return "forbidden";
	}

	Boolean hasError = listService.checkAndSaveDisease(oldDiseaseName, diseaseDto.getDiseaseName());

	if (hasError) {
	    model.addAttribute("err", "Ya existe una enfermedad con ese nombre");
	    if (oldDiseaseName == "") {
		model.addAttribute("title", "Nueva Enfermedad");
	    } else {
		model.addAttribute("title", "Detalle Enfermedad");
		model.addAttribute("disease", diseaseDto);
		model.addAttribute("oldDiseaseName", oldDiseaseName);
	    }
	    return "admin/diseaseDetails";
	}

	ArrayList<DiseaseDto> diseasesList = listService.listAllDiseasesDto();

	model.addAttribute("diseases", diseasesList);

	return "admin/diseases";
    }

}