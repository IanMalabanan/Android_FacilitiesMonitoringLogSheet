package com.example.ianmalabanan.facilitiesmonitoringlogsheet;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ActivityWaterMonitoring_Logs extends AppCompatActivity implements View.OnClickListener {

    ImageButton btnAddWaterLog, btnGenerateWaterLogs;

    ListView listViewWaterLogs;

    ClsCustomWaterLogs clsCustomWaterLogs;

    TextView tbViewLocation;

    String _locationname, FullName_FnameFirst;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water_monitoring__logs);

        getSupportActionBar().hide();

        _locationname = getIntent().getExtras().getString("locationname");

        FullName_FnameFirst = getIntent().getExtras().getString("fullname_fnamefirst");

        btnAddWaterLog = (ImageButton) findViewById(R.id.btnAddWaterLog);

        btnGenerateWaterLogs = (ImageButton) findViewById(R.id.btnGenerateWaterLogs);

        listViewWaterLogs = (ListView) findViewById(R.id.listViewWaterLogs);

        tbViewLocation = (TextView) findViewById(R.id.tbViewLocation);

        tbViewLocation.setText(_locationname);

        GetAllWaterLogs();

        btnAddWaterLog.setOnClickListener(this);

        btnGenerateWaterLogs.setOnClickListener(this);


        listViewWaterLogs.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {

                //Toast.makeText(getApplicationContext(), view.getTag().toString() , Toast.LENGTH_LONG).show();

                Intent launchNextActivity = new Intent(ActivityWaterMonitoring_Logs.this, ActivityWaterMonitoring_LogDetails.class);

                launchNextActivity.putExtra("locationname", _locationname);

                launchNextActivity.putExtra("datechecked", view.getTag().toString());

                startActivity(launchNextActivity);
            }
        });
    }


    public void GetAllWaterLogs() {

        ClsDBMonitoringReport clsDBMonitoringReport = new ClsDBMonitoringReport();

        clsDBMonitoringReport.GetWaterMonitoringDataByLocation_Android(_locationname);

        clsCustomWaterLogs = new ClsCustomWaterLogs(this, clsDBMonitoringReport.clsDTWaterLocation);

        listViewWaterLogs.setAdapter(clsCustomWaterLogs);

        clsCustomWaterLogs.notifyDataSetChanged();
    }


    @Override
    public void onBackPressed() {
        Intent launchNextActivity;

        launchNextActivity = new Intent(ActivityWaterMonitoring_Logs.this, ActivityWaterMonitoring_AllLocation.class);

        launchNextActivity.putExtra("fullname_fnamefirst", FullName_FnameFirst);

        startActivity(launchNextActivity);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btnAddWaterLog:
                Intent launchNextActivity;

                launchNextActivity = new Intent(ActivityWaterMonitoring_Logs.this, ActivityWaterMonitoring.class);

                launchNextActivity.putExtra("locationname", _locationname);

                launchNextActivity.putExtra("fullname_fnamefirst", FullName_FnameFirst);

                startActivity(launchNextActivity);
                //Toast.makeText(ActivityWaterMonitoring_Logs.this, FullName_FnameFirst, Toast.LENGTH_LONG).show();
                break;

            case R.id.btnGenerateWaterLogs:

                GetAllWaterLogs();

                Toast.makeText(getApplicationContext(), "Refreshed" , Toast.LENGTH_LONG).show();

                break;
        }
    }
}
