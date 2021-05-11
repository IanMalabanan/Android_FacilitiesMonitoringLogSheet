package com.example.ianmalabanan.facilitiesmonitoringlogsheet;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.zip.Inflater;

public class ActivityMain extends AppCompatActivity implements View.OnClickListener {

    ImageButton btnWaterMonitoring, btnPowerMonitoring;

    AlertDialog alert;

    AlertDialog.Builder altdial;

    String FullName_FnameFirst = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();


        //setTitle("asd");
        FullName_FnameFirst = getIntent().getExtras().getString("fullname_fnamefirst");

        btnPowerMonitoring = (ImageButton) findViewById(R.id.btnPowerMonitoring);

        btnWaterMonitoring = (ImageButton)findViewById(R.id.btnWaterMonitoring);


        btnWaterMonitoring.setOnClickListener(this);

        btnPowerMonitoring.setOnClickListener(this);
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();

        altdial = new AlertDialog.Builder(ActivityMain.this);

        altdial.setMessage("Do you want to signout?").setCancelable(false)
                .setPositiveButton("Signout", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent launchNextActivity;
                        launchNextActivity = new Intent(ActivityMain.this, ActivityLogin.class);
                        launchNextActivity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        launchNextActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        launchNextActivity.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(launchNextActivity);
                    }
                })

                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });

        alert = altdial.create();

        alert.show();
    }


    @Override
    public void onClick(View view) {
       Intent intent;

        switch (view.getId()) {

            case R.id.btnPowerMonitoring:

                Intent launchNextActivity;

                launchNextActivity = new Intent(ActivityMain.this, ActivityPowerMonitoring_AllPanel.class);

                launchNextActivity.putExtra("FullName_FnameFirst", FullName_FnameFirst);

                startActivity(launchNextActivity);

                break;

            case R.id.btnWaterMonitoring:

                Intent launchWaterActivity;

                launchWaterActivity = new Intent(ActivityMain.this, ActivityWaterMonitoring_AllLocation.class);

                launchWaterActivity.putExtra("FullName_FnameFirst", FullName_FnameFirst);

                startActivity(launchWaterActivity);

                break;
        }

    }
}
