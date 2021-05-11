package com.example.ianmalabanan.facilitiesmonitoringlogsheet;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
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

import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ActivityPowerMonitoring extends AppCompatActivity implements View.OnClickListener {

    EditText txtPanelName, tbNewPanelName, txtPWMDate, txtPWMTime, txtVRL1
            , txtVRL2, txtVRL3, txtKLAMPL1, txtKLAMPL2, txtKLAMPL3, txtKWL1
            , txtKWL2, txtKWL3, txtKWPower, txtKWPerHr, txtPWRemarks
            , txtKVAL1, txtKVAL2, txtKVAL3, txtPowerFrequency;

    Spinner spinnerPanelName, spinnerPowerResPerson;

    Button btnSavePW;

    ImageButton btnGetPWMTime, btnGetPWMDate;

    DatePickerDialog.OnDateSetListener mDateSetListener;

    Calendar cal = Calendar.getInstance();

    ClsDBMonitoringReport clsDBMonitoringReport;

    ClsDBUser clsDBUser;

    ClsUtils clsUtils;

    AlertDialog.Builder altdial;

    AlertDialog alert;

    String FullName_FnameFirst = "";

    TextView lblSavePanelName;


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

        //String chars = capitalize(FullName_FnameFirst);

        String _responsibleperson = spinnerPowerResPerson.getSelectedItem().toString();

        String _panelname = //spinnerPanelName.getSelectedItem().toString();
                lblSavePanelName.getText().toString();

        String _datechecked = txtPWMDate.getText().toString();

        String _timechecked = txtPWMTime.getText().toString();

        String _vr_l1 = txtVRL1.getText().toString();

        String _vr_l2 = txtVRL2.getText().toString();

        String _vr_l3 = txtVRL3.getText().toString();

        String _kl_amp_r_l1 = txtKLAMPL1.getText().toString();

        String _kl_amp_r_l2 = txtKLAMPL2.getText().toString();

        String _kl_amp_r_l3 = txtKLAMPL3.getText().toString();

        String _klw_r_l1 = txtKWL1.getText().toString();

        String _klw_r_l2 = txtKWL2.getText().toString();

        String _klw_r_l3 = txtKWL3.getText().toString();

        String _klw_power = txtKWPower.getText().toString();

        String _klwperhr_mr = txtKWPerHr.getText().toString();

        String _kval1 = txtKVAL1.getText().toString();

        String _kval2 = txtKVAL2.getText().toString();

        String _kval3 = txtKVAL3.getText().toString();

        String _powerfrequency = txtPowerFrequency.getText().toString();

        String _remarks = txtPWRemarks.getText().toString();

        String _encodedby = spinnerPowerResPerson.getSelectedItem().toString();

        clsDBMonitoringReport.SavePowerMonitoringDataNew(_responsibleperson, _panelname,
                _datechecked, _timechecked, _vr_l1, _vr_l2, _vr_l3, _kl_amp_r_l1,
                _kl_amp_r_l2, _kl_amp_r_l3, _klw_r_l1, _klw_r_l2, _klw_r_l3

                ,_kval1,_kval2,_kval3

                , _klw_power, _klwperhr_mr,

                _powerfrequency,_remarks, _encodedby);

        Toast.makeText(ActivityPowerMonitoring.this, clsDBMonitoringReport.z, Toast.LENGTH_LONG).show();

        Intent frm = new Intent(ActivityPowerMonitoring.this, ActivityPowerMonitoring_Logs.class);

        frm.putExtra("panelname", _panelname);

        startActivity(frm);

          /*  if (clsDBMonitoringReport.isSuccess == true) {

                altdial = new AlertDialog.Builder(ActivityPowerMonitoring.this);

                altdial.setMessage("Record Save. Add another?").setCancelable(false)
                        .setPositiveButton("New", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                clearForm((ViewGroup) findViewById(R.id.scrollView));
                                txtPanelName.requestFocus();
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_power_monitoring);

        getSupportActionBar().hide();

        ActionBar actionBar = getSupportActionBar();

        actionBar.setDisplayHomeAsUpEnabled(true);

        actionBar.setDisplayShowHomeEnabled(true);

        actionBar.setDisplayShowHomeEnabled(true);


        spinnerPanelName = (Spinner) findViewById(R.id.spinnerPanelName);

        spinnerPowerResPerson = (Spinner) findViewById(R.id.spinnerPowerResPerson);

        tbNewPanelName = (EditText) findViewById(R.id.tbNewPanelName);

        lblSavePanelName = (TextView) findViewById(R.id.lblSavePanelName);

        txtPWMDate = (EditText) findViewById(R.id.txtPWMDate);

        txtPWMTime = (EditText) findViewById(R.id.txtPWMTime);

        txtVRL1 = (EditText) findViewById(R.id.txtVRL1);

        txtVRL2 = (EditText) findViewById(R.id.txtVRL2);

        txtVRL3 = (EditText) findViewById(R.id.txtVRL3);

        txtKLAMPL1 = (EditText) findViewById(R.id.txtKLAMPL1);

        txtKLAMPL2 = (EditText) findViewById(R.id.txtKLAMPL2);

        txtKLAMPL3 = (EditText) findViewById(R.id.txtKLAMPL3);

        txtKWL1 = (EditText) findViewById(R.id.txtKWL1);

        txtKWL2 = (EditText) findViewById(R.id.txtKWL2);

        txtKWL3 = (EditText) findViewById(R.id.txtKWL3);

        txtKWPower = (EditText) findViewById(R.id.txtKWPower);

        txtKWPerHr = (EditText) findViewById(R.id.txtKWPerHr);

        txtPWRemarks = (EditText) findViewById(R.id.txtPWRemarks);

         txtKVAL1 = (EditText) findViewById(R.id.txtKVAL1);

         txtKVAL2= (EditText) findViewById(R.id.txtKVAL2);

         txtKVAL3= (EditText) findViewById(R.id.txtKVAL3);

         txtPowerFrequency= (EditText) findViewById(R.id.txtPowerFrequency);

        btnGetPWMDate = (ImageButton) findViewById(R.id.btnGetPWMDate);

        btnGetPWMTime = (ImageButton) findViewById(R.id.btnGetPWMTime);

        btnSavePW = (Button) findViewById(R.id.btnSavePW);


        lblSavePanelName.setText(getIntent().getExtras().getString("panelname"));

        btnSavePW.setOnClickListener(this);

        btnGetPWMTime.setOnClickListener(this);

        btnGetPWMDate.setOnClickListener(this);

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

                txtPWMDate.setText(date);
            }
        };
    }

    public void GetLocation() {

        clsDBMonitoringReport = new ClsDBMonitoringReport();

        clsDBMonitoringReport.GetPowerPanelName();

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, clsDBMonitoringReport.array);

        spinnerPanelName.setAdapter(arrayAdapter);
    }

    public void GetFacilitiesCrew() {
        clsDBUser = new ClsDBUser();

        clsDBUser.GetFacilitiesCrew();

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, clsDBUser.array);

        spinnerPowerResPerson.setAdapter(arrayAdapter);
    }

    @Override
    public void onBackPressed() {
        Intent launchNextActivity;

        launchNextActivity = new Intent(ActivityPowerMonitoring.this, ActivityPowerMonitoring_Logs.class);

        launchNextActivity.putExtra("panelname", getIntent().getExtras().getString("panelname"));

        //launchNextActivity.putExtra("fullname_fnamefirst", FullName_FnameFirst);

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

    @Override
    public void onClick(View view) {
        Intent intent;

        switch (view.getId()) {

            case R.id.btnGetPWMDate:
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dpd = new DatePickerDialog(
                        ActivityPowerMonitoring.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener, year, month, day);

                dpd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dpd.show();
                break;
            case R.id.btnGetPWMTime:

                int hours = cal.get(Calendar.HOUR);
                int minss = cal.get(Calendar.MINUTE);

                TimePickerDialog tpd;

                tpd = new TimePickerDialog(ActivityPowerMonitoring.this,
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

                                txtPWMTime.setText(newtime);
                            }
                        }, hours, minss, false);

                tpd.show();
                break;
            case R.id.btnSavePW:
                Register();
                break;
        }
    }
}
