package at.ac.univie.hci.findmeaseat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import at.ac.univie.hci.findmeaseat.ui.login.Users;



import at.ac.univie.hci.findmeaseat.MainActivity;
import at.ac.univie.hci.findmeaseat.R;
import at.ac.univie.hci.findmeaseat.model.user.AuthenticationService;
import at.ac.univie.hci.findmeaseat.model.user.AuthenticationServiceFactory;


public class LoginActivity extends AppCompatActivity {

    public static final String LOGIN_QUERY = "at.ac.univie.hci.findmeaseat.ui.login.Users.LOGIN_QUERY";


    private TextView intro;
    private TextView username = null; // MRC unnecessary null assignment
    private TextView password = null;
    private Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        intro = (TextView) findViewById(R.id.login_Text); // MRC unnecessary casts
        username = (TextView) findViewById(R.id.LogIn_username);
        password = (TextView) findViewById(R.id.LogIn_Password);

        login = (Button) findViewById(R.id.button_logIn);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLogInActivity();
            }
        });


    }

    public void openLogInActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        String user = username.getText().toString();
        String password1 = password.getText().toString();

        Users users = new Users();
        if(users.checkUser(user, password1)){
            intent.putExtra(LOGIN_QUERY, user);
            startActivity(intent);}
        else {
            intro.setText("Try Again!");
        }
    }

}


