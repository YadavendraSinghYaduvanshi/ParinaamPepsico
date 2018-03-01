package com.cpm.dailyEntry;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.text.InputFilter;
import android.text.Spanned;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.cpm.Constants.CommonString;
import com.cpm.dailyEntry.StoreVisitedActivity.UploadAttandence;
import com.cpm.database.PepsicoDatabase;
import com.cpm.delegates.CoverageBean;
import com.cpm.delegates.ReasonModel;
import com.cpm.delegates.StoreBean;
import com.cpm.parinaampepsicoo.R;
import com.cpm.upload.ImageUploadActivity;

public class NonWorkingActivity extends Activity implements
		OnItemSelectedListener, OnClickListener {

	ArrayList<ReasonModel> reasondata = new ArrayList<ReasonModel>();
	ArrayList<ReasonModel> subreasondata = new ArrayList<ReasonModel>();
	ArrayList<ReasonModel> keyaccount = new ArrayList<ReasonModel>();
	private Spinner reasonspinner, keyacunt, subreason;
	private PepsicoDatabase database;
	String reasonname, reasonid, image, entry, intime, subresn, subresnid,
			keyname, keyacuntid, informedto, storeinformation,otherreason;
	Button camera, save;
	private ArrayAdapter<CharSequence> reason_adapter, keyacuntadapter,
			subreason_adapter;
	protected String _path;
	// private static String Reason[];
	// private static String ReasonId[];
	// private static String Image[];
	// private static String Entry[];
	protected String _pathforcheck = "";
	private ArrayList<StoreBean> storedata = new ArrayList<StoreBean>();
	private String image1 = "";
	// private ArrayList<CoverageBean> cdata = new ArrayList<CoverageBean>();
	protected boolean _taken;
	protected static final String PHOTO_TAKEN = "photo_taken";
	private SharedPreferences preferences;
	String TempDate = "";
	String _UserId, visit_date, store_id;
	protected boolean status = true;
	TextView text;
	AlertDialog alert;
	EditText edit, storeinfo,other_reason;
	private ArrayList<CoverageBean> coverageBeanlist = new ArrayList<CoverageBean>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.nonworking);

		reasonspinner = (Spinner) findViewById(R.id.spinner2);
		subreason = (Spinner) findViewById(R.id.subreason);
		keyacunt = (Spinner) findViewById(R.id.keyacount);
		camera = (Button) findViewById(R.id.picbtn1);
		save = (Button) findViewById(R.id.save);
		text = (TextView) findViewById(R.id.testview4);
		edit = (EditText) findViewById(R.id.edit);
		storeinfo = (EditText) findViewById(R.id.storeinfo);
		other_reason = (EditText) findViewById(R.id.otherreason);

		TempDate = new Date().toLocaleString().toString().replace(' ', '_')
				.replace(',', '_').replace(':', '-');

		preferences = PreferenceManager.getDefaultSharedPreferences(this);
		_UserId = preferences.getString(CommonString.KEY_USERNAME, "");
		visit_date = preferences.getString(CommonString.KEY_DATE, null);
		store_id = preferences.getString(CommonString.KEY_STORE_ID, "");

		database = new PepsicoDatabase(this);
		database.open();

		reasondata = database.getNonWorkingData();

		keyaccount = database.getKEYAccData();
		// cdata = database.getCoverageData(visit_date, null);
		storedata = database.getStoreData(visit_date);

		intime = getCurrentTime();

		camera.setOnClickListener(this);
		save.setOnClickListener(this);

		reason_adapter = new ArrayAdapter<CharSequence>(this,
				android.R.layout.simple_spinner_item);

		reason_adapter.add("Select Reason");

		keyacuntadapter = new ArrayAdapter<CharSequence>(this,
				android.R.layout.simple_spinner_item);

		subreason_adapter = new ArrayAdapter<CharSequence>(this,
				android.R.layout.simple_spinner_item);

		keyacuntadapter.add("Select Keyaccount");

		subreason_adapter.add("Select SubReason");

		subreason.setAdapter(subreason_adapter);
		subreason_adapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		subreason.setOnItemSelectedListener(this);

		for (int i = 0; i < keyaccount.size(); i++) {
			keyacuntadapter.add(keyaccount.get(i).getKeyaccunt());
		}

		keyacunt.setAdapter(keyacuntadapter);
		keyacuntadapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		keyacunt.setOnItemSelectedListener(this);

		for (int i = 0; i < reasondata.size(); i++) {
			reason_adapter.add(reasondata.get(i).getMreason());
		}

		reasonspinner.setAdapter(reason_adapter);

		reason_adapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		reasonspinner.setOnItemSelectedListener(this);
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub

		Intent i = new Intent(this, StoreVisitedActivity.class);
		startActivity(i);
		NonWorkingActivity.this.finish();
	}

	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int position,
			long arg3) {
		// TODO Auto-generated method stub

		switch (arg0.getId()) {
		case R.id.spinner2:
			if (position != 0) {
				reasonname = reasondata.get(position - 1).getMreason();
				reasonid = reasondata.get(position - 1).getMreasonid();

				subreason_adapter.clear();
				subreason_adapter.add("Select SubReason");
				subreasondata = database.getSubReasonData(reasonid);
				for (int i = 0; i < subreasondata.size(); i++) {
					subreason_adapter.add(subreasondata.get(i).getReason());
				}

				subreason.setAdapter(subreason_adapter);

			} else {
				reasonname = "";
				reasonid = "";

			}

		case R.id.keyacount:
			if (position != 0) {
				keyacuntid = keyaccount.get(position - 1).getKeyid();
				keyname = keyaccount.get(position - 1).getKeyaccunt();

			} else {
				keyname = "";
				keyacuntid = "";
			}
			break;
		case R.id.subreason:
			if (position != 0) {
				subresn = subreasondata.get(position - 1).getReason();
				subresnid = subreasondata.get(position - 1).getReasonid();
				image = subreasondata.get(position - 1).getImage();
				entry = subreasondata.get(position - 1).getEntry();
				informedto = subreasondata.get(position - 1).getInformnedto();
				storeinformation = subreasondata.get(position - 1)
						.getStoreinfo();
				otherreason = subreasondata.get(position - 1)
						.getOtherreason();
				
				image1 = "";
				keyacunt.setSelection(0);
				keyacuntid="";
				keyname = "";
				other_reason.setText("");
				storeinfo.setText("");
				edit.setText("");
				camera.setBackgroundResource(R.drawable.camera_list);

				if (image.equalsIgnoreCase("true")) {
					text.setVisibility(View.VISIBLE);
					camera.setVisibility(View.VISIBLE);
				} else {
					text.setVisibility(View.GONE);
					camera.setVisibility(View.GONE);
				}

				if (informedto.equalsIgnoreCase("true")) {
					edit.setVisibility(View.VISIBLE);

				} else {

					edit.setVisibility(View.GONE);
				}
				if (otherreason.equalsIgnoreCase("true")) {
					other_reason.setVisibility(View.VISIBLE);

				} else {

					other_reason.setVisibility(View.GONE);
				}

				if (storeinformation.equalsIgnoreCase("true")) {
					keyacunt.setVisibility(View.VISIBLE);
					storeinfo.setVisibility(View.VISIBLE);
				} else {
					keyacunt.setVisibility(View.GONE);
					storeinfo.setVisibility(View.GONE);
				}

			} else {
				subresn = "";
				subresnid = "";
				image = "";
				entry = "";
				informedto = "";
				storeinformation = "";
				otherreason="";
				
			}
			break;
		}

	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub

	}

	protected void startCameraActivity() {

		try {
			Log.i("MakeMachine", "startCameraActivity()");
			File file = new File(_path);
			Uri outputFileUri = Uri.fromFile(file);

			Intent intent = new Intent(
					android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
			intent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);

			startActivityForResult(intent, 0);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		Log.i("MakeMachine", "resultCode: " + resultCode);
		switch (resultCode) {
		case 0:
			Log.i("MakeMachine", "User cancelled");
			break;

		case -1:

			if (ImageUploadActivity.CheckGeotagImage(_pathforcheck)) {

				camera.setBackgroundResource(R.drawable.camera_list_tick);
				image1 = _pathforcheck;

			}
			break;
		}

	}

	public boolean imageAllowed() {
		boolean result = true;

		if (image.equalsIgnoreCase("true")) {

			if (image1.equalsIgnoreCase("")) {

				result = false;

			}
		} else {
			image1 = "";
		}

		return result;

	}

	public boolean informedto() {
		boolean result = true;

		if (informedto.equalsIgnoreCase("true")) {

			if (edit.getText().toString().equalsIgnoreCase("")) {

				result = false;

			}
		}

		return result;

	}
	public boolean OtherReason() {
		boolean result = true;

		if (otherreason.equalsIgnoreCase("true")) {

			if (other_reason.getText().toString().equalsIgnoreCase("")) {

				result = false;

			}
		}

		return result;

	}

	public boolean storeinfo() {
		boolean result = true;

		if (storeinformation.equalsIgnoreCase("true")) {

			if (storeinfo.getText().toString().equalsIgnoreCase("")) {

				result = false;

			}
		}

		return result;

	}

	public boolean validatedata() {
		boolean result = false;
		if (reasonid != null && !reasonid.equalsIgnoreCase("")) {
			result = true;
		}

		return result;

	}

	public boolean validateSubreasondata() {
		boolean result = false;
		if (subresnid != null && !subresn.equalsIgnoreCase("")) {
			result = true;
		}
		return result;

	}

	public boolean validateKeyaccunt() {
		boolean result = false;
		if (storeinformation.equalsIgnoreCase("true")) {
			if (keyacuntid != null && !keyacuntid.equalsIgnoreCase("")) {
				result = true;
			}
		} else {
			result = true;

		}
		return result;

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (v.getId() == R.id.picbtn1) {
			_pathforcheck = store_id + "NonWorking" + _UserId + TempDate
					+ "image1" + ".jpg";

			_path = Environment.getExternalStorageDirectory()
					+ "/PepsicoImages/" + _pathforcheck;

			Log.i("MakeMachine", "ButtonClickHandler.onClick()");
			startCameraActivity();
		}
		if (v.getId() == R.id.save) {

			if (validatedata()) {

				if (validateSubreasondata()) {

					if (imageAllowed()) {

						if (informedto()) {

							if (validateKeyaccunt()) {

								if (storeinfo()) {
									
									if (OtherReason()) {

									AlertDialog.Builder builder = new AlertDialog.Builder(
											NonWorkingActivity.this);
									builder.setMessage(
											"Do you want to save the data ")
											.setCancelable(false)
											.setPositiveButton(
													"OK",
													new DialogInterface.OnClickListener() {
														public void onClick(
																DialogInterface dialog,
																int id) {

															alert.getButton(
																	AlertDialog.BUTTON_POSITIVE)
																	.setEnabled(
																			false);

															if (entry
																	.equalsIgnoreCase("true")) {

																CoverageBean cdata = new CoverageBean();
																cdata.setStoreId(store_id);
																cdata.setVisitDate(visit_date);
																cdata.setUserId(_UserId);
																cdata.setInTime(intime);
																cdata.setOutTime(getCurrentTime());
																cdata.setReason(subresn);
																cdata.setRemarks(edit
																		.getText()
																		.toString()
																		.replaceAll(
																				"[&^<>{}'$]",
																				" "));
																cdata.setReasonid(subresnid);
																cdata.setLatitude("0.0");
																cdata.setLongitude("0.0");
																cdata.setImage(image1);
																cdata.setKeycontactId(keyacuntid);
																cdata.setStoreinfo(storeinfo
																		.getText()
																		.toString());
																cdata.setStatus(CommonString.STORE_STATUS_LEAVE);
																cdata.setOtherreson(other_reason.getText().toString());
																
																
																coverageBeanlist = database.getCoverageData(visit_date, null);
																
																if(coverageBeanlist.size()==0)
																{
																	new UploadAttandence().execute();
																}
																
																
																
																
																
																database.InsertCoverageData(cdata);
																
																database.updateStoreStatusOnLeave(
																		store_id,
																		visit_date,
																		CommonString.STORE_STATUS_LEAVE);

																database.updateStoreStatusOnCheckout(
																		store_id,
																		visit_date,
																		CommonString.KEY_L);

																image1 = "";

																SharedPreferences.Editor editor = preferences
																		.edit();
																editor.putString(
																		CommonString.KEY_STOREVISITED_STATUS,
																		"no");
																editor.commit();

																Intent intent = new Intent(
																		getApplicationContext(),
																		StorelistActivity.class);
																startActivity(intent);
																NonWorkingActivity.this
																		.finish();

															} else {

																for (int i = 0; i < storedata
																		.size(); i++) {

																	if (database
																			.CheckMid(
																					visit_date,
																					storedata
																							.get(i)
																							.getStoreid()) > 0
																			|| storedata
																					.get(i)
																					.getStatus()
																					.equalsIgnoreCase(
																							CommonString.KEY_U)) {

																	} else {

																		CoverageBean cdata = new CoverageBean();
																		cdata.setStoreId(storedata
																				.get(i)
																				.getStoreid());
																		cdata.setVisitDate(visit_date);
																		cdata.setUserId(_UserId);
																		cdata.setInTime(intime);
																		cdata.setOutTime(getCurrentTime());
																		cdata.setReason(subresn);
																		cdata.setReasonid(subresnid);
																		cdata.setLatitude("0.0");
																		cdata.setLongitude("0.0");
																		cdata.setImage(image1);
																		cdata.setOtherreson(other_reason.getText().toString());
																		cdata.setRemarks(edit
																				.getText()
																				.toString()
																				.replaceAll(
																						"[&^<>{}'$]",
																						" "));
																		cdata.setKeycontactId(keyacuntid);
																		cdata.setStoreinfo(storeinfo
																				.getText()
																				.toString());
																		cdata.setStatus(CommonString.STORE_STATUS_LEAVE);
																		database.InsertCoverageData(cdata);

																		database.updateStoreStatusOnLeave(
																				storedata
																						.get(i)
																						.getStoreid(),
																				visit_date,
																				CommonString.STORE_STATUS_LEAVE);

																		database.updateStoreStatusOnCheckout(
																				store_id,
																				visit_date,
																				CommonString.KEY_L);

																		SharedPreferences.Editor editor = preferences
																				.edit();
																		editor.putString(
																				CommonString.KEY_STOREVISITED_STATUS,
																				"no");
																		editor.commit();

																		Intent intent = new Intent(
																				getApplicationContext(),
																				StorelistActivity.class);
																		startActivity(intent);
																		NonWorkingActivity.this
																				.finish();
																	}

																}

															}

														}
													})
											.setNegativeButton(
													"Cancel",
													new DialogInterface.OnClickListener() {
														public void onClick(
																DialogInterface dialog,
																int id) {
															dialog.cancel();
														}
													});

									alert = builder.create();
									alert.show();
								}

								else {
									Toast.makeText(getApplicationContext(),
											"Please entered Reason",
											Toast.LENGTH_SHORT).show();
								}

							}
								else {
									Toast.makeText(getApplicationContext(),
											"Please entered Storeinfo",
											Toast.LENGTH_SHORT).show();
								}

							}

							else {
								Toast.makeText(getApplicationContext(),
										"Please Select KeyAccount",
										Toast.LENGTH_SHORT).show();
							}
						} else {
							Toast.makeText(getApplicationContext(),
									"Please fill Informed to",
									Toast.LENGTH_SHORT).show();
						}
					} else {
						Toast.makeText(getApplicationContext(),
								"Please Capture Photo", Toast.LENGTH_SHORT)
								.show();
					}

				} else {
					Toast.makeText(getApplicationContext(),
							"Please Select a SubReason", Toast.LENGTH_SHORT)
							.show();

				}
			} else {
				Toast.makeText(getApplicationContext(),
						"Please Select a Reason", Toast.LENGTH_SHORT).show();

			}

		}
	}

	public String getCurrentTime() {

		Calendar m_cal = Calendar.getInstance();

		String intime = m_cal.get(Calendar.HOUR_OF_DAY) + ":"
				+ m_cal.get(Calendar.MINUTE) + ":" + m_cal.get(Calendar.SECOND);

		return intime;

	}
	
	
	public class UploadAttandence extends AsyncTask<Void, String, Void>  
	{

		@Override
		protected Void doInBackground(Void... params)  {
			// TODO Auto-generated method stub
			
			try 
			{
				SoapObject request = new SoapObject(
						CommonString.NAMESPACE,                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       
						CommonString.METHOD_UPLOAD_ATTANDANCEDATA);
				
		/*		string USER_ID 
				  string ATT_DATE             
				 int REASON_ID */


				String onXML = "[DATA][USER_DATA][USER_ID]"
						+ _UserId
						+ "[/USER_ID]" + "[ATT_DATE]"
						+ visit_date
						+ "[/ATT_DATE][REASON_ID]"
						+ reasonid
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
			
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
			}
			
			return null;

		}
	
}
}
