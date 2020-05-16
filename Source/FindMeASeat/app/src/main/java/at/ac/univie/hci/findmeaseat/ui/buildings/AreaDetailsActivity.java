package at.ac.univie.hci.findmeaseat.ui.buildings;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import at.ac.univie.hci.findmeaseat.R;

public class AreaDetailsActivity extends AppCompatActivity {

    public static final String BUILDING_ID_EXTRA_NAME = "buildingId";
    public static final String AREA_NAME_EXTRA_NAME = "areaName";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_area_details);
    }
}
