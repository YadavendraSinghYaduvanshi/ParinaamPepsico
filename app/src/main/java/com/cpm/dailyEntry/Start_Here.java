package com.cpm.dailyEntry;



import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.cpm.Constants.CommonString;
import com.cpm.database.PepsicoDatabase;
import com.cpm.delegates.AssetDelegate;
import com.cpm.delegates.BrandWiseTarget;
import com.cpm.delegates.PromotionViewGetterSetter;
import com.cpm.parinaampepsico.MainMenuActivity;
import com.cpm.parinaampepsicoo.R;


public class Start_Here extends Activity {
	private SharedPreferences preferences = null;
	private SharedPreferences.Editor editor = null;
	ArrayList<AssetDelegate> asset;
	ArrayList<PromotionViewGetterSetter> promotion;
	ArrayList<BrandWiseTarget> brandWiseTarget;
	String store_id,store_name,visit_date,username;
	PepsicoDatabase database;
	ListView list1,list2,list3;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	
		super.onCreate(savedInstanceState);
		setContentView(R.layout.start_here);
		
		preferences = PreferenceManager.getDefaultSharedPreferences(this);
		store_name = preferences.getString(CommonString.KEY_STORE_NAME, "");
		store_id = preferences.getString(CommonString.KEY_STORE_ID, "");
		visit_date = preferences.getString(CommonString.KEY_DATE, "");
		username = preferences.getString(CommonString.KEY_USERNAME, "");
		
		database = new PepsicoDatabase(this);
		database.open();
		
	    	findViewById(R.id.dailyEntry).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(Start_Here.this, DailyentryMenuActivity.class);
				startActivity(i);
				Start_Here.this.finish();
			}
		});
		
          findViewById(R.id.kpireport).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(Start_Here.this, kpi_Report_Screen.class);
				startActivity(i);
				Start_Here.this.finish();
			}
		});
         findViewById(R.id.targetdata).setOnClickListener(new OnClickListener() {
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent i = new Intent(Start_Here.this, TargetViewScreen.class);
		startActivity(i);
		Start_Here.this.finish();
	}
});
	}
	
	@Override
	protected void onStart() {
	super.onStart();
    Context con =	getApplicationContext();
	promotion = database.getPromotionData(store_id);
	asset = database.getAssetDataforview(store_id);
	brandWiseTarget = database.getBrandWiseData();

	
	
	}
	
	
	
	
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		Intent i = new Intent(Start_Here.this, MainMenuActivity.class);
		startActivity(i);
		Start_Here.this.finish();
		
	}
	
	
	
}
