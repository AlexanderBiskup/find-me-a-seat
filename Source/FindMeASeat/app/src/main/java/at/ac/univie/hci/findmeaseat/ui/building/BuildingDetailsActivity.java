package at.ac.univie.hci.findmeaseat.ui.building;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.stream.Collectors;

import at.ac.univie.hci.findmeaseat.R;
import at.ac.univie.hci.findmeaseat.model.building.Area;
import at.ac.univie.hci.findmeaseat.model.building.Building;
import at.ac.univie.hci.findmeaseat.model.building.service.BuildingService;
import at.ac.univie.hci.findmeaseat.model.building.service.BuildingServiceFactory;

public class BuildingDetailsActivity extends AppCompatActivity {

    public static final String BUILDING_ID_EXTRA_NAME = "buildingId";

    private final BuildingService buildingService = BuildingServiceFactory.getSingletonInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_building_details);

        UUID buildingId = UUID.fromString(getIntent().getStringExtra(BUILDING_ID_EXTRA_NAME));
        Building building = buildingService.getBuildingById(buildingId);
        TextView buildingName = findViewById(R.id.building_name_view);
        TextView seats = findViewById(R.id.seats_view);
        ListView areas = findViewById(R.id.area_list);
        buildingName.setText(building.getName());

        seats.setText(String.format(Locale.GERMAN, "%d / %d", building.availableleSeats(), building.maximalSeats()));
        List<String> areaNames = building.getAllAreas().stream().map(Area::getName).collect(Collectors.toList());
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, areaNames);
        areas.setAdapter(arrayAdapter);
    }

}
