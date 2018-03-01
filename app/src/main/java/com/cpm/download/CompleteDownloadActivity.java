package com.cpm.download;

import java.io.IOException;
import java.io.StringReader;
import java.net.MalformedURLException;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.cpm.Constants.CommonString;
import com.cpm.database.PepsicoDatabase;
import com.cpm.delegates.TableBeen;
import com.cpm.geotagging.GeoTagCityGetterSetter;
import com.cpm.geotagging.GeoTagCityXMLHandler;
import com.cpm.geotagging.GeoTagGetterSetter;
import com.cpm.geotagging.GeoTagXMLHandler;
import com.cpm.message.AlertMessage;
import com.cpm.parinaampepsicoo.R;
import com.cpm.xmlGetterSetter.AssetsGetterSetter;
import com.cpm.xmlGetterSetter.AssetsTargetGetterSetter;
import com.cpm.xmlGetterSetter.BrandMasterGetterSetter;
import com.cpm.xmlGetterSetter.CategoryMasterGetterSetter;
import com.cpm.xmlGetterSetter.CategoryVerticalMappingGetterSetter;
import com.cpm.xmlGetterSetter.CompanyMasterGetterSetter;
import com.cpm.xmlGetterSetter.CompetitorGetterSetter;
import com.cpm.xmlGetterSetter.FailureGetterSetter;
import com.cpm.xmlGetterSetter.JCPGetterSetter;
import com.cpm.xmlGetterSetter.KpiReportGetterSetter;
import com.cpm.xmlGetterSetter.NonSkuGetterSetter;
import com.cpm.xmlGetterSetter.NonWorkingGetterSetter;
import com.cpm.xmlGetterSetter.PosmMasterGetterSetter;
import com.cpm.xmlGetterSetter.PromotionGetterSetter;
import com.cpm.xmlGetterSetter.SkuMasterDataGetterSetter;
import com.cpm.xmlGetterSetter.SkuTargetGetterSetter;
import com.cpm.xmlGetterSetter.SkufocusGetterSetter;
import com.cpm.xmlGetterSetter.TargetBrandWiseGetterSetter;
import com.cpm.xmlGetterSetter.TargetKPIGetterSetter;
import com.cpm.xmlGetterSetter.VerticalMasterGetterSetter;
import com.cpm.xmlHandler.XMLHandlers;

public class CompleteDownloadActivity extends Activity {

    JCPGetterSetter jcpdata = null;
    VerticalMasterGetterSetter verticalmasterdata = null;
    BrandMasterGetterSetter brandmasterdata = null;

    CategoryMasterGetterSetter categoryMaster = null;
    CompetitorGetterSetter competitor = null;

    CategoryVerticalMappingGetterSetter categoryVerticalMapping = null;
    private FailureGetterSetter failureGetterSetter = null;
    private GeoTagCityGetterSetter geoCityGetterSetter = null;
    private GeoTagGetterSetter geoTagGetterSetter = null;
    private PosmMasterGetterSetter posmGetterSetter = null;
    private SkuTargetGetterSetter targetdata = null;
    private AssetsGetterSetter assetdata = null;
    private AssetsTargetGetterSetter assettarget = null;
    private NonWorkingGetterSetter nonworking, subnon, keyacunt = null;

    private NonSkuGetterSetter nonskudata = null;
    private PromotionGetterSetter promotion = null;
    private TargetKPIGetterSetter kpitarget = null;

    private KpiReportGetterSetter kpiREPORT_DATA = null;

    private SkuMasterDataGetterSetter sku_data = null;
    private CompanyMasterGetterSetter companydata = null;
    private SkufocusGetterSetter skufocus = null;
    private TargetBrandWiseGetterSetter brandwisetargetdata;
    private Dialog dialog;
    private ProgressBar pb;
    private TextView percentage, message;
    private String username;
    private Data data;
    private PepsicoDatabase db;

    private SharedPreferences preferences = null;
    static int counter = 1;
    int eventType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainpage);

        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        username = preferences.getString(CommonString.KEY_USERNAME, "");

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        db = new PepsicoDatabase(this);
        db.open();


        new BackgroundTask(this).execute();
    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();

        // db.open();

    }

    @Override
    protected void onStop() {
        // TODO Auto-generated method stub
        super.onStop();
        // db.close();
    }

    private class BackgroundTask extends AsyncTask<Void, Data, String> {
        private Context context;

        BackgroundTask(Context context) {
            this.context = context;
        }

        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();

            dialog = new Dialog(context);
            dialog.setContentView(R.layout.custom);
            dialog.setTitle("Download Files");
            dialog.setCancelable(false);
            dialog.show();
            pb = (ProgressBar) dialog.findViewById(R.id.progressBar1);
            percentage = (TextView) dialog.findViewById(R.id.percentage);
            message = (TextView) dialog.findViewById(R.id.message);

        }

        @Override
        protected String doInBackground(Void... params) {
            // TODO Auto-generated method stub

            try {

                data = new Data();

                // JCP
                XmlPullParserFactory factory = XmlPullParserFactory
                        .newInstance();
                factory.setNamespaceAware(true);
                XmlPullParser xpp = factory.newPullParser();

                SoapObject request = new SoapObject(CommonString.NAMESPACE,
                        CommonString.METHOD_NAME_JCP);
                request.addProperty("User_ID", username);

                SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                        SoapEnvelope.VER11);
                envelope.dotNet = true;
                envelope.setOutputSoapObject(request);

                HttpTransportSE androidHttpTransport = new HttpTransportSE(
                        CommonString.URL);

                androidHttpTransport.call(CommonString.SOAP_ACTION_JCP,
                        envelope);
                Object result = (Object) envelope.getResponse();

                if (result.toString()
                        .equalsIgnoreCase(CommonString.KEY_NO_DATA)) {
                    return CommonString.METHOD_NAME_JCP;
                }

                // for failure
                xpp.setInput(new StringReader(result.toString()));
                xpp.next();
                eventType = xpp.getEventType();
                failureGetterSetter = XMLHandlers.failureXMLHandler(xpp,
                        eventType);

                if (failureGetterSetter.getStatus().equalsIgnoreCase(
                        CommonString.KEY_FAILURE)) {
                    return CommonString.METHOD_NAME_JCP + ","
                            + failureGetterSetter.getErrorMsg();
                }

                xpp.setInput(new StringReader(result.toString()));
                xpp.next();
                eventType = xpp.getEventType();
                jcpdata = XMLHandlers.JCPXMLHandler(xpp, eventType);

                data.value = 10;
                data.name = "JCP Data Downloading";
                publishProgress(data);

                // NON WORKING RESAON
                request = new SoapObject(CommonString.NAMESPACE,
                        CommonString.METHOD_NON_WORKING_MASTER);

                envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                envelope.dotNet = true;
                envelope.setOutputSoapObject(request);

                androidHttpTransport = new HttpTransportSE(CommonString.URL);

                androidHttpTransport.call(CommonString.SOAP_ACTION_NONWORKING,
                        envelope);
                result = (Object) envelope.getResponse();

                if (result.toString().equalsIgnoreCase(CommonString.KEY_FALSE)) {
                    return CommonString.METHOD_NON_WORKING_MASTER;
                }

                if (result.toString()
                        .equalsIgnoreCase(CommonString.KEY_NO_DATA)) {
                    return "No Data in "
                            + CommonString.METHOD_NON_WORKING_MASTER;
                }

                // for failure
                xpp.setInput(new StringReader(result.toString()));
                xpp.next();
                eventType = xpp.getEventType();
                failureGetterSetter = XMLHandlers.failureXMLHandler(xpp,
                        eventType);

                if (failureGetterSetter.getStatus().equalsIgnoreCase(
                        CommonString.KEY_FAILURE)) {
                    return CommonString.METHOD_NON_WORKING_MASTER + ","
                            + failureGetterSetter.getErrorMsg();
                }

                xpp.setInput(new StringReader(result.toString()));
                xpp.next();
                eventType = xpp.getEventType();
                nonworking = XMLHandlers.NonWorkingXMLHandler(xpp, eventType);

                data.value = 12;
                data.name = "Non Working Reason Downloading";
                publishProgress(data);

                // subreason

                request = new SoapObject(CommonString.NAMESPACE,
                        CommonString.METHOD_SUBNON_WORKING_MASTER);

                envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                envelope.dotNet = true;
                envelope.setOutputSoapObject(request);

                androidHttpTransport = new HttpTransportSE(CommonString.URL);

                androidHttpTransport.call(CommonString.SOAP_ACTION_SUBNONWORKING,
                        envelope);
                result = (Object) envelope.getResponse();

                if (result.toString().equalsIgnoreCase(CommonString.KEY_FALSE)) {
                    return CommonString.METHOD_SUBNON_WORKING_MASTER;
                }

                if (result.toString()
                        .equalsIgnoreCase(CommonString.KEY_NO_DATA)) {
                    return "No Data in "
                            + CommonString.METHOD_SUBNON_WORKING_MASTER;
                }

                // for failure
                xpp.setInput(new StringReader(result.toString()));
                xpp.next();
                eventType = xpp.getEventType();
                failureGetterSetter = XMLHandlers.failureXMLHandler(xpp,
                        eventType);

                if (failureGetterSetter.getStatus().equalsIgnoreCase(
                        CommonString.KEY_FAILURE)) {
                    return CommonString.METHOD_SUBNON_WORKING_MASTER + ","
                            + failureGetterSetter.getErrorMsg();
                }

                xpp.setInput(new StringReader(result.toString()));
                xpp.next();
                eventType = xpp.getEventType();
                subnon = XMLHandlers.SubNonWorkingXMLHandler(xpp, eventType);

                data.value = 12;
                data.name = "Sub Non Working Reason Downloading";
                publishProgress(data);

                // keyacount

                // NON WORKING RESAON
                request = new SoapObject(CommonString.NAMESPACE,
                        CommonString.METHOD_KEY_ACCOUNT_MASTER);

                envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                envelope.dotNet = true;
                envelope.setOutputSoapObject(request);

                androidHttpTransport = new HttpTransportSE(CommonString.URL);

                androidHttpTransport.call(CommonString.SOAP_ACTION_KEY_ACCOUNT,
                        envelope);
                result = (Object) envelope.getResponse();

                if (result.toString().equalsIgnoreCase(CommonString.KEY_FALSE)) {
                    return CommonString.METHOD_KEY_ACCOUNT_MASTER;
                }

                if (result.toString()
                        .equalsIgnoreCase(CommonString.KEY_NO_DATA)) {
                    return "No Data in "
                            + CommonString.METHOD_KEY_ACCOUNT_MASTER;
                }

                // for failure
                xpp.setInput(new StringReader(result.toString()));
                xpp.next();
                eventType = xpp.getEventType();
                failureGetterSetter = XMLHandlers.failureXMLHandler(xpp,
                        eventType);

                if (failureGetterSetter.getStatus().equalsIgnoreCase(
                        CommonString.KEY_FAILURE)) {
                    return CommonString.METHOD_KEY_ACCOUNT_MASTER + ","
                            + failureGetterSetter.getErrorMsg();
                }

                xpp.setInput(new StringReader(result.toString()));
                xpp.next();
                eventType = xpp.getEventType();
                keyacunt = XMLHandlers.KeyAcuntXMLHandler(xpp, eventType);

                data.value = 12;
                data.name = "Key account Downloading";
                publishProgress(data);

                // ASSTES MASTER
                request = new SoapObject(CommonString.NAMESPACE,
                        CommonString.METHOD_ASSETS_MASTER);

                envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                envelope.dotNet = true;
                envelope.setOutputSoapObject(request);

                androidHttpTransport = new HttpTransportSE(CommonString.URL);

                androidHttpTransport.call(
                        CommonString.SOAP_ACTION_ASSET_MASTER, envelope);
                result = (Object) envelope.getResponse();

                if (result.toString().equalsIgnoreCase(CommonString.KEY_FALSE)) {
                    return CommonString.METHOD_ASSETS_MASTER;
                }

                if (result.toString()
                        .equalsIgnoreCase(CommonString.KEY_NO_DATA)) {
                    return "No Data in " + CommonString.METHOD_ASSETS_MASTER;
                }

                // for failure
                xpp.setInput(new StringReader(result.toString()));
                xpp.next();
                eventType = xpp.getEventType();
                failureGetterSetter = XMLHandlers.failureXMLHandler(xpp,
                        eventType);

                if (failureGetterSetter.getStatus().equalsIgnoreCase(
                        CommonString.KEY_FAILURE)) {
                    return CommonString.METHOD_ASSETS_MASTER + ","
                            + failureGetterSetter.getErrorMsg();
                }

                xpp.setInput(new StringReader(result.toString()));
                xpp.next();
                eventType = xpp.getEventType();
                assetdata = XMLHandlers.AssetsXMLHandler(xpp, eventType);

                data.value = 15;
                data.name = "Assets Data Downloading";
                publishProgress(data);

                // ASSTES TARGET

                request = new SoapObject(CommonString.NAMESPACE,
                        CommonString.METHOD_ASSETS_TARGET);

                request.addProperty("USER_ID", username);

                envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                envelope.dotNet = true;
                envelope.setOutputSoapObject(request);

                androidHttpTransport = new HttpTransportSE(CommonString.URL);

                androidHttpTransport.call(
                        CommonString.SOAP_ACTION_ASSET_TARGET, envelope);
                result = (Object) envelope.getResponse();

                if (result.toString().equalsIgnoreCase(CommonString.KEY_FALSE)) {
                    //	return CommonString.METHOD_ASSETS_TARGET;
                } else if (result.toString().equalsIgnoreCase(
                        CommonString.KEY_NO_DATA)) {
                    //return "No Data in " + CommonString.METHOD_ASSETS_TARGET;
                } else {
                    // for failure
                    xpp.setInput(new StringReader(result.toString()));
                    xpp.next();
                    eventType = xpp.getEventType();
                    failureGetterSetter = XMLHandlers.failureXMLHandler(xpp,
                            eventType);

                    if (failureGetterSetter.getStatus().equalsIgnoreCase(
                            CommonString.KEY_FAILURE)) {
                        return CommonString.METHOD_ASSETS_TARGET + ","
                                + failureGetterSetter.getErrorMsg();
                    }

                    xpp.setInput(new StringReader(result.toString()));
                    xpp.next();
                    eventType = xpp.getEventType();
                    assettarget = XMLHandlers.AssetsTargetXMLHandler(xpp,
                            eventType);

                    data.value = 25;
                    data.name = "Assets Target Downloading";
                    publishProgress(data);

                    db.open();
                    db.InsertAssetTarget(assettarget);
                }
                // VERTICAL MASTER

                request = new SoapObject(CommonString.NAMESPACE,
                        CommonString.METHOD_VERTICAL_MASTER);

                envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                envelope.dotNet = true;
                envelope.setOutputSoapObject(request);

                androidHttpTransport = new HttpTransportSE(CommonString.URL);

                androidHttpTransport.call(
                        CommonString.SOAP_ACTION_VERTICAL_MASTER, envelope);
                result = (Object) envelope.getResponse();

                if (result.toString().equalsIgnoreCase(CommonString.KEY_FALSE)) {
                    return CommonString.METHOD_VERTICAL_MASTER;
                }
                if (result.toString()
                        .equalsIgnoreCase(CommonString.KEY_NO_DATA)) {
                    return "No Data in " + CommonString.METHOD_VERTICAL_MASTER;
                }

                // for failure
                xpp.setInput(new StringReader(result.toString()));
                xpp.next();
                eventType = xpp.getEventType();
                failureGetterSetter = XMLHandlers.failureXMLHandler(xpp,
                        eventType);

                if (failureGetterSetter.getStatus().equalsIgnoreCase(
                        CommonString.KEY_FAILURE)) {
                    return CommonString.METHOD_VERTICAL_MASTER + ","
                            + failureGetterSetter.getErrorMsg();
                }

                xpp.setInput(new StringReader(result.toString()));
                xpp.next();
                eventType = xpp.getEventType();
                verticalmasterdata = XMLHandlers.VerticalMasterXMLHandler(xpp,
                        eventType);

                data.value = 30;
                data.name = "Vertical Master Downloading";
                publishProgress(data);

                // BRAND MASTER
                request = new SoapObject(CommonString.NAMESPACE,
                        CommonString.METHOD_BRAND_MASTER);

                envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                envelope.dotNet = true;
                envelope.setOutputSoapObject(request);

                androidHttpTransport = new HttpTransportSE(CommonString.URL);

                androidHttpTransport.call(
                        CommonString.SOAP_ACTION_BRAND_Master, envelope);
                result = (Object) envelope.getResponse();

                if (result.toString().equalsIgnoreCase(CommonString.KEY_FALSE)) {
                    return CommonString.METHOD_BRAND_MASTER;
                }
                if (result.toString()
                        .equalsIgnoreCase(CommonString.KEY_NO_DATA)) {
                    return "No Data in " + CommonString.METHOD_BRAND_MASTER;
                }

                // for failure
                xpp.setInput(new StringReader(result.toString()));
                xpp.next();
                eventType = xpp.getEventType();
                failureGetterSetter = XMLHandlers.failureXMLHandler(xpp,
                        eventType);

                if (failureGetterSetter.getStatus().equalsIgnoreCase(
                        CommonString.KEY_FAILURE)) {
                    return CommonString.METHOD_BRAND_MASTER + ","
                            + failureGetterSetter.getErrorMsg();
                }

                xpp.setInput(new StringReader(result.toString()));
                xpp.next();
                eventType = xpp.getEventType();
                brandmasterdata = XMLHandlers.BrandMasterXMLHandler(xpp,
                        eventType);

                data.value = 40;
                data.name = "Brand Master Data Downloading";
                publishProgress(data);

                // category master
                request = new SoapObject(CommonString.NAMESPACE,
                        CommonString.METHOD_CATEGORY_MASTER);

                envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                envelope.dotNet = true;
                envelope.setOutputSoapObject(request);

                androidHttpTransport = new HttpTransportSE(CommonString.URL);

                androidHttpTransport.call(
                        CommonString.SOAP_ACTION_CATEGORY_MASTER, envelope);
                result = (Object) envelope.getResponse();

                if (result.toString().equalsIgnoreCase(CommonString.KEY_FALSE)) {
                    return CommonString.METHOD_CATEGORY_MASTER;
                }
                if (result.toString()
                        .equalsIgnoreCase(CommonString.KEY_NO_DATA)) {
                    return "No Data in " + CommonString.METHOD_CATEGORY_MASTER;
                }

                // for failure
                xpp.setInput(new StringReader(result.toString()));
                xpp.next();
                eventType = xpp.getEventType();
                failureGetterSetter = XMLHandlers.failureXMLHandler(xpp,
                        eventType);

                if (failureGetterSetter.getStatus().equalsIgnoreCase(
                        CommonString.KEY_FAILURE)) {
                    return CommonString.METHOD_CATEGORY_MASTER + ","
                            + failureGetterSetter.getErrorMsg();
                }

                xpp.setInput(new StringReader(result.toString()));
                xpp.next();
                eventType = xpp.getEventType();

                categoryMaster = XMLHandlers.CategoryMasterXMLHandler(xpp,
                        eventType);

                data.value = 45;
                data.name = "Category Data Downloading";
                publishProgress(data);

                // category verical MAPPING
                request = new SoapObject(CommonString.NAMESPACE,
                        CommonString.METHOD_CATEGORY_VERTICAL_MAPPING);

                envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                envelope.dotNet = true;
                envelope.setOutputSoapObject(request);

                androidHttpTransport = new HttpTransportSE(CommonString.URL);

                androidHttpTransport.call(
                        CommonString.SOAP_ACTION_CATEGORY_VERTICAL_MAPPING,
                        envelope);
                result = (Object) envelope.getResponse();

                if (result.toString().equalsIgnoreCase(CommonString.KEY_FALSE)) {
                    return CommonString.METHOD_CATEGORY_VERTICAL_MAPPING;
                }
                if (result.toString()
                        .equalsIgnoreCase(CommonString.KEY_NO_DATA)) {
                    return "No Data in "
                            + CommonString.METHOD_CATEGORY_VERTICAL_MAPPING;
                }

                // for failure
                xpp.setInput(new StringReader(result.toString()));
                xpp.next();
                eventType = xpp.getEventType();
                failureGetterSetter = XMLHandlers.failureXMLHandler(xpp,
                        eventType);

                if (failureGetterSetter.getStatus().equalsIgnoreCase(
                        CommonString.KEY_FAILURE)) {
                    return CommonString.METHOD_CATEGORY_VERTICAL_MAPPING + ","
                            + failureGetterSetter.getErrorMsg();
                }

                xpp.setInput(new StringReader(result.toString()));
                xpp.next();
                eventType = xpp.getEventType();
                categoryVerticalMapping = XMLHandlers
                        .CategoryVerticalMappingXMLHandler(xpp, eventType);

                data.value = 50;
                data.name = "Vertical Mapping Data Downloading";
                publishProgress(data);

                // posm MASTER
                request = new SoapObject(CommonString.NAMESPACE,
                        CommonString.METHOD_POSM_MASTER_DOWNLOAD);

                envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                envelope.dotNet = true;
                envelope.setOutputSoapObject(request);

                androidHttpTransport = new HttpTransportSE(CommonString.URL);

                androidHttpTransport
                        .call(CommonString.SOAP_ACTION_POSM_MASTER_DOWNLOAD,
                                envelope);
                result = (Object) envelope.getResponse();

                if (result.toString().equalsIgnoreCase(CommonString.KEY_FALSE)) {
                    return CommonString.METHOD_POSM_MASTER_DOWNLOAD;
                }
                if (result.toString()
                        .equalsIgnoreCase(CommonString.KEY_NO_DATA)) {
                    return "No Data in "
                            + CommonString.METHOD_POSM_MASTER_DOWNLOAD;
                }

                // for failure
                xpp.setInput(new StringReader(result.toString()));
                xpp.next();
                eventType = xpp.getEventType();
                failureGetterSetter = XMLHandlers.failureXMLHandler(xpp,
                        eventType);

                if (failureGetterSetter.getStatus().equalsIgnoreCase(
                        CommonString.KEY_FAILURE)) {
                    return CommonString.METHOD_POSM_MASTER_DOWNLOAD + ","
                            + failureGetterSetter.getErrorMsg();
                }

                xpp.setInput(new StringReader(result.toString()));
                xpp.next();
                eventType = xpp.getEventType();
                posmGetterSetter = XMLHandlers.PosmMasterXMLHandler(xpp,
                        eventType);

                data.value = 60;
                data.name = "Posm Data Downloading";
                publishProgress(data);

                // SKU MASTER

                request = new SoapObject(CommonString.NAMESPACE,
                        CommonString.METHOD_SKU_MASTER_DOWNLOAD);

                envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                envelope.dotNet = true;
                envelope.setOutputSoapObject(request);

                androidHttpTransport = new HttpTransportSE(CommonString.URL);

                androidHttpTransport.call(CommonString.SOAP_ACTION_SKU_MASTER,
                        envelope);
                result = (Object) envelope.getResponse();

                if (result.toString().equalsIgnoreCase(CommonString.KEY_FALSE)) {
                    return CommonString.METHOD_SKU_MASTER_DOWNLOAD;
                }
                if (result.toString()
                        .equalsIgnoreCase(CommonString.KEY_NO_DATA)) {
                    return "No Data in "
                            + CommonString.METHOD_SKU_MASTER_DOWNLOAD;
                }

                // for failure
                xpp.setInput(new StringReader(result.toString()));
                xpp.next();
                eventType = xpp.getEventType();
                failureGetterSetter = XMLHandlers.failureXMLHandler(xpp,
                        eventType);

                if (failureGetterSetter.getStatus().equalsIgnoreCase(
                        CommonString.KEY_FAILURE)) {
                    return CommonString.METHOD_SKU_MASTER_DOWNLOAD + ","
                            + failureGetterSetter.getErrorMsg();
                }

                xpp.setInput(new StringReader(result.toString()));
                xpp.next();
                eventType = xpp.getEventType();
                sku_data = XMLHandlers.SkuMasterDataXMLHandler(xpp, eventType);

                data.value = 70;
                data.name = "Sku Master Downloading";
                publishProgress(data);

                // COMPANY MASTER
                request = new SoapObject(CommonString.NAMESPACE,
                        CommonString.METHOD_COMPANY_MASTER_DOWNLOAD);

                envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                envelope.dotNet = true;
                envelope.setOutputSoapObject(request);

                androidHttpTransport = new HttpTransportSE(CommonString.URL);

                androidHttpTransport.call(
                        CommonString.SOAP_ACTION_COMPANY_MASTER, envelope);
                result = (Object) envelope.getResponse();

                if (result.toString().equalsIgnoreCase(CommonString.KEY_FALSE)) {
                    return CommonString.METHOD_COMPANY_MASTER_DOWNLOAD;
                }
                if (result.toString()
                        .equalsIgnoreCase(CommonString.KEY_NO_DATA)) {
                    return "No Data in "
                            + CommonString.METHOD_COMPANY_MASTER_DOWNLOAD;
                }

                // for failure
                xpp.setInput(new StringReader(result.toString()));
                xpp.next();
                eventType = xpp.getEventType();
                failureGetterSetter = XMLHandlers.failureXMLHandler(xpp,
                        eventType);

                if (failureGetterSetter.getStatus().equalsIgnoreCase(
                        CommonString.KEY_FAILURE)) {
                    return CommonString.METHOD_COMPANY_MASTER_DOWNLOAD + ","
                            + failureGetterSetter.getErrorMsg();
                }

                xpp.setInput(new StringReader(result.toString()));
                xpp.next();
                eventType = xpp.getEventType();
                companydata = XMLHandlers.CompanyMasterXMLHandler(xpp,
                        eventType);

                data.value = 75;
                data.name = "Company Master Downloading";
                publishProgress(data);

                // Non Stock Reason (Shahab Ahmad)

                request = new SoapObject(CommonString.NAMESPACE,
                        CommonString.METHOD_NONSKU_REASON);

                envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                envelope.dotNet = true;
                envelope.setOutputSoapObject(request);

                androidHttpTransport = new HttpTransportSE(CommonString.URL);

                androidHttpTransport.call(
                        CommonString.SOAP_ACTION_NONSKU_REASON, envelope);
                result = (Object) envelope.getResponse();

                if (result.toString().equalsIgnoreCase(CommonString.KEY_FALSE)) {
                    return CommonString.METHOD_NONSKU_REASON;
                }
                if (result.toString()
                        .equalsIgnoreCase(CommonString.KEY_NO_DATA)) {
                    return "No Data in " + CommonString.METHOD_NONSKU_REASON;
                }

                // for failure
                xpp.setInput(new StringReader(result.toString()));
                xpp.next();
                eventType = xpp.getEventType();
                failureGetterSetter = XMLHandlers.failureXMLHandler(xpp,
                        eventType);

                if (failureGetterSetter.getStatus().equalsIgnoreCase(
                        CommonString.KEY_FAILURE)) {
                    return CommonString.METHOD_NONSKU_REASON + ","
                            + failureGetterSetter.getErrorMsg();
                }

                xpp.setInput(new StringReader(result.toString()));
                xpp.next();
                eventType = xpp.getEventType();
                nonskudata = XMLHandlers.NonSkuXMLHandler(xpp, eventType);

                data.value = 75;
                data.name = "Non SKU Reason Master Downloading";
                publishProgress(data);

                // SKU FOCUS

                request = new SoapObject(CommonString.NAMESPACE,
                        CommonString.METHOD_SKU_FOCUS_DOWNLOAD);

                request.addProperty("USER_ID", username);

                envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                envelope.dotNet = true;
                envelope.setOutputSoapObject(request);

                androidHttpTransport = new HttpTransportSE(CommonString.URL);

                androidHttpTransport.call(CommonString.SOAP_ACTION_SKU_FOCUS,
                        envelope);
                result = (Object) envelope.getResponse();

                if (result.toString().equalsIgnoreCase(CommonString.KEY_FALSE)) {
                    return CommonString.METHOD_SKU_FOCUS_DOWNLOAD;
                }
                if (result.toString()
                        .equalsIgnoreCase(CommonString.KEY_NO_DATA)) {
                    return "No Data in "
                            + CommonString.METHOD_SKU_FOCUS_DOWNLOAD;
                }

                // for failure
                xpp.setInput(new StringReader(result.toString()));
                xpp.next();
                eventType = xpp.getEventType();
                failureGetterSetter = XMLHandlers.failureXMLHandler(xpp,
                        eventType);

                if (failureGetterSetter.getStatus().equalsIgnoreCase(
                        CommonString.KEY_FAILURE)) {
                    return CommonString.METHOD_SKU_FOCUS_DOWNLOAD + ","
                            + failureGetterSetter.getErrorMsg();
                }

                xpp.setInput(new StringReader(result.toString()));
                xpp.next();
                eventType = xpp.getEventType();
                skufocus = XMLHandlers.SkufocusXMLHandler(xpp, eventType);

                data.value = 80;
                data.name = "Sku Focus Downloading";
                publishProgress(data);

                // MAPPING COMPETITOR
                request = new SoapObject(CommonString.NAMESPACE,
                        CommonString.METHOD_MAPPING_COMPETITOR);

                envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                envelope.dotNet = true;
                envelope.setOutputSoapObject(request);

                androidHttpTransport = new HttpTransportSE(CommonString.URL);

                androidHttpTransport.call(
                        CommonString.SOAP_ACTION_MAPPING_COMPETITOR, envelope);
                result = (Object) envelope.getResponse();

                if (result.toString().equalsIgnoreCase(CommonString.KEY_FALSE)) {
                    return CommonString.METHOD_MAPPING_COMPETITOR;
                }
                if (result.toString()
                        .equalsIgnoreCase(CommonString.KEY_NO_DATA)) {
                    return "No Data in "
                            + CommonString.METHOD_MAPPING_COMPETITOR;
                }
                // for failure
                xpp.setInput(new StringReader(result.toString()));
                xpp.next();
                eventType = xpp.getEventType();
                failureGetterSetter = XMLHandlers.failureXMLHandler(xpp,
                        eventType);

                if (failureGetterSetter.getStatus().equalsIgnoreCase(
                        CommonString.KEY_FAILURE)) {
                    return CommonString.METHOD_MAPPING_COMPETITOR + ","
                            + failureGetterSetter.getErrorMsg();
                }

                xpp.setInput(new StringReader(result.toString()));
                xpp.next();
                eventType = xpp.getEventType();
                competitor = XMLHandlers.CompetitorXMLHandler(xpp, eventType);

                data.value = 85;
                data.name = "Mapping Data Downloading";
                publishProgress(data);

                // SKUTAREGT
                request = new SoapObject(CommonString.NAMESPACE,
                        CommonString.METHOD_SKUTARGET_FOCUS);

                request.addProperty("USER_ID", username);

                envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                envelope.dotNet = true;
                envelope.setOutputSoapObject(request);

                androidHttpTransport = new HttpTransportSE(CommonString.URL);

                androidHttpTransport.call(
                        CommonString.SOAP_ACTION_SKUTARGET_FOCUS, envelope);
                result = (Object) envelope.getResponse();

                if (result.toString().equalsIgnoreCase(CommonString.KEY_FALSE)) {
                    return CommonString.METHOD_SKUTARGET_FOCUS;
                }

                if (result.toString()
                        .equalsIgnoreCase(CommonString.KEY_NO_DATA)) {
                    return "No Data in " + CommonString.METHOD_SKUTARGET_FOCUS;
                }

                // for failure
                xpp.setInput(new StringReader(result.toString()));
                xpp.next();
                eventType = xpp.getEventType();
                failureGetterSetter = XMLHandlers.failureXMLHandler(xpp,
                        eventType);

                if (failureGetterSetter.getStatus().equalsIgnoreCase(
                        CommonString.KEY_FAILURE)) {
                    return CommonString.METHOD_SKUTARGET_FOCUS + ","
                            + failureGetterSetter.getErrorMsg();
                }

                xpp.setInput(new StringReader(result.toString()));
                xpp.next();
                eventType = xpp.getEventType();
                targetdata = XMLHandlers.SkuTargetXMLHandler(xpp, eventType);

                data.value = 90;
                data.name = "Sod Target Data Downloading";
                publishProgress(data);

                // promotion
                request = new SoapObject(CommonString.NAMESPACE,
                        CommonString.METHOD_PROMOTION_TARGET);

                request.addProperty("USER_ID", username);

                envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                envelope.dotNet = true;
                envelope.setOutputSoapObject(request);

                androidHttpTransport = new HttpTransportSE(CommonString.URL);

                androidHttpTransport.call(CommonString.SOAP_ACTION_PROMOTION,
                        envelope);
                result = (Object) envelope.getResponse();

                if (result.toString().equalsIgnoreCase(CommonString.KEY_FALSE)) {
                    //return CommonString.METHOD_PROMOTION_TARGET;
                } else if (result.toString().equalsIgnoreCase(
                        CommonString.KEY_NO_DATA)) {
                    //return "No Data in " + CommonString.METHOD_PROMOTION_TARGET;
                } else {
                    xpp.setInput(new StringReader(result.toString()));
                    xpp.next();
                    eventType = xpp.getEventType();
                    failureGetterSetter = XMLHandlers.failureXMLHandler(xpp,
                            eventType);

                    if (failureGetterSetter.getStatus().equalsIgnoreCase(
                            CommonString.KEY_FAILURE)) {
                        return CommonString.METHOD_PROMOTION_TARGET + ","
                                + failureGetterSetter.getErrorMsg();
                    }

                    xpp.setInput(new StringReader(result.toString()));
                    xpp.next();
                    eventType = xpp.getEventType();
                    promotion = XMLHandlers.PromotionXMLHandler(xpp, eventType);

                    data.value = 91;
                    data.name = "Promotion Data Downloading";
                    publishProgress(data);

                    db.open();
                    db.InsertPromotionData(promotion);

                }

                // for failure

                // Geo Tags
                request = new SoapObject(CommonString.NAMESPACE,
                        CommonString.METHOD_GEO_TAG_CITY);
                envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                envelope.dotNet = true;
                envelope.setOutputSoapObject(request);

                androidHttpTransport = new HttpTransportSE(CommonString.URL);

                androidHttpTransport.call(
                        CommonString.SOAP_ACTION_GEO_TAG_CITY, envelope);
                result = (Object) envelope.getResponse();

                if (result.toString().equalsIgnoreCase(CommonString.KEY_FALSE)) {
                    return CommonString.METHOD_GEO_TAG_CITY;
                }
                if (result.toString()
                        .equalsIgnoreCase(CommonString.KEY_NO_DATA)) {
                    return "No Data in " + CommonString.METHOD_GEO_TAG_CITY;
                }
                // for failure
                xpp.setInput(new StringReader(result.toString()));
                xpp.next();
                eventType = xpp.getEventType();
                failureGetterSetter = XMLHandlers.failureXMLHandler(xpp,
                        eventType);

                if (failureGetterSetter.getStatus().equalsIgnoreCase(
                        CommonString.KEY_FAILURE)) {
                    return CommonString.METHOD_PROMOTION_TARGET + ","
                            + failureGetterSetter.getErrorMsg();
                }

                xpp.setInput(new StringReader(result.toString()));
                xpp.next();
                eventType = xpp.getEventType();
                geoCityGetterSetter = XMLHandlers.GeoTagCityXMLHandler(xpp,
                        eventType);

                // geodata

                request = new SoapObject(CommonString.NAMESPACE,
                        CommonString.METHOD_GEO_TAG);

                request.addProperty("USER_ID", username);

                envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                envelope.dotNet = true;
                envelope.setOutputSoapObject(request);

                androidHttpTransport = new HttpTransportSE(CommonString.URL);

                androidHttpTransport.call(CommonString.SOAP_ACTION_GEO_TAG,
                        envelope);
                result = (Object) envelope.getResponse();

                if (result.toString().equalsIgnoreCase(CommonString.KEY_FALSE)) {
                    return CommonString.METHOD_GEO_TAG;
                }
                if (result.toString()
                        .equalsIgnoreCase(CommonString.KEY_NO_DATA)) {
                    return "No Data in " + CommonString.METHOD_GEO_TAG;
                }
                // for failure
                xpp.setInput(new StringReader(result.toString()));
                xpp.next();
                eventType = xpp.getEventType();
                failureGetterSetter = XMLHandlers.failureXMLHandler(xpp,
                        eventType);

                if (failureGetterSetter.getStatus().equalsIgnoreCase(
                        CommonString.KEY_FAILURE)) {
                    return CommonString.METHOD_GEO_TAG + ","
                            + failureGetterSetter.getErrorMsg();
                }

                xpp.setInput(new StringReader(result.toString()));
                xpp.next();
                eventType = xpp.getEventType();
                geoTagGetterSetter = XMLHandlers.GeoTagXMLHandler(xpp,
                        eventType);

                data.value = 93;
                data.name = "GeoTag Data Downloading";
                publishProgress(data);

                // Ashish New Update Start

                // targetdata download
                request = new SoapObject(CommonString.NAMESPACE,
                        CommonString.METHOD_TARGET_KPI);

                request.addProperty("USER_ID", username);

                envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                envelope.dotNet = true;
                envelope.setOutputSoapObject(request);

                androidHttpTransport = new HttpTransportSE(CommonString.URL);

                androidHttpTransport.call(CommonString.SOAP_TARGET_KPI,
                        envelope);
                result = (Object) envelope.getResponse();

                if (result.toString().equalsIgnoreCase(CommonString.KEY_FALSE)) {
                    //return CommonString.METHOD_PROMOTION_TARGET;
                } else if (result.toString().equalsIgnoreCase(
                        CommonString.KEY_NO_DATA)) {
                    //return "No Data in " + CommonString.METHOD_PROMOTION_TARGET;
                } else {
                    xpp.setInput(new StringReader(result.toString()));
                    xpp.next();
                    eventType = xpp.getEventType();
                    failureGetterSetter = XMLHandlers.failureXMLHandler(xpp,
                            eventType);

                    if (failureGetterSetter.getStatus().equalsIgnoreCase(
                            CommonString.KEY_FAILURE)) {
                        return CommonString.METHOD_TARGET_KPI + ","
                                + failureGetterSetter.getErrorMsg();
                    }

                    xpp.setInput(new StringReader(result.toString()));
                    xpp.next();
                    eventType = xpp.getEventType();
                    kpitarget = XMLHandlers.KPITARGET(xpp, eventType);

                    data.value = 95;
                    data.name = "KPI DATA";
                    publishProgress(data);

                    db.open();
                    db.insertKPITarget(kpitarget);

                }


                // universal service for target
                request = new SoapObject(CommonString.NAMESPACE,
                        CommonString.METHOD_TARGET_BRANDWISE_SOS);

                request.addProperty("Type", "TARGET_BRANDWISE_SOS");
                request.addProperty("UserName", username);

                envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                envelope.dotNet = true;
                envelope.setOutputSoapObject(request);

                androidHttpTransport = new HttpTransportSE(CommonString.URL);

                androidHttpTransport.call(CommonString.SOAP_ACTION_TARGET_BRANDWISE,
                        envelope);
                result = (Object) envelope.getResponse();


                if (result.toString().equalsIgnoreCase(
                        CommonString.KEY_NO_DATA)) {
                } else {

                    xpp.setInput(new StringReader(result.toString()));
                    xpp.next();
                    eventType = xpp.getEventType();

                    brandwisetargetdata = XMLHandlers.getTargetBrandWise(xpp, eventType);

                    if (brandwisetargetdata.getBrandid().size() > 0) {

                        String merchandiserTable = brandwisetargetdata.getMeta_data();
                        TableBeen.setBrandWiseSOS(merchandiserTable);

                        db.reCreatePerformance();
                    } else {
                        return "TARGET_BRANDWISE_SOS";
                    }

                    data.value = 94;
                    data.name = "TARGET_BRANDWISE_SOS";

                }

                if (result.toString().equalsIgnoreCase(CommonString.KEY_FALSE)) {
                    //return CommonString.METHOD_PROMOTION_TARGET;
                }
//
//				else if (result.toString().equalsIgnoreCase(
//						CommonString.KEY_NO_DATA)) {
//					//return "No Data in " + CommonString.METHOD_PROMOTION_TARGET;
//				} else {
//					xpp.setInput(new StringReader(result.toString()));
//					xpp.next();
//					eventType = xpp.getEventType();
//					failureGetterSetter = XMLHandlers.failureXMLHandler(xpp,
//							eventType);
//
//					if (failureGetterSetter.getStatus().equalsIgnoreCase(
//							CommonString.KEY_FAILURE)) {
//						return CommonString.METHOD_TARGET_BRANDWISE_SOS + ","
//								+ failureGetterSetter.getErrorMsg();
//					}
//
//					xpp.setInput(new StringReader(result.toString()));
//					xpp.next();
//					eventType = xpp.getEventType();
//					promotion = XMLHandlers.PromotionXMLHandler(xpp, eventType);
//
//					data.value = 97;
//					data.name = "TARGET_BRANDWISE_SOS";
//					publishProgress(data);
//
//					db.open();
//					db.InsertPromotionData(promotion);
//
//				}

                //  Downloading data for kpi reports

                request = new SoapObject(CommonString.NAMESPACE,
                        CommonString.METHOD_KPI_REPORT);

                request.addProperty("USER_ID", username);

                envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                envelope.dotNet = true;
                envelope.setOutputSoapObject(request);

                androidHttpTransport = new HttpTransportSE(CommonString.URL);

                androidHttpTransport.call(CommonString.SOAP_KPI_REPORT,
                        envelope);
                result = (Object) envelope.getResponse();

                if (result.toString().equalsIgnoreCase(CommonString.KEY_FALSE)) {
                    //return CommonString.METHOD_PROMOTION_TARGET;
                } else if (result.toString().equalsIgnoreCase(
                        CommonString.KEY_NO_DATA)) {
                    //return "No Data in " + CommonString.METHOD_PROMOTION_TARGET;
                } else {
                    xpp.setInput(new StringReader(result.toString()));
                    xpp.next();
                    eventType = xpp.getEventType();
                    failureGetterSetter = XMLHandlers.failureXMLHandler(xpp,
                            eventType);

                    if (failureGetterSetter.getStatus().equalsIgnoreCase(
                            CommonString.KEY_FAILURE)) {
                        return CommonString.METHOD_TARGET_KPI + ","
                                + failureGetterSetter.getErrorMsg();
                    }

                    xpp.setInput(new StringReader(result.toString()));
                    xpp.next();
                    eventType = xpp.getEventType();
                    kpiREPORT_DATA = XMLHandlers.KPIREPORT_DATA(xpp, eventType);

                    data.value = 95;
                    data.name = "KPI DATA";
                    publishProgress(data);

                    db.open();

                    //inserting kpi reprot data below

                    db.insertKPI_REPORTDATA(kpiREPORT_DATA);


                }


                db.open();
                db.InsertStoreData(jcpdata);
                db.InsertBrandMasterData(brandmasterdata);
                db.InsertVerticalMaster(verticalmasterdata);
                db.InsertCategoryMaster(categoryMaster);
                db.InsertCategoryVerticalMapping(categoryVerticalMapping);
                db.InsertGeoTagCityData(geoCityGetterSetter);
                db.InsertGeoTagData(geoTagGetterSetter);


                data.value = 95;
                data.name = "Inserting Data";
                publishProgress(data);

                db.InsertPosmData(posmGetterSetter);
                db.InsertSkuMasterData(sku_data);
                db.InsertNonSKUReason(nonskudata);
                db.InsertComapnyMasterData(companydata);

                data.value = 97;
                data.name = "Inserting Data";
                publishProgress(data);

                db.InsertSkuFocusData(skufocus);

                db.InsertCompetitorData(competitor);

                db.InsertSkuTargetData(targetdata);

                data.value = 99;
                data.name = "Inserting Data";
                publishProgress(data);

                db.InsertAssetData(assetdata);

                db.InsertNonWorkingData(nonworking);
                db.InsertSubNonWorkingData(subnon);
                db.InsertKEYAccData(keyacunt);
                db.InsertBrandWiseTarget(brandwisetargetdata);

                db.deleteDailyEntryData();

                data.value = 100;
                data.name = "Finishing";
                publishProgress(data);

                return CommonString.KEY_SUCCESS;

            } catch (MalformedURLException e) {

                final AlertMessage message = new AlertMessage(
                        CompleteDownloadActivity.this,
                        AlertMessage.MESSAGE_EXCEPTION, "download", e);
                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        // TODO Auto-generated method stub
                        message.showMessage();
                    }
                });

            } catch (IOException e) {
                final AlertMessage message = new AlertMessage(
                        CompleteDownloadActivity.this,
                        AlertMessage.MESSAGE_SOCKETEXCEPTION, "socket", e);
                counter++;
                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        // TODO Auto-generated method stub

                        if (counter < 3) {
                            new BackgroundTask(CompleteDownloadActivity.this)
                                    .execute();
                        } else {
                            message.showMessage();
                            counter = 1;
                        }

                    }
                });
            } catch (Exception e) {
                final AlertMessage message = new AlertMessage(
                        CompleteDownloadActivity.this,
                        AlertMessage.MESSAGE_EXCEPTION, "download", e);

                e.getMessage();
                e.printStackTrace();
                e.getCause();
                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        // TODO Auto-generated method stub
                        message.showMessage();
                    }
                });
            }

            return "";
        }

        @Override
        protected void onProgressUpdate(Data... values) {
            // TODO Auto-generated method stub

            pb.setProgress(values[0].value);
            percentage.setText(values[0].value + "%");
            message.setText(values[0].name);

        }

        @Override
        protected void onPostExecute(String result) {
            // TODO Auto-generated method stub
            super.onPostExecute(result);
            dialog.dismiss();

            if (result.equals(CommonString.KEY_SUCCESS)) {
                AlertMessage message = new AlertMessage(
                        CompleteDownloadActivity.this,
                        AlertMessage.MESSAGE_DOWNLOAD, "success", null);
                message.showMessage();
            } else if (result.equals(CommonString.METHOD_NAME_JCP)) {
                AlertMessage message = new AlertMessage(
                        CompleteDownloadActivity.this,
                        AlertMessage.MESSAGE_JCP_FALSE, "success", null);
                message.showMessage();
            } else if (!result.equals("")) {
                AlertMessage message = new AlertMessage(
                        CompleteDownloadActivity.this, result, "success", null);
                message.showMessage();
            }

        }

    }

    class Data {
        int value;
        String name;
    }

}
