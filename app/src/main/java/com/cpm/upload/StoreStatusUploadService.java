package com.cpm.upload;

import java.io.IOException;
import java.io.StringReader;
import java.net.MalformedURLException;
import java.util.Calendar;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.widget.Toast;

import com.cpm.Constants.CommonString;
import com.cpm.database.PepsicoDatabase;
import com.cpm.xmlHandler.FailureXMLHandler;

public class StoreStatusUploadService extends Service implements
		LocationListener {

	private SharedPreferences preferences;
	PepsicoDatabase db;
	int counter;
	String store_id, audit_id, username, cycleid, visitdate;
	private Double currLatitude = 0.0;
	private Double currLongitude = 0.0;
	private LocationManager m_gpsManager = null;

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {

		return super.onStartCommand(intent, flags, startId);
	}

	public void onCreate() {
		super.onCreate();

		db = new PepsicoDatabase(StoreStatusUploadService.this);

		preferences = PreferenceManager.getDefaultSharedPreferences(this);

		store_id = preferences.getString(CommonString.KEY_STORE_ID, "");
		username = preferences.getString(CommonString.KEY_USERNAME, "");
		visitdate = preferences.getString(CommonString.KEY_VISIT_DATE, "");

		m_gpsManager = (LocationManager) getSystemService(LOCATION_SERVICE);
		m_gpsManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0,
				this);

		if (!m_gpsManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {

			Intent intent = new Intent("android.location.GPS_ENABLED_CHANGE");
			intent.putExtra("enabled", true);
			//sendBroadcast(intent);

		}

		startService();

	}

	private void startService() {

		new MyTask().execute();

	}

	public String getCurrentTime() {

		Calendar m_cal = Calendar.getInstance();
		int hour = m_cal.get(Calendar.HOUR_OF_DAY);
		int min = m_cal.get(Calendar.MINUTE);

		String intime = "";

		if (hour == 0) {
			intime = "" + 12 + ":" + min + " AM";
		} else if (hour == 12) {
			intime = "" + 12 + ":" + min + " PM";
		} else {

			if (hour > 12) {
				hour = hour - 12;
				intime = "" + hour + ":" + min + " PM";
			} else {
				intime = "" + hour + ":" + min + " AM";
			}
		}
		return intime;

	}

	private class MyTask extends AsyncTask<Void, Void, Void> {

		protected void onPreExecute() {

		}

		@Override
		protected void onPostExecute(Void result) {

			Intent intent = new Intent("android.location.GPS_ENABLED_CHANGE");
			intent.putExtra("enabled", false);
			//sendBroadcast(intent);

			stopSelf();

		}

		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub
			try {

				final String result = doWebData();

				if (result.equalsIgnoreCase(CommonString.KEY_SUCCESS)) {

				} else {

				}

			} catch (Exception e) {

			}

			return null;
		}

	}

	public String doWebData() {

		try {
			SAXParserFactory saxPF = SAXParserFactory.newInstance();
			SAXParser saxP = saxPF.newSAXParser();
			XMLReader xmlR = saxP.getXMLReader();

			/*
			 * if (currLatitude.equals(0.0) && currLongitude.equals(0.0)) {
			 * m_gpsManager = (LocationManager)
			 * getSystemService(LOCATION_SERVICE);
			 * m_gpsManager.requestLocationUpdates(
			 * LocationManager.NETWORK_PROVIDER, 0, 0, this); }
			 */
			String onXML = "[DATA][USER_DATA][USER_ID]" + username
					+ "[/USER_ID]" + "[STORE_ID]" + store_id
					+ "[/STORE_ID][CUR_DATE]" + visitdate
					+ "[/CUR_DATE][CUR_TIME]" + getCurrentTime()
					+ "[/CUR_TIME][LATITUDE]" + currLatitude
					+ "[/LATITUDE][LONGITUDE]" + currLongitude
					+ "[/LONGITUDE][/USER_DATA][/DATA]";

			SoapObject request = new SoapObject(CommonString.NAMESPACE,
					CommonString.METHOD_UPLOAD_STORE_STATUS);
			request.addProperty("onXML", onXML);

			SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
					SoapEnvelope.VER11);
			envelope.dotNet = true;
			envelope.setOutputSoapObject(request);

			HttpTransportSE androidHttpTransport = new HttpTransportSE(
					CommonString.URL);

			androidHttpTransport.call(
					CommonString.SOAP_ACTION_UPLOAD_STORE_STATUS, envelope);
			Object result = (Object) envelope.getResponse();

			if (result.toString().equalsIgnoreCase(CommonString.KEY_SUCCESS)) {

			} else {
				if (result.toString().equalsIgnoreCase(CommonString.KEY_FALSE)) {
					return CommonString.METHOD_UPLOAD_STORE_STATUS;
				}

			}

		} catch (MalformedURLException e) {

			return e.getMessage();

		} catch (IOException e) {

			counter++;
			if (counter < 2) {
				new MyTask().execute();

			} else {
				counter = 1;
			}

			return e.getMessage();
		}

		catch (Exception e) {

			return e.getMessage();
		}
		return CommonString.KEY_SUCCESS;

	}

	public void onDestroy() {
		super.onDestroy();

	}

	@Override
	public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub
		currLatitude = location.getLatitude();
		currLongitude = location.getLongitude();
	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub

	}

}
