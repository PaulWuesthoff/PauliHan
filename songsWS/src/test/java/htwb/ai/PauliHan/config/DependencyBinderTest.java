package htwb.ai.PauliHan.config;

import org.glassfish.jersey.internal.inject.AbstractBinder;

import htwb.ai.PauliHan.dao.ISongDAO;
import htwb.ai.PauliHan.dao.InMemoryDao;

public class DependencyBinderTest extends AbstractBinder{

	@Override
	protected void configure() {
		bind(InMemoryDao.class).to(ISongDAO.class);
	}
}
