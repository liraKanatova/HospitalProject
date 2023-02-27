package peaksoft.service.serviceImpl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.model.*;
import peaksoft.repository.*;
import peaksoft.service.AppointmentService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class AppointmentServiceImpl implements AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final HospitalRepository hospitalRepository;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;
    private final DepartmentRepository departmentRepository;

    @Transactional
    @Override
    public Appointment save(Long id, Appointment appointment) {

        System.out.println("appointment.getDepartmentId() = " + appointment.getDepartmentId());
        System.out.println("appointment.getDoctorId() = " + appointment.getDoctorId());
        System.out.println("appointment.getPatientId() = " + appointment.getPatientId());
        Appointment appointment1 = new Appointment();
        Hospital hospital = hospitalRepository.getHospitalById(id);
        appointment1.setDate(appointment.getDate());
        appointment1.setId(appointment.getId());
        appointment1.setPatient(patientRepository.getPatientById(appointment.getPatientId()));
        appointment1.setDoctor(doctorRepository.getDoctorById(appointment.getDoctorId()));
        appointment1.setDepartment(departmentRepository.getDepartmentById(appointment.getDepartmentId()));
        hospital.addAppointment(appointment1);
        return appointmentRepository.save(appointment1);
    }

    @Override
    public List<Appointment> getAllAppointments(Long hospitalId) {
        return appointmentRepository.getAllAppointments(hospitalId);
    }

    @Override
    public Appointment getAppointmentById(Long id) {
        return appointmentRepository.getAppointmentById(id);
    }

    @Override
    public void deleteAppointment(Long id) {
        Appointment existApp = appointmentRepository.getAppointmentById(id);
        Hospital hospital = existApp.getDoctor().getHospital();
        hospital.getAppointments().remove(existApp);
        for (Doctor doctor : hospital.getDoctors()) {
            doctor.getAppointments().remove(existApp);
        }
        for (Patient patient : hospital.getPatients()) {
            patient.getAppointments().remove(existApp);
        }
        appointmentRepository.deleteAppointment(id);

    }

    @Override
    public void updateAppointment(Long id, Appointment updatedAppointment) {
        Appointment appointment = getAppointmentById(id);
        appointmentRepository.save(appointment);
        appointment.setDate(updatedAppointment.getDate());
        appointment.setDepartment(departmentRepository.getDepartmentById(updatedAppointment.getDepartmentId()));
        appointment.setDoctor(doctorRepository.getDoctorById(updatedAppointment.getDoctorId()));

    }
}
