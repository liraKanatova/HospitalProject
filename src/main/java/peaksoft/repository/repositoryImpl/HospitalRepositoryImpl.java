package peaksoft.repository.repositoryImpl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import peaksoft.model.Hospital;
import peaksoft.repository.HospitalRepository;

import java.util.List;
@Repository
@Transactional
public class HospitalRepositoryImpl  implements HospitalRepository {
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public Hospital save(Hospital hospital) {
         entityManager.persist(hospital);
         return hospital;

    }

    @Override
    public List<Hospital> getAllHospitals() {
           return entityManager.createQuery("select h from Hospital h", Hospital.class).getResultList();
    }

    @Override
    public Hospital getHospitalById(Long id) {
           return entityManager.find(Hospital.class,id);

    }

    @Override
    public void deleteHospital(Long id) {
          entityManager.remove(entityManager.find(Hospital.class,id));

    }

    @Override
    @Transactional
    public void updateHospital(Long id, Hospital updatedHospital) {
           Hospital hospital = entityManager.find(Hospital.class, id);
           hospital.setName(updatedHospital.getName());
           hospital.setAddress(updatedHospital.getAddress());
           entityManager.merge(hospital);
    }

    @Override
    public List<Hospital> search(String search) {
   return   entityManager.createQuery("""
        select h from Hospital h where h.name ilike (:search)
        """, Hospital.class).setParameter("search","%"+search).getResultList();

    }
}
