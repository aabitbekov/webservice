import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ApplicationPath;


@ApplicationPath("/")
public class MainApplication extends ResourceConfig {
   public MainApplication(){
      packages("controllers", "filters");

   }
}
