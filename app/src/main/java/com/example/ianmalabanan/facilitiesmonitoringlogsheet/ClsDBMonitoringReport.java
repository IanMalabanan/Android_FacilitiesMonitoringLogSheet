package com.example.ianmalabanan.facilitiesmonitoringlogsheet;

import net.sourceforge.jtds.jdbc.DateTime;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by ian.malabanan on 04/03/2018.
 */

public class ClsDBMonitoringReport {

    ClsConnection clsConnection;

    CallableStatement callableStatement;

    ClsModelPowerPanel clsModelPowerPanel;

    ClsDTPowerPanel clsDTPowerPanel = new ClsDTPowerPanel();

    ClsDTWaterLocation clsDTWaterLocation = new ClsDTWaterLocation();


    ArrayList<ClsModelPowerPanel> datas = new ArrayList<ClsModelPowerPanel>();

    ArrayList<ClsModelWaterLocation> dWater = new ArrayList<ClsModelWaterLocation>();


    public Boolean isSuccess = false;

    public String z, _panelname, _datechecked, _timechecked, _vrl1, _vrl2, _vrl3, _karl1, _karl2, _karl3, _kwrl1,
            _kwrl2, _kwrl3, _kwpower, _kwperhr, _powerremarks, _responsibleperson, _location, _waterreading
            , _kVAl1, _kVAl2, _kVAl3, _frequency;

    public String[] array;


    public boolean SavePowerMonitoringDataNew(
            String _responsibleperson,
            String _panelname,
            String _datechecked, String _timechecked,
            String _vr_l1, String _vr_l2, String _vr_l3,
            String _kl_amp_r_l1, String _kl_amp_r_l2, String _kl_amp_r_l3,
            String _klw_r_l1, String _klw_r_l2, String _klw_r_l3,

            String _kval1,String _kval2,String _kval3,

            String _klw_power, String _klwperhr_mr,

            String _powerfrequency,

            String _remarks, String _encodedby) {

        String insertStoreProc = "{call SavePowerMonitoringDataNew(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";

        try {
            clsConnection = new ClsConnection();

            Connection con = clsConnection.Conn();

            callableStatement = con.prepareCall(insertStoreProc);

            callableStatement.setString(1, _responsibleperson);

            callableStatement.setString(2, _panelname);

            callableStatement.setString(3, _datechecked);

            callableStatement.setString(4, _timechecked);

            callableStatement.setString(5, _vr_l1);

            callableStatement.setString(6, _vr_l2);

            callableStatement.setString(7, _vr_l3);

            callableStatement.setString(8, _kl_amp_r_l1);

            callableStatement.setString(9, _kl_amp_r_l2);

            callableStatement.setString(10, _kl_amp_r_l3);

            callableStatement.setString(11, _klw_r_l1);

            callableStatement.setString(12, _klw_r_l2);

            callableStatement.setString(13, _klw_r_l3);


            callableStatement.setString(14, _kval1);

            callableStatement.setString(15, _kval2);

            callableStatement.setString(16, _kval3);


            callableStatement.setString(17, _klw_power);

            callableStatement.setString(18, _klwperhr_mr);


            callableStatement.setString(19, _powerfrequency);


            callableStatement.setString(20, _remarks);

            callableStatement.setString(21, _encodedby);

            callableStatement.executeUpdate();

            con.setAutoCommit(true);

            isSuccess = true;

            z = "Record Added";

        } catch (Exception ex) {
            z = ex.getMessage().toString();
            isSuccess = false;
        }

        return isSuccess;
    }

    public boolean SavePowerMonitoring(
            String _responsibleperson,
            String _panelname,
            String _datechecked, String _timechecked,
            String _vr_l1, String _vr_l2, String _vr_l3,
            String _kl_amp_r_l1, String _kl_amp_r_l2, String _kl_amp_r_l3,
            String _klw_r_l1, String _klw_r_l2, String _klw_r_l3,
            String _klw_power, String _klwperhr_mr, String _remarks, String _encodedby) {

        String insertStoreProc = "{call SavePowerMonitoringData(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";

        try {
            clsConnection = new ClsConnection();

            Connection con = clsConnection.Conn();

            callableStatement = con.prepareCall(insertStoreProc);

            callableStatement.setString(1, _responsibleperson);

            callableStatement.setString(2, _panelname);

            callableStatement.setString(3, _datechecked);

            callableStatement.setString(4, _timechecked);

            callableStatement.setString(5, _vr_l1);

            callableStatement.setString(6, _vr_l2);

            callableStatement.setString(7, _vr_l3);

            callableStatement.setString(8, _kl_amp_r_l1);

            callableStatement.setString(9, _kl_amp_r_l2);

            callableStatement.setString(10, _kl_amp_r_l3);

            callableStatement.setString(11, _klw_r_l1);

            callableStatement.setString(12, _klw_r_l2);

            callableStatement.setString(13, _klw_r_l3);

            callableStatement.setString(14, _klw_power);

            callableStatement.setString(15, _klwperhr_mr);

            callableStatement.setString(16, _remarks);

            callableStatement.setString(17, _encodedby);

            callableStatement.executeUpdate();

            con.setAutoCommit(true);

            isSuccess = true;

            z = "Record Added";

        } catch (Exception ex) {
            z = ex.getMessage().toString();
            isSuccess = false;
        }

        return isSuccess;
    }

    public boolean SaveWaterMonitoring(
            String _responsibleperson, String _location,
            String _datechecked, String _timechecked,
            String _meter_reading, String _remarks, String _encodedby) {

        String insertStoreProc = "{call SaveWaterMonitoringData(?,?,?,?,?,?,?)}";

        try {
            clsConnection = new ClsConnection();

            Connection con = clsConnection.Conn();

            callableStatement = con.prepareCall(insertStoreProc);

            callableStatement.setString(1, _responsibleperson);

            callableStatement.setString(2, _location);

            callableStatement.setString(3, _datechecked);

            callableStatement.setString(4, _timechecked);

            callableStatement.setString(5, _meter_reading);

            callableStatement.setString(6, _remarks);

            callableStatement.setString(7, _encodedby);

            callableStatement.executeUpdate();

            con.setAutoCommit(true);

            isSuccess = true;

            z = "Record Added";

        } catch (Exception ex) {
            z = ex.getMessage().toString();
            isSuccess = false;
        }

        return isSuccess;
    }


    public boolean GetWaterMeterLocation() {
        try {
            clsConnection = new ClsConnection();

            Connection con = clsConnection.Conn();

//            String query = "Select * from tblWaterMeterLocation";

            String query = "call GetWaterLocation";

            CallableStatement callableStatement = con.prepareCall(query);

            ResultSet rs = callableStatement.executeQuery();

            ArrayList<String> data = new ArrayList<String>();

            while (rs.next()) {
                String lname = rs.getString("locationname");

                data.add(lname);

                isSuccess = true;
            }

            array = data.toArray(new String[0]);

        } catch (Exception e) {
            e.printStackTrace();
            isSuccess = false;
        }

        return isSuccess;
    }

    public boolean GetWaterMeterLocation2() {
        try {
            clsConnection = new ClsConnection();

            Connection con = clsConnection.Conn();

            String query = "call GetWaterLocation_AndroidList";

            CallableStatement callableStatement = con.prepareCall(query);

            ResultSet rs = callableStatement.executeQuery();

            while (rs.next()) {

                ClsModelWaterLocation itemLocation = new ClsModelWaterLocation();

                itemLocation.setLocationname(rs.getString("locationname"));

                dWater.add(itemLocation);

                clsDTWaterLocation.setItemWaterLocation(dWater);

                isSuccess = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
            isSuccess = false;
        }

        return isSuccess;
    }

    public boolean GetWaterMonitoringDataByLocation_Android(String _location) {
        try {
            clsConnection = new ClsConnection();

            Connection con = clsConnection.Conn();

            String query = "{call GetWaterMonitoringDataByLocation_Android(?)}";

            CallableStatement callableStatement = con.prepareCall(query);

            callableStatement.setString(1, _location);

            ResultSet rs = callableStatement.executeQuery();


            while (rs.next()) {

                ClsModelWaterLocation itemLocation = new ClsModelWaterLocation();

                itemLocation.setDatechecked(rs.getString("newdate"));

                dWater.add(itemLocation);

                clsDTWaterLocation.setItemWaterLocation(dWater);

                isSuccess = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
            isSuccess = false;
        }

        return isSuccess;
    }

    public boolean GetPowerPanelName() {
        try {
            clsConnection = new ClsConnection();

            Connection con = clsConnection.Conn();

            String query = "call GetPowerPanelName";

            CallableStatement callableStatement = con.prepareCall(query);

            ResultSet rs = callableStatement.executeQuery();

            ArrayList<String> data = new ArrayList<String>();

            while (rs.next()) {
                String panelsname = rs.getString("panelname");

                data.add(panelsname);

                isSuccess = true;
            }

            array = data.toArray(new String[0]);

        } catch (Exception e) {
            e.printStackTrace();
            isSuccess = false;
        }

        return isSuccess;
    }

    public boolean GetPowerPanelName2() {
        try {
            clsConnection = new ClsConnection();

            Connection con = clsConnection.Conn();

            String query = "call GetPowerPanelName_AndroidList";

            CallableStatement callableStatement = con.prepareCall(query);

            ResultSet rs = callableStatement.executeQuery();

            while (rs.next()) {

                ClsModelPowerPanel itemPanel = new ClsModelPowerPanel();

                itemPanel.setPanelname(rs.getString("panelname"));

                datas.add(itemPanel);

                clsDTPowerPanel.setItemPowerPanel(datas);

                isSuccess = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
            isSuccess = false;
        }

        return isSuccess;
    }

    public boolean GetPowerMonitoringDataByPanelName_Android(String _panelname) {
        try {
            clsConnection = new ClsConnection();

            Connection con = clsConnection.Conn();

            String query = "{call GetPowerMonitoringDataByPanelName_Android(?)}";

            CallableStatement callableStatement = con.prepareCall(query);

            callableStatement.setString(1, _panelname);

            ResultSet rs = callableStatement.executeQuery();

            while (rs.next()) {

                ClsModelPowerPanel itemPanel = new ClsModelPowerPanel();

                itemPanel.setDatechecked(rs.getString("newdate"));

                datas.add(itemPanel);

                clsDTPowerPanel.setItemPowerPanel(datas);

                isSuccess = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
            isSuccess = false;
        }

        return isSuccess;
    }

    public boolean GetPowerMonitoringDetails(String panelname, String datechecked) {
        try {
            clsConnection = new ClsConnection();

            Connection con = clsConnection.Conn();

            if (con == null) {
                z = "Error in Network Connection";
            } else {
                String SP = "{call GetPowerMonitoringDataByPanelNameAndDate_Android(?,?)}";

                callableStatement = con.prepareCall(SP);

                callableStatement.setString(1, panelname);

                callableStatement.setString(2, datechecked);

                ResultSet rs = callableStatement.executeQuery();

                if (rs.next()) {
                    z = "";

                    _panelname = rs.getString("panelname");

                    _datechecked = rs.getString("newdate");

                    _timechecked = rs.getString("newtime");

                    _vrl1 = rs.getString("vr_l1");

                    _vrl2 = rs.getString("vr_l2");

                    _vrl3 = rs.getString("vr_l3");

                    _karl1 = rs.getString("klAmp_r_l1");

                    _karl2 = rs.getString("klAmp_r_l2");

                    _karl3 = rs.getString("klAmp_r_l3");

                    _kwrl1 = rs.getString("klW_r_l1");

                    _kwrl2 = rs.getString("klW_r_l2");

                    _kwrl3 = rs.getString("klW_r_l3");


                    _kVAl1 = rs.getString("kva_r_l1");

                    _kVAl2 = rs.getString("kva_r_l2");

                    _kVAl3 = rs.getString("kva_r_l3");



                    _kwpower = rs.getString("klW_power");

                    _kwperhr = rs.getString("klWperhr_mr");


                    _frequency = rs.getString("frequency");


                    _powerremarks = rs.getString("remarks");

                    _responsibleperson = rs.getString("responsible_person");

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

    public boolean GetWaterMonitoringDetails(String locationname, String datechecked) {
        try {
            clsConnection = new ClsConnection();

            Connection con = clsConnection.Conn();

            if (con == null) {
                z = "Error in Network Connection";
            } else {
                String SP = "{call GetWaterMonitoringDataByLocationAndDate_Android(?,?)}";

                callableStatement = con.prepareCall(SP);

                callableStatement.setString(1, locationname);

                callableStatement.setString(2, datechecked);

                ResultSet rs = callableStatement.executeQuery();

                if (rs.next()) {

                    z = "";

                    _location = rs.getString("location");

                    _datechecked = rs.getString("newdate");

                    _timechecked = rs.getString("newtime");

                    _waterreading = rs.getString("meter_reading");

                    _responsibleperson = rs.getString("responsible_person");

                    _powerremarks =  rs.getString("remarks");


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



}
