package com.example.ianmalabanan.facilitiesmonitoringlogsheet;

import android.app.AlertDialog;
import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

public class ActivityWaterMonitoring_AllLocation extends AppCompatActivity implements View.OnClickListener {


    ImageButton btnRefreshAllWaterLocation;

    ListView listViewWaterLocations;

    ClsDBMonitoringReport clsDBMonitoringReport;

    ClsUtils clsUtils;

    AlertDialog.Builder altdial;

    AlertDialog alert;

    String FullName_FnameFirst = "";

    ClsCustomWateLocationLists clsCustomWateLocationLists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water_monitoring__all_location);

        FullName_FnameFirst = getIntent().getExtras().getString("FullName_FnameFirst");

        getSupportActionBar().hide();

        listViewWaterLocations = (ListView) findViewById(R.id.listViewWaterLocations);

        btnRefreshAllWaterLocation = (ImageButton) findViewById(R.id.btnRefreshAllWaterLocation);

        GetAllLocations();

        btnRefreshAllWaterLocation.setOnClickListener(this);

        listViewWaterLocations.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                //Toast.makeText(getApplicationContext(), view.getTag().toString() , Toast.LENGTH_LONG).show();
                Intent ii = new Intent(ActivityWaterMonitoring_AllLocation.this, ActivityWaterMonitoring_Logs.class);

                ii.putExtra("fullname_fnamefirst", FullName_FnameFirst);

                ii.putExtra("locationname", view.getTag().toString());

                startActivity(ii);
            }
        });
    }


    public void GetAllLocations() {

        clsDBMonitoringReport = new ClsDBMonitoringReport();

        clsDBMonitoringReport.GetWaterMeterLocation2();

        clsCustomWateLocationLists = new ClsCustomWateLocationLists(this, clsDBMonitoringReport.clsDTWaterLocation);

        listViewWaterLocations.setAdapter(clsCustomWateLocationLists);

        clsCustomWateLocationLists.notifyDataSetChanged();
    }

    @Override
    public void onBackPressed() {
        Intent launchNextActivity;

        launchNextActivity = new Intent(ActivityWaterMonitoring_AllLocation.this, ActivityMain.class);

        launchNextActivity.putExtra("fullname_fnamefirst", FullName_FnameFirst);

        startActivity(launchNextActivity);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.btnRefreshAllWaterLocation:

                GetAllLocations();

                Toast.makeText(getApplicationContext(), "Refreshed", Toast.LENGTH_LONG).show();

                break;
        }
    }
}
