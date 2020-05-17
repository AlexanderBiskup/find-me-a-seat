package at.ac.univie.hci.findmeaseat.model.booking;

public class BookingServiceFactory {

    static private final BookingService bookingService = new DummyBookingService();

    public static BookingService getSingletonInstance() {
        return bookingService;
    }

}
