package com.cpm.dailyEntry;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.Color;
import android.inputmethodservice.Keyboard;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.cpm.Constants.CommonString;
import com.cpm.database.PepsicoDatabase;
import com.cpm.delegates.CoverageBean;
import com.cpm.delegates.NonSkuBean;
import com.cpm.delegates.VericalBrandBean;
import com.cpm.keyboard.BasicOnKeyboardActionListener;
import com.cpm.keyboard.CustomKeyboardView;
import com.cpm.parinaampepsicoo.R;

public class ooseVerticalFood extends Activity {
	
	

	public String store_name;
	 public ListView lv;
	 int trigger = 0;
	public static int spinnerposition;
	static String selectedreasonid;
	static String selectedreason;
	public static int pos[], pos1[], pos2[], pos3[], pos4[], pos5[], pos6[];
	public Keyboard mKeyboard;
	private static ArrayAdapter<CharSequence> nonskuadapter;
	public static CustomKeyboardView mKeyboardView;
	private String store_id, username, intime, brand_id, brand_name,
			visit_date;
	private SharedPreferences preferences;
	private Bundle extras;
	static PepsicoDatabase database;
	public final static int DATE_DIALOG_ID = 0;
	public final static int DATE_DIALOG_ID1 = 1;
	public final static int DATE_DIALOG_ID2 = 2;
	private static ArrayList<VericalBrandBean> sku_list = new ArrayList<VericalBrandBean>();
	private static ArrayList<NonSkuBean> nonskureason = new ArrayList<NonSkuBean>();
	static int row_pos= 10000;
	private static int myear;
	private static int month;
	private static int day;
	static String date = "";
	static int date_position;
	static int spinner_position;
	static String date_num = "0";
	public static int enable[];
	Calendar c;
	static int currentapiVersion = 1;
	boolean ok = false;
	AlertDialog alert;
	boolean enter_all = false;
	List<String> list;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.newentry1);
		 list=new ArrayList<String>();
	    list.add("select stock");
	    list.add("zero");
	    list.add("less than 10");
	    list.add("more than 10");
	    

		lv = (ListView) findViewById(R.id.listNewEntry);

		currentapiVersion = android.os.Build.VERSION.SDK_INT;

		preferences = PreferenceManager.getDefaultSharedPreferences(this);
		store_name = preferences.getString(CommonString.KEY_STORE_NAME, "");
		store_id = preferences.getString(CommonString.KEY_STORE_ID, "");
		brand_id = preferences.getString(CommonString.KEY_BRAND_ID, "");
		brand_name = preferences.getString(CommonString.KEY_BRAND_NAME, "");
		visit_date = preferences.getString(CommonString.KEY_DATE, "");

		TextView text1 = (TextView) findViewById(R.id.mainpage_remembermetext);
		text1.setText(store_name + " - " + brand_name);

		c = Calendar.getInstance();
		myear = c.get(Calendar.YEAR);
		month = c.get(Calendar.MONTH);
		day = c.get(Calendar.DAY_OF_MONTH);

		// KeyboardView kbd = new KeyboardView(SkuList.this);

		database = new PepsicoDatabase(this);
		database.open();

		intime = getCurrentTime();

		sku_list = database.getSkuMappingData121_forFood(store_id);
		//sku_list.remove(0);
	

//		pos = new int[sku_list.size()];
//
//		for (int i = 0; i < sku_list.size(); i++) {
//
//			pos[i] = -1;
//			sku_list.get(i).setSkuqty("");
//
//		}
//
//		pos1 = new int[sku_list.size()];
//
//		for (int i = 0; i < sku_list.size(); i++) {
//
//			pos1[i] = -1;
//			sku_list.get(i).setSkuqty1("");
//
//		}
//
//		pos2 = new int[sku_list.size()];
//
//		for (int i = 0; i < sku_list.size(); i++) {
//
//			pos2[i] = -1;
//			sku_list.get(i).setSkuqty2("");
//
//		}
//
//		pos3 = new int[sku_list.size()];
//
//		for (int i = 0; i < sku_list.size(); i++) {
//
//			pos3[i] = -1;
//			sku_list.get(i).setDom1("1/1/1900");
//
//		}
//
//		pos4 = new int[sku_list.size()];
//
//		for (int i = 0; i < sku_list.size(); i++) {
//
//			pos4[i] = -1;
//			sku_list.get(i).setDom2("1/1/1900");
//
//		}
//
//		pos5 = new int[sku_list.size()];
//
//		for (int i = 0; i < sku_list.size(); i++) {
//
//			pos5[i] = -1;
//			sku_list.get(i).setDom3("1/1/1900");
//
//		}
		
		pos5 = new int[sku_list.size()];

		for (int i = 0; i < sku_list.size(); i++) {

			pos5[i] = -1;
			sku_list.get(i).setReason("select stock");

		}
		
		pos6 = new int[sku_list.size()];
		for (int i = 0; i < sku_list.size(); i++) {

			pos6[i] = -1;
			sku_list.get(i).setReasonid("");
			sku_list.get(i).setReason_position(0);

		}
		enable = new int[sku_list.size()];
		for (int i = 0; i < sku_list.size(); i++) {

			enable[i] = -1;

		}

		mKeyboard = new Keyboard(ooseVerticalFood.this, R.xml.keyboard);
		mKeyboardView = (CustomKeyboardView) findViewById(R.id.keyboard_view);
		mKeyboardView.setKeyboard(mKeyboard);

		mKeyboardView
				.setOnKeyboardActionListener(new BasicOnKeyboardActionListener(
						this, 2));

		lv.setAdapter(new MyAdapter(this));

		lv.setOnScrollListener(new OnScrollListener() {
			@Override
			public void onScroll(AbsListView view, int firstVisibleItem,
					int visibleItemCount, int totalItemCount) {

			}

			@Override
			public void onScrollStateChanged(AbsListView arg0, int arg1) {

				lv.invalidateViews();
			}

		});

	}

	private class MyAdapter extends BaseAdapter {
		private LayoutInflater mInflater;
		 Context mcontext;

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

		public View getView(int position, View convertView, ViewGroup parent) {
			final ViewHolder holder;
			if (convertView == null) {
				convertView = mInflater.inflate(
						R.layout.newlayoutadapter, null);
				holder = new ViewHolder();

				holder.layoutcolor=(LinearLayout)convertView.findViewById(R.id.firstrow);
				
				holder.sku = (TextView) convertView
						.findViewById(R.id.focusskulistview_1c_titletext);
				holder.skureason = (Spinner) convertView
						.findViewById(R.id.spinner1);
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}



			ArrayAdapter<String> adp1=new ArrayAdapter<String>(mcontext,
                    android.R.layout.simple_list_item_1,list);
    adp1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    holder.skureason.setAdapter(adp1);
    holder.skureason.setSelection(sku_list.get(position).getReason_position());
    holder.skureason
	.setOnItemSelectedListener(new spnlistner(position));
	holder.sku.setText(sku_list.get(position).getSkuname().toString());
	
		if(trigger==2){
		if(sku_list.get(position).getReason().equalsIgnoreCase("select stock"))
		{
			
			
			holder.layoutcolor.setBackgroundColor(Color.rgb(255, 127, 127));
		}else{
			holder.layoutcolor.setBackgroundColor(Color.WHITE);
		}
		
		}
			return convertView;
		}

		 class ViewHolder {
			TextView sku;
			Spinner skureason;
			LinearLayout layoutcolor;
		}

		private class spnlistner implements OnItemSelectedListener {
			private int position;
			private String num;

			spnlistner(int position) {
				this.position = position;
				// this.num = num;

			}

			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {

				spinner_position = position;

				if (arg2 == 0) {

					selectedreasonid = "-1";
					selectedreason = "select stock";

				} else {

					selectedreasonid = list.get(arg2 - 1);
							
					selectedreason = list.get(arg2);

				}

				if (selectedreason.equals("select stock")) {
					pos6[position] = -1;
					sku_list.get(spinner_position).setReasonid("");
					sku_list.get(spinner_position).setReason("select stock");
					sku_list.get(spinner_position).setReason_position(arg2);

				} else {
					pos6[position] = 0;
					sku_list.get(spinner_position)
							.setReasonid(selectedreasonid);
					sku_list.get(spinner_position).setReason_position(arg2);
					sku_list.get(spinner_position).setReason(selectedreason);
				}

			}

			public void onNothingSelected(AdapterView<?> arg0) {

			}

		}
	}


	public boolean condition() {
		boolean result = true;

		for (int i = 0; i < sku_list.size(); i++) {
			if (!sku_list.get(i).getSkuqty().equalsIgnoreCase("")
					&& !sku_list.get(i).getSkuqty1().equalsIgnoreCase("")) {
				if (Integer.parseInt(sku_list.get(i).getSkuqty1()) > (Integer
						.parseInt(sku_list.get(i).getSkuqty()))) {
					row_pos = i + 1;
					result = false;
					break;
				}
			}

		}

		return result;
	}

	public boolean validate_data() {
		boolean result = false;
		enter_all = false;

		for (int i = 0; i < sku_list.size(); i++) {

			if (!sku_list.get(i).getSkuqty().equalsIgnoreCase("")) {

				if (sku_list.get(i).getSkuqty().equalsIgnoreCase("0")) {
					if (sku_list.get(i).getReason()
							.equalsIgnoreCase("select stock")) {
						result = true;
						row_pos = i + 1;
						break;

					}

				} else if (!sku_list.get(i).getSkuqty().equalsIgnoreCase("0")
						&& !sku_list.get(i).getSkuqty().equalsIgnoreCase("")) {
					if (sku_list.get(i).getSkuqty1().equalsIgnoreCase("")
							|| sku_list.get(i).getSkuqty2()
									.equalsIgnoreCase("")) {
						row_pos = i + 1;
						result = true;
						break;
					}
					if (sku_list.get(i).getDom1().equalsIgnoreCase("1/1/1900")
							&& sku_list.get(i).getDom2()
									.equalsIgnoreCase("1/1/1900")
							&& sku_list.get(i).getDom3()
									.equalsIgnoreCase("1/1/1900")) {
						row_pos = i + 1;
						result = true;
						break;
					}

				} else if (sku_list.get(i).getSkuqty().equalsIgnoreCase("")) {
					if (!sku_list.get(i).getSkuqty1().equalsIgnoreCase("")
							|| !sku_list.get(i).getSkuqty2()
									.equalsIgnoreCase("")
							|| (!sku_list.get(i).getDom1()
									.equalsIgnoreCase("1/1/1900")
									|| !sku_list.get(i).getDom2()
											.equalsIgnoreCase("1/1/1900") || !sku_list
									.get(i).getDom3()
									.equalsIgnoreCase("1/1/1900"))) {
						
						row_pos = i + 1;
						result = true;
						break;
					} else {

						result = false;
						// break;

					}
				}
			} else {
				row_pos = i + 1;
				result = true;
				enter_all = true;
				break;
			}
		}

		return result;

	}

	public boolean check_condition() {
		boolean result = true;

		for (int i = 0; i < sku_list.size(); i++) {

			if (sku_list.get(i).getSkuqty().equalsIgnoreCase("")
					&& sku_list.get(i).getSkuqty1().equalsIgnoreCase("")
					&& sku_list.get(i).getSkuqty2().equalsIgnoreCase("")
					&& sku_list.get(i).getDom1().equalsIgnoreCase("1/1/1900")
					&& sku_list.get(i).getDom2().equalsIgnoreCase("1/1/1900")
					&& sku_list.get(i).getDom3().equalsIgnoreCase("1/1/1900")) {

				result = true;

			} else {
				result = false;
				break;
			}
		}

		return result;

	}

	public void onButtonClick(View v) {
		switch (v.getId()) {
		case R.id.training_intellogo:

			Intent intent = new Intent(this, ShareOfShelfActivity.class);

			startActivity(intent);

			ooseVerticalFood.this.finish();

			break;

		case R.id.save:

			row_pos = 10000;
			for(int i=0;i<sku_list.size();i++){

				if(sku_list.get(i).getReason()!=null){
					if((sku_list.get(i).getReason().toString().equalsIgnoreCase("select stock"))){
						Toast.makeText(getApplicationContext(), "please enter the data", 100).show();
						trigger=2;
						lv.invalidateViews();
						return;
					}	
				}else{
					Toast.makeText(getApplicationContext(), "please enter the data", 100).show();
					trigger=2;
					lv.invalidateViews();
					return;
				}
			
			}
		
		//	else {

//				if (validate_data()) {
//					if (enter_all) {
//						lv.invalidateViews();
//			
//						Toast.makeText(getApplicationContext(), "Enter Data in all SkU At Row = "
//								+ row_pos,
//								Toast.LENGTH_SHORT).show();
//						
//					} else {
//
//						lv.invalidateViews();
//						Toast.makeText(getApplicationContext(), "Invalid Data At Row = "
//										+ row_pos,
//								Toast.LENGTH_SHORT).show();
//						
//					}
//				}
		//		else {

				//	if (condition()) {
						AlertDialog.Builder builder = new AlertDialog.Builder(
								ooseVerticalFood.this);
						alert = builder.create();

						builder.setMessage("Do you want to save the data for Food ")
								.setCancelable(false)
								.setPositiveButton("OK",
										new DialogInterface.OnClickListener() {
											public void onClick(
													DialogInterface dialog,
													int id) {

												alert.getButton(
														AlertDialog.BUTTON_POSITIVE)
														.setEnabled(false);

												
											
												row_pos = 10000;
												database.open();

												database.InsertSosPepsiDataOOS(
														getMid(), store_id,
														sku_list, brand_id,
														CommonString.KEY_pepsi,visit_date);

												
												
												
//												if (database.CheckBrandid(
//														brand_id, store_id,CommonString.KEY_pepsi) == 0) {
//													database.InsertBrandCheck(
//															brand_id, store_id,CommonString.KEY_pepsi);
//												}
												SharedPreferences.Editor editor = preferences
														.edit();
												editor.putString(
														"Cateogry_Edit", "Y");
												editor.commit();

												Intent i = new Intent(
														getApplicationContext(),
														DailyentryMenuActivity.class);
												startActivity(i);
												ooseVerticalFood.this.finish();

											}
										})
								.setNegativeButton("Cancel",
										new DialogInterface.OnClickListener() {
											public void onClick(
													DialogInterface dialog,
													int id) {
												dialog.cancel();
											}
										});

						alert = builder.create();
						alert.show();
						

				//	} 
//					else {
//						
//						lv.invalidateViews();
//						Toast.makeText(
//								getBaseContext(),
//								"Invalid Faceup, Faceup Should Be Less Than The Stock At Row = "
//										+ row_pos, Toast.LENGTH_LONG).show();
//						
//					}

		//		}

		//	}

			break;
		}
	}

	public String getCurrentTime() {

		Calendar m_cal = Calendar.getInstance();

		String intime = m_cal.get(Calendar.HOUR_OF_DAY) + ":"
				+ m_cal.get(Calendar.MINUTE) + ":" + m_cal.get(Calendar.SECOND);

		return intime;

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

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub

		if (mKeyboardView.getVisibility() == View.VISIBLE) {
			mKeyboardView.setVisibility(View.INVISIBLE);
			mKeyboardView.requestFocusFromTouch();
		} else {

			SharedPreferences.Editor editor = preferences.edit();

			editor.putString("Cateogry_Edit", "Y");

			editor.commit();
			
			row_pos = 10000;

			Intent intent = new Intent(this, DailyentryMenuActivity.class);

			startActivity(intent);

			ooseVerticalFood.this.finish();
		}

	}

	/*
	 * @Override protected void onStop() { // TODO Auto-generated method stub
	 * super.onStop(); database.close(); }
	 */

	public void hide() {
		mKeyboardView.setVisibility(View.INVISIBLE);
		// mKeyboardView.clearFocus();
		mKeyboardView.requestFocusFromTouch();

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
