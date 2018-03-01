package com.cpm.dailyEntry;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.cpm.Constants.CommonString;
import com.cpm.database.PepsicoDatabase;
import com.cpm.delegates.CategoryBean;
import com.cpm.delegates.CategoryImageBean;
import com.cpm.delegates.CoverageBean;
import com.cpm.delegates.StoreBean;
import com.cpm.delegates.VericalBrandBean;
import com.cpm.parinaampepsicoo.R;

public class ShareOfShelfActivity extends Activity implements
		OnItemSelectedListener {

	private Spinner verticalspinner, categoryspinner;
	protected boolean _taken;
	private PepsicoDatabase database;
	private ArrayAdapter<CharSequence> verticleadapter, categoryadapter;
	private ArrayList<VericalBrandBean> vertical_list = new ArrayList<VericalBrandBean>();
	private ArrayList<CategoryBean> category_list = new ArrayList<CategoryBean>();
	private ArrayList<CategoryBean> category_count_list = new ArrayList<CategoryBean>();
	private ArrayList<VericalBrandBean> sku_list = new ArrayList<VericalBrandBean>();
	
	
	
	private ArrayList<VericalBrandBean> sku_list_forcompetitor = new ArrayList<VericalBrandBean>();
	
	private ArrayList<VericalBrandBean> checkbrand_list = new ArrayList<VericalBrandBean>();
	private ArrayList<VericalBrandBean> COMPETITOR_brandLIST = new ArrayList<VericalBrandBean>();
	private ArrayList<VericalBrandBean> other_brandLIST = new ArrayList<VericalBrandBean>();
	public ListView lv, lv2;
	public static int pos[];
	private String verticalname, verticalid, cateroryid, categoryname,
			visit_date, store_id, username, intime;
	private SharedPreferences preferences;
	private Bundle extras;
	Button save;
	public String store_name;
	String category, ver_id, cateogry_edit;
	int vertical_pos, cat_pos;
	ImageView camera;

	protected String _path;
	protected String _path1;
	protected String _path2;
	static String img1 = "";
	static String img2 = "";
	static String img3 = "";
	protected String _pathforcheck = "";
	protected String _path1forcheck = "";
	protected String _path2forcheck = "";
	protected static final String PHOTO_TAKEN = "photo_taken";
	Button savebtn;
	private String image1 = "";

	private String image2 = "";

	String TempDate = "";
	String _UserId;
	private String image3 = "";
	public static final int POPUP_SELECT = 1;
	CategoryImageBean categry_img = new CategoryImageBean();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.new_share_of_shelf);

		camera = (ImageView) findViewById(R.id.pepsicolistview_3ccameralist);

		camera.setVisibility(View.INVISIBLE);

		verticalspinner = (Spinner) findViewById(R.id.vertical);
		categoryspinner = (Spinner) findViewById(R.id.category);
		lv = (ListView) findViewById(R.id.list);
		lv2 = (ListView) findViewById(R.id.count_list);

		preferences = PreferenceManager.getDefaultSharedPreferences(this);
		_UserId = preferences.getString(CommonString.KEY_USERNAME, ""); 
		preferences = PreferenceManager.getDefaultSharedPreferences(this);
		store_name = preferences.getString(CommonString.KEY_STORE_NAME, "");
		store_id = preferences.getString(CommonString.KEY_STORE_ID, "");
		visit_date = preferences.getString(CommonString.KEY_DATE, "");
		category = preferences.getString(CommonString.KEY_CATEGORY_ID, "");
		ver_id = preferences.getString(CommonString.KEY_VERTICAL_ID, "");

		vertical_pos = preferences.getInt("skuvertical_position", 0);
		cat_pos = preferences.getInt("skucategory_position", 0);

		cateogry_edit = preferences.getString("Cateogry_Edit", "");

		database = new PepsicoDatabase(this);
		database.open();

		category_count_list = database.getCategoryVerticalMapping();
		if (category_count_list.size() > 0) {
			lv2.setVisibility(View.VISIBLE);
			lv2.setAdapter(new CountAdapter(getApplicationContext()));
		} else {
			lv2.setVisibility(View.INVISIBLE);
		}

		checkbrand_list = database.getBrandList(store_id,
				CommonString.KEY_pepsi);
		COMPETITOR_brandLIST = database.getBrandList(store_id,
				CommonString.KEY_COMPETITOR);
		other_brandLIST = database.getBrandList(store_id,
				CommonString.KEY_other);

		TextView text1 = (TextView) findViewById(R.id.mainpage_remembermetext);
		text1.setText(store_name);

		verticleadapter = new ArrayAdapter<CharSequence>(this,
				android.R.layout.simple_spinner_item);
		categoryadapter = new ArrayAdapter<CharSequence>(this,
				android.R.layout.simple_spinner_item);

		verticleadapter.add("Select Vertical");
		categoryadapter.add("Select Category");

		verticleadapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		categoryadapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		verticalspinner.setOnItemSelectedListener(this);
		categoryspinner.setOnItemSelectedListener(this);

		camera.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				database.open();
				categry_img = database.getImages(cateroryid, store_id);

				image1 = categry_img.getImg1();
				image2 = categry_img.getImg2();
				image3 = categry_img.getImg3();

				Intent in = new Intent(getApplicationContext(),
						ImageSosActivity.class);
				in.putExtra(CommonString.KEY_CATEGORY_ID, cateroryid);
				in.putExtra("image1path", image1);
				in.putExtra("image2path", image2);
				in.putExtra("image3path", image3);

				startActivityForResult(in, POPUP_SELECT);
			}
		});

		intime = getCurrentTime();

		vertical_list = database.getVerticalLevelMasterData();

		for (int i = 0; i < vertical_list.size(); i++) {
			verticleadapter.add(vertical_list.get(i).getVertical());
		}

		verticalspinner.setAdapter(verticleadapter);
		categoryspinner.setAdapter(categoryadapter);

		preferences = PreferenceManager.getDefaultSharedPreferences(this);
		visit_date = preferences.getString(CommonString.KEY_DATE, null);
		username = preferences.getString(CommonString.KEY_USERNAME, null);

		if (cateogry_edit.equalsIgnoreCase("Y")) {

			try {

				sku_list = database.getBrandJoinData(store_id, category);
				lv2.setVisibility(View.GONE);
				lv.setAdapter(new MyAdapter(getApplicationContext()));
				verticalspinner.setSelection(vertical_pos);

				for (int i = 0; i < sku_list.size(); i++) {
					sku_list.get(i).setBrand_status("no");
					sku_list.get(i).setCompetitor_brandstatus("no");
					sku_list.get(i).setOther_brandstaus("no");
					sku_list.get(i).setNodataValidate("");
					
				}

				for (int i = 0; i < sku_list.size(); i++) {
					for (int j = 0; j < checkbrand_list.size(); j++) {
						if (sku_list
								.get(i)
								.getBrandid()
								.equalsIgnoreCase(
										checkbrand_list.get(j).getBrandid())) {
							sku_list.get(i).setBrand_status("YES");
							break;
						} else {
							sku_list.get(i).setBrand_status("no");
						}

					}
				}

				for (int i = 0; i < sku_list.size(); i++) {
					for (int j = 0; j < COMPETITOR_brandLIST.size(); j++) {
						
						
						
						if (sku_list
								.get(i)
								.getBrandid()
								.equalsIgnoreCase(
										COMPETITOR_brandLIST.get(j)
												.getBrandid())) {
							sku_list.get(i).setCompetitor_brandstatus("YES");
							
							
							
							
							
							
							break;
						} else 
						{
							sku_list.get(i).setCompetitor_brandstatus("no");
							
						
							
							
							
						}

					}
				}
				
				
				
				for (int i = 0; i < sku_list.size(); i++) 
				
				{
					
					sku_list_forcompetitor = database.getSkuCompetitorData(sku_list
							.get(i)
							.getBrandid(), store_id);
					
					if(sku_list_forcompetitor.size()>0)
					{
						sku_list.get(i).setCompetitor_brandstatus("no");
					}
					else
					{
						sku_list.get(i).setCompetitor_brandstatus("YES");
					}
				}
				
				
				
				
				
				

				for (int i = 0; i < sku_list.size(); i++) {
					for (int j = 0; j < other_brandLIST.size(); j++) {
						if (sku_list
								.get(i)
								.getBrandid()
								.equalsIgnoreCase(
										other_brandLIST.get(j)
												.getBrandid())) {
							sku_list.get(i).setOther_brandstaus("YES");
							break;
						} else {
							sku_list.get(i).setOther_brandstaus("no");
						}

					}
				}

				
				category_list = database.getCategoryVerticalMapping(ver_id);

				for (int i = 0; i < category_list.size(); i++) {
					categoryadapter.add(category_list.get(i).getCategoryname());
				}

				categoryspinner.setAdapter(categoryadapter);
				categoryspinner.setSelection(cat_pos);

			} catch (Exception e) {

			}

		}

		lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View item,
					int position, long id) {

				preferences = PreferenceManager
						.getDefaultSharedPreferences(ShareOfShelfActivity.this);
				SharedPreferences.Editor editor = preferences.edit();
				editor.putString(CommonString.KEY_BRAND_ID,
						sku_list.get(position).getBrandid());
				editor.putString(CommonString.KEY_BRAND_NAME,
						sku_list.get(position).getBrand());

				editor.commit();

				if (database.CheckBranddata(
						sku_list.get(position).getBrandid(), store_id) == 0) {

					if (sku_list.get(position).getBrand().startsWith("Others")) {
						Intent i = new Intent(getApplicationContext(),
								SkuOtherActivity.class);
						startActivity(i);
						ShareOfShelfActivity.this.finish();
					} else {

						Intent i = new Intent(getApplicationContext(),
								SkuTabActivity.class);
						startActivity(i);
						ShareOfShelfActivity.this.finish();
					}
				}

				else {

					if (sku_list.get(position).getBrand().startsWith("Others")) {
						Intent i = new Intent(getApplicationContext(),
								EditSkuOtherActivity.class);
						startActivity(i);
						ShareOfShelfActivity.this.finish();
					} else {

						Intent i = new Intent(getApplicationContext(),
								EditSkuTabActivity.class);
						startActivity(i);
						ShareOfShelfActivity.this.finish();
					}
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

	public void onButtonClick(View v) {
		switch (v.getId()) {
		case R.id.training_intellogo:

			Intent i = new Intent(this, DailyentryMenuActivity.class);
			i.putExtra(CommonString.KEY_STORE_ID, store_id);
			i.putExtra(CommonString.KEY_VISIT_DATE, visit_date);
			startActivity(i);

			ShareOfShelfActivity.this.finish();
			break;

		}
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
	public void onBackPressed() {
		// TODO Auto-generated method stub

		Intent i = new Intent(this, DailyentryMenuActivity.class);
		startActivity(i);
		ShareOfShelfActivity.this.finish();
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

		public View getView(int position, View convertView, ViewGroup parent) {
			final ViewHolder holder;
			if (convertView == null) {

				convertView = mInflater.inflate(R.layout.brandlistview, null);
				holder = new ViewHolder();

				holder.sku = (TextView) convertView.findViewById(R.id.sku_id);
				holder.image = (ImageView) convertView.findViewById(R.id.image);

				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}

			holder.sku.setText(sku_list.get(position).getBrand().toString());

			if (sku_list.get(position).getBrand_status()
					.equalsIgnoreCase("yes")&& ((sku_list.get(position).getCompetitor_brandstatus()
					.equalsIgnoreCase("yes"))))
			
			{
				holder.image.setBackgroundResource(R.drawable.green);
				holder.image.setVisibility(View.VISIBLE);
			} 
			
			else if(sku_list.get(position).getBrand_status()
					.equalsIgnoreCase("yes")&& ((sku_list.get(position).getCompetitor_brandstatus()
					.equalsIgnoreCase("no"))))
			{
				
				holder.image.setBackgroundResource(R.drawable.yelloww);
				holder.image.setVisibility(View.VISIBLE);
				
			}
				
			
			
			else if (sku_list.get(position).getCompetitor_brandstatus()
					.equalsIgnoreCase("yes")&&(!sku_list.get(position).getNodataValidate()
					.equalsIgnoreCase("nodata"))) {
			
				
				
				holder.image.setBackgroundResource(R.drawable.red);
				holder.image.setVisibility(View.VISIBLE);
			}
			else if (sku_list.get(position).getOther_brandstaus()
					.equalsIgnoreCase("yes")) {
				holder.image.setBackgroundResource(R.drawable.green);
				holder.image.setVisibility(View.VISIBLE);
			}
			else {
				holder.image.setVisibility(View.INVISIBLE);
			}

			return convertView;
		}

		class ViewHolder {
			TextView sku;
			ImageView image;

		}

	}

	private class CountAdapter extends BaseAdapter {
		private LayoutInflater mInflater;
		private Context mcontext;

		public CountAdapter(Context context) {
			mInflater = LayoutInflater.from(context);
			mcontext = context;
		}

		public int getCount() {
			return category_count_list.size();

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

				convertView = mInflater.inflate(R.layout.countlistview, null);
				holder = new ViewHolder();

				holder.sku = (TextView) convertView.findViewById(R.id.sku_id);
				holder.image = (TextView) convertView.findViewById(R.id.image);

				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}

			holder.sku.setText(category_count_list.get(position).getSkuname());
			holder.image.setText(category_count_list.get(position).getSkuqty());

			return convertView;
		}

		class ViewHolder {
			TextView sku;
			TextView image;

		}

	}

	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int position,
			long arg3) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.vertical:
			if (position > 0) {

				try {
					preferences = PreferenceManager
							.getDefaultSharedPreferences(this);

					String cateogry_edit = preferences.getString(
							"Cateogry_Edit", "");

					if (cateogry_edit.equalsIgnoreCase("Y")) {

						SharedPreferences.Editor editor = preferences.edit();

						editor.putString("Cateogry_Edit", "N");

						editor.commit();

					} else

					{

						verticalname = vertical_list.get(position - 1)
								.getVertical();
						verticalid = vertical_list.get(position - 1)
								.getVerticalid();
						camera.setVisibility(View.INVISIBLE);
						category_list.clear();
						categoryadapter.clear();
						sku_list.clear();
						lv2.setVisibility(View.GONE);
						lv.setAdapter(new MyAdapter(getApplicationContext()));

						SharedPreferences.Editor editor = preferences.edit();
						editor.putInt("skuvertical_position", position);
						editor.putString(CommonString.KEY_VERTICAL_ID,
								verticalid);

						editor.commit();

						categoryadapter.add("Select Category");
						database = new PepsicoDatabase(this);
						database.open();
						category_list = database
								.getCategoryVerticalMapping(verticalid);
						for (int i = 0; i < category_list.size(); i++) {
							categoryadapter.add(category_list.get(i)
									.getCategoryname());
						}
						categoryspinner.setAdapter(categoryadapter);

					}

				} catch (Exception e) {

				}
			} else {
				verticalname = "";
				verticalid = "";
			}
			break;
		case R.id.category:
			if (position > 0) {

				try {

					categoryname = category_list.get(position - 1)
							.getCategoryname();
					cateroryid = category_list.get(position - 1)
							.getCategoryid();

					database.open();
					categry_img = database.getImages(cateroryid, store_id);

					image1 = categry_img.getImg1();
					image2 = categry_img.getImg2();
					image3 = categry_img.getImg3();

					camera.setVisibility(View.VISIBLE);
					camera.setBackgroundResource(R.drawable.camera_list);

					if ((image1 != null && !image1.equals(""))
							|| (image2 != null && !image2.equals(""))
							|| (image3 != null && !image3.equals(""))) {

						camera.setBackgroundResource(R.drawable.camera_list_tick);

					} else {
						camera.setBackgroundResource(R.drawable.camera_list);
					}

					sku_list.clear();

					SharedPreferences.Editor editor = preferences.edit();
					editor.putString(CommonString.KEY_CATEGORY_ID, cateroryid);
					editor.putInt("skucategory_position", position);
					editor.commit();
					
					sku_list = database.getBrandJoinData(store_id, cateroryid);
					
					for (int i = 0; i < sku_list.size(); i++) {
						sku_list.get(i).setBrand_status("no");
						sku_list.get(i).setCompetitor_brandstatus("no");
						sku_list.get(i).setOther_brandstaus("no");
						sku_list.get(i).setNodataValidate(""); 
						
					}

					for (int i = 0; i < sku_list.size(); i++) {
						for (int j = 0; j < checkbrand_list.size(); j++) {
							if (sku_list
									.get(i)
									.getBrandid()
									.equalsIgnoreCase(
											checkbrand_list.get(j).getBrandid())) {
								sku_list.get(i).setBrand_status("YES");
								break;
							} else {
								sku_list.get(i).setBrand_status("no");
							}

						}
					}
					for (int i = 0; i < sku_list.size(); i++) {
						for (int j = 0; j < COMPETITOR_brandLIST.size(); j++) {
							if (sku_list
									.get(i)
									.getBrandid()
									.equalsIgnoreCase(
											COMPETITOR_brandLIST.get(j)
													.getBrandid())) {
								sku_list.get(i)
										.setCompetitor_brandstatus("YES");
								break;
							} else {
								sku_list.get(i).setCompetitor_brandstatus("no");
							}

						}
					}
					for (int i = 0; i < sku_list.size(); i++) {
						for (int j = 0; j < other_brandLIST.size(); j++) {
							if (sku_list
									.get(i)
									.getBrandid()
									.equalsIgnoreCase(
											other_brandLIST.get(j)
													.getBrandid())) {
								sku_list.get(i).setOther_brandstaus("YES");
								break;
							} else {
								sku_list.get(i).setOther_brandstaus("no");
							}

						}
					}

					
					
					for (int i = 0; i < sku_list.size(); i++) 
						
					{
						
						sku_list_forcompetitor = database.getSkuCompetitorData(sku_list
								.get(i)
								.getBrandid(), store_id);
						
					
						if(sku_list_forcompetitor.size()==0)
						{
							sku_list.get(i).setCompetitor_brandstatus("Yes");
							sku_list.get(i).setNodataValidate("nodata");
							
						}
					}
					
					
					lv2.setVisibility(View.GONE);
					lv.setAdapter(new MyAdapter(getApplicationContext()));

				} catch (Exception e) {

				}

			} else {
				categoryname = "";
				cateroryid = "";
			}
			break;
		}

	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub

	}

	public long checkMid() {
		return database.CheckMid(visit_date, store_id);
	}

	public long getMid() {

		long mid = 0;

		mid = checkMid();

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

	public void onActivityResult(int requestCode, int resultCode, Intent data) {

		if (requestCode == POPUP_SELECT) {

			if (resultCode == RESULT_OK) {
				img1 = "";
				img2 = "";
				img3 = "";
				img1 = data.getStringExtra(CommonString.KEY_IMAGE_PATH1);
				img2 = data.getStringExtra(CommonString.KEY_IMAGE_PATH2);
				img3 = data.getStringExtra(CommonString.KEY_IMAGE_PATH3);

				if ((img1 != null && !img1.equals(""))
						|| (img2 != null && !img2.equals(""))
						|| (img3 != null && !img3.equals("")))

				{

					camera.setBackgroundResource(R.drawable.camera_list_tick);

				} else {

				}

			}

		}

	}
}
