package peaksoft.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
@Entity
@Table(name = "hospitals")
@Getter
@Setter
@NoArgsConstructor
public class Hospital {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "hospital_id_gen")
    @SequenceGenerator(name = "hospital_id_gen",sequenceName = "hospital_seq",allocationSize = 1)
    private Long id;
    private String name;
    private String address;
    private String image;


    @OneToMany(mappedBy = "hospital",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Doctor>doctors;
    public void addDoctor(Doctor doctor){
        if (doctor != null){
           doctors.add(doctor);
        }else {
            throw new NullPointerException();
        }
    }

//    public void addDoctor(Doctor doctor){
//        if(doctors==null){
//            doctors=new ArrayList<>();
//        }
//        doctors.add(doctor);
   // }
    @OneToMany(mappedBy = "hospital",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Patient>patients;
//    public void addPatient(Patient patient){
//        if (patient != null){
//            patients.add(patient);
//        }else {
//            throw new NullPointerException();
//        }
    public void addPatient(Patient patient){
        if ( patient == null){
            patients = new ArrayList<>();
        }
        patients.add(patient);
    }
    @OneToMany (mappedBy = "hospital",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Department>departments;
    public void addDepartment(Department department){
        if (department != null){
            departments.add(department);
        }else {
            throw new NullPointerException();
        }
//    public void aadDepartment(Department department){
//        if(departments==null){
//            departments=new ArrayList<>();
//        }
//        departments.add(department);
  }
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Appointment>appointments;
    public void addAppointment(Appointment appointment){
       if (appointment != null){
           appointments.add(appointment);
       }else {
           throw new NullPointerException();
       }
    }


}
