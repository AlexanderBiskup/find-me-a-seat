package at.ac.univie.hci.findmeaseat.ui.bookings;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collection;

import at.ac.univie.hci.findmeaseat.model.booking.Booking;
import at.ac.univie.hci.findmeaseat.ui.bookings.BookingCardViewCreator.BookingCardViewHolder;

public class BookingsAdapter extends RecyclerView.Adapter<BookingCardViewHolder> {

    private ArrayList<Booking> bookings;
    private final BookingCardViewCreator bookingCardViewCreator;

    public BookingsAdapter(Collection<Booking> bookings, LayoutInflater inflater) {
        this.bookings = new ArrayList<>(bookings);
        this.bookingCardViewCreator = new BookingCardViewCreator(inflater);
    }

    @NonNull
    @Override
    public BookingCardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return bookingCardViewCreator.createViewHolder(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull BookingCardViewHolder holder, int position) {
        Booking booking = bookings.get(position);
        holder.bindBooking(booking);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return bookings.size();
    }

}
