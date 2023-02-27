package peaksoft.repository.repositoryImpl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.hibernate.HibernateException;
import org.springframework.stereotype.Repository;
import peaksoft.model.Appointment;
import peaksoft.model.Department;
import peaksoft.model.Doctor;
import peaksoft.model.Hospital;
import peaksoft.repository.AppointmentRepository;

import java.util.List;
@Repository
@Transactional
public class AppointmentRepositoryImpl  implements AppointmentRepository {
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public Appointment save(Appointment appointment) {
            entityManager.persist(appointment);
            return appointment;

    }

    @Override
    public List<Appointment> getAllAppointments(Long hospitalId) {
           return entityManager.createQuery("select a from Hospital h join h.appointments a where h.id=:id", Appointment.class).setParameter("id",hospitalId).getResultList();
    }

    @Override
    public Appointment getAppointmentById(Long id) {
           return entityManager.find(Appointment.class,id);
    }

    @Override
    public void deleteAppointment(Long id) {
        try{
            entityManager.createQuery("delete from Appointment a where a.id = :id")
                    .setParameter("id",id).executeUpdate();
        }catch (HibernateException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void updateAppointment(Long id, Appointment updatedAppointment) {
           Appointment appointment = entityManager.find(Appointment.class, id);
           appointment.setDate(updatedAppointment.getDate());
           entityManager.merge(appointment);
    }
}
