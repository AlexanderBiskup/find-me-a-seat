package at.ac.univie.hci.findmeaseat.ui.home;

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

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import at.ac.univie.hci.findmeaseat.BuildingAdapter;
import at.ac.univie.hci.findmeaseat.BuildingDetails;
import at.ac.univie.hci.findmeaseat.R;
import at.ac.univie.hci.findmeaseat.model.building.Address;
import at.ac.univie.hci.findmeaseat.model.building.Building;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private BuildingAdapter adapterBuilding;

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        final ListView buildingList = root.findViewById(R.id.building_list);
        EditText filter = root.findViewById(R.id.filter_text);

        final List<Building> buildings = new ArrayList<>();
        InputStream is = getResources().openRawResource(R.raw.buildings);
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        String line = "";
        try{
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] separator = line.split(";");
                String buildingName = separator[0];
                String street = separator[1];
                String city = separator[2];
                String zibCode = separator[3];
                buildings.add(new Building(buildingName,new Address(street,city,zibCode)));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch(IOException e){

            e.printStackTrace();
        }

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
                Intent intent = new Intent(HomeFragment.this.getActivity(), BuildingDetails.class);



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



        //final TextView textView = root.findViewById(R.id.text_home);
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                //textView.setText(s);
            }
        });
        return root;
    }
}
