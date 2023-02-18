package peaksoft.repository;

import peaksoft.model.Patient;

import java.util.List;

public interface PatientRepository {
    void save(Patient patient,Long hospitalId);



    List<Patient> getAllPatients();



    Patient getPatientById(Long id);



    void deletePatient(Long id);



    void updatePatient( Long id,Patient updatedPatient);
}
