package peaksoft.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.*;

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
    @ManyToMany(mappedBy = "doctors",cascade = {PERSIST,REFRESH, MERGE, DETACH})
    private List<Department>departments;
    @OneToMany(mappedBy = "doctor",cascade = {PERSIST,REFRESH, MERGE, DETACH})
    private List<Appointment>appointments;
    @ManyToOne(cascade = {PERSIST,PERSIST,MERGE,DETACH})
    private Hospital hospital;
    @Transient
    private Long hospitalId;
    @Transient
    private List<Long>departmentId=new ArrayList<>();

    @Override
    public String toString() {
        return "Doctor{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", position='" + position + '\'' +
                ", image='" + image + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public void addDepartment(Department department) {
        this.departments.add(department);
    }
}
