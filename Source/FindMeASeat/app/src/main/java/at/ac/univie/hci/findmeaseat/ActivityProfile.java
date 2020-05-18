package at.ac.univie.hci.findmeaseat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.UUID;

import at.ac.univie.hci.findmeaseat.ui.bookings.BookingDetailsActivity;
import at.ac.univie.hci.findmeaseat.ui.login.User;
import at.ac.univie.hci.findmeaseat.ui.login.Users;

public class ActivityProfile extends AppCompatActivity {

    private TextView name;
    private TextView lastname;
    private TextView email;
    private TextView matrikel;
    private Button logOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_dashboard);
        User user = new Users().getUsers().get(getIntent().getStringExtra("at.ac.univie.hci.findmeaseat.ui.login.Users.LOGIN_QUERY"));
        name =  findViewById(R.id.name);
        email = findViewById(R.id.email);
        matrikel = findViewById(R.id.matrikel);
        lastname = findViewById(R.id.lastname);

        name =  findViewById(R.id.name);
        name.setText(user.getFirstname());

        lastname = findViewById(R.id.lastname);
        lastname.setText(user.getLastname());

        email =  findViewById(R.id.email);
        email.setText(user.getEmail());

        matrikel =  findViewById(R.id.matrikel);
        matrikel.setText(user.getMatrikel());

        logOut =  findViewById(R.id.logOut);
        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLogOutActivity();
            }
        });


    }

    public void openLogOutActivity() {
        Intent intent = new Intent(this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        this.finish();
    }
}
