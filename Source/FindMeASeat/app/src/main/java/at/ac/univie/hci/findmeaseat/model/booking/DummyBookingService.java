package at.ac.univie.hci.findmeaseat.model.booking;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import at.ac.univie.hci.findmeaseat.model.building.Address;
import at.ac.univie.hci.findmeaseat.model.building.Area;
import at.ac.univie.hci.findmeaseat.model.building.Building;
import at.ac.univie.hci.findmeaseat.model.building.Seat;

import static java.time.LocalDateTime.now;
import static java.util.UUID.randomUUID;

public final class DummyBookingService implements BookingService {

    private List<Booking> bookings = new ArrayList<>();


    DummyBookingService() {
        Address address = new Address("Währingerstraße 29", "Wien", "1180");
        Building building = new Building("Fakultät für Informatik", address);
        building.addArea("1. Stock");
        Area area = building.getArea("1. Stock");
        area.addSeat("A1");
        Seat seat = area.getSeat("A1");
        bookings.add(new Booking(randomUUID(), randomUUID(), seat, now(), now().plusHours(1)));
        bookings.add(new Booking(randomUUID(), randomUUID(), seat, now().minusHours(3), now().minusHours(2)));
        bookings.add(new Booking(randomUUID(), randomUUID(), seat, now().minusHours(6), now().minusHours(5)));
        bookings.add(new Booking(randomUUID(), randomUUID(), seat, now().minusHours(11), now().minusHours(10)));
    }

    @Override
    public Booking getBookingById(UUID id) {
        return bookings.get(0);
    }

    @Override
    public void cancelBookingById(UUID id) {
        // TODO
    }

    @Override
    public List<Booking> getAllBookings() {
        return bookings;
    }

}
