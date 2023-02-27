package peaksoft.service.serviceImpl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.model.Doctor;
import peaksoft.repository.DepartmentRepository;
import peaksoft.repository.DoctorRepository;
import peaksoft.repository.HospitalRepository;
import peaksoft.service.DoctorService;

import java.util.List;
@Service
@RequiredArgsConstructor
@Transactional
public class DoctorServiceImpl implements DoctorService {
    private final DoctorRepository doctorRepository;
    private final HospitalRepository hospitalRepository;
    private final DepartmentRepository departmentRepository;
    @Override
    public Doctor save(Long id,Doctor doctor) {
         Doctor doctor1 = new Doctor();
         doctor1.setId(doctor.getId());
         doctor1.setFirstName(doctor.getFirstName());
         doctor1.setLastName(doctor.getLastName());
         doctor1.setPosition(doctor.getPosition());
         doctor1.setImage(doctor.getImage());
         doctor1.setEmail(doctor.getEmail());
         doctor1.setHospital(hospitalRepository.getHospitalById(doctor.getHospitalId()));
         doctor1.getDepartmentId().forEach(u -> doctor1.getDepartments().add(departmentRepository.getDepartmentById(u)));
         return doctorRepository.save(id, doctor1);
     }

    @Override
    public List<Doctor> getAllDoctors() {
           return doctorRepository.getAllDoctors();

    }

    @Override
    public Doctor getDoctorById(Long id) {
           return doctorRepository.getDoctorById(id);

    }



    @Override
    public List<Doctor> getAll(Long id) {
           return doctorRepository.getAll(id);

    }

    @Override
    public void deleteDoctor(Long id) {
           doctorRepository.deleteDoctor(id);


    }

    @Override
    @Transactional
    public void updateDoctor(Long id, Doctor updatedDoctor) {
           doctorRepository.updateDoctor(id, updatedDoctor);


    }

    @Override
    public List<Doctor> findDoctorsByHospitalId(Long hospitalId) {
        return doctorRepository.findDoctorsByHospitalId(hospitalId);
    }

}
