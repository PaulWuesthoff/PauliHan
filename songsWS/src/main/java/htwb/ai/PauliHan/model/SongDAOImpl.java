package htwb.ai.PauliHan.model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.*;
import java.util.List;

public class SongDAOImpl implements ISongDAO {

    private EntityManagerFactory entityManagerFactory;
    private SessionFactory sessionFactory;

    public SongDAOImpl(String persistenceUnit) {
        this.entityManagerFactory = Persistence.createEntityManagerFactory(persistenceUnit);
    }

    @Override
    public Song getSong(Integer id) {
        EntityManager em = null;
        try {
            em = entityManagerFactory.createEntityManager();
            return em.find(Song.class, id);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public List<Song> getSongs() {
        return null;
    }

    @Override
    public Integer addSong(Song song) {
        EntityManager manager = null;
        EntityTransaction transaction = null;

        try {
            manager = entityManagerFactory.createEntityManager();
            transaction = manager.getTransaction();
            transaction.begin();
            manager.persist(song);
            transaction.commit();
            return song.getId();
        } catch (IllegalStateException | EntityExistsException | RollbackException e) {
            if (manager != null) {
                manager.getTransaction().rollback();
            }
            throw new PersistenceException(e.getMessage());

        } finally {
            if (manager != null) {
                manager.close();
            }
        }

    }

    @Override
    public boolean updateSong(Song song) {
        return false;
    }

    @Override
    public Song deleteSong(Integer id) {
        return null;
    }

    public void done() {
        entityManagerFactory.close();
    }
}
