package controllers;
import domain.AccessToken;
import domain.UserLoginData;
import services.AuthServiceForTeachers;
import services.intefaces.IAuthorizationServiceForTeachers;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("authteachers")
public class AuthControllerForTeachers {
    private final IAuthorizationServiceForTeachers authService = new AuthServiceForTeachers();
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(UserLoginData data) {
        try {
            AccessToken token = authService.authenticateTeachers(data);
            return Response
                    .ok(token)
                    .build();
        } catch (Exception e) {
            return Response
                    .status(Response.Status.UNAUTHORIZED)
                    .entity(e.getMessage())
                    .build();
        }
    }
}