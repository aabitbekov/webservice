package services;

import domain.AccessToken;
import domain.Teachers;
import domain.UserLoginData;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import repositories.TeacherRepository;
import repositories.interfaces.ITeacherRepository;
import services.intefaces.IAuthorizationServiceForTeachers;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Random;

public class AuthServiceForTeachers implements IAuthorizationServiceForTeachers {
    private final ITeacherRepository TeachRepository = new TeacherRepository();

    public AccessToken authenticateTeachers(UserLoginData data) throws Exception {
        Teachers authenticatedStudent = signIn(data);
        return new AccessToken(issueToken(authenticatedStudent));
    }

    @Override
    public Teachers getTeacherByUsername(String issuer) {
        return TeacherRepository.getTeachersByUsername(issuer);
    }


    private Teachers signIn(UserLoginData data) throws Exception {
        Teachers Teacher = TeacherRepository.findTeachersByLogin(data);
        if (Teacher == null) {
            throw new Exception("Authentication failed!");
        }
        return Teacher;
    }

    private String issueToken(Teachers Teacher) {
        Instant now = Instant.now();
        String secretWord = "TheStrongestSecretKeyICanThinkOf";
        return Jwts.builder()
                .setIssuer(Teacher.getUsername())
                .setIssuedAt(Date.from(now))
                .claim("1d20", new Random().nextInt(20) + 1)
                .setExpiration(Date.from(now.plus(10, ChronoUnit.MINUTES)))
                .signWith(Keys.hmacShaKeyFor(secretWord.getBytes()))
                .compact();
    }

}
