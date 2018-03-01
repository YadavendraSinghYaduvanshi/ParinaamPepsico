package com.cpm.upload;

import java.io.File;
import java.io.StringReader;
import java.util.ArrayList;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xml.sax.InputSource;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Toast;

import com.cpm.Constants.CommonString;
import com.cpm.dailyEntry.DailyentryMenuActivity;
import com.cpm.dailyEntry.ImagePepsicoActivity;
import com.cpm.dailyEntry.SodMasterActivity;
import com.cpm.database.PepsicoDatabase;
import com.cpm.delegates.CoverageBean;
import com.cpm.delegates.GeotaggingBeans;
import com.cpm.delegates.PosmBean;
import com.cpm.delegates.SodBean;
import com.cpm.delegates.StoreBean;
import com.cpm.delegates.VericalBrandBean;
import com.cpm.message.AlertMessage;
import com.cpm.parinaampepsico.MainMenuActivity;
import com.cpm.parinaampepsicoo.R;
import com.cpm.xmlHandler.FailureXMLHandler;

public class UploadOptionActivity extends Activity {

    private Intent intent;

    private String date;
    private SharedPreferences preferences;
    private static PepsicoDatabase database;
    ArrayList<CoverageBean> cdata = new ArrayList<CoverageBean>();
    ArrayList<GeotaggingBeans> gdata = new ArrayList<GeotaggingBeans>();
    StoreBean storestatus = new StoreBean();
    private ArrayList<CoverageBean> coverageBeanlist = new ArrayList<CoverageBean>();
    private ArrayList<PosmBean> assetlist = new ArrayList<PosmBean>();
    String visit_date, username, result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.upload_option);
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        visit_date = preferences.getString(CommonString.KEY_DATE, null);
        username = preferences.getString(CommonString.KEY_USERNAME, null);
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        date = preferences.getString(CommonString.KEY_DATE, null);

        database = new PepsicoDatabase(this);
        database.open();

    }

    public void onButtonClick(View v) {
        switch (v.getId()) {
            case R.id.upload_data:


                cdata = database.getCoverageData(date, null);
                gdata = database.getGeotaggingData("Y");

                if (cdata.size() == 0 && gdata.size() == 0) {

                    Toast.makeText(getBaseContext(), AlertMessage.MESSAGE_NO_DATA,
                            Toast.LENGTH_LONG).show();

                } else {

                    if ((validate_data()) || gdata.size() > 0) {

                        Intent i = new Intent(UploadOptionActivity.this,
                                UploadDataActivity.class);
                        startActivity(i);
                        UploadOptionActivity.this.finish();
                    } else {
                        Toast.makeText(getBaseContext(),
                                AlertMessage.MESSAGE_NO_DATA, Toast.LENGTH_LONG)
                                .show();
                    }

                }

                break;
            case R.id.upload_image:

                cdata = database.getCoverageData(date, null);
                gdata = database.getGeotaggingData("D");

                if (cdata.size() == 0 && gdata.size() == 0) {

                    Toast.makeText(getBaseContext(), AlertMessage.MESSAGE_NO_IMAGE,
                            Toast.LENGTH_LONG).show();

                } else {

                    if (validate() || ValidateGeoTagImage() == 1) {

                        intent = new Intent(this, UploadImageActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(getBaseContext(),
                                AlertMessage.MESSAGE_DATA_FIRST, Toast.LENGTH_LONG)
                                .show();
                    }
                }
                break;

        }
    }

    public boolean validate_data() {
        boolean result = false;

        database.open();
        cdata = database.getCoverageData(date, null);

        for (int i = 0; i < cdata.size(); i++) {

            storestatus = database.getStoreStatus(cdata.get(i).getStoreId());

            if ((storestatus.getStatus().equalsIgnoreCase(CommonString.KEY_N)
                    || storestatus.getStatus().equalsIgnoreCase(
                    CommonString.KEY_P) || storestatus.getStatus()
                    .equalsIgnoreCase(CommonString.STORE_STATUS_LEAVE))) {
                result = true;
                break;

            }

        }

        return result;
    }

    public boolean validate() {
        boolean result = false;

        database.open();
        cdata = database.getCoverageData(date, null);

        for (int i = 0; i < cdata.size(); i++) {

            if (cdata.get(i).getStatus().equalsIgnoreCase(CommonString.KEY_D)) {
                result = true;
                break;

            }
        }

        return result;
    }

    @Override
    protected void onStop() {
        // TODO Auto-generated method stub
        super.onStop();
        database.close();
    }

    @Override
    public void onBackPressed() {
        // TODO Auto-generated method stub

        Intent i = new Intent(this, MainMenuActivity.class);
        startActivity(i);
        UploadOptionActivity.this.finish();
    }

    private int ValidateGeoTagImage() {

        int result = 0;

        gdata = database.getGeotaggingData(CommonString.KEY_D);

        if (gdata.size() > 0) {

            result = 1;
        }
        return result;
    }
}
