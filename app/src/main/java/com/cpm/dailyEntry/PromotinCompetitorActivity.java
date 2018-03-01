package com.cpm.dailyEntry;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.inputmethodservice.Keyboard;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.text.InputFilter;
import android.text.Spanned;
import android.util.Log;
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
import com.cpm.delegates.CityBean;
import com.cpm.delegates.CoverageBean;
import com.cpm.delegates.PromotionBean;
import com.cpm.delegates.SodBean;
import com.cpm.delegates.VericalBrandBean;
import com.cpm.keyboard.BasicOnKeyboardActionListener;
import com.cpm.keyboard.CustomKeyboardView;
import com.cpm.message.AlertMessage;
import com.cpm.parinaampepsicoo.R;
import com.cpm.upload.ImageUploadActivity;

public class PromotinCompetitorActivity extends Activity implements
		OnItemSelectedListener {

	private Spinner companyspinner, brandspinner;
	private PepsicoDatabase database;
	private ArrayAdapter<CharSequence> coadapter, brandadapter;

	private ArrayList<VericalBrandBean> brand_list = new ArrayList<VericalBrandBean>();
	private ArrayList<CityBean> company_list = new ArrayList<CityBean>();
	private ArrayList<PromotionBean> inserted_list = new ArrayList<PromotionBean>();

	public ListView lv;
	protected String _path;

	private String visit_date, store_id, username, intime, company_id,
			companyname, brandid, brandname;

	private SharedPreferences preferences;
	protected String _pathforcheck = "";
	EditText remarks;

	String num = "0", TempDate;
	public final static int DATE_DIALOG_ID = 0;
	public String store_name;
	private String image1 = "";
	private ArrayList<CoverageBean> cdata = new ArrayList<CoverageBean>();
	protected boolean _taken;
	protected static final String PHOTO_TAKEN = "photo_taken";
	ImageView camera;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.promotion_comp);

		companyspinner = (Spinner) findViewById(R.id.company);
		brandspinner = (Spinner) findViewById(R.id.brand);

		camera = (ImageView) findViewById(R.id.image);

		lv = (ListView) findViewById(R.id.list);

		remarks = (EditText) findViewById(R.id.remarks);

		intime = getCurrentTime();
		database = new PepsicoDatabase(this);
		database.open();

		TempDate = new Date().toString().replace(' ', '_').replace(',', '_')
				.replace(':', '-');

		preferences = PreferenceManager.getDefaultSharedPreferences(this);
		username = preferences.getString(CommonString.KEY_USERNAME, "");
		store_name = preferences.getString(CommonString.KEY_STORE_NAME, "");
		store_id = preferences.getString(CommonString.KEY_STORE_ID, "");
		visit_date = preferences.getString(CommonString.KEY_VISIT_DATE, "");

		TextView text1 = (TextView) findViewById(R.id.mainpage_remembermetext);
		text1.setText(store_name);

		coadapter = new ArrayAdapter<CharSequence>(this,
				android.R.layout.simple_spinner_item);

		coadapter.add("Select Company");

		companyspinner.setAdapter(coadapter);

		coadapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		companyspinner.setOnItemSelectedListener(this);

		brandadapter = new ArrayAdapter<CharSequence>(this,
				android.R.layout.simple_spinner_item);

		brandadapter.add("Select Brand");

		brandspinner.setAdapter(brandadapter);

		brandadapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		brandspinner.setOnItemSelectedListener(this);

		database = new PepsicoDatabase(this);
		database.open();

		company_list = database.getCompanyMasterExceptPepsi();

		for (int i = 0; i < company_list.size(); i++) {
			coadapter.add(company_list.get(i).getCompany());
		}

		inserted_list = database.gePromotionOtherData(store_id);

		lv.invalidateViews();

		lv.setAdapter(new MyAdapter(getApplicationContext()));

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
			// onPhotoTaken();
			if (new File("/mnt/sdcard/PepsicoImages/" + _pathforcheck).exists()) {
				camera.setBackgroundResource(R.drawable.camera_list_tick);
				image1 = _pathforcheck;

			}
			break;
		}

	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		database.close();
	}

	public boolean validate_data() {
		boolean result = false;

		if (company_id != null && brandid != null) {

			if ((!remarks.getText().toString().equals(""))) {

				result = true;

			}

		}
		return result;

	}

	public boolean validateImage() {
		boolean result = true;

		if (image1.equalsIgnoreCase("")) {

			result = false;

		}
		return result;
	}

	public void onButtonClick(View v) {
		switch (v.getId()) {

		case R.id.save:

			if (validate_data()) {

				String result = remarks.getText().toString()
						.replaceAll("[&^<>{}'$]", " ");
				PromotionBean sod = new PromotionBean();
				sod.setStoreid(store_id);
				sod.setRemarks(result);
				sod.setBrand(brandname);
				sod.setBrandid(brandid);
				sod.setCompanyname(companyname);
				sod.setCompanyid(company_id);
				sod.setImage(image1);

				database.open();
				database.InsertPromotionOtherData(getMid(), sod);

				remarks.setText("");
				brandspinner.setSelection(0);
				companyspinner.setSelection(0);
				image1 = "";
				camera.setBackgroundResource(R.drawable.camera);

				inserted_list = database.gePromotionOtherData(store_id);

				lv.invalidateViews();

				lv.setAdapter(new MyAdapter(getApplicationContext()));

			} else {
				Toast.makeText(getBaseContext(),
						AlertMessage.MESSAGE_INVALID_DATA, Toast.LENGTH_LONG)
						.show();
			}
			break;

		case R.id.image:
			_pathforcheck = store_id + "Promotion" + getTime() + username
					+ "image1" + ".jpg";

			_path = Environment.getExternalStorageDirectory()
					+ "/PepsicoImages/" + _pathforcheck;

			Log.i("MakeMachine", "ButtonClickHandler.onClick()");
			startCameraActivity();
			break;

		}
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub

		Intent intent = new Intent(this, DailyentryMenuActivity.class);
		startActivity(intent);
		PromotinCompetitorActivity.this.finish();

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

				convertView = mInflater.inflate(R.layout.promotion_listview,
						null);
				holder = new ViewHolder();

				holder.sku = (TextView) convertView.findViewById(R.id.sku);

				holder.remarks = (TextView) convertView
						.findViewById(R.id.remarks);
				holder.company = (TextView) convertView
						.findViewById(R.id.company);

				holder.del = (ImageView) convertView.findViewById(R.id.delete);
				holder.editcamera = (ImageView) convertView
						.findViewById(R.id.camera);

				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}

			holder.sku.setText(inserted_list.get(position).getBrand()
					.toString());
			holder.remarks.setText(inserted_list.get(position).getRemarks()
					.toString());
			holder.company.setText(inserted_list.get(position).getCompanyname()
					.toString());

			holder.del.setOnClickListener(new View.OnClickListener() {
				public void onClick(final View v) {
					final AlertDialog.Builder alertDialog = new AlertDialog.Builder(
							PromotinCompetitorActivity.this);
					alertDialog.setMessage("Do you want to delete ");
					alertDialog.setPositiveButton("YES",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int which) {

									int position = v.getId();
									database.open();

									database.DeletePromotion(Integer
											.parseInt(inserted_list.get(
													position).getId()));

									inserted_list = database
											.gePromotionOtherData(store_id);
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
							EditImagePromotionActivity.class);
					intent.putExtra("image1path", inserted_list.get(position)
							.getImage());
					startActivity(intent);

				}
			});

			holder.del.setId(position);

			return convertView;
		}

		class ViewHolder {
			TextView sku, remarks, company;
			ImageView del, editcamera;

		}

	}

	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int position,
			long arg3) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.company:
			if (position > 0) {

				companyname = company_list.get(position - 1).getCompany();
				company_id = company_list.get(position - 1).getCompanyid();

				database.open();
				brandadapter.clear();
				brand_list.clear();
				brandadapter.add("Select Brand");
				brand_list = database.getBrandCompanyList(company_id);

				for (int i = 0; i < brand_list.size(); i++) {
					brandadapter.add(brand_list.get(i).getBrand());
				}
				brandspinner.setAdapter(brandadapter);

			} else {
				companyname = null;
				company_id = null;
			}
			break;

		case R.id.brand:
			if (position > 0) {

				brandname = brand_list.get(position - 1).getBrand();
				brandid = brand_list.get(position - 1).getBrandid();

			} else {
				brandname = null;
				brandid = null;
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

	public String getTime() {
		Calendar m_cal = Calendar.getInstance();
		int hour = m_cal.get(Calendar.HOUR_OF_DAY);
		int min = m_cal.get(Calendar.MINUTE);
		int sec = m_cal.get(Calendar.SECOND);
		return hour + "-" + min + "-" + sec;

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
}
