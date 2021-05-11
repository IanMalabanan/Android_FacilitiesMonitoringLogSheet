package com.example.ianmalabanan.facilitiesmonitoringlogsheet;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Date;

public class ActivityPowerMonitoring_LogDetails extends AppCompatActivity {

    TextView lblViewPanelName, lblViewDate, lblViewTime, lblViewVRL1, lblViewVRL2, lblViewVRL3
            , lblViewkARL1, lblViewkARL2, lblViewkARL3, lblViewkWRL1, lblViewkWRL2, lblViewkWRL3
            , lblViewkWPower, lblViewkWPerHrMR, lblViewPowerRemarks, lblViewResponsiblePerson
            , lblViewkvaL1,lblViewkvaL2,lblViewkvaL3,lblViewPowerFrequency;

    String _panelname, _datechecked, FullName_FnameFirst;

    ClsDBMonitoringReport clsDBMonitoringReport;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_power_monitoring__log_details);

        this.setTitle("Log Details");

        getSupportActionBar().hide();

        _panelname = getIntent().getExtras().getString("panelname");

        _datechecked = getIntent().getExtras().getString("datechecked");

        //FullName_FnameFirst = getIntent().getExtras().getString("fullname_fnamefirst");

        lblViewPanelName = (TextView) findViewById(R.id.lblViewPanelName);

        lblViewDate = (TextView) findViewById(R.id.lblViewDate);

        lblViewTime = (TextView) findViewById(R.id.lblViewTime);

        lblViewVRL1 = (TextView) findViewById(R.id.lblViewVRL1);

        lblViewVRL2 = (TextView) findViewById(R.id.lblViewVRL2);

        lblViewVRL3 = (TextView) findViewById(R.id.lblViewVRL3);

        lblViewkARL1 = (TextView) findViewById(R.id.lblViewkARL1);

        lblViewkARL2 = (TextView) findViewById(R.id.lblViewkARL2);

        lblViewkARL3 = (TextView) findViewById(R.id.lblViewkARL3);

        lblViewkWRL1 = (TextView) findViewById(R.id.lblViewkWRL1);

        lblViewkWRL2 = (TextView) findViewById(R.id.lblViewkWRL2);

        lblViewkWRL3 = (TextView) findViewById(R.id.lblViewkWRL3);

        lblViewkWPower = (TextView) findViewById(R.id.lblViewkWPower);

        lblViewkWPerHrMR = (TextView) findViewById(R.id.lblViewkWPerHrMR);

        lblViewResponsiblePerson = (TextView) findViewById(R.id.lblViewResponsiblePerson);

        lblViewPowerRemarks = (TextView) findViewById(R.id.lblViewPowerRemarks);


         lblViewkvaL1 = (TextView) findViewById(R.id.lblViewkvaL1);

         lblViewkvaL2= (TextView) findViewById(R.id.lblViewkvaL2);

         lblViewkvaL3= (TextView) findViewById(R.id.lblViewkvaL3);

         lblViewPowerFrequency= (TextView) findViewById(R.id.lblViewPowerFrequency);

        ViewDetails();

    }

    @Override
    public void onBackPressed() {
        Intent launchNextActivity;

        launchNextActivity = new Intent(ActivityPowerMonitoring_LogDetails.this, ActivityPowerMonitoring_Logs.class);

        launchNextActivity.putExtra("panelname", getIntent().getExtras().getString("panelname"));

        //launchNextActivity.putExtra("fullname_fnamefirst", FullName_FnameFirst);

        startActivity(launchNextActivity);
    }

    public void ViewDetails() {

        clsDBMonitoringReport = new ClsDBMonitoringReport();

        ClsModelPowerPanel clsModelPowerPanel = new ClsModelPowerPanel();

        clsDBMonitoringReport.GetPowerMonitoringDetails(_panelname, _datechecked);

        lblViewPanelName.setText(clsDBMonitoringReport._panelname);

        lblViewDate.setText(clsDBMonitoringReport._datechecked);

        lblViewTime.setText(clsDBMonitoringReport._timechecked);

        lblViewVRL1.setText(clsDBMonitoringReport._vrl1);

        lblViewVRL2.setText(clsDBMonitoringReport._vrl2);

        lblViewVRL3.setText(clsDBMonitoringReport._vrl3);

        lblViewkARL1.setText(clsDBMonitoringReport._karl1);

        lblViewkARL2.setText(clsDBMonitoringReport._karl2);

        lblViewkARL3.setText(clsDBMonitoringReport._karl3);

        lblViewkWRL1.setText(clsDBMonitoringReport._kwrl1);

        lblViewkWRL2.setText(clsDBMonitoringReport._kwrl2);

        lblViewkWRL3.setText(clsDBMonitoringReport._kwrl3);


        lblViewkvaL1.setText(clsDBMonitoringReport._kVAl1);

        lblViewkvaL2.setText(clsDBMonitoringReport._kVAl2);

        lblViewkvaL3.setText(clsDBMonitoringReport._kVAl3);




        lblViewkWPower.setText(clsDBMonitoringReport._kwpower);

        lblViewkWPerHrMR.setText(clsDBMonitoringReport._kwperhr);

        lblViewPowerFrequency.setText(clsDBMonitoringReport._frequency);



        lblViewResponsiblePerson.setText(clsDBMonitoringReport._responsibleperson);

        lblViewPowerRemarks.setText(clsDBMonitoringReport._powerremarks);

    }

}
