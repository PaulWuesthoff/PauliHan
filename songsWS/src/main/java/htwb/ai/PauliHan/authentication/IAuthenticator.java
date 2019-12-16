package htwb.ai.PauliHan.authentication;

import htwb.ai.PauliHan.model.User;

public interface IAuthenticator {
    boolean authenticate(String token);
    String createToken(User user);
}
