package peaksoft.service;

import peaksoft.model.Department;

import java.util.List;

public interface DepartmentService {
    void save(Department department,Long hospitalId);



    List<Department> getAllDepartments();



    Department getDepartmentById(Long id);



    void deleteDepartment(Long id);



    void updateDepartment( Long id,Department updatedDepartment);
}
