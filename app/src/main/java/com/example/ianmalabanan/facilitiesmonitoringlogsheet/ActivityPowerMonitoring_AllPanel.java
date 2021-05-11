package com.example.ianmalabanan.facilitiesmonitoringlogsheet;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class ActivityPowerMonitoring_AllPanel extends AppCompatActivity implements View.OnClickListener {

    ImageButton btnRefreshAllPanelLists;

    ListView listViewPowerPanels;

    ClsDBMonitoringReport clsDBMonitoringReport;

    ClsUtils clsUtils;

    AlertDialog.Builder altdial;

    AlertDialog alert;

    String FullName_FnameFirst = "";

    ClsCustomPowerPanelLists clsCustomPowerPanelLists;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_power_monitoring__all_panel);

        getSupportActionBar().hide();

        listViewPowerPanels = (ListView)findViewById(R.id.listViewPowerPanels);

        btnRefreshAllPanelLists = (ImageButton)findViewById(R.id.btnRefreshAllPanelLists);

        //FullName_FnameFirst = getIntent().getExtras().getString("FullName_FnameFirst");

        GetAllPanels();

        listViewPowerPanels.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                //Toast.makeText(getApplicationContext(), view.getTag().toString() , Toast.LENGTH_LONG).show();
                Intent ii = new Intent(ActivityPowerMonitoring_AllPanel.this, ActivityPowerMonitoring_Logs.class);

                //ii.putExtra("fullname_fnamefirst", FullName_FnameFirst);

                ii.putExtra("panelname", view.getTag().toString());

                startActivity(ii);
            }
        });

        btnRefreshAllPanelLists.setOnClickListener(this);
    }

    public void GetAllPanels() {

        clsDBMonitoringReport = new ClsDBMonitoringReport();

        clsDBMonitoringReport.GetPowerPanelName2();

        clsCustomPowerPanelLists = new ClsCustomPowerPanelLists(this, clsDBMonitoringReport.clsDTPowerPanel);

        listViewPowerPanels.setAdapter(clsCustomPowerPanelLists);

        clsCustomPowerPanelLists.notifyDataSetChanged();
    }

    @Override
    public void onBackPressed() {
        Intent launchNextActivity;

        launchNextActivity = new Intent(ActivityPowerMonitoring_AllPanel.this, ActivityMain.class);

        //launchNextActivity.putExtra("panelname", getIntent().getExtras().getString("panelname"));

        //launchNextActivity.putExtra("fullname_fnamefirst", FullName_FnameFirst);

        startActivity(launchNextActivity);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btnRefreshAllWaterLocation:

                GetAllPanels();

                Toast.makeText(getApplicationContext(), "Refreshed" , Toast.LENGTH_LONG).show();

                break;
        }
    }
}
