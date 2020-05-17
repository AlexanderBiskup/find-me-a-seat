package at.ac.univie.hci.findmeaseat.ui.bookings;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Locale;
import java.util.UUID;

import at.ac.univie.hci.findmeaseat.R;
import at.ac.univie.hci.findmeaseat.model.booking.Booking;
import at.ac.univie.hci.findmeaseat.model.building.Address;
import at.ac.univie.hci.findmeaseat.model.building.Area;
import at.ac.univie.hci.findmeaseat.model.building.Building;
import at.ac.univie.hci.findmeaseat.model.building.Seat;

import static java.time.format.DateTimeFormatter.ofPattern;

public class BookingFragment extends Fragment {

    private static final String ARG_BOOKING_ID = "argBookingId";

    private UUID bookingId;
    private BookingFragmentContext context;

    public BookingFragment() {
    }

    static BookingFragment newInstance(UUID bookingId) {
        BookingFragment fragment = new BookingFragment();
        Bundle args = new Bundle();
        args.putString(ARG_BOOKING_ID, bookingId.toString());
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            bookingId = UUID.fromString(getArguments().getString(ARG_BOOKING_ID));
        }
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = (BookingFragmentContext) context;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_booking, container, false);

        //Booking Example
        Address address = new Address("Waehringer strasse", "Wien", "1090");
        Building building = new Building("Fakuektaet fuer Informatik ", address);
        Area area = new Area("1. Stock", building);
        Seat seat = new Seat("D03", area);
        Booking booking = new Booking(UUID.randomUUID(), UUID.randomUUID(), seat, LocalDateTime.now(), LocalDateTime.now());

        ((TextView) view.findViewById(R.id.buildingNameTextView)).setText(building.getName());
        ((TextView) view.findViewById(R.id.streetTextView)).setText(building.getAddress().getStreet());
        ((TextView) view.findViewById(R.id.cityTextView)).setText(getFormattedCity(building.getAddress()));
        ((TextView) view.findViewById(R.id.datesTextView)).setText(getFormattedDates(booking));
        ((TextView) view.findViewById(R.id.areaTextView)).setText(booking.getSeat().getArea().getName());
        ((TextView) view.findViewById(R.id.seatTextView)).setText(String.format("Platz %s", booking.getSeat().getName()));
        ((TextView) view.findViewById(R.id.durationTextView)).setText(getFormattedDuration(booking));
        return view;
    }

    private String getFormattedCity(Address address) {
        return String.format("%s, %s", address.getZipCode(), address.getCity());
    }

    private String getFormattedDates(Booking booking) {
        String date = booking.getStart().format(ofPattern("E dd.MM.", Locale.GERMAN));
        return String.format("%s %s - %s Uhr", date, booking.getStart().format(ofPattern("HH:mm")), booking.getEnd().format(ofPattern("HH:mm")));
    }

    private String getFormattedDuration(Booking booking) {
        if (LocalDateTime.now().isBefore(booking.getStart())) {
            return String.format(Locale.GERMAN, "Beginnt in %d min", LocalDateTime.now().until(booking.getStart(), ChronoUnit.MINUTES));
        } else if (LocalDateTime.now().isBefore(booking.getEnd())) {
            return String.format(Locale.GERMAN, "Noch %d min", LocalDateTime.now().until(booking.getEnd(), ChronoUnit.MINUTES));
        } else {
            return "Beendet";
        }
    }

  public interface BookingFragmentContext {
        Booking getBooking(UUID bookingId);
        void onClick(UUID bookingId);
    }

    public static String getArgBookingId() {
        return ARG_BOOKING_ID;
    }

    public UUID getBookingId() {
        return bookingId;
    }

    public void setBookingId(UUID bookingId) {
        this.bookingId = bookingId;
    }

    @Nullable
    @Override
    public Context getContext() {
        return (Context) context;
    }

    public void setContext(BookingFragmentContext context) {
        this.context = context;
    }
}
