package at.ac.univie.hci.findmeaseat.ui.bookings;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Locale;

import at.ac.univie.hci.findmeaseat.R;
import at.ac.univie.hci.findmeaseat.model.booking.Booking;
import at.ac.univie.hci.findmeaseat.model.building.Address;
import at.ac.univie.hci.findmeaseat.model.building.Building;

import static java.time.format.DateTimeFormatter.ofPattern;

class BookingViewHolderFactory {

    private final LayoutInflater inflater;

    BookingViewHolderFactory(LayoutInflater inflater) {
        this.inflater = inflater;
    }

    BookingCardViewHolder createViewHolder(ViewGroup parent) {
        View view = inflater.inflate(R.layout.fragment_booking, parent, false);
        return new BookingCardViewHolder(view);
    }

    static class BookingCardViewHolder extends RecyclerView.ViewHolder {

        private final View bookingCardView;

        BookingCardViewHolder(View bookingCardView) {
            super(bookingCardView);
            this.bookingCardView = bookingCardView;
        }

        void bindBooking(Booking booking) {
            Building building = booking.getSeat().getArea().getBuilding();
            ((TextView) bookingCardView.findViewById(R.id.buildingNameTextView)).setText(building.getName());
            ((TextView) bookingCardView.findViewById(R.id.streetTextView)).setText(building.getAddress().getStreet());
            ((TextView) bookingCardView.findViewById(R.id.cityTextView)).setText(getFormattedCity(building.getAddress()));
            ((TextView) bookingCardView.findViewById(R.id.datesTextView)).setText(getFormattedDates(booking));
            ((TextView) bookingCardView.findViewById(R.id.areaTextView)).setText(booking.getSeat().getArea().getName());
            ((TextView) bookingCardView.findViewById(R.id.seatTextView)).setText(String.format("Platz %s", booking.getSeat().getName()));
            ((TextView) bookingCardView.findViewById(R.id.durationTextView)).setText(getFormattedBookingDuration(booking));
        }

        View getBookingCardView() {
            return bookingCardView;
        }

        private String getFormattedCity(Address address) {
            return String.format("%s, %s", address.getZipCode(), address.getCity());
        }

        private String getFormattedDates(Booking booking) {
            String date = booking.getStart().format(ofPattern("E dd.MM.", Locale.getDefault()));
            return String.format("%s %s - %s Uhr", date, booking.getStart().format(ofPattern("HH:mm")), booking.getEnd().format(ofPattern("HH:mm")));
        }

        private String getFormattedBookingDuration(Booking booking) {
            if (LocalDateTime.now().isBefore(booking.getStart())) {
                long durationInMinutes = LocalDateTime.now().until(booking.getStart(), ChronoUnit.MINUTES);
                return String.format(Locale.getDefault(), "Beginnt in\n%s", getFormattedDuration(durationInMinutes));
            } else if (LocalDateTime.now().isBefore(booking.getEnd())) {
                long durationInMinutes = LocalDateTime.now().until(booking.getEnd(), ChronoUnit.MINUTES);
                return String.format(Locale.getDefault(), "Noch\n%s", getFormattedDuration(durationInMinutes));
            } else {
                return "Beendet";
            }
        }

        private String getFormattedDuration(long durationInMinutes) {
            long days = durationInMinutes / (60 * 24);
            long hours = (durationInMinutes - (days * 60 * 24)) / 60;
            long minutes = durationInMinutes - (days * 60 * 24) - (hours * 60);
            StringBuilder builder = new StringBuilder();
            if (days > 0) builder.append(days).append(" Tagen ");
            if (hours > 0) builder.append(hours).append(" Stunden ");
            if (minutes > 0) builder.append(minutes).append(" Minuten ");
            return builder.toString();
        }

    }

}
