package peaksoft.service.serviceImpl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.model.Patient;
import peaksoft.repository.HospitalRepository;
import peaksoft.repository.PatientRepository;
import peaksoft.service.PatientService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {
    private final PatientRepository patientRepository;
    private final HospitalRepository hospitalRepository;

    @Override
    public void save(Patient patient, Long hospitalId) {
        patientRepository.save(patient, hospitalId);
    }

    @Override
    public List<Patient> getAllPatients() {
        return null;
    }

    @Override
    public Patient getPatientById(Long id) {
        return null;
    }

    @Override
    public void deletePatient(Long id) {

    }

    @Override
    public void updatePatient(Long id, Patient updatedPatient) {

    }
}
