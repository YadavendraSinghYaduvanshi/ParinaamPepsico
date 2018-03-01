package com.cpm.parinaampepsico;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import android.app.ProgressDialog;
import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager.NameNotFoundException;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Binder;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.widget.Toast;

import com.cpm.Constants.CommonString;
import com.cpm.database.PepsicoDatabase;
import com.cpm.delegates.LocationBean;

public class GetService extends Service implements LocationListener {
	private ProgressDialog dialog1;
	private LocationManager m_gpsManager = null;
	private String Text;
	private Double currLatitude = 0.0;
	private Double currLongitude = 0.0;
	private String provider;
	private String username, password, visit_date;
	private SharedPreferences preferences;
	private int versionCode;
	private static ArrayList<LocationBean> locationlist;
	PepsicoDatabase database;
	private Timer timer;
	Handler handler;
	private boolean status = false;
	String app_ver; 

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return new Binder();
	}

	public void onCreate() {
		super.onCreate();

		preferences = PreferenceManager.getDefaultSharedPreferences(this);
		username = preferences.getString(CommonString.KEY_USERNAME, null);
		password = preferences.getString(CommonString.KEY_PASSWORD, null);
		visit_date = preferences.getString(CommonString.KEY_DATE, null);

		handler = new Handler();

		database = new PepsicoDatabase(this);
		database.open();

		m_gpsManager = (LocationManager) getSystemService(LOCATION_SERVICE);
		m_gpsManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0,
				this);
		provider = m_gpsManager.GPS_PROVIDER;
		locationlist = new ArrayList<LocationBean>();

		if (!m_gpsManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {

			Intent intent = new Intent("android.location.GPS_ENABLED_CHANGE");
			intent.putExtra("enabled", true);
			//sendBroadcast(intent);

		}

		startService();
	}

	private void startService() {

		SharedPreferences.Editor editor = preferences.edit();
		editor.putString(CommonString.KEY_SERVICE_STATUS, "YES");
		editor.commit();

		final Handler handler = new Handler();
		timer = new Timer();

		TimerTask doAsynchronousTask = new TimerTask() {
			@Override
			public void run() {

				handler.postDelayed(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub

						Intent intent = new Intent(
								"android.location.GPS_ENABLED_CHANGE");
						intent.putExtra("enabled", true);
						//sendBroadcast(intent);

						if (status == true) {
							timer.cancel();
						} else {

							if (!currLatitude.equals(0.0)
									&& !currLongitude.equals(0.0)) {

								new MyTask().execute();
							} else {
								openNetwork();
								new MyTask().execute();
							}
						}

					}
				}, 900000);
			}
		};

		timer.schedule(doAsynchronousTask, 0, 3600000);
		//timer.schedule(doAsynchronousTask, 0, 100000);

	}

	public void openNetwork() {

		if (currLatitude.equals(0.0) && currLongitude.equals(0.0)) {

			m_gpsManager = (LocationManager) getSystemService(LOCATION_SERVICE);
			m_gpsManager.requestLocationUpdates(
					LocationManager.NETWORK_PROVIDER, 0, 0, this);
			provider = m_gpsManager.NETWORK_PROVIDER;

		}
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();

		status = true;

	}

	@Override
	public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub

		currLatitude = location.getLatitude();
		currLongitude = location.getLongitude();

	}

	private class MyTask extends AsyncTask<Void, Void, Void> {

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub

		}

		@Override
		protected void onPostExecute(Void result) {

			Text = "My current location is: " + "Latitude = " + currLatitude
					+ "Longitude = " + currLongitude + " provider is "
					+ provider + "Time is" + getCurrentTime();

			if (m_gpsManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
				Intent intent = new Intent(
						"android.location.GPS_ENABLED_CHANGE");
				intent.putExtra("enabled", false);
			//	sendBroadcast(intent);
			}

		}

		@Override
		protected Void doInBackground(Void... params) {

			database.open();
			database.InsertLocationData(visit_date, getCurrentTime(),
					Double.toString(currLatitude),
					Double.toString(currLongitude), provider);

			locationlist = database.getLocationData();
			try {
				app_ver = getPackageManager().getPackageInfo(
							getPackageName(), 0).versionName;
				} catch (NameNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			try {
				for (int i = 0; i < locationlist.size(); i++) {

					String userauth_xml = "[DATA]" + "[USER_DATA][USER_ID]"
							+ username + "[/USER_ID]" + "[PASSWORD]" + password
							+ "[/PASSWORD]" + "[IN_TIME]"
							+ locationlist.get(i).getTime() + "[/IN_TIME]"
							+ "[LATITUDE]" + locationlist.get(i).getLatitude()
							+ "[/LATITUDE]" + "[LONGITUDE]"
							+ locationlist.get(i).getLongitude()
							+ "[/LONGITUDE]" + "[APP_VERSION]"
							+ app_ver + "[/APP_VERSION]"
							+ "[ATT_MODE]online[/ATT_MODE]"
							+ "[NETWORK_STATUS]"
							+ locationlist.get(i).getNetworkStatus()
							+ "[/NETWORK_STATUS]"+"[IMEINO]" + "1" + "[/IMEINO]" + "[/USER_DATA][/DATA]";

					SoapObject request = new SoapObject(CommonString.NAMESPACE,
							CommonString.METHOD_LOGIN);
					request.addProperty("onXML", userauth_xml);

					SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
							SoapEnvelope.VER11);
					envelope.dotNet = true;
					envelope.setOutputSoapObject(request);

					HttpTransportSE androidHttpTransport = new HttpTransportSE(
							CommonString.URL);
					androidHttpTransport.call(CommonString.SOAP_ACTION_LOGIN,
							envelope);

					Object result = (Object) envelope.getResponse();

					if ((!result.toString().equalsIgnoreCase(
							CommonString.KEY_FAILURE))
							&& (!result.toString().equalsIgnoreCase(
									CommonString.KEY_FALSE))) {

						database.deleteLocatioData(locationlist.get(i).getId());

					}

				}
				return null;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (XmlPullParserException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}

			return null;
		}
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

	public String getCurrentTime() {

		Calendar m_cal = Calendar.getInstance();

		String intime = m_cal.get(Calendar.HOUR_OF_DAY) + ":"
				+ m_cal.get(Calendar.MINUTE) + ":" + m_cal.get(Calendar.SECOND);

		return intime;

	}

}
