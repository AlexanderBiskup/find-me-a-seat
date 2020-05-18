package at.ac.univie.hci.findmeaseat.ui.buildings;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalDateTime;
import java.util.UUID;

import at.ac.univie.hci.findmeaseat.R;
import at.ac.univie.hci.findmeaseat.model.booking.Period;
import at.ac.univie.hci.findmeaseat.model.booking.status.SeatStatusService;
import at.ac.univie.hci.findmeaseat.model.booking.status.SeatStatusServiceFactory;
import at.ac.univie.hci.findmeaseat.model.building.Area;
import at.ac.univie.hci.findmeaseat.model.building.Building;
import at.ac.univie.hci.findmeaseat.model.building.Seat;
import at.ac.univie.hci.findmeaseat.model.building.service.BuildingService;
import at.ac.univie.hci.findmeaseat.model.building.service.BuildingServiceFactory;

import static java.time.LocalDateTime.now;

public class AreaDetailsActivity extends AppCompatActivity implements SeatsAdapter.SeatSelectionHandler {

    public static final String BUILDING_ID_EXTRA_NAME = "buildingId";
    public static final String AREA_NAME_EXTRA_NAME = "areaName";

    private BuildingService buildingService = BuildingServiceFactory.getSingletonInstance();
    private SeatStatusService seatStatusService = SeatStatusServiceFactory.getSingletonInstance();

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
        seatsAdapter = new SeatsAdapter(area.getAllSeats(),this, this, seatStatusService, new Period(now(), now().plusHours(1)));
        seatsRecyclerView.setAdapter(seatsAdapter);
    }

    @Override
    public boolean isSelected(Seat seat) {
        return seat.equals(selectedSeat);
    }

    @Override
    public void select(Seat seat) {
        selectedSeat = seat;
        seatsAdapter.notifyDataSetChanged();
        TextView selectedSeatTextView = findViewById(R.id.selected_seat_text_view);
        selectedSeatTextView.setText(String.format("Auswahl: %s", seat.getName()));
    }
}
