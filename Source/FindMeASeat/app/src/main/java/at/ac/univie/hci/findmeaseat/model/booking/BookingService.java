package at.ac.univie.hci.findmeaseat.model.booking;

import java.util.UUID;

public interface BookingService {

    Booking getBookingById(UUID id);

    void cancelBookingById(UUID id);

}
