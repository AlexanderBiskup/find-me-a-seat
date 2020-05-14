package at.ac.univie.hci.findmeaseat.ui.bookings;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.UUID;

import at.ac.univie.hci.findmeaseat.R;
import at.ac.univie.hci.findmeaseat.model.booking.Booking;
import at.ac.univie.hci.findmeaseat.model.booking.BookingService;
import at.ac.univie.hci.findmeaseat.model.booking.DummyBookingService;

public class BookingDetailsActivity extends AppCompatActivity {

    public static final String BOOKING_ID_EXTRA_NAME = "bookingId";

    private BookingService bookingService = new DummyBookingService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_details);
        UUID bookingId = UUID.fromString(getIntent().getStringExtra(BOOKING_ID_EXTRA_NAME));
        Booking booking = bookingService.getBooking(bookingId);
        setTitle(booking.getSeat().getArea().getBuilding().getName());
    }
}
