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
import es.udc.tfgproject.backend.rest.dtos.ChemicalComponentDto;
import es.udc.tfgproject.backend.rest.dtos.MedicamentExtendedDto;

@Transactional
@ControllerAdvice
@RequestMapping("/administracion/medicamentos")
public class medicamentController {

    @Autowired
    private ListService listService;

    @GetMapping("")
    public String medicaments(Model model) {
	ArrayList<MedicamentExtendedDto> medicamentList = listService.listAllMedicamentsExtendedDto();

	model.addAttribute("medicaments", medicamentList);

	return "admin/medicaments";
    }

    @GetMapping("/eliminar/{code}")
    public String deleteComponent(@PathVariable("code") String code, Model model) {
	ArrayList<MedicamentExtendedDto> medicamentList = listService.listAllMedicamentsExtendedDto();

	listService.deleteMedicamentByCode(medicamentList, code);

	medicamentList.clear();

	medicamentList = listService.listAllMedicamentsExtendedDto();

	model.addAttribute("medicaments", medicamentList);

	return "admin/medicaments";
    }

    @GetMapping("/editar/{code}")
    public String editComponent(@PathVariable("code") String code, Model model) {

	MedicamentExtendedDto medicament = listService.getMedicamentByCode(code);

	if (medicament.getMedicamentName() == null || medicament.getMedicamentName() == "") {
	    model.addAttribute("title", "Nuevo medicamento");
	    model.addAttribute("err", "No se ha encontrado el medicamento, si guarda se insertará uno nuevo");
	    return "admin/medicamentDetails";
	} else {
	    model.addAttribute("title", "Detalle medicamento");
	    model.addAttribute("oldMedicamentName", medicament.getMedicamentName());
	    model.addAttribute("code", code);
	}

	model.addAttribute("medicament", medicament);
	ArrayList<ChemicalComponentDto> chemicalComponentsList = listService.listAllChemicalComponentsDto();

	model.addAttribute("components", chemicalComponentsList);

	return "admin/medicamentDetails";
    }

    @GetMapping("/nuevo")
    public String newComponent(Model model) {

	model.addAttribute("title", "Nuevo medicamento");

	MedicamentExtendedDto medicament = new MedicamentExtendedDto();
	model.addAttribute("medicament", medicament);

	ArrayList<ChemicalComponentDto> chemicalComponentsList = listService.listAllChemicalComponentsDto();

	model.addAttribute("components", chemicalComponentsList);

	return "admin/medicamentDetails";
    }

    @PostMapping("/guardar")
    public String saveComponent(@ModelAttribute("medicament") MedicamentExtendedDto medicamentDto,
	    @RequestParam(value = "oldMedicamentName", required = false) String oldMedicamentName,
	    @RequestParam(value = "code", required = false) String code,
	    @RequestParam(value = "componentsL", required = false) String[] componentsL, Model model) {

	Boolean hasError = listService.checkAndSaveMedicament(oldMedicamentName, medicamentDto.getMedicamentName(),
		componentsL);

	if (hasError) {
	    ArrayList<ChemicalComponentDto> chemicalComponentsList = listService.listAllChemicalComponentsDto();

	    model.addAttribute("components", chemicalComponentsList);
	    model.addAttribute("err", "Ya existe un medicamento con ese nombre");
	    MedicamentExtendedDto medicament = new MedicamentExtendedDto();
	    if (oldMedicamentName == "") {
		model.addAttribute("title", "Nuevo Medicamento");
	    } else {
		model.addAttribute("title", "Detalle Medicamento");
		medicament = listService.getMedicamentByCode(code);
		model.addAttribute("medicament", medicament);
		model.addAttribute("oldMedicamentName", oldMedicamentName);
		model.addAttribute("code", code);
	    }
	    medicament.setMedicamentName(medicamentDto.getMedicamentName());
	    model.addAttribute("medicament", medicament);
	    return "admin/medicamentDetails";
	}

	ArrayList<MedicamentExtendedDto> medicamentList = listService.listAllMedicamentsExtendedDto();

	model.addAttribute("medicaments", medicamentList);

	return "admin/medicaments";
    }

}