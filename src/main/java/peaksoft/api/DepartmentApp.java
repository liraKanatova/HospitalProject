package peaksoft.api;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.model.Department;
import peaksoft.service.DepartmentService;
import peaksoft.service.HospitalService;

@Controller
@RequestMapping("/departments")
@RequiredArgsConstructor
public class DepartmentApp {
    private final DepartmentService departmentService;
    private final HospitalService hospitalService;

    @GetMapping("/{hospitalId}")
    public String getAllDepartments(@PathVariable("hospitalId")Long hospitalId,Model model){
        model.addAttribute("departments",departmentService.getAllDepartments(hospitalId));
        model.addAttribute(hospitalId);
        return "department/mainPage";

    }
   @GetMapping("/{hospitalId}/new")
    public String create(@PathVariable("hospitalId")Long hospitalId,Model model){
        model.addAttribute("newDepartment",new Department());
        model.addAttribute(hospitalId);
        return "department/newDepartment";
    }


   @PostMapping("/{hospitalId}/savePage")
    public String save(@PathVariable Long hospitalId,@ModelAttribute("newDepartment")Department department){
        departmentService.save(hospitalId,department);
        return "redirect:/departments/"+hospitalId;
    }
    @DeleteMapping("/{hospitalId}/{departmentId}/delete")
    public String delete(@PathVariable("hospitalId")Long hospitalId,
                         @PathVariable("departmentId")Long departmentId){
        departmentService.deleteDepartment(departmentId);
        return "redirect:/departments/"+hospitalId;
    }
    @GetMapping("/{departmentId}/edit")
    public String get(Model model,@PathVariable("departmentId")Long id){
        Department departmentById = departmentService.getDepartmentById(id);
        model.addAttribute("department",departmentById);
        model.addAttribute("hospitalId",departmentById.getHospital().getId());
        return "department/edit";
    }
    @PutMapping("/{hospitalId}/update/{depId}")
    public String update(@PathVariable("hospitalId") Long hospitalId,
                         @PathVariable("depId") Long departmentId,
                         @ModelAttribute("department")Department department){
        departmentService.updateDepartment(departmentId, department);
        return "redirect:/departments/"+hospitalId;
    }
}
