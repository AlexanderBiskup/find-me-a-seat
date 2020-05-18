package at.ac.univie.hci.findmeaseat.model.user;

import java.util.UUID;

public final class User {

    private final UUID id;
    private final String username;
    private final String password;

    public User(String username, String password) {
        this.id = UUID.randomUUID();
        this.username = username;
        this.password = password;
    }

    public UUID getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

}
