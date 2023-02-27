package peaksoft.repository.repositoryImpl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.hibernate.HibernateException;
import org.springframework.stereotype.Repository;
import peaksoft.model.Patient;
import peaksoft.repository.PatientRepository;

import java.util.List;
@Repository
@Transactional
public class PatientRepositoryImpl implements PatientRepository  {
    @PersistenceContext
    private  EntityManager entityManager;



    @Override
    public Patient save(Patient patient) {
          entityManager.persist(patient);
          return patient;
    }

    @Override
    public List<Patient> getAllPatients(Long hospitalId) {
           return entityManager.createQuery("select p from Patient p join p.hospital h where h.id = :id", Patient.class)
                   .setParameter("id",hospitalId).getResultList();
    }

    @Override
    public Patient getPatientById(Long id) {
           return entityManager.find(Patient.class,id);

    }
    @Override
    public void deletePatient(Long id) {
        try{
            entityManager.createQuery("delete from Patient p where p.id = :id")
                    .setParameter("id",id).executeUpdate();
        }catch (HibernateException e){
            throw new RuntimeException(e.getMessage());
        }


    }

    @Override
    public void updatePatient(Long id, Patient updatedPatient) {
            Patient patient = entityManager.find(Patient.class, id);
            patient.setFirstName(updatedPatient.getFirstName());
            patient.setLastName(updatedPatient.getLastName());
            patient.setPhoneNumber(updatedPatient.getPhoneNumber());
            patient.setGender(updatedPatient.getGender());
            patient.setEmail(updatedPatient.getEmail());
            entityManager.merge(patient);
    }
}
