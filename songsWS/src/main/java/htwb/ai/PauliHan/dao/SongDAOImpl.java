package htwb.ai.PauliHan.dao;


import htwb.ai.PauliHan.model.Song;

import javax.inject.Inject;
import javax.persistence.*;
import java.util.Collection;


public class SongDAOImpl implements ISongDAO {

    private EntityManagerFactory entityManagerFactory;

    @Inject
    public SongDAOImpl(EntityManagerFactory entityManagerFactory) {
      this.entityManagerFactory = entityManagerFactory;
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
    public Collection<Song> getSongs() {
        EntityManager em = null;
        try {
            em = entityManagerFactory.createEntityManager();
            Query q = em.createQuery("SELECT s FROM Song s");
            return q.getResultList();
        } finally {
            if (em != null) {
                em.close();
            }
        }
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
    public Song updateSong(Song song) {
        EntityManager em = null;
        EntityTransaction trans = null;
        try {
            em = entityManagerFactory.createEntityManager();
            trans = em.getTransaction();
            trans.begin();
            Song mergedSong = em.merge(song);
            if (mergedSong != null) {
                trans.commit();
            }	
            return mergedSong;
        } catch (Exception ex) {
            if (trans != null) trans.rollback();
            throw new PersistenceException(ex.getMessage());
        } finally {
            if (em != null) em.close();
        }
    }

    @Override
    public boolean deleteSong(Integer id) {
        EntityTransaction transaction = null;
        EntityManager manager = entityManagerFactory.createEntityManager();
        Song song = null;
        try {
            transaction = manager.getTransaction();
            transaction.begin();
            song = getSong(id);
            if (song != null) {
                manager.remove(manager.find(Song.class,id));
                transaction.commit();
                return true;
            } else {
                return false;
            }

        } catch (Exception e) {
            System.out.println("Error removing song " + e.getMessage());
            throw new PersistenceException("Could not remove entity: " + e.toString());
        } finally {
            if (manager != null) {
                manager.close();
            }
        }

    }

    public void done() {
        entityManagerFactory.close();
    }
}
