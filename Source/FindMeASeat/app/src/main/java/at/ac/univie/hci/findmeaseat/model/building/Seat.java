package at.ac.univie.hci.findmeaseat.model.building;

public final class Seat {

    private final String name;
    private final Area area;

    Seat(String name, Area area) {
        this.name = name;
        this.area = area;
    }

    public String getName() {
        return name;
    }

    public Area getArea() {
        return area;
    }

}
