package peaksoft.api;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import peaksoft.model.Patient;
import peaksoft.service.PatientService;

@Controller
@RequestMapping("/patients")
public class PatientApp {
    private final PatientService patientService;


    public PatientApp(PatientService patientService) {
        this.patientService = patientService;
    }
    @GetMapping
    public String getAllPatients(Model model){
        model.addAttribute("patients",patientService.getAllPatients());
        return "hospital/mainPage";
    }
    @GetMapping("/new")
    public String createPatient(Model model){
        model.addAttribute("newPatient",new Patient());
        return "hospital/newHospital";
    }
//    @PostMapping("/save")
//    public String save(@ModelAttribute("newPatient")Patient patient){
//        patientService.save(patient);
//        return "redirect:/patients";
//    }
}
