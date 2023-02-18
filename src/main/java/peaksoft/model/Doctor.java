package peaksoft.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Entity
@Table(name = "doctors")
@Getter
@Setter
@NoArgsConstructor
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "doctor_id_gen")
    @SequenceGenerator(name = "doctor_id_gen",sequenceName = "doctor_seq",allocationSize = 1)
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    private String position;
    private String image;
    @Column(unique = true)
    private String email;
    @ManyToMany(mappedBy = "doctors",cascade = {CascadeType.PERSIST,CascadeType.REFRESH,CascadeType.MERGE,CascadeType.DETACH})
    private List<Department>departments;
    @OneToMany(mappedBy = "doctor",cascade = {CascadeType.PERSIST,CascadeType.REFRESH,CascadeType.MERGE,CascadeType.DETACH})
    private List<Appointment>appointments;
    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.PERSIST,CascadeType.MERGE,CascadeType.DETACH})
    private Hospital hospital;
}
