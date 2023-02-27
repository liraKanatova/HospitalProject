package peaksoft.service;

import org.springframework.stereotype.Service;
import peaksoft.model.Appointment;

import java.util.List;
@Service
public interface AppointmentService {
    Appointment save(Long id,Appointment appointment);



    List<Appointment> getAllAppointments(Long hospitalId);



    Appointment getAppointmentById(Long id);



    void deleteAppointment(Long id);



    void updateAppointment( Long id,Appointment updatedAppointment);
}
