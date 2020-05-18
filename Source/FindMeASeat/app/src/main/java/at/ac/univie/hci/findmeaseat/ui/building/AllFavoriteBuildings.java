package at.ac.univie.hci.findmeaseat.ui.building;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import at.ac.univie.hci.findmeaseat.R;
import at.ac.univie.hci.findmeaseat.model.building.Building;
import at.ac.univie.hci.findmeaseat.model.user.favorite.FavoriteService;
import at.ac.univie.hci.findmeaseat.model.user.favorite.FavoriteServiceFactory;

public class AllFavoriteBuildings extends Fragment {

    private FavoriteService favoriteService = FavoriteServiceFactory.getSingletonInstance();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_favorite_buildings, container, false);

        final ListView buildinglist = root.findViewById(R.id.favorite_building_list);
        BuildingAdapter adapter = new BuildingAdapter(getContext(), favoriteService.getAllFavorites());
        buildinglist.setAdapter(adapter);

        return root;
    }
}
