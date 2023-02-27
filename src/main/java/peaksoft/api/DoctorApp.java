package peaksoft.api;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.model.Department;
import peaksoft.model.Doctor;
import peaksoft.service.DepartmentService;
import peaksoft.service.DoctorService;
import peaksoft.service.HospitalService;


@Controller
@RequestMapping("/doctors")
@RequiredArgsConstructor
public class DoctorApp {
    private final DoctorService doctorService;
    private final HospitalService hospitalService;
    private final DepartmentService departmentService;

    private Long hospitalId;

    @GetMapping("/{hospitalId}")
    public String getAllDoctors(@PathVariable("hospitalId") Long hospitalId,Model model,@ModelAttribute("department")Department department){
        model.addAttribute("doctors",doctorService.getAll(hospitalId));
        model.addAttribute("departments", departmentService.findDepartmentsByHospitalId(hospitalId));
        model.addAttribute(hospitalId);
        return "doctor/mainPage";
    }
    @GetMapping("/{hospitalId}/new")
    public String create( @PathVariable("hospitalId") Long hospitalId,Model model){
        model.addAttribute("newDoctor",new Doctor());
        model.addAttribute("hospitals",hospitalService.getAllHospitals());
        model.addAttribute("departments",departmentService.getAllDepartments(hospitalId));
        return "doctor/newDoctor";
    }
    @PostMapping("/{hospitalId}/savePage")
    public String save(@PathVariable Long hospitalId,@ModelAttribute("newDoctor")Doctor doctor){
        doctorService.save(hospitalId,doctor);
        return "redirect:/doctors/"+hospitalId;
    }
    @DeleteMapping("/{hospitalId}/{doctorId}/delete")
    public String delete(@PathVariable("hospitalId")Long hospitalId,
                         @PathVariable("doctorId") Long doctorId){
        doctorService.deleteDoctor(doctorId);
        return "redirect:/doctors/"+hospitalId;

    }
    @GetMapping("/{doctorId}/edit")
    public String get(Model model,@PathVariable("doctorId")Long id){
         Doctor doctorById = doctorService.getDoctorById(id);
        model.addAttribute("doctor",doctorById);
        model.addAttribute("hospitalId",doctorById.getHospital().getId());
        return "doctor/edit";
    }
    @PutMapping("/{hospitalId}/update/{docId}")
    public String update(@PathVariable("hospitalId") Long hospitalId,
                         @PathVariable("docId") Long doctorId,
                         @ModelAttribute("doctor")Doctor doctor){
       doctorService.updateDoctor(doctorId, doctor);
        return "redirect:/doctors/"+hospitalId;
    }



}
