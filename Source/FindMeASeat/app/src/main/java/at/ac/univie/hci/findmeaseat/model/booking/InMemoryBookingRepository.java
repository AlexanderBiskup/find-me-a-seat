package at.ac.univie.hci.findmeaseat.model.booking;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import at.ac.univie.hci.findmeaseat.model.building.Seat;

public class InMemoryBookingRepository implements BookingRepository {

    private final List<Booking> bookings = new ArrayList<>();

    @Override
    public void save(Booking booking) {
        bookings.add(booking);
    }

    @Override
    public void remove(Booking booking) {
        bookings.remove(booking);
    }

    @Override
    public Booking findById(UUID id) {
        Optional<Booking> booking = bookings
                .stream()
                .filter(b -> b.getId().equals(id))
                .findFirst();
        if (booking.isPresent()) {
            return booking.get();
        }
        throw new IllegalArgumentException("Booking could not be found.");
    }

    @Override
    public List<Booking> findBySeat(Seat seat, Period period) {
        return bookings
                .stream()
                .filter(b -> b.getSeat().equals(seat))
                .filter(b -> !(b.getEnd().isBefore(period.getStart())) && !(b.getStart().isAfter(period.getEnd())))
                .collect(Collectors.toList());
    }

    @Override
    public List<Booking> findByUser(UUID userId) {
        return bookings
                .stream()
                .filter(b -> b.getUserId().equals(userId))
                .collect(Collectors.toList());
    }

}
