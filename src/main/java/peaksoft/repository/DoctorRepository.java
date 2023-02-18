package peaksoft.repository;

import peaksoft.model.Doctor;

import java.util.List;

public interface DoctorRepository {
    void save(Doctor doctor,Long hospitalId);



    List<Doctor> getAllDoctors();



    Doctor getDoctorById(Long id);



    void deleteDoctor(Long id);



    void updateDoctor( Long id,Doctor updatedDoctor);
    void assignDoctorToDepartment(Long doctorId,Long departmentId);
}
