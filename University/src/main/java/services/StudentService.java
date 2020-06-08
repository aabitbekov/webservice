package services;

import domain.Department;
import domain.Students;
import repositories.StudentRepository;
import repositories.interfaces.IStudentRepository;
import services.intefaces.IStudentService;

public class StudentService implements IStudentService {
    private IStudentRepository Repo = new StudentRepository();


    @Override
    public Students getStudentsByID(long id) {
        return Repo.getStudentsByID(id);
    }

    @Override
    public Students findStudentByDepartment(Department department) {
        return Repo.findStudentByDepartment(department);
    }

    @Override
    public Students getStudentsByUsername(String username) {
        return IStudentRepository.getStudentsByUsername(username);
    }

    public void addUser(Students Student) {
        Repo.add(Student);
    }

    public void updateUser(Students Student) {
        Repo.update(Student);
    }

}
