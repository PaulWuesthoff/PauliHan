package htwb.ai.PauliHan.model;

public interface IUserDao {
    Boolean authenticate(String userID, String key);
}
