package com.cpm.dailyEntry;

import java.util.ArrayList;
import java.util.Calendar;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.InputFilter;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.cpm.Constants.CommonString;
import com.cpm.database.PepsicoDatabase;
import com.cpm.delegates.CoverageBean;
import com.cpm.delegates.PosmBean;
import com.cpm.message.AlertMessage;
import com.cpm.parinaampepsicoo.R;

public class AssetsSkuActivity extends Activity {

	public String store_name;
	public ListView lv;
	private String store_id, username, intime, brand_id, visit_date;
	private SharedPreferences preferences;
	private PepsicoDatabase database;
	public static String presence[];
	public static String purity[];
	public static String remarks[];
	public static int pos[], camera[], image1[], image2[], image3[];
	private static ArrayList<PosmBean> sku_list = new ArrayList<PosmBean>();
	private ArrayList<PosmBean> assetlist = new ArrayList<PosmBean>();
	static String img1;
	static String img2;
	static String img3;
	public static final int Info_SELECT = 1;
	public static final int POPUP_SELECT = 2;
	static int mposition = -1;
	AlertDialog alert;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.assest_lis);

		lv = (ListView) findViewById(R.id.list);

		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		intime = getCurrentTime();
		preferences = PreferenceManager.getDefaultSharedPreferences(this);
		store_name = preferences.getString(CommonString.KEY_STORE_NAME, "");
		username = preferences.getString(CommonString.KEY_USERNAME, "");
		store_id = preferences.getString(CommonString.KEY_STORE_ID, "");
		brand_id = preferences.getString(CommonString.KEY_BRAND_ID, "");
		visit_date = preferences.getString(CommonString.KEY_DATE, "");

		TextView text1 = (TextView) findViewById(R.id.mainpage_remembermetext);
		text1.setText(store_name);

		database = new PepsicoDatabase(this);
		database.open();

		sku_list = database.getAssets(store_id);
		
		//doing for purity
		
		purity = new String[sku_list.size()];
		
		for(int i=0;i<sku_list.size();i++){
			sku_list.get(i).setAssetpure("NO");
		}
		
		presence = new String[sku_list.size()];

		for (int i = 0; i < sku_list.size(); i++) {

			presence[i] = "-1";
			sku_list.get(i).setAvailable("NO");

		}

		pos = new int[sku_list.size()];

		for (int i = 0; i < sku_list.size(); i++) {

			pos[i] = -1;
			sku_list.get(i).setRemarks("");

		}

		camera = new int[sku_list.size()];

		for (int i = 0; i < sku_list.size(); i++) {

			camera[i] = -1;
			sku_list.get(i).setCamera("NO");

		}

		image1 = new int[sku_list.size()];

		for (int i = 0; i < sku_list.size(); i++) {

			camera[i] = -1;
			sku_list.get(i).setImage1("");

		}

		image2 = new int[sku_list.size()];

		for (int i = 0; i < sku_list.size(); i++) {

			camera[i] = -1;
			sku_list.get(i).setImage2("");

		}

		image3 = new int[sku_list.size()];

		for (int i = 0; i < sku_list.size(); i++) {

			camera[i] = -1;
			sku_list.get(i).setImage3("");

		}

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

		public View getView(final int position, View convertView,
				ViewGroup parent) {
			final ViewHolder holder;
			if (convertView == null) {
				convertView = mInflater.inflate(R.layout.assetlistview, null);
				holder = new ViewHolder();

				holder.sku = (TextView) convertView.findViewById(R.id.sku);

				holder.assets = (TextView) convertView
						.findViewById(R.id.assest);

				holder.type = (TextView) convertView.findViewById(R.id.type);

				holder.presence = (ToggleButton) convertView
						.findViewById(R.id.toggleButton);
				holder.pure = (ToggleButton) convertView
						.findViewById(R.id.togglepure);
				
				holder.remarks = (EditText) convertView
						.findViewById(R.id.remarks);

				holder.camera = (ImageView) convertView
						.findViewById(R.id.cameraicon);

				convertView.setTag(holder);

			} else {

				holder = (ViewHolder) convertView.getTag();

			}

			 holder.pure.setText(sku_list.get(position).getAssetpure().toString());
			 
			 if(sku_list.get(position).getDownlodassetpure().equalsIgnoreCase("True"))
			 {
				 holder.pure.setEnabled(true);
			 }
			 else
			 {
				 holder.pure.setEnabled(false);
			 }
			 
			 
			 

			holder.assets
					.setText(sku_list.get(position).getAssest().toString());

			holder.type
					.setText(sku_list.get(position).getVertical().toString());

			holder.camera.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent intent = new Intent((AssetsSkuActivity) mcontext,
							ImageAssetsActivity.class);
					int position = v.getId();
					intent.putExtra("position", position);
					intent.putExtra("image1path", sku_list.get(position)
							.getImage1());
					intent.putExtra("image2path", sku_list.get(position)
							.getImage2());
					intent.putExtra("image3path", sku_list.get(position)
							.getImage3());
					((AssetsSkuActivity) mcontext).startActivityForResult(
							intent, POPUP_SELECT);
				}
			});

			holder.remarks
					.setOnFocusChangeListener(new OnFocusChangeListener() {
						public void onFocusChange(View v, boolean hasFocus) {

							if (!hasFocus) {
								final int position = v.getId();
								final EditText Caption = (EditText) v;
								String value1 = Caption.getText().toString();

								if (value1.equals("")) {
									pos[position] = -1;
									sku_list.get(position).setRemarks("");

								} else {
									pos[position] = 0;
									sku_list.get(position).setRemarks(value1);
								}

							}
						}
					});

			if (pos[position] == 0) {

				holder.remarks.setText(sku_list.get(position).getRemarks());
			}
			if (pos[position] == -1) {

				holder.remarks.setText("");

			}

			
			holder.pure.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub

					//purity[position] = "0";
					String val = holder.pure.getText().toString();
					sku_list.get(position).setAssetpure(val);

				}
			});
			
			
			
			holder.presence.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub

					presence[position] = "0";
					String val = holder.presence.getText().toString();
					sku_list.get(position).setAvailable(val);
					if (val.equalsIgnoreCase("YES")) {
						holder.remarks.setText("");
						sku_list.get(position).setRemarks("");
						holder.remarks.setEnabled(false);
						holder.camera.setEnabled(true);
						holder.camera
								.setBackgroundResource(R.drawable.listcamera);

						if (sku_list.get(position).getCamera()
								.equalsIgnoreCase("NO")) {
							holder.camera
									.setBackgroundResource(R.drawable.listcamera);
						} else {
							holder.camera
									.setBackgroundResource(R.drawable.listcameratick);
						}

					}
					if (val.equalsIgnoreCase("NO")) {
						holder.remarks.setEnabled(true);
						holder.camera.setEnabled(false);
						holder.camera
								.setBackgroundResource(R.drawable.listcameralocked);

						sku_list.get(position).setCamera("NO");
						sku_list.get(position).setImage1("");
						sku_list.get(position).setImage2("");
						sku_list.get(position).setImage3("");

					}

				}
			});

			
			if (sku_list.get(position).getAssetpure().equalsIgnoreCase("yes")) {
				holder.pure.setChecked(true);
				

			} 
			else
			{
				holder.pure.setChecked(false);
				
			}
			
			
			if (presence[position].equals("-1")) {
				holder.presence.setChecked(false);
				holder.camera.setEnabled(false);
				holder.remarks.setEnabled(true);
				holder.camera
						.setBackgroundResource(R.drawable.listcameralocked);

			} 
			else
			{
				if (sku_list.get(position).getAvailable().toString()
						.equalsIgnoreCase("YES")) {
					holder.presence.setChecked(true);
					holder.remarks.setText("");
					sku_list.get(position).setRemarks("");
					holder.remarks.setEnabled(false);
					holder.camera.setEnabled(true);
					holder.camera.setBackgroundResource(R.drawable.listcamera);

					if (position == mposition) {

						sku_list.get(position).setImage1(img1);
						sku_list.get(position).setImage2(img2);
						sku_list.get(position).setImage3(img3);
						mposition = -1;
						sku_list.get(position).setCamera("YES");
						//if (!img1.equals("") && !img2.equals("")
								//&& !img3.equals("")) {
							//sku_list.get(position).setCamera("YES");
						//}
						
					}

					if (sku_list.get(position).getCamera()
							.equalsIgnoreCase("NO")) {
						holder.camera
								.setBackgroundResource(R.drawable.listcamera);
					} else {
						holder.camera
								.setBackgroundResource(R.drawable.listcameratick);
					}

				} else {
					holder.presence.setChecked(false);
					holder.camera.setEnabled(false);
					holder.camera
							.setBackgroundResource(R.drawable.listcameralocked);
					holder.remarks.setEnabled(true);

				}

			}
			// holder.remarks.requestFocus();
			holder.remarks.setId(position);
			holder.camera.setId(position);

			return convertView;
		}

		static class ViewHolder {
			TextView sku, assets, type;
			ToggleButton presence,pure;
			EditText remarks;
			ImageView camera;
		}

	}

	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch (requestCode) {

		case Info_SELECT:

		case POPUP_SELECT:
			if (resultCode == RESULT_OK) {

				mposition = data.getIntExtra("position", 1000);
				img1 = data.getStringExtra(CommonString.KEY_IMAGE_PATH1);
				img2 = data.getStringExtra(CommonString.KEY_IMAGE_PATH2);
				img3 = data.getStringExtra(CommonString.KEY_IMAGE_PATH3);
				lv.invalidateViews();
				break;

			}

		}

	}

	public void onButtonClick(View v) {
		switch (v.getId()) {
		case R.id.training_intellogo:
			Intent intent = new Intent(this, ShareOfShelfActivity.class);

			startActivity(intent);

			AssetsSkuActivity.this.finish();
			break;

		case R.id.save:
			lv.clearFocus();
			if (validate_data()) {
				Toast.makeText(getApplicationContext(),
						AlertMessage.MESSAGE_INVALID_DATA, Toast.LENGTH_SHORT)
						.show();
			} else {
				if (validate_image()) {

					Toast.makeText(getApplicationContext(),
							"Please Capture Images", Toast.LENGTH_SHORT)
							.show();
				} else {

					AlertDialog.Builder builder = new AlertDialog.Builder(
							AssetsSkuActivity.this);
					builder.setMessage("Do you want to save the data ")
							.setCancelable(false)
							.setPositiveButton("OK",
									new DialogInterface.OnClickListener() {
										public void onClick(
												DialogInterface dialog, int id) {

											alert.getButton(
													AlertDialog.BUTTON_POSITIVE)
													.setEnabled(false);
											mposition = -1;
											database.open();
											database.InsertAssetData(getMid(),
													store_id, sku_list);

											Intent i = new Intent(
													getApplicationContext(),
													DailyentryMenuActivity.class);
											startActivity(i);
											AssetsSkuActivity.this.finish();

										}
									})
							.setNegativeButton("Cancel",
									new DialogInterface.OnClickListener() {
										public void onClick(
												DialogInterface dialog, int id) {
											dialog.cancel();
										}
									});
					alert = builder.create();
					alert.show();
				}
			}
		}
	}

	public boolean validate_data() {
		boolean result = false;

		for (int i = 0; i < sku_list.size(); i++) {
			if (sku_list.get(i).getAvailable().equalsIgnoreCase("NO")) {
				if (sku_list.get(i).getRemarks().equalsIgnoreCase("")) {

					result = true;

					break;
				}
			}

		}
		return result;

	}

	public boolean validate_image() {
		boolean result = false;

		for (int i = 0; i < sku_list.size(); i++) {
			if (sku_list.get(i).getAvailable().equalsIgnoreCase("Yes")) {
				if (sku_list.get(i).getImage1().equalsIgnoreCase("")
						&& sku_list.get(i).getImage2().equalsIgnoreCase("")
						&& sku_list.get(i).getImage3().equalsIgnoreCase("")) {

					result = true;

					break;
				}
			}

		}
		return result;

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

		Intent intent = new Intent(this, DailyentryMenuActivity.class);

		startActivity(intent);

		AssetsSkuActivity.this.finish();
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		database.close();
	}

}
