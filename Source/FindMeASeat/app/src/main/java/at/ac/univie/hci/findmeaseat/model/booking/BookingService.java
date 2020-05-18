package at.ac.univie.hci.findmeaseat.model.booking;

import java.util.List;
import java.util.UUID;

public interface BookingService {

    Booking getBookingById(UUID id);

    void cancelBookingById(UUID id);

    List<Booking> getAllBookings();

    List<Booking> getAllValidBookings();

}
