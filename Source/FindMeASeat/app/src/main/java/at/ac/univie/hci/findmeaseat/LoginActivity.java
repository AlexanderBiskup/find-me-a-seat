package at.ac.univie.hci.findmeaseat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import at.ac.univie.hci.findmeaseat.ui.login.Users;


public class LoginActivity extends AppCompatActivity {

    private TextView usernameTextView;
    private TextView passwordTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameTextView = findViewById(R.id.LogIn_username);
        passwordTextView = findViewById(R.id.LogIn_Password);
    }

    public void login(View view) {
        String user = usernameTextView.getText().toString();
        String password = passwordTextView.getText().toString();
        Users users = new Users();
        if (users.checkUser(user, password)) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Login fehlgeschlagen!", Toast.LENGTH_LONG).show();
        }
    }

}

