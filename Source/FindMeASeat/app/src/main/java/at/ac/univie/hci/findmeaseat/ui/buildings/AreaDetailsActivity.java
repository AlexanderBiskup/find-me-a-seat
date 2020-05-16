package at.ac.univie.hci.findmeaseat.ui.buildings;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import java.util.UUID;

import at.ac.univie.hci.findmeaseat.R;
import at.ac.univie.hci.findmeaseat.model.building.Area;
import at.ac.univie.hci.findmeaseat.model.building.Building;
import at.ac.univie.hci.findmeaseat.model.building.service.BuildingService;
import at.ac.univie.hci.findmeaseat.model.building.service.DummyBuildingService;

public class AreaDetailsActivity extends AppCompatActivity {

    public static final String BUILDING_ID_EXTRA_NAME = "buildingId";
    public static final String AREA_NAME_EXTRA_NAME = "areaName";

    private BuildingService buildingService = new DummyBuildingService(); // TODO use factory

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_area_details);
        UUID buildingId = UUID.fromString(getIntent().getStringExtra(BUILDING_ID_EXTRA_NAME));
        String areaName = getIntent().getStringExtra(AREA_NAME_EXTRA_NAME);
        Building building = buildingService.getBuildingById(buildingId);
        Area area = building.getArea(areaName);
        setTitle(area.getName());
    }

}
