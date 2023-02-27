package peaksoft.repository;

import peaksoft.model.Patient;

import java.util.List;

public interface PatientRepository {
    Patient save(Patient patient);



    List<Patient> getAllPatients(Long hospitalId);



    Patient getPatientById(Long id);



    void deletePatient(Long id);



    void updatePatient( Long id,Patient updatedPatient);
}
