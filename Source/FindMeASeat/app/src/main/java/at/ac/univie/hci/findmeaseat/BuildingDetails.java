package at.ac.univie.hci.findmeaseat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class BuildingDetails extends AppCompatActivity {
    private TextView buildingName;
    private TextView seats;
    private ListView areas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_building_details);
        Intent intent = getIntent();
        String building = intent.getStringExtra("building");
        String seat = intent.getStringExtra("seat");
        int area = intent.getIntExtra("area",0);

        buildingName = findViewById(R.id.building_name_view);
        seats = findViewById(R.id.seats_view);
        areas = findViewById(R.id.area_list);

        buildingName.setText(building);
        seats.setText(seat);

        List<String> areaList = new ArrayList<String>();
        for(int i=1; i <= area; ++i){
            areaList.add("Stock " + i);
        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                areaList );

        areas.setAdapter(arrayAdapter);



    }
}
