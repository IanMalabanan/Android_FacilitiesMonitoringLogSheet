package com.example.ianmalabanan.facilitiesmonitoringlogsheet;

import android.graphics.Bitmap;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;


public class ClsDBUser {

    ClsConnection clsConnection;

    CallableStatement callableStatement;

    public Boolean isSuccess = false;

    public String z = "";

    public String[] array;

    public String empid = "", fname = "", FullName_FnameFirst = "", deptcode = "",sectioncode = "";

    //public int userid;


    public boolean EncoderLogin(String username, String password) {
        try {
            clsConnection = new ClsConnection();

            Connection con = clsConnection.Conn();

            if (con == null) {
                z = "Error in Network Connection";
            } else {
                String SP = "{call encoder_login(?,?)}";

                callableStatement = con.prepareCall(SP);

                callableStatement.setString(1, username);

                callableStatement.setString(2, password);

                ResultSet rs = callableStatement.executeQuery();

                if (rs.next()) {

                    z = "";

                    //empid = rs.getString("empid");

                    //FullName_FnameFirst = rs.getString("FullName_LnameFirst");

                    isSuccess = true;

                } else {
                    z = "Access Denied";

                    isSuccess = false;
                }
            }
        } catch (Exception ex) {
            isSuccess = false;
            z = ex.toString();
        }

        return isSuccess;
    }

    public boolean GetUsernames() {
        try {
            clsConnection = new ClsConnection();

            Connection con = clsConnection.Conn();

            String query = "Select uname from tblUsers";

            PreparedStatement preparedStatement = con.prepareStatement(query);

            ResultSet rs = preparedStatement.executeQuery();

            ArrayList<String> data = new ArrayList<String>();

            while (rs.next()) {
                String id = rs.getString("uname");

                data.add(id);

                isSuccess = true;
            }

            array = data.toArray(new String[0]);

        } catch (Exception e) {
            e.printStackTrace();
            isSuccess = false;
        }

        return isSuccess;
    }

    public boolean GetFacilitiesCrew() {
        try {
            clsConnection = new ClsConnection();

            Connection con = clsConnection.Conn();

            String SP = "{call GetAllFacilitiesMen}";

            CallableStatement callableStatement = con.prepareCall(SP);

            ResultSet rs = callableStatement.executeQuery();

            ArrayList<String> data = new ArrayList<String>();

            while (rs.next()) {
                String id = rs.getString("FullName_LnameFirst");

                data.add(id);

                isSuccess = true;
            }

            array = data.toArray(new String[0]);

        } catch (Exception e) {
            e.printStackTrace();
            isSuccess = false;
        }

        return isSuccess;
    }




}
