package at.ac.univie.hci.findmeaseat.ui.building;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import at.ac.univie.hci.findmeaseat.R;

public class BuildingDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_building_details);
        Intent intent = getIntent();
        String building = intent.getStringExtra("building");
        String seat = intent.getStringExtra("seat");
        int area = intent.getIntExtra("area",0);

        TextView buildingName = findViewById(R.id.building_name_view);
        TextView seats = findViewById(R.id.seats_view);
        ListView areas = findViewById(R.id.area_list);

        buildingName.setText(building);
        seats.setText(seat);

        List<String> areaList = new ArrayList<>();
        for(int i=1; i <= area; ++i){
            areaList.add("Stock " + i);
        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                areaList );

        areas.setAdapter(arrayAdapter);
    }

}
