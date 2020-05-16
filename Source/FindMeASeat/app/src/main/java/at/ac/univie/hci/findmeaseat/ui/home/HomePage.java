package at.ac.univie.hci.findmeaseat.ui.home;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import at.ac.univie.hci.findmeaseat.R;
import at.ac.univie.hci.findmeaseat.ui.bookings.BookingFragment;

public class HomePage extends AppCompatActivity {

    TextView overview;
    TextView booking;
    TextView building;

    Fragment bookingFragment;
    Fragment buildingFragment;

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

    }





}