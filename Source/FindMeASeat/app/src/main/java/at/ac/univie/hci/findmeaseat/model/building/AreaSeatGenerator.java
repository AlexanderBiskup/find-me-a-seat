package at.ac.univie.hci.findmeaseat.model.building;

import java.security.SecureRandom;
import java.util.Collection;
import java.util.Random;

public class AreaSeatGenerator {
    public void areaSeatGenerator(Building building, Collection<Building> buildings) {
        Random r = new SecureRandom();
        int randomAreas = r.nextInt(8 - 1) + 1;

        for (int i = 1; i <= randomAreas; ++i)
            building.addArea(i + "." + " " + "Stock");

        int seatNumber = 1;
        char c = 'A';
        for (int floor = 1; floor <= randomAreas; ++floor) {
            if (floor > 1)
                ++c;
            if (seatNumber == 11) {
                seatNumber = 0;
            }
            for (; seatNumber <= 10; ++seatNumber) {
                char finalC = c;
                String finalS = String.valueOf(c);
                int finalSeatNumber = seatNumber;
                Collection<Area> areas = building.getAllAreas();
                areas.forEach(area -> area.addSeat(finalS + finalSeatNumber));
            }
        }
    }

    public int maximalSeats(Area a) {
        Collection<Seat> seats = a.getAllSeats();
        return seats.size();
    }
}
