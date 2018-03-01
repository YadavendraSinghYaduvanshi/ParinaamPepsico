package com.cpm.dailyEntry;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.text.InputFilter;
import android.text.Spanned;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;
import android.widget.AbsListView.OnScrollListener;

import com.cpm.Constants.CommonString;
import com.cpm.database.PepsicoDatabase;
import com.cpm.delegates.CoverageBean;
import com.cpm.delegates.PosmBean;
import com.cpm.delegates.PromoBean;
import com.cpm.message.AlertMessage;
import com.cpm.parinaampepsicoo.R;
import com.cpm.upload.ImageUploadActivity;

public class EditPromotinPepsiActivity extends Activity {

	public String store_name;
	public ListView lv;
	private static String store_id;
	private static String username;
	private String intime;
	private String visit_date;
	private SharedPreferences preferences;
	private PepsicoDatabase database;
	public static String presence[];
	public static String type[];
	public static int pos[], camera[];
	private static ArrayList<PromoBean> promo_list = new ArrayList<PromoBean>();
	protected boolean _taken;
	protected static final String PHOTO_TAKEN = "photo_taken";
	static String img1 = "";
	protected static String _path;
	protected static String _pathforcheck = "";
	public static final int Info_SELECT = 1;
	public static final int POPUP_SELECT = 2;
	static int mposition = 1000;
	AlertDialog alert;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.promotion_pepsi);

		intime = getCurrentTime();
		preferences = PreferenceManager.getDefaultSharedPreferences(this);
		store_name = preferences.getString(CommonString.KEY_STORE_NAME, "");
		username = preferences.getString(CommonString.KEY_USERNAME, "");
		store_id = preferences.getString(CommonString.KEY_STORE_ID, "");
		visit_date = preferences.getString(CommonString.KEY_DATE, "");

		TextView text1 = (TextView) findViewById(R.id.mainpage_remembermetext);
		text1.setText(store_name);

		Button update = (Button) findViewById(R.id.save);
		update.setText("Update");

		database = new PepsicoDatabase(this);
		database.open();

		promo_list = database.gePromotionPepsi(store_id);

		for (int i = 0; i < promo_list.size(); i++) {

			if (promo_list.get(i).getImage().equals(""))
				promo_list.get(i).setCamera("NO");
			else
				promo_list.get(i).setCamera("YES");

		}

		for (int i = 0; i < promo_list.size(); i++) {

			if (promo_list.get(i).getAvailable().equals("1")) {
				promo_list.get(i).setAvailable("YES");
			} else {
				promo_list.get(i).setAvailable("NO");
			}

		}
		lv = (ListView) findViewById(R.id.list);
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

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		Log.i("MakeMachine", "resultCode: " + resultCode);
		switch (resultCode) {
		case 0:
			mposition = -1;
			Log.i("MakeMachine", "User cancelled");
			break;

		case -1:

			if (new File("/mnt/sdcard/PepsicoImages/" + _pathforcheck).exists())

				img1 = _pathforcheck;
			lv.invalidateViews();

			break;
		}

	}

	private static class MyAdapter extends BaseAdapter {
		private LayoutInflater mInflater;
		Context mcontext;

		public MyAdapter(Context context) {
			mInflater = LayoutInflater.from(context);
			mcontext = context;
		}

		public int getCount() {
			return promo_list.size();
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
				convertView = mInflater.inflate(R.layout.promotion_list, null);
				holder = new ViewHolder();

				holder.promo = (TextView) convertView.findViewById(R.id.promo);

				holder.type = (TextView) convertView.findViewById(R.id.type);

				holder.remarks = (TextView) convertView
						.findViewById(R.id.remarks);
				holder.presence = (ToggleButton) convertView
						.findViewById(R.id.toggleButton);

				holder.camera = (ImageView) convertView
						.findViewById(R.id.image);

				convertView.setTag(holder);

			} else {

				holder = (ViewHolder) convertView.getTag();

			}

			holder.camera.setId(position);

			holder.remarks.setId(position);

			holder.promo
					.setText(promo_list.get(position).getPromo().toString());

			holder.type.setText(promo_list.get(position).getPromotype()
					.toString());

			holder.presence.setText(promo_list.get(position).getAvailable()
					.toString());

			holder.remarks.setText(promo_list.get(position).getRemarks()
					.toString());

			holder.camera.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub

					mposition = position;
					_pathforcheck = store_id + "Promotion_pepsi" + getTime()
							+ username + "image1" + ".jpg";

					_path = Environment.getExternalStorageDirectory()
							+ "/PepsicoImages/" + _pathforcheck;

					startCameraActivity();

				}
			});

			holder.camera
					.setOnLongClickListener(new View.OnLongClickListener() {

						@Override
						public boolean onLongClick(View v) {
							// TODO Auto-generated method stub

							Intent intent = new Intent(
									(EditPromotinPepsiActivity) mcontext,
									EditImagePromotionActivity.class);
							intent.putExtra("image1path",
									promo_list.get(position).getImage());
							((EditPromotinPepsiActivity) mcontext)
									.startActivity(intent);
							return false;
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
									promo_list.get(position).setRemarks("");

								} else {

									promo_list.get(position).setRemarks(value1);
								}

							}
						}
					});

			holder.presence.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub

					String val = holder.presence.getText().toString();
					promo_list.get(position).setAvailable(val);
					if (val.equalsIgnoreCase("YES")) {

						holder.camera.setEnabled(true);
						holder.remarks.setText("");
						promo_list.get(position).setRemarks("");
						holder.remarks.setEnabled(false);
						holder.camera
								.setBackgroundResource(R.drawable.listcamera);

						/*
						 * if (!img1.equalsIgnoreCase("")) { if (position ==
						 * mposition) {
						 * promo_list.get(position).setCamera("YES");
						 * promo_list.get(position).setImage(img1);
						 * 
						 * } }
						 */

						if (promo_list.get(position).getCamera()
								.equalsIgnoreCase("NO")) {
							holder.camera
									.setBackgroundResource(R.drawable.listcamera);
						} else {
							holder.camera
									.setBackgroundResource(R.drawable.listcameratick);
						}

					}
					if (val.equalsIgnoreCase("NO")) {

						holder.camera.setEnabled(false);
						holder.remarks.setEnabled(true);
						promo_list.get(position).setCamera("NO");
						promo_list.get(position).setImage("");
						holder.camera
								.setBackgroundResource(R.drawable.listcameralocked);

						/*
						 * if (position == mposition) {
						 * promo_list.get(position).setCamera("NO");
						 * promo_list.get(position).setImage("");
						 * 
						 * }
						 */
					}

				}
			});

			if (promo_list.get(position).getAvailable().toString()
					.equalsIgnoreCase("YES")) {
				holder.presence.setChecked(true);
				holder.camera.setEnabled(true);
				holder.remarks.setEnabled(false);
				holder.remarks.setText("");
				promo_list.get(position).setRemarks("");
				holder.camera.setBackgroundResource(R.drawable.listcamera);

				if (!img1.equalsIgnoreCase("")) {
					if (position == mposition) {
						promo_list.get(position).setCamera("YES");
						promo_list.get(position).setImage(img1);
						img1 = "";

					}

				}

				if (promo_list.get(position).getCamera().equalsIgnoreCase("NO")) {
					holder.camera.setBackgroundResource(R.drawable.listcamera);
				} else {
					holder.camera
							.setBackgroundResource(R.drawable.listcameratick);
				}

			} else {
				holder.presence.setChecked(false);
				holder.camera.setEnabled(false);
				promo_list.get(position).setCamera("NO");
				promo_list.get(position).setImage("");
				holder.remarks.setEnabled(true);
				holder.camera
						.setBackgroundResource(R.drawable.listcameralocked);

			}

			if (promo_list.get(position).getCamera().toString()
					.equalsIgnoreCase("YES")) {
				holder.camera.setBackgroundResource(R.drawable.listcameratick);
			} else {
				// holder.camera.setBackgroundResource(R.drawable.listcamera);
			}

			return convertView;
		}

		static class ViewHolder {
			TextView promo, type, remarks;
			ToggleButton presence;
			ImageView camera;
		}

		public String getTime() {
			Calendar m_cal = Calendar.getInstance();
			int hour = m_cal.get(Calendar.HOUR_OF_DAY);
			int min = m_cal.get(Calendar.MINUTE);
			int sec = m_cal.get(Calendar.SECOND);
			return hour + "-" + min + "-" + sec;

		}

		protected void startCameraActivity() {

			try {
				Log.i("MakeMachine", "startCameraActivity()");
				File file = new File(_path);
				Uri outputFileUri = Uri.fromFile(file);

				Intent intent = new Intent(
						android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
				intent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
				((Activity) mcontext).startActivityForResult(intent, 0);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void onButtonClick(View v) {
		switch (v.getId()) {
		case R.id.training_intellogo:
			Intent intent = new Intent(this, ShareOfShelfActivity.class);

			startActivity(intent);

			EditPromotinPepsiActivity.this.finish();
			break;

		case R.id.save:
			lv.clearFocus();
			if (validate_data()) {
				Toast.makeText(getApplicationContext(),
						AlertMessage.MESSAGE_INVALID_DATA, Toast.LENGTH_SHORT)
						.show();
			} else {

				AlertDialog.Builder builder = new AlertDialog.Builder(
						EditPromotinPepsiActivity.this);
				builder.setMessage("Do you want to update the data ")
						.setCancelable(false)
						.setPositiveButton("OK",
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int id) {
										mposition = -1;

										alert.getButton(
												AlertDialog.BUTTON_POSITIVE)
												.setEnabled(false);

										database.open();
										database.UpdatePromotionData(store_id,
												promo_list);

										Intent i = new Intent(
												getApplicationContext(),
												DailyentryMenuActivity.class);
										startActivity(i);
										EditPromotinPepsiActivity.this.finish();

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

	public boolean validate_data() {
		boolean result = false;

		for (int i = 0; i < promo_list.size(); i++) {
			if (promo_list.get(i).getAvailable().equalsIgnoreCase("NO")) {
				if (promo_list.get(i).getRemarks().equalsIgnoreCase("")) {
					result = true;

					break;

				}
			}
			
			if (promo_list.get(i).getAvailable().equalsIgnoreCase("YES")) {
				if (promo_list.get(i).getImage().equalsIgnoreCase("")) {
					result = true;

					break;

				}
			}
			
			
		}
		return result;

	}
	
	
	public boolean validate_dataImage() {
		boolean result = false;

		for (int i = 0; i < promo_list.size(); i++) {
			if (promo_list.get(i).getAvailable().equalsIgnoreCase("YES")) {
				if (promo_list.get(i).getImage().equalsIgnoreCase("")) {
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

	public static String getCurrentTime() {

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

		EditPromotinPepsiActivity.this.finish();
	}

}
