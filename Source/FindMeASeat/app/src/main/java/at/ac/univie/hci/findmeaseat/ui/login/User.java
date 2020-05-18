package at.ac.univie.hci.findmeaseat.ui.login;

public class User {
    private String username;
    private String password;
    private String firstname;
    private String lastname;

    private String email;
    private String matrikel;

    public User(String username, String password, String email, String matrikel, String firstname, String lastname) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.matrikel = matrikel;
        this.email = firstname;
        this.matrikel = lastname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }


    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }


    public String getMatrikel() {
        return matrikel;
    }

    public void setMatrikel(String matrikel) {
        this.matrikel = matrikel;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", matrikel='" + matrikel + '\'' +
                '}';
    }
}

