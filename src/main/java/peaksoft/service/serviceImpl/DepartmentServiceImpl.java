package peaksoft.service.serviceImpl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import peaksoft.model.Department;
import peaksoft.model.Hospital;
import peaksoft.repository.DepartmentRepository;
import peaksoft.repository.HospitalRepository;
import peaksoft.service.DepartmentService;

import java.util.List;
@Service
@RequiredArgsConstructor
@Transactional
public class DepartmentServiceImpl  implements DepartmentService {
    private final DepartmentRepository departmentRepository;
    private final HospitalRepository hospitalRepository;
    @Override
    public Department save(Long id,Department department) {
            Hospital hospital = hospitalRepository.getHospitalById(id);
            department.setHospital(hospital);
            return departmentRepository.save(department);
    }
    @Override
    public List<Department> getAllDepartments(Long hospitalId) {
           return departmentRepository.getAllDepartments(hospitalId);
    }

    @Override
    public Department getDepartmentById(Long id) {
           return departmentRepository.getDepartmentById(id);
    }

    @Override
    public void deleteDepartment(Long id) {
            departmentRepository.deleteDepartment(id);

    }

    @Override
    public void updateDepartment(Long id, Department updatedDepartment) {
           departmentRepository.updateDepartment(id, updatedDepartment);

    }

    @Override
    public List<Department> findDepartmentsByHospitalId(Long hospitalId) {
        return departmentRepository.findDepartmentsByHospitalId(hospitalId);
    }

    @Override
    public void assignDoctor(Long doctorId, Long departmentId) {
        departmentRepository.assignDoctor(doctorId, departmentId);
    }
}
