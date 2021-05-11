package com.example.ianmalabanan.facilitiesmonitoringlogsheet;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by ian.malabanan on 09/08/2018.
 */

public class ClsCustomPowerPanelLists extends BaseAdapter {

    Context context;

    ClsDTPowerPanel clsDTPowerPanel;

    private static LayoutInflater inflater = null;



    public ClsCustomPowerPanelLists(Context context, ClsDTPowerPanel clsDTPowerPanel) {
        this.context = context;

        this.clsDTPowerPanel = clsDTPowerPanel;

        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        return (clsDTPowerPanel.getItemPowerPanel() == null) ? 0 : clsDTPowerPanel.getItemPowerPanel().size();
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
        TextView tViewCusPanel;
    }


    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final Holder holder = new Holder();

        View rowView = inflater.inflate(R.layout.activity_powermonitoring_custompanellist, null);

        holder.tViewCusPanel = rowView.findViewById(R.id.tViewCusPowerPanelName);

        holder.tViewCusPanel.setText(clsDTPowerPanel.getItemPowerPanel().get(i).getPanelname());

        rowView.setTag(holder.tViewCusPanel.getText().toString());

        return rowView;
    }


}
