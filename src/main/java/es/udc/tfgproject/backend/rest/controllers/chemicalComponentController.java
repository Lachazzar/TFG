package es.udc.tfgproject.backend.rest.controllers;

import java.util.ArrayList;
import java.util.List;

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

import es.udc.tfgproject.backend.model.entities.restrictions.RegularRestriction;
import es.udc.tfgproject.backend.model.services.ListService;
import es.udc.tfgproject.backend.rest.dtos.AllergyDto;
import es.udc.tfgproject.backend.rest.dtos.ChemicalComponentCompleteDto;
import es.udc.tfgproject.backend.rest.dtos.ChemicalComponentDto;
import es.udc.tfgproject.backend.rest.dtos.DiseaseDto;
import es.udc.tfgproject.backend.rest.dtos.FamilyDto;
import es.udc.tfgproject.backend.rest.dtos.IntoleranceDto;

@Transactional
@ControllerAdvice
@RequestMapping("/administracion/componentes")
public class chemicalComponentController {

    @Autowired
    private ListService listService;

    @GetMapping("")
    public String chemicalComponents(Model model) {
	ArrayList<ChemicalComponentCompleteDto> chemicalComponentList = listService
		.listAllChemicalComponentsCompleteDto();

	model.addAttribute("components", chemicalComponentList);

	return "admin/chemicalComponents";
    }

    @GetMapping("/eliminar/{code}")
    public String deleteComponent(@PathVariable("code") String code, Model model) {
	ArrayList<ChemicalComponentCompleteDto> chemicalComponentList = listService
		.listAllChemicalComponentsCompleteDto();

	listService.deleteChemicalComponentByCode(chemicalComponentList, code);

	chemicalComponentList.clear();

	chemicalComponentList = listService.listAllChemicalComponentsCompleteDto();

	model.addAttribute("components", chemicalComponentList);

	return "admin/chemicalComponents";
    }

    @GetMapping("/editar/{code}")
    public String editComponent(@PathVariable("code") String code, Model model) {

	ChemicalComponentCompleteDto component = listService.getChemicalComponentByCode(code);

	if (component.getComponentName() == null || component.getComponentName() == "") {
	    model.addAttribute("title", "Nuevo componente");
	    model.addAttribute("err", "No se ha encontrado el componente, si guarda se insertará uno nuevo");
	    model.addAttribute("component", component);
	    return "admin/componentDetails";
	} else {
	    model.addAttribute("title", "Detalle componente");
	    model.addAttribute("component", component);
	    model.addAttribute("oldComponentName", component.getComponentName());
	    model.addAttribute("code", code);
	}

	ArrayList<FamilyDto> familiesList = listService.listAllFamiliesDto();
	List<RegularRestriction> rRestrictionsList = listService.listAllRegularRestrictions();
	ArrayList<DiseaseDto> diseasesList = listService.listAllDiseasesDto();
	ArrayList<AllergyDto> allergiesList = listService.listAllAllergiesDto();
	ArrayList<IntoleranceDto> intolerancesList = listService.listAllIntolerancesDto();
	ArrayList<ChemicalComponentDto> chemicalComponentsList = listService
		.listAllChemicalComponentsDtoExceptComponent(code);

	model.addAttribute("families", familiesList);
	model.addAttribute("rRestrictions", rRestrictionsList);
	model.addAttribute("diseases", diseasesList);
	model.addAttribute("allergies", allergiesList);
	model.addAttribute("intolerances", intolerancesList);
	model.addAttribute("components", chemicalComponentsList);

	return "admin/componentDetails";
    }

    @GetMapping("/nuevo")
    public String newComponent(Model model) {

	model.addAttribute("title", "Nuevo componente");

	ChemicalComponentCompleteDto component = new ChemicalComponentCompleteDto();
	model.addAttribute("component", component);

	ArrayList<FamilyDto> familiesList = listService.listAllFamiliesDto();
	List<RegularRestriction> rRestrictionsList = listService.listAllRegularRestrictions();
	ArrayList<DiseaseDto> diseasesList = listService.listAllDiseasesDto();
	ArrayList<AllergyDto> allergiesList = listService.listAllAllergiesDto();
	ArrayList<IntoleranceDto> intolerancesList = listService.listAllIntolerancesDto();
	ArrayList<ChemicalComponentDto> chemicalComponentsList = listService.listAllChemicalComponentsDto();

	model.addAttribute("families", familiesList);
	model.addAttribute("rRestrictions", rRestrictionsList);
	model.addAttribute("diseases", diseasesList);
	model.addAttribute("allergies", allergiesList);
	model.addAttribute("intolerances", intolerancesList);
	model.addAttribute("components", chemicalComponentsList);

	return "admin/componentDetails";
    }

    @PostMapping("/guardar")
    public String saveComponent(@ModelAttribute("component") ChemicalComponentCompleteDto componentDto,
	    @RequestParam(value = "oldComponentName", required = false) String oldComponentName,
	    @RequestParam(value = "code", required = false) String code,
	    @RequestParam(value = "componentsL", required = false) String[] componentsL,
	    @RequestParam(value = "intolerancesL", required = false) String[] intolerancesL,
	    @RequestParam(value = "allergiesL", required = false) String[] allergiesL,
	    @RequestParam(value = "diseasesL", required = false) String[] diseasesL,
	    @RequestParam(value = "rRestrictionsL", required = false) String[] rRestrictionsL,
	    @RequestParam(value = "family", required = false) String family, Model model) {

	Boolean hasError = listService.checkAndSaveChemicalComponent(oldComponentName, componentDto.getComponentName(),
		family, componentsL, intolerancesL, allergiesL, diseasesL, rRestrictionsL);

	if (hasError) {
	    ArrayList<FamilyDto> familiesList = listService.listAllFamiliesDto();
	    List<RegularRestriction> rRestrictionsList = listService.listAllRegularRestrictions();
	    ArrayList<DiseaseDto> diseasesList = listService.listAllDiseasesDto();
	    ArrayList<AllergyDto> allergiesList = listService.listAllAllergiesDto();
	    ArrayList<IntoleranceDto> intolerancesList = listService.listAllIntolerancesDto();
	    model.addAttribute("families", familiesList);
	    model.addAttribute("rRestrictions", rRestrictionsList);
	    model.addAttribute("diseases", diseasesList);
	    model.addAttribute("allergies", allergiesList);
	    model.addAttribute("intolerances", intolerancesList);
	    model.addAttribute("err", "Ya existe un componente con ese nombre");
	    ChemicalComponentCompleteDto component = new ChemicalComponentCompleteDto();
	    ArrayList<ChemicalComponentDto> chemicalComponentsList = new ArrayList<ChemicalComponentDto>();
	    if (oldComponentName == "") {
		model.addAttribute("title", "Nuevo Componente");
		chemicalComponentsList = listService.listAllChemicalComponentsDto();
	    } else {
		model.addAttribute("title", "Detalle Componente");
		model.addAttribute("component", componentDto);
		chemicalComponentsList = listService.listAllChemicalComponentsDtoExceptComponent(code);
		component = listService.getChemicalComponentByCode(code);
		model.addAttribute("code", code);
		model.addAttribute("oldComponentName", oldComponentName);
	    }
	    model.addAttribute("components", chemicalComponentsList);
	    component.setComponentName(componentDto.getComponentName());
	    model.addAttribute("component", component);
	    return "admin/componentDetails";
	}

	ArrayList<ChemicalComponentCompleteDto> chemicalComponentList = listService
		.listAllChemicalComponentsCompleteDto();

	model.addAttribute("components", chemicalComponentList);

	return "admin/chemicalComponents";
    }

}