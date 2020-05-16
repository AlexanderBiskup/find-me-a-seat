package at.ac.univie.hci.findmeaseat.ui.bookings;

import android.os.Bundle;
import android.view.ViewManager;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.time.LocalDateTime;
import java.util.UUID;

import at.ac.univie.hci.findmeaseat.R;
import at.ac.univie.hci.findmeaseat.model.booking.Booking;
import at.ac.univie.hci.findmeaseat.model.booking.BookingService;
import at.ac.univie.hci.findmeaseat.model.booking.DummyBookingService;
import at.ac.univie.hci.findmeaseat.ui.bookings.BookingFragment.BookingFragmentContext;

public class BookingDetailsActivity extends AppCompatActivity implements BookingFragmentContext {

    public static final String BOOKING_ID_EXTRA_NAME = "bookingId";

    private BookingService bookingService = new DummyBookingService(); // TODO use factory

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_details);
        UUID bookingId = UUID.fromString(getIntent().getStringExtra(BOOKING_ID_EXTRA_NAME));
        Booking booking = bookingService.getBooking(bookingId);
        setTitle("Buchungsdetails");
        if (findViewById(R.id.booking_details_booking_container) != null) {
            BookingFragment fragment = BookingFragment.newInstance(bookingId);
            getSupportFragmentManager().beginTransaction().add(R.id.booking_details_booking_container, fragment).commit();
        }
        Button cancelBookingButton = findViewById(R.id.cancelBookingButton);
        if(LocalDateTime.now().isBefore(booking.getStart())) {
            cancelBookingButton.setText("Buchung stornieren");
        } else if(LocalDateTime.now().isBefore(booking.getEnd())) {
            cancelBookingButton.setText("Buchung beenden");
        } else {
            ((ViewManager) getParent()).removeView(cancelBookingButton);
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
