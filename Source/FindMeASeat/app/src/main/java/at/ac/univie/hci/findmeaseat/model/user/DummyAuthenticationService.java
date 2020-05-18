package at.ac.univie.hci.findmeaseat.model.user;

import java.util.HashMap;
import java.util.Map;

public class DummyAuthenticationService implements AuthenticationService {

    private User authenticatedUser;

    private Map<String, User> users = new HashMap<>();

    DummyAuthenticationService() {
        User dummyUser = new User("user", "user");
        users.put(dummyUser.getUsername(), dummyUser);
        authenticatedUser = dummyUser;
    }

    @Override
    public User getAuthenticatedUser() {
        if (authenticatedUser == null) throw new IllegalStateException("No user is currently authenticated.");
        return authenticatedUser;
    }

    @Override
    public void authenticateUser(String username, String password) {
        User user;
        if (users.containsKey(username)) {
            user = users.get(username);
        } else {
            user = new User(username, password);
            users.put(username, user);
        }
        assert user != null;
        if (!user.getPassword().equals(password)) throw new IllegalArgumentException("Authentication failed.");
        authenticatedUser = user;
    }

}
