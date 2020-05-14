package at.ac.univie.hci.findmeaseat.model.booking;

import java.util.UUID;

import at.ac.univie.hci.findmeaseat.model.building.Address;
import at.ac.univie.hci.findmeaseat.model.building.Area;
import at.ac.univie.hci.findmeaseat.model.building.Building;
import at.ac.univie.hci.findmeaseat.model.building.Seat;

import static java.time.LocalDateTime.now;
import static java.util.UUID.randomUUID;

public final class DummyBookingService implements BookingService {

    @Override
    public Booking getBooking(UUID id) {
        Address address = new Address("Währingerstraße 29", "Wien", "1180");
        Building building = new Building("Fakultät für Informatik", address);
        building.addArea("1. Stock");
        Area area = building.getArea("1. Stock");
        area.addSeat("A1");
        Seat seat = area.getSeat("A1");
       return new Booking(id, randomUUID(), seat, now(), now().plusHours(1));
    }

}
