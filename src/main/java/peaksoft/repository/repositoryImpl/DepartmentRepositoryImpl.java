package peaksoft.repository.repositoryImpl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import peaksoft.model.Department;
import peaksoft.model.Hospital;
import peaksoft.repository.DepartmentRepository;

import java.util.List;
@Repository
@Transactional
public class DepartmentRepositoryImpl implements DepartmentRepository {
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public void save(Department department,Long hospitalId) {
        Hospital hospital = entityManager.find(Hospital.class, hospitalId);
        hospital.aadDepartment(department);
        department.setHospital(hospital);
        entityManager.merge(department);

    }

    @Override
    public List<Department> getAllDepartments() {
        return entityManager.createQuery("select d from Department d", Department.class).getResultList();
    }

    @Override
    public Department getDepartmentById(Long id) {
        return entityManager.find(Department.class,id);
    }

    @Override
    public void deleteDepartment(Long id) {
       entityManager.remove(entityManager.find(Department.class,id));

    }

    @Override
    public void updateDepartment(Long id, Department updatedDepartment) {
        Department department = entityManager.find(Department.class, id);
        department.setName(updatedDepartment.getName());
        entityManager.merge(department);

    }
}
