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
import at.ac.univie.hci.findmeaseat.model.booking.status.SeatStatusService;
import at.ac.univie.hci.findmeaseat.model.booking.status.SeatStatusServiceFactory;
import at.ac.univie.hci.findmeaseat.model.user.favorite.FavoriteService;
import at.ac.univie.hci.findmeaseat.model.user.favorite.FavoriteServiceFactory;

public class FavoriteBuildingsFragment extends Fragment {

    private FavoriteService favoriteService = FavoriteServiceFactory.getSingletonInstance();
    private SeatStatusService seatStatusService = SeatStatusServiceFactory.getSingletonInstance();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_favorite_buildings, container, false);

        final ListView building = root.findViewById(R.id.favorite_building_list);
        BuildingAdapter adapter = new BuildingAdapter(getContext(), favoriteService.getAllFavorites(), seatStatusService);
        building.setAdapter(adapter);

        return root;
    }
}
