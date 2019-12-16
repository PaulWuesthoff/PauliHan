package htwb.ai.PauliHan.config;

import htwb.ai.PauliHan.dao.ISongDAO;
import org.glassfish.jersey.internal.inject.AbstractBinder;


import htwb.ai.PauliHan.model.InMemoryDatabase;


public class DependencyBinderTest extends AbstractBinder {

    @Override
    protected void configure() {
        bind(InMemoryDatabase.class).to(ISongDAO.class);
    }
}
