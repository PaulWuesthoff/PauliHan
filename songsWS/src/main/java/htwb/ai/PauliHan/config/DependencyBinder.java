package htwb.ai.PauliHan.config;

import htwb.ai.PauliHan.model.ISongDAO;
import htwb.ai.PauliHan.model.IUserDao;
import htwb.ai.PauliHan.model.SongDAOImpl;
import htwb.ai.PauliHan.model.UserDaoImpl;
import org.glassfish.hk2.utilities.binding.AbstractBinder;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DependencyBinder extends AbstractBinder {
    
	@Override
    protected void configure() {
        bind (Persistence.createEntityManagerFactory("songDB-PU")).to(EntityManagerFactory.class);
        bind(SongDAOImpl.class).to(ISongDAO.class);
        bind(UserDaoImpl.class).to(IUserDao.class);
    }
}
