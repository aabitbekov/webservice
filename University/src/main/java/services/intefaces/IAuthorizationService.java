package services.intefaces;

import domain.AccessToken;
import domain.Students;
import domain.UserLoginData;

public interface IAuthorizationService {
    AccessToken authenticateStudent(UserLoginData data) throws Exception;
    Students getStudentByUsername(String issuer);

}



