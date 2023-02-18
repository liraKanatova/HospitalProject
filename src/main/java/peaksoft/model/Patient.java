package peaksoft.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import peaksoft.enums.Gender;

import java.util.List;
@Entity
@Table(name="patients")
@Getter
@Setter
@NoArgsConstructor
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "patient_id_gen")
    @SequenceGenerator(name = "patient_id_gen",sequenceName = "patient_seq",allocationSize =1)

private Long id;
    @Column(name = "first_name")
private String firstName;
    @Column(name = "last_name")
private String lastName;
    @Column(name = "phone_number")
private String phoneNumber;
@Enumerated(EnumType.STRING)
private Gender gender;
@Column(unique = true)
private String email;
@ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REFRESH,CascadeType.DETACH})
private Hospital hospital;
@OneToMany(mappedBy = "patient",cascade = {CascadeType.PERSIST,CascadeType.REFRESH,CascadeType.MERGE,CascadeType.DETACH})
private List<Appointment>appointments;
}
