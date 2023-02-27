package peaksoft.service;

import peaksoft.model.Patient;

import java.util.List;

public interface PatientService {
    Patient save(Long id,Patient patient);



    List<Patient> getAllPatients(Long hospitalId);



    Patient getPatientById(Long id);



    void deletePatient(Long id);



    void updatePatient( Long id,Patient updatedPatient);
}
