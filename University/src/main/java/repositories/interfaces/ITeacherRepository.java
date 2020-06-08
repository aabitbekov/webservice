package repositories.interfaces;
import domain.Teachers;

public interface ITeacherRepository extends IEntityRepository<Teachers> {
    Teachers getTeachersByID(long id);

    static Teachers getTeachersByUsername(String username) {
        return null;
    }
}

