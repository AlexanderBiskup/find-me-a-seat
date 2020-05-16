package at.ac.univie.hci.findmeaseat.ui.login;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;



import java.util.HashMap;

import at.ac.univie.hci.findmeaseat.MainActivity;

public class Users {
    // Hashmap Objekt erstellen, um die Users der App manuell zu speichern
    HashMap <String, String> users = null;

    public Users(){
        users = init();
    }

    public HashMap<String, String> init(){
        users = new HashMap<>();
        users.put("uni_administrator", "admin");
        users.put("student1", "student");
        users.put("student2", "student");
        users.put("student3", "student");
        return users;
    }

    public Boolean checkUser(String username, String password){
        for (String user : users.keySet()){
            if (user.equals(username)){
                if (users.get(user).equals(password)){
                    return true;
                }
            }
        }
        return false;
    }
}


