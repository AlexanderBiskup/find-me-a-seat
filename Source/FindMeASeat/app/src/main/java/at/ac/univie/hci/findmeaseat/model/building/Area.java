package at.ac.univie.hci.findmeaseat.model.building;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public final class Area {

    private final String name;
    private final Building building;
    private final Map<String, Seat> seats;

    public Area(String name, Building building) {
        this.name = name;
        this.building = building;
        this.seats = new HashMap<>();
    }

    public void addSeat(String name) {
        seats.put(name, new Seat(name, this));
    }

    public Seat getSeat(String name) {
        Seat seat = seats.get(name);
        if (seat == null) throw new IllegalArgumentException("Seat not found.");
        return seat;
    }

    public String getName() {
        return name;
    }

    public Building getBuilding() {
        return building;
    }

    public Collection<Seat> getAllSeats() {
        return seats.values();
    }
}
