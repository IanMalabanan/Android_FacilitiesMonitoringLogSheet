package com.example.ianmalabanan.facilitiesmonitoringlogsheet;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by ian.malabanan on 09/08/2018.
 */

public class ClsCustomWateLocationLists extends BaseAdapter {

    Context context;

    ClsDTWaterLocation clsDTWaterLocation;

    private static LayoutInflater inflater = null;



    public ClsCustomWateLocationLists(Context context, ClsDTWaterLocation clsDTWaterLocation) {
        this.context = context;

        this.clsDTWaterLocation = clsDTWaterLocation;

        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        return (clsDTWaterLocation.getItemWaterLocation() == null) ? 0 : clsDTWaterLocation.getItemWaterLocation().size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }


    public class Holder {
        TextView tViewCusWaterLocation;
    }


    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final ClsCustomWateLocationLists.Holder holder = new ClsCustomWateLocationLists.Holder();

        View rowView = inflater.inflate(R.layout.activity_water_monitoring_customlocation, null);

        holder.tViewCusWaterLocation = rowView.findViewById(R.id.tViewCusWaterLocation);

        holder.tViewCusWaterLocation.setText(clsDTWaterLocation.getItemWaterLocation().get(i).getLocationname());

        rowView.setTag(holder.tViewCusWaterLocation.getText().toString());

        return rowView;
    }
}
