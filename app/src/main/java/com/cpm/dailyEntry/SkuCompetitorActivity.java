package com.cpm.dailyEntry;

import java.util.ArrayList;
import java.util.Calendar;

import android.app.Activity;
import android.app.AlertDialog;
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
import android.view.WindowManager;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AbsListView.OnScrollListener;

import com.cpm.Constants.CommonString;
import com.cpm.database.PepsicoDatabase;
import com.cpm.delegates.CoverageBean;
import com.cpm.delegates.VericalBrandBean;
import com.cpm.keyboard.BasicOnKeyboardActionListener;
import com.cpm.keyboard.CustomKeyboardView;
import com.cpm.message.AlertMessage;
import com.cpm.parinaampepsicoo.R;

public class SkuCompetitorActivity extends Activity {

	public String store_name;
	public ListView lv;
	public static int pos[], pos1[], pos2[];
	public Keyboard mKeyboard;
	public static CustomKeyboardView mKeyboardView;
	private String store_id, username, intime, brand_id, visit_date;
	private SharedPreferences preferences;
	private Bundle extras;
	private PepsicoDatabase database;
	static int currentapiVersion = 1;
	private static ArrayList<VericalBrandBean> sku_list = new ArrayList<VericalBrandBean>();
	AlertDialog alert;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.skulist);

		currentapiVersion = android.os.Build.VERSION.SDK_INT;

		lv = (ListView) findViewById(R.id.list);

		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

		intime = getCurrentTime();
		preferences = PreferenceManager.getDefaultSharedPreferences(this);
		store_name = preferences.getString(CommonString.KEY_STORE_NAME, "");
		store_id = preferences.getString(CommonString.KEY_STORE_ID, "");
		brand_id = preferences.getString(CommonString.KEY_BRAND_ID, "");
		visit_date = preferences.getString(CommonString.KEY_VISIT_DATE, "");
		String brand_name = preferences.getString(CommonString.KEY_BRAND_NAME,
				"");

		TextView text1 = (TextView) findViewById(R.id.mainpage_remembermetext);
		text1.setText(store_name + " - " + brand_name);

		database = new PepsicoDatabase(this);
		database.open();
		/*
		 * extras = getIntent().getExtras(); if (extras != null) { brand_id =
		 * extras.getString(CommonString.KEY_BRAND_ID); }
		 */

		sku_list = database.getSkuCompetitorData(brand_id, store_id);

		pos = new int[sku_list.size()];

		for (int i = 0; i < sku_list.size(); i++) {

			pos[i] = -1;
			sku_list.get(i).setSkuqty("");

		}

		pos1 = new int[sku_list.size()];

		for (int i = 0; i < sku_list.size(); i++) {

			pos1[i] = -1;
			sku_list.get(i).setSkuqty1("");

		}

		pos2 = new int[sku_list.size()];

		for (int i = 0; i < sku_list.size(); i++) {

			pos2[i] = -1;
			sku_list.get(i).setSkuqty2("0");

		}

		for (int i = 0; i < sku_list.size(); i++) {

			sku_list.get(i).setReasonid("");

		}

		for (int i = 0; i < sku_list.size(); i++) {

			sku_list.get(i).setDom1("1/1/1900");
			sku_list.get(i).setDom2("1/1/1900");
			sku_list.get(i).setDom3("1/1/1900");

		}

		mKeyboard = new Keyboard(this, R.xml.keyboard);
		mKeyboardView = (CustomKeyboardView) findViewById(R.id.keyboard_view);
		mKeyboardView.setKeyboard(mKeyboard);
		mKeyboardView
				.setOnKeyboardActionListener(new BasicOnKeyboardActionListener(
						this, 7));

		mKeyboardView.setVisibility(View.INVISIBLE);

		lv.setAdapter(new MyAdapter(getApplicationContext()));

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

		public MyAdapter(Context context) {
			mInflater = LayoutInflater.from(context);

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
			ViewHolder holder;
			if (convertView == null) {
				convertView = mInflater
						.inflate(R.layout.focusskulistview, null);
				holder = new ViewHolder();

				holder.sku = (TextView) convertView
						.findViewById(R.id.focusskulistview_1c_titletext);
				holder.sku_text1 = (EditText) convertView
						.findViewById(R.id.focusskulistview_edit1);
				holder.sku_text2 = (EditText) convertView
						.findViewById(R.id.focusskulistview_edit2);
				holder.sku_text3 = (EditText) convertView
						.findViewById(R.id.focusskulistview_edit3);

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

			holder.sku_text1
					.setOnFocusChangeListener(new OnFocusChangeListener() {
						public void onFocusChange(View v, boolean hasFocus) {

							if (!hasFocus) {
								final int position = v.getId();
								final EditText Caption = (EditText) v;
								String value1 = Caption.getText().toString();

								if (value1.equals("")) {
									pos[position] = -1;
									sku_list.get(position).setSkuqty("");

								} else {
									pos[position] = 0;
									sku_list.get(position).setSkuqty(value1);
								}

							}
						}
					});

			if (pos[position] == 0) {

				holder.sku_text1.setText(sku_list.get(position).getSkuqty());
			}
			if (pos[position] == -1) {

				holder.sku_text1.setText("");

			}

			holder.sku_text2
					.setOnFocusChangeListener(new OnFocusChangeListener() {
						public void onFocusChange(View v, boolean hasFocus) {

							if (!hasFocus) {
								final int position = v.getId();
								final EditText Caption = (EditText) v;
								String value1 = Caption.getText().toString();

								if (value1.equals("")) {
									pos1[position] = -1;
									sku_list.get(position).setSkuqty1("");

								} else {
									pos1[position] = 0;
									sku_list.get(position).setSkuqty1(value1);
								}

							}
						}
					});

			if (pos1[position] == 0) {

				holder.sku_text2.setText(sku_list.get(position).getSkuqty1());
			}
			if (pos1[position] == -1) {

				holder.sku_text2.setText("");

			}

			holder.sku_text3
					.setOnFocusChangeListener(new OnFocusChangeListener() {
						public void onFocusChange(View v, boolean hasFocus) {

							if (!hasFocus) {

								final int position = v.getId();
								final EditText Caption = (EditText) v;
								String value1 = Caption.getText().toString();

								if (value1.equals("")) {
									pos2[position] = -1;
									sku_list.get(position).setSkuqty2("0");
									// holder.sku_text3.setFocusable(false);

								} else {
									pos2[position] = 0;
									sku_list.get(position).setSkuqty2(value1);
									// holder.sku_text3.setFocusable(false);

								}

							}

						}
					});

			if (pos2[position] == 0) {

				holder.sku_text3.setText(sku_list.get(position).getSkuqty2());
			}
			if (pos2[position] == -1) {

				holder.sku_text3.setText("");

			}
			holder.sku_text1.setId(position);
			holder.sku_text2.setId(position);
			holder.sku_text3.setId(position);
			return convertView;
		}

		static class ViewHolder {
			TextView sku;
			EditText sku_text1, sku_text2, sku_text3;
		}

	}

	public boolean check_condition() {
		boolean result = true;

		for (int i = 0; i < sku_list.size(); i++) {
			
		

			if (sku_list.get(i).getSkuqty1().equalsIgnoreCase("")) {

				result = true;
				break;

			} else {
				result = false;
				
			}
		}

		return result;

	}

	public void onButtonClick(View v) {
		switch (v.getId()) {
		case R.id.training_intellogo:
			Intent intent = new Intent(this, ShareOfShelfActivity.class);

			startActivity(intent);

			SkuCompetitorActivity.this.finish();
			break;

		case R.id.save:

			mKeyboardView.requestFocusFromTouch();

			if (check_condition()) {

				Toast.makeText(getApplicationContext(),
						AlertMessage.MESSAGE_INVALID_DATA, Toast.LENGTH_SHORT)
						.show();

			} else {

				AlertDialog.Builder builder = new AlertDialog.Builder(
						SkuCompetitorActivity.this);
				alert = builder.create();
				builder.setMessage("Do you want to save the data")
						.setCancelable(false)
						.setPositiveButton("OK",
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int id) {

										alert.getButton(
												AlertDialog.BUTTON_POSITIVE)
												.setEnabled(false);

										database.open();
										database.InsertSosPepsiData(getMid(),
												store_id, sku_list, brand_id,
												CommonString.KEY_COMPETITOR);
										if (database.CheckBrandid(
												brand_id, store_id,CommonString.KEY_COMPETITOR) == 0) {
											database.InsertBrandCheck(
													brand_id, store_id,CommonString.KEY_COMPETITOR);
										}

										SharedPreferences.Editor editor = preferences
												.edit();
										editor.putString("Cateogry_Edit", "Y");
										editor.commit();

										Intent i = new Intent(
												getApplicationContext(),
												ShareOfShelfActivity.class);
										startActivity(i);
										SkuCompetitorActivity.this.finish();

									}
								})
						.setNegativeButton("Cancel",
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int id) {
										dialog.cancel();
									}
								});

				alert = builder.create();
				alert.show();

			}

		}
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
	public void onBackPressed() {
		// TODO Auto-generated method stub

		if (mKeyboardView.getVisibility() == View.VISIBLE) {
			mKeyboardView.setVisibility(View.INVISIBLE);
			mKeyboardView.requestFocusFromTouch();
		} else {
			/*
			 * Toast.makeText(getBaseContext(), AlertMessage.MESSAGE_BACK,
			 * Toast.LENGTH_LONG).show();
			 */
			SharedPreferences.Editor editor = preferences.edit();

			editor.putString("Cateogry_Edit", "Y");

			editor.commit();
			Intent intent = new Intent(this, ShareOfShelfActivity.class);

			startActivity(intent);

			SkuCompetitorActivity.this.finish();
		}

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
