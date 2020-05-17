package at.ac.univie.hci.findmeaseat.model.building;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import at.ac.univie.hci.findmeaseat.R;

public class BuildingAdapter extends BaseAdapter implements Filterable{
    private Context context;
    private List<Building>  buildingList;
    private List<Building> filteredBuildingList;

    public BuildingAdapter(Context context, List<Building> buildingList) {
        this.context = context;
        this.buildingList = buildingList;
        this.filteredBuildingList = buildingList;
    }

    @Override
    public int getCount() {
        return buildingList.size();
    }

    @Override
    public Object getItem(int position) {
        return buildingList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).
                    inflate(R.layout.activity_list_items_building, parent, false);
        }

        TextView buildingName = convertView.findViewById(R.id.building_list_name);
        TextView buildingAddress = convertView.findViewById(R.id.building_list_adress);
        TextView buildingFloor = convertView.findViewById(R.id.building_list_seat);

        Building buildingItem = (Building) getItem(position);

        buildingName.setText(buildingItem.getName());
        buildingAddress.setText(buildingItem.getAddress().getStreet());
        buildingFloor.setText(buildingItem.availableleSeats() + "/" + buildingItem.maximalSeats());
        return convertView;
    }

    @Override
    public Filter getFilter(){
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults filterResults = new FilterResults();
                if (constraint == null ||constraint.length() == 0) {
                    filterResults.values = buildingList;
                    filterResults.count = buildingList.size();
                } else {
                    String upperConstraintName = constraint.toString().toUpperCase();
                    String upperConstraintAddress = constraint.toString().toUpperCase();
                    List<Building> tempBuilding = new ArrayList<>();

                    for (int i = 0; i < filteredBuildingList.size(); ++i) {
                        if (filteredBuildingList.get(i).getName().toUpperCase().contains(upperConstraintName) || filteredBuildingList.get(i).getAddress().getStreet().toUpperCase().contains(upperConstraintAddress)) {
                            Building b = new Building(filteredBuildingList.get(i).getName(), filteredBuildingList.get(i).getAddress());
                            tempBuilding.add(b);
                        }
                    }
                    filterResults.values = tempBuilding;
                    filterResults.count = tempBuilding.size();
                }
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                buildingList = (ArrayList<Building>) results.values;
                notifyDataSetChanged();
            }
        };

    }


}
