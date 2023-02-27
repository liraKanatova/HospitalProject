package peaksoft.service;

import org.springframework.stereotype.Service;
import peaksoft.model.Department;

import java.util.List;
@Service

public interface DepartmentService {
    Department save(Long id,Department department);



    List<Department> getAllDepartments(Long hospitalId);



    Department getDepartmentById(Long id);



    void deleteDepartment(Long id);



    void updateDepartment( Long id,Department updatedDepartment);

    List<Department> findDepartmentsByHospitalId(Long hospitalId);


}
