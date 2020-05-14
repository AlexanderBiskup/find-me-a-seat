package at.ac.univie.hci.findmeaseat.model.booking;

import java.util.UUID;

public interface BookingService {

    Booking getBooking(UUID id);

    void cancelBooking(UUID id);

}
