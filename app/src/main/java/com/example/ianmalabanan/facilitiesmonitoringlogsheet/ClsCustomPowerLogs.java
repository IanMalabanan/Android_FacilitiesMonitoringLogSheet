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

public class ClsCustomPowerLogs extends BaseAdapter {

    Context context;

    ClsDTPowerPanel clsDTPowerPanel;

    private static LayoutInflater inflater = null;



    public ClsCustomPowerLogs(Context context, ClsDTPowerPanel clsDTPowerPanel) {
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
        TextView tViewCusDateChecked;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final ClsCustomPowerLogs.Holder holder = new ClsCustomPowerLogs.Holder();

        View rowView = inflater.inflate(R.layout.activity_powermonitoring_customlogs, null);

        holder.tViewCusDateChecked = rowView.findViewById(R.id.tViewCusPowerLogs);

        holder.tViewCusDateChecked.setText(clsDTPowerPanel.getItemPowerPanel().get(i).getDatechecked());

        rowView.setTag(holder.tViewCusDateChecked.getText().toString());

        return rowView;
    }


}
