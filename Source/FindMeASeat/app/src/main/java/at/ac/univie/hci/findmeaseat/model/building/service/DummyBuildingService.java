package at.ac.univie.hci.findmeaseat.model.building.service;

import android.content.Context;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import at.ac.univie.hci.findmeaseat.model.building.Building;

public class DummyBuildingService implements BuildingService {

    private Map<UUID, Building> buildings = new HashMap<>();

    @Override
    public Building getBuildingById(UUID id) {
        Building building =  buildings.get(id);
        if(building == null) throw new IllegalArgumentException("Invalid building id.");
        return building;
    }

    @Override
    public List<Building> getAllBuildings() {
        return new ArrayList<>(buildings.values());
    }

    public void initializeDummyBuildings(Context context) {
        CSVBuildingLoader loader = new CSVBuildingLoader();
        List<Building> buildings = loader.loadBuildings(context);
        buildings.forEach((building) -> {
            building.addArea("1. Stock");
            building.addArea("2. Stock");
            this.buildings.put(building.getId(), building);
        });
    }

}
