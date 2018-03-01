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
import com.cpm.delegates.CategoryBean;
import com.cpm.delegates.CoverageBean;
import com.cpm.delegates.SodBean;
import com.cpm.delegates.VericalBrandBean;
import com.cpm.keyboard.BasicOnKeyboardActionListener;
import com.cpm.keyboard.CustomKeyboardView;
import com.cpm.message.AlertMessage;
import com.cpm.parinaampepsicoo.R;

public class SodTempOtherActivity extends Activity implements
		OnItemSelectedListener {

	private Spinner skuspinner, verticalspinner, categoryspinner;
	private PepsicoDatabase database;
	private ArrayAdapter<CharSequence> verticaladapter, categoryadapter,
			skuadapter;
	private ArrayList<VericalBrandBean> vertical_list = new ArrayList<VericalBrandBean>();
	private ArrayList<CategoryBean> category_list = new ArrayList<CategoryBean>();
	private ArrayList<VericalBrandBean> sku_list = new ArrayList<VericalBrandBean>();
	private ArrayList<SodBean> inserted_list = new ArrayList<SodBean>();
	public ListView lv;

	public Keyboard mKeyboard;
	public CustomKeyboardView mKeyboardView;
	private String verticalname, verticalid, cateroryid, categoryname,
			visit_date, store_id, username, intime, skuid, skuname;

	private static int myear;
	private static int month;
	private static int day;
	private SharedPreferences preferences;
	Button save;
	TextView text;
	EditText stock, faceup, l, b, h;

	String num = "0";
	public final static int DATE_DIALOG_ID = 0;
	public String store_name;
	String companyname, company_id;
	static int currentapiVersion = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sodtemp_transaction);

		currentapiVersion = android.os.Build.VERSION.SDK_INT;
		
		skuspinner = (Spinner) findViewById(R.id.sku);
		verticalspinner = (Spinner) findViewById(R.id.vertical);
		categoryspinner = (Spinner) findViewById(R.id.category);
		text = (TextView) findViewById(R.id.training_remembermetext);
		lv = (ListView) findViewById(R.id.list);
		save = (Button) findViewById(R.id.save);
		stock = (EditText) findViewById(R.id.stock);
		faceup = (EditText) findViewById(R.id.faceup);

		intime = getCurrentTime();

		database = new PepsicoDatabase(this);
		database.open();

		if (currentapiVersion >= 11) {
			stock.setTextIsSelectable(true);
			stock.setRawInputType(InputType.TYPE_CLASS_TEXT);

			faceup.setTextIsSelectable(true);
			faceup.setRawInputType(InputType.TYPE_CLASS_TEXT);

		} else {
			stock.setInputType(0);
			faceup.setInputType(0);
		}

		preferences = PreferenceManager.getDefaultSharedPreferences(this);
		store_name = preferences.getString(CommonString.KEY_STORE_NAME, "");
		store_id = preferences.getString(CommonString.KEY_STORE_ID, "");
		visit_date = preferences.getString(CommonString.KEY_VISIT_DATE, "");

		Intent intent = getIntent();
		company_id = intent.getStringExtra(CommonString.KEY_COMPANY_ID);

		TextView text1 = (TextView) findViewById(R.id.mainpage_remembermetext);
		text1.setText(store_name);

		verticaladapter = new ArrayAdapter<CharSequence>(this,
				android.R.layout.simple_spinner_item);
		categoryadapter = new ArrayAdapter<CharSequence>(this,
				android.R.layout.simple_spinner_item);

		skuadapter = new ArrayAdapter<CharSequence>(this,
				android.R.layout.simple_spinner_item);

		verticaladapter.add("Select Vertical");
		categoryadapter.add("Select Category");
		skuadapter.add("Select SKU");

		verticalspinner.setAdapter(verticaladapter);
		categoryspinner.setAdapter(categoryadapter);
		skuspinner.setAdapter(skuadapter);

		verticaladapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		categoryadapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		skuadapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		categoryspinner.setOnItemSelectedListener(this);
		verticalspinner.setOnItemSelectedListener(this);
		skuspinner.setOnItemSelectedListener(this);

		database = new PepsicoDatabase(this);
		database.open();

		vertical_list = database.getVerticalLevelMasterData();

		for (int i = 0; i < vertical_list.size(); i++) {
			verticaladapter.add(vertical_list.get(i).getVertical());
		}

		inserted_list = database.getSodOtherData(store_id);

		lv.invalidateViews();

		lv.setAdapter(new MyAdapter(getApplicationContext()));

		mKeyboard = new Keyboard(this, R.xml.keyboard);
		mKeyboardView = (CustomKeyboardView) findViewById(R.id.keyboard_view);
		mKeyboardView.setKeyboard(mKeyboard);
		mKeyboardView
				.setOnKeyboardActionListener(new BasicOnKeyboardActionListener(
						this, 8));

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

		if (verticalid != null && cateroryid != null && skuid != null) {

			if ((!stock.getText().toString().equals(""))
					&& (!faceup.getText().toString().equals(""))) {

				result = true;

			}

		}
		return result;

	}

	public boolean validate_stock() {
		boolean result = true;
		if (stock.getText().toString().equals("0")) {
			result = true;
			
			//result = true;
			
			//changes here sod temp bec of poi report
			
		}

		return result;
	}

	public boolean validate() {
		boolean result = true;

		for (int i = 0; i < inserted_list.size(); i++) {
			if (inserted_list.get(i).getSkuname().equalsIgnoreCase(skuname)) {

				result = false;
				break;
			}

		}
		return result;
	}

	public boolean condition() {
		boolean result = true;

		if (Integer.parseInt(faceup.getText().toString()) > (Integer
				.parseInt(stock.getText().toString()))) {
			result = false;
		}

		return result;
	}

	public void onButtonClick(View v) {
		switch (v.getId()) {
		case R.id.training_intellogo:
			Intent i = new Intent(this, SodMasterActivity.class);
			startActivity(i);
			SodTempOtherActivity.this.finish();
			break;
		case R.id.save:

			if (validate_data()) {
				if (validate_stock()) {
					if (condition()) {
						if (validate()) {

							SodBean sod = new SodBean();
							sod.setStoreid(store_id);
							sod.setFaceup(faceup.getText().toString());
							sod.setStock(stock.getText().toString());
							sod.setDom1("1/1/1900");
							sod.setDom2("1/1/1900");
							sod.setDom3("1/1/1900");
							sod.setSkuname(skuname);
							sod.setVerticalName(verticalname);
							sod.setSkuid(skuid);

							database.InsertSodTempData(1, sod);

							mKeyboardView.setVisibility(View.INVISIBLE);

							stock.setText("");
							faceup.setText("");
							skuspinner.setSelection(0);

							inserted_list = database.getSodOtherData(store_id);

							lv.invalidateViews();

							lv.setAdapter(new MyAdapter(getApplicationContext()));

						} else {
							Toast.makeText(getBaseContext(),
									"Already Added, Select Another SKU",
									Toast.LENGTH_LONG).show();
						}
					} else {
						Toast.makeText(getBaseContext(), "Invalid Faceup",
								Toast.LENGTH_LONG).show();
					}

				} else {
					Toast.makeText(getBaseContext(), "Stock Cant Be 0",
							Toast.LENGTH_LONG).show();
				}

			} else {
				Toast.makeText(getBaseContext(),
						AlertMessage.MESSAGE_INVALID_DATA, Toast.LENGTH_LONG)
						.show();
			}
			break;

		case R.id.faceup:
			if (mKeyboardView.getVisibility() == View.INVISIBLE)
				mKeyboardView.setVisibility(View.VISIBLE);
			break;

		case R.id.stock:
			if (mKeyboardView.getVisibility() == View.INVISIBLE)
				mKeyboardView.setVisibility(View.VISIBLE);
			break;

		}
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		if (mKeyboardView.getVisibility() == View.VISIBLE) {
			mKeyboardView.setVisibility(View.INVISIBLE);
		} else {

			Intent intent = new Intent(this, SodMasterActivity.class);
			setResult(RESULT_OK, intent);
			SodTempOtherActivity.this.finish();

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
			return inserted_list.size();

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

				convertView = mInflater.inflate(R.layout.sodtempother_listview,
						null);
				holder = new ViewHolder();

				holder.sku = (TextView) convertView.findViewById(R.id.sku);

				holder.stock = (TextView) convertView.findViewById(R.id.stock);
				holder.faceup = (TextView) convertView
						.findViewById(R.id.faceup);

				holder.del = (ImageView) convertView.findViewById(R.id.delete);

				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}

			holder.sku.setText(inserted_list.get(position).getSkuname()
					.toString());
			holder.stock.setText(inserted_list.get(position).getStock()
					.toString());
			holder.faceup.setText(inserted_list.get(position).getFaceup()
					.toString());

			holder.del.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
					final AlertDialog.Builder alertDialog = new AlertDialog.Builder(
							SodTempOtherActivity.this);
					alertDialog.setMessage("Do you want to delete ");
					alertDialog.setPositiveButton("YES",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int which) {

									database.open();

									database.DeleteSodTempData(Integer
											.parseInt(inserted_list.get(
													position).getId()));

									inserted_list = database
											.getSodOtherData(store_id);
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

			holder.del.setId(position);

			return convertView;
		}

		class ViewHolder {
			TextView sku, stock, faceup;
			ImageView del;

		}

	}

	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int position,
			long arg3) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.vertical:
			if (position != 0) {
				verticalname = vertical_list.get(position - 1).getVertical();
				verticalid = vertical_list.get(position - 1).getVerticalid();
				category_list.clear();
				categoryadapter.clear();
				categoryadapter.add("Select Category");
				database = new PepsicoDatabase(this);
				database.open();
				category_list = database.getCategoryVerticalMapping(verticalid);
				for (int i = 0; i < category_list.size(); i++) {
					categoryadapter.add(category_list.get(i).getCategoryname());
				}
				categoryspinner.setAdapter(categoryadapter);

			} else {
				verticalname = null;
				verticalid = null;
			}
			break;
		case R.id.category:
			if (position != 0) {

				categoryname = category_list.get(position - 1)
						.getCategoryname();
				cateroryid = category_list.get(position - 1).getCategoryid();
				skuadapter.clear();
				sku_list.clear();
				skuadapter.add("Select SKU");
				database = new PepsicoDatabase(this);
				database.open();

				sku_list = database.getSku(cateroryid, company_id,store_id);

				for (int i = 0; i < sku_list.size(); i++) {
					skuadapter.add(sku_list.get(i).getSkuname());
				}
				skuspinner.setAdapter(skuadapter);

			} else {
				categoryname = null;
				cateroryid = null;
			}
			break;

		case R.id.sku:
			if (position > 0) {

				skuname = sku_list.get(position - 1).getSkuname();
				skuid = sku_list.get(position - 1).getSkuid();

			} else {
				skuname = null;
				skuid = null;
			}
			break;
		}

	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub

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
