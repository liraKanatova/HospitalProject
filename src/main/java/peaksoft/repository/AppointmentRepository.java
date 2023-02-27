package peaksoft.repository;

import peaksoft.model.Appointment;

import java.util.List;

public interface AppointmentRepository {
    Appointment save(Appointment appointment);



    List<Appointment> getAllAppointments(Long hospitalId);



    Appointment getAppointmentById(Long id);



    void deleteAppointment(Long id);



    void updateAppointment( Long id,Appointment updatedAppointment);
}
