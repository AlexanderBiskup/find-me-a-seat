package at.ac.univie.hci.findmeaseat.ui.building;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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
import at.ac.univie.hci.findmeaseat.model.user.favorite.FavoriteService;
import at.ac.univie.hci.findmeaseat.model.user.favorite.FavoriteServiceFactory;
import at.ac.univie.hci.findmeaseat.ui.buildings.AreaDetailsActivity;

public class BuildingDetailsActivity extends AppCompatActivity {

    public static final String BUILDING_ID_EXTRA_NAME = "buildingId";

    private final BuildingService buildingService = BuildingServiceFactory.getSingletonInstance();
    private final FavoriteService favoriteService = FavoriteServiceFactory.getSingletonInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_building_details);

        UUID buildingId = UUID.fromString(getIntent().getStringExtra(BUILDING_ID_EXTRA_NAME));
        Building building = buildingService.getBuildingById(buildingId);
        setTitle(building.getName());
        TextView seats = findViewById(R.id.seats_view);
        ListView areas = findViewById(R.id.area_list);
        ImageButton favoriteButton = findViewById(R.id.favorite_button);

        seats.setText(String.format(Locale.GERMAN, "%d / %d", building.availableSeats(), building.maximalSeats()));
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

        if(favoriteService.isFavorite(building)) {
            displayAsFavorite(favoriteButton);
        } else {
            displayAsNotFavorite(favoriteButton);
        }

        favoriteButton.setOnClickListener(v -> {
            if(favoriteService.isFavorite(building)){
                displayAsNotFavorite(favoriteButton);
                Toast.makeText(this, "Von Startseite entfernt", Toast.LENGTH_SHORT).show();
                favoriteService.removeFromFavorites(building);
            }else{
                displayAsFavorite(favoriteButton);
                Toast.makeText(this, "Zur Startseite hinzugefügt", Toast.LENGTH_SHORT).show();
                favoriteService.addToFavorites(building);
            }
        });

    }

    private void displayAsFavorite(ImageButton favoriteButton) {
        favoriteButton.setImageResource(android.R.drawable.btn_star_big_on);
    }

    private void displayAsNotFavorite(ImageButton favoriteButton) {
        favoriteButton.setImageResource(android.R.drawable.btn_star_big_off);
    }

}
