package com.example.ianmalabanan.facilitiesmonitoringlogsheet;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.text.DecimalFormat;

public class ActivityWaterMonitoring_LogDetails extends AppCompatActivity {

    TextView lblViewLocationName, lblViewDateWater, lblViewTimeWater, lblViewWaterReading, lblViewResponsiblePersonWater, lblViewWaterRemarks;

    ClsDBMonitoringReport clsDBMonitoringReport;

    String _locationname, _datechecked,FullName_FnameFirst;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water_monitoring__log_details);

        this.setTitle("Log Details");

        getSupportActionBar().hide();

        _locationname = getIntent().getExtras().getString("locationname");

        _datechecked = getIntent().getExtras().getString("datechecked");

        FullName_FnameFirst = getIntent().getExtras().getString("fullname_fnamefirst");


        lblViewLocationName = (TextView) findViewById(R.id.lblViewLocationName);

        lblViewDateWater = (TextView) findViewById(R.id.lblViewDateWater);

        lblViewTimeWater = (TextView) findViewById(R.id.lblViewTimeWater);

        lblViewWaterReading = (TextView) findViewById(R.id.lblViewWaterReading);

        lblViewResponsiblePersonWater = (TextView) findViewById(R.id.lblViewResponsiblePersonWater);

        lblViewWaterRemarks = (TextView) findViewById(R.id.lblViewWaterRemarks);

        ViewDetails();
    }


    @Override
    public void onBackPressed() {
        Intent launchNextActivity;

        launchNextActivity = new Intent(ActivityWaterMonitoring_LogDetails.this, ActivityWaterMonitoring_Logs.class);

        launchNextActivity.putExtra("locationname", _locationname);

        launchNextActivity.putExtra("fullname_fnamefirst", FullName_FnameFirst);

        startActivity(launchNextActivity);
    }

    public void ViewDetails() {

        clsDBMonitoringReport = new ClsDBMonitoringReport();

        clsDBMonitoringReport.GetWaterMonitoringDetails(_locationname, _datechecked);

        lblViewLocationName.setText(clsDBMonitoringReport._location);

        lblViewDateWater.setText(clsDBMonitoringReport._datechecked);

        lblViewTimeWater.setText(clsDBMonitoringReport._timechecked);

        DecimalFormat formatter = new DecimalFormat("#,###,###.##");

        float f1 = Float.parseFloat(clsDBMonitoringReport._waterreading);

        lblViewWaterReading.setText(formatter.format(f1));

        lblViewResponsiblePersonWater.setText(clsDBMonitoringReport._responsibleperson);

        lblViewWaterRemarks.setText(clsDBMonitoringReport._powerremarks);
    }
}
