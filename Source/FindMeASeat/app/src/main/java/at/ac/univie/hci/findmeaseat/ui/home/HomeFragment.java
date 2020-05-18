package at.ac.univie.hci.findmeaseat.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;

import java.util.ArrayList;
import java.util.List;

import at.ac.univie.hci.findmeaseat.R;
import at.ac.univie.hci.findmeaseat.model.building.Address;
import at.ac.univie.hci.findmeaseat.model.building.Building;
import at.ac.univie.hci.findmeaseat.model.building.service.BuildingService;
import at.ac.univie.hci.findmeaseat.model.building.service.BuildingServiceFactory;
import at.ac.univie.hci.findmeaseat.model.building.service.DummyBuildingService;
import at.ac.univie.hci.findmeaseat.ui.building.BuildingAdapter;
import at.ac.univie.hci.findmeaseat.ui.building.BuildingDetailsActivity;
import at.ac.univie.hci.findmeaseat.ui.building.BuildingFragment;

public class HomeFragment extends Fragment {

    List<Building> buildingsList = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);




        return root;
    }

}
