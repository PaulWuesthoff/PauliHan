package htwb.ai.PauliHan.dao;


import htwb.ai.PauliHan.dao.IUserDao;
import htwb.ai.PauliHan.model.User;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceException;

public class UserDaoImpl implements IUserDao {
    private EntityManagerFactory entityManagerFactory;

    @Inject
    public UserDaoImpl(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    public User getUser(String id, String key) {
        EntityManager manager = null;
        try {
            manager = entityManagerFactory.createEntityManager();
            User user = manager.find(User.class, id);
            if (user == null) {
                return null;
            }
            if (!user.getKey().equals(key)) {
                return null;
            }
            return user;
        } catch (Exception e) {
            throw new PersistenceException(e.getMessage());
        } finally {
            if (manager != null) {
                manager.close();
            }
        }
    }


//    @Override
//    public Boolean authenticate(String userID, String key) {
//        EntityManager manager = null;
//        try {
//            manager = entityManagerFactory.createEntityManager();
//            User user = manager.find(User.class, userID);
//            System.out.println(user.toString());
//            if (user.getKey().equals(key)) {
//                return true;
//            } else {
//                return false;
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            if (manager != null) {
//                manager.close();
//            }
//        }
//        return false;
//    }
}
