package peaksoft.repository.repositoryImpl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import peaksoft.model.Department;
import peaksoft.model.Doctor;
import peaksoft.repository.DepartmentRepository;

import java.util.List;
@Repository
@Transactional
public class DepartmentRepositoryImpl  implements DepartmentRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Department save(Department department) {
            entityManager.persist(department);
            return department;
    }


    @Override
    public List<Department> getAllDepartments(Long hospitalId) {
            return entityManager.createQuery("select d from Department d join d.hospital h where h.id=:id", Department.class)
                    .setParameter("id", hospitalId).getResultList();
    }

    @Override
    public Department getDepartmentById(Long id) {
            return entityManager.find(Department.class, id);
    }

    @Override
    public void deleteDepartment(Long id) {
            entityManager.remove(entityManager.find(Department.class, id));

    }

    @Override
    public void updateDepartment(Long id, Department updatedDepartment) {
            Department department = entityManager.find(Department.class, id);
            department.setName(updatedDepartment.getName());
            entityManager.merge(department);
}

    @Override
    public List<Department> findDepartmentsByHospitalId(Long hospitalId) {
        return entityManager.createQuery("select d from Department d where d.hospital.id = ?1", Department.class)
                .setParameter(1, hospitalId)
                .getResultList();
    }


}