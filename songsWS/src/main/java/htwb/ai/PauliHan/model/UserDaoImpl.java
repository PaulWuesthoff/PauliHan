package htwb.ai.PauliHan.model;

import htwb.ai.PauliHan.service.RandomString;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class UserDaoImpl implements IUserDao {
    private EntityManagerFactory entityManagerFactory;
    private RandomString randomString;

    @Inject
    public UserDaoImpl(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    public Boolean authenticate(String userID, String key) {
        EntityManager manager = null;
        try {
            manager = entityManagerFactory.createEntityManager();
            User user = manager.find(User.class, userID);
            if (user.getUserId().equals(userID) && user.getKey().equals(key)) {
                randomString = new RandomString(15);
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (manager != null) {
                manager.close();
            }
        }
        return false;
    }
}
