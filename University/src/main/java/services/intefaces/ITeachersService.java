package services.intefaces;

import domain.Teachers;
import domain.UserLoginData;

public interface ITeachersService {
    Teachers getTeachersByID(long id);

    Teachers getTeachersByUsername(String username);
}