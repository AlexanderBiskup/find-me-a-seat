package at.ac.univie.hci.findmeaseat.model.booking;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

import at.ac.univie.hci.findmeaseat.model.booking.status.SeatStatusService;
import at.ac.univie.hci.findmeaseat.model.building.Address;
import at.ac.univie.hci.findmeaseat.model.building.Area;
import at.ac.univie.hci.findmeaseat.model.building.Building;
import at.ac.univie.hci.findmeaseat.model.building.Seat;
import at.ac.univie.hci.findmeaseat.model.user.AuthenticationService;

import static at.ac.univie.hci.findmeaseat.model.booking.status.SeatStatusService.SeatStatus.OCCUPIED;
import static java.time.LocalDateTime.now;
import static java.util.UUID.randomUUID;

public final class DummyBookingService implements BookingService {

    private final AuthenticationService authenticationService;
    private final BookingRepository bookingRepository;
    private final SeatStatusService seatStatusService;

    DummyBookingService(AuthenticationService authenticationService, BookingRepository bookingRepository, SeatStatusService seatStatusService) {
        this.authenticationService = authenticationService;
        this.bookingRepository = bookingRepository;
        this.seatStatusService = seatStatusService;
    }

    @Override
    public Booking getBookingById(UUID id) {
        return bookingRepository.findById(id);
    }

    @Override
    public void cancelBooking(Booking booking) {
        bookingRepository.remove(booking);
    }

    @Override
    public void bookSeat(Seat seat, Period period) {
        if (seatStatusService.getStatus(seat, period).equals(OCCUPIED)) throw new IllegalStateException("Can't book occupied seat");
        Booking booking = new Booking(authenticationService.getAuthenticatedUser().getId(), seat, period.getStart(), period.getEnd());
        bookingRepository.save(booking);
    }

    @Override
    public void bookAnySeat(Building building, Period period) {
        List<Seat> freeSeats = seatStatusService.getFreeSeats(building, period);
        int randomSeatIndex = ThreadLocalRandom.current().nextInt(0, freeSeats.size() + 1) - 1;
        Seat selectedSeat = freeSeats.get(randomSeatIndex);
        bookSeat(selectedSeat, period);
    }

    @Override
    public List<Booking> getAllBookings() {
        return bookingRepository.findByUser(authenticationService.getAuthenticatedUser().getId());
    }

    public void initializeDummyBookings(List<Building> buildings) {
        Address address = new Address("Währingerstraße 29", "Wien", "1180");
        Building building = new Building("Fakultät für Informatik", address);
        building.addArea("1. Stock");
        Area area = building.getArea("1. Stock");
        area.addSeat("A1");
        Seat seat = area.getSeat("A1");
        bookingRepository.save(new Booking(randomUUID(), seat, now(), now().plusHours(1)));
        bookingRepository.save(new Booking(randomUUID(), seat, now().minusHours(3), now().minusHours(2)));
    }

}
