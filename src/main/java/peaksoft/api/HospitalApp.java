package peaksoft.api;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.model.Hospital;
import peaksoft.service.HospitalService;

@Controller
@RequestMapping("/hospitals")
public class HospitalApp {

   private final HospitalService hospitalService;

    public HospitalApp(HospitalService hospitalService) {
        this.hospitalService = hospitalService;
    }
    @GetMapping
    public String getAllHospitals(Model model){
        model.addAttribute("hospitals",hospitalService.getAllHospitals());
        return "hospital/mainPage";

    }
    @GetMapping("/new")
    public String create(Model model){
        model.addAttribute("newHospital",new Hospital());
        return "hospital/newHospital";
    }
    @PostMapping("/savePage")
    public String save(@ModelAttribute("newHospital")Hospital hospital){
        hospitalService.save(hospital);
        return "redirect:/hospitals";
    }
    @DeleteMapping("{hospitalId}/delete")
    public String delete(@PathVariable("hospitalId")Long id){
        hospitalService.deleteHospital(id);
        return "redirect:/hospitals";
    }
    @GetMapping("/{hospitalId}/edit")
    public String get(@PathVariable("hospitalId")Long id,Model model){
        Hospital hospital = hospitalService.getHospitalById(id);
        model.addAttribute("hospital",hospital);
        return "hospital/edit";
    }
    @PutMapping("/{hospitalId}/update")
    public String update(@PathVariable("hospitalId")Long id,@ModelAttribute("hospital")Hospital hospital){
        hospitalService.updateHospital(id, hospital);
        return "redirect:/hospitals";
    }

}
