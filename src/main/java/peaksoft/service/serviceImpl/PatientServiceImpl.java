package peaksoft.service.serviceImpl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.model.Hospital;
import peaksoft.model.Patient;
import peaksoft.repository.HospitalRepository;
import peaksoft.repository.PatientRepository;
import peaksoft.service.PatientService;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class PatientServiceImpl  implements PatientService {
    private final PatientRepository patientRepository;
    private final HospitalRepository hospitalRepository;

    @Override
    public Patient save(Long id,Patient patient) {
           Hospital hospital = hospitalRepository.getHospitalById(id);
           patient.setHospital(hospital);
           return patientRepository.save(patient);
    }

    @Override
    public List<Patient> getAllPatients(Long hospitalId) {
            return patientRepository.getAllPatients(hospitalId);
    }

    @Override
    public Patient getPatientById(Long id) {
           return patientRepository.getPatientById(id);
    }

    @Override
    public void deletePatient(Long id) {
           patientRepository.deletePatient(id);

    }

    @Override
    public void updatePatient(Long id, Patient updatedPatient) {
           patientRepository.updatePatient(id, updatedPatient);

    }
}
