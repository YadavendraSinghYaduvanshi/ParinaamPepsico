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
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.database.DataSetObserver;
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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.cpm.Constants.CommonString;
import com.cpm.database.PepsicoDatabase;
import com.cpm.delegates.CategoryBean;
import com.cpm.delegates.SodBean;
import com.cpm.delegates.VericalBrandBean;
import com.cpm.keyboard.BasicOnKeyboardActionListener;
import com.cpm.keyboard.CustomKeyboardView;
import com.cpm.message.AlertMessage;
import com.cpm.parinaampepsicoo.R;

public class SodTempPepsiActivity extends Activity implements
		OnItemSelectedListener,SpinnerAdapter {

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
	Calendar c;
	private static int month;
	private static int day;
	private SharedPreferences preferences;
	Button save;
	TextView text;
	EditText stock, faceup, l, b, h;
	Button dom1, dom2, dom3;
	String num = "0";
	public final static int DATE_DIALOG_ID = 0;
	public final static int DATE_DIALOG_ID1 = 1;
	public final static int DATE_DIALOG_ID2 = 2;
	public String store_name;
	String companyname, company_id;
	int currentapiVersion = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sod_transaction);

		currentapiVersion = android.os.Build.VERSION.SDK_INT;
		c = Calendar.getInstance();
		myear = c.get(Calendar.YEAR);
		month = c.get(Calendar.MONTH);
		day = c.get(Calendar.DAY_OF_MONTH);

		skuspinner = (Spinner) findViewById(R.id.sku);
		verticalspinner = (Spinner) findViewById(R.id.vertical);
		categoryspinner = (Spinner) findViewById(R.id.category);
		text = (TextView) findViewById(R.id.training_remembermetext);
		lv = (ListView) findViewById(R.id.list);
		save = (Button) findViewById(R.id.save);
		stock = (EditText) findViewById(R.id.stock);
		faceup = (EditText) findViewById(R.id.faceup);
		dom1 = (Button) findViewById(R.id.dom1);
		dom2 = (Button) findViewById(R.id.dom2);
		dom3 = (Button) findViewById(R.id.dom3);

		database = new PepsicoDatabase(this);
		database.open();

		intime = getCurrentTime();

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
				.setDropDownViewResource(R.layout.multiline_spinner);

		
		//skus.createFromResource(this,skuadapter,R.layout.multiline_spinner);
		
		
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
						this, 3));

	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		database.close();
	}

	public void hide() {
		mKeyboardView.setVisibility(View.INVISIBLE);

	}

	public boolean validate_data() {
		boolean result = false;

		if (verticalid != null && cateroryid != null && skuid != null) {

			if ((!stock.getText().toString().equals(""))
					&& (!faceup.getText().toString().equals(""))) {

				if ((!dom1.getText().toString().equals("MFD"))
						|| (!dom2.getText().toString().equals("MFD"))
						|| (!dom3.getText().toString().equals("MFD"))) {

					result = true;
				}
			}

		}
		return result;

	}

	public boolean validate_stock() {
		boolean result = true;
		if (stock.getText().toString().equals("0")) {
			result = false;
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
			SodTempPepsiActivity.this.finish();
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
							sod.setDom1(dom1.getText().toString());
							sod.setDom2(dom2.getText().toString());
							sod.setDom3(dom3.getText().toString());
							sod.setSkuname(skuname);
							sod.setSkuid(skuid);

							database.InsertSodTempData(1, sod);

							mKeyboardView.setVisibility(View.INVISIBLE);

							dom1.setText("MFD");
							dom2.setText("MFD");
							dom3.setText("MFD");
							stock.setText("");
							faceup.setText("");
							skuspinner.setSelection(0);

							inserted_list = database.getSodOtherData(store_id);

							lv.invalidateViews();

							lv.setAdapter(new MyAdapter(getApplicationContext()));

							SharedPreferences.Editor editor = preferences
									.edit();
							editor.putString("Sod_Status", "N");
							editor.commit();

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

		case R.id.dom1:
			showDialog(DATE_DIALOG_ID);
			num = "1";
			break;
		case R.id.dom2:
			showDialog(DATE_DIALOG_ID1);
			num = "2";
			break;
		case R.id.dom3:
			showDialog(DATE_DIALOG_ID2);
			num = "3";
			break;

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

		if (num.equals("1")) {

			dom1.setText(new StringBuilder().append(month + 1).append("/")
					.append(day).append("/").append(myear).append(" "));
		}
		if (num.equals("2")) {
			dom2.setText(new StringBuilder().append(month + 1).append("/")
					.append(day).append("/").append(myear).append(" "));
		}
		if (num.equals("3")) {
			dom3.setText(new StringBuilder().append(month + 1).append("/")
					.append(day).append("/").append(myear).append(" "));
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
			SodTempPepsiActivity.this.finish();

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

				convertView = mInflater
						.inflate(R.layout.sodtran_listview, null);
				holder = new ViewHolder();

				holder.sku = (TextView) convertView.findViewById(R.id.sku);

				holder.stock = (TextView) convertView.findViewById(R.id.stock);
				holder.faceup = (TextView) convertView
						.findViewById(R.id.faceup);

				holder.dom1 = (TextView) convertView.findViewById(R.id.dom1);
				holder.dom2 = (TextView) convertView.findViewById(R.id.dom2);
				holder.dom3 = (TextView) convertView.findViewById(R.id.dom3);
				holder.del = (ImageView) convertView.findViewById(R.id.delete);

				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}

			holder.dom1.setText(inserted_list.get(position).getDom1()
					.toString());
			holder.dom2.setText(inserted_list.get(position).getDom2()
					.toString());
			holder.dom3.setText(inserted_list.get(position).getDom3()
					.toString());
			holder.sku.setText(inserted_list.get(position).getSkuname()
					.toString());
			holder.stock.setText(inserted_list.get(position).getStock()
					.toString());
			holder.faceup.setText(inserted_list.get(position).getFaceup()
					.toString());

			holder.del.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
					final AlertDialog.Builder alertDialog = new AlertDialog.Builder(
							SodTempPepsiActivity.this);
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
			TextView sku, stock, faceup, dom1, dom2, dom3;
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
				for (int i = 0; i < category_list.size();  i++) {
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
				// lv.setAdapter(new MyAdapter(getApplicationContext()));

			} else {
				categoryname = null;
				cateroryid = null;
			}
			break;

		case R.id.sku:
			if (position != 0) {

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

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getItemViewType(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getViewTypeCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean hasStableIds() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void registerDataSetObserver(DataSetObserver arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void unregisterDataSetObserver(DataSetObserver arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public View getDropDownView(int position, View convertView, ViewGroup parent) {
		
		  if (convertView == null) {
	            convertView = new TextView(getApplicationContext());
	        }

	        TextView item = (TextView) convertView;
	        item.setText("asddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd");
	        final TextView finalItem = item;
	        item.post(new Runnable() {
	            @Override
	            public void run() {
	                finalItem.setSingleLine(false);
	            }
	        });
	        return item;
	}

	
	
	
}
