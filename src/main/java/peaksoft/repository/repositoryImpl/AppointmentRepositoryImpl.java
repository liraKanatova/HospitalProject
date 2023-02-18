package peaksoft.repository.repositoryImpl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import peaksoft.model.Appointment;
import peaksoft.model.Hospital;
import peaksoft.repository.AppointmentRepository;

import java.util.List;
@Repository
@Transactional
public class AppointmentRepositoryImpl implements AppointmentRepository {
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public void save(Appointment appointment,Long hospitalId) {



    }

    @Override
    public List<Appointment> getAllAppointments() {
        return null;
    }

    @Override
    public Appointment getAppointmentById(Long id) {
        return null;
    }

    @Override
    public void deleteAppointment(Long id) {

    }

    @Override
    public void updateAppointment(Long id, Appointment updatedAppointment) {

    }
}
