package at.ac.univie.hci.findmeaseat.ui.building;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.stream.Collectors;

import at.ac.univie.hci.findmeaseat.R;
import at.ac.univie.hci.findmeaseat.model.booking.BookingService;
import at.ac.univie.hci.findmeaseat.model.booking.BookingServiceFactory;
import at.ac.univie.hci.findmeaseat.model.booking.Period;
import at.ac.univie.hci.findmeaseat.model.booking.status.SeatStatusService;
import at.ac.univie.hci.findmeaseat.model.booking.status.SeatStatusServiceFactory;
import at.ac.univie.hci.findmeaseat.model.building.Area;
import at.ac.univie.hci.findmeaseat.model.building.Building;
import at.ac.univie.hci.findmeaseat.model.building.Seat;
import at.ac.univie.hci.findmeaseat.model.building.service.BuildingService;
import at.ac.univie.hci.findmeaseat.model.building.service.BuildingServiceFactory;
import at.ac.univie.hci.findmeaseat.model.user.favorite.FavoriteService;
import at.ac.univie.hci.findmeaseat.model.user.favorite.FavoriteServiceFactory;
import at.ac.univie.hci.findmeaseat.ui.buildings.AreaDetailsActivity;

import static java.time.LocalDateTime.now;

public class BuildingDetailsActivity extends AppCompatActivity {

    public static final String BUILDING_ID_EXTRA_NAME = "buildingId";

    private final BuildingService buildingService = BuildingServiceFactory.getSingletonInstance();
    private final FavoriteService favoriteService = FavoriteServiceFactory.getSingletonInstance();
    private final SeatStatusService seatStatusService = SeatStatusServiceFactory.getSingletonInstance();
    private final BookingService bookingService = BookingServiceFactory.getSingletonInstance();
    private final Calendar calendarFrom = Calendar.getInstance();
    private final Calendar calendarTo = Calendar.getInstance();
    private Building building;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_building_details);

        UUID buildingId = UUID.fromString(getIntent().getStringExtra(BUILDING_ID_EXTRA_NAME));
        this.building = buildingService.getBuildingById(buildingId);
        setTitle(building.getName());
        TextView seats = findViewById(R.id.seats_view);
        ListView areas = findViewById(R.id.area_list);
        EditText dateFrom = findViewById(R.id.from_date);
        EditText dateTo = findViewById(R.id.to_date);
        ImageButton favoriteButton = findViewById(R.id.favorite_button);

        DatePickerDialog.OnDateSetListener dateSetListenerFrom = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                calendarFrom.set(Calendar.YEAR, year);
                calendarFrom.set(Calendar.MONTH, month);
                calendarFrom.set(Calendar.DAY_OF_MONTH, day);
                String dateFormat = "MM/dd/yy";
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat, Locale.GERMAN);
                dateFrom.setText(simpleDateFormat.format(calendarFrom.getTime()));
            }
        };

        DatePickerDialog.OnDateSetListener dateSetListenerTo = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                calendarTo.set(Calendar.YEAR, year);
                calendarTo.set(Calendar.MONTH, month);
                calendarTo.set(Calendar.DAY_OF_MONTH, day);
                String dateFormat = "MM/dd/yy";
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat, Locale.GERMAN);
                dateTo.setText(simpleDateFormat.format(calendarTo.getTime()));
            }
        };

        dateFrom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(BuildingDetailsActivity.this, R.style.Datepicker, dateSetListenerFrom, calendarFrom
                        .get(Calendar.YEAR), calendarFrom.get(Calendar.MONTH),
                        calendarFrom.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        dateTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(BuildingDetailsActivity.this, R.style.Datepicker, dateSetListenerTo, calendarTo
                        .get(Calendar.YEAR), calendarTo.get(Calendar.MONTH),
                        calendarTo.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        List<Seat> freeSeats = seatStatusService.getFreeSeats(building, new Period(now(), now().plusHours(1)));
        seats.setText(String.format(Locale.GERMAN, "%d / %d", freeSeats.size(), building.maximalSeats()));
        List<String> areaNames = building.getAllAreas().stream().map(Area::getName).collect(Collectors.toList());
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, areaNames);
        areas.setAdapter(arrayAdapter);

        areas.setOnItemClickListener((parent, view, position, id) -> {
            Area area = building.getArea(areaNames.get(position));
            Intent intent = new Intent(this, AreaDetailsActivity.class);
            intent.putExtra(AreaDetailsActivity.BUILDING_ID_EXTRA_NAME, building.getId().toString());
            intent.putExtra(AreaDetailsActivity.AREA_NAME_EXTRA_NAME, area.getName());
            startActivity(intent);
        });

        if (favoriteService.isFavorite(building)) {
            displayAsFavorite(favoriteButton);
        } else {
            displayAsNotFavorite(favoriteButton);
        }

        favoriteButton.setOnClickListener(v -> {
            if (favoriteService.isFavorite(building)) {
                displayAsNotFavorite(favoriteButton);
                Toast.makeText(this, "Von Startseite entfernt", Toast.LENGTH_SHORT).show();
                favoriteService.removeFromFavorites(building);
            } else {
                displayAsFavorite(favoriteButton);
                Toast.makeText(this, "Zur Startseite hinzugefügt", Toast.LENGTH_SHORT).show();
                favoriteService.addToFavorites(building);
            }
        });

    }

    public void performQuickBooking(View view) {
        bookingService.bookAnySeat(building, new Period(now(), now().plusHours(1)));
        Toast.makeText(this, "Sitz wurde gebucht", Toast.LENGTH_LONG).show();
    }

    private void displayAsFavorite(ImageButton favoriteButton) {
        favoriteButton.setImageResource(android.R.drawable.btn_star_big_on);
    }

    private void displayAsNotFavorite(ImageButton favoriteButton) {
        favoriteButton.setImageResource(android.R.drawable.btn_star_big_off);
    }

}
