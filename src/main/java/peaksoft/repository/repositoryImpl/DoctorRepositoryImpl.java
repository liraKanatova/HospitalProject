package peaksoft.repository.repositoryImpl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import peaksoft.model.Department;
import peaksoft.model.Doctor;
import peaksoft.model.Hospital;
import peaksoft.repository.DoctorRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
@Repository
@Transactional
public class DoctorRepositoryImpl implements DoctorRepository {
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public void save(Doctor doctor,Long hospitalId) {
        Hospital hospital = entityManager.find(Hospital.class, hospitalId);
        hospital.addDoctor(doctor);
        doctor.setHospital(hospital);
        entityManager.merge(doctor);

    }

    @Override
    public List<Doctor> getAllDoctors() {
        return entityManager.createQuery("select d from Doctor d", Doctor.class).getResultList();
    }

    @Override
    public Doctor getDoctorById(Long id) {
        return entityManager.find(Doctor.class,id);
    }

    @Override
    public void deleteDoctor(Long id) {
       entityManager.remove( entityManager.find(Doctor.class, id));

    }

    @Override
    public void updateDoctor(Long id, Doctor updatedDoctor) {
        Doctor doctor = entityManager.find(Doctor.class, id);
        doctor.setFirstName(updatedDoctor.getFirstName());
        doctor.setLastName(updatedDoctor.getLastName());
        doctor.setPosition(updatedDoctor.getPosition());
        doctor.setEmail(updatedDoctor.getEmail());
        entityManager.merge(doctor);

    }

    @Override
    public void assignDoctorToDepartment(Long doctorId, Long departmentId) {
        Doctor doctor = entityManager.find(Doctor.class, doctorId);
        Department department = entityManager.find(Department.class, departmentId);
        List<Doctor> doctorList = new ArrayList<>(Arrays.asList(doctor));
        List<Department> departmentList = new ArrayList<>(Arrays.asList(department));
        department.setDoctors(doctorList);
        doctor.setDepartments(departmentList);
        entityManager.merge(doctor);
    }
}
