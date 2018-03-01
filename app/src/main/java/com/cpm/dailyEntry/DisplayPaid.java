package com.cpm.dailyEntry;

import java.util.ArrayList;
import java.util.Calendar;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.cpm.Constants.CommonString;
import com.cpm.database.PepsicoDatabase;
import com.cpm.delegates.CoverageBean;
import com.cpm.delegates.FinalDataBeanDisplay;
import com.cpm.delegates.PosmBean;
import com.cpm.message.AlertMessage;
import com.cpm.parinaampepsicoo.R;

public class DisplayPaid extends Activity {

	public String store_name;
	public static ListView lv;
	private static String store_id;
	private static String username,assetiDpref;
	public static int indexvalue;
	private static String intime;
	private String brand_id;
	private static String visit_date;
	private SharedPreferences preferences;
	private static PepsicoDatabase database;
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
	private static SharedPreferences preferences1 = null;
	private static SharedPreferences.Editor editor1 = null;
	
	public static ArrayList<FinalDataBeanDisplay> updatedData;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.display_paid);

		lv = (ListView) findViewById(R.id.list);

		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		intime = getCurrentTime();
		preferences = PreferenceManager.getDefaultSharedPreferences(this);
		store_name = preferences.getString(CommonString.KEY_STORE_NAME, "");
		username = preferences.getString(CommonString.KEY_USERNAME, "");
		store_id = preferences.getString(CommonString.KEY_STORE_ID, "");
		brand_id = preferences.getString(CommonString.KEY_BRAND_ID, "");
		visit_date = preferences.getString(CommonString.KEY_DATE, "");
		preferences1 = PreferenceManager.getDefaultSharedPreferences(this);
		
		
		editor1 = preferences1.edit();
		editor1.putString("assetID", null);
		editor1.commit();
	
		
		TextView text1 = (TextView) findViewById(R.id.mainpage_remembermetext);
		text1.setText(store_name);

		database = new PepsicoDatabase(this);
		database.open();

		sku_list = database.getAssets(store_id);
		
	//doing for purity
		
		purity = new String[sku_list.size()];
		
		for(int i=0;i<sku_list.size();i++){
			sku_list.get(i).setAssetpure("False");
		}
		presence = new String[sku_list.size()];

		for (int i = 0; i < sku_list.size(); i++) {

			presence[i] = "-1";
			sku_list.get(i).setAvailable("NO");

		}
		updatedData = database.getDisplayTransactionData_chillars_StoreId(store_id);
		
		if(updatedData.size()>0){
      	   for(int i=0 ; i < sku_list.size(); i++)
      		   for(int j =0;j< updatedData.size();j++){
      			   if(sku_list.get(i).getAssetid()!=null){
      				   if((sku_list.get(i).getAssetid().equals(updatedData.get(j).getAssets_Id()))&&(sku_list.get(i).getAutoId().equalsIgnoreCase(updatedData.get(j).getAuto_Id()))){
      					 sku_list.get(i).setAutoId(updatedData.get(j).getAuto_Id());
      					   sku_list.get(i).setRemarks(updatedData.get(j).getRemark());
          	          sku_list.get(i).setAvailable(updatedData.get(j).getAvailable());
          	        sku_list.get(i).setAssetpure(updatedData.get(j).getAsset_Pure());
          	      sku_list.get(i).setUpdatestaus("update");
          	    sku_list.get(i).setImage1(updatedData.get(j).getImage_Path1());
      	        sku_list.get(i).setImage2(updatedData.get(j).getImage_Path2());
      	      sku_list.get(i).setImage3(updatedData.get(j).getImage_Path3());
            		   }  
      			   }
      			 
      		   }
      		   
      	   
         }
		
		
		  for(int i=0 ; i < sku_list.size(); i++)
		  {
			  
			  if(sku_list.get(i).getUpdatestaus()==null)
					  {
				  sku_list.get(i).setUpdatestaus("Add");
					  }
			  
			 
			  
		  }
		
		
	
		
		

		pos = new int[sku_list.size()];

		for (int i = 0; i < sku_list.size(); i++) {

			pos[i] = -1;
			if(sku_list.get(i).getRemarks()==null){
				
				sku_list.get(i).setRemarks("");
				
			}
			//

		}

		camera = new int[sku_list.size()];

		for (int i = 0; i < sku_list.size(); i++) {

			camera[i] = -1;
			sku_list.get(i).setCamera("NO");

		}

		image1 = new int[sku_list.size()];

		for (int i = 0; i < sku_list.size(); i++) {

			camera[i] = -1;
			if(sku_list.get(i).getImage1()==null){
				sku_list.get(i).setImage1("");
			}
			

		}

		image2 = new int[sku_list.size()];

		for (int i = 0; i < sku_list.size(); i++) {

			camera[i] = -1;
			if(sku_list.get(i).getImage2()==null){
				sku_list.get(i).setImage2("");
			}

		}

		image3 = new int[sku_list.size()];

		for (int i = 0; i < sku_list.size(); i++) {

			camera[i] = -1;
			if(sku_list.get(i).getImage3()==null){
				sku_list.get(i).setImage3("");
			}

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
				convertView = mInflater.inflate(R.layout.displaypaidadapter, null);
				holder = new ViewHolder();

				indexvalue=position;
				
				holder.sku = (TextView) convertView.findViewById(R.id.sku);
				holder.brandname= (TextView)convertView.findViewById(R.id.brandname);
				holder.assets = (TextView) convertView
						.findViewById(R.id.assest);

				holder.listColorChange = (LinearLayout)convertView.findViewById(R.id.flexviewlist_contentarea);
				
				holder.type = (TextView) convertView.findViewById(R.id.type);

				holder.presence = (ToggleButton) convertView
						.findViewById(R.id.toggleButton1);
				holder.pure = (ToggleButton) convertView
						.findViewById(R.id.togglepure);
				
				holder.remarks = (EditText) convertView
						.findViewById(R.id.remarks);

				holder.camera = (ImageView) convertView
						.findViewById(R.id.cameraicon);
				holder.btn_add = (Button) convertView
						.findViewById(R.id.addetail);


				convertView.setTag(holder);

			} else {

				holder = (ViewHolder) convertView.getTag();

			}
		
		//	 holder.pure.setText(sku_list.get(position).getAssetpure().toString());
			 
			assetiDpref = preferences1.getString("assetID", null);
			if(assetiDpref!=null){
			if(assetiDpref.equalsIgnoreCase(String.valueOf(position)))
			{
				holder.remarks.setEnabled(true);
				holder.presence.setEnabled(true);
				holder.presence.setChecked(true);
				holder.listColorChange.setBackgroundColor(Color.WHITE);
				
			}
			else
			{
				holder.remarks.setEnabled(false);
				holder.presence.setEnabled(false);
				holder.presence.setChecked(false);
				holder.listColorChange.setBackgroundColor(Color.RED);
				
			}
			}
			
			 
			 
			 

			holder.assets
					.setText(sku_list.get(position).getAssest().toString());

			holder.type
					.setText(sku_list.get(position).getVertical().toString());
//			
			
			

			holder.camera.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent intent = new Intent((DisplayPaid) mcontext,
							ImageAssetsActivity.class);
					int position = v.getId();
					intent.putExtra("position", position);
					intent.putExtra("image1path", sku_list.get(position)
							.getImage1());
					intent.putExtra("image2path", sku_list.get(position)
							.getImage2());
					intent.putExtra("image3path", sku_list.get(position)
							.getImage3());
					((DisplayPaid) mcontext).startActivityForResult(
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
									//editor1.putString("assetID", sku_list.get(position).getAssetid());
									
									editor1.putString("assetID", String.valueOf(position));
									editor1.commit();
									lv.invalidateViews();
								}
								
							}
						}
					});
			
			holder.btn_add.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					lv.clearFocus();
					if(sku_list.get(position).getAvailable().equalsIgnoreCase("Yes")){
						if(validate_image())
						{
							Toast.makeText(mcontext, "please capture the image", 100).show();
						}else{
							mposition = -1;
							database.open();
							database.InsertDisplayTempData(getMid(),
									store_id, sku_list);
							Intent intent = new Intent(mcontext,DisplayPaidDetail.class);
							intent.putExtra("case", "Yes");
							intent.putExtra("index", String.valueOf(position));
							intent.putExtra("assetidYes",String.valueOf(sku_list.get(position).getAssetid()));
							intent.putExtra("SECONDARY_KEY", String.valueOf(sku_list.get(position).getSECONDARYKEY()));
							
							intent.putExtra("brandId", String.valueOf(sku_list.get(position).getBrand_id()));
							
							
							mcontext.startActivity(intent);
						}
						
					}else{
						if(!sku_list.get(position).getRemarks().equalsIgnoreCase("")){
						mposition = -1;
						database.open();
						database.InsertDisplayTempData(getMid(),
								store_id, sku_list);
						Intent intent = new Intent(mcontext,DisplayPaidDetail.class);
						intent.putExtra("case", "No");
						intent.putExtra("SECONDARY_KEY", String.valueOf(sku_list.get(position).getSECONDARYKEY()));
						intent.putExtra("assetid",String.valueOf(sku_list.get(position).getAssetid()));
						intent.putExtra("asset",String.valueOf(sku_list.get(position).getAssest()));
						intent.putExtra("remark",String.valueOf(sku_list.get(position).getRemarks()));
						intent.putExtra("verticalid",String.valueOf(sku_list.get(position).getVerticalid()));
						intent.putExtra("brandId", String.valueOf(sku_list.get(position).getBrand_id()));
						mcontext.startActivity(intent);
						}else{
							Toast.makeText(mcontext, "please fill data", 100).show();
						}
					}
				}
			});

//			if (pos[position] == 0) {
//
//				holder.remarks.setText(sku_list.get(position).getRemarks());
//			}
//			if (pos[position] == -1) {
//
//				holder.remarks.setText("");
//
//			}

			
			holder.pure.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub

					//purity[position] = "0";
					String val = holder.pure.getText().toString();
					if(val.equalsIgnoreCase("YES")){
						val = "True";
					}else{
						val = "False";
					}
					sku_list.get(position).setAssetpure(val);

				}
			});
			
			
			if(sku_list.get(position).getDownlodassetpure().equalsIgnoreCase("false"))
			{
				holder.pure.setEnabled(false);
				holder.pure.setText("NO");
			}
			else
			{
				holder.pure.setEnabled(true);
				holder.pure.setText("YES");
			}
			if(sku_list.get(position).getAssetpure().equalsIgnoreCase("false"))
			{
				holder.pure.setEnabled(false);
				holder.pure.setText("NO");
				holder.pure.setChecked(false);
			}
			else
			{
				holder.pure.setEnabled(true);
				holder.pure.setText("YES");
				holder.pure.setChecked(true);
			}
			
			holder.presence.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub

					presence[position] = "0";
					String val = holder.presence.getText().toString();
					sku_list.get(position).setAvailable(val);
					if (val.equalsIgnoreCase("YES")) {
						
						
						if(sku_list.get(position).getDownlodassetpure().equalsIgnoreCase("false"))
						{
							holder.pure.setEnabled(false);
						}
						else
						{
							holder.pure.setEnabled(true);
						}
						
					//	holder.pure.setEnabled(true);
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
						holder.pure.setEnabled(false);
						holder.pure.setText("NO");
						holder.pure.setChecked(false);
						sku_list.get(position).setCamera("NO");
						sku_list.get(position).setImage1("");
						sku_list.get(position).setImage2("");
						sku_list.get(position).setImage3("");

					}
					//editor1.putString("assetID", sku_list.get(position).getAssetid());
					editor1.putString("assetID", String.valueOf(position));
					editor1.commit();
					lv.invalidateViews();
				}
			});

	
			if(sku_list.get(position).getAvailable().equalsIgnoreCase("no"))
			{
				
				holder.camera
				.setBackgroundResource(R.drawable.listcameralocked);
				holder.camera.setEnabled(false);
				holder.pure.setEnabled(false);
				holder.presence.setChecked(false);
				holder.remarks.setText(sku_list.get(position).getRemarks());
			}else{
				/*holder.camera
				.setBackgroundResource(R.drawable.listcameratick);*/
				holder.presence.setText("YES");
				holder.presence.setChecked(true);
				holder.remarks.setEnabled(false);
				holder.remarks.setText("");
				holder.camera.setEnabled(true);
			}
			 if((!sku_list.get(position).getImage1().equalsIgnoreCase(""))||(!sku_list.get(position).getImage2().equalsIgnoreCase(""))||(!sku_list.get(position).getImage3().equalsIgnoreCase(""))){
				 holder.camera
					.setBackgroundResource(R.drawable.listcameratick);
			 }
			
			
			if(mposition==position)
			{
			
			if(img1!=null || img2!=null || img3!=null)
			{
				sku_list.get(position).setImage1(img1);
				sku_list.get(position).setImage2(img2);
				sku_list.get(position).setImage3(img3);
			
				sku_list.get(position).setCamera("YES");
				
			}
			
			if(sku_list.get(position).getCamera().equalsIgnoreCase("yes"))
			{
				holder.camera
				.setBackgroundResource(R.drawable.listcameratick);
			}
			
			}
   			
			holder.btn_add.setText(sku_list.get(position).getUpdatestaus());
			
			if(sku_list.get(position).getUpdatestaus().equalsIgnoreCase("add")){
				
				holder.listColorChange.setBackgroundColor(Color.WHITE);
			}
			else
			{
				holder.listColorChange.setBackgroundColor(Color.GREEN);
			}
			
           holder.brandname.setText(sku_list.get(position).getBrand_name());
           
           if(sku_list.get(position).getBrand_name().equalsIgnoreCase("NA"))
        		   {
        	   holder.pure.setEnabled(false);
        		   }
           else
           
           {
        	   if(sku_list.get(position).getAvailable().equalsIgnoreCase("yes"))
        	   {
        		   holder.pure.setEnabled(true);   
        	   }
        	   
        	   
           }
           
            holder.brandname.setId(position);
			holder.remarks.setId(position);
			holder.camera.setId(position);
			holder.presence.setId(position);
			holder.pure.setId(position);
			holder.btn_add.setId(position);
			holder.listColorChange.setId(position);
			return convertView;
		}

		static class ViewHolder {
			TextView sku, assets, type,brandname;
			ToggleButton presence,pure;
			EditText remarks;
			ImageView camera;
			LinearLayout listColorChange;
			Button btn_add;
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

			DisplayPaid.this.finish();
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
							DisplayPaid.this);
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
											database.InsertDisplayTempData(getMid(),
													store_id, sku_list);

											Intent i = new Intent(
													getApplicationContext(),
													DailyentryMenuActivity.class);
											startActivity(i);
											DisplayPaid.this.finish();

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

	public static boolean validate_data() {
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

	public static boolean validate_image() {
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

	public static long getMid() {

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

		DisplayPaid.this.finish();
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		database.close();
	}

}
