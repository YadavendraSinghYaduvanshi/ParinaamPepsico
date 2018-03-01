package com.cpm.dailyEntry;

import java.util.ArrayList;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

import com.cpm.Constants.CommonString;
import com.cpm.database.PepsicoDatabase;
import com.cpm.delegates.CoverageBean;
import com.cpm.parinaampepsico.GetService;
import com.cpm.parinaampepsicoo.R;
import com.cpm.upload.StoreStatusUploadService;
import com.cpm.web.ShowUpdateActivity;

public class StoreVisitedActivity extends Activity {

	PepsicoDatabase database;
	private int _mid;
	String store_id, visit_date,_UserId;
	SharedPreferences preferences;
	 ArrayList<CoverageBean> coverageBeanlist = new ArrayList<CoverageBean>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.store_visited);

		RadioButton yes = (RadioButton) findViewById(R.id.yes);
		RadioButton no = (RadioButton) findViewById(R.id.no);

		database = new PepsicoDatabase(this);
		database.open();
	

		preferences = PreferenceManager.getDefaultSharedPreferences(this);
		store_id = preferences.getString(CommonString.KEY_STORE_ID, "");
		visit_date = preferences.getString(CommonString.KEY_DATE, "");
		_UserId = preferences.getString(CommonString.KEY_USERNAME, "");


		if (getMid() != 0) {
			yes.setChecked(true);
		}
	}

	public void onButtonClick(View v) {
		switch (v.getId()) {
		case R.id.yes:

			
			
			
			coverageBeanlist = database.getCoverageData(visit_date, null);
			
			if(coverageBeanlist.size()==0)
			{
				new UploadAttandence().execute();
			}
			
			
			
			Intent serviceIntent = new Intent(StoreVisitedActivity.this,
					StoreStatusUploadService.class);
			startService(serviceIntent);

			Intent i = new Intent(this, Start_Here.class);
			startActivity(i);
			StoreVisitedActivity.this.finish();
			
			
			
			
			
			
			/*
			 * if (preferences.getString(CommonString.KEY_STOREVISITED_STATUS,
			 * "no").equalsIgnoreCase("yes")) {
			 * 
			 * if (preferences.getString(CommonString.KEY_STOREVISITED, "")
			 * .equalsIgnoreCase(store_id)) { Intent serviceIntent = new Intent(
			 * StoreVisitedActivity.this, StoreStatusUploadService.class);
			 * startService(serviceIntent);
			 * 
			 * Intent i = new Intent(this, DailyentryMenuActivity.class);
			 * startActivity(i); StoreVisitedActivity.this.finish(); } else {
			 * Toast.makeText(StoreVisitedActivity.this, "You Cant Checkin",
			 * Toast.LENGTH_SHORT).show(); }
			 * 
			 * } else { Intent serviceIntent = new
			 * Intent(StoreVisitedActivity.this,
			 * StoreStatusUploadService.class); startService(serviceIntent);
			 * 
			 * Intent i = new Intent(this, DailyentryMenuActivity.class);
			 * startActivity(i); StoreVisitedActivity.this.finish();
			 * 
			 * }
			 */

			break;

		case R.id.no:

			if (getMid() != 0) {

				AlertDialog.Builder builder = new AlertDialog.Builder(
						StoreVisitedActivity.this);
				builder.setMessage("Your all data will be deleted.")
						.setCancelable(false)
						.setPositiveButton("OK",
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int id) {

										UpdateData();

									}
								})
						.setNegativeButton("Back",
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int id) {
										dialog.cancel();
										Intent i = new Intent(
												StoreVisitedActivity.this,
												StorelistActivity.class);
										startActivity(i);
										StoreVisitedActivity.this.finish();
									}
								});
				AlertDialog alert = builder.create();

				alert.show();

			} else {

				Intent in = new Intent(this, NonWorkingActivity.class);
				startActivity(in);
				StoreVisitedActivity.this.finish();

			}

			break;

		}
	}

	public long getMid() {

		int mid = 0;

		mid = database.CheckMid(visit_date, store_id);
		_mid = mid;
		return mid;
	}

	public void UpdateData() {

		database.open();
		database.deleteStoreMidData(_mid, store_id);

		database.updateStoreStatusOnLeave(store_id, visit_date,
				CommonString.KEY_N);

		database.updateStoreStatusOnCheckout(store_id, visit_date,
				CommonString.KEY_N);

		Intent intent = new Intent(StoreVisitedActivity.this,
				NonWorkingActivity.class);

		startActivity(intent);

		StoreVisitedActivity.this.finish();

	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub

		Intent i = new Intent(this, StorelistActivity.class);
		startActivity(i);
		StoreVisitedActivity.this.finish();
	}
	
	
	
	
	
	
	
	public class UploadAttandence  extends AsyncTask<Void, String, Void>  
	{

		@Override
		protected Void doInBackground(Void... params)  {
			// TODO Auto-generated method stub
			
			try 
			{
			SoapObject request = new SoapObject(
					CommonString.NAMESPACE,
					CommonString.METHOD_UPLOAD_ATTANDANCEDATA);
			

			String onXML = "[DATA][USER_DATA][USER_ID]"
					+ _UserId
					+ "[/USER_ID]" + "[ATT_DATE]"
					+ visit_date
					+ "[/ATT_DATE][REASON_ID]"
					+ "23"
					+ "[/REASON_ID]"
					+ "[/USER_DATA][/DATA]";
			
			
			request.addProperty("onXML", onXML);
			
			
			SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
					SoapEnvelope.VER11);
			envelope.dotNet = true;
			envelope.setOutputSoapObject(request);

			HttpTransportSE androidHttpTransport = new HttpTransportSE(
					CommonString.URL);

			androidHttpTransport
					.call(CommonString.SOAP_ACTION_UPLOAD_ATTANDANCE,
							envelope);
			Object result = (Object) envelope.getResponse();
			String resulty = result.toString();
			
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
			}
			return null;
			
		

		}

		
	
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	

}
