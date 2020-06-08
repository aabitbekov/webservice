package services.intefaces;

import domain.Department;
import domain.Students;
import domain.UserLoginData;

public interface IStudentService {

    Students getStudentsByID(long id);

    Students findStudentByDepartment(Department department);

    Students getStudentsByUsername(String username);
}
