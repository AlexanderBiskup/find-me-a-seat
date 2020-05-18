package at.ac.univie.hci.findmeaseat.ui.login;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;
import java.io.InputStream;

import at.ac.univie.hci.findmeaseat.R;

public class Users {

    HashMap <String, User> users;

    public Users(){
        users = init();
    }

    private HashMap<String, User> init(){
        users = new HashMap<>();
        users.put("uni_administrator", new User("uni_administrator", "admin", "admin@findmeaseat.com", "23456", "admin", "admin"));
        users.put("student1", new User("student1", "student", "student1@findmeaseat.com", "23454","student", "student"));
        users.put("student2", new User("student2", "student", "astudent2@findmeaseat.com", "23457","student", "student"));
        users.put("student3", new User("student3", "student", "student3@findmeaseat.com", "234543","student", "student"));
        return users;
    }

    public Boolean checkUser(String username, String password){
        if (users.get(username) == null){
            return false;
        }
        String storedPassword = users.get(username).getPassword();
        return storedPassword != null && storedPassword.equals(password);

    }

    public HashMap<String, User> getUsers() {
        return users;
    }
}

