package services;
import domain.Teachers;
import repositories.TeacherRepository;
import repositories.interfaces.ITeacherRepository;
import services.intefaces.ITeachersService;

public class TeacherService implements ITeachersService {
    private ITeacherRepository Repo = new TeacherRepository();

    @Override
    public Teachers getTeachersByID(long id) {
        return Repo.getTeachersByID(id);
    }


    @Override
    public Teachers getTeachersByUsername(String username) {
        return ITeacherRepository.getTeachersByUsername(username);

    }
}
