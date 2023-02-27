package peaksoft.service;

import org.springframework.stereotype.Service;
import peaksoft.model.Doctor;

import java.util.List;
@Service
public interface DoctorService {
    Doctor save(Long id,Doctor doctor);



    List<Doctor> getAllDoctors();



    Doctor getDoctorById(Long id);



    List<Doctor> getAll(Long id);

    void deleteDoctor(Long id);



    void updateDoctor( Long id,Doctor updatedDoctor);

    List<Doctor> findDoctorsByHospitalId(Long hospitalId);
}
