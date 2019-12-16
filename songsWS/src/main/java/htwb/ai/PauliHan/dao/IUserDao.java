package htwb.ai.PauliHan.dao;

import htwb.ai.PauliHan.model.User;

public interface IUserDao {
    User getUser(String Id, String key);
}
