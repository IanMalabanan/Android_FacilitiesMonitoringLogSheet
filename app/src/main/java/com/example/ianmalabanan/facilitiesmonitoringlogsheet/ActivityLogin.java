package com.example.ianmalabanan.facilitiesmonitoringlogsheet;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ActivityLogin extends AppCompatActivity {

    Button btnSignin;

    AutoCompleteTextView autoCompleteTextUsername;

    EditText txtPassword;

    ClsDBUser clsDBUser;

    ClsUtils clsUtils;


    String fname,FullName_FnameFirst;

    int userid = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        getSupportActionBar().hide();


        clsUtils = new ClsUtils();

        autoCompleteTextUsername = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextUsername);

        txtPassword = (EditText) findViewById(R.id.txtPassword);

        btnSignin = (Button) findViewById(R.id.btnSignin);

        if (!clsUtils.HasConnection(this)) {
            clsUtils.NoInternetBuilDialog(this).show();

        } else {
            GetUsernames();

            btnSignin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    CheckLogin checkLogin = new CheckLogin();
                    checkLogin.execute("");
                }
            });

            autoCompleteTextUsername.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    InputMethodManager in = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    in.hideSoftInputFromWindow(view.getApplicationWindowToken(), 0);
                }
            });

        }
    }

    public class CheckLogin extends AsyncTask<String, String, String> {
        String z = "";

        Boolean isSuccess = false;

        String username = autoCompleteTextUsername.getText().toString();

        String password = txtPassword.getText().toString();

        @Override
        protected void onPreExecute() {
        }

        @Override
        protected void onPostExecute(String s) {
            if (s != "") {
                Toast.makeText(ActivityLogin.this, s, Toast.LENGTH_SHORT).show();
            }

            if (isSuccess) {
                Intent i = new Intent(ActivityLogin.this, ActivityMain.class);

                i.putExtra("fullname_fnamefirst", FullName_FnameFirst);

                //i.putExtra("userid", String.valueOf(userid));

                startActivity(i);
            }
        }

        @Override
        protected String doInBackground(String... strings) {

            if (username.trim().equals(""))
                z = "Username Cannot Be Empty. Username Required";

            else if (password.trim().equals(""))
                z = "Password Cannot Be Empty. Password Required";

            else {
                clsDBUser = new ClsDBUser();

                clsDBUser.EncoderLogin(username, password);

                z = clsDBUser.z;

                FullName_FnameFirst = clsDBUser.FullName_FnameFirst;

                isSuccess = clsDBUser.isSuccess;
            }
            return z;
        }
    }

    public void GetUsernames() {

        clsDBUser = new ClsDBUser();

        clsDBUser.GetUsernames();

        ArrayAdapter userNameAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, clsDBUser.array);

        autoCompleteTextUsername.setAdapter(userNameAdapter);

        autoCompleteTextUsername.setThreshold(1);
    }


    boolean doubleBackToExitPressedOnce = false;

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Click again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 1000);
    }
}
