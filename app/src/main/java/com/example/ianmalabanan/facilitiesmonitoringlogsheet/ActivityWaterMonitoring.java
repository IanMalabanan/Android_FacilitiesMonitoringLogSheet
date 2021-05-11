package com.example.ianmalabanan.facilitiesmonitoringlogsheet;


import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ActivityWaterMonitoring extends AppCompatActivity implements View.OnClickListener {

    ClsDBMonitoringReport clsDBMonitoringReport;

    ClsDBUser clsDBUser;

    ClsUtils clsUtils;

    AlertDialog.Builder altdial;

    AlertDialog alert;

    EditText txtDate, txtTime, txtLocation, txtMeterReading, txtRemarks;

    TextView tbSaveLocation;

    Spinner spinnerLocation, spinnerWaterResPerson;

    Button btnSave;

    ImageButton btnGetTime,btnGetDate ;

    DatePickerDialog.OnDateSetListener mDateSetListener;

    Calendar cal = Calendar.getInstance();

    String FullName_FnameFirst, _locationname;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water_monitoring);

        //this.setTitle("Facilities Monitoring");

        FullName_FnameFirst = getIntent().getExtras().getString("fullname_fnamefirst");

        _locationname = getIntent().getExtras().getString("locationname");

        getSupportActionBar().hide();

        ActionBar actionBar = getSupportActionBar();

        actionBar.setDisplayHomeAsUpEnabled(true);

        actionBar.setDisplayShowHomeEnabled(true);

        actionBar.setDisplayShowHomeEnabled(true);


        txtDate = (EditText) findViewById(R.id.txtDate);

        tbSaveLocation = (TextView) findViewById(R.id.tbSaveLocation);

        spinnerLocation = (Spinner) findViewById(R.id.spinnerLocation);

        spinnerWaterResPerson = (Spinner) findViewById(R.id.spinnerWaterResPerson);

        txtTime = (EditText) findViewById(R.id.txtTime);

        //txtLocation = (EditText) findViewById(R.id.txtLocation);

        txtMeterReading = (EditText) findViewById(R.id.txtMeterReading);

        txtRemarks = (EditText) findViewById(R.id.txtRemarks);


        btnGetDate = (ImageButton) findViewById(R.id.btnGetDate);

        btnGetTime = (ImageButton) findViewById(R.id.btnGetTime);

        btnSave = (Button) findViewById(R.id.btnSave);


        btnGetDate.setOnClickListener(this);

        btnGetTime.setOnClickListener(this);

        btnSave.setOnClickListener(this);


        tbSaveLocation.setText(_locationname);

        //GetLocation();

        GetFacilitiesCrew();


        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                month = month + 1;

                String date = "", newmonth = "", newday = "";

                switch (month) {
                    case 1:
                        newmonth = "January";
                        break;
                    case 2:
                        newmonth = "February";
                        break;
                    case 3:
                        newmonth = "March";
                        break;
                    case 4:
                        newmonth = "April";
                        break;
                    case 5:
                        newmonth = "May";
                        break;
                    case 6:
                        newmonth = "June";
                        break;
                    case 7:
                        newmonth = "July";
                        break;
                    case 8:
                        newmonth = "August";
                        break;
                    case 9:
                        newmonth = "September";
                        break;
                    case 10:
                        newmonth = "October";
                        break;
                    case 11:
                        newmonth = "November";
                        break;
                    case 12:
                        newmonth = "December";
                        break;
                    default:
                        break;
                }

                if (dayOfMonth >= 1 && dayOfMonth <= 9) {
                    newday = "0" + dayOfMonth;
                } else {
                    newday = String.valueOf(dayOfMonth);
                }


                date = newday + "-" + newmonth + "-" + year;

                txtDate.setText(date);

            }
        };

    }

    public void GetLocation() {

        clsDBMonitoringReport = new ClsDBMonitoringReport();

        clsDBMonitoringReport.GetWaterMeterLocation();

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, clsDBMonitoringReport.array);

        spinnerLocation.setAdapter(arrayAdapter);
    }


    public void GetFacilitiesCrew() {
        clsDBUser = new ClsDBUser();

        clsDBUser.GetFacilitiesCrew();

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, clsDBUser.array);

        spinnerWaterResPerson.setAdapter(arrayAdapter);
    }


    @Override
    public void onBackPressed() {
        Intent launchNextActivity;

        launchNextActivity = new Intent(ActivityWaterMonitoring.this, ActivityWaterMonitoring_Logs.class);

        launchNextActivity.putExtra("locationname", _locationname);

        launchNextActivity.putExtra("fullname_fnamefirst", FullName_FnameFirst);

        startActivity(launchNextActivity);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                onBackPressed();

                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private String capitalize(String capString) {
        StringBuffer capBuffer = new StringBuffer();
        Matcher capMatcher = Pattern.compile("([a-z])([a-z]*)", Pattern.CASE_INSENSITIVE).matcher(capString);
        while (capMatcher.find()) {
            capMatcher.appendReplacement(capBuffer, capMatcher.group(1).toUpperCase() + capMatcher.group(2).toLowerCase());
        }

        return capMatcher.appendTail(capBuffer).toString();
    }

    public void Register() {

        clsDBMonitoringReport = new ClsDBMonitoringReport();

        //String chars = capitalize(getIntent().getExtras().getString("fullname_fnamefirst"));

        String _responsibleperson = spinnerWaterResPerson.getSelectedItem().toString();

        String _location = //spinnerLocation.getSelectedItem().toString();
                tbSaveLocation.getText().toString();

        String _date = txtDate.getText().toString();

        String _time = txtTime.getText().toString();

        String _meter_reading = txtMeterReading.getText().toString();

        String _remarks = txtRemarks.getText().toString();

        String _encodedby = spinnerWaterResPerson.getSelectedItem().toString();
        ;//getIntent().getExtras().getString("fullname_fnamefirst");



        /*if (!clsUtils.HasConnection(this)) {
            clsUtils.NoInternetBuilDialog(this).show();

        } else {*/
        clsDBMonitoringReport.SaveWaterMonitoring(_responsibleperson, _location,
                _date, _time, _meter_reading, _remarks, _encodedby);

        Toast.makeText(ActivityWaterMonitoring.this, clsDBMonitoringReport.z, Toast.LENGTH_LONG).show();

        Intent frm = new Intent(ActivityWaterMonitoring.this, ActivityWaterMonitoring_Logs.class);

        frm.putExtra("locationname", getIntent().getExtras().getString("locationname"));

        //frm.putExtra("fullname_fnamefirst", getIntent().getExtras().getString("fullname_fnamefirst"));

        startActivity(frm);


     /*       if (clsDBMonitoringReport.isSuccess == true) {

                altdial = new AlertDialog.Builder(ActivityWaterMonitoring.this);

                altdial.setMessage("Record Save. Add another?").setCancelable(false)
                        .setPositiveButton("New", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                clearForm((ViewGroup) findViewById(R.id.scrollView));
                                txtLocation.requestFocus();
                            }
                        })

                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //dialogInterface.cancel();

                            }
                        });

                alert = altdial.create();
                alert.show();
            } else {

            }*/
        //}
    }

    private void clearForm(ViewGroup group) {
        for (int i = 0, count = group.getChildCount(); i < count; ++i) {
            View view = group.getChildAt(i);
            if (view instanceof EditText) {
                ((EditText) view).setText("");
            }

            if (view instanceof ViewGroup && (((ViewGroup) view).getChildCount() > 0))
                clearForm((ViewGroup) view);
        }
    }

    @Override
    public void onClick(View view) {

        Intent intent;

        switch (view.getId()) {

            case R.id.btnGetDate:
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dpd = new DatePickerDialog(
                        ActivityWaterMonitoring.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener, year, month, day);

                dpd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dpd.show();
                break;
            case R.id.btnGetTime:

                int hours = cal.get(Calendar.HOUR);

                int minss = cal.get(Calendar.MINUTE);

                TimePickerDialog tpd;

                tpd = new TimePickerDialog(ActivityWaterMonitoring.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                                String newtime = "", newhour = "", newminute = "";

                                if (hourOfDay >= 0 && hourOfDay <= 9) {
                                    newhour = "0" + hourOfDay;
                                } else {
                                    newhour = String.valueOf(hourOfDay);
                                }

                                if (minute >= 0 && minute <= 9) {
                                    newminute = "0" + minute;
                                } else {
                                    newminute = String.valueOf(minute);
                                }

                                newtime = newhour + ":" + newminute;

                                txtTime.setText(newtime);
                            }
                        }, hours, minss, false);

                tpd.show();

                break;
            case R.id.btnSave:
                //clearForm((ViewGroup)findViewById(R.id.scrollView));
                Register();
                break;
        }
    }
}
