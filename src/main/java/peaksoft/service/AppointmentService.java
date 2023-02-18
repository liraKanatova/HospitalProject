package peaksoft.service;

import peaksoft.model.Appointment;

import java.util.List;

public interface AppointmentService {
    void save(Appointment appointment,Long hospitalId);



    List<Appointment> getAllAppointments();



    Appointment getAppointmentById(Long id);



    void deleteAppointment(Long id);



    void updateAppointment( Long id,Appointment updatedAppointment);
}
