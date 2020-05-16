package at.ac.univie.hci.findmeaseat;

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

import at.ac.univie.hci.findmeaseat.model.building.Building;

public class BuildingAdapter extends BaseAdapter implements Filterable{
    private Context context;
    private List<Building>  buildingList;
    private List<Building>  filtredBuildingList;

    public BuildingAdapter(Context context, List<Building> buildingList) {
        this.context = context;
        this.buildingList = buildingList;
        this.filtredBuildingList = buildingList;
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
        TextView buildingAdress = convertView.findViewById(R.id.building_list_adress);
        TextView buildingFloor = convertView.findViewById(R.id.building_list_seat);

        Building buildingItem = (Building) getItem(position);

        buildingName.setText(buildingItem.getName());
        buildingAdress.setText(buildingItem.getAddress().getStreet());
        buildingFloor.setText(buildingItem.availableleSeats() + "/" + buildingItem.maximalSeats());
        return convertView;
    }

    @Override
    public Filter getFilter(){
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults filterResults = new FilterResults();
                if (constraint.length() == 0 || constraint.equals(null)) {
                    filterResults.values = buildingList;
                    filterResults.count = buildingList.size();
                } else {
                    String upperConstraint = constraint.toString().toUpperCase();
                    List<Building> tempBuilding = new ArrayList<>();

                    for (int i = 0; i < filtredBuildingList.size(); ++i) {
                        if (filtredBuildingList.get(i).getName().toUpperCase().contains(upperConstraint)) {
                            Building b = new Building(filtredBuildingList.get(i).getName(),filtredBuildingList.get(i).getAddress());
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

    public List<Building> getBuildingList() {
        return buildingList;
    }

}
