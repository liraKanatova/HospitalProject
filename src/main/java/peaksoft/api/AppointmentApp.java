package peaksoft.api;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.model.Appointment;
import peaksoft.model.Hospital;
import peaksoft.service.*;

@Controller
@RequestMapping("/appointments")
@RequiredArgsConstructor
public class AppointmentApp {
    private final AppointmentService appointmentService;
    private final DepartmentService departmentService;
    private final DoctorService doctorService;
    private final HospitalService hospitalService;
    private final PatientService patientService;

    @GetMapping("/{hospitalId}")
    public String getAll(@PathVariable("hospitalId") Long hospitalId, Model model) {
        model.addAttribute("hospitalAppointments", hospitalService.getHospitalById(hospitalId).getAppointments());
        return "appointment/mainPage";

    }

    @GetMapping("/{hospitalId}/new")
    public String create(@PathVariable("hospitalId") Long hospitalId, Model model) {
        model.addAttribute("newAppointment", new Appointment());
        model.addAttribute("departments", departmentService.getAllDepartments(hospitalId));
        model.addAttribute("doctors", doctorService.getAll(hospitalId));
        model.addAttribute("patients", patientService.getAllPatients(hospitalId));
        model.addAttribute(hospitalId);
        return "appointment/newAppointment";
    }


    @PostMapping("/{hospitalId}/savePage")
    public String save(@PathVariable Long hospitalId,
                       @ModelAttribute("newAppointment") Appointment appointment) {
        appointmentService.save(hospitalId, appointment);
        return "redirect:/appointments/" + hospitalId;
    }

    @DeleteMapping("/{hospitalId}/{appointmentId}/delete")
    public String delete(@PathVariable("hospitalId") Long hospitalId,
                         @PathVariable("appointmentId") Long appointmentId) {
        appointmentService.deleteAppointment(appointmentId);
        return "redirect:/appointments/" + hospitalId;

    }
    @GetMapping("/{hospitalId}/{appointmentId}/edit")
    public String edit(Model model, @PathVariable("hospitalId") Long hospitalId,
                       @PathVariable("appointmentId") Long appointmentId){
        model.addAttribute("appointment", appointmentService.getAppointmentById(appointmentId));
        model.addAttribute("hospitalId", hospitalId);
        model.addAttribute("patients", patientService.getAllPatients(hospitalId));
        model.addAttribute("doctors", doctorService.getAll(hospitalId));
        model.addAttribute("departments", departmentService.getAllDepartments(hospitalId));
        return "appointment/updatePage";
    }

    @PostMapping("/{hospitalId}/{appointmentId}/update")
    public String update( @PathVariable("hospitalId") Long hospitalId,
                          @PathVariable("appointmentId") Long appointmentId,
                          @ModelAttribute("appointment") Appointment newAppointment){
        appointmentService.updateAppointment( appointmentId,newAppointment);
        return "redirect:/appointments/"+hospitalId;
    }



//    @GetMapping("/{hospitalId}/{appointmentId}")
//    String updateForm(@PathVariable Long hospitalId,
//                      @PathVariable Long appointmentId,
//                      Model model) {
//        Appointment appointment = appointmentService.getAppointmentById(appointmentId);
//        model.addAttribute("appointment", appointment);
//        model.addAttribute("departments", departmentService.findDepartmentsByHospitalId(hospitalId));
//        model.addAttribute("doctors", doctorService.findDoctorsByHospitalId(hospitalId));
//        return "appointment/updatePage";
//    }
//
//    @PostMapping("/{hospitalId}/{appointmentId}/update")
//    public String update(@PathVariable Long hospitalId,
//                         @PathVariable Long appointmentId) {
//        appointmentService.updateAppointment(appointmentId, appointmentService.getAppointmentById(appointmentId));
//        return "redirect: /appointments/"+hospitalId;
//    }

}

  //    @GetMapping("/{hospitalId}/{appointmentId}/edit")
//    public String get(@PathVariable("hospitalId") Long hospitalId,@PathVariable("appointmentId")Long id,Model model){
//        model.addAttribute("appointment",appointmentService.getAppointmentById(id));
//        model.addAttribute("hospitalId",hospitalId);
//        return "appointment/edit";
//    }
//    @PutMapping("/{hospitalId}/{appointmentId}/update")
//    public String update(@PathVariable("hospitalId") Long hospitalId,
//                         @PathVariable("appointmentId") Long appointmentId,
//                         @ModelAttribute("appointment")Appointment appointment){
//       appointmentService.updateAppointment(appointmentId, appointment);
//        return "redirect:/appointments/"+hospitalId;
//    }


