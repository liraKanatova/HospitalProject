package peaksoft.api;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.model.Hospital;
import peaksoft.service.HospitalService;

@Controller
@RequestMapping("/hospitals")
@RequiredArgsConstructor
public class HospitalApp {

   private final HospitalService hospitalService;

    @GetMapping
    public String getAllHospitals(Model model,@RequestParam(name="search",required = false)String search){
        model.addAttribute("search",search);
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
    @GetMapping("/details/{hospitalId}")
    public String details(@PathVariable("hospitalId") Long hospitalId, Model model){
        model.addAttribute("hospital",hospitalService.getHospitalById(hospitalId));
        return "hospital/details";
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
