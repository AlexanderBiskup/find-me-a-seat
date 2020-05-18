package at.ac.univie.hci.findmeaseat.model.user.favorite;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import at.ac.univie.hci.findmeaseat.model.building.Address;
import at.ac.univie.hci.findmeaseat.model.building.Building;
import at.ac.univie.hci.findmeaseat.model.building.service.DummyBuildingService;

public class DummyFavoriteService implements FavoriteService{

    DummyBuildingService dummyBuildingService = new DummyBuildingService();
    List<Building> favorite = new ArrayList<>();

    DummyFavoriteService(){

        Address address = new Address("Waehringerstrasse 29", "Wien", "1180");
        Building building = new Building("Fakultaet für Informatik", address);

        favorite.add(building);
        favorite.add(building);
        favorite.add(building);
    }

    @Override
    public void addToFavorites(Building building) {

    }

    @Override
    public void removeFromFavorites(Building building) {

    }

    @Override
    public boolean isFavorite(Building building) {
        return false;
    }

    @Override
    public List<Building> getAllFavorites() {
        return favorite;
    }
}
