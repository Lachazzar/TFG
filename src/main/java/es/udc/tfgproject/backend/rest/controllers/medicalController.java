package es.udc.tfgproject.backend.rest.controllers;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import es.udc.tfgproject.backend.model.entities.Alert;
import es.udc.tfgproject.backend.model.entities.Sexo;
import es.udc.tfgproject.backend.model.entities.disease_intolerance_allergy.Allergy;
import es.udc.tfgproject.backend.model.entities.disease_intolerance_allergy.Disease;
import es.udc.tfgproject.backend.model.entities.disease_intolerance_allergy.Intolerance;
import es.udc.tfgproject.backend.model.entities.medicamentInformation.ChemicalComponent;
import es.udc.tfgproject.backend.model.entities.patientInformation.MedicalHistory;
import es.udc.tfgproject.backend.model.entities.patientInformation.Treatment;
import es.udc.tfgproject.backend.model.exceptions.InstanceNotFoundException;
import es.udc.tfgproject.backend.model.services.ListService;
import es.udc.tfgproject.backend.model.services.MedicalService;
import es.udc.tfgproject.backend.rest.dtos.AllergyDto;
import es.udc.tfgproject.backend.rest.dtos.ChemicalComponentDto;
import es.udc.tfgproject.backend.rest.dtos.DiseaseDto;
import es.udc.tfgproject.backend.rest.dtos.DniDto;
import es.udc.tfgproject.backend.rest.dtos.IntoleranceDto;
import es.udc.tfgproject.backend.rest.dtos.MedicalHistoryDto;
import es.udc.tfgproject.backend.rest.dtos.MedicalHistoryJsonDto;
import es.udc.tfgproject.backend.rest.dtos.MedicalInfoJsonDto;
import es.udc.tfgproject.backend.rest.dtos.MedicamentDto;
import es.udc.tfgproject.backend.rest.dtos.TreatmentDto;

@Controller
@RequestMapping("/medical")
public class medicalController {

    @Autowired
    private ListService listService;

    @Autowired
    private MedicalService medicalService;

    @GetMapping("/historyForm")
    public String index(Model model) {

	List<Sexo> sexos = Arrays.asList(Sexo.values());

	ArrayList<DiseaseDto> diseasesList = new ArrayList<DiseaseDto>();
	ArrayList<AllergyDto> allergiesList = new ArrayList<AllergyDto>();
	ArrayList<IntoleranceDto> intolerancesList = new ArrayList<IntoleranceDto>();

	listService.listAllDiseases().forEach(d -> {
	    DiseaseDto disease = new DiseaseDto(d.getDiseaseName());
	    diseasesList.add(disease);
	});

	listService.listAllAllergies().forEach(a -> {
	    AllergyDto allergy = new AllergyDto(a.getAllergyName());
	    allergiesList.add(allergy);
	});

	listService.listAllIntolerances().forEach(i -> {
	    IntoleranceDto intolerance = new IntoleranceDto(i.getIntoleranceName());
	    intolerancesList.add(intolerance);
	});

	MedicalHistoryDto history = new MedicalHistoryDto(0, null, false, false, 0, false, null, null, null, null);

	DniDto dni = new DniDto();

	model.addAttribute("dni", dni);
	model.addAttribute("history", history);
	model.addAttribute("sexos", sexos);
	model.addAttribute("allergies", allergiesList);
	model.addAttribute("diseases", diseasesList);
	model.addAttribute("intolerances", intolerancesList);

	return "medicalHistoryForm";
    }

    @PostMapping("/historyFormLoad")
    public String index(@ModelAttribute("history") MedicalHistoryDto historyDto,
	    @RequestParam(value = "dis", required = false) String[] dis,
	    @RequestParam(value = "als", required = false) String[] als,
	    @RequestParam(value = "ints", required = false) String[] ints,
	    @RequestParam(value = "meds", required = false) String[] meds, Model model) {

	List<Sexo> sexos = Arrays.asList(Sexo.values());

	ArrayList<DiseaseDto> diseasesList = new ArrayList<DiseaseDto>();
	ArrayList<AllergyDto> allergiesList = new ArrayList<AllergyDto>();
	ArrayList<IntoleranceDto> intolerancesList = new ArrayList<IntoleranceDto>();

	ArrayList<DiseaseDto> diseases = new ArrayList<DiseaseDto>();
	ArrayList<AllergyDto> allergies = new ArrayList<AllergyDto>();
	ArrayList<IntoleranceDto> intolerances = new ArrayList<IntoleranceDto>();
	ArrayList<TreatmentDto> medicaments = new ArrayList<TreatmentDto>();

	listService.listAllDiseases().forEach(d -> {
	    DiseaseDto disease = new DiseaseDto(d.getDiseaseName());
	    diseasesList.add(disease);
	});

	listService.listAllAllergies().forEach(a -> {
	    AllergyDto allergy = new AllergyDto(a.getAllergyName());
	    allergiesList.add(allergy);
	});

	listService.listAllIntolerances().forEach(i -> {
	    IntoleranceDto intolerance = new IntoleranceDto(i.getIntoleranceName());
	    intolerancesList.add(intolerance);
	});

	if (dis != null) {
	    for (int i = 0; i < dis.length; i++) {
		DiseaseDto disease = new DiseaseDto(dis[i]);
		diseases.add(disease);
	    }
	    historyDto.setDiseases(diseases);
	}

	if (als != null) {
	    for (int i = 0; i < als.length; i++) {
		AllergyDto allergy = new AllergyDto(als[i]);
		allergies.add(allergy);
	    }
	    historyDto.setAllergies(allergies);
	}

	if (ints != null) {
	    for (int i = 0; i < ints.length; i++) {
		IntoleranceDto intolerance = new IntoleranceDto(ints[i]);
		intolerances.add(intolerance);
	    }
	    historyDto.setIntolerances(intolerances);
	}

	if (meds != null) {
	    for (int i = 0; i < meds.length; i++) {
		TreatmentDto medicament = new TreatmentDto(meds[i]);
		medicaments.add(medicament);
	    }
	    historyDto.setActualTreatments(medicaments);
	}

	DniDto dni = new DniDto();

	model.addAttribute("dni", dni);
	model.addAttribute("history", historyDto);
	model.addAttribute("sexos", sexos);
	model.addAttribute("allergies", allergiesList);
	model.addAttribute("diseases", diseasesList);
	model.addAttribute("intolerances", intolerancesList);

	return "medicalHistoryForm";
    }

    @PostMapping("/historyFormJsonLoad")
    public String jsonLoad(@ModelAttribute("dni") DniDto dni, Model model) throws URISyntaxException {

	List<Sexo> sexos = Arrays.asList(Sexo.values());

	ArrayList<DiseaseDto> diseasesList = new ArrayList<DiseaseDto>();
	ArrayList<AllergyDto> allergiesList = new ArrayList<AllergyDto>();
	ArrayList<IntoleranceDto> intolerancesList = new ArrayList<IntoleranceDto>();

	ArrayList<DiseaseDto> diseases = new ArrayList<DiseaseDto>();
	ArrayList<AllergyDto> allergies = new ArrayList<AllergyDto>();
	ArrayList<IntoleranceDto> intolerances = new ArrayList<IntoleranceDto>();
	ArrayList<TreatmentDto> medicaments = new ArrayList<TreatmentDto>();

	listService.listAllDiseases().forEach(d -> {
	    DiseaseDto disease = new DiseaseDto(d.getDiseaseName());
	    diseasesList.add(disease);
	});

	listService.listAllAllergies().forEach(a -> {
	    AllergyDto allergy = new AllergyDto(a.getAllergyName());
	    allergiesList.add(allergy);
	});

	listService.listAllIntolerances().forEach(i -> {
	    IntoleranceDto intolerance = new IntoleranceDto(i.getIntoleranceName());
	    intolerancesList.add(intolerance);
	});

	RestTemplate restTemplate = new RestTemplate();

	final String baseUrl = "http://localhost:8081/dniParser/" + dni.getDni();

	MedicalHistoryJsonDto jsonInfo = restTemplate.getForEntity(baseUrl, MedicalHistoryJsonDto.class).getBody();

	if (jsonInfo.getDiseases() != null) {
	    jsonInfo.getDiseases().forEach(d -> {
		DiseaseDto disease = new DiseaseDto(d);
		diseases.add(disease);
	    });
	}

	if (jsonInfo.getAllergies() != null) {
	    jsonInfo.getAllergies().forEach(a -> {
		AllergyDto allergy = new AllergyDto(a);
		allergies.add(allergy);
	    });
	}

	if (jsonInfo.getIntolerances() != null) {
	    jsonInfo.getIntolerances().forEach(i -> {
		IntoleranceDto intolerance = new IntoleranceDto(i);
		intolerances.add(intolerance);
	    });
	}

	if (jsonInfo.getActualTreatments() != null) {
	    jsonInfo.getActualTreatments().forEach(t -> {
		TreatmentDto treatment = new TreatmentDto(t);
		medicaments.add(treatment);
	    });
	}

	MedicalHistoryDto historyDto = new MedicalHistoryDto(jsonInfo.getEdad(), jsonInfo.getSexo(),
		jsonInfo.isEmbarazo(), jsonInfo.isLactancia(), jsonInfo.getGlomerularFiltration(),
		jsonInfo.isLiverFailure(), medicaments, allergies, diseases, intolerances);

	model.addAttribute("dni", dni);
	model.addAttribute("history", historyDto);
	model.addAttribute("sexos", sexos);
	model.addAttribute("allergies", allergiesList);
	model.addAttribute("diseases", diseasesList);
	model.addAttribute("intolerances", intolerancesList);

	return "medicalHistoryForm";
    }

    @PostMapping("/medicamentForm")
    public String medicamentForm(@ModelAttribute("history") MedicalHistoryDto historyDto,
	    @RequestParam(value = "dis", required = false) String[] dis,
	    @RequestParam(value = "als", required = false) String[] als,
	    @RequestParam(value = "ints", required = false) String[] ints,
	    @RequestParam(value = "meds", required = false) String[] meds, Model model) {

	ArrayList<DiseaseDto> diseases = new ArrayList<DiseaseDto>();
	ArrayList<AllergyDto> allergies = new ArrayList<AllergyDto>();
	ArrayList<IntoleranceDto> intolerances = new ArrayList<IntoleranceDto>();
	ArrayList<TreatmentDto> medicaments = new ArrayList<TreatmentDto>();
	ArrayList<TreatmentDto> medicamentsInUse = new ArrayList<TreatmentDto>();

	if (dis != null) {
	    for (int i = 0; i < dis.length; i++) {
		DiseaseDto disease = new DiseaseDto(dis[i]);
		diseases.add(disease);
	    }
	}
	if (als != null) {
	    for (int i = 0; i < als.length; i++) {
		AllergyDto allergy = new AllergyDto(als[i]);
		allergies.add(allergy);
	    }
	}
	if (ints != null) {
	    for (int i = 0; i < ints.length; i++) {
		IntoleranceDto intolerance = new IntoleranceDto(ints[i]);
		intolerances.add(intolerance);
	    }
	}

	if (meds != null) {
	    for (int i = 0; i < meds.length; i++) {
		TreatmentDto medicament = new TreatmentDto(meds[i]);
		medicamentsInUse.add(medicament);
	    }
	}

	listService.listAllCommercialMedicaments().forEach(m -> {
	    TreatmentDto treatment = new TreatmentDto(m.getName());
	    medicaments.add(treatment);
	});

	historyDto.setAllergies(allergies);
	historyDto.setDiseases(diseases);
	historyDto.setIntolerances(intolerances);
	historyDto.setActualTreatments(medicamentsInUse);

	model.addAttribute("history", historyDto);
	model.addAttribute("medicaments", medicaments);

	return "medicamentForm";
    }

    @PostMapping("/results")
    public String results(@ModelAttribute("history") MedicalHistoryDto historyDto,
	    @RequestParam(value = "dis", required = false) String[] dis,
	    @RequestParam(value = "als", required = false) String[] als,
	    @RequestParam(value = "ints", required = false) String[] ints,
	    @RequestParam(value = "meds", required = false) String[] meds,
	    @RequestParam(value = "medRecet", required = false) String medRecet,
	    @RequestParam(value = "compRecetados", required = false) boolean compRecetados, Model model)
	    throws InstanceNotFoundException {

	ArrayList<Treatment> medicaments = new ArrayList<Treatment>();
	Treatment treatment = null;

	if (meds != null) {
	    for (int i = 0; i < meds.length; i++) {
		Treatment medicament = new Treatment(meds[i]);
		medicaments.add(medicament);
	    }
	}

	if (medRecet != null) {
	    treatment = new Treatment(medRecet);
	}

	ArrayList<Allergy> allergies = new ArrayList<Allergy>();
	ArrayList<Disease> diseases = new ArrayList<Disease>();
	ArrayList<Intolerance> intolerances = new ArrayList<Intolerance>();

	if (dis != null) {
	    for (int i = 0; i < dis.length; i++) {
		Disease disease = new Disease(dis[i]);
		diseases.add(disease);
	    }
	}

	if (als != null) {
	    for (int i = 0; i < als.length; i++) {
		Allergy allergy = new Allergy(als[i]);
		allergies.add(allergy);
	    }
	}

	if (ints != null) {
	    for (int i = 0; i < ints.length; i++) {
		Intolerance intolerance = new Intolerance(ints[i]);
		intolerances.add(intolerance);
	    }
	}

	MedicalHistory history = new MedicalHistory(historyDto.getEdad(), historyDto.getSexo(), historyDto.isEmbarazo(),
		historyDto.isLactancia(), historyDto.getGlomerularFiltration(), historyDto.isLiverFailure(),
		medicaments, allergies, diseases, intolerances);

	List<Alert> alerts = medicalService.completeReport(history, treatment, compRecetados);

	ArrayList<MedicamentDto> medicamentsDto = new ArrayList<MedicamentDto>();
	Boolean medicamentsBool = true;
	if (!history.getActualTreatments().isEmpty()) {
	    history.getActualTreatments().forEach(t -> {
		List<ChemicalComponentDto> comps = new ArrayList<ChemicalComponentDto>();
		try {
		    List<ChemicalComponent> chemComponents = medicalService.newChemicalComponentList(t);
		    if (!chemComponents.isEmpty()) {
			chemComponents.forEach(c -> {
			    ChemicalComponentDto cc = new ChemicalComponentDto(c.getComponentName(), c.getFamily());
			    comps.add(cc);
			});
		    }
		} catch (InstanceNotFoundException e) {
		    e.printStackTrace();
		}
		MedicamentDto medicament = new MedicamentDto(t.getCommercialMedicamentName(), comps);
		medicamentsDto.add(medicament);
	    });
	} else {
	    medicamentsBool = false;
	}

	if (!compRecetados) {
	    medicamentsBool = false;
	}

	MedicamentDto medicamentRecet = null;
	Boolean treatmentBool = true;
	if (treatment != null) {
	    List<ChemicalComponentDto> compsRecet = new ArrayList<ChemicalComponentDto>();
	    try {
		List<ChemicalComponent> chemComponentsRecet = medicalService.newChemicalComponentList(treatment);
		if (!chemComponentsRecet.isEmpty()) {
		    chemComponentsRecet.forEach(c -> {
			ChemicalComponentDto cc = new ChemicalComponentDto(c.getComponentName(), c.getFamily());
			compsRecet.add(cc);
		    });
		}
	    } catch (InstanceNotFoundException e) {
		e.printStackTrace();
	    }
	    medicamentRecet = new MedicamentDto(treatment.getCommercialMedicamentName(), compsRecet);
	} else {
	    treatmentBool = false;
	}

	ArrayList<DiseaseDto> diseasesDto = new ArrayList<DiseaseDto>();
	ArrayList<AllergyDto> allergiesDto = new ArrayList<AllergyDto>();
	ArrayList<IntoleranceDto> intolerancesDto = new ArrayList<IntoleranceDto>();
	ArrayList<TreatmentDto> medicamentsInUse = new ArrayList<TreatmentDto>();

	if (dis != null) {
	    for (int i = 0; i < dis.length; i++) {
		DiseaseDto disease = new DiseaseDto(dis[i]);
		diseasesDto.add(disease);
	    }
	}
	if (als != null) {
	    for (int i = 0; i < als.length; i++) {
		AllergyDto allergy = new AllergyDto(als[i]);
		allergiesDto.add(allergy);
	    }
	}
	if (ints != null) {
	    for (int i = 0; i < ints.length; i++) {
		IntoleranceDto intolerance = new IntoleranceDto(ints[i]);
		intolerancesDto.add(intolerance);
	    }
	}

	if (meds != null) {
	    for (int i = 0; i < meds.length; i++) {
		TreatmentDto medicament = new TreatmentDto(meds[i]);
		medicamentsInUse.add(medicament);
	    }
	}

	historyDto.setAllergies(allergiesDto);
	historyDto.setDiseases(diseasesDto);
	historyDto.setIntolerances(intolerancesDto);
	historyDto.setActualTreatments(medicamentsInUse);

	Boolean alertsEmpty = alerts.isEmpty();

	model.addAttribute("history", historyDto);
	model.addAttribute("medicaments", medicamentsDto);
	model.addAttribute("medicamentsBool", medicamentsBool);
	model.addAttribute("medRecet", medicamentRecet);
	model.addAttribute("treatmentBool", treatmentBool);
	model.addAttribute("alerts", alerts);
	model.addAttribute("alertsEmpty", alertsEmpty);
	return "results";
    }

    @PostMapping("/resultsJson")
    @ResponseBody
    public List<Alert> results(@RequestBody MedicalInfoJsonDto jsonInfo) throws InstanceNotFoundException {

	ArrayList<Treatment> medicaments = new ArrayList<Treatment>();
	Treatment treatment = null;

	if (jsonInfo.getMedRecet() != null && jsonInfo.getMedRecet() != "") {
	    treatment = new Treatment(jsonInfo.getMedRecet());
	}

	ArrayList<Allergy> allergies = new ArrayList<Allergy>();
	ArrayList<Disease> diseases = new ArrayList<Disease>();
	ArrayList<Intolerance> intolerances = new ArrayList<Intolerance>();

	if (jsonInfo.getDiseases() != null) {
	    jsonInfo.getDiseases().forEach(d -> {
		Disease disease = new Disease(d);
		diseases.add(disease);
	    });
	}

	if (jsonInfo.getAllergies() != null) {
	    jsonInfo.getAllergies().forEach(a -> {
		Allergy allergy = new Allergy(a);
		allergies.add(allergy);
	    });
	}

	if (jsonInfo.getIntolerances() != null) {
	    jsonInfo.getIntolerances().forEach(i -> {
		Intolerance intolerance = new Intolerance(i);
		intolerances.add(intolerance);
	    });
	}

	MedicalHistory history = new MedicalHistory(jsonInfo.getEdad(), jsonInfo.getSexo(), jsonInfo.isEmbarazo(),
		jsonInfo.isLactancia(), jsonInfo.getGlomerularFiltration(), jsonInfo.isLiverFailure(), medicaments,
		allergies, diseases, intolerances);

	List<Alert> alerts = medicalService.completeReport(history, treatment, jsonInfo.getCompRecetados());

	return alerts;
    }

}
