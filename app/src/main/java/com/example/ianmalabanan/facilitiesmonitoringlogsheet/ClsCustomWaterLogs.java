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

public class ClsCustomWaterLogs extends BaseAdapter {
    Context context;

    ClsDTWaterLocation clsDTWaterLocation;

    private static LayoutInflater inflater = null;



    public ClsCustomWaterLogs(Context context, ClsDTWaterLocation clsDTWaterLocation) {
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
        TextView tViewCusWaterLogs;
    }


    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        final ClsCustomWaterLogs.Holder holder = new ClsCustomWaterLogs.Holder();

        View rowView = inflater.inflate(R.layout.activity_water_monitoring_customlogs, null);

        holder.tViewCusWaterLogs = rowView.findViewById(R.id.tViewCusWaterLogs);

        holder.tViewCusWaterLogs.setText(clsDTWaterLocation.getItemWaterLocation().get(i).getDatechecked());

        rowView.setTag(holder.tViewCusWaterLogs.getText().toString());

        return rowView;
    }
}
