package htwb.ai.PauliHan.config;

import org.glassfish.jersey.internal.inject.AbstractBinder;

import htwb.ai.PauliHan.model.ISongDAO;
import htwb.ai.PauliHan.model.InMemoryDatabase;
import htwb.ai.PauliHan.service.InMemoryDao;

public class DependencyBinderTest extends AbstractBinder{

	@Override
	protected void configure() {
		bind(InMemoryDatabase.class).to(ISongDAO.class);
	}
}
