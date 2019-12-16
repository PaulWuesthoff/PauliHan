package htwb.ai.PauliHan.config;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("/rest")
public class ResourceConfigTest extends ResourceConfig{

	public ResourceConfigTest() {
		register(new DependencyBinderTest());
		packages("htwb.ai.PauliHan.service");
	}
}
