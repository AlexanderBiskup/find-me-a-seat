package at.ac.univie.hci.findmeaseat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.UUID;

import at.ac.univie.hci.findmeaseat.ui.login.Users;
import at.ac.univie.hci.findmeaseat.ui.bookings.BookingDetailsActivity;


public class LoginActivity extends AppCompatActivity {

    public static final String LOGIN_QUERY = "at.ac.univie.hci.findmeaseat.ui.login.Users.LOGIN_QUERY";


    private TextView intro;
    private TextView username;
    private TextView password;
    private Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        intro =  findViewById(R.id.login_Text);
        username = findViewById(R.id.LogIn_username);
        password = findViewById(R.id.LogIn_Password);

        login = (Button) findViewById(R.id.button_logIn);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLogInActivity();
            }
        });


    }

    public void openLogInActivity() {
        Intent intent = new Intent(this, BookingDetailsActivity.class);
        String user = username.getText().toString();
        String password1 = password.getText().toString();

        Users users = new Users();
        if(users.checkUser(user, password1)){
            intent.putExtra(BookingDetailsActivity.BOOKING_ID_EXTRA_NAME, UUID.randomUUID().toString());
            intent.putExtra(LOGIN_QUERY, user);
            startActivity(intent);}
        else {
            intro.setText("Try Again!");
        }
    }

}

