package at.ac.univie.hci.findmeaseat.ui.bookings;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import java.util.UUID;

import at.ac.univie.hci.findmeaseat.R;
import at.ac.univie.hci.findmeaseat.model.booking.Booking;
import at.ac.univie.hci.findmeaseat.model.booking.BookingService;
import at.ac.univie.hci.findmeaseat.model.booking.DummyBookingService;
import at.ac.univie.hci.findmeaseat.ui.bookings.BookingFragment.BookingFragmentContext;

public class BookingDetailsActivity extends AppCompatActivity implements BookingFragmentContext {

    public static final String BOOKING_ID_EXTRA_NAME = "bookingId";

    private BookingService bookingService = new DummyBookingService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_details);
        UUID bookingId = UUID.fromString(getIntent().getStringExtra(BOOKING_ID_EXTRA_NAME));
        setTitle("Buchungsdetails");
        if (findViewById(R.id.booking_details_booking_container) != null) {
            BookingFragment fragment = BookingFragment.newInstance(bookingId);
            getSupportFragmentManager().beginTransaction().add(R.id.booking_details_booking_container, fragment).commit();
        }
    }

    @Override
    public Booking getBooking(UUID bookingId) {
        return bookingService.getBooking(bookingId);
    }

    @Override
    public void onClick(UUID bookingId) {
    }

}
