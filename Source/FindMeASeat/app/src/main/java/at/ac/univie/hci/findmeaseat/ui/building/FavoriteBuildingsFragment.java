package at.ac.univie.hci.findmeaseat.ui.building;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import at.ac.univie.hci.findmeaseat.R;
import at.ac.univie.hci.findmeaseat.model.booking.status.SeatStatusService;
import at.ac.univie.hci.findmeaseat.model.booking.status.SeatStatusServiceFactory;
import at.ac.univie.hci.findmeaseat.model.building.Building;
import at.ac.univie.hci.findmeaseat.model.user.favorite.FavoriteService;
import at.ac.univie.hci.findmeaseat.model.user.favorite.FavoriteServiceFactory;

public class FavoriteBuildingsFragment extends Fragment {

    private FavoriteService favoriteService = FavoriteServiceFactory.getSingletonInstance();
    private SeatStatusService seatStatusService = SeatStatusServiceFactory.getSingletonInstance();
    TextView nofavorites;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_favorite_buildings, container, false);

        nofavorites = root.findViewById(R.id.nofavorites);
        if(favoriteService.getAllFavorites().size() == 0){
            nofavorites.setText("Es gibt keine gemerkte Gebäude!");
        }

        final ListView buildings = root.findViewById(R.id.favorite_building_list);
        BuildingAdapter buildingAdapter = new BuildingAdapter(getContext(), favoriteService.getAllFavorites(), seatStatusService);
        buildings.setAdapter(buildingAdapter);

        buildings.setOnItemClickListener((adapter, view, position, id) -> {
            Intent intent = new Intent(requireActivity(), BuildingDetailsActivity.class);
            Building building = (Building) adapter.getItemAtPosition(position);
            intent.putExtra(BuildingDetailsActivity.BUILDING_ID_EXTRA_NAME, building.getId().toString());
            startActivity(intent);
        });



        return root;
    }



}
