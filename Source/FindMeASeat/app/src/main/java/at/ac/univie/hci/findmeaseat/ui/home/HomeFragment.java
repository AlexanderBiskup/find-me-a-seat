package at.ac.univie.hci.findmeaseat.ui.home;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import at.ac.univie.hci.findmeaseat.BuildingDetails;
import at.ac.univie.hci.findmeaseat.MainActivity;
import at.ac.univie.hci.findmeaseat.R;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        ListView buildingList = root.findViewById(R.id.buildingList);

        String[] buildings = new String[] {
                "Fakultät für Informatik",
                "Fakultät für Chemie",
                "Hauptgebäude",
                "Fakultät für Informatik",
                "Fakultät für Chemie",
                "Hauptgebäude"
        };

        List<String> buildings_list = new ArrayList<>(Arrays.asList(buildings));

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>
                (getActivity(), android.R.layout.simple_list_item_1, buildings_list);

        buildingList.setAdapter(arrayAdapter);


        buildingList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int position, long id) {
                Intent appInfo = new Intent(HomeFragment.this.getActivity(), BuildingDetails.class);
                startActivity(appInfo);
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
