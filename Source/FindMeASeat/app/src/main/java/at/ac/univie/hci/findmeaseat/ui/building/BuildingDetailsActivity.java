package at.ac.univie.hci.findmeaseat.ui.building;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.time.Clock;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Timer;
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

public class BuildingDetailsActivity extends AppCompatActivity {

    public static final String BUILDING_ID_EXTRA_NAME = "buildingId";

    private final BuildingService buildingService = BuildingServiceFactory.getSingletonInstance();
    private final FavoriteService favoriteService = FavoriteServiceFactory.getSingletonInstance();
    private final SeatStatusService seatStatusService = SeatStatusServiceFactory.getSingletonInstance();
    private final BookingService bookingService = BookingServiceFactory.getSingletonInstance();
    private final Calendar calendarFrom = Calendar.getInstance();
    private final Calendar calendarTo = Calendar.getInstance();
    private final Calendar timeFrom = Calendar.getInstance();
    private final Calendar timeTo = Calendar.getInstance();
    private Building building;
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yy", Locale.GERMAN);
    private final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_building_details);

        UUID buildingId = UUID.fromString(getIntent().getStringExtra(BUILDING_ID_EXTRA_NAME));
        this.building = buildingService.getBuildingById(buildingId);
        setTitle(building.getName());
        ListView areas = findViewById(R.id.area_list);
        EditText dateFrom = findViewById(R.id.from_date);
        EditText dateTo = findViewById(R.id.to_date);
        EditText startTimeEditText = findViewById(R.id.from_time);
        EditText endTimeEditText = findViewById(R.id.to_time);
        ImageButton favoriteButton = findViewById(R.id.favorite_button);

        dateFrom.setText(dateFormat.format(calendarFrom.getTime()));
        dateTo.setText(dateFormat.format(calendarTo.getTime()));
        startTimeEditText.setText(LocalTime.now().format(timeFormatter));
        endTimeEditText.setText(LocalTime.now().plusHours(1).format(timeFormatter));

        DatePickerDialog.OnDateSetListener dateSetListenerFrom = (view, year, month, day) -> {
            calendarFrom.set(Calendar.YEAR, year);
            calendarFrom.set(Calendar.MONTH, month);
            calendarFrom.set(Calendar.DAY_OF_MONTH, day);
            dateFrom.setText(dateFormat.format(calendarFrom.getTime()));
            updateFreeSeats();
        };

        DatePickerDialog.OnDateSetListener dateSetListenerTo = (view, year, month, day) -> {
            calendarTo.set(Calendar.YEAR, year);
            calendarTo.set(Calendar.MONTH, month);
            calendarTo.set(Calendar.DAY_OF_MONTH, day);
            dateTo.setText(dateFormat.format(calendarTo.getTime()));
            updateFreeSeats();
        };


        dateFrom.setOnClickListener(v -> new DatePickerDialog(BuildingDetailsActivity.this, R.style.picker, dateSetListenerFrom, calendarFrom
                .get(Calendar.YEAR), calendarFrom.get(Calendar.MONTH),
                calendarFrom.get(Calendar.DAY_OF_MONTH)).show());

        dateTo.setOnClickListener(v -> new DatePickerDialog(BuildingDetailsActivity.this, R.style.picker, dateSetListenerTo, calendarTo
                .get(Calendar.YEAR), calendarTo.get(Calendar.MONTH),
                calendarTo.get(Calendar.DAY_OF_MONTH)).show());

        startTimeEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int hour = timeFrom.get(Calendar.HOUR_OF_DAY);
                int minute = timeFrom.get(Calendar.MINUTE);
                TimePickerDialog timePicker = new TimePickerDialog(BuildingDetailsActivity.this, R.style.picker, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        startTimeEditText.setText( selectedHour + ":" + selectedMinute);
                    }
                }, hour, minute, true);
                timePicker.show();
            }
        });

        endTimeEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int hour = timeTo.get(Calendar.HOUR_OF_DAY);
                int minute = timeTo.get(Calendar.MINUTE);
                TimePickerDialog timePicker = new TimePickerDialog(BuildingDetailsActivity.this, R.style.picker, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        endTimeEditText.setText( selectedHour + ":" + selectedMinute);
                    }
                }, hour, minute, true);
                timePicker.show();
            }
        });

        updateFreeSeats();

        List<String> areaNames = building.getAllAreas().stream().map(Area::getName).collect(Collectors.toList());
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, areaNames);
        areas.setAdapter(arrayAdapter);

        areas.setOnItemClickListener((parent, view, position, id) -> {
            Area area = building.getArea(areaNames.get(position));
            Intent intent = new Intent(this, AreaDetailsActivity.class);
            intent.putExtra(AreaDetailsActivity.BUILDING_ID_EXTRA_NAME, building.getId().toString());
            intent.putExtra(AreaDetailsActivity.AREA_NAME_EXTRA_NAME, area.getName());
            intent.putExtra(AreaDetailsActivity.START_DATE_EXTRA_NAME, getPeriod().getStart().toString());
            intent.putExtra(AreaDetailsActivity.END_DATE_EXTRA_NAME, getPeriod().getEnd().toString());
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
        try {
            bookingService.bookAnySeat(building, getPeriod());
            Toast.makeText(this, "Sitz wurde gebucht", Toast.LENGTH_LONG).show();
            updateFreeSeats();
        } catch (Throwable exception) {
            Toast.makeText(this, "Keine Plätze mehr frei", Toast.LENGTH_LONG).show();
        }

    }

    private Period getPeriod() {
        EditText startTimeEditText = findViewById(R.id.from_time);
        LocalTime startTime = LocalTime.parse(startTimeEditText.getText(), DateTimeFormatter.ofPattern("HH:mm"));
        EditText endTimeEditText = findViewById(R.id.to_time);
        LocalTime endTime = LocalTime.parse(endTimeEditText.getText(), DateTimeFormatter.ofPattern("HH:mm"));
        LocalDateTime startDateTime = calendarFrom.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime()
                .withHour(startTime.getHour()).withMinute(startTime.getMinute());
        LocalDateTime endDateTime = calendarTo.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime()
                .withHour(endTime.getHour()).withMinute(endTime.getMinute());
        return new Period(startDateTime, endDateTime);
    }

    private void displayAsFavorite(ImageButton favoriteButton) {
        favoriteButton.setImageResource(android.R.drawable.btn_star_big_on);
    }

    private void displayAsNotFavorite(ImageButton favoriteButton) {
        favoriteButton.setImageResource(android.R.drawable.btn_star_big_off);
    }

    private void updateFreeSeats() {
        TextView seats = findViewById(R.id.seats_view);
        List<Seat> freeSeats = seatStatusService.getFreeSeats(building, getPeriod());
        seats.setText(String.format(Locale.GERMAN, "%d / %d", freeSeats.size(), building.maximalSeats()));
    }

}
