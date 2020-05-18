package at.ac.univie.hci.findmeaseat.ui.buildings;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collection;

import at.ac.univie.hci.findmeaseat.R;
import at.ac.univie.hci.findmeaseat.model.building.Seat;

public class SeatsAdapter extends RecyclerView.Adapter<SeatsAdapter.SeatViewHolder> {

    private ArrayList<Seat> seats;
    private SeatSelectionHandler seatSelectionHandler;
    private Context context;

    SeatsAdapter(Collection<Seat> seats, Context context, SeatSelectionHandler seatSelectionHandler) {
        this.seats = new ArrayList<>(seats);
        this.seatSelectionHandler = seatSelectionHandler;
        this.context = context;
    }

    @NonNull
    @Override
    public SeatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_seat_item, parent, false);
        return new SeatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SeatViewHolder holder, int position) {
        final Seat seat = seats.get(position);
        holder.parentView.setOnClickListener(v -> seatSelectionHandler.select(seat));
        holder.textView.setText(seat.getName());
        if(seatSelectionHandler.isSelected(seat)) {
            holder.textView.setBackgroundColor(context.getColor(R.color.colorAccent));
        } else {
            holder.textView.setBackgroundColor(context.getColor(R.color.freeSeat));
        } // TODO refactor
    }

    @Override
    public int getItemCount() {
        return seats.size();
    }

    static class SeatViewHolder extends RecyclerView.ViewHolder {
        View parentView;
        TextView textView;
        SeatViewHolder(View view) {
            super(view);
            this.parentView = view;
            this.textView = view.findViewById(R.id.seat_item_text);
        }
    }

    public interface SeatSelectionHandler {

        boolean isSelected(Seat seat);

        void select(Seat seat);

    }

}
