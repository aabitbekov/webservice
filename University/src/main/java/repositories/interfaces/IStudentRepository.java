package repositories.interfaces;

import domain.Department;
import domain.Students;

public interface IStudentRepository extends IEntityRepository<Students>{
    Students getStudentsByID(long id);

    static Students getStudentsByUsername(String username) {
        return null;
    }

    Students findStudentByDepartment(Department department);
}
