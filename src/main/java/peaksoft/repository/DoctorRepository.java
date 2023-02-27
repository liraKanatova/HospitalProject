package peaksoft.repository;

import peaksoft.model.Doctor;

import java.util.List;

public interface DoctorRepository {
    Doctor save(Long id,Doctor doctor);



    List<Doctor> getAllDoctors();



    Doctor getDoctorById(Long id);



    void deleteDoctor(Long id);
    List<Doctor>getAll(Long id);



    void updateDoctor( Long id,Doctor updatedDoctor);

    List<Doctor> findDoctorsByHospitalId(Long hospitalId);


}
