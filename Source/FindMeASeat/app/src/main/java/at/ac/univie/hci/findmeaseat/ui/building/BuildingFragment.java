package at.ac.univie.hci.findmeaseat.ui.building;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import java.util.List;

import at.ac.univie.hci.findmeaseat.model.building.BuildingAdapter;
import at.ac.univie.hci.findmeaseat.R;
import at.ac.univie.hci.findmeaseat.model.building.Building;
import at.ac.univie.hci.findmeaseat.model.building.CSVBuildingLoader;


public class BuildingFragment extends Fragment {
    private BuildingAdapter adapterBuilding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        BuildingViewModel buildingViewModel;
        buildingViewModel = ViewModelProviders.of(this).get(BuildingViewModel.class);
        View root = inflater.inflate(R.layout.fragment_building, container, false);

        final ListView buildingList = root.findViewById(R.id.building_list);
        EditText filter = root.findViewById(R.id.filter_text);

        List<Building> buildings;

        CSVBuildingLoader c = new CSVBuildingLoader();
        buildings = c.loadBuildings(requireContext());

        adapterBuilding = new BuildingAdapter(getContext(), buildings);
        buildingList.setAdapter(adapterBuilding);

        filter.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapterBuilding.getFilter().filter(s);

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        buildingList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int position, long id) {
                Intent intent = new Intent(BuildingFragment.this.getActivity(), BuildingDetailsActivity.class);



                Building buildingItem = (Building) adapterBuilding.getItem(position);
                String buildingName = buildingItem.getName();
                String seats = buildingItem.availableleSeats() + "/" +buildingItem.maximalSeats();

                int area = buildingItem.floor();

                intent.putExtra("building", buildingName);
                intent.putExtra("seat", seats);
                intent.putExtra("area", area);

                startActivityForResult(intent, 1);
            }
        });



        buildingViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
            }
        });
        return root;
    }
}
