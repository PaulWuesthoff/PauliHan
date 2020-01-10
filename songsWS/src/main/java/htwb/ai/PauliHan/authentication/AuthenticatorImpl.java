package htwb.ai.PauliHan.authentication;

import htwb.ai.PauliHan.model.User;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class AuthenticatorImpl implements IAuthenticator {

    private Map<User, String> tokenMap = new ConcurrentHashMap<>();
    private RandomString randomString;

    @Override
    public boolean authenticate(String token) {
        return tokenMap.containsValue(token);
    }

    @Override
    public String createToken(User user) {
        randomString = new RandomString(15);

        String token = randomString.nextString();
        if (!tokenMap.containsKey(user)) {
            this.addTokenToSession(user, token);
        } else {
            tokenMap.replace(user, token);
        }
        return token;
    }

    private void addTokenToSession(User user, String token) {
        this.tokenMap.put(user, token);
    }

    @Override
    public Map<User, String> getTokenMap() {
        return tokenMap;
    }
}
