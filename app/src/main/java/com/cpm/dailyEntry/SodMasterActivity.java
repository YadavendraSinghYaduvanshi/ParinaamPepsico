package com.cpm.dailyEntry;

import java.util.ArrayList;
import java.util.Calendar;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.inputmethodservice.Keyboard;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.cpm.Constants.CommonString;
import com.cpm.database.PepsicoDatabase;
import com.cpm.delegates.CityBean;
import com.cpm.delegates.CoverageBean;
import com.cpm.delegates.PosmBean;
import com.cpm.delegates.SodBean;
import com.cpm.keyboard.BasicOnKeyboardActionListener;
import com.cpm.keyboard.CustomKeyboardView;
import com.cpm.message.AlertMessage;
import com.cpm.parinaampepsicoo.R;

public class SodMasterActivity extends Activity implements
		OnItemSelectedListener {

	private Spinner companyspinner, displayspinner;
	private PepsicoDatabase database;
	private ArrayAdapter<CharSequence> company_master, displayadapter;
	private ArrayList<CityBean> company_list = new ArrayList<CityBean>();
	private ArrayList<PosmBean> displaylist = new ArrayList<PosmBean>();
	private ArrayList<SodBean> sku_list = new ArrayList<SodBean>();
	public ListView lv;
	public final static int DATE_DIALOG_ID = 0;
	public Keyboard mKeyboard;
	public CustomKeyboardView mKeyboardView;
	private String companyname, company_id, posmid, posmname, visit_date,
			store_id, username, intime;
	private SharedPreferences preferences;
	Button save,countervalue;
	TextView text;
	Button camera;
	EditText stock, faceup, l, b, h;
	public static final int POPUP_SELECT = 1;
	public static final int ADD_SKU = 2;
	static String img1 = "";
	static String img2 = "";
	static String img3 = "";
	public String store_name;
	private static int myear;
	private static int month;
	private static int day;
	Button dom1, dom2, dom3;
	String num = "0";
	private ArrayList<SodBean> temp_list = new ArrayList<SodBean>();
	String sod_status;
	static int currentapiVersion = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sod_competitor);

		currentapiVersion = android.os.Build.VERSION.SDK_INT;

		companyspinner = (Spinner) findViewById(R.id.sku);
		displayspinner = (Spinner) findViewById(R.id.display);
		text = (TextView) findViewById(R.id.training_remembermetext);
		
		countervalue = (Button) findViewById(R.id.totalcounter);
		
		lv = (ListView) findViewById(R.id.list);
		save = (Button) findViewById(R.id.save);
		l = (EditText) findViewById(R.id.length);
		b = (EditText) findViewById(R.id.width);
		h = (EditText) findViewById(R.id.heigth);
		dom1 = (Button) findViewById(R.id.dom1);
		dom2 = (Button) findViewById(R.id.dom2);
		dom3 = (Button) findViewById(R.id.dom3);
		camera = (Button) findViewById(R.id.image1);

		intime = getCurrentTime();

		if (currentapiVersion >= 11) {
			l.setTextIsSelectable(true);
			l.setRawInputType(InputType.TYPE_CLASS_TEXT);
			b.setTextIsSelectable(true);
			b.setRawInputType(InputType.TYPE_CLASS_TEXT);
			h.setTextIsSelectable(true);
			h.setRawInputType(InputType.TYPE_CLASS_TEXT);

		} else {
			l.setInputType(0);
			b.setInputType(0);
			h.setInputType(0);
		}

		preferences = PreferenceManager.getDefaultSharedPreferences(this);
		store_name = preferences.getString(CommonString.KEY_STORE_NAME, "");
		store_id = preferences.getString(CommonString.KEY_STORE_ID, "");
		visit_date = preferences.getString(CommonString.KEY_VISIT_DATE, "");

		TextView text1 = (TextView) findViewById(R.id.mainpage_remembermetext);
		text1.setText(store_name);

		database = new PepsicoDatabase(this);
		database.open();

		company_master = new ArrayAdapter<CharSequence>(this,
				android.R.layout.simple_spinner_item);
		displayadapter = new ArrayAdapter<CharSequence>(this,
				android.R.layout.simple_spinner_item);

		company_master.add("Select Company");
		displayadapter.add("Select Display");

		companyspinner.setAdapter(company_master);
		displayspinner.setAdapter(displayadapter);

		company_master
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		displayadapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		companyspinner.setOnItemSelectedListener(this);
		displayspinner.setOnItemSelectedListener(this);

		database = new PepsicoDatabase(this);
		database.open();

		intime = getCurrentTime();

		company_list = database.getCompanyMaster();

		for (int i = 0; i < company_list.size(); i++) {
			company_master.add(company_list.get(i).getCompany());
		}

		displaylist = database.getPosmMasterdata();

		for (int i = 0; i < displaylist.size(); i++) {
			displayadapter.add(displaylist.get(i).getPosm());
		}

		sku_list = database.getSodData(store_id);
		
		if(sku_list.size()>0){
			countervalue.setText(sku_list.get(sku_list.size()-1).getCounter().toString());
		}
	
		
		lv.invalidateViews();
		lv.setAdapter(new MyAdapter(getApplicationContext()));

		mKeyboard = new Keyboard(this, R.xml.keyboard);
		mKeyboardView = (CustomKeyboardView) findViewById(R.id.keyboard_view);
		mKeyboardView.setKeyboard(mKeyboard);
		mKeyboardView
				.setOnKeyboardActionListener(new BasicOnKeyboardActionListener(
						this, 4));

		lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View item,
					int position, long id) {

				if (sku_list.get(position).getCompanyname()
						.equalsIgnoreCase("pepsico")) {
					Intent i = new Intent(getApplicationContext(),
							SodSkuViewList.class);

					i.putExtra(CommonString.KEY_SOD_ID, sku_list.get(position)
							.getId());

					startActivity(i);
					SodMasterActivity.this.finish();
				} else {
					Intent i = new Intent(getApplicationContext(),
							SodSkuOthersViewList.class);
					i.putExtra(CommonString.KEY_SOD_ID, sku_list.get(position)
							.getId());
					startActivity(i);
					SodMasterActivity.this.finish();
				}
			}
		});
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		database.close();
	}

	public void hide() {

		mKeyboardView.setVisibility(View.INVISIBLE);
		mKeyboardView.requestFocusFromTouch();

	}

	public boolean validate_data() {
		boolean result = false;

		if (company_id != null && posmid != null) {

			if ((!l.getText().toString().equals(""))
					&& (!b.getText().toString().equals(""))
					&& (!h.getText().toString().equals(""))) {

				result = true;

				/*
				 * if ((!img1.equals("") || !img2.equals("") ||
				 * !img3.equals(""))) {
				 * 
				 * result = true; }
				 */
			}

		}
		return result;

	}

	public void onButtonClick(View v) {
		switch (v.getId()) {
		case R.id.training_intellogo:
			Intent i = new Intent(this, DailyentryMenuActivity.class);
			startActivity(i);
			SodMasterActivity.this.finish();
			break;
		case R.id.savedata:

			if (validate_data()) {

				database.open();
				temp_list = database.getSodOtherData(store_id);

				if (temp_list.size() == 0) {
					Toast.makeText(getBaseContext(), "Enter SKU",
							Toast.LENGTH_LONG).show();
				} else {

					SodBean sod = new SodBean();
					sod.setCompanyid(company_id);
					sod.setCompanyname(companyname);
					sod.setStoreid(store_id);
					sod.setPosm(posmname);
					
					sod.setH(h.getText().toString());
					sod.setL(l.getText().toString());
					sod.setB(b.getText().toString());
					sod.setPosmid(posmid);
					sod.setImg1(img1);
					sod.setImg2(img2);
					sod.setImg3(img3);

					long sod_id = database.InsertSodData(getMid(), sod);

					database.InsertSodTransactionData(sod_id, temp_list,companyname);

					mKeyboardView.setVisibility(View.INVISIBLE);

					companyspinner.setEnabled(true);
					displayspinner.setEnabled(true);

					companyspinner.setSelection(0);
					displayspinner.setSelection(0);

					l.setText("");
					b.setText("");
					h.setText("");

					img1 = "";
					img2 = "";
					img3 = "";

					camera.setBackgroundResource(R.drawable.camera_list);

					sku_list = database.getSodData(store_id);

					lv.invalidateViews();

					lv.setAdapter(new MyAdapter(getApplicationContext()));

				}

			} else {
				Toast.makeText(getBaseContext(),
						AlertMessage.MESSAGE_INVALID_DATA, Toast.LENGTH_LONG)
						.show();
			}
			break;

		case R.id.length:
			if (mKeyboardView.getVisibility() == View.INVISIBLE)
				mKeyboardView.setVisibility(View.VISIBLE);
			break;

		case R.id.width:
			if (mKeyboardView.getVisibility() == View.INVISIBLE)
				mKeyboardView.setVisibility(View.VISIBLE);
			break;
		case R.id.heigth:
			if (mKeyboardView.getVisibility() == View.INVISIBLE)
				mKeyboardView.setVisibility(View.VISIBLE);
			break;
		case R.id.image1:

			Intent intent = new Intent(getApplicationContext(),
					ImagePepsicoActivity.class);
			intent.putExtra("image1path", img1);
			intent.putExtra("image2path", img2);
			intent.putExtra("image3path", img3);

			startActivityForResult(intent, POPUP_SELECT);
			break;

		case R.id.save:

			if (validate_data()) {

				if (companyname.equalsIgnoreCase("pepsico")) {

					Intent in = new Intent(getApplicationContext(),
							SodTempPepsiActivity.class);
					in.putExtra(CommonString.KEY_COMPANY_ID, company_id);
					startActivityForResult(in, ADD_SKU);
				} else {
					Intent in = new Intent(getApplicationContext(),
							SodTempOtherActivity.class);
					in.putExtra(CommonString.KEY_COMPANY_ID, company_id);
					startActivityForResult(in, ADD_SKU);
				}
			} else {
				Toast.makeText(getBaseContext(),
						AlertMessage.MESSAGE_INVALID_DATA, Toast.LENGTH_LONG)
						.show();
			}

			break;
		}
	}

	public void onActivityResult(int requestCode, int resultCode, Intent data) {

		if (requestCode == POPUP_SELECT) {

			if (resultCode == RESULT_OK) {
				img1 = "";
				img2 = "";
				img3 = "";
				img1 = data.getStringExtra(CommonString.KEY_IMAGE_PATH1);
				img2 = data.getStringExtra(CommonString.KEY_IMAGE_PATH2);
				img3 = data.getStringExtra(CommonString.KEY_IMAGE_PATH3);
				if (img1.equals("") && img2.equals("") && img3.equals("")) {

					
				} else {
					camera.setBackgroundResource(R.drawable.camera_list_tick);

				}

			}

		}

		if (requestCode == ADD_SKU) {

			if (resultCode == RESULT_OK) {

				companyspinner.setEnabled(false);
				displayspinner.setEnabled(false);
				// img3 = data.getStringExtra(CommonString.KEY_IMAGE_PATH3);
			}

		}

	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		if (mKeyboardView.getVisibility() == View.VISIBLE) {
			mKeyboardView.setVisibility(View.INVISIBLE);
		} else {
			database.open();
			database.delete_temp();
			img1 = "";
			img2 = "";
			img3 = "";
			Intent i = new Intent(this, DailyentryMenuActivity.class);
			startActivity(i);
			SodMasterActivity.this.finish();
		}
	}

	private class MyAdapter extends BaseAdapter {
		private LayoutInflater mInflater;
		private Context mcontext;

		public MyAdapter(Context context) {
			mInflater = LayoutInflater.from(context);
			mcontext = context;
		}

		public int getCount() {
			return sku_list.size();

		}

		public Object getItem(int position) {
			return position;
		}

		public long getItemId(int position) {
			return position;
		}

		public View getView(final int position, View convertView,
				ViewGroup parent) {
			final ViewHolder holder;
			if (convertView == null) {

				convertView = mInflater.inflate(R.layout.sod_listview, null);
				holder = new ViewHolder();

				holder.sku = (TextView) convertView.findViewById(R.id.sku);
				holder.posm = (TextView) convertView.findViewById(R.id.posm);
				holder.l = (TextView) convertView.findViewById(R.id.l);
				holder.h = (TextView) convertView.findViewById(R.id.h);
				holder.w = (TextView) convertView.findViewById(R.id.w);
				holder.counter=(TextView)convertView.findViewById(R.id.counter);
				holder.del = (ImageView) convertView.findViewById(R.id.delete);
				holder.editcamera = (ImageView) convertView
						.findViewById(R.id.editcamera);

				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}

			holder.sku.setText(sku_list.get(position).getCompanyname()
					.toString());
			holder.posm.setText(sku_list.get(position).getPosm().toString());
			holder.l.setText(sku_list.get(position).getL().toString());
			holder.h.setText(sku_list.get(position).getH().toString());
			holder.w.setText(sku_list.get(position).getB().toString());
			holder.counter.setText(sku_list.get(position).getCounter());

			holder.del.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
					final AlertDialog.Builder alertDialog = new AlertDialog.Builder(
							SodMasterActivity.this);
					alertDialog.setMessage("Do you want to delete ");
					alertDialog.setPositiveButton("YES",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int which) {

									database.open();

									database.DeleteSodData(Integer
											.parseInt(sku_list.get(position)
													.getId()));

									sku_list = database.getSodData(store_id);
									lv.invalidateViews();

								}
							});
					alertDialog.setNegativeButton("NO",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int which) {
									// Write your code here to invoke NO event
									dialog.cancel();
								}
							});
					alertDialog.show();
				}
			});

			holder.editcamera.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
					Intent intent = new Intent(getApplicationContext(),
							EditImagePepsicoActivity.class);
					intent.putExtra("image1path", sku_list.get(position)
							.getImg1());
					intent.putExtra("image2path", sku_list.get(position)
							.getImg2());
					intent.putExtra("image3path", sku_list.get(position)
							.getImg3());
					startActivity(intent);

				}
			});

			holder.del.setId(position);
			return convertView;
		}

		class ViewHolder {
			TextView sku, posm, l, h, w,counter;
			ImageView del, editcamera;

		}

	}

	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int position,
			long arg3) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.sku:
			if (position > 0) {

				companyname = company_list.get(position - 1).getCompany();
				company_id = company_list.get(position - 1).getCompanyid();

				img1 = "";
				img2 = "";
				img3 = "";
				camera.setBackgroundResource(R.drawable.camera_list);

			} else {
				companyname = null;
				company_id = null;
			}
			break;
		case R.id.display:
			if (position > 0) {

				posmname = displaylist.get(position - 1).getPosm();
				posmid = displaylist.get(position - 1).getPosmid();

				img1 = "";
				img2 = "";
				img3 = "";
				camera.setBackgroundResource(R.drawable.camera_list);

			} else {
				posmname = null;
				posmid = null;
			}
			break;
		}

	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub

	}

	public long getMid() {

		long mid = 0;

		mid = database.CheckMid(visit_date, store_id);

		if (mid == 0) {
			CoverageBean cdata = new CoverageBean();
			cdata.setStoreId(store_id);
			cdata.setVisitDate(visit_date);
			cdata.setUserId(username);
			cdata.setInTime(intime);
			cdata.setOutTime(getCurrentTime());
			cdata.setReason("");
			cdata.setReasonid("0");
			cdata.setLatitude("0.0");
			cdata.setLongitude("0.0");
			mid = database.InsertCoverageData(cdata);

		}

		return mid;
	}

	public String getCurrentTime() {

		Calendar m_cal = Calendar.getInstance();

		String intime = m_cal.get(Calendar.HOUR_OF_DAY) + ":"
				+ m_cal.get(Calendar.MINUTE) + ":" + m_cal.get(Calendar.SECOND);

		return intime;

	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		mKeyboardView.setVisibility(View.GONE);
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		// TODO Auto-generated method stub
		super.onConfigurationChanged(newConfig);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		mKeyboardView.setKeyboard(mKeyboard);
		mKeyboardView.setVisibility(View.INVISIBLE);
	}

}
