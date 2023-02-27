package peaksoft.repository;

import org.springframework.stereotype.Repository;
import peaksoft.model.Department;

import java.util.List;

@Repository

public interface DepartmentRepository {
    Department save(Department department);

    List<Department> getAllDepartments(Long hospitalId);



    Department getDepartmentById(Long id);



    void deleteDepartment(Long id);



    void updateDepartment( Long id,Department updatedDepartment);

    List<Department> findDepartmentsByHospitalId(Long hospitalId);

    void assignDoctor(Long doctorId, Long departmentId);

}
