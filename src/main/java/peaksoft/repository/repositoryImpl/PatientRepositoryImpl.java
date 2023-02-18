package peaksoft.repository.repositoryImpl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import peaksoft.model.Hospital;
import peaksoft.model.Patient;
import peaksoft.repository.PatientRepository;

import java.util.List;
@Repository
@Transactional
public class PatientRepositoryImpl implements PatientRepository {
    @PersistenceContext
    private  EntityManager entityManager;



    @Override
    public void save(Patient patient,Long hospitalId) {
        Hospital hospital = entityManager.find(Hospital.class, hospitalId);
        hospital.addPatient(patient);
        patient.setHospital(hospital);
        entityManager.merge(patient);

    }

    @Override
    public List<Patient> getAllPatients() {
        return entityManager.createQuery("select p from Patient p", Patient.class).getResultList();
    }

    @Override
    public Patient getPatientById(Long id) {
        return entityManager.find(Patient.class,id);

    }

    @Override
    public void deletePatient(Long id) {
        entityManager.remove(entityManager.find(Patient.class,id));

    }

    @Override
    public void updatePatient(Long id, Patient updatedPatient) {
        Patient patient = entityManager.find(Patient.class, id);
        patient.setFirstName(patient.getFirstName());
        patient.setLastName(patient.getLastName());
        patient.setPhoneNumber(patient.getPhoneNumber());
        patient.setGender(patient.getGender());
        patient.setEmail(patient.getEmail());
        entityManager.merge(patient);
    }

}
