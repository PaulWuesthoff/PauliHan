package htwb.ai.PauliHan.config;

import htwb.ai.PauliHan.dao.ISongDAO;
import htwb.ai.PauliHan.dao.IUserDao;
import htwb.ai.PauliHan.dao.SongDAOImpl;
import htwb.ai.PauliHan.dao.UserDaoImpl;
import htwb.ai.PauliHan.authentication.AuthenticatorImpl;
import htwb.ai.PauliHan.authentication.IAuthenticator;
import org.glassfish.hk2.utilities.binding.AbstractBinder;

import javax.inject.Singleton;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DependencyBinder extends AbstractBinder {

    @Override
    protected void configure() {
        bind(Persistence.createEntityManagerFactory("songDB-PU")).to(EntityManagerFactory.class);
        bind(SongDAOImpl.class).to(ISongDAO.class).in(Singleton.class);
        bind(UserDaoImpl.class).to(IUserDao.class).in(Singleton.class);
        bind(AuthenticatorImpl.class).to(IAuthenticator.class).in(Singleton.class);

    }
}
