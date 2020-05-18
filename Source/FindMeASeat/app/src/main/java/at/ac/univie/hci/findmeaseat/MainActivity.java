package at.ac.univie.hci.findmeaseat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import java.util.UUID;

import at.ac.univie.hci.findmeaseat.ui.bookings.BookingDetailsActivity;
import at.ac.univie.hci.findmeaseat.ui.buildings.AreaDetailsActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_all_bookings)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
    }

    // TODO This is used only temporary for dev until the connection to the start screen is setup
    public void startBookingDetailsActivity(View view) {
        Intent intent = new Intent(this, BookingDetailsActivity.class);
        intent.putExtra(BookingDetailsActivity.BOOKING_ID_EXTRA_NAME, UUID.randomUUID().toString()); // TODO use real id
        startActivity(intent);
    }

    // TODO This is used only temporary for dev until the connection to the building details screen is setup
    public void startAreaDetailsActivity(View view) {
        Intent intent = new Intent(this, AreaDetailsActivity.class);
        intent.putExtra(AreaDetailsActivity.BUILDING_ID_EXTRA_NAME, UUID.randomUUID().toString()); // TODO use real id
        intent.putExtra(AreaDetailsActivity.AREA_NAME_EXTRA_NAME, "1. Stock"); // TODO use real name
        startActivity(intent);
    }

}
