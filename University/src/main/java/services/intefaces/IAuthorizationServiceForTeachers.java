package services.intefaces;

import domain.AccessToken;
import domain.Teachers;
import domain.UserLoginData;

public interface IAuthorizationServiceForTeachers {
    AccessToken authenticateTeachers(UserLoginData data) throws Exception;
    Teachers getTeacherByUsername(String issuer);
}
