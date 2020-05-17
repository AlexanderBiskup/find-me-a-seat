package at.ac.univie.hci.findmeaseat.model.building;

import androidx.annotation.NonNull;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public final class Building {

    private final UUID id;
    private final String name;
    private final Address address;
    private final Map<String, Area> areas;

    public Building(@NonNull String name, @NonNull Address address) {
        id = UUID.randomUUID();
        this.name = name;
        this.address = address;
        this.areas = new HashMap<>();
    }

    public void addArea(String name) {
        areas.put(name, new Area(name, this));
    }

    public Area getArea(String name) {
        Area area = areas.get(name);
        if(area == null) throw new IllegalArgumentException("Area not found.");
        return area;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Address getAddress() {
        return address;
    }

    public Collection<Area> getAllAreas() {
        return areas.values();
    }

}
