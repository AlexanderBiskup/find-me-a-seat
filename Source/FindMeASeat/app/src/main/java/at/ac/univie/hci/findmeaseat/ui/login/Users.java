package at.ac.univie.hci.findmeaseat.ui.login;

import java.util.HashMap;

public class Users {

    HashMap <String, String> users;

    public Users(){
        users = init();
    }

    private HashMap<String, String> init(){
        users = new HashMap<>();
        users.put("uni_administrator", "admin");
        users.put("student1", "student");
        users.put("student2", "student");
        users.put("student3", "student");
        return users;
    }

    public Boolean checkUser(String username, String password){
        String storedPassword = users.get(username);
        return storedPassword != null && storedPassword.equals(password);

    }
}


