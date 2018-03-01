package com.cpm.dailyEntry;

import java.util.ArrayList;
import java.util.Calendar;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.inputmethodservice.Keyboard;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
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
import com.cpm.message.AlertMessage;
import com.cpm.parinaampepsicoo.R;

public class EditSkuList extends Activity {

	public String store_name;
	public ListView lv;
	public static int spinnerposition;
	static String selectedreasonid;
	static String selectedreason;
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

	private static int myear;
	private static int month;
	private static int day;
	static String date = "";
	static int date_position;
	static int spinner_position;
	static String date_num = "0";
	static int currentapiVersion = 1;
	AlertDialog alert;
	Calendar c;
	static int row_pos= 10000;
	boolean enter_all = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.new_sos_pepsi);

		lv = (ListView) findViewById(R.id.list);

		currentapiVersion = android.os.Build.VERSION.SDK_INT;

		Button update = (Button) findViewById(R.id.save);
		update.setText("Update");

		preferences = PreferenceManager.getDefaultSharedPreferences(this);
		store_name = preferences.getString(CommonString.KEY_STORE_NAME, "");
		store_id = preferences.getString(CommonString.KEY_STORE_ID, "");
		brand_id = preferences.getString(CommonString.KEY_BRAND_ID, "");
		brand_name = preferences.getString(CommonString.KEY_BRAND_NAME, "");
		visit_date = preferences.getString(CommonString.KEY_VISIT_DATE, "");

		TextView text1 = (TextView) findViewById(R.id.mainpage_remembermetext);
		text1.setText(store_name + " - " + brand_name);

		c = Calendar.getInstance();
		myear = c.get(Calendar.YEAR);
		month = c.get(Calendar.MONTH);
		day = c.get(Calendar.DAY_OF_MONTH);

		// KeyboardView kbd = new KeyboardView(SkuList.this);

		database = new PepsicoDatabase(this);
		database.open();

		nonskureason = database.getNonSkuReasonData();

		intime = getCurrentTime();

		sku_list = database.getSosEditData(store_id, brand_id,
				CommonString.KEY_pepsi);

		for (int i = 0; i < sku_list.size(); i++) {

			sku_list.get(i).setReason_position(0);

		}

		for (int i = 0; i < sku_list.size(); i++) {

			if (sku_list.get(i).getDom1().equals("1/1/1900")) {
				sku_list.get(i).setDom1("MFD");

			}

			if (sku_list.get(i).getDom2().equals("1/1/1900")) {
				sku_list.get(i).setDom2("MFD");

			}

			if (sku_list.get(i).getDom3().equals("1/1/1900")) {
				sku_list.get(i).setDom3("MFD");

			}

			sku_list.get(i).setReason("");

		}

		for (int i = 0; i < sku_list.size(); i++) {
			if (!sku_list.get(i).getReasonid().equals("0")) {

				for (int j = 0; j < nonskureason.size(); j++) {
					if (sku_list.get(i).getReasonid()
							.equals(nonskureason.get(j).getSkureaonid())) {
						sku_list.get(i).setReason_position(j + 1);
					}
				}

			} else {
				sku_list.get(i).setReason_position(0);
			}
		}

		mKeyboard = new Keyboard(EditSkuList.this, R.xml.keyboard);
		mKeyboardView = (CustomKeyboardView) findViewById(R.id.keyboard_view);
		mKeyboardView.setKeyboard(mKeyboard);

		mKeyboardView
				.setOnKeyboardActionListener(new BasicOnKeyboardActionListener(
						this, 1));

		lv.setAdapter(new MyAdapter(this));

		lv.setOnScrollListener(new OnScrollListener() {
			@Override
			public void onScroll(AbsListView view, int firstVisibleItem,
					int visibleItemCount, int totalItemCount) {

			}

			@Override
			public void onScrollStateChanged(AbsListView arg0, int arg1) {
				// TODO Auto-generated method stub
				lv.invalidateViews();
			}

		});

	}

	private static class MyAdapter extends BaseAdapter {
		private LayoutInflater mInflater;
		static Context mcontext;

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
						R.layout.new_pepsifocusskulistview, null);
				holder = new ViewHolder();

				holder.sku = (TextView) convertView
						.findViewById(R.id.focusskulistview_1c_titletext);
				holder.sku_text1 = (EditText) convertView
						.findViewById(R.id.focusskulistview_edit1);
				holder.sku_text2 = (EditText) convertView
						.findViewById(R.id.focusskulistview_edit2);
				holder.sku_text3 = (EditText) convertView
						.findViewById(R.id.focusskulistview_edit3);
				holder.skureason = (Spinner) convertView
						.findViewById(R.id.spinner1);

				holder.dom1 = (Button) convertView.findViewById(R.id.dom1);

				holder.dom2 = (Button) convertView.findViewById(R.id.dom2);

				holder.dom3 = (Button) convertView.findViewById(R.id.dom3);
				holder.layout = (LinearLayout) convertView.findViewById(R.id.focusskulistview_chartcontentarea);
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}

			if (currentapiVersion >= 11) {
				holder.sku_text1.setTextIsSelectable(true);
				holder.sku_text2.setTextIsSelectable(true);
				holder.sku_text3.setTextIsSelectable(true);
				holder.sku_text1.setRawInputType(InputType.TYPE_CLASS_TEXT);
				holder.sku_text2.setRawInputType(InputType.TYPE_CLASS_TEXT);
				holder.sku_text3.setRawInputType(InputType.TYPE_CLASS_TEXT);
			} else {
				holder.sku_text1.setInputType(0);
				holder.sku_text2.setInputType(0);
				holder.sku_text3.setInputType(0);

			}
			
			if(position == row_pos-1)
			{
				
				holder.sku.setBackgroundColor(mcontext.getResources().getColor(R.color.red));
				holder.layout.setBackgroundColor(mcontext.getResources().getColor(R.color.red));
			}
			else
			{
				holder.sku.setBackgroundColor(mcontext.getResources().getColor(R.color.light));
				holder.layout.setBackgroundColor(mcontext.getResources().getColor(R.color.light));
			}
			
			nonskuadapter = new ArrayAdapter<CharSequence>(mcontext,
					android.R.layout.simple_spinner_item);

			nonskuadapter.add("Select Reason");
			
			

			nonskuadapter
					.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

			holder.sku.setText(sku_list.get(position).getSkuname().toString());

			holder.sku_text1.setOnTouchListener(new View.OnTouchListener() {

				@Override
				public boolean onTouch(View v, MotionEvent event) {

					if (mKeyboardView.getVisibility() == View.INVISIBLE) {

						mKeyboardView.setVisibility(View.VISIBLE);
					}
					// TODO Auto-generated method stub

					return false;
				}
			});
			
			holder.sku_text3.setText("0");


			holder.sku_text2.setOnTouchListener(new View.OnTouchListener() {

				@Override
				public boolean onTouch(View v, MotionEvent event) {
					// TODO Auto-generated method stub

					if (mKeyboardView.getVisibility() == View.INVISIBLE) {
						mKeyboardView.setVisibility(View.VISIBLE);
					}
					return false;
				}
			});

			holder.sku_text3.setOnTouchListener(new View.OnTouchListener() {

				@Override
				public boolean onTouch(View v, MotionEvent event) {
					// TODO Auto-generated method stub
					if (mKeyboardView.getVisibility() == View.INVISIBLE) {
						mKeyboardView.setVisibility(View.VISIBLE);
					}
					return false;
				}
			});

			for (int i = 0; i < nonskureason.size(); i++) {
				nonskuadapter.add(nonskureason.get(i).getSkureason());
			}

			holder.skureason.setAdapter(nonskuadapter);

			holder.sku_text1.setText(sku_list.get(position).getSkuqty());
			holder.sku_text2.setText(sku_list.get(position).getSkuqty1());
			holder.sku_text3.setText(sku_list.get(position).getSkuqty2());
			holder.dom1.setText(sku_list.get(position).getDom1());
			holder.dom2.setText(sku_list.get(position).getDom2());
			holder.dom3.setText(sku_list.get(position).getDom3());

			holder.sku_text1
					.setOnFocusChangeListener(new OnFocusChangeListener() {
						public void onFocusChange(View v, boolean hasFocus) {

							if (!hasFocus) {
								final int position = v.getId();
								final EditText Caption = (EditText) v;
								String value1 = Caption.getText().toString();

								if (value1.equals("")) {

									sku_list.get(position).setSkuqty("");
									holder.dom1.setEnabled(true);
									holder.dom2.setEnabled(true);
									holder.dom3.setEnabled(true);
									holder.sku_text2.setEnabled(true);
									holder.sku_text3.setEnabled(true);
									holder.skureason.setEnabled(false);

								} else {

									sku_list.get(position).setSkuqty(value1);
									if (value1.equalsIgnoreCase("0")) {
										holder.skureason.setEnabled(true);
										holder.dom1.setEnabled(false);
										holder.dom2.setEnabled(false);
										holder.dom3.setEnabled(false);
										holder.dom1.setText("MFD");
										holder.dom2.setText("MFD");
										holder.dom3.setText("MFD");
										holder.sku_text2.setText("");
										holder.sku_text3.setText("");
										holder.sku_text2.setEnabled(false);
										sku_list.get(position).setSkuqty1("");
										sku_list.get(position).setSkuqty2("");
										sku_list.get(position).setDom1("MFD");
										sku_list.get(position).setDom2("MFD");
										sku_list.get(position).setDom3("MFD");

										holder.sku_text3.setEnabled(false);

									} else {
										holder.skureason.setEnabled(false);
										holder.skureason.setSelection(0);
										holder.dom1.setEnabled(true);
										holder.dom2.setEnabled(true);
										holder.dom3.setEnabled(true);
										holder.sku_text2.setEnabled(true);
										holder.sku_text3.setEnabled(true);
										sku_list.get(position).setReasonid("0");
										sku_list.get(position)
												.setReason_position(0);

									}
								}

							}
						}
					});

			holder.sku_text2
					.setOnFocusChangeListener(new OnFocusChangeListener() {
						public void onFocusChange(View v, boolean hasFocus) {

							if (!hasFocus) {
								final int position = v.getId();
								final EditText Caption = (EditText) v;
								String value1 = Caption.getText().toString();

								if (value1.equals("")) {

									sku_list.get(position).setSkuqty1("");

								} else {

									sku_list.get(position).setSkuqty1(value1);
								}

							}
						}
					});

			holder.sku_text3
					.setOnFocusChangeListener(new OnFocusChangeListener() {
						public void onFocusChange(View v, boolean hasFocus) {

							if (!hasFocus) {

								final int position = v.getId();
								final EditText Caption = (EditText) v;
								String value1 = Caption.getText().toString();

								if (value1.equals("")) {

									sku_list.get(position).setSkuqty2("");

								} else {

									sku_list.get(position).setSkuqty2(value1);

								}

							}

						}
					});

			holder.skureason.setSelection(sku_list.get(position)
					.getReason_position());

			if (sku_list.get(position).getSkuqty().equalsIgnoreCase("0")) {
				holder.skureason.setEnabled(true);
				holder.dom1.setEnabled(false);
				holder.dom2.setEnabled(false);
				holder.dom3.setEnabled(false);
				holder.sku_text2.setEnabled(false);
				holder.sku_text3.setEnabled(false);

			} else {
				holder.skureason.setEnabled(false);
				holder.dom1.setEnabled(true);
				holder.dom2.setEnabled(true);
				holder.dom3.setEnabled(true);
				holder.sku_text2.setEnabled(true);
				holder.sku_text3.setEnabled(true);
			}

			holder.sku_text1.setId(position);
			holder.sku_text2.setId(position);
			holder.sku_text3.setId(position);

			holder.skureason
					.setOnItemSelectedListener(new spnlistner(position));
			holder.dom1.setId(position);
			holder.dom2.setId(position);
			holder.dom3.setId(position);
			holder.dom1.setOnClickListener(new MyClickListener(position, "1"));
			holder.dom2.setOnClickListener(new MyClickListener(position, "2"));
			holder.dom3.setOnClickListener(new MyClickListener(position, "3"));
			return convertView;
		}

		static class ViewHolder {
			TextView sku;
			EditText sku_text1, sku_text2, sku_text3;
			Button dom1, dom2, dom3;
			Spinner skureason;
			LinearLayout layout;
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
					selectedreason = "Select Reason";

				} else {

					selectedreasonid = nonskureason.get(arg2 - 1)
							.getSkureaonid();
					selectedreason = nonskureason.get(arg2 - 1).getSkureason();

				}

				if (selectedreason.equals("Select Reason")) {

					sku_list.get(spinner_position).setReasonid("0");
					sku_list.get(spinner_position).setReason("Select Reason");
					sku_list.get(spinner_position).setReason_position(arg2);

				} else {

					sku_list.get(spinner_position)
							.setReasonid(selectedreasonid);
					sku_list.get(spinner_position).setReason_position(arg2);
					sku_list.get(spinner_position).setReason(selectedreason);
				}

			}

			public void onNothingSelected(AdapterView<?> arg0) {

			}

		}

		private class MyClickListener implements OnClickListener {
			private int position;
			private String num;

			MyClickListener(int position, String num) {
				this.position = position;
				this.num = num;

			}

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				date_position = position;
				date_num = num;

				if (num.equals("1")) {

					((Activity) MyAdapter.this.mcontext)
							.removeDialog(DATE_DIALOG_ID);

					((Activity) MyAdapter.this.mcontext)
							.showDialog(DATE_DIALOG_ID);
				}
				if (num.equals("2")) {

					((Activity) MyAdapter.this.mcontext)
							.removeDialog(DATE_DIALOG_ID1);

					((Activity) MyAdapter.this.mcontext)
							.showDialog(DATE_DIALOG_ID1);
				}
				if (num.equals("3")) {

					((Activity) MyAdapter.this.mcontext)
							.removeDialog(DATE_DIALOG_ID2);

					((Activity) MyAdapter.this.mcontext)
							.showDialog(DATE_DIALOG_ID2);
				}

			}

		}
	}

	private DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {

		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {
			myear = year;
			month = monthOfYear;
			day = dayOfMonth;
			updateDisplay();
		}
	};

	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case DATE_DIALOG_ID:

			c = Calendar.getInstance();
			myear = c.get(Calendar.YEAR);
			month = c.get(Calendar.MONTH);
			day = c.get(Calendar.DAY_OF_MONTH);

			DatePickerDialog d = new DatePickerDialog(this, mDateSetListener,
					myear, month, day);
			c.getTimeInMillis();

			if (currentapiVersion >= 11) {
				d.getDatePicker().setMaxDate(c.getTimeInMillis());
			}

			return d;

		case DATE_DIALOG_ID1:

			c = Calendar.getInstance();
			myear = c.get(Calendar.YEAR);
			month = c.get(Calendar.MONTH);
			day = c.get(Calendar.DAY_OF_MONTH);

			DatePickerDialog d1 = new DatePickerDialog(this, mDateSetListener,
					myear, month, day);
			c.getTimeInMillis();

			if (currentapiVersion >= 11) {
				d1.getDatePicker().setMaxDate(c.getTimeInMillis());
			}

			return d1;

		case DATE_DIALOG_ID2:

			c = Calendar.getInstance();
			myear = c.get(Calendar.YEAR);
			month = c.get(Calendar.MONTH);
			day = c.get(Calendar.DAY_OF_MONTH);

			DatePickerDialog d2 = new DatePickerDialog(this, mDateSetListener,
					myear, month, day);
			c.getTimeInMillis();

			if (currentapiVersion >= 11) {
				d2.getDatePicker().setMaxDate(c.getTimeInMillis());
			}

			return d2;

		}
		return null;
	}

	private void updateDisplay() {

		date = "";
		int mon = month + 1;
		date = "" + mon + "/" + day + "/" + myear;

		if (date_num.equals("1")) {

			sku_list.get(date_position).setDom1(date);
			lv.invalidateViews();
		}
		if (date_num.equals("2")) {

			sku_list.get(date_position).setDom2(date);
			lv.invalidateViews();
		}
		if (date_num.equals("3")) {
			sku_list.get(date_position).setDom3(date);
			lv.invalidateViews();
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

		for (int i = 0; i < sku_list.size(); i++) {
			//sku_list.get(i).setSkuqty2(skuqty2);
			if (!sku_list.get(i).getSkuqty().equalsIgnoreCase("")) {
			if (sku_list.get(i).getSkuqty().equalsIgnoreCase("0")) {
				if (sku_list.get(i).getReason()
						.equalsIgnoreCase("Select Reason")) {
					row_pos = i + 1;
					result = true;
					break;

				}

			} else if (!sku_list.get(i).getSkuqty().equalsIgnoreCase("0")
					&& !sku_list.get(i).getSkuqty().equalsIgnoreCase("")) {
				if (sku_list.get(i).getSkuqty1().equalsIgnoreCase("")
						|| sku_list.get(i).getSkuqty2().equalsIgnoreCase("")) {
					row_pos = i + 1;
					result = true;
					break;
				}
				if (sku_list.get(i).getDom1().equalsIgnoreCase("MFD")
						&& sku_list.get(i).getDom2().equalsIgnoreCase("MFD")
						&& sku_list.get(i).getDom3().equalsIgnoreCase("MFD")) {
					
					row_pos = i + 1;
					result = true;
					break;
				}

			} else if (sku_list.get(i).getSkuqty().equalsIgnoreCase("")) {
				if (!sku_list.get(i).getSkuqty1().equalsIgnoreCase("")
						|| !sku_list.get(i).getSkuqty2().equalsIgnoreCase("")
						|| (!sku_list.get(i).getDom1().equalsIgnoreCase("MFD")
								|| !sku_list.get(i).getDom2()
										.equalsIgnoreCase("MFD") || !sku_list
								.get(i).getDom3().equalsIgnoreCase("MFD"))) {
					row_pos = i + 1;
					result = true;
					break;
				} else {

					result = false;

				}
			}

		}
			else {
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
					&& sku_list.get(i).getDom1().equalsIgnoreCase("MFD")
					&& sku_list.get(i).getDom2().equalsIgnoreCase("MFD")
					&& sku_list.get(i).getDom3().equalsIgnoreCase("MFD")) {

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

			EditSkuList.this.finish();

			break;

		case R.id.save:
			row_pos = 10000;
			if (check_condition()) {
				Toast.makeText(getApplicationContext(),
						AlertMessage.MESSAGE_INVALID_DATA, Toast.LENGTH_SHORT)
						.show();
			} else {
				if (validate_data()) {
					if (enter_all) {
						lv.invalidateViews();
						
						Toast.makeText(getApplicationContext(), "Enter Data in all SkU At Row = "
								+ row_pos,
								Toast.LENGTH_SHORT).show();
					} else {

						lv.invalidateViews();
						Toast.makeText(getApplicationContext(), "Invalid Data At Row = "
										+ row_pos,
								Toast.LENGTH_SHORT).show();
					}
					
				} else {

					if (condition()) {
						AlertDialog.Builder builder = new AlertDialog.Builder(
								EditSkuList.this);
						alert = builder.create();
						builder.setMessage("Do you want to update the data ")
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

												database.UpdateSosPepsiData(
														store_id, sku_list,
														brand_id,
														CommonString.KEY_pepsi);

												if (database.CheckBrandid(
														brand_id, store_id,CommonString.KEY_pepsi) == 0) {
													database.InsertBrandCheck(
															brand_id, store_id,CommonString.KEY_pepsi);
												}
												SharedPreferences.Editor editor = preferences
														.edit();
												editor.putString(
														"Cateogry_Edit", "Y");
												editor.commit();

												Intent i = new Intent(
														getApplicationContext(),
														ShareOfShelfActivity.class);
												startActivity(i);
												EditSkuList.this.finish();

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
					} else {
						lv.invalidateViews();
						Toast.makeText(
								getBaseContext(),
								"Invalid Faceup, Faceup Should Be Less Than The Stock At Row = "
										+ row_pos, Toast.LENGTH_LONG).show();
					}

				}
			}

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

			Intent intent = new Intent(this, ShareOfShelfActivity.class);

			startActivity(intent);

			EditSkuList.this.finish();
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
		// super.onConfigurationChanged(newConfig);
		// setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		mKeyboardView.setKeyboard(mKeyboard);
		mKeyboardView.setVisibility(View.INVISIBLE);
	}

}
