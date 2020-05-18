package at.ac.univie.hci.findmeaseat.ui.bookings;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.LayoutManager;

import java.time.LocalDateTime;
import java.util.List;

import at.ac.univie.hci.findmeaseat.R;
import at.ac.univie.hci.findmeaseat.model.booking.Booking;
import at.ac.univie.hci.findmeaseat.model.booking.BookingService;
import at.ac.univie.hci.findmeaseat.model.booking.BookingServiceFactory;

public class AllValidBookingsFragment extends Fragment implements BookingsAdapter.SelectBookingHandler {

    private BookingService bookingService = BookingServiceFactory.getSingletonInstance();

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_all_bookings, container, false);
        RecyclerView bookingsRecyclerView = root.findViewById(R.id.bookingsListView);


        BookingsAdapter adapter = new BookingsAdapter(bookingService.getAllValidBookings(), inflater, this);
        bookingsRecyclerView.setAdapter(adapter);
        LayoutManager layoutManager = new LinearLayoutManager(getContext());
        bookingsRecyclerView.setLayoutManager(layoutManager);
        return root;
    }

    @Override
    public void onSelect(Booking booking) {
        Intent intent = new Intent(getContext(), BookingDetailsActivity.class);
        intent.putExtra(BookingDetailsActivity.BOOKING_ID_EXTRA_NAME, booking.getId().toString());
        startActivity(intent);
    }

}
