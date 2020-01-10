package htwb.ai.PauliHan.authentication;

import htwb.ai.PauliHan.model.User;

import java.util.Map;

public interface IAuthenticator {
    boolean authenticate(String token);
    String createToken(User user);
    Map<User,String> getTokenMap();
}
