package htwb.ai.PauliHan;

import javax.ws.rs.ApplicationPath;

import htwb.ai.PauliHan.authentication.AuthorizationFilter;
import htwb.ai.PauliHan.config.DependencyBinder;
import org.glassfish.jersey.server.ResourceConfig;

//import htwb.ai.PauliHan.di.DependencyBinder;

@ApplicationPath("/rest")
public class MyApplication extends ResourceConfig {
    public MyApplication() {
        register(new DependencyBinder());
        register(AuthorizationFilter.class);
        packages("htwb.ai.PauliHan.service");
        packages("htwb.ai.PauliHan.api");
    }
}

