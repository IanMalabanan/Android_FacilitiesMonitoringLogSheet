package com.example.ianmalabanan.facilitiesmonitoringlogsheet;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ActivityPowerMonitoring_Logs extends AppCompatActivity implements View.OnClickListener{

    ClsCustomPowerLogs clsCustomPowerLogs;

    ListView listViewPowerPanelLogs;

    TextView tbViewPanelName;

    String _panelname, _datechecked, FullName_FnameFirst;

    ImageButton btnAddPowerLog, btnGeneratePowerLogs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_power_monitoring__logs);

        getSupportActionBar().hide();

        listViewPowerPanelLogs = (ListView)findViewById(R.id.listViewPowerPanelLogs);

        tbViewPanelName = (TextView)findViewById(R.id.tbViewPanelName);

        btnAddPowerLog = (ImageButton)findViewById(R.id.btnAddPowerLog);

        btnGeneratePowerLogs = (ImageButton)findViewById(R.id.btnGeneratePowerLogs);



        _panelname = getIntent().getExtras().getString("panelname");

        //FullName_FnameFirst = getIntent().getExtras().getString("fullname_fnamefirst");


        tbViewPanelName.setText(_panelname);

        btnAddPowerLog.setOnClickListener(this);

        btnGeneratePowerLogs.setOnClickListener(this);



        GetAllPanelLogs();


        listViewPowerPanelLogs.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                //Toast.makeText(getApplicationContext(), view.getTag().toString() , Toast.LENGTH_LONG).show();

                Intent launchNextActivity = new Intent(ActivityPowerMonitoring_Logs.this, ActivityPowerMonitoring_LogDetails.class);

                launchNextActivity.putExtra("panelname", tbViewPanelName.getText().toString());

                launchNextActivity.putExtra("datechecked", view.getTag().toString());

                //launchNextActivity.putExtra("fullname_fnamefirst", FullName_FnameFirst);

                startActivity(launchNextActivity);
            }
        });

    }

    @Override
    public void onBackPressed() {
        Intent launchNextActivity;

        launchNextActivity = new Intent(ActivityPowerMonitoring_Logs.this, ActivityPowerMonitoring_AllPanel.class);

        //launchNextActivity.putExtra("panelname", getIntent().getExtras().getString("panelname"));

        //launchNextActivity.putExtra("fullname_fnamefirst", FullName_FnameFirst);

        startActivity(launchNextActivity);
    }

    @Override
    public void onClick(View view) {
        Intent intent;

        switch (view.getId()) {

            case R.id.btnAddPowerLog:

                Intent launchNextActivity;

                launchNextActivity = new Intent(ActivityPowerMonitoring_Logs.this, ActivityPowerMonitoring.class);

                //launchNextActivity.putExtra("fullname_fnamefirst", FullName_FnameFirst);

                launchNextActivity.putExtra("panelname", _panelname);

                startActivity(launchNextActivity);

                //Toast.makeText(ActivityPowerMonitoring_Logs.this, FullName_FnameFirst, Toast.LENGTH_LONG).show();

                break;

            case R.id.btnGeneratePowerLogs:

                GetAllPanelLogs();

                Toast.makeText(getApplicationContext(), "Refreshed" , Toast.LENGTH_LONG).show();

                break;
        }

    }

    public void GetAllPanelLogs() {

        ClsDBMonitoringReport clsDBMonitoringReport = new ClsDBMonitoringReport();

        clsDBMonitoringReport.GetPowerMonitoringDataByPanelName_Android(tbViewPanelName.getText().toString());

        clsCustomPowerLogs = new ClsCustomPowerLogs(this, clsDBMonitoringReport.clsDTPowerPanel);

        listViewPowerPanelLogs.setAdapter(clsCustomPowerLogs);

        clsCustomPowerLogs.notifyDataSetChanged();
    }


}
