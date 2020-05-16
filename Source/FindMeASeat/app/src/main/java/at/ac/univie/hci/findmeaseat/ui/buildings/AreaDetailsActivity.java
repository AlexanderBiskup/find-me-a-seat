package at.ac.univie.hci.findmeaseat.ui.buildings;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.UUID;

import at.ac.univie.hci.findmeaseat.R;
import at.ac.univie.hci.findmeaseat.model.building.Area;
import at.ac.univie.hci.findmeaseat.model.building.Building;
import at.ac.univie.hci.findmeaseat.model.building.Seat;
import at.ac.univie.hci.findmeaseat.model.building.service.BuildingService;
import at.ac.univie.hci.findmeaseat.model.building.service.DummyBuildingService;

public class AreaDetailsActivity extends AppCompatActivity implements SeatsAdapter.SeatSelectionHandler {

    public static final String BUILDING_ID_EXTRA_NAME = "buildingId";
    public static final String AREA_NAME_EXTRA_NAME = "areaName";

    private BuildingService buildingService = new DummyBuildingService(); // TODO use factory

    private RecyclerView.Adapter seatsAdapter;
    private Seat selectedSeat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_area_details);
        UUID buildingId = UUID.fromString(getIntent().getStringExtra(BUILDING_ID_EXTRA_NAME));
        String areaName = getIntent().getStringExtra(AREA_NAME_EXTRA_NAME);
        Building building = buildingService.getBuildingById(buildingId);
        Area area = building.getArea(areaName);
        setTitle(String.format("%s - %s", area.getName(), area.getBuilding().getName()));

        RecyclerView seatsRecyclerView = findViewById(R.id.seatsRecyclerView);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 5);
        seatsRecyclerView.setLayoutManager(layoutManager);
        seatsAdapter = new SeatsAdapter(area.getAllSeats(),this, this);
        seatsRecyclerView.setAdapter(seatsAdapter);
    }

    @Override
    public boolean isSelected(Seat seat) {
        return seat.equals(selectedSeat);
    }

    @Override
    public void select(Seat seat) {
        selectedSeat = seat;
        seatsAdapter.notifyDataSetChanged(); // TODO use index to only update changed item
        TextView selectedSeatTextView = findViewById(R.id.selected_seat_text_view);
        selectedSeatTextView.setText(String.format("Auswahl: %s", seat.getName()));
    }
}
