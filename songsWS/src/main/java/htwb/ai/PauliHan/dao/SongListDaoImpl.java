package htwb.ai.PauliHan.dao;

import htwb.ai.PauliHan.model.Song;
import htwb.ai.PauliHan.model.SongList;
import htwb.ai.PauliHan.model.User;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import java.util.Collection;


public class SongListDaoImpl implements ISongListDao {
    private EntityManagerFactory entityManagerFactory;

    @Inject
    public SongListDaoImpl(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    public Collection<SongList> getSongLists(String flag) {
        if (isNumeric(flag)) {
            //get songlist
        }
        if (checkIfUserExists(flag)) {
        	getListFromUser(flag);
        	
        	
        }
        return null;
    }

    private Collection<SongList> getListFromUser(String userId) {
    	EntityManager em = null;
        try {
            em = entityManagerFactory.createEntityManager();
            Query q = em.createQuery("SELECT s FROM Song s");		//Select * from songList WHERE ownerId	= mmuster
            return q.getResultList();
        } finally {
            if (em != null) {
                em.close();
            }
        }
	}

	@Override
    public Integer addSongList(Collection<Song> songCollection) {
        //add songList to Database
        return null;
    }

    @Override
    public boolean deleteSongList(Integer id) {
        EntityTransaction transaction = null;
        EntityManager manager = entityManagerFactory.createEntityManager();
        SongList songList = null;

        try {
            transaction = manager.getTransaction();
            transaction.begin();
            manager.remove(manager.find(SongList.class, id));
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



}

