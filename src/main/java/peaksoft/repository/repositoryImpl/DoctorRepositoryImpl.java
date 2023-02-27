package peaksoft.repository.repositoryImpl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.hibernate.HibernateException;
import org.springframework.stereotype.Repository;

import peaksoft.model.Department;
import peaksoft.model.Doctor;
import peaksoft.model.Hospital;
import peaksoft.repository.DoctorRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
@Repository
@Transactional
public class DoctorRepositoryImpl  implements DoctorRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Doctor save(Long id,Doctor doctor) {
            Hospital hospital = entityManager.find(Hospital.class, id);
            hospital.getDoctors().add(doctor);
            doctor.setHospital(hospital);
            entityManager.merge(hospital);
            return doctor;
    }
    @Override
    public List<Doctor> getAllDoctors() {
           return entityManager.createQuery("select d from Doctor d ", Doctor.class).getResultList();
    }

    @Override
    public Doctor getDoctorById(Long id) {
           return entityManager.find(Doctor.class, id);
    }

    @Override
    public void deleteDoctor(Long id) {

        try{
            entityManager.createQuery("delete from Doctor d where d.id = :id")
                    .setParameter("id",id).executeUpdate();
        }catch (HibernateException e){
            throw new RuntimeException(e.getMessage());
        }

    }

    @Override
    public List<Doctor> getAll(Long id) {
            return entityManager.createQuery("select d from Doctor d join d.hospital h where h.id=:id", Doctor.class)
                    .setParameter("id", id)
                    .getResultList();
    }
    @Override
    @Transactional
    public void updateDoctor(Long id, Doctor updatedDoctor) {
           Doctor doctor = entityManager.find(Doctor.class, id);
           doctor.setFirstName(updatedDoctor.getFirstName());
           doctor.setLastName(updatedDoctor.getLastName());
           doctor.setPosition(updatedDoctor.getPosition());
           doctor.setEmail(updatedDoctor.getEmail());
           entityManager.merge(doctor);

    }

    @Override
    public List<Doctor> findDoctorsByHospitalId(Long hospitalId) {
        return entityManager.createQuery("select d from Doctor d where d.hospital.id = ?1", Doctor.class)
                .setParameter(1, hospitalId)
                .getResultList();


    }

}
