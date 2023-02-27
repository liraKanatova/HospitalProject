package peaksoft.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.List;

import static jakarta.persistence.CascadeType.*;

@Entity
@Table(name = "departments")
@Getter
@Setter
@NoArgsConstructor
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "department_id_gen")
    @SequenceGenerator(name = "department_id_gen",sequenceName = "department_seq",allocationSize = 1)
    private Long id;
    private String name;
    @ManyToMany(cascade = {REFRESH,PERSIST,MERGE,DETACH})
    private List<Doctor>doctors;
    @ManyToOne(cascade = {PERSIST, REFRESH, MERGE,DETACH})
    private Hospital hospital;
    @Transient
    private Long hospitalId;

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public void addDoctor(Doctor doctor) {
        this.doctors.add(doctor);
    }
}
