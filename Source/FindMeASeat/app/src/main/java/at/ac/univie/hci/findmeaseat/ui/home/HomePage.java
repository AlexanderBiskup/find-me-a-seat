package at.ac.univie.hci.findmeaseat.ui.home;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.UUID;

import at.ac.univie.hci.findmeaseat.R;
import at.ac.univie.hci.findmeaseat.model.building.Building;
import at.ac.univie.hci.findmeaseat.model.building.BuildingFragment;
import at.ac.univie.hci.findmeaseat.ui.bookings.BookingFragment;

public class HomePage extends AppCompatActivity {


    public static FragmentManager fragmentManager_booking;
    public static FragmentManager fragmentManager_building;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_home);

        fragmentManager_booking = getSupportFragmentManager();
        fragmentManager_building = getSupportFragmentManager();

        if(findViewById(R.id.home_booking_frame) != null){
            if(savedInstanceState != null){
                return;
            }
            FragmentTransaction fragmentTransaction_booking = fragmentManager_booking.beginTransaction();
            BookingFragment bookingFragment = new BookingFragment();

            fragmentTransaction_booking.add(R.id.home_booking_frame, bookingFragment, null);
            fragmentTransaction_booking.commit();
        }

        /*
        if(findViewById(R.id.home_building_frame) != null){
            if(savedInstanceState != null){
                return;
            }
            FragmentTransaction fragmentTransaction_booking = fragmentManager_building.beginTransaction();
            BuildingFragment buildingFragment = new BuildingFragment();

            fragmentTransaction_booking.add(R.id.home_building_frame, buildingFragment, null);
            fragmentTransaction_booking.commit();
        }
*/
    }





}