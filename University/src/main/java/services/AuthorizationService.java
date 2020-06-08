package services;
import domain.AccessToken;
import domain.Students;
import domain.UserLoginData;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import repositories.StudentRepository;
import repositories.interfaces.IStudentRepository;
import services.intefaces.IAuthorizationService;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Random;


public class AuthorizationService implements IAuthorizationService {
    private final IStudentRepository studRepository = new StudentRepository();

    public AccessToken authenticateStudent(UserLoginData data) throws Exception {
        Students authenticatedStudent = signIn(data);
        return new AccessToken(issueToken(authenticatedStudent));
    }

    @Override
    public Students getStudentByUsername(String username) {
        return StudentRepository.getStudentsByUsername(username);
    }


    private Students signIn(UserLoginData data) throws Exception {
        Students Student = StudentRepository.findStudentsByLogin(data);
        if (Student == null) {
            throw new Exception("Authentication failed!");
        }
        return Student;
    }

    private String issueToken(Students student) {
        Instant now = Instant.now();
        String secretWord = "TheStrongestSecretKeyICanThinkOf";
        return Jwts.builder()
                .setIssuer(student.getUsername())
                .setIssuedAt(Date.from(now))
                .claim("1d20", new Random().nextInt(20) + 1)
                .setExpiration(Date.from(now.plus(10, ChronoUnit.MINUTES)))
                .signWith(Keys.hmacShaKeyFor(secretWord.getBytes()))
                .compact();
    }

}
