package at.ac.univie.hci.findmeaseat;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import at.ac.univie.hci.findmeaseat.ui.bookings.BookingFragment;

public class HomeActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.home_activity);
        BookingFragment bookingFragment = new BookingFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.home_booking_frame, bookingFragment).commit();

        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications).build();
        
    }





}
