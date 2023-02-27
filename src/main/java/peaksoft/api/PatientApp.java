package peaksoft.api;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.model.Patient;
import peaksoft.service.HospitalService;
import peaksoft.service.PatientService;

@Controller
@RequestMapping("/patients")
@RequiredArgsConstructor
public class PatientApp {
    private final PatientService patientService;
    private final HospitalService hospitalService;


    @GetMapping("/{hospitalId}")
    public String getAllPatients(@PathVariable("hospitalId")Long hospitalId,Model model){
        model.addAttribute("patients",patientService.getAllPatients(hospitalId));
        model.addAttribute(hospitalId);
        return "patient/mainPage";

    }
    @GetMapping("/{hospitalId}/new")
    public String create(@PathVariable("hospitalId")Long hospitalId,Model model){
        model.addAttribute("newPatient",new Patient());
        model.addAttribute(hospitalId);
        return "patient/newPatient";
    }


    @PostMapping("/{hospitalId}/savePage")
    public String save(@PathVariable Long hospitalId,@ModelAttribute("newPatient")Patient patient){
        patientService.save(hospitalId,patient);
        return "redirect:/patients/"+hospitalId;
    }
    @DeleteMapping("/{hospitalId}/{patientId}/delete")
    public String delete(@PathVariable("hospitalId")Long hospitalId,
                         @PathVariable("patientId")Long patientId){
        patientService.deletePatient(patientId);
        return "redirect:/patients/"+hospitalId;
    }
    @GetMapping("/{patientId}/edit")
    public String get(Model model,@PathVariable("patientId")Long id){
        Patient patientById = patientService.getPatientById(id);
        model.addAttribute("patient",patientById);
        model.addAttribute("hospitalId",patientById.getHospital().getId());
        return "patient/edit";
    }
    @PutMapping("/{hospitalId}/update/{patId}")
    public String update(@PathVariable("hospitalId") Long hospitalId,
                         @PathVariable("patId") Long patientId,
                         @ModelAttribute("patient")Patient patient){
        patientService.updatePatient(patientId, patient);
        return "redirect:/patients/"+hospitalId;
    }

}
