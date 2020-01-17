package htwb.ai.PauliHan.dao;

import htwb.ai.PauliHan.model.Song;
import htwb.ai.PauliHan.model.SongList;
import htwb.ai.PauliHan.model.User;

import javax.inject.Inject;
import javax.persistence.*;

import java.util.ArrayList;
import java.util.Collection;


public class SongListDaoImpl implements ISongListDao {
    private EntityManagerFactory entityManagerFactory;

    @Inject
    public SongListDaoImpl(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    public Collection<SongList> getSongLists(String flag) {        //checken ob das die richtigen user sind fehlt noch
        if (isNumeric(flag)) {
            return getSongListId(flag);
        }
        if (checkIfUserExists(flag)) {
            return getSongListFromUser(flag);
        }
        return null;
    }

    private Collection<SongList> getSongListId(String flag) {
        EntityManager em = null;
        int id = Integer.parseInt(flag);
        try {
            em = entityManagerFactory.createEntityManager();
            Collection<SongList> songList = new ArrayList<>();
            SongList foundSongList = em.find(SongList.class, id);
            if (foundSongList == null) {
                return null;
            }
            songList.add(foundSongList);
            return songList;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    private Collection<SongList> getSongListFromUser(String userId) {
        EntityManager em = null;
        try {
            em = entityManagerFactory.createEntityManager();
            Query q = em.createQuery("SELECT s FROM SongList s WHERE s.user.userId = :userId")
                    .setParameter("userId", userId);        //Select * from songList WHERE ownerId	= mmuster
            return q.getResultList();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public Integer addSongList(SongList songList) {
        EntityManager em = null;
        EntityTransaction transaction = null;
        try {
            em = entityManagerFactory.createEntityManager();
            transaction = em.getTransaction();
            transaction.begin();
            em.persist(songList);
            transaction.commit();
            return songList.getId();
        } catch (Exception e) {
            if (em != null) {
                em.getTransaction().rollback();
            }
            throw new PersistenceException(e.getMessage());
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public boolean deleteSongList(Integer id) {
        EntityTransaction transaction = null;
        EntityManager manager = entityManagerFactory.createEntityManager();
        SongList songList = null;

        try {
            transaction = manager.getTransaction();
            transaction.begin();
            manager.remove(manager.find(SongList.class, id)); //gucken ob song berhaupot da ist
            transaction.commit();
            return true;

        } catch (Exception e) {
            System.out.println("Error removing songList " + e.getMessage());
            throw new PersistenceException("Could not remove entity: " + e.toString());
        } finally {
            if (manager != null) {
                manager.close();
            }
        }

    }


    private boolean isNumeric(String strNum) {
        if (strNum == null || strNum.isEmpty()) {
            return false;
        }
        try {
            Integer listId = Integer.parseInt(strNum);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    private boolean checkIfUserExists(String userId) {
        EntityManager entityManager = null;
        try {
            entityManager = entityManagerFactory.createEntityManager();
            if (entityManager.find(User.class, userId) != null) {
                return true;
            } else return false;
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }

    public Collection<SongList> getAll() {
        EntityManager em = null;
        try {
            em = entityManagerFactory.createEntityManager();
            Query q = em.createQuery("SELECT s FROM SongList s");
            return q.getResultList();
        } catch (Exception e) {
            throw new PersistenceException(e.getMessage());
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

}

