package at.ac.univie.hci.findmeaseat;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.ui.AppBarConfiguration;

import at.ac.univie.hci.findmeaseat.ui.bookings.BookingFragment;

public class HomeActivity extends AppCompatActivity {


    public static FragmentManager fragmentManager_booking;
    public static FragmentManager fragmentManager_building;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);

        fragmentManager_booking = getSupportFragmentManager();

        if(findViewById(R.id.home_booking_fragment_frame) != null){
            if(savedInstanceState != null){
                return;
            }
            FragmentTransaction fragmentTransaction_booking = fragmentManager_booking.beginTransaction();
            BookingFragment bookingFragment = new BookingFragment();
            fragmentTransaction_booking.add(R.id.home_booking_fragment_frame, bookingFragment, null);
            fragmentTransaction_booking.commit();
        }


        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications).build();



    }





}
