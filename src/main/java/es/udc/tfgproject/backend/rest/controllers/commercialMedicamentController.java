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
import es.udc.tfgproject.backend.rest.dtos.CommercialMedicamentDto;
import es.udc.tfgproject.backend.rest.dtos.MedicamentExtendedDto;

@Transactional
@ControllerAdvice
@RequestMapping("/administracion/medicamentos")
public class commercialMedicamentController {

    @Autowired
    private ListService listService;
    @Autowired
    private SecService secService;

    @GetMapping("")
    public String commercialMedicaments(Model model) {
	boolean security = secService.checkSecurity("ROLE_ADMIN");

	if (!security) {
	    return "forbidden";
	}
	ArrayList<CommercialMedicamentDto> commercialMedicamentsList = listService.listAllCommercialMedicamentDto();

	model.addAttribute("medicaments", commercialMedicamentsList);

	return "admin/commercialMedicaments";
    }

    @GetMapping("/eliminar/{code}")
    public String deleteCommercialMedicament(@PathVariable("code") String code, Model model) {
	boolean security = secService.checkSecurity("ROLE_ADMIN");

	if (!security) {
	    return "forbidden";
	}
	ArrayList<CommercialMedicamentDto> commercialMedicamentsList = listService.listAllCommercialMedicamentDto();

	listService.deleteCommercialMedicamentByCode(commercialMedicamentsList, code);

	commercialMedicamentsList.clear();

	commercialMedicamentsList = listService.listAllCommercialMedicamentDto();

	model.addAttribute("medicaments", commercialMedicamentsList);

	return "admin/commercialMedicaments";
    }

    @GetMapping("/editar/{code}")
    public String editCommercialMedicament(@PathVariable("code") String code, Model model) {

	boolean security = secService.checkSecurity("ROLE_ADMIN");

	if (!security) {
	    return "forbidden";
	}

	CommercialMedicamentDto medicament = listService.getCommercialMedicamentByCode(code);

	if (medicament.getCommercialMedicamentName() == null || medicament.getCommercialMedicamentName() == "") {
	    model.addAttribute("title", "Nuevo Medicamento");
	    model.addAttribute("err", "No se ha encontrado el medicamento, si guarda se insertará una nuevo");
	    model.addAttribute("medicament", medicament);
	} else {
	    model.addAttribute("title", "Detalle Medicamento");
	    model.addAttribute("medicament", medicament);
	    model.addAttribute("oldCommercialMedicamentName", medicament.getCommercialMedicamentName());
	}

	ArrayList<MedicamentExtendedDto> medicamentList = listService.listAllMedicamentsExtendedDto();

	model.addAttribute("medicamentsDto", medicamentList);

	return "admin/commercialMedicamentDetails";
    }

    @GetMapping("/nuevo")
    public String newCommercialMedicament(Model model) {
	boolean security = secService.checkSecurity("ROLE_ADMIN");

	if (!security) {
	    return "forbidden";
	}

	model.addAttribute("title", "Nuevo Medicamento");

	CommercialMedicamentDto medicament = new CommercialMedicamentDto();
	model.addAttribute("medicament", medicament);

	ArrayList<MedicamentExtendedDto> medicamentList = listService.listAllMedicamentsExtendedDto();

	model.addAttribute("medicamentsDto", medicamentList);

	return "admin/commercialMedicamentDetails";
    }

    @PostMapping("/guardar")
    public String saveCommercialMedicament(@ModelAttribute("medicament") CommercialMedicamentDto medicament,
	    @RequestParam(value = "oldCommercialMedicamentName", required = false) String oldCommercialMedicamentName,
	    @RequestParam(value = "medicamentName", required = false) String medicamentName, Model model) {

	boolean security = secService.checkSecurity("ROLE_ADMIN");

	if (!security) {
	    return "forbidden";
	}

	Boolean hasError = listService.checkAndSaveCommercialMedicament(oldCommercialMedicamentName,
		medicament.getCommercialMedicamentName(), medicamentName);

	if (hasError) {
	    model.addAttribute("err", "Ya existe un medicamento con ese nombre");
	    if (oldCommercialMedicamentName == "") {
		model.addAttribute("title", "Nuevo Medicamento");
	    } else {
		model.addAttribute("title", "Detalle Medicamento");
		model.addAttribute("medicament", medicament);
		model.addAttribute("oldCommercialMedicamentName", oldCommercialMedicamentName);
	    }
	    ArrayList<MedicamentExtendedDto> medicamentList = listService.listAllMedicamentsExtendedDto();
	    model.addAttribute("medicamentsDto", medicamentList);

	    return "admin/commercialMedicamentDetails";
	}

	ArrayList<CommercialMedicamentDto> commercialMedicamentsList = listService.listAllCommercialMedicamentDto();

	model.addAttribute("medicaments", commercialMedicamentsList);

	return "admin/commercialMedicaments";
    }

}