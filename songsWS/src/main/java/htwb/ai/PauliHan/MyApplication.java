package htwb.ai.PauliHan;
import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;

//import htwb.ai.PauliHan.di.DependencyBinder;

@ApplicationPath("/rest")
public class MyApplication extends ResourceConfig {
    public MyApplication() {
//        register(new DependencyBinder());
        packages("htwb.ai.PauliHan.services");
    }
}

