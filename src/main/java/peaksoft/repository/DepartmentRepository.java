package peaksoft.repository;

import peaksoft.model.Department;

import java.util.List;

public interface DepartmentRepository {
    void save(Department department,Long hospitalId);



    List<Department> getAllDepartments();



    Department getDepartmentById(Long id);



    void deleteDepartment(Long id);



    void updateDepartment( Long id,Department updatedDepartment);
    }
