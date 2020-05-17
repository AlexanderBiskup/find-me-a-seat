package at.ac.univie.hci.findmeaseat.model.building.service;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import at.ac.univie.hci.findmeaseat.model.building.Address;
import at.ac.univie.hci.findmeaseat.model.building.Area;
import at.ac.univie.hci.findmeaseat.model.building.Building;

public class DummyBuildingService implements BuildingService {

    @Override
    public Building getBuildingById(UUID id) {
        Address address = new Address("Währingerstraße 29", "Wien", "1180");
        Building building = new Building("Fakultät für Informatik", address);
        building.addArea("1. Stock");
        Area area = building.getArea("1. Stock");
        area.addSeat("A1");
        area.addSeat("A2");
        area.addSeat("A3");
        area.addSeat("A4");
        area.addSeat("A5");
        return building;
    }

    @Override
    public List<Building> getAllBuildings() {
        return Collections.emptyList();
    }

}
